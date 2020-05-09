package org.personal.employee.dao.impl;

import org.personal.employee.dao.BaseDao;
import org.personal.employee.dao.DepartmentDao;
import org.personal.employee.entity.Department;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class DepartmentDaoImpl extends BaseDao implements DepartmentDao {

    @Override
    public Department save(Department department) throws SQLException {
        preparedStatement = connection.prepareStatement("insert into departments (name, description, created_at) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, department.getName());
        preparedStatement.setString(2, department.getDescription());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(department.getCreatedAt()));
        int rowAffected = preparedStatement.executeUpdate();
        return getDepartment(department, rowAffected);
    }

    @Override
    public Department update(Department department) throws SQLException {
        preparedStatement = connection.prepareStatement("update departments set name = ?, description = ?, updated_at = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, department.getName());
        preparedStatement.setString(2, department.getDescription());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(department.getUpdatedAt()));
        preparedStatement.setLong(4, department.getId());
        int rowAffected = preparedStatement.executeUpdate();
        return getDepartment(department, rowAffected);
    }

    @Override
    public List<Department> findAll() throws SQLException {
        final List<Department> departments = new LinkedList<>();
        resultSet = connection.prepareStatement("select *from departments").executeQuery();
        while (resultSet.next()) {
            departments.add(getDepartment());
        }
        return departments;
    }

    @Override
    public void remove(Long id) throws SQLException {
        preparedStatement = connection.prepareStatement("delete from departments where id = ?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public Department findOne(Long id) throws SQLException {
        preparedStatement = connection.prepareStatement("select *from departments where id = ?");
        preparedStatement.setLong(1, id);
        resultSet = preparedStatement.executeQuery();
        Department department = null;
        while (resultSet.next()) {
            department = getDepartment();
        }
        return department;
    }

    private Department getDepartment() throws SQLException {
        return new Department(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getTimestamp("created_at").toLocalDateTime(),
                resultSet.getTimestamp("updated_at") != null ? resultSet.getTimestamp("updated_at").toLocalDateTime() : null);
    }

    private Department getDepartment(Department department, int rowAffected) throws SQLException {
        if (rowAffected == 1) {
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                department = findOne(resultSet.getLong(rowAffected));
            }
        }
        return department;
    }
}
