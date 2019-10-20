package com.example.sqlvsnosql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Document(collection = "salaries")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "salaries")
public class SalaryMongo {

    @Id
    private String id;

    @NotNull
    private Double salary;

    @NotNull
    private String empId;

    @NotNull
    private Instant updateDate;

}
