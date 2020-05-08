package org.personal.employee.dao;

import org.personal.employee.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    Employee save(Employee employee);

    Employee update(Employee employee);

    List<Employee> findAll();

    void remove(Long id);

    Optional<Employee> findOne(Long id);
}
