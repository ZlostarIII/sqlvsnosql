package com.example.sqlvsnosql.service;

import com.example.sqlvsnosql.model.EmployeeMongo;
import com.example.sqlvsnosql.model.EmployeeMySQL;

import java.util.Optional;

public interface EmployeesService {

    Iterable<EmployeeMongo> findAllFromMongo();

    Iterable<EmployeeMySQL> findAllFromMySQL();

    Optional<EmployeeMongo> findEmployeeFromMongo(String id);

    Optional<EmployeeMySQL> findEmployeeFromMySQL(String id);

    EmployeeMongo saveToMongo(EmployeeMongo employee);

    EmployeeMySQL saveToMySQL(EmployeeMySQL employee);
}
