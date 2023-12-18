
package course;

import adt.SortedArrayList;
import entity.Course;
import entity.Programme;

import java.util.Scanner;

/**
 * @author thongjiayi
 */
public class CourseManagement {

    private final static SortedArrayList<Programme> progList = new SortedArrayList<>();
    private final static SortedArrayList<Course> courseList = new SortedArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Course discreteMath = new Course("Discrete Mathematics", "BACS1013", 3, "Main", "FAFB");
        Course databaseManagement = new Course("Database Management", "BACS1053", 3, "Main", "FAFB");
        Course itFundamental = new Course("IT Fundamental", "BAIT1173", 3, "Main", "FOCS");
        Course academicEnglish = new Course("Academic English", "BJEL1023", 3, "Main", "FOBE");
        Course coCurricular = new Course("Co-curricular", "BCOQ", 2, "Main", "FOCS");

        courseList.add(discreteMath);
        courseList.add(databaseManagement);
        courseList.add(itFundamental);
        courseList.add(academicEnglish);
        courseList.add(coCurricular);

        SortedArrayList<Course> rsdCourseList = new SortedArrayList<>();
        rsdCourseList.add(discreteMath);
        rsdCourseList.add(databaseManagement);
        rsdCourseList.add(itFundamental);

        SortedArrayList<Course> reiCourseList = new SortedArrayList<>();
        reiCourseList.add(databaseManagement);
        reiCourseList.add(itFundamental);
        reiCourseList.add(coCurricular);

        SortedArrayList<Course> rstCourseList = new SortedArrayList<>();
        rstCourseList.add(itFundamental);
        rstCourseList.add(academicEnglish);

        SortedArrayList<Course> rdsCourseList = new SortedArrayList<>();
        rdsCourseList.add(academicEnglish);
        rdsCourseList.add(coCurricular);
        rdsCourseList.add(discreteMath);

        SortedArrayList<Course> rseCourseList = new SortedArrayList<>();
        rseCourseList.add(coCurricular);
        rseCourseList.add(databaseManagement);
        rseCourseList.add(discreteMath);

        progList.add(new Programme("RSD", "Software Development", 3, rsdCourseList));
        progList.add(new Programme("REI", "Enterprise Information Systems", 3, reiCourseList));
        progList.add(new Programme("RST", "Interactive Software Technology", 3, rstCourseList));
        progList.add(new Programme("RDS", "Data Science", 3, rdsCourseList));
        progList.add(new Programme("RSE", "Software Engineering", 3, rseCourseList));

        courseMenu();
    }

    public static void courseMenu() {
        Scanner sc = new Scanner(System.in);
        char choice;

        do {
            System.out.println("Course Management");
            System.out.println("1. Add Course");
            System.out.println("2. Add Course To Programme");
            System.out.println("3. Search Method");
            System.out.println("4. Update Course");
            System.out.println("5. Remove Course");
            System.out.println("6. View Programme's Course List");
            System.out.println("7. Remove Course from Programme");
            System.out.println("8. Summary Report");
            System.out.println("9. Back");


            System.out.print("Enter your choice: ");

            choice = sc.next().charAt(0);

            switch (choice) {
                case '1':
                    addCourse();
                    break;
                case '2':
                    addCourseToProgramme();
                    break;
                case '3':
                    searchMethod();
                    break;
                case '4':
                    updateCourse();
                    break;
                case '5':
                    removeCourse();
                    break;
                case '6':
                    viewProgramCourse();
                    break;
                case '7':
                    removeCourseFromProgramme();
                    break;
                case '8':
                    generateSummaryReport();
                    break;
                case '9':
                    break;
                default:
                    System.out.println("Invalid input. Please try again.\n");
                    break;
            }

        } while (choice != '7');
    }

    public static Programme searchProgramme(String programmeCode) {
        for (int i = 0; i < progList.getNumberOfEntries(); i++) {
            Programme p = progList.getEntry(i);
            if (p.getProgrammeID().equalsIgnoreCase(programmeCode)) {
                return p;
            }
        }
        return null;
    }

    public static Course searchCourse(String courseCode) {
        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            Course c = courseList.getEntry(i);
            if (c.getCourseID().equalsIgnoreCase(courseCode)) {
                return c;
            }
        }
        return null;
    }

    public static SortedArrayList<Programme> searchCourseFromProgram(String courseCode) {
        SortedArrayList<Programme> tempProgList = new SortedArrayList<>();

        for (int i = 0; i < progList.getNumberOfEntries(); i++) {
            Programme p = progList.getEntry(i);
            for (int j = 0; j < p.getCourseList().getNumberOfEntries(); j++) {
                Course c = p.getCourseList().getEntry(j);
                if (c.getCourseID().equalsIgnoreCase(courseCode)) {
                    tempProgList.add(p);
                }
            }
        }

        if (tempProgList.isEmpty()) {
            return null;
        } else {
            return tempProgList;
        }
    }

    public static void addCourse() {
        Scanner sc = new Scanner(System.in);
        Scanner chSc = new Scanner(System.in);
        char choice = 0;
        String creditHour = "";
        System.out.println("\nAdd A Course");

        do {
            System.out.print("Enter Course ID: ");
            String courseID = sc.nextLine().toUpperCase();
            System.out.print("Enter Course Name: ");
            String courseName = sc.nextLine();

            do {
                System.out.print("Enter Credit Hour: ");
                creditHour = sc.nextLine();

                if (!creditHour.matches("[0-9]+")) {
                    System.out.println("Invalid input. Please try again.");
                }
            } while (!creditHour.matches("[0-9]+"));

            System.out.print("Enter Course Type: ");
            String tempType = sc.nextLine();
            String type = tempType.substring(0, 1).toUpperCase() + tempType.toUpperCase().substring(1);
            System.out.print("Enter Faculty: ");
            String faculty = sc.nextLine().toUpperCase();

            Course newCourse = new Course(courseName, courseID, Double.parseDouble(creditHour), type, faculty);

            if (courseList.contains(newCourse)) {
                System.out.println("Course already exist.");
            } else {
                courseList.add(newCourse);
                System.out.println("Course added successfully.");
            }

            System.out.print("Do you want to add next one? (N/n to quit): ");
            choice = chSc.next().charAt(0);
            System.out.println();

        } while (choice != 'N' && choice != 'n');
    }

    private static void addCourseToProgramme() {
        Scanner sc = new Scanner(System.in);
        Scanner chSc = new Scanner(System.in);
        Scanner innerChSc = new Scanner(System.in);
        char choice, innerChoice = 0;

        System.out.println("\nAdd Course To Programme");

        do {
            System.out.print("Enter programme ID: ");
            String programmeID = sc.nextLine();

            Programme foundProg = searchProgramme(programmeID);

            if (foundProg != null) {
                do {
                    System.out.println("\nExisting Course List: ");
                    System.out.printf("%-25s %-25s %-20s %-10s %-10s\n", "Course Name", "Course ID", "Credit Hour", "Type", "Faculty");
                    System.out.println(courseList);

                    System.out.print("Enter course ID to add: ");
                    String courseID = sc.nextLine();

                    Course foundCourse = searchCourse(courseID);

                    if (foundCourse != null) {
                        if (foundProg.getCourseList().contains(foundCourse)) {
                            System.out.println("Course already exist in programme.");
                        } else {
                            foundProg.getCourseList().add(foundCourse);
                            System.out.println("Course added successfully.");
                        }
                    } else {
                        System.out.println("Course not found.");
                    }

                    System.out.print("Do you want to add next course to this programme? (N/n to quit): ");
                    innerChoice = innerChSc.next().charAt(0);

                } while (innerChoice != 'N' && innerChoice != 'n');

            } else {
                System.out.println("Programme not found.");
            }

            System.out.print("Do you want to add next programme? (N/n to quit): ");
            choice = chSc.next().charAt(0);

        } while (choice != 'N' && choice != 'n');
    }

    public static void updateCourse() {
        String courseID;
        char choice;
        Scanner sc = new Scanner(System.in);
        Scanner chSc = new Scanner(System.in);

        System.out.println("\nUpdate Course");

        do {

            if (courseList.isEmpty()) {
                System.out.println("No courses available to amend.");
                return;
            }

            // Display the current courses before amendment
            System.out.println("\nCurrent Courses:");
            System.out.printf("%-25s %-25s %-20s %-10s %-10s\n", "Course Name", "Course ID", "Credit Hour", "Type", "Faculty");
            System.out.println(courseList);

            // Prompt user for course ID to amend
            do {
                System.out.print("Enter the course ID to amend: ");
                courseID = sc.nextLine().trim();

                if (courseID.isEmpty()) {
                    System.out.println("Course ID cannot be empty. Please try again.");
                }

            } while (courseID.isEmpty());

            Course foundCourse = searchCourse(courseID);

            if (foundCourse != null) {
                // Course found, prompt user for new details
                System.out.println("\nEnter new details for the course:");

                do {
                    System.out.print("Enter Course Name: ");
                    String newCourseName = sc.nextLine();

                    if (newCourseName.isEmpty() || !newCourseName.matches("[A-Za-z]+")) {
                        System.out.println("Course Name cannot be empty. Please try again.");
                    } else {
                        foundCourse.setCourseName(newCourseName);
                        break;
                    }
                } while (true);

                do {
                    System.out.print("Enter Credit Hour: ");
                    String creditHour = sc.nextLine();

                    if (!creditHour.matches("[0-9]+")) {
                        System.out.println("Invalid input. Please try again.");
                    } else {
                        foundCourse.setCreditHour(Double.parseDouble(creditHour));
                        break;
                    }
                } while (true);

                do {
                    System.out.print("Enter Course Type: ");
                    String tempType = sc.nextLine();
                    String newType = tempType.substring(0, 1).toUpperCase() + tempType.toUpperCase().substring(1);

                    if (newType.isEmpty()) {
                        System.out.println("Course Type cannot be empty. Please try again.");
                    } else {
                        foundCourse.setType(newType);
                        break;
                    }
                } while (true);

                do {
                    System.out.print("Enter Faculty: ");
                    String newFaculty = sc.nextLine().toUpperCase();

                    if (newFaculty.length() != 4 || !newFaculty.matches("[A-Z]+")) {
                        System.out.println("Faculty cannot be empty. Please try again.");
                    } else {
                        foundCourse.setFaculty(newFaculty);
                        break;
                    }
                } while (true);

                updateProgramCourseDetails(foundCourse);

                System.out.println("Course details amended successfully.");
            } else {
                System.out.println("Course not found. Unable to amend.");
            }

            System.out.print("Do you want to amend another course? (Y/N): ");
            choice = chSc.next().charAt(0);
            System.out.println();

        } while (choice != 'N' && choice != 'n');
    }

    public static void updateProgramCourseDetails(Course amendedCourse){
        for (int i = 0; i < progList.getNumberOfEntries(); i++) {
            Programme p = progList.getEntry(i);
            for (int j = 0; j < p.getCourseList().getNumberOfEntries(); j++) {
                Course c = p.getCourseList().getEntry(j);
                if (c.getCourseID().equalsIgnoreCase(amendedCourse.getCourseID())) {
                    p.getCourseList().getEntry(j).setCourseName(amendedCourse.getCourseName());
                    p.getCourseList().getEntry(j).setCreditHour(amendedCourse.getCreditHour());
                    p.getCourseList().getEntry(j).setType(amendedCourse.getType());
                    p.getCourseList().getEntry(j).setFaculty(amendedCourse.getFaculty());
                }
            }
        }
    }

    public static void searchMethod() {
        Scanner sc = new Scanner(System.in);
        String alignment = "%-25s %-25s %-20s %-10s %-10s\n";
        System.out.println();
        System.out.print("Enter search query: ");
        String searchQuery = sc.nextLine().trim().toLowerCase();

        if (searchQuery.isEmpty()) {
            System.err.println("Search query cannot be empty.");
            return;
        }

        System.out.println("\nSearch Results:");
        System.out.printf(alignment, "Course Name", "Course ID", "Credit Hour", "Type", "Faculty");

        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            Course foundCourse = courseList.getEntry(i);
            String courseDetails = (foundCourse.getCourseName() + foundCourse.getCourseID() + foundCourse.getCreditHour() + foundCourse.getType() + foundCourse.getFaculty()).toLowerCase();

            if (courseDetails.contains(searchQuery)) {
                System.out.println(foundCourse);
            }
        }

        System.out.println("End of Search Results");
        System.out.println();
    }

    public static void removeCourse() {
        Scanner sc = new Scanner(System.in);
        Scanner chSc = new Scanner(System.in);
        char choice = 0;
        System.out.println("\nRemove A Course");

        do {
            System.out.println("\nExisting Course List: ");
            System.out.printf("%-25s %-25s %-20s %-10s %-10s\n", "Course Name", "Course ID", "Credit Hour", "Type", "Faculty");
            System.out.println(courseList);

            System.out.print("Enter Course ID: ");
            String courseID = sc.nextLine();

            SortedArrayList<Programme> foundCourseFromProgram = searchCourseFromProgram(courseID);
            Course foundCourse = searchCourse(courseID);

            if (foundCourse != null) {
                if (foundCourseFromProgram != null) {
                    System.out.printf("\nThe Course ID: (%s) appeared in the Programme below: ", courseID);
                    System.out.printf("\n%-15s %-40s %10s%n", "Programme ID", "Programme Name", "Duration");

                    for (int i = 0; i < foundCourseFromProgram.getNumberOfEntries(); i++) {
                        System.out.println(foundCourseFromProgram.getEntry(i));
                    }
                } else {
                    System.out.printf("\nThe Course ID: (" + courseID + ") appeared in no Programme.\n");
                }

                System.out.print("\nAre you sure you want to remove this course? (Y/N): ");
                choice = chSc.next().charAt(0);

                if (choice == 'Y' || choice == 'y') {
                    boolean isRemoved = true;

                    if (foundCourseFromProgram != null) {
                        for (int i = 0; i < foundCourseFromProgram.getNumberOfEntries(); i++) {
                            Programme p = foundCourseFromProgram.getEntry(i);
                            if (!p.removeCourse(courseID)) {
                                isRemoved = false;
                                System.out.println("Something went wrong.");
                                break;
                            }
                        }
                    }

                    if (!courseList.remove(foundCourse)) {
                        isRemoved = false;
                        System.out.println("Something went wrong.");
                    }

                    if (isRemoved) {
                        System.out.println("Course removed from all programme successfully.");
                    }
                }
                System.out.print("Do you want to remove next one? (N/n to quit): ");

            } else {
                System.out.println("Course not found.");
                System.out.print("Do you want to try again? (N/n to quit): ");
            }

            choice = chSc.next().charAt(0);
            System.out.println();

        } while (choice != 'N' && choice != 'n');
    }

    public static void removeCourseFromProgramme() {
        Scanner sc = new Scanner(System.in);
        Scanner chSc = new Scanner(System.in);
        char choice = 0;
        System.out.println("\nRemove Course From Programme");

        do {
            System.out.print("Enter programme ID: ");
            String programmeID = sc.nextLine();

            Programme foundProg = searchProgramme(programmeID);

            if (foundProg != null) {
                if (foundProg.getCourseList().isEmpty()) {
                    System.out.printf("\nNo course found under programme ID: %s.\n", programmeID);
                } else {
                    System.out.println("\nExisting Course List: ");
                    System.out.printf("%-25s %-25s %-20s %-10s %-10s\n", "Course Name", "Course ID", "Credit Hour", "Type", "Faculty");
                    System.out.println(foundProg.getCourseList());
                    System.out.print("Enter course ID to remove: ");
                    String courseID = sc.nextLine();

                    System.out.print("Are you sure you want to remove this course? (Y/N): ");
                    choice = chSc.next().charAt(0);

                    if (choice == 'Y' || choice == 'y') {
                        if (foundProg.removeCourse(courseID)) {
                            System.out.println("Course removed successfully.");
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else if (choice == 'N' || choice == 'n') {
                        System.out.println("Course remove cancel.");
                    }
                }
                System.out.print("Do you want to remove next one? (N/n to quit): ");

            } else {
                System.out.println("Programme not found.");
                System.out.print("Do you want to try again? (N/n to quit): ");
            }

            choice = chSc.next().charAt(0);

        } while (choice != 'N' && choice != 'n');
    }

    public static void viewProgramCourse() {
        Scanner sc = new Scanner(System.in);
        Scanner chSc = new Scanner(System.in);
        char choice = 0;
        System.out.println("\nView Programme Course");

        do {
            System.out.print("Enter programme ID: ");
            String programmeID = sc.nextLine();

            Programme foundProg = searchProgramme(programmeID);

            if (foundProg != null) {
                if (foundProg.getCourseList().isEmpty()) {
                    System.out.printf("\nNo course found under programme ID: %s.\n", programmeID);
                } else {
                    System.out.println("\nExisting Course List: ");
                    System.out.printf("%-25s %-25s %-20s %-10s %-10s\n", "Course Name", "Course ID", "Credit Hour", "Type", "Faculty");
                    System.out.println(foundProg.getCourseList());
                }
                System.out.print("Do you want to view next one? (N/n to quit): ");

            } else {
                System.out.println("Programme not found.");
                System.out.print("Do you want to try again? (N/n to quit): ");
            }

            choice = chSc.next().charAt(0);

        } while (choice != 'N' && choice != 'n');
    }

    public static void generateSummaryReport() {
        String input = "Summary Report";
        int width = 166;
        int padding = (width - input.length()) / 2;
        String centeredString = String.format("%" + (padding + input.length()) + "s", input);

        System.out.println("\n" + centeredString + "\n");
        String alignment = "%-15s %-35s %-10s %-20s %-25s %-20s %-10s %-10s\n";
        System.out.printf(alignment, "Programme ID", "Programme Name", "Duration", "Course ID", "Course Name", "Credit Hour", "Type", "Faculty");

        for (int i = 0; i < progList.getNumberOfEntries(); i++) {
            Programme p = progList.getEntry(i);
            boolean loopProg = true;
            for (int j = 0; j < p.getCourseList().getNumberOfEntries(); j++) {
                Course c = p.getCourseList().getEntry(j);
                if (loopProg) {
                    System.out.printf(alignment, p.getProgrammeID(), p.getProgrammeName(), p.getDuration(), c.getCourseID(), c.getCourseName(), c.getCreditHour(), c.getType(), c.getFaculty());
                    loopProg = false;
                } else {
                    System.out.printf(alignment, "", "", "", c.getCourseID(), c.getCourseName(), c.getCreditHour(), c.getType(), c.getFaculty());
                }
            }
        }
    }
}
