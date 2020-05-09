package org.personal.employee.dao;

import org.personal.employee.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

    Employee save(Employee employee) throws SQLException;

    Employee update(Employee employee) throws SQLException;

    List<Employee> findAll() throws SQLException;

    void remove(Long id) throws SQLException;

    Employee findOne(Long id) throws SQLException;
}
