package org.personal.employee.entity;

import lombok.Getter;
import lombok.Setter;
import org.personal.employee.base.BaseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class Department extends BaseEntity {

    private String name;

    private String description;

    public Department(String name) {
        super();
        this.name = name;
    }

    public Department(Long id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.description = description;
    }

    public Department(String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.name = name;
        this.description = description;
    }
}
