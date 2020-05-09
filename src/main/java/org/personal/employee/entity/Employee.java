package org.personal.employee.entity;

import lombok.Getter;
import lombok.Setter;
import org.personal.employee.base.BaseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class Employee extends BaseEntity {

    private String firstName;

    private String lastName;

    private String email;

    private Double salary;

    private Department department;

    public Employee(Long id, String firstName, String lastName, String email, Double salary, Department department, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.department = department;
    }

    public Employee(String firstName, String lastName, String email, Double salary, Department department, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.department = department;
    }
}
