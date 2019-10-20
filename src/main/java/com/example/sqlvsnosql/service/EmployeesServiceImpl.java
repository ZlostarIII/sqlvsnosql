package com.example.sqlvsnosql.service;

import com.example.sqlvsnosql.model.EmployeeMongo;
import com.example.sqlvsnosql.model.EmployeeMySQL;
import com.example.sqlvsnosql.repository.mongo.EmployeesMongoRepository;
import com.example.sqlvsnosql.repository.mysql.EmployeesMySQLRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class EmployeesServiceImpl implements EmployeesService {

    private EmployeesMongoRepository employeesMongoRepository;
    private EmployeesMySQLRepository employeesMySQLRepository;

    @Autowired
    public EmployeesServiceImpl(EmployeesMongoRepository employeesMongoRepository, EmployeesMySQLRepository employeesMySQLRepository) {
        this.employeesMongoRepository = employeesMongoRepository;
        this.employeesMySQLRepository = employeesMySQLRepository;
    }

    @Override
    public Iterable<EmployeeMongo> findAllFromMongo() {
        log.info("Get all from mongo database");
        return employeesMongoRepository.findAll();
    }

    @Override
    public Iterable<EmployeeMySQL> findAllFromMySQL() {
        log.info("Get all from MySQL database");
        return employeesMySQLRepository.findAll();
    }

    @Override
    public Optional<EmployeeMongo> findEmployeeFromMongo(String id) {
        log.info("Get employee from mongo database");
        return employeesMongoRepository.findById(id);
    }

    @Override
    public Optional<EmployeeMySQL> findEmployeeFromMySQL(String id) {
        log.info("Get employee from MySQL database");
        return employeesMySQLRepository.findById(id);
    }


    @Override
    public EmployeeMongo saveToMongo(EmployeeMongo employee) {
        log.info("Saved employee to mongo database: " + employee.getFirstName() + " " + employee.getLastName());
        employee.setEmpId(UUID.randomUUID().toString());
        return employeesMongoRepository.save(employee);
    }

    @Override
    public EmployeeMySQL saveToMySQL(EmployeeMySQL employee) {
        log.info("Saved employee to MySQL database: " + employee.getFirstName() + " " + employee.getLastName());
        employee.setEmpId(UUID.randomUUID().toString());
        return employeesMySQLRepository.save(employee);
    }
}
