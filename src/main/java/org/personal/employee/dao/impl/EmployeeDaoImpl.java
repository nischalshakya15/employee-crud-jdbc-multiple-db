package org.personal.employee.dao.impl;

import org.personal.employee.dao.BaseDao;
import org.personal.employee.dao.EmployeeDao;
import org.personal.employee.entity.Department;
import org.personal.employee.entity.Employee;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {

    @Override
    public Employee save(Employee employee) throws SQLException {
        preparedStatement = connection.prepareStatement("insert into employees " +
                "(first_name, last_name, email, salary, department_id, created_at) " +
                "values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, employee.getEmail());
        preparedStatement.setDouble(4, employee.getSalary());
        preparedStatement.setLong(5, employee.getDepartment().getId());
        preparedStatement.setTimestamp(6, Timestamp.valueOf(employee.getCreatedAt()));
        int rowAffected = preparedStatement.executeUpdate();
        return getEmployee(employee, rowAffected);
    }

    @Override
    public Employee update(Employee employee) throws SQLException {
        preparedStatement = connection.prepareStatement("update employees set first_name = ?, last_name = ?, email = ?, " +
                "salary = ?, department_id = ?, updated_at = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, employee.getEmail());
        preparedStatement.setDouble(4, employee.getSalary());
        preparedStatement.setLong(5, employee.getDepartment().getId());
        preparedStatement.setTimestamp(6, Timestamp.valueOf(employee.getUpdatedAt()));
        preparedStatement.setLong(7, employee.getId());
        int rowAffected = preparedStatement.executeUpdate();
        return getEmployee(employee, rowAffected);
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        final List<Employee> employees = new ArrayList<>();
        resultSet = connection.prepareStatement("select employees.id, employees.first_name, employees.last_name, employees.salary, employees.email, employees.created_at, " +
                "employees.updated_at, departments.name from employees inner join departments on employees.department_id = departments.id").executeQuery();
        while (resultSet.next()) {
            employees.add(getEmployee());
        }
        return employees;
    }

    @Override
    public void remove(Long id) throws SQLException {
        preparedStatement = connection.prepareStatement("delete from employees where id = ?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public Employee findOne(Long id) throws SQLException {
        preparedStatement = connection.prepareStatement("select employees.id, employees.first_name,employees.last_name, employees.salary, employees.email, employees.created_at, " +
                "employees.updated_at, d.name from employees inner join departments on employees.department_id = departments.id where employees.id = ?");
        preparedStatement.setLong(1, id);
        resultSet = preparedStatement.executeQuery();
        Employee employee = null;
        if (resultSet.next()) {
            employee = getEmployee();
        }
        return employee;
    }

    private Employee getEmployee() throws SQLException {
        return new Employee(
                resultSet.getLong("employees.id"),
                resultSet.getString("employees.first_name"),
                resultSet.getString("employees.last_name"),
                resultSet.getString("employees.email"),
                resultSet.getDouble("employees.salary"),
                new Department(resultSet.getString("departments.name")),
                resultSet.getTimestamp("employees.created_at").toLocalDateTime(),
                resultSet.getTimestamp("employees.updated_at") != null ? resultSet.getTimestamp("employees.updated_at").toLocalDateTime() : null);
    }

    private Employee getEmployee(Employee employee, int rowAffected) throws SQLException {
        if (rowAffected == 1) {
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                employee = findOne(resultSet.getLong(rowAffected));
            }
        }
        return employee;
    }
}
