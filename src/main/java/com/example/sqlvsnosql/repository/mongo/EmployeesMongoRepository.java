package com.example.sqlvsnosql.repository.mongo;

import com.example.sqlvsnosql.model.EmployeeMongo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesMongoRepository extends CrudRepository<EmployeeMongo, String> {
}
