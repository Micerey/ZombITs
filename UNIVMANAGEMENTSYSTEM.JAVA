import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Subject {
    private String name;
    private String description;
    private String code;

    public Subject(String name, String description, String code) {
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Person {
    protected String name;
    protected String id;

    public Person(String firstName, String middleName, String lastName, String id) {
        this.name = firstName + " " + middleName + " " + lastName;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
}

class Member extends Person {
    protected ArrayList<Subject> subjects;

    public Member(String firstName, String middleName, String lastName, String id) {
        super(firstName, middleName, lastName, id);
        subjects = new ArrayList<>();
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }
}

class Instructor extends Member {
    private String address;
    private int age;

    public Instructor(String firstName, String middleName, String lastName, String id, String address, int age) {
        super(firstName, middleName, lastName, id);
        this.address = address;
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Student extends Member {
    private String address;
    private int age;

    public Student(String firstName, String middleName, String lastName, String id, String address, int age) {
        super(firstName, middleName, lastName, id);
        this.address = address;
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class UniversityManagementSystem {
    private static ArrayList<Instructor> instructors = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Subject> subjects = new ArrayList<>();
    private static Random rand = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("University Profiling Management System");
            System.out.println("1. Add");
            System.out.println("2. Assign");
            System.out.println("3. Display");
            System.out.println("4. Delete");
            System.out.println("5. Edit");
            System.out.println("6. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAddMenu();
                    break;
                case 2:
                    displayAssignMenu();
                    break;
                case 3:
                    displayDisplayMenu();
                    break;
                case 4:
                    displayDeleteMenu();
                    break;
                case 5:
                    displayEditMenu();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void displayAddMenu() {
        System.out.println("Add Menu");
        System.out.println("1. Add Student");
        System.out.println("2. Add Instructor");
        System.out.println("3. Add Subject");
        System.out.println("4. Return");
        System.out.println();
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                addInstructor();
                break;
            case 3:
                addSubject();
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void displayAssignMenu() {
        System.out.println("Assign Menu");
        System.out.println("1. Assign Subject to Instructor");
        System.out.println("2. Assign Subject to Student");
        System.out.println("3. Return");
        System.out.println();
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                if (instructors.isEmpty() || subjects.isEmpty()) {
                    System.out.println("No existing instructors or subjects. Please add them first.");
                    System.out.println();
                } else {
                    assignSubjectToInstructor();
                    System.out.println();
                }
                break;
            case 2:
                if (students.isEmpty() || subjects.isEmpty()) {
                    System.out.println("No existing students or subjects. Please add them first.");
                    System.out.println();
                } else {
                    addSubjectToStudent();
                    System.out.println();
                }
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void displayDisplayMenu() {
        displayStudentInformationIn2DArrayFormat();
        
        System.out.println("Display Menu");
        System.out.println("1. Display Student Information and Subject Details");
        System.out.println("2. Display Instructor Information and Handled Subjects");
        System.out.println("3. Return");
        System.out.println();
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
    
        switch (choice) {
            case 1:
                if (students.isEmpty()) {
                    System.out.println("No existing students. Please add students first.");
                    System.out.println();
                } else {
                    displayStudentInformation();
                    System.out.println();
                }
                break;
            case 2:
                if (instructors.isEmpty()) {
                    System.out.println("No existing instructors. Please add instructors first.");
                    System.out.println();
                } else {
                    displayInstructorInformation();
                    System.out.println();
                }
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private static void displayStudentInformationIn2DArrayFormat() {
        int maxStudentNameLength = getMaxNameLength(students);
        int maxInstructorNameLength = getMaxNameLength(instructors);
        
        // Determine the length of the header for students
        int studentHeaderLength = Math.max(maxStudentNameLength, "STUDENT".length());
        
        // Determine the length of the header for instructors
        int instructorHeaderLength = Math.max(maxInstructorNameLength, "INSTRUCTOR".length());
        
        // Print header for students
        System.out.println("+-----------------------------------------+");
        System.out.printf("| %-"+ (studentHeaderLength + 2) + "s | %-5s | %-7s |\n", "STUDENT", "CODE", "SUBJECT");
        System.out.println("+-----------------------------------------+");
        for (Student student : students) {
            System.out.printf("| %-"+ (studentHeaderLength + 2) + "s | %-8s | %-7s |\n", student.getName(), student.getId(), "");
            for (Subject subject : student.getSubjects()) {
                System.out.printf("| %-"+ (studentHeaderLength + 2) + "s | %-8s | %-7s (%s) |\n", "", "", subject.getName(), subject.getCode());
            }
            System.out.println("+-----------------------------------------+");
        }
        
        // Print header for instructors
        System.out.printf("| %-"+ (instructorHeaderLength + 2) + "s | %-5s | %-7s |\n", "INSTRUCTOR", "CODE", "SUBJECT");
        System.out.println("+-----------------------------------------+");
    }    
     
    private static int getMaxNameLength(ArrayList<? extends Person> people) {
        int maxLength = 0;
        for (Person person : people) {
            int length = person.getName().length();
            if (length > maxLength) {
                maxLength = length;
            }
        }
        return maxLength;
    }
    
    
    private static void addInstructor() {
        System.out.println("Please fill all the information needed. Fields with '*' are required.");
        System.out.println("Press enter to continue...");
        scanner.nextLine();
        scanner.nextLine();
        System.out.print("* First name: ");
        String firstName = scanner.nextLine();
        if (firstName.trim().isEmpty()) {
            System.out.println("First name cannot be empty. Please try again.");
            addInstructor();
            return;
        }
        System.out.print("* Last name: ");
        String lastName = scanner.nextLine();
        if (lastName.trim().isEmpty()) {
            System.out.println("Last name cannot be empty. Please try again.");
            addInstructor();
            return;
        }
        System.out.print(" Middle name: ");
        String middleName = scanner.nextLine().trim(); // Read the whole line and trim extra spaces
        if (middleName.isEmpty()) {
            middleName = ""; // If middle name is empty, make it null
        }
        System.out.print("\n" + "* Address: "); // Consume newline
        String address = scanner.nextLine();
        System.out.print("* Age: ");
        int age = scanner.nextInt();
    
        if (age < 24) {
            System.out.println("Error: Instructor must be at least 24 years old.");
            return;
        }
    
        String id = generateInstructorId();
        Instructor instructor = new Instructor(firstName, middleName, lastName, id, address, age);
        instructors.add(instructor);
        System.out.println("Instructor added successfully. ID: " + id);
        System.out.println();
    }

    private static String generateInstructorId() {
        return "INS24-" + generatePaddedNumber();
    }

    private static void addStudent() {
        System.out.println("Please fill all the information needed. Fields with '*' are required.");
        System.out.println("Press enter to continue...");
        scanner.nextLine();
        scanner.nextLine();
    
        System.out.println("Add student information.");
        System.out.print("* First name: ");
        String firstName = scanner.nextLine();
        if (firstName.trim().isEmpty()) {
            System.out.println("First name cannot be empty. Please try again.");
            addStudent();
            return;
        }
        System.out.print("* Last name: ");
        String lastName = scanner.nextLine();
        if (lastName.trim().isEmpty()) {
            System.out.println("Last name cannot be empty. Please try again.");
            addStudent();
            return;
        }
        System.out.print(" Middle name: ");
        String middleName = scanner.nextLine().trim(); // Read the whole line and trim extra spaces
        if (middleName.isEmpty()) {
            middleName = ""; // If middle name is empty, make it null
        }
        System.out.print("\n" + "* Address: "); // Consume newline
        String address = scanner.nextLine();
        System.out.print("* Age: ");
        int age = scanner.nextInt();
        if (age < 17) {
            System.out.println("Error: Student must be at least 17 years old.");
            return;
        }
    
        String id = generateStudentId();
        Student student = new Student(firstName, middleName, lastName, id, address, age);
        students.add(student);
        System.out.println("Student added successfully. ID: " + id);
    }

    private static String generateStudentId() {
        return "ST24-" + generatePaddedNumber();
    }

    private static void addSubject() {
        System.out.print("Enter subject name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter subject description: ");
        String description = scanner.nextLine();
        String code = generateSubjectCode();
        Subject subject = new Subject(name, description, code);
        subjects.add(subject);
        System.out.println("Subject added successfully. Code: " + code);
    }

    private static String generateSubjectCode() {
        return "SUB-" + generatePaddedNumber();
    }

    private static String generatePaddedNumber() {
        return String.format("%04d", rand.nextInt(10000));
    }

    private static void assignSubjectToInstructor() {
        System.out.print("Enter instructor ID: ");
        String id = scanner.next();
        Instructor instructor = findInstructorById(id);
        if (instructor != null) {
            System.out.print("Enter subject code: ");
            String code = scanner.next();
            Subject subject = findSubjectByCode(code);
            if (subject != null) {
                instructor.addSubject(subject);
                System.out.println("Subject assigned to instructor successfully.");
                System.out.println();
            } else {
                System.out.println("Subject not found.");
            }
        } else {
            System.out.println("Instructor not found.");
            System.out.println();
        }
    }

    private static void addSubjectToStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.next();
        Student student = findStudentById(id);
        if (student != null) {
            System.out.print("Enter subject code: ");
            String code = scanner.next();
            Subject subject = findSubjectByCode(code);
            if (subject != null) {
                student.addSubject(subject);
                System.out.println("Subject added to student successfully.");
                System.out.println();
            } else {
                System.out.println("Subject not found.");
            }
        } else {
            System.out.println("Student not found.");
            System.out.println();
        }
    }

    private static void displayStudentInformation() {
        System.out.print("Enter student ID: ");
        String id = scanner.next();
        Student student = findStudentById(id);
        if (student != null) {
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student ID: " + student.getId());
            System.out.println("Address: " + student.getAddress());
            System.out.println("Age: " + student.getAge());
            System.out.println("Subjects: ");
            for (Subject subject : student.getSubjects()) {
                System.out.println("- " + subject.getName() + " (" + subject.getCode() + ")");
            }
        } else {
            System.out.println("Student not found.");
            System.out.println();
        }
    }

    private static void displayInstructorInformation() {
        System.out.print("Enter instructor ID: ");
        String id = scanner.next();
        Instructor instructor = findInstructorById(id);
        if (instructor != null) {
            System.out.println("Instructor Name: " + instructor.getName());
            System.out.println("Instructor ID: " + instructor.getId());
            System.out.println("Address: " + instructor.getAddress());
            System.out.println("Age: " + instructor.getAge());
            System.out.println("Handled Subjects:");
            for (Subject subject : instructor.getSubjects()) {
                System.out.println("- " + subject.getName() + " (" + subject.getCode() + ")");
                System.out.println();
            }
        } else {
            System.out.println("Instructor not found.");
            System.out.println();
        }
    }

    private static void displayDeleteMenu() {
        System.out.println("Delete Menu");
        System.out.println("1. Delete Student");
        System.out.println("2. Delete Instructor");
        System.out.println("3. Delete Subject");
        System.out.println("4. Return");
        System.out.println();
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                deleteStudent();
                break;
            case 2:
                deleteInstructor();
                break;
            case 3:
                deleteSubject();
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        String id = scanner.next();
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student data deleted successfully.");
            System.out.println();
        } else {
            System.out.println("Student not found.");
        }
        displayDeleteMenu();
        System.out.println();
    }

    private static void deleteInstructor() {
        System.out.print("Enter instructor ID to delete: ");
        String id = scanner.next();
        Instructor instructor = findInstructorById(id);
        if (instructor != null) {
            instructors.remove(instructor);
            System.out.println("Instructor data deleted successfully.");
            System.out.println();
        } else {
            System.out.println("Instructor not found.");
        }
        displayDeleteMenu();
        System.out.println();
    }

    private static void deleteSubject() {
        System.out.print("Enter subject code to delete: ");
        String code = scanner.next();
        Subject subject = findSubjectByCode(code);
        if (subject != null) {
            // Remove subject from all students and instructors first
            for (Student student : students) {
                student.getSubjects().remove(subject);
            }
            for (Instructor instructor : instructors) {
                instructor.getSubjects().remove(subject);
            }
            subjects.remove(subject);
            System.out.println("Subject data deleted successfully.");
            System.out.println();
        } else {
            System.out.println("Subject not found.");
        }
        displayDeleteMenu();
        System.out.println();
    }

    private static void displayEditMenu() {
        System.out.println("Edit Menu");
        System.out.println("1. Edit Student");
        System.out.println("2. Edit Instructor");
        System.out.println("3. Edit Subject");
        System.out.println("4. Return");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                editStudent();
                break;
            case 2:
                editInstructor();
                break;
            case 3:
                editSubject();
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void editStudent() {
        System.out.print("Enter student ID to edit: ");
        String id = scanner.next();
        Student student = findStudentById(id);
        if (student != null) {
            // Display student details
            System.out.println("Current Details:");
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Address: " + student.getAddress());

            // Prompt for editing
            System.out.println("1. Edit Name");
            System.out.println("2. Edit Age");
            System.out.println("3. Edit Address");
            System.out.println();
            System.out.print("Enter field to edit: ");  
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    scanner.nextLine(); // Consume newline
                    String newName = scanner.nextLine();
                    student.setName(newName);
                    break;
                case 2:
                    System.out.print("Enter new age: ");
                    int newAge = scanner.nextInt();
                    student.setAge(newAge);
                    break;
                case 3:
                    System.out.print("Enter new address: ");
                    scanner.nextLine(); // Consume newline
                    String newAddress = scanner.nextLine();
                    student.setAddress(newAddress);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }

            // Confirm changes
            System.out.println("Updated Details:");
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Address: " + student.getAddress());
            System.out.println();
            System.out.print("Confirm changes? (yes/no): ");
            String confirm = scanner.next();
            if (confirm.equalsIgnoreCase("yes")) {
                System.out.println("Changes confirmed.");
            } else {
                System.out.println("Changes discarded.");
            }
        } else {
            System.out.println("Student not found.");
            System.out.println();
        }
    }

    private static void editInstructor() {
        System.out.print("Enter instructor ID to edit: ");
        String id = scanner.next();
        Instructor instructor = findInstructorById(id);
        if (instructor != null) {
            // Display instructor details
            System.out.println("Current Details:");
            System.out.println("Name: " + instructor.getName());
            System.out.println("Age: " + instructor.getAge());
            System.out.println("Address: " + instructor.getAddress());

            // Prompt for editing
            System.out.println("1. Edit Name");
            System.out.println("2. Edit Age");
            System.out.println("3. Edit Address");
            System.out.println();
            System.out.print("Enter field to edit: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    scanner.nextLine(); // Consume newline
                    String newName = scanner.nextLine();
                    instructor.setName(newName);
                    break;
                case 2:
                    System.out.print("Enter new age: ");
                    int newAge = scanner.nextInt();
                    instructor.setAge(newAge);
                    break;
                case 3:
                    System.out.print("Enter new address: ");
                    scanner.nextLine(); // Consume newline
                    String newAddress = scanner.nextLine();
                    instructor.setAddress(newAddress);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }

            // Confirm changes
            System.out.println("Updated Details:");
            System.out.println("Name: " + instructor.getName());
            System.out.println("Age: " + instructor.getAge());
            System.out.println("Address: " + instructor.getAddress());
            System.out.println();
            System.out.print("Confirm changes? (yes/no): ");
            String confirm = scanner.next();
            if (confirm.equalsIgnoreCase("yes")) {
                System.out.println("Changes confirmed.");
            } else {
                System.out.println("Changes discarded.");
            }
        } else {
            System.out.println("Instructor not found.");
            System.out.println();
        }
    }

    private static void editSubject() {
        System.out.print("Enter subject code to edit: ");
        String code = scanner.next();
        Subject subject = findSubjectByCode(code);
        if (subject != null) {
            // Display subject details
            System.out.println("Current Details:");
            System.out.println("Name: " + subject.getName());
            System.out.println("Code: " + subject.getCode());

            // Prompt for editing
            System.out.print("Enter new subject name: ");
            scanner.nextLine(); // Consume newline
            String newName = scanner.nextLine();
            subject.setName(newName);

            // Confirm changes
            System.out.println("Updated Details:");
            System.out.println("Name: " + subject.getName());
            System.out.println("Code: " + subject.getCode());
            System.out.println();
            System.out.print("Confirm changes? (yes/no): ");
            String confirm = scanner.next();
            if (confirm.equalsIgnoreCase("yes")) {
                System.out.println("Changes confirmed.");
            } else {
                System.out.println("Changes discarded.");
            }
        } else {
            System.out.println("Subject not found.");
            System.out.println();
        }
    }

    private static Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    private static Instructor findInstructorById(String id) {
        for (Instructor instructor : instructors) {
            if (instructor.getId().equals(id)) {
                return instructor;
            }
        }
        return null;
    }

    private static Subject findSubjectByCode(String code) {
        for (Subject subject : subjects) {
            if (subject.getCode().equals(code)) {
                return subject;
            }
        }
        return null;
    }
}
