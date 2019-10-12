package com.example.sqlvsnosql.repository.mysql;

import com.example.sqlvsnosql.model.EmployeeMySQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesMySQLRepository extends JpaRepository<EmployeeMySQL, String> {
}
