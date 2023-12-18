
package entity;

import adt.ListInterface;
import adt.SortedArrayList;

import java.util.Objects;

/**
 * @author thongjiayi
 */
public class Course implements Comparable<Course> {

    private String courseName;
    private String courseID;
    private double creditHour;
    private String type;
    private String faculty;

    public Course() {
    }

    public Course(String courseName, String courseID, double creditHour, String type, String faculty) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.creditHour = creditHour;
        this.type = type;
        this.faculty = faculty;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public double getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(double creditHour) {
        this.creditHour = creditHour;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }


    @Override
    public String toString() {
        return String.format("%-25s %-25s %-20.1f %-10s %-10s", courseName, courseID, creditHour, type, faculty);
    }

    @Override
    public int compareTo(Course other) {
        // Implement your comparison logic here
        // Return a negative value if this object is less than the other,
        // zero if they are equal, and a positive value if this object is greater
        return this.courseID.compareTo(other.courseID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course otherCourse = (Course) obj;
        return Objects.equals(courseID, otherCourse.getCourseID());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.courseName);
        hash = 59 * hash + Objects.hashCode(this.courseID);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.creditHour) ^ (Double.doubleToLongBits(this.creditHour) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.type);
        return hash;
    }

//    public void addProgramme(String programme) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public int getNumberOfProgrammes() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public Programme removeProgramme(Programme programmeChoice) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public Programme getProgramme(int i) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }


}
