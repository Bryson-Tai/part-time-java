/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedArrayList;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author joyii
 */
public class Programme implements Serializable, Comparable<Programme> {
    private String programmeID;
    private String programmeName;
    public int duration;
    private SortedArrayList<Course> courseList = new SortedArrayList<>();


    public Programme() {
    }

    public Programme(String programmeID, String programmeName, int duration, SortedArrayList<Course> courseList) {
        this.programmeID = programmeID;
        this.programmeName = programmeName;
        this.duration = duration;
        this.courseList = courseList;
    }

    public String getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(String ProgrammeID) {
        this.programmeID = ProgrammeID;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String ProgrammeName) {
        this.programmeName = ProgrammeName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public SortedArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(SortedArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Programme other = (Programme) obj;
        if (!Objects.equals(this.programmeID, other.programmeID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-40s %10d", programmeID, programmeName, duration);
    }

    @Override
    public int compareTo(Programme o) {
        return Integer.compare(this.duration, o.duration);
    }

    public Programme getEntry(int j) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean removeCourse(String courseID) {
        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            Course c = new Course();
            c = courseList.getEntry(i);
            if (c.getCourseID().equals(courseID)) {
                courseList.remove(c);
                return true;
            }
        }
        return false;
    }

}
