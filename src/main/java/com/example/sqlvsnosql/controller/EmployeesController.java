package com.example.sqlvsnosql.controller;

import com.example.sqlvsnosql.model.EmployeeMongo;
import com.example.sqlvsnosql.model.EmployeeMySQL;
import com.example.sqlvsnosql.service.EmployeesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("employees")
@RestController
public class EmployeesController {

    @Autowired
    private EmployeesServiceImpl employeesService;

    @GetMapping("mongo")
    public ResponseEntity<List<EmployeeMongo>> getAllMongoEmployees() {
        return new ResponseEntity<>((List<EmployeeMongo>) employeesService.findAllFromMongo(), HttpStatus.OK);
    }

    @GetMapping("mysql")
    public ResponseEntity<List<EmployeeMySQL>> getAllMySQLEmployees() {
        return new ResponseEntity<>((List<EmployeeMySQL>) employeesService.findAllFromMySQL(), HttpStatus.OK);
    }

    @PostMapping("mongo")
    public ResponseEntity<EmployeeMongo> saveMongoEmployee(@RequestBody EmployeeMongo employee) {
        return new ResponseEntity<>(employeesService.saveToMongo(employee), HttpStatus.OK);
    }

    @PostMapping("mysql")
    public ResponseEntity<EmployeeMySQL> saveMysqlEmployee(@RequestBody EmployeeMySQL employee) {
        return new ResponseEntity<>(employeesService.saveToMySQL(employee), HttpStatus.OK);
    }
}
