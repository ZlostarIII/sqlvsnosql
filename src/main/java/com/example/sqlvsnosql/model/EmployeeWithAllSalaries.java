package com.example.sqlvsnosql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeWithAllSalaries {

    private String _id;
    private String firstName;
    private String lastName;
    private List<Object> salaries;

}
