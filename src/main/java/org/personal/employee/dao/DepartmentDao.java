package org.personal.employee.dao;

import org.personal.employee.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentDao {

    Department save(Department department);

    Department update(Department department);

    List<Department> findAll();

    void remove(Long id);

    Optional<Department> findOne(Long id);

    Department findOneDepartment(Long id);
}
