package control;

import adt.ListInterface;
import adt.SortedArrayList;
import boundary.courseControlUI;
import entity.Course;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Iterator;

/**
 * @author thongjiayi
 */
public class courseControl {

    private static Scanner scanner = new Scanner(System.in);

    private Course currentCourse;
    private String code;
    private Object pList;


    public ListInterface<Course> getCourseList() {
        return cList;
    }

    public void setCourseList(ListInterface<Course> courseList) {
        this.cList = cList;
    }

    /**
     * @param args the command line arguments
     */
    public ListInterface<Course> cList = new SortedArrayList<>();
    private final courseControlUI courseUI = new courseControlUI();


    public void runCourseControlUI() {
        int choice = 0;
        do {
            choice = courseUI.getMenuChoice();
            switch (choice) {
                case 0:
                    System.out.println("Exit program");
                    break;
                case 1:
                    addNewCourse();
                    break;
                case 2:
                    removeCourse();
                    break;
                case 3:
                    searchMethod();
                    break;
                case 4:
                    amendCourse();
                    break;
                case 5:
                    listCoursesTakenBySameFaculty();
                    break;
                case 6:
                    listAllCourse(cList);
                    break;
//                case 7:
//                    selectCourse();
//                    addProgramToCourse();
//                    break;
//                case 8:
//                    removeProgramFromCourse();
                case 9:
                    generateSummaryReport(cList);
                default:
                    System.err.println("Invalid input");
            }
        } while (choice != 0);
    }

    public void addNewCourse() {
        Course newCourse = inputNewCourse();

        if (isDuplicateCourseID(newCourse.getCourseID())) {
            System.err.println("Error: Course ID must be unique. A course with the same ID already exists.");
        } else {
            cList.add(newCourse);
            System.out.println("Course added successfully.");
        }
    }

    private boolean isDuplicateCourseID(String courseID) {
        for (int i = 1; i <= cList.getNumberOfEntries(); i++) {
            if (cList.getEntry(i).getCourseID().equalsIgnoreCase(courseID)) {
                return true; // Duplicate course ID found
            }
        }
        return false; // Course ID is unique
    }

    public String getAllCourse() {
        String outputStr = " ";

        for (int i = 1; i <= cList.getNumberOfEntries(); i++) {
            outputStr += cList.getEntry(i) + "\n";
        }

        return outputStr;
    }


    public void listAllCourse(ListInterface<Course> cList) {
        if (cList.getNumberOfEntries() > 0) {
            System.out.printf("%-13s %-40s %-10s %-20s %-20s%n",
                    "Course Name", "Course ID", "Credit Hours", "Type", "Faculty");

            for (int i = 1; i <= cList.getNumberOfEntries(); i++) {
                Course currentCourse = cList.getEntry(i);

                // Print course details
                System.out.printf("%-13s %-40s %-10s %-20s %-20s%n",
                        currentCourse.getCourseName(), currentCourse.getCourseID(),
                        currentCourse.getCreditHour(), currentCourse.getType(), currentCourse.getFaculty());

                // Add a line break between courses
                System.out.println();
            }
        } else {
            System.out.println("No courses available.");
        }
    }


    public String inputCourseName() {
        String courseName;
        do {
            System.out.print("Enter course name: ");
            courseName = scanner.nextLine().trim(); // Trim to remove leading/trailing whitespaces

            if (courseName.isEmpty()) {
                System.err.println("Course name cannot be empty. Please try again.");
            } else if (containsDigit(courseName)) {
                System.err.println("Course name cannot contain digits. Please try again.");
            }
        } while (courseName.isEmpty() || containsDigit(courseName)); // Repeat until a non-empty input without digits is provided

        // Consume any remaining newline characters in the input buffer
        scanner.nextLine();

        return courseName;
    }


    public String inputCourseID() {
        String courseID;
        do {
            System.out.print("Enter course ID (must be in the format 'B####'): ");
            courseID = scanner.nextLine().trim();

            if (courseID.isEmpty()) {
                System.err.println("Course ID cannot be empty. Please try again.");
            } else if (!isValidCourseIDFormat(courseID)) {
                System.err.println("Invalid course ID format. Please use 'B' followed by 4 characters and 4 digits.");
            }
        } while (courseID.isEmpty() || !isValidCourseIDFormat(courseID));
        return courseID;
    }

    public boolean isValidCourseIDFormat(String courseID) {
        // Replace this with your actual validation logic
        // For example, you might want to check if the courseID follows a specific format
        // Return true if the format is valid, false otherwise
        return true;
    }


    public double inputCreditHour() {
        double creditHour = 0;
        boolean validInput;
        do {
            System.out.print("Credit Hours: ");
            try {
                creditHour = scanner.nextDouble();
                validInput = true;

                if (creditHour <= 0) {
                    System.err.println("Credit hours must be greater than zero. Please try again.");
                    validInput = false;
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a numeric value for credit hours.");
                validInput = false;
            } finally {
                scanner.nextLine(); // Consume the newline character
            }
        } while (!validInput);

        return creditHour;
    }

    public String inputType() {
        String type;

        do {
            System.out.print("Enter status (main, resit, repeat): ");
            type = scanner.nextLine().toLowerCase(); // Convert to lowercase for case-insensitive comparison

            // Validate the input against allowed options
            if (!type.equals("main") && !type.equals("resit") && !type.equals("repeat")) {
                System.err.println("Invalid input. Please enter 'main', 'resit', or 'repeat'.");
            }

        } while (!type.equals("main") && !type.equals("resit") && !type.equals("repeat"));

        return type;
    }

    public Course inputNewCourse() {
        String courseName = inputCourseName();
        String courseID = inputCourseID();
        double creditHour = inputCreditHour();
        String type = inputType();
        String faculty = inputFaculty();  // Added faculty input
        System.out.println();
        return new Course(courseName, courseID, creditHour, type, faculty);  // Adjusted constructor
    }

    public String inputFaculty() {
        String faculty;
        do {
            System.out.print("Enter faculty: ");
            faculty = scanner.nextLine().trim();

            if (faculty.isEmpty()) {
                System.err.println("Faculty cannot be empty. Please try again.");
            }
        } while (faculty.isEmpty());

        return faculty;
    }

    public void removeCourse() {
        if (cList.isEmpty()) {
            System.out.println("No courses available to remove.");
            return;
        }

        do {
            String courseID = inputCourseIDToRemove();

            if (courseID != null) {
                boolean courseFound = false;

                for (Iterator<Course> iterator = cList.iterator(); iterator.hasNext(); ) {
                    Course currentCourse = iterator.next();

                    if (currentCourse.getCourseID().equalsIgnoreCase(courseID)) {
                        iterator.remove();
                        courseFound = true;
                        System.out.println("Course removed successfully.");
                        break;
                    }
                }

                if (!courseFound) {
                    System.out.println("Course not found. Unable to remove.");

                    System.out.print("Do you want to try removing another course? (1 for yes, 0 for no): ");
                    int tryAgain = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    if (tryAgain != 1) {
                        break;
                    }
                } else {
                    break; // Exit the loop if the course was found and removed
                }
            }
        } while (true);
    }


    public void setCode(String code) {
        // Implement the setCode method based on your requirements
        this.code = code;
    }


    private int findCourseIndex(String courseID) {
        for (int i = 0; i < cList.getNumberOfEntries(); i++) {
            if (cList.getEntry(i + 1).getCourseID().equalsIgnoreCase(courseID)) {
                return i; // Course found, return the index
            }
        }
        return -1; // Course not found
    }


    public String inputCourseIDToRemove() {
        String courseID;
        do {
            System.out.print("Enter the course ID to remove: ");
            courseID = scanner.nextLine().trim();

            if (courseID.isEmpty()) {
                System.err.println("Course ID cannot be empty. Please try again.");
            }
        } while (courseID.isEmpty());

        return courseID;
    }

    public void searchMethod() {
        System.out.print("Enter search query: ");
        String searchQuery = scanner.nextLine().trim().toLowerCase();

        if (searchQuery.isEmpty()) {
            System.err.println("Search query cannot be empty.");
            return;
        }

        System.out.println("\nSearch Results:\nCourse Name \t\t CourseID \t\t Credit Hour \t\t Type \t\t Faculty");

        for (int i = 0; i < cList.getNumberOfEntries(); i++) {
            Course c = cList.getEntry(i + 1);  // Adjusting for 1-based index
            // Convert course details to lowercase for case-insensitive comparison
            String courseDetails = (c.getCourseName() + c.getCourseID() + c.getCreditHour() + c.getType() + c.getFaculty()).toLowerCase();

            if (courseDetails.contains(searchQuery)) {
                System.out.println(c);
            }
        }

        System.out.println("End of Search Results");
    }


    public void amendCourse() {
        if (cList.isEmpty()) {
            System.out.println("No courses available to amend.");
            return;
        }

        // Display the current courses before amendment
        System.out.println("\nCurrent Courses:");
        listAllCourse(cList);

        // Prompt user for course ID to amend
        String courseIDToAmend = inputCourseIDToAmend();

        // Check if the course ID is valid
        if (courseIDToAmend != null) {
            // Find the index of the course with the specified course ID
            int index = findCourseIndex(courseIDToAmend);

            if (index != -1) {
                // Course found, prompt user for new details
                Course existingCourse = cList.getEntry(index + 1);
                System.out.println("\nEnter new details for the course:");

                // Get updated details
                String newCourseName = inputCourseName();
                double newCreditHour = inputCreditHour();
                String newType = inputType();
                String newFaculty = inputFaculty(); // Prompt for new faculty

                // Update the course details
                existingCourse.setCourseName(newCourseName);
                existingCourse.setCreditHour(newCreditHour);
                existingCourse.setType(newType);
                existingCourse.setFaculty(newFaculty); // Update faculty

                System.out.println("Course details amended successfully.");
            } else {
                System.out.println("Course not found. Unable to amend.");
            }
        }
    }


    private String inputCourseIDToAmend() {
        String courseID;
        do {
            System.out.print("Enter the course ID to amend: ");
            courseID = scanner.nextLine().trim();

            if (courseID.isEmpty()) {
                System.err.println("Course ID cannot be empty. Please try again.");
            }
        } while (courseID.isEmpty());

        return courseID;
    }


    public void listCoursesTakenBySameFaculty() {
        // Get the faculty name from the user
        String facultyName = inputFaculty();

        // Check if the provided faculty name is empty
        if (facultyName.isEmpty()) {
            System.err.println("Faculty name cannot be empty.");
            return;
        }

        // Flag to check if any courses are found for the given faculty
        boolean foundCourses = false;

        System.out.println("\nList of Courses: \nCourse Name \t\t CourseID \t\t Credit Hour \t\t Type \t Faculty");


        // Iterate through the list of courses
        for (int i = 1; i <= cList.getNumberOfEntries(); i++) {
            Course currentCourse = cList.getEntry(i);

            // Check if the current course has the same faculty name
            if (facultyName.equalsIgnoreCase(currentCourse.getFaculty())) {
                // Print the course information
                System.out.println(currentCourse);

                // Set the flag to true since at least one course is found
                foundCourses = true;
            }
        }

        // Check if no courses are found for the given faculty
        if (!foundCourses) {
            System.out.println("No courses found for the given faculty: " + facultyName);
        }
    }
    // Helper method to print courses


    public void displayCourse() {
        //check if cList is not null before calling 
        if (cList != null) {
            listAllCourse(cList);
        } else {
            System.out.println("cList is null");
        }
    }

    private boolean containsDigit(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true; // The string contains a digit
            }
        }
        return false; // The string does not contain any digit
    }


    private String inputProgramCodeToAdd() {
        String programCode;
        do {
            System.out.print("Enter the program code to add: ");
            programCode = scanner.nextLine().trim();

            if (programCode.isEmpty()) {
                System.err.println("Program code cannot be empty. Please try again.");
            }
        } while (programCode.isEmpty());

        return programCode;
    }


    private boolean isValidProgramCode(String programCode) {
        // Check if the program code exists in the list of programs
        return findProgrammeByCode(programCode) != null;
    }
    // Inside courseControl class


    public void selectCourse() {
        int courseChoice = getCourse();
        if (courseChoice >= 1 && courseChoice <= cList.getNumberOfEntries()) {
            currentCourse = cList.getEntry(courseChoice);
            System.out.println("Course selected: " + currentCourse.getCourseName());
        } else {
            System.out.println("Invalid course selection.");
        }
    }


    public void listAllProgrammes() {
        // Get the program from the user
        String programmeName = inputProgramme();

        // Check if the user selected a program
        if (programmeName == null) {
            System.out.println("No program selected. Exiting.");
            return;
        }

        // Flag to check if any courses are found for the given program
        boolean foundCourses = false;

        System.out.println("\nList of Courses for Programme " + programmeName + ": \nCourse Name \t\t CourseID \t\t Credit Hour \t\t Type \t Faculty");

        // Iterate through the list of courses
        for (int i = 1; i <= cList.getNumberOfEntries(); i++) {
            // Check if the current course is associated with the chosen program
//        if (currentCourse.getProgrammes().contains(programmeName)) {
//            // Print the course information
//            System.out.println(currentCourse);
//
//            // Set the flag to true since at least one course is found
//            foundCourses = true;
//        }

        }


        // Check if no courses are found for the given program
        if (!foundCourses) {
            System.out.println("No courses found for the given program: " + programmeName);
        }
    }

    // Assume you have a Course class and a Programme class

    public void addProgrammeToCourse() {
//    listAllCourse(cList);
//
//    String courseID = inputCourseID();
//    course course = findCourseByID(courseID);
//
//    if (course != null) {
//        String selectedProgramme = inputProgramme();
//        Programme programme = findProgramme(selectedProgramme, pList);
//
//        if (programme != null) {
//            course.addProgramme(programme);
//            System.out.println("Programme added to the course successfully!");
//        } else {
//            System.out.println(selectedProgramme + " does not exist");
//        }
//    } else {
//        System.out.println(courseID + " does not exist");
//    }
    }

    public void removeProgrammeFromCourse() {
//    listAllCourse(cList);
//
//    String courseID = inputCourseID();
//    course course = findCourseByID(courseID);
//
//    if (course != null) {
//        showProgrammesInCourse(course);
//
//        String programmeID = removeProgrammeID();
//
//        Programme programme = findProgrammeByID(programmeID);
//
//        if (programme != null) {
//            course.removeProgramme(programme);
//            System.out.println("Programme removed successfully from the course.");
//        } else {
//            System.out.println(programmeID + " not found.");
//        }
//    } else {
//        System.out.println(courseID + " does not exist");
//    }
    }


    public Programme inputNewProgramme() {
        System.out.print("Enter programme code: ");
        String programCode = scanner.next().trim();

        // Consume the newline character left in the buffer
        scanner.nextLine();

        System.out.print("Enter programme name: ");
        String programName = scanner.nextLine().trim();

        int duration = inputProgrammeDuration();

        return new Programme(programCode, programName, duration);
    }

    // Add this method to handle program duration input
    public int inputProgrammeDuration() {
        int duration = -1; // Set a default value, or any value that indicates no user input
        do {
            System.out.print("Enter programme duration: ");
            try {
                duration = scanner.nextInt();
                if (duration < 0) {
                    System.err.println("Duration must be a non-negative integer. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a valid integer for duration.");
                duration = -1; // Set a value to ensure the loop continues
            } finally {
                scanner.nextLine(); // Consume the newline character
            }
        } while (duration < 0);

        return duration;
    }


    public String inputProgrammeName() {
        System.out.print("Enter programme name: ");
        return scanner.nextLine().trim();
    }


    public static int inputDuration(String prompt) {
        int duration = 0;
        do {
            System.out.print(prompt);
            try {
//            duration = scanner.nextInt();
                if (duration < 0) {
                    System.err.println("Duration must be a non-negative integer. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a valid integer for duration.");
                duration = -1; // Set a value to ensure the loop continues
            } finally {
//            scanner.nextLine(); // Consume the newline character
            }
        } while (duration < 0);

        return duration;
    }


    // Assuming you have a method named removeProgram in the Course class
//public void removeProgramFromCourse() {
//    if (currentCourse == null) {
//        System.out.println("Error: No current course selected.");
//        return;
//    }
//
//    // Display existing programs
//    System.out.println("List of Available Programs:");
//    for (int i = 1; i <= currentCourse.getProgramList().getNumberOfEntries(); i++) {
//        Programme currentProgram = currentCourse.getProgramList().getEntry(i);
//        System.out.println((i) + ". " + currentProgram.getProgrammeID());
//    }
//
//    System.out.println("Enter the program number to remove: ");
//    int programNumberToRemove = validateIntegerInput("Program Number: ");
//
//    // Check if the chosen index is valid
//    if (programNumberToRemove > 0 && programNumberToRemove <= currentCourse.getProgramList().getNumberOfEntries()) {
//        Programme programToRemove = currentCourse.getProgramList().getEntry(programNumberToRemove);
//        currentCourse.removeProgram(programToRemove.getProgrammeID());
//        System.out.println("Program removed successfully.");
//    } else {
//        System.out.println("Invalid program selection.");
//    }
//}


    private String inputProgramCodeToRemove() {
        String programCode;
        do {
            System.out.print("Enter the program code to remove: ");
            programCode = scanner.nextLine().trim();

            if (programCode.isEmpty()) {
                System.err.println("Program code cannot be empty. Please try again.");
            }
        } while (programCode.isEmpty());

        return programCode;
    }

    private void displayAssociatedProgram(Course existingCourse, Object associatedProgram) {
        /*Programme*/
        Object associatedProgram1 = associatedProgram;

        if (associatedProgram != null) {
            System.out.println("Associated Program: " + associatedProgram);
        } else {
            System.out.println("No program associated with this course.");
        }
    }

    public void generateSummaryReport(ListInterface<Course> cList) {
        // Get the current date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);

        // Display report header
        System.out.println("Summary Report for Course Management Subsystem:");
        System.out.println("Generated on: " + formattedDate);
//        System.out.println("Generated by: " + );
        System.out.println();


        // Display course information
        System.out.println("\nCourses:");
        for (int i = 1; i <= cList.getNumberOfEntries(); i++) {
            Course course = cList.getEntry(i);
            System.out.println("Course ID: " + course.getCourseID());
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Credit Hour: " + course.getCreditHour());
            System.out.println("Type of Exam: " + course.getType());
            System.out.println("Faculty: " + course.getFaculty());

//        // Display associated programs
//        System.out.println("Associated Programs:");
//        for (Programme programme : course.getProgrammes()) {
//            System.out.println("  Program ID: " + programme.getProgrammeID());
//            System.out.println("  Program Name: " + programme.getProgrammeName());
//        }
//        System.out.println();
//    }
//
//    // Display program information
//    System.out.println("\nPrograms:");
//    for (int i = 1; i <= pList.getNumberOfEntries(); i++) {
//        Programme programme = pList.getEntry(i);
//        System.out.println("Program ID: " + programme.getProgrammeID());
//        System.out.println("Program Name: " + programme.getProgrammeName());
//        System.out.println("Duration: " + programme.getDuration());
//
//        // Display associated courses
//        System.out.println("Associated Courses:");
//        for (course course : programme.getCourse()) {
//            System.out.println("  Course ID: " + course.getCourseID());
//            System.out.println("  Course Name: " + course.getCourseName());
//        }
//        System.out.println();
        }
    }


    public static void main(String[] args) {
        courseControl courseControl = new courseControl();
        courseControl.runCourseControlUI();

        courseControl courseController = new courseControl();
        courseController.generateSummaryReport(courseController.cList);
    }


    private Programme findProgrammeByCode(String programCodeToAdd) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void displayAssociatedProgram(Course existingCourse) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String inputProgramme() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    private int getCourse() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Course findCourseByID(String courseID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void showProgrammesInCourse(Course course) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String removeProgrammeID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Programme findProgrammeByID(String programmeID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    private static class Programme {

        public Programme(String programCode, String programName, int duration) {
        }

        private String getProgramCode() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    private static class associatedPrograms {

        public associatedPrograms() {
        }
    }

    public static class addProgramme {

        public addProgramme() {
        }
    }

    private static class chosenProgramCode {

        public chosenProgramCode() {
        }
    }


}
