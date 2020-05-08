package org.personal.employee.view;

import org.personal.employee.dao.DepartmentDao;
import org.personal.employee.dao.impl.DepartmentDaoImpl;
import org.personal.employee.entity.Department;
import org.personal.employee.utils.CommandLineTable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DepartmentView {

    private final Scanner scan = new Scanner(System.in);

    private final DepartmentDao departmentDao = new DepartmentDaoImpl();

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
        departmentDao.save(department);
        System.out.println("Department created successfully");
    }

    private void update() {
        System.out.println("Enter an department id: ");
        Long id = scan.nextLong();

        Optional<Department> updateDepartment = departmentDao.findOne(id);

        if (updateDepartment.isPresent()) {
            scan.nextLine();
            System.out.println("Enter name: ");
            String name = scan.nextLine();

            System.out.println("Enter description: ");
            String description = scan.nextLine();

            Department department = updateDepartment.get();
            department.setId(id);
            department.setName(name);
            department.setDescription(description);
            department.setUpdatedAt(LocalDateTime.now());

            departmentDao.update(department);
            System.out.println("Department updated successfully");
        } else {
            System.out.println("Department doesn't exist");
        }
    }

    private void remove() {
        System.out.println("Enter an department id: ");
        Long id = scan.nextLong();

        Optional<Department> department = departmentDao.findOne(id);
        if (department.isPresent()) {
            departmentDao.remove(id);
            System.out.println("Department removed successfully ");
        } else {
            System.out.println("Department doesn't exist");
        }
    }

    private void view() {
        CommandLineTable commandLineTable = new CommandLineTable();
        commandLineTable.setHeaders("Id", "Name", "Description", "Created At", "Updated At");
        commandLineTable.setShowVerticalLines(true);

        List<Department> departments = departmentDao.findAll();
        departments.forEach(d -> {
            commandLineTable.addRow(String.valueOf(d.getId()), String.valueOf(d.getName()), String.valueOf(d.getDescription()),
                    String.valueOf(d.getCreatedAt()), String.valueOf(d.getUpdatedAt()));
        });
        commandLineTable.print();
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
