package com.test.empList;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);
        Employee employee;
        ArrayList<Employee> employeeList;
        String filename = "out/empList.sav";
        int recordNo, yearOfBirth;
        String mode;

        employeeList = load(filename);
        do {
            System.out.println("\nSelect mode (add/delete/list/save/load/save as/load from/quit): ");
            switch (mode = in.nextLine()) {
                case "add":
                    employee = new Employee();

                    System.out.print("First Name: ");
                    employee.firstname = in.nextLine();

                    System.out.print("Last Name: ");
                    employee.lastname = in.nextLine();

                    do {
                        System.out.print("Year of birth: ");
                        yearOfBirth = Integer.parseInt(in.nextLine());
                    } while (!employee.setYearOfBirth(yearOfBirth));

                    System.out.print("Department: ");
                    employee.department = in.nextLine();

                    System.out.print("Manager: ");
                    employee.manager = in.nextLine();

                    System.out.print("Phone number: ");
                    employee.phone = in.nextLine();

                    employeeList.add(employee);
                    break;

                case "delete":
                    System.out.print("Enter record number to delete: ");
                    recordNo = Integer.parseInt(in.nextLine());
                    employeeList.remove(recordNo);
                    break;

                case "list":
                    for (int i = 0; i < employeeList.size(); i++) {
                        System.out.println("EmpNo " + i + ". " + employeeList.get(i).firstname + " " + employeeList.get(i).lastname +
                                " born in " + employeeList.get(i).yearOfBirth + " year, works in " +
                                employeeList.get(i).department + " under " + employeeList.get(i).manager +
                                " management and has phone number " + employeeList.get(i).phone
                        );
                    }
                    break;

                case "save":
                    save(employeeList, filename);
                    break;

                case "load":
                    employeeList = load(filename);
                    break;

                case "save as":
                    System.out.println("Enter filename: ");
                    filename = in.nextLine();
                    save(employeeList, filename);
                    break;

                case "load from":
                    System.out.println("Enter filename: ");
                    filename = in.nextLine();
                    employeeList = load(filename);
                    break;

                case "quit":
                    System.out.println("Good bye!");
                    break;

                default:
                    System.out.println("Incorrect mode selected");
            }
        } while (!Objects.equals(mode, "quit"));

        save(employeeList, filename);
    }

    public static ArrayList<Employee> load(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        ArrayList<Employee> employeeList = new ArrayList<>();
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            employeeList = (ArrayList<Employee>) objectInputStream.readObject();
            fileInputStream.close();
        }
        return employeeList;
    }

    public static void save(ArrayList<Employee> employeeList, String filename) throws IOException {
        File file = new File(filename);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(employeeList);
        fileOutputStream.close();
    }
}
