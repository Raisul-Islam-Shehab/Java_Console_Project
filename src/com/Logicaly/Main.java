package com.Logicaly;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeManager employeeManager = new EmployeeManager();

    public static void main(String[] args) {
        welcome();
        int choice;
        boolean quit = false;

        while (!quit) {

            operations();

            while (true) {
                System.out.println("Enter an operation (1-7) :");
                String inp = scanner.nextLine();
                try {
                    choice = Integer.parseInt(inp);
                    if (choice >= 1 && choice <= 7) break;
                    else System.err.println("Invalid input. Enter an integer between (1-7)");
                } catch (Exception e) {
                    System.err.println("Invalid input. Enter an integer between (1-7)");
                }
            }

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    searchEmployee();
                    break;
                case 4:
                    employeeManager.displayRecords();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    employeeManager.generateFile();
                    break;
                case 7:
                    quit = true;
                    System.out.println("See you next time on Logicaly Employees' Manager!");
                    break;

                default:
                    System.err.println("Enter a valid option (1-7).");

            }
        }

    }

    private static void welcome() {
        System.out.println("\nWelcome to LOGICALY employees' records manager!");
    }

    private static void operations() {
        System.out.println(
                "======++Operations Menu++======\n" +
                        "1. Add a new employee\n" +
                        "2. Update an employee\n" +
                        "3. Search an employee\n" +
                        "4. Display all employees' records\n" +
                        "5. Delete an employee\n" +
                        "6. Generate a file\n" +
                        "7. Exit\n");
    }


    private static void addEmployee() {
        Employee newEmployee = new Employee();
        setData(newEmployee);
        if (employeeManager.addEmployee(newEmployee)) {
            System.out.println("Employee added successfully.\n");
        } else {
            System.out.println("Employee is already in the records");
        }
    }

    private static void setData(Employee newEmployee) {
        try {
            System.out.println("Enter valid employee details : ");
            System.out.println("Enter valid employee number. Starting with 'E01' followed by 2 digits different than 00. Example :" +
                    "E0110, E0122 ");
            String employeeNumber = scanner.nextLine();
            newEmployee.setEmployeeNumber(employeeNumber);
            System.out.println("Enter valid employee name within two words. Example ->Bob Marley ");
            String name = scanner.nextLine();
            newEmployee.setName(name);
            System.out.println("Enter valid employee title (Manager, Director, Assistant, Supervisor, Trainee)");
            String title = scanner.nextLine();
            newEmployee.setTitle(title);
            System.out.println("Enter valid employee department (Sales, R&D, IT, PROD, HR)");
            String department = scanner.nextLine();
            newEmployee.setDepartment(department);
            System.out.println("Enter valid employee phone number. Consists of 10 digits. Starting with 06 or 07, followed by 5,7,9 or 0. " +
                    "Then remaining digits. Examples ->0651234567, 0791234567");
            String phoneNumber = scanner.nextLine();
            newEmployee.setPhoneNumber(phoneNumber);
            System.out.println("Enter valid employee email address. Starting with one or two letters, followed by a dot(.) and " +
                    "3-10 alphabets. Then an underscore and a digit. Lasty add '@logicaly.net'. Example ->sh.bob_5@logicaly.net" +
                    "s.marley_7@logicaly.net");
            String emailAddress = scanner.nextLine();
            newEmployee.setEmailAddress(emailAddress);
        } catch (InvalidEmployeeDetailException e) {
            System.err.println(e.getMessage());
            setData(newEmployee);
        }
    }

    private static void updateEmployee() {
        System.out.println("Enter valid employee number. Starting with 'E01' followed by 2 digits different than 00. Example :" +
                "E0110, E0122 ");
        String employeeNumber = scanner.nextLine();
        Employee existingEmployee = employeeManager.getEmployee(employeeNumber);

        if (existingEmployee == null) {
            System.err.println("Employee not found\n");
            return;
        }
        boolean exit = false;
        int input;

        while (!exit) {
            System.out.println("Which detail do you want to update?");
            updateWelcome();

            while (true) {
                System.out.println("Enter a valid option (1-7)");
                String inp = scanner.nextLine();
                try {
                    input = Integer.parseInt(inp);
                    if (input >= 1 && input <= 7) break;
                    else System.err.println("Invalid input. Enter an integer between (1-7)");

                } catch (Exception e) {
                    System.err.println("Invalid input. Enter an integer between (1-7)");
                }
            }


            switch (input) {
                case 1:
                    updateEmployeeNumber(existingEmployee);
                    break;
                case 2:
                    updateEmployeeName(existingEmployee);
                    break;
                case 3:
                    updateEmployeeTitle(existingEmployee);
                    break;
                case 4:
                    updateEmployeeDepartment(existingEmployee);
                    break;
                case 5:
                    updateEmployeePhoneNumber(existingEmployee);
                    break;
                case 6:
                    updateEmployeeEmailAddress(existingEmployee);
                    break;
                case 7:
                    exit = true;
                    System.out.println("Update done\n");
                    break;
                default:
                    System.err.println("Enter a valid option (1-7)");
            }
        }

    }

    private static void updateWelcome() {
        System.out.println(
                "\n1. Employee Number" +
                        "\n2. Employee Name" +
                        "\n3. Employee Title" +
                        "\n4. Employee Department" +
                        "\n5. Employee PhoneNumber" +
                        "\n6. Employee EmailAddress" +
                        "\n7. Exit");
    }

    private static void searchEmployee() {
        System.out.println("Enter valid employee number. Starting with 'E01' followed by 2 digits different than 00. Example :" +
                "E0110, E0122 ");
        String employeeNumber = scanner.nextLine();
        Employee existingEmployee = employeeManager.getEmployee(employeeNumber);

        if (existingEmployee == null) {
            System.err.println("Employee not found\n");
            return;
        }

        System.out.println("employee id -> " + existingEmployee.getId() +
                "\nemployee number -> " + existingEmployee.getEmployeeNumber() +
                "\nemployee name -> " + existingEmployee.getName() +
                "\nemployee title -> " + existingEmployee.getTitle() +
                "\nemployee department -> " + existingEmployee.getDepartment() +
                "\nemployee phone number -> " + existingEmployee.getPhoneNumber() +
                "\nemployee email -> " + existingEmployee.getEmailAddress() + "\n");
    }

    private static void deleteEmployee() {
        System.out.println("Enter valid employee number. Starting with 'E01' followed by 2 digits different than 00. Example :" +
                "E0110, E0122 ");
        String employeeNumber = scanner.nextLine();
        Employee existingEmployee = employeeManager.getEmployee(employeeNumber);

        if (existingEmployee == null) {
            System.err.println("Employee not found\n");
            return;
        }

        if (employeeManager.deleteEmployee(existingEmployee)) {
            System.out.println("Employee record deleted successfully\n");
        } else {
            System.out.println("There was an error deleting the record");
        }
    }

    private static void updateEmployeeNumber(Employee newEmployee) {
        try {
            System.out.println("Enter valid employee number. Starting with 'E01' followed by 2 digits different than 00. Example ->" +
                    "E0110, E0122 ");
            String employeeNumber = scanner.nextLine();
            boolean t = employeeManager.isEmployeeExists(employeeNumber);
            if (t) {
                throw new InvalidEmployeeDetailException();
            } else {
                newEmployee.setEmployeeNumber(employeeNumber);
                System.out.println("Employee number is updated successfully.\n");
            }
        } catch (InvalidEmployeeDetailException ex) {
            System.err.println("Invalid input details");
            System.out.println("Please enter a different employee number.");
            updateEmployeeNumber(newEmployee);
        }
    }

    private static void updateEmployeeName(Employee newEmployee) {

        try {
            System.out.println("Enter valid employee name within two words. Example ->Bob Marley ");
            String name = scanner.nextLine();
            newEmployee.setName(name);
            System.out.println("Employee name is updated successfully.\n");
        } catch (InvalidEmployeeDetailException ex) {
            System.err.println("Invalid input details");
            updateEmployeeName(newEmployee);
        }
    }


    private static void updateEmployeeTitle(Employee newEmployee) {
        try {
            System.out.println("Enter valid employee title. (Manager, Director, Assistant, Supervisor, Trainee)");
            String title = scanner.nextLine();
            newEmployee.setTitle(title);
            System.out.println("Employee title is updated successfully.\n");
        } catch (InvalidEmployeeDetailException ex) {
            System.err.println("Invalid input details");
            updateEmployeeTitle(newEmployee);
        }
    }


    private static void updateEmployeeDepartment(Employee newEmployee) {
        try {
            System.out.println("Enter valid employee department. (Sales, R&D, IT, PROD, HR)");
            String department = scanner.nextLine();
            newEmployee.setDepartment(department);
            System.out.println("Employee department is updated successfully.\n");
        } catch (InvalidEmployeeDetailException ex) {
            System.err.println("Invalid input details");
            updateEmployeeDepartment(newEmployee);
        }
    }


    private static void updateEmployeePhoneNumber(Employee newEmployee) {
        try {
            System.out.println("Enter valid employee phone number. Consists of 10 digits. Starting with 06 or 07, followed by 5,7,9 or 0. " +
                    "Then remaining digits. Examples -> 0651234567, 0791234567 ");
            String phoneNumber = scanner.nextLine();
            newEmployee.setPhoneNumber(phoneNumber);
        } catch (InvalidEmployeeDetailException ex) {
            System.err.println("Invalid input details");
            updateEmployeePhoneNumber(newEmployee);
        }
    }


    private static void updateEmployeeEmailAddress(Employee newEmployee) {
        try {
            System.out.println("Enter valid employee email address. Starting with one or two letters, followed by a dot(.) and " +
                    "3-10 alphabets, can contain at most one underscore or digit. Lastly add '@logicaly.net'. Example ->sh.bob_5@logicaly.net" +
                    "s.my_7@logicaly.net ");
            String emailAddress = scanner.nextLine();
            newEmployee.setEmailAddress(emailAddress);
            System.out.println("Employee email address is updated successfully.");
        } catch (InvalidEmployeeDetailException ex) {
            System.err.println(ex.getMessage());
            updateEmployeeEmailAddress(newEmployee);
        }
    }


}
