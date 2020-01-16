package com.example.sqlvsnosql.model;

import com.example.sqlvsnosql.utils.MyCustomInstantDeserializer;
import com.example.sqlvsnosql.utils.MyCustomInstantSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "salaries")
public class SalaryMySQL {

    @Id
    private String id;

    @NotNull
    private Double salary;

    @NotNull
    private String empId;

    @NotNull
    @JsonDeserialize(using = MyCustomInstantDeserializer.class)
    @JsonSerialize(using = MyCustomInstantSerializer.class)
    private Instant updateDate;

}
