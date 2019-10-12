package com.example.sqlvsnosql.service;

import com.example.sqlvsnosql.model.EmployeeMongo;
import com.example.sqlvsnosql.model.EmployeeMySQL;
import com.example.sqlvsnosql.repository.mongo.EmployeesMongoRepository;
import com.example.sqlvsnosql.repository.mysql.EmployeesMySQLRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeesServiceImpl implements EmployeesService {

    private EmployeesMongoRepository employeesMongoRepository;
    private EmployeesMySQLRepository employeesMySQLRepository;
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public EmployeesServiceImpl(EmployeesMongoRepository employeesMongoRepository, EmployeesMySQLRepository employeesMySQLRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.employeesMongoRepository = employeesMongoRepository;
        this.employeesMySQLRepository = employeesMySQLRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
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
    public EmployeeMongo saveToMongo(EmployeeMongo employee) {
        employee.setId(sequenceGeneratorService.generateSequence(EmployeeMongo.SEQUENCE_NAME));
        log.info("Saved employee to mongo database: " + employee.getFirstName() + " " + employee.getLastName());
        return employeesMongoRepository.save(employee);
    }

    @Override
    public EmployeeMySQL saveToMySQL(EmployeeMySQL employee) {
        log.info("Saved employee to MySQL database: " + employee.getFirstName() + " " + employee.getLastName());
        return employeesMySQLRepository.save(employee);
    }
}
