package com.example.sqlvsnosql.service;

import com.example.sqlvsnosql.model.EmployeeMongo;
import com.example.sqlvsnosql.model.EmployeeMySQL;
import com.example.sqlvsnosql.model.EmployeeWithAllSalaries;
import com.example.sqlvsnosql.model.SalaryMySQL;
import com.example.sqlvsnosql.repository.mongo.EmployeesMongoRepository;
import com.example.sqlvsnosql.repository.mysql.EmployeesMySQLRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class EmployeesServiceImpl implements EmployeesService {

    private EmployeesMongoRepository employeesMongoRepository;
    private EmployeesMySQLRepository employeesMySQLRepository;
    private MongoTemplate mongoTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeesServiceImpl(EmployeesMongoRepository employeesMongoRepository, EmployeesMySQLRepository employeesMySQLRepository, MongoTemplate mongoTemplate, DataSource dataSource) {
        this.employeesMongoRepository = employeesMongoRepository;
        this.employeesMySQLRepository = employeesMySQLRepository;
        this.mongoTemplate = mongoTemplate;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Iterable<EmployeeMongo> findAllFromMongo() {
        return employeesMongoRepository.findAll();
    }

    @Override
    public Iterable<EmployeeMySQL> findAllFromMySQL() {
        return employeesMySQLRepository.findAll();
    }

    @Override
    public Optional<EmployeeMongo> findEmployeeFromMongo(String id) {
        return employeesMongoRepository.findById(id);
    }

    @Override
    public Optional<EmployeeMySQL> findEmployeeFromMySQL(String id) {
        return employeesMySQLRepository.findById(id);
    }


    @Override
    public EmployeeMongo saveToMongo(EmployeeMongo employee) {
        employee.setEmpId(UUID.randomUUID().toString());
        return employeesMongoRepository.save(employee);
    }

    @Override
    public EmployeeMySQL saveToMySQL(EmployeeMySQL employee) {
        employee.setEmpId(UUID.randomUUID().toString());
        return employeesMySQLRepository.save(employee);
    }

    public EmployeeWithAllSalaries lookupEmployeeSalaries(String id) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("salaries")
                .localField("_id")
                .foreignField("empId")
                .as("salaries");
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("_id").is(id)), lookupOperation);
        return mongoTemplate.aggregate(aggregation, "employees", EmployeeWithAllSalaries.class).getUniqueMappedResult();
    }

    public EmployeeWithAllSalaries getEmployeeSalaries(String id) {
        String query = "SELECT \n" +
                "    e.emp_id as _id,\n" +
                "    e.first_name as firstName,\n" +
                "    e.last_name as lastName,\n" +
                "    JSON_ARRAYAGG(JSON_OBJECT('id', s.id, 'salary', s.salary, 'empId', s.emp_id, 'updateDate', s.update_date)) as salaries\n" +
                "FROM\n" +
                "    salaries s\n" +
                "        JOIN\n" +
                "    employees e ON e.emp_id = s.emp_id\n" +
                "WHERE\n" +
                "    e.emp_id = ?";

        return jdbcTemplate.queryForObject(
                query,
                new Object[]{id},
                (rs, rowNum) -> {
                    try {
                        return EmployeeWithAllSalaries.builder()
                                ._id(rs.getString("_id"))
                                .firstName(rs.getString("firstName"))
                                .lastName(rs.getString("lastName"))
                                .salaries(Arrays.asList(new ObjectMapper().readValue(rs.getString("salaries"), SalaryMySQL[].class))).build();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        );

    }
}
