package org.personal.employee.dao.impl;

import org.personal.employee.dao.DepartmentDao;
import org.personal.employee.entity.Department;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDao {

    private static List<Department> departments = new LinkedList<>();

    static {
        departments.add(new Department(1L, LocalDateTime.now(), LocalDateTime.now(), "HR", "Human Resource Management"));
        departments.add(new Department(2L, LocalDateTime.now(), LocalDateTime.now(), "Sales", "Sales Management"));
        departments.add(new Department(3L, LocalDateTime.now(), LocalDateTime.now(), "Marketing", "Marketing Management"));
        departments.add(new Department(4L, LocalDateTime.now(), LocalDateTime.now(), "IT", "Information Technology"));
    }

    @Override
    public Department save(Department department) {
        department.setId(departments.get(departments.size() - 1).getId() + 1);
        departments.add(department);
        return departments.get(departments.size() - 1);
    }

    @Override
    public Department update(Department department) {
        Optional<Department> optionalDepartment = findOne(department.getId());
        optionalDepartment.ifPresent(value -> departments.set(departments.indexOf(value), department));
        return department;
    }

    @Override
    public List<Department> findAll() {
        return departments;
    }

    @Override
    public void remove(Long id) {
        Optional<Department> department = findOne(id);
        department.ifPresent(value -> departments.remove(value));
    }

    @Override
    public Optional<Department> findOne(Long id) {
        return departments.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    @Override
    public Department findOneDepartment(Long id) {
        Optional<Department> department = departments.stream().filter(d -> d.getId().equals(id)).findFirst();
        return department.orElse(null);
    }
}
