package student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private String grade;
    // Add any other relevant details

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters and setters for the attributes

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }
}

public class StudentManagementSystemApp {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManagementSystem system = new StudentManagementSystem();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int option = getUserInput(1, 5);
            switch (option) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    searchStudent();
                    break;
                case 3:
                    displayAllStudents();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("***********Student Management System **********");
        System.out.println("1. Add a new student");
        System.out.println("2. Search for a student");
        System.out.println("3. Display all students");
        System.out.println("4. Remove a student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserInput(int min, int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    break;
                } else {
                    System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        return choice;
    }

    private static void addStudent() {
        System.out.println("********** Add a New Student *************");
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student's roll number: ");
        int rollNumber = getUserInput(1, Integer.MAX_VALUE);

        System.out.print("Enter student's grade: ");
        String grade = scanner.nextLine();

        Student student = new Student(name, rollNumber, grade);
        system.addStudent(student);
        System.out.println("Student added successfully!");
    }

    private static void searchStudent() {
        System.out.println("**********Search for a Student**********");
        System.out.print("Enter student's roll number: ");
        int rollNumber = getUserInput(1, Integer.MAX_VALUE);

        Student student = system.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student found:");
            System.out.println("Name: " + student.getName());
            System.out.println("Roll Number: " + student.getRollNumber());
            System.out.println("Grade: " + student.getGrade());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void displayAllStudents() {
        System.out.println("*********** All Students*************");
        List<Student> students = system.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println("Name: " + student.getName());
                System.out.println("Roll Number: " + student.getRollNumber());
                System.out.println("Grade: " + student.getGrade());
                System.out.println("-----------------------------");
            }
        }
    }

    private static void removeStudent() {
        System.out.println("**********Remove a Student**************");
        System.out.print("Enter student's roll number: ");
        int rollNumber = getUserInput(1, Integer.MAX_VALUE);

        Student student = system.searchStudent(rollNumber);
        if (student != null) {
            system.removeStudent(student);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }
}
