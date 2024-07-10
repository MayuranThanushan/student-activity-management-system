import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    private static final int MAXCAPACITY = 100;
    private static Student[] students = new Student[MAXCAPACITY];
    private static int studentCount = 0;
    private static final String STUDENTIDTEXT = "Enter student ID: ";
    private static final String ERRORIDTEXT = "Invalid ID format.";
    private static final String ERRORSTUDENTTEXT = "Student not found.";
    private static final String ERRORSMARKTEXT = "Invalid module mark. Marks should be between 0 and 100.";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Student Management System...");

        while (true) {
            showMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        checkAvailableSeats();
                        break;
                    case 2:
                        registerStudent(scanner);
                        break;
                    case 3:
                        deleteStudent(scanner);
                        break;
                    case 4:
                        findStudent(scanner);
                        break;
                    case 5:
                        storeStudentDetails();
                        break;
                    case 6:
                        loadStudentDetails();
                        break;
                    case 7:
                        viewStudentsSortedByName();
                        break;
                    case 8:
                        extraControls(scanner);
                        break;
                    case 0:
                        System.out.println("Exiting program...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Displays the menu options for the user.
     * The method prints the menu and prompts the user to enter their choice.
     */
    private static void showMenu() {
        System.out.println("""

                ________ MENU ________
                1. Check available seats
                2. Register student
                3. Delete student
                4. Find student
                5. Store student details into a file
                6. Load student details from the file
                7. View the list of students based on their names
                8. Extra controls
                0. Exit""");
        System.out.print("Enter your choice: ");
    }

    /**
     * Checks and displays the number of available seats.
     * This method calculates the available seats by subtracting the
     * current number of registered students (studentCount) from the
     * maximum capacity (MAXCAPACITY). It then prints the result.
     */
    private static void checkAvailableSeats() {
        int availableSeats = MAXCAPACITY - studentCount;
        System.out.println("Available seats: " + availableSeats);
    }

    /**
     * Registers a new student if there are available seats.
     * This method performs the following steps:
     * 1. Checks if there are available seats. If not, it prints a message and returns.
     * 2. Prompts the user for a student ID and validates it.
     * 3. Checks if the student ID is already registered. If so, it prints a message and returns.
     * 4. Prompts the user for the student's name.
     * 5. Creates a new Student object with the provided ID and name.
     * 6. Adds the new student to the students array and increments the student count.
     * 7. Prints a success message.
     * @param scanner the Scanner object to read user input
     */
    private static void registerStudent(Scanner scanner) {
        if (studentCount >= MAXCAPACITY) {
            System.out.println("No available seats.");
            return;
        }
        System.out.print(STUDENTIDTEXT);
        String id = scanner.next();
        if (isNotValidId(id)) {
            System.out.println(ERRORIDTEXT);
            return;
        }

        for (int i = 0; i < studentCount; ++i) {
            if (students[i].getId().equals(id)) {
                System.out.println("Student " + id + " has already been registered.");
                return;
            }
        }
        System.out.print("Enter student name: ");
        String name = scanner.next();

        Student student = new Student(id, name);
        students[studentCount++] = student;
        System.out.println("Student registered successfully.");
    }

    /**
     * Checks if the given student ID is not valid.
     * A valid student ID must match the following pattern: "w" followed by 7 digits.
     * For example, "w1234567" is a valid ID.
     * @param id the student ID to be validated
     * @return true if the ID is not valid, false otherwise
     */
    private static boolean isNotValidId(String id) {
        return !id.matches("^w\\d{7}$");
    }

    /**
     * Deletes a student from the students array based on the given student ID.
     * This method performs the following steps:
     * 1. Prompts the user to enter a student ID to delete.
     * 2. Validates the student ID. If not valid, prints an error message and returns.
     * 3. Searches for the student with the matching ID in the students array.
     * 4. If found, deletes the student from the array, shifts remaining elements, and decrements studentCount.
     * 5. Prints a success message if the student is deleted.
     * 6. Prints an error message if no student with the given ID is found.
     * @param scanner the Scanner object to read user input
     */
    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter student ID to delete: ");
        String id = scanner.next();
        if (isNotValidId(id)) {
            System.out.println(ERRORIDTEXT);
            return;
        }
        for (int i = 0; i < studentCount; ++i) {
            if (students[i].getId().equals(id)) {
                students[i] = null;
                studentCount--;
                for (int j = i; j < studentCount; ++j) {
                    students[j] = students[j + 1];
                }
                System.out.println("Student " + id + " has been deleted.");
                return;
            }
        }
        System.out.println(ERRORSTUDENTTEXT);
    }

    /**
     * Finds and displays student details based on the given student ID.
     * This method performs the following steps:
     * 1. Prompts the user to enter a student ID to find.
     * 2. Validates the student ID. If not valid, prints an error message and returns.
     * 3. Searches for the student with the matching ID in the students array.
     * 4. If found, prints the student's name and ID.
     * 5. If no student with the given ID is found, prints an error message.
     * @param scanner the Scanner object to read user input
     */
    private static void findStudent(Scanner scanner) {
        System.out.print("Enter student ID to find: ");
        String id = scanner.next();
        if (isNotValidId(id)) {
            System.out.println(ERRORIDTEXT);
            return;
        }
        for (int i = 0; i < studentCount; ++i) {
            if (students[i].getId().equals(id)) {
                System.out.println("Student found:");
                System.out.println("Name: " + students[i].getName() + ", Id: " + students[i].getId());
                return;
            }
        }
        System.out.println(ERRORSTUDENTTEXT);
    }

    /**
     * Stores student details into a text file.
     * This method sorts the students by average marks using bubble sort, and then
     * writes each student's details to a file named "studentDetails.txt". Each student's
     * details include ID, name, marks for three modules, total marks, average marks,
     * and grade.
     * If an IOException occurs while writing the file, an error message is printed.
     */
    private static void storeStudentDetails() {
        try (FileWriter writer = new FileWriter("studentDetails.txt")) {
            Student[] sortedStudents = Arrays.copyOf(students, studentCount);
            bubbleSortByAverage(sortedStudents);

            for (Student student : sortedStudents) {
                Module module = student.getModule();
                int totalModuleMarks = Arrays.stream(module.getModuleMarks()).sum();
                writer.write("ID: " + student.getId() +
                        ", Name: " + student.getName() +
                        ", Module 1: " + module.getModuleMarks()[0] +
                        ", Module 2: " + module.getModuleMarks()[1] +
                        ", Module 3: " + module.getModuleMarks()[2] +
                        ", Total: " + totalModuleMarks +
                        ", Average: " + module.calculateAverage() +
                        ", Grade: " + module.getGrade());
                writer.write("\n");
            }
            System.out.println("Stored student details into a file.");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    /**
     * Loads student details from a text file into the application.
     * This method reads student details from a file named "studentDetails.txt". Each line
     * in the file represents a student's information including ID, name, module marks,
     * total marks, average marks, and grade. It parses each line, extracts the necessary
     * information, creates Student objects, and stores them in the students array.
     * If an IOException or NumberFormatException occurs while reading or parsing the file,
     * an error message is printed.
     */
    private static void loadStudentDetails() {
        try (BufferedReader reader = new BufferedReader(new FileReader("studentDetails.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String id = parts[0].substring(4);
                    String name = parts[1].substring(7);
                    int[] moduleMarks = {
                            Integer.parseInt(parts[2].substring(11)),
                            Integer.parseInt(parts[3].substring(11)),
                            Integer.parseInt(parts[4].substring(11))

                    };

                    Student student = new Student(id, name);
                    student.getModule().setModuleMarks(moduleMarks);
                    students[studentCount++] = student;
                }
            }
            System.out.println("Student details loaded successfully.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading student details: " + e.getMessage());
        }
    }

    /**
     * Displays a list of students sorted by their names.
     * This method creates a copy of the students array, sorts it alphabetically by
     * student names, and then prints each student's name and ID. If no students are
     * registered (studentCount is 0), it prints a message indicating that no students
     * were found.
     */
    private static void viewStudentsSortedByName() {
        Student[] sortedStudents = Arrays.copyOf(students, studentCount);
        Arrays.sort(sortedStudents, Comparator.comparing(Student::getName));
        if (studentCount == 0) {
            System.out.println("No Student found.");
        }
        for (Student student : sortedStudents) {
            System.out.println("Name: " + student.getName() + ", Id: " + student.getId());
        }
    }

    /**
     * Provides additional controls based on user input.
     * This method displays an extra menu of options and allows the user to choose
     * an action:
     * - 'a': Add a student name
     * - 'b': Add module marks for a student
     * - 'c': Generate a summary of student details
     * - 'd': Generate a complete report of student details
     * If an invalid choice is entered, it prints a message asking the user to try again.
     * @param scanner the Scanner object to read user input
     */
    private static void extraControls(Scanner scanner) {
        showExtraMenu();
        char choice = scanner.next().toLowerCase().charAt(0);
        switch (choice) {
            case 'a':
                addStudentName(scanner);
                break;
            case 'b':
                addModuleMarks(scanner);
                break;
            case 'c':
                generateSummary();
                break;
            case 'd':
                generateCompleteReport();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    /**
     * Displays an extra menu of options for additional controls.
     * Users are prompted to enter their choice after the menu is displayed.
     */
    private static void showExtraMenu() {
        System.out.println("""

                a. Add student name
                b. Add module marks
                c. Generate summary
                d. Generate complete report""");
        System.out.print("Enter your choice: ");
    }

    /**
     * Updates the name of a student based on the provided student ID.
     * This method performs the following steps:
     * 1. Prompts the user to enter a student ID.
     * 2. Validates the student ID. If not valid, prints an error message and returns.
     * 3. Searches for the student with the matching ID in the students array.
     * 4. If found, prompts the user to enter a new name for the student and updates the name.
     * 5. Prints a success message if the student's name is updated.
     * 6. Prints an error message if no student with the given ID is found.
     * @param scanner the Scanner object to read user input
     */
    private static void addStudentName(Scanner scanner) {
        System.out.print(STUDENTIDTEXT);
        String id = scanner.next();
        if (isNotValidId(id)) {
            System.out.println(ERRORIDTEXT);
            return;
        }
        for (int i = 0; i < studentCount; ++i) {
            if (students[i].getId().equals(id)) {
                System.out.print("Enter new student name: ");
                students[i].setName(scanner.next());
                System.out.println("Student name updated successfully.");
                return;
            }
        }
        System.out.println(ERRORSTUDENTTEXT);
    }


    /**
     * Updates module marks for a student based on the provided student ID.
     * This method performs the following steps:
     * 1. Prompts the user to enter a student ID.
     * 2. Validates the student ID. If not valid, prints an error message and returns.
     * 3. Searches for the student with the matching ID in the students array.
     * 4. If found, prompts the user to enter marks for three modules (Module 1, Module 2, Module 3).
     *    - Each module mark is validated to ensure it is within a valid range.
     * 5. Updates the module marks and recalculates the grade for the student's modules.
     * 6. Prints a success message if the module marks and grade are updated.
     * 7. Prints an error message if no student with the given ID is found.
     * @param scanner the Scanner object to read user input
     */
    private static void addModuleMarks(Scanner scanner) {
        System.out.print(STUDENTIDTEXT);
        String id = scanner.next();
        if (isNotValidId(id)) {
            System.out.println(ERRORIDTEXT);
            return;
        }
        for (int i = 0; i < studentCount; ++i) {
            if (students[i].getId().equals(id)) {
                int[] marks = new int[3];
                System.out.print("Enter marks for Module 1: ");
                marks[0] = scanner.nextInt();
                if (isNotValidMarks(marks[0])) {
                    System.out.println(ERRORSMARKTEXT);
                    return;
                }

                System.out.print("Enter marks for Module 2: ");
                marks[1] = scanner.nextInt();
                if (isNotValidMarks(marks[1])) {
                    System.out.println(ERRORSMARKTEXT);
                    return;
                }

                System.out.print("Enter marks for Module 3: ");
                marks[2] = scanner.nextInt();
                if (isNotValidMarks(marks[2])) {
                    System.out.println(ERRORSMARKTEXT);
                    return;
                }
                scanner.nextLine();

                students[i].getModule().setModuleMarks(marks);
                students[i].getModule().calculateGrade();

                System.out.println("Module marks and grade updated successfully.");
                return;
            }
        }
        System.out.println(ERRORSTUDENTTEXT);
    }

    /**
     * Checks if the provided marks are not valid.
     * Valid marks range from 0 to 100 (inclusive). This method checks if
     * the provided marks fall outside this range.
     * @param marks the marks to validate
     * @return true if the marks are not valid, false otherwise
     */
    private static boolean isNotValidMarks(int marks) {
        return marks < 0 || marks > 100;
    }

    private static void generateSummary() {
        int totalStudents = studentCount;
        int studentsPassedModule = 0;

        for (int i = 0; i < studentCount; i++) {
            Module module = students[i].getModule();
            if (module.getModuleMarks()[0] >= 40 &&
                    module.getModuleMarks()[1] >= 40 &&
                    module.getModuleMarks()[2] >= 40) {
                studentsPassedModule++;
            }
        }

        System.out.println("Total student registrations: " + totalStudents);
        System.out.println("Total students who scored more than 40 marks in module 1,2 and 3: " + studentsPassedModule);
    }

    /**
     * Generates a summary of student performance.
     * This method calculates and prints the following information:
     * - Total number of student registrations.
     * - Number of students who scored 40 or more marks in all three modules (Module 1, Module 2, Module 3).
     * The summary provides insights into the overall performance of students based on module marks criteria.
     */
    private static void generateCompleteReport() {
        Student[] sortedStudents = Arrays.copyOf(students, studentCount);
        bubbleSortByAverage(sortedStudents);

        for (Student student : sortedStudents) {
            Module module = student.getModule();
            int totalModuleMarks = Arrays.stream(module.getModuleMarks()).sum();
            if (module.getGrade() == null) {
                System.out.println("ID: " + student.getId() +
                        ", Name: " + student.getName() + ", Marks not updated yet.");
            }
            else {
                System.out.println("ID: " + student.getId() +
                        ", Name: " + student.getName() +
                        ", Module 1: " + module.getModuleMarks()[0] +
                        ", Module 2: " + module.getModuleMarks()[1] +
                        ", Module 3: " + module.getModuleMarks()[2] +
                        ", Total: " + totalModuleMarks +
                        ", Average: " + module.calculateAverage() +
                        ", Grade: " + module.getGrade());
            }
        }
    }

    /**
     * Sorts an array of students based on the average marks using bubble sort.
     * This method sorts the array of students in ascending order based on the average marks
     * calculated from each student's module scores.
     * @param students the array of Student objects to be sorted
     */
    private static void bubbleSortByAverage(Student[] students) {
        int n = students.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Module module1 = students[j].getModule();
                Module module2 = students[j + 1].getModule();
                double average1 = module1.calculateAverage();
                double average2 = module2.calculateAverage();
                if (average1 < average2) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }
}
