

package entity;

import adt.ListInterface;
import adt.SortedArrayList;
import java.util.ArrayList;
import java.util.List;

public class CourseInitializer {

    private static final List<Programme> allPrograms = new ArrayList<>();

    public static List<Programme> getAllPrograms() {
        return new ArrayList<>(allPrograms);
    }

//    public ListInterface<Course> initializeCourses() {
//        ListInterface<Course> courseList = new SortedArrayList<>();
//        courseList.add(new Course("Discrete Mathematics", "BAMS1623", 3, "main", "FAFB"));
//        courseList.add(new Course("Database Management", "BACS1053", 3, "main", "FAFB"));
//        courseList.add(new Course("IT Fundamental", "BAIT1173", 3, "main", "FOCS"));
//        courseList.add(new Course("Academic English", "BJEL1023", 3, "main", "FOBE"));
//        courseList.add(new Course("Co-curricular", "BCOQ", 2, "main", "FOCS"));
//        courseList.add(new Course("Software Engineering", "BACS2163", 3, "main", "FOCS"));
//
//        // Add associated programs to courses
//        addProgramsToCourse(courseList, "BAMS1623", "Software Development", "RSD");
//        addProgramsToCourse(courseList, "BACS1053", "Enterprise Information Systems", "REI");
//        addProgramsToCourse(courseList, "BAIT1173", "Software Development", "RSD");
//        addProgramsToCourse(courseList, "BJEL1023", "Enterprise Information Systems", "REI");
//        addProgramsToCourse(courseList, "BAIT1173", "Data Science", "RDS");
//        addProgramsToCourse(courseList, "BJEL1023", "Interactive Software Technology", "RST");
//
//        return courseList;
//    }

//    private void addProgramsToCourse(ListInterface<Course> courseList, String courseID, String programName, String programCode) {
//        for (int i = 0; i <= courseList.getNumberOfEntries() - 1; i++) {
//            Course currentCourse = courseList.getEntry(i);
//            if (currentCourse.getCourseID().equals(courseID)) {
//                // Add associated program
//                Programme program = new Programme(programName);
//                String programme = null;
//                currentCourse.addProgramme(programme);
//                allPrograms.add(program); // Add program to the global program list
//                break;
//            }
//        }
//    }
}
