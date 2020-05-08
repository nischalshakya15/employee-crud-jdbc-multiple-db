package org.personal.employee.dao.impl;

import org.personal.employee.dao.DepartmentDao;
import org.personal.employee.dao.EmployeeDao;
import org.personal.employee.entity.Employee;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDao {

    private static final List<Employee> employees = new ArrayList<>();

    private static final DepartmentDao departmentDao = new DepartmentDaoImpl();

    static {
        employees.add(new Employee(1L,
                "Nischal", "Shakya", "nischalshakya@gmail.com", 20000.0D,
                departmentDao.findOneDepartment(1L), LocalDateTime.now(), LocalDateTime.now()));

        employees.add(new Employee(2L,
                "Naruto", "Uzumaki", "nischalshakya@gmail.com", 20000.0D,
                departmentDao.findOneDepartment(2L), LocalDateTime.now(), LocalDateTime.now()));
    }

    @Override
    public Employee save(Employee employee) {
        employee.setId(employees.get(employees.size() - 1).getId() + 1);
        employees.add(employee);
        return employees.get(employees.size() - 1);
    }

    @Override
    public Employee update(Employee employee) {
        Optional<Employee> optionalEmployee = findOne(employee.getId());
        optionalEmployee.ifPresent(value -> employees.set(employees.indexOf(value), employee));
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public void remove(Long id) {
        Optional<Employee> employee = findOne(id);
        employee.ifPresent(employees::remove);
    }

    @Override
    public Optional<Employee> findOne(Long id) {
        return employees.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

}
