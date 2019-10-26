package com.example.sqlvsnosql.repository.mongo;

import com.example.sqlvsnosql.model.SalaryMongo;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalariesMongoRepository extends CrudRepository<SalaryMongo, String> {

    @Query(value = "{'empId' : ?0}")
    List<SalaryMongo> getSalariesOfEmployee(String empId);

}
