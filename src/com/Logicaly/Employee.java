package com.Logicaly;

import java.util.Objects;

public class Employee {
    private static int idCount = 0;
    private final int id;
    private String employeeNumber;
    private String name;
    private String title;
    private String department;
    private String phoneNumber;
    private String emailAddress;

    public Employee() {
        this.id = ++idCount;
    }

    public int getId() {
        return id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        boolean t = employeeNumber.matches("^E01(0[1-9]|[1-9]{2}|[1-9]0)$");
        if (t) {
            this.employeeNumber = employeeNumber;
        } else {
            throw new InvalidEmployeeDetailException("Invalid Employee number :" + employeeNumber);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        boolean t = name.matches("^[a-zA-Z]{3,10} [a-zA-Z]{3,10}$");
        if (t) {
            this.name = name;
        } else {
            throw new InvalidEmployeeDetailException("Invalid Name: " + name);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        boolean t = title.matches("^(?i)(director|manager|supervisor|trainee|assistant)$");
        if (t) {
            this.title = title;
        } else {
            throw new InvalidEmployeeDetailException("Invalid Title: " + title);
        }
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        boolean t = department.matches("^(?i)(it|sales|r&d|prod|hr)$");
        if (t) {
            this.department = department;
        } else {
            throw new InvalidEmployeeDetailException("Invalid Department: " + department);
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        boolean t = phoneNumber.matches("^0[6|7][56790][0-9]{7}$");
        if (t) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new InvalidEmployeeDetailException("Invalid Phone Number: " + phoneNumber);
        }
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        boolean t = emailAddress.matches("^[a-zA-Z]{1,2}\\.[a-zA-Z0-9_]{3,10}@logicaly.net$");

        int underScoreCount = 0;
        int numCount = 0;
        for (char c : emailAddress.toCharArray()) {
            if (Character.isDigit(c)) numCount++;
            if (c == '_') underScoreCount++;
        }


        if (t && underScoreCount <= 1 && numCount <= 1) {
            this.emailAddress = emailAddress;
        } else {
            throw new InvalidEmployeeDetailException("Invalid Email: "+emailAddress);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "\n" +
                "employee id -> " + id + '\n' +
                "employee number -> " + employeeNumber + '\n' +
                "employee name -> " + name + '\n' +
                "employee title -> " + title + '\n' +
                "employee department -> " + department + '\n' +
                "employee phone number -> " + phoneNumber + '\n' +
                "employee email -> " + emailAddress + '\n' +
                '\n';
    }
}
