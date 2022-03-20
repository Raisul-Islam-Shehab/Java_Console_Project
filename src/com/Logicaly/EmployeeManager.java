package com.Logicaly;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeManager {
    private final ArrayList<Employee> employeeList;

    public EmployeeManager() {
        this.employeeList = new ArrayList<>();
    }

    public boolean addEmployee(Employee newEmployee) {
        if (findEmployee(newEmployee) >= 0) {
            System.out.println("Employee is already in the list.");
            return false;
        } else {
            employeeList.add(newEmployee);
            return true;
        }
    }

    public boolean isEmployeeExists(String employeeNumber) {
        int position = findEmployee(employeeNumber);
        return position >= 0;
    }

    public boolean deleteEmployee(Employee employee) {
        int position = findEmployee(employee);
        if (position < 0) {
            System.err.println("Employee is not in the list.");
            return false;
        }

        this.employeeList.remove(position);
        return true;
    }

    private int findEmployee(Employee employee) {
        return this.employeeList.indexOf(employee);
    }

    private int findEmployee(String employeeNumber) {
        for (int i = 0; i < this.employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            if (employee.getEmployeeNumber().equals(employeeNumber)) {
                return i;
            }
        }
        return -1;
    }

    public Employee getEmployee(String employeeNumber) {
        int position = findEmployee(employeeNumber);
        if (position >= 0) {
            return employeeList.get(position);
        }

        return null;
    }

    public void generateFile() {
        try {
            File f = new File("logicaly.txt");
            FileWriter newFile = new FileWriter(f);
            System.out.println("File created successfully. To access employees' records" +
                    "please refer to the file located here " + f.getAbsolutePath() + "\n");

            int len = this.employeeList.size();

            if (len == 0) {
                newFile.write("Currently, there is no employee record.\n");
            } else {
                newFile.write("Total number of employees' are (" + len + ") -> \n\n");
                for (int i = 0; i < len; i++) {
                    newFile.write("Employee : " + (i + 1) + employeeList.get(i));
                }
            }
            newFile.close();

        } catch (IOException e) {
            System.out.println("There was an error creating file");
            e.printStackTrace();
        }
    }

    public void displayRecords() {
        int len = this.employeeList.size();
        if (len == 0) {
            System.out.println("Currently, there is no employee record.\n");
            return;
        }
        System.out.println("Total number of employees' are (" + len + ") ->\n");
        for (int i = 0; i < len; i++) {
            System.out.println("Employee : " + (i + 1) + employeeList.get(i));
        }
    }
}
