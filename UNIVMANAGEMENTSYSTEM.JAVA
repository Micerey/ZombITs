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
    static String ANSI_RESET = "\u001B[0m";
    static String ANSI_RED = "\u001B[31m";
    static String ANSI_CYAN = "\u001B[36m";
    static String ANSI_BLUE = "\u001B[34m";
    static String ANSI_GREEN = "\u001B[32m";

    private static ArrayList<Instructor> instructors = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Subject> subjects = new ArrayList<>();
    private static Random rand = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        System.out.println(ANSI_RED + " ".repeat(10) + //
                "█ █ █▄ █ █ █ █ █▀▀ █▀█ █▀ █ ▀█▀ █▄█   █▀█ █▀█ █▀█ █▀▀ █ █   █ █▄ █ █▀▀  █▀▄▀█ ▄▀█ █▄ █ ▄▀█ █▀▀ █▀▀ █▀▄▀█ █▀▀ █▄ █ ▀█▀\r\n"
                + " ".repeat(10) + //
                "█▄█ █ ▀█ █ ▀▄▀ ██▄ █▀▄ ▄█ █  █   █    █▀▀ █▀▄ █▄█ █▀  █ █▄▄ █ █ ▀█ █▄█  █ ▀ █ █▀█ █ ▀█ █▀█ █▄█ ██▄ █ ▀ █ ██▄ █ ▀█  █ \r\n"
                + //
                "\r\n" + //
                " ".repeat(50) + "█▀ █▄█ █▀ ▀█▀ █▀▀ █▀▄▀█\r\n" + //
                " ".repeat(50) + "▄█  █  ▄█  █  ██▄ █ ▀ █" + ANSI_RESET);
        border();

        do {
            System.out.println();
            System.out.println(" ".repeat(42) + "+---------------------------------------+");
            System.out.println(" ".repeat(50) + "1. Add \t4. Delete");
            System.out.println(" ".repeat(50) + "2. Assign \t5. Edit");
            System.out.println(" ".repeat(50) + "3. Display \t6. Exit");
            System.out.println(" ".repeat(42) + "+---------------------------------------+");
            System.out.print(ANSI_BLUE + " ".repeat(50) + "Enter your choice: " + ANSI_RESET);
            choice = scanner.nextInt();
            System.out.println();
            border();

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
                    System.out.println("\tExiting...");
                    break;
                default:
                    System.out.println(ANSI_RED + "\tInvalid choice!" + ANSI_RESET);
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void displayAddMenu() {
        System.out.println();
        System.out.println(" ".repeat(47) + //
                "▄▀█ █▀▄ █▀▄  █▀▄▀█ █▀▀ █▄ █ █ █\r\n" + " ".repeat(47) + //
                "█▀█ █▄▀ █▄▀  █ ▀ █ ██▄ █ ▀█ █▄█");
        System.out.println();
        System.out.println(" ".repeat(42) + "+---------------------------------------+");
        System.out.println(" ".repeat(55) + "1. Add Student");
        System.out.println(" ".repeat(55) + "2. Add Instructor");
        System.out.println(" ".repeat(55) + "3. Add Subject");
        System.out.println(" ".repeat(55) + "4. Return");
        System.out.println(" ".repeat(42) + "+---------------------------------------+");
        System.out.print(ANSI_CYAN + " ".repeat(50) + "Enter your choice: " + ANSI_RESET);
        int choice = scanner.nextInt();
        System.out.println();
        border();

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
                System.out.println(ANSI_RED + "\tInvalid choice!" + ANSI_RESET);
        }
    }

    private static void displayAssignMenu() {
        System.out.println();
        System.out.println(" ".repeat(43) + //
                "▄▀█ █▀ █▀ █ █▀▀ █▄ █  █▀▄▀█ █▀▀ █▄ █ █ █\r\n" + " ".repeat(43) + //
                "█▀█ ▄█ ▄█ █ █▄█ █ ▀█  █ ▀ █ ██▄ █ ▀█ █▄█");
        System.out.println();
        System.out.println(" ".repeat(42) + "+----------------------------------------+");
        System.out.println(" ".repeat(46) + "1. Assign Subject to Instructor");
        System.out.println(" ".repeat(46) + "2. Assign Subject to Student");
        System.out.println(" ".repeat(46) + "3. Return");
        System.out.println(" ".repeat(42) + "+----------------------------------------+");
        System.out.print(" ".repeat(50) + ANSI_CYAN + "Enter your choice: " + ANSI_RESET);
        int choice = scanner.nextInt();
        System.out.println();
        border();

        switch (choice) {
            case 1:
                if (instructors.isEmpty() || subjects.isEmpty()) {
                    System.out.println(
                            ANSI_RED + "\tNo existing instructors or subjects. Please add them first." + ANSI_RESET);
                    System.out.println();
                } else {
                    assignSubjectToInstructor();
                    System.out.println();
                }
                break;
            case 2:
                if (students.isEmpty() || subjects.isEmpty()) {
                    System.out.println(
                            ANSI_RED + "\tNo existing students or subjects. Please add them first." + ANSI_RESET);
                    System.out.println();
                } else {
                    addSubjectToStudent();
                    System.out.println();
                }
                break;
            case 3:
                break;
            default:
                System.out.println(ANSI_RED + "\tInvalid choice!" + ANSI_RESET);
        }
    }

    private static void displayDisplayMenu() {
        displayStudentAndInstructorInformation();
        System.out.println();
        System.out.println(" ".repeat(40) + //
                "█▀▄ █ █▀ █▀█ █   ▄▀█ █▄█  █▀▄▀█ █▀▀ █▄ █ █ █\r\n" + " ".repeat(40) + //
                "█▄▀ █ ▄█ █▀▀ █▄▄ █▀█  █   █ ▀ █ ██▄ █ ▀█ █▄█");
        System.out.println();
        System.out.println(" ".repeat(35) + "+----------------------------------------------------------+");
        System.out.println(" ".repeat(38) + "1. Display Student Information and Subject Details");
        System.out.println(" ".repeat(38) + "2. Display Instructor Information and Handled Subjects");
        System.out.println(" ".repeat(38) + "3. Return");
        System.out.println(" ".repeat(35) + "+----------------------------------------------------------+");
        System.out.print(" ".repeat(50) + ANSI_CYAN + "Enter your choice: " + ANSI_RESET);
        int choice = scanner.nextInt();
        border();

        switch (choice) {
            case 1:
                if (students.isEmpty()) {
                    System.out.println(ANSI_RED + "\tNo existing students. Please add students first." + ANSI_RESET);
                    System.out.println();
                } else {
                    displayStudentInformation();
                    System.out.println();
                }
                break;
            case 2:
                if (instructors.isEmpty()) {
                    System.out.println(
                            ANSI_RED + "\tNo existing instructors. Please add instructors first." + ANSI_RESET);
                    System.out.println();
                } else {
                    displayInstructorInformation();
                    System.out.println();
                }
                break;
            case 3:
                break;
            default:
                System.out.println(ANSI_RED + "\tInvalid choice!" + ANSI_RESET);
        }
    }

    private static void displayStudentAndInstructorInformation() {
        System.out.println();
        // Check if there are students
        if (students.isEmpty()) {
            System.out.println(ANSI_RED + "\tNo students found." + ANSI_RESET);
        } else {
            // Print header
            System.out.println(ANSI_GREEN + "\tStudents:" + ANSI_RESET);

            // Print student information
            for (Student student : students) {
                System.out.println("\t\tName: " + student.getName() + ", Code: " + student.getId());
            }
        }

        System.out.println();
        // Check if there are instructors
        if (instructors.isEmpty()) {
            System.out.println(ANSI_RED + "\tNo instructors found." + ANSI_RESET);
        } else {
            // Print header
            System.out.println(ANSI_GREEN + "\tInstructors:" + ANSI_RESET);

            // Print instructor information
            for (Instructor instructor : instructors) {
                System.out.println("\t\tName: " + instructor.getName() + ", Code: " + instructor.getId());
            }
        }
        System.out.println();
    }

    private static void addInstructor() {
        System.out.println(
                ANSI_RED + "\tPlease fill all the information needed. Fields with '*' are required." + ANSI_RESET);
        System.out.println("\tPress enter to continue...");
        scanner.nextLine();
        scanner.nextLine();

        System.out.print("\t* First name: ");
        String firstName = scanner.nextLine();
        if (firstName.trim().isEmpty()) {
            System.out.println(ANSI_RED + "\tFirst name cannot be empty. Please try again." + ANSI_RESET);
            addInstructor();
            return;
        }

        System.out.print("\t* Last name: ");
        String lastName = scanner.nextLine();
        if (lastName.trim().isEmpty()) {
            System.out.println(ANSI_RED + "\tLast name cannot be empty. Please try again." + ANSI_RESET);
            addInstructor();
            return;
        }
        System.out.print("\t  Middle name: ");
        String middleName = scanner.nextLine().trim(); // Read the whole line and trim extra spaces
        if (middleName.isEmpty()) {
            middleName = " "; // If middle name is empty, make it null
        }
        System.out.print("\n" + "\t* Address: "); // Consume newline
        String address = scanner.nextLine();
        System.out.print("\t* Age: ");
        int age = scanner.nextInt();

        if (age < 24) {
            System.out.println(ANSI_RED + "\tError: Instructor must be at least 24 years old." + ANSI_RESET);
            return;
        }

        String id = generateInstructorId();
        Instructor instructor = new Instructor(firstName, middleName, lastName, id, address, age);
        instructors.add(instructor);
        System.out.println(ANSI_GREEN + "\tInstructor added successfully. \n\tID: " + id + ANSI_RESET);
        System.out.println();
    }

    private static String generateInstructorId() {
        return "INS24-" + generatePaddedNumber();
    }

    private static void addStudent() {
        System.out.println(
                ANSI_RED + "\tPlease fill all the information needed. Fields with '*' are required." + ANSI_RESET);
        System.out.println("\tPress enter to continue...");
        scanner.nextLine();
        scanner.nextLine();

        System.out.println(ANSI_GREEN + "\tAdd student information." + ANSI_RESET);
        System.out.print("\t* First name: ");
        String firstName = scanner.nextLine();
        if (firstName.trim().isEmpty()) {
            System.out.println(ANSI_RED + "First name cannot be empty. Please try again." + ANSI_RESET);
            addStudent();
            return;
        }
        System.out.print("\t* Last name: ");
        String lastName = scanner.nextLine();
        if (lastName.trim().isEmpty()) {
            System.out.println(ANSI_RED + "\tLast name cannot be empty. Please try again." + ANSI_RESET);
            addStudent();
            return;
        }
        System.out.print("\t  Middle name: ");
        String middleName = scanner.nextLine().trim(); // Read the whole line and trim extra spaces
        if (middleName.isEmpty()) {
            middleName = " "; // If middle name is empty, make it null
        }
        System.out.print("\n" + "\t* Address: "); // Consume newline
        String address = scanner.nextLine();
        System.out.print("\t* Age: ");
        int age = scanner.nextInt();
        if (age < 17) {
            System.out.println(ANSI_RED + "\tError: Student must be at least 17 years old." + ANSI_RESET);
            return;
        }

        String id = generateStudentId();
        Student student = new Student(firstName, middleName, lastName, id, address, age);
        students.add(student);
        System.out.println(ANSI_GREEN + "\tStudent added successfully. \n\tID: " + id + ANSI_RESET);
    }

    private static String generateStudentId() {
        return "ST24-" + generatePaddedNumber();
    }

    private static void addSubject() {
        System.out.print("\tEnter subject name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("\tEnter subject description: ");
        String description = scanner.nextLine();
        String code = generateSubjectCode();
        Subject subject = new Subject(name, description, code);
        subjects.add(subject);
        System.out.println(ANSI_GREEN + "\tSubject added successfully. \n\tCode: " + code + ANSI_RESET);
    }

    private static String generateSubjectCode() {
        return "SUB-" + generatePaddedNumber();
    }

    private static String generatePaddedNumber() {
        return String.format("%04d", rand.nextInt(10000));
    }

    private static void assignSubjectToInstructor() {
        System.out.print("\tEnter instructor ID: ");
        String id = scanner.next();
        Instructor instructor = findInstructorById(id);
        if (instructor != null) {
            System.out.print("\tEnter subject code: ");
            String code = scanner.next();
            Subject subject = findSubjectByCode(code);
            if (subject != null) {
                instructor.addSubject(subject);
                System.out.println(ANSI_GREEN + "\tSubject assigned to instructor successfully." + ANSI_RESET);
                System.out.println();
            } else {
                System.out.println(ANSI_RED + "Subject not found." + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "Instructor not found." + ANSI_RESET);
            System.out.println();
        }
    }

    private static void addSubjectToStudent() {
        System.out.print("\tEnter student ID: ");
        String id = scanner.next();
        Student student = findStudentById(id);
        if (student != null) {
            System.out.print("\tEnter subject code: ");
            String code = scanner.next();
            Subject subject = findSubjectByCode(code);
            if (subject != null) {
                student.addSubject(subject);
                System.out.println(ANSI_GREEN + "\tSubject added to student successfully." + ANSI_RESET);
                System.out.println();
            } else {
                System.out.println(ANSI_RED + "\tSubject not found." + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "\tStudent not found." + ANSI_RESET);
            System.out.println();
        }
    }

    private static void displayStudentInformation() {
        System.out.print("\tEnter student ID: ");
        String id = scanner.next();
        Student student = findStudentById(id);
        if (student != null) {
            System.out.println("\tStudent Name: " + student.getName());
            System.out.println("\tStudent ID: " + student.getId());
            System.out.println("\tAddress: " + student.getAddress());
            System.out.println("\tAge: " + student.getAge());
            System.out.println("\tSubjects: ");
            for (Subject subject : student.getSubjects()) {
                System.out.println("- " + subject.getName() + " (" + subject.getCode() + ")");
            }
        } else {
            System.out.println(ANSI_RED + "\tStudent not found." + ANSI_RESET);
            System.out.println();
        }
    }

    private static void displayInstructorInformation() {
        System.out.print("\tEnter instructor ID: ");
        String id = scanner.next();
        Instructor instructor = findInstructorById(id);
        if (instructor != null) {
            System.out.println("\tInstructor Name: " + instructor.getName());
            System.out.println("\tInstructor ID: " + instructor.getId());
            System.out.println("\tAddress: " + instructor.getAddress());
            System.out.println("\tAge: " + instructor.getAge());
            System.out.println("\tHandled Subjects:");
            for (Subject subject : instructor.getSubjects()) {
                System.out.println("- " + subject.getName() + " (" + subject.getCode() + ")");
                System.out.println();
            }
        } else {
            System.out.println(ANSI_RED + "\tInstructor not found." + ANSI_RESET);
            System.out.println();
        }
    }

    private static void displayDeleteMenu() {
        System.out.println();
        System.out.println(" ".repeat(41) + //
                "█▀▄ █▀▀ █   █▀▀ ▀█▀ █▀▀   █▀▄▀█ █▀▀ █▄ █ █ █\r\n" + " ".repeat(41) + //
                "█▄▀ ██▄ █▄▄ ██▄  █  ██▄   █ ▀ █ ██▄ █ ▀█ █▄█");
        System.out.println();
        System.out.println(" ".repeat(38) + "+------------------------------------------------+");
        System.out.println(" ".repeat(55) + "1. Delete Student");
        System.out.println(" ".repeat(55) + "2. Delete Instructor");
        System.out.println(" ".repeat(55) + "3. Delete Subject");
        System.out.println(" ".repeat(55) + "4. Return");
        System.out.println(" ".repeat(38) + "+------------------------------------------------+");
        System.out.print(" ".repeat(50) + ANSI_CYAN + "Enter your choice: " + ANSI_RESET);
        int choice = scanner.nextInt();
        System.out.println();
        border();

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
                System.out.println(ANSI_RED + "\tInvalid choice!" + ANSI_RESET);
        }
    }

    private static void deleteStudent() {
        System.out.print("\tEnter student ID to delete: ");
        String id = scanner.next();
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println(ANSI_GREEN + "\tStudent data deleted successfully." + ANSI_RESET);
            System.out.println();
        } else {
            System.out.println(ANSI_RED + "\tStudent not found." + ANSI_RESET);
        }
        displayDeleteMenu();
        System.out.println();
    }

    private static void deleteInstructor() {
        System.out.print("\tEnter instructor ID to delete: ");
        String id = scanner.next();
        Instructor instructor = findInstructorById(id);
        if (instructor != null) {
            instructors.remove(instructor);
            System.out.println(ANSI_GREEN + "\tInstructor data deleted successfully." + ANSI_RESET);
            System.out.println();
        } else {
            System.out.println(ANSI_RED + "\tInstructor not found." + ANSI_RESET);
        }
        displayDeleteMenu();
        System.out.println();
    }

    private static void deleteSubject() {
        System.out.print("\tEnter subject code to delete: ");
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
            System.out.println(ANSI_GREEN + "\tSubject data deleted successfully." + ANSI_RESET);
            System.out.println();
        } else {
            System.out.println(ANSI_RED + "\tSubject not found." + ANSI_RESET);
        }
        displayDeleteMenu();
        System.out.println();
    }

    private static void displayEditMenu() {
        System.out.println();
        System.out.println(" ".repeat(46) + //
                "█▀▀ █▀▄ █ ▀█▀   █▀▄▀█ █▀▀ █▄ █ █ █\r\n" + " ".repeat(46) + //
                "██▄ █▄▀ █  █    █ ▀ █ ██▄ █ ▀█ █▄█");
        System.out.println();
        System.out.println(" ".repeat(42) + "+----------------------------------------+");
        System.out.println(" ".repeat(55) + "1. Edit Student");
        System.out.println(" ".repeat(55) + "2. Edit Instructor");
        System.out.println(" ".repeat(55) + "3. Edit Subject");
        System.out.println(" ".repeat(55) + "4. Return");
        System.out.println(" ".repeat(42) + "+----------------------------------------+");
        System.out.print(ANSI_CYAN + " ".repeat(50) + "Enter your choice: " + ANSI_RESET);
        int choice = scanner.nextInt();
        System.out.println();
        border();

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
                System.out.println(ANSI_RED + "\tInvalid choice!" + ANSI_RESET);
        }
    }

    private static void editStudent() {
        System.out.print("\tEnter student ID to edit: ");
        String id = scanner.next();
        Student student = findStudentById(id);
        if (student != null) {
            // Display student details
            System.out.println(ANSI_CYAN + "\tCurrent Details:" + ANSI_RESET);
            System.out.println("\t\tName: " + student.getName());
            System.out.println("\t\tAge: " + student.getAge());
            System.out.println("\t\tAddress: " + student.getAddress());

            // Prompt for editing
            System.out.println();
            System.out.println("+---------------------------+");
            System.out.println("\t1. Edit Name");
            System.out.println("\t2. Edit Age");
            System.out.println("\t3. Edit Address");
            System.out.println();
            System.out.print("\tEnter field to edit: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Edit student information.");
                    System.out.println("\nPlease fill all the information needed. Fields with '*' are required.");
                    System.out.println("Press enter to continue...");
                    scanner.nextLine(); // Consume newline
                    scanner.nextLine(); // Wait for enter
                    
                    String newFirstName = "";
                    String newLastName = "";
                
                    // Loop until valid input is provided for required fields
                    while (newFirstName.isEmpty() || newLastName.isEmpty()) {
                        System.out.print("* Enter new first name: ");
                        newFirstName = scanner.nextLine().trim();
                        if (newFirstName.isEmpty()) {
                            System.out.println("First name cannot be empty. Please try again.");
                            continue;
                        }
                
                        System.out.print("* Enter new last name: ");
                        newLastName = scanner.nextLine().trim();
                        if (newLastName.isEmpty()) {
                            System.out.println("Last name cannot be empty. Please try again.");
                        }
                    }
                
                    System.out.print("Enter new middle name: ");
                    String newMiddleName = scanner.nextLine().trim();
                
                    // Update the student's name
                    String fullName = newFirstName + " " + newMiddleName + " " + newLastName;
                    student.setName(fullName);
                    break;
                case 2:
                    System.out.print("\tEnter new age: ");
                    int newAge = scanner.nextInt();
                    student.setAge(newAge);
                    break;
                case 3:
                    System.out.print("\tEnter new address: ");
                    scanner.nextLine(); // Consume newline
                    String newAddress = scanner.nextLine();
                    student.setAddress(newAddress);
                    break;
                default:
                    System.out.println(ANSI_RED + "\tInvalid choice!" + ANSI_RESET);
                    return;
            }

            // Confirm changes
            System.out.println(ANSI_GREEN + "Updated Details:" + ANSI_RESET);
            System.out.println("\t\tName: " + student.getName());
            System.out.println("\t\tAge: " + student.getAge());
            System.out.println("\t\tAddress: " + student.getAddress());
            System.out.println("+----------------------------------------+");
            System.out.print("\tConfirm changes? (yes/no): ");
            String confirm = scanner.next();
            if (confirm.equalsIgnoreCase("yes")) {
                System.out.println(ANSI_GREEN + "\tChanges confirmed." + ANSI_RESET);
            } else {
                System.out.println(ANSI_GREEN + "\tChanges discarded." + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "\tStudent not found." + ANSI_RESET);
            System.out.println();
        }
    }

    private static void editInstructor() {
        System.out.print("\tEnter instructor ID to edit: ");
        String id = scanner.next();
        Instructor instructor = findInstructorById(id);
        if (instructor != null) {
            // Display instructor details
            System.out.println(ANSI_CYAN + "\tCurrent Details:" + ANSI_RESET);
            System.out.println("\t\tName: " + instructor.getName());
            System.out.println("\t\tAge: " + instructor.getAge());
            System.out.println("\t\tAddress: " + instructor.getAddress());

            // Prompt for editing
            System.out.println();
            System.out.println("+---------------------------+");
            System.out.println("\t1. Edit Name");
            System.out.println("\t2. Edit Age");
            System.out.println("\t3. Edit Address");
            System.out.println();
            System.out.print("\tEnter field to edit: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                System.out.println("Edit instructor information.");
                System.out.println("\nPlease fill all the information needed. Fields with '*' are required.");
                System.out.println("Press enter to continue...");
                scanner.nextLine(); // Consume newline
                scanner.nextLine(); // Wait for enter

                String newFirstName = "";
                String newLastName = "";

                // Loop until valid input is provided for required fields
                while (newFirstName.isEmpty() || newLastName.isEmpty()) {
                    System.out.print("* New First name: ");
                    newFirstName = scanner.nextLine().trim();
                    if (newFirstName.isEmpty()) {
                        System.out.println("First name cannot be empty. Please try again.");
                        continue;
                    }

                    System.out.print("* New Last name: ");
                    newLastName = scanner.nextLine().trim();
                    if (newLastName.isEmpty()) {
                        System.out.println("Last name cannot be empty. Please try again.");
                    }
                }

                System.out.print("New Middle name: ");
                String newMiddleName = scanner.nextLine().trim();

                // Update the instructor's name
                String fullName = newFirstName + " " + newMiddleName + " " + newLastName;
                instructor.setName(fullName);
                break;
                case 2:
                    System.out.print("\tEnter new age: ");
                    int newAge = scanner.nextInt();
                    instructor.setAge(newAge);
                    break;
                case 3:
                    System.out.print("\tEnter new address: ");
                    scanner.nextLine(); // Consume newline
                    String newAddress = scanner.nextLine();
                    instructor.setAddress(newAddress);
                    break;
                default:
                    System.out.println(ANSI_RED + "\tInvalid choice!" + ANSI_RESET);
                    return;
            }

            // Confirm changes
            System.out.println(ANSI_GREEN + "\tUpdated Details:" + ANSI_RESET);
            System.out.println("\t\tName: " + instructor.getName());
            System.out.println("\t\tAge: " + instructor.getAge());
            System.out.println("\t\tAddress: " + instructor.getAddress());
            System.out.println("+----------------------------------------+");
            System.out.print("\tConfirm changes? (yes/no): ");
            String confirm = scanner.next();
            if (confirm.equalsIgnoreCase("yes")) {
                System.out.println(ANSI_GREEN + "\tChanges confirmed." + ANSI_RESET);
            } else {
                System.out.println(ANSI_GREEN + "\tChanges discarded." + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "\tInstructor not found." + ANSI_RESET);
            System.out.println();
        }
    }

    private static void editSubject() {
        System.out.print("\tEnter subject code to edit: ");
        String code = scanner.next();
        Subject subject = findSubjectByCode(code);
        if (subject != null) {
            // Display subject details
            System.out.println(ANSI_GREEN + "\tCurrent Details:" + ANSI_RESET);
            System.out.println("\t\tName: " + subject.getName());
            System.out.println("\t\tCode: " + subject.getCode());

            // Prompt for editing
            System.out.println();
            System.out.print("\tEnter new subject name: ");
            scanner.nextLine(); // Consume newline
            String newName = scanner.nextLine();
            subject.setName(newName);

            // Confirm changes
            System.out.println(ANSI_GREEN + "Updated Details:" + ANSI_RESET);
            System.out.println("\t\tName: " + subject.getName());
            System.out.println("\t\tCode: " + subject.getCode());
            System.out.println("+----------------------------------------+");
            System.out.print("\tConfirm changes? (yes/no): ");
            String confirm = scanner.next().toLowerCase();
            if (confirm.equalsIgnoreCase("yes")) {
                System.out.println(ANSI_GREEN + "\tChanges confirmed." + ANSI_RESET);
            } else {
                System.out.println(ANSI_GREEN + "\tChanges discarded." + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "\tSubject not found." + ANSI_RESET);
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

    public static void border() {
        System.out.println("+" + "-".repeat(138) + "+");
    }

}
