package com.example.sqlvsnosql.controller;

import com.example.sqlvsnosql.model.SalaryMongo;
import com.example.sqlvsnosql.model.SalaryMySQL;
import com.example.sqlvsnosql.service.SalariesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("salaries")
@RestController
public class SalariesController {

    @Autowired
    private SalariesServiceImpl salariesService;

    @GetMapping("mongo/{empId}")
    public ResponseEntity<List<SalaryMongo>> getMongoSalariesById(@PathVariable("empId") String empId) {
        return new ResponseEntity<>(salariesService.findSalariesForEmployeeMongo(empId), HttpStatus.OK);
    }

    @GetMapping("mysql/{empId}")
    public ResponseEntity<List<SalaryMySQL>> getMySQLSalariessById(@PathVariable("empId") String empId) {
        return new ResponseEntity<>((List<SalaryMySQL>) salariesService.findSalariesForEmployeeMySQL(empId), HttpStatus.OK);
    }

    @PostMapping("mongo")
    public ResponseEntity<SalaryMongo> saveMongoSalary(@RequestBody SalaryMongo salary) {
        return new ResponseEntity<>(salariesService.saveToMongo(salary), HttpStatus.OK);
    }

    @PostMapping("mysql")
    public ResponseEntity<SalaryMySQL> saveMysqlSalary(@RequestBody SalaryMySQL salary) {
        return new ResponseEntity<>(salariesService.saveToMySQL(salary), HttpStatus.OK);
    }
}
