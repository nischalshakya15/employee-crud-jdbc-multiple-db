package org.personal.employee.view;

import java.util.Scanner;

public class View {

    private static final Scanner scan = new Scanner(System.in);

    public View() {
        displayMenu();
    }

    private void displayMenu() {
        char cont;
        do {
            System.out.println("Select Menu");
            System.out.println("1. Employee: ");
            System.out.println("2. Department: ");
            System.out.println("3- Exit: ");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    new EmployeeView();
                    break;
                case 2:
                    new DepartmentView();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println("Press \"C\" to Continue and \"X\" ");
            cont = scan.next().charAt(0);
        } while (cont == 'c' || cont == 'C');
    }
}
