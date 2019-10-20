package com.example.sqlvsnosql.service;

import com.example.sqlvsnosql.model.SalaryMongo;
import com.example.sqlvsnosql.model.SalaryMySQL;

public interface SalariesService {

    Iterable<SalaryMongo> findSalariesForEmployeeMongo(String empId);

    Iterable<SalaryMySQL> findSalariesForEmployeeMySQL(String empId);

    SalaryMongo saveToMongo(SalaryMongo salary);

    SalaryMySQL saveToMySQL(SalaryMySQL salary);
}
