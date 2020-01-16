package com.example.sqlvsnosql.repository.mysql;

import com.example.sqlvsnosql.model.EmployeeMySQL;
import com.example.sqlvsnosql.model.EmployeeWithAllSalaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesMySQLRepository extends JpaRepository<EmployeeMySQL, String> {

    @Query(value =
            "SELECT \n" +
            "    e.emp_id as _id,\n" +
            "    e.first_name as firstName,\n" +
            "    e.last_name as lastName,\n" +
            "    JSON_ARRAYAGG(JSON_OBJECT('id', s.id, 'salary', s.salary, 'empId', s.emp_id, 'updateDate', s.update_date)) as salaries\n" +
            "FROM\n" +
            "    salaries s\n" +
            "        JOIN\n" +
            "    employees e ON e.emp_id = s.emp_id\n" +
            "WHERE\n" +
            "    e.emp_id = :empId",
            nativeQuery = true)
    EmployeeWithAllSalaries getEmployeeSalaries(@Param("empId") String empId);
}
