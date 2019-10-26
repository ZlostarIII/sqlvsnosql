package com.example.sqlvsnosql.service;

import com.example.sqlvsnosql.model.SalaryMongo;
import com.example.sqlvsnosql.model.SalaryMySQL;
import com.example.sqlvsnosql.repository.mongo.SalariesMongoRepository;
import com.example.sqlvsnosql.repository.mysql.SalariesMySQLRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class SalariesServiceImpl implements SalariesService {

    private SalariesMongoRepository salariesMongoRepository;
    private SalariesMySQLRepository salariesMySQLRepository;

    @Autowired
    public SalariesServiceImpl(SalariesMongoRepository salariesMongoRepository, SalariesMySQLRepository salariesMySQLRepository) {
        this.salariesMongoRepository = salariesMongoRepository;
        this.salariesMySQLRepository = salariesMySQLRepository;
    }

    @Override
    public List<SalaryMongo> findSalariesForEmployeeMongo(String empId) {
        return salariesMongoRepository.getSalariesOfEmployee(empId);
    }

    @Override
    public Iterable<SalaryMySQL> findSalariesForEmployeeMySQL(String empId) {
        return salariesMySQLRepository.getSalariesOfEmployee(empId);
    }

    @Override
    public SalaryMongo saveToMongo(SalaryMongo salary) {
        salary.setId(UUID.randomUUID().toString());
        return salariesMongoRepository.save(salary);
    }

    @Override
    public SalaryMySQL saveToMySQL(SalaryMySQL salary) {
        salary.setId(UUID.randomUUID().toString());
        return salariesMySQLRepository.save(salary);
    }
}
