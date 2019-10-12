package com.example.sqlvsnosql.service;

import com.example.sqlvsnosql.model.EmployeeMongo;
import com.example.sqlvsnosql.model.EmployeeMySQL;

public interface EmployeesService {

    Iterable<EmployeeMongo> findAllFromMongo();

    Iterable<EmployeeMySQL> findAllFromMySQL();

    EmployeeMongo saveToMongo(EmployeeMongo employee);

    EmployeeMySQL saveToMySQL(EmployeeMySQL employee);
}
