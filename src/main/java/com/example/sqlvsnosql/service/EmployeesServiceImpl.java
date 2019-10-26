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
}
