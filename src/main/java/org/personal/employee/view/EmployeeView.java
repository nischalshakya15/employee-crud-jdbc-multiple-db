package org.personal.employee.view;

import org.personal.employee.dao.DepartmentDao;
import org.personal.employee.dao.EmployeeDao;
import org.personal.employee.dao.impl.DepartmentDaoImpl;
import org.personal.employee.dao.impl.EmployeeDaoImpl;
import org.personal.employee.entity.Department;
import org.personal.employee.entity.Employee;
import org.personal.employee.utils.CommandLineTable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeView {

    private final Scanner scan = new Scanner(System.in);

    private final DepartmentDao departmentDao = new DepartmentDaoImpl();

    private final EmployeeDao employeeDao = new EmployeeDaoImpl();

    public EmployeeView() {
        displayMenu();
    }

    private void create() {
        scan.nextLine();
        System.out.println("Enter firstName: ");
        String firstName = scan.nextLine();
        System.out.println("Enter lastName: ");
        String lastName = scan.nextLine();
        System.out.println("Enter email: ");
        String email = scan.nextLine();
        System.out.println("Enter salary: ");
        Double salary = scan.nextDouble();
        System.out.println("Select Department");
        departmentDao.findAll().forEach(d -> {
            System.out.println(d.getId() + " " + d.getName());
        });
        Long departmentId = scan.nextLong();
        Optional<Department> department = departmentDao.findOne(departmentId);
        if (department.isPresent()) {
            Employee employee = new Employee(firstName, lastName, email, salary, department.get(), LocalDateTime.now(), null);
            employeeDao.save(employee);
            System.out.println("Employee created successfully.");
        }
    }

    private void update() {
        System.out.println("Enter an employee id: ");
        Long id = scan.nextLong();

        Optional<Employee> updateEmployee = employeeDao.findOne(id);

        if (updateEmployee.isPresent()) {
            scan.nextLine();
            System.out.println("Enter firstName: ");
            String firstName = scan.nextLine();
            System.out.println("Enter lastName: ");
            String lastName = scan.nextLine();
            System.out.println("Enter email: ");
            String email = scan.nextLine();
            System.out.println("Enter salary: ");
            Double salary = scan.nextDouble();
            System.out.println("Select Department");
            departmentDao.findAll().forEach(d -> {
                System.out.println(d.getId() + " " + d.getName());
            });
            Long departmentId = scan.nextLong();
            Optional<Department> department = departmentDao.findOne(departmentId);
            if (department.isPresent()) {
                Employee employee = updateEmployee.get();
                employee.setId(id);
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setEmail(email);
                employee.setSalary(salary);
                employee.setDepartment(department.get());
                employee.setUpdatedAt(LocalDateTime.now());
                employeeDao.update(employee);
                System.out.println("Department updated successfully");
            } else {
                System.out.println("Selected department is invalid");
            }
        } else {
            System.out.println("Employee doesn't exist");
        }
    }

    private void remove() {
        System.out.println("Enter an employee id: ");
        Long id = scan.nextLong();

        Optional<Employee> employee = employeeDao.findOne(id);
        if (employee.isPresent()) {
            employeeDao.remove(id);
            System.out.println("Employee removed successfully ");
        } else {
            System.out.println("Employee doesn't exist");
        }
    }


    private void view() {
        CommandLineTable commandLineTable = new CommandLineTable();
        commandLineTable.setHeaders("Id", "Name", "Email", "Salary", "Department", "Created At", "Updated At");
        commandLineTable.setShowVerticalLines(true);

        final List<Employee> employees = employeeDao.findAll();
        employees.forEach(d -> {
            commandLineTable.addRow(String.valueOf(d.getId()),
                    d.getFirstName() + " " + d.getLastName(),
                    d.getEmail(),
                    String.valueOf(d.getSalary()),
                    d.getDepartment().getName(),
                    String.valueOf(d.getCreatedAt()), String.valueOf(d.getUpdatedAt()));
        });
        commandLineTable.print();
    }

    private void displayMenu() {
        char cont;
        do {
            System.out.println("Employee CRUD View");
            System.out.println("1- Create employee");
            System.out.println("2- Update employee");
            System.out.println("3- Remove employee");
            System.out.println("4- View employee");
            System.out.println("5- Go back to main menu");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    create();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    remove();
                    break;
                case 4:
                    view();
                    break;
                case 5:
                    new View();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println("Press \"C\" to Continue and \"X\" to back to main menu");
            cont = scan.next().charAt(0);
            if (Character.toLowerCase(cont) == 'x') {
                new View();
            }
        } while (cont == 'c' || cont == 'C');
    }
}
