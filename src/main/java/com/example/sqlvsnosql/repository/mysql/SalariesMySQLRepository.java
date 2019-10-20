package com.example.sqlvsnosql.repository.mysql;

import com.example.sqlvsnosql.model.SalaryMySQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalariesMySQLRepository extends JpaRepository<SalaryMySQL, String> {

    @Query("select s.id, s.salary, s.empId, s.updateDate from SalaryMySQL s where s.empId = " +
            "(select distinct e.empId from EmployeeMySQL e where e.empId = :empId)")
    Iterable<SalaryMySQL> getSalariesOfEmployee(@Param("empId") String empId);

}
