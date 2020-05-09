package org.personal.employee.view;

import org.personal.employee.dao.impl.DepartmentDaoImpl;
import org.personal.employee.entity.Department;
import org.personal.employee.utils.CommandLineTable;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class DepartmentView {

    private final Scanner scan = new Scanner(System.in);

    private final org.personal.employee.dao.DepartmentDao departmentDao = new DepartmentDaoImpl();

    public DepartmentView() {
        displayMenu();
    }

    private void create() {
        scan.nextLine();
        System.out.println("Enter name: ");
        String name = scan.nextLine();

        System.out.println("Enter description: ");
        String description = scan.nextLine();

        Department department = new Department(name, description, LocalDateTime.now(), null);
        try {
            department = departmentDao.save(department);
            System.out.println("Department with id " + department.getId() + " created successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void update() {
        System.out.println("Enter an department id: ");
        Long id = scan.nextLong();

        try {
            Department updateDepartment = departmentDao.findOne(id);
            if (updateDepartment != null) {
                scan.nextLine();
                System.out.println("Enter name: ");
                String name = scan.nextLine();

                System.out.println("Enter description: ");
                String description = scan.nextLine();

                updateDepartment.setId(id);
                updateDepartment.setName(name);
                updateDepartment.setDescription(description);
                updateDepartment.setUpdatedAt(LocalDateTime.now());

                updateDepartment = departmentDao.update(updateDepartment);
                System.out.println("Department with id " + updateDepartment.getId() + " updated successfully.");
            } else {
                System.out.println("Department doesn't exist");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


    }

    private void remove() {
        System.out.println("Enter an department id: ");
        Long id = scan.nextLong();
        try {
            Department department = departmentDao.findOne(id);
            if (department != null) {
                departmentDao.remove(department.getId());
                System.out.println("Department removed successfully");
            } else {
                System.out.println("Department doesn't exist");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void view() {
        CommandLineTable commandLineTable = new CommandLineTable();
        commandLineTable.setHeaders("Id", "Name", "Description", "Created At", "Updated At");
        commandLineTable.setShowVerticalLines(true);

        try {
            final List<Department> departments = departmentDao.findAll();
            departments.forEach(d ->
                    commandLineTable.addRow(
                            String.valueOf(d.getId()),
                            d.getName(),
                            d.getDescription(),
                            String.valueOf(d.getCreatedAt()),
                            String.valueOf(d.getUpdatedAt())
                    )
            );
            commandLineTable.print();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void displayMenu() {
        char cont;
        do {
            System.out.println("Department CRUD View");
            System.out.println("1- Create Department");
            System.out.println("2- Update Department");
            System.out.println("3- Remove Department");
            System.out.println("4- View Department");
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
