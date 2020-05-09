package org.personal.employee.dao;

import org.personal.employee.entity.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDao {

    Department save(Department department) throws SQLException;

    Department update(Department department) throws SQLException;

    List<Department> findAll() throws Exception;

    void remove(Long id) throws SQLException;

    Department findOne(Long id) throws SQLException;
}
