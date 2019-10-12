package com.example.sqlvsnosql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.PrePersist;
import javax.persistence.Transient;
import java.time.Instant;

@Document(collection = "employees")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeMongo {

    @Transient
    public static final String SEQUENCE_NAME = "employees_sequence";

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private Instant hireDate;

    @PrePersist
    public void prePersist() {
        this.hireDate = Instant.now();
    }

}
