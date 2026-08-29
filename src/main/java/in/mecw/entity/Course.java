package in.mecw.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Course {

    @Id
    private int courseId;

    @Column
    private String courseName;

    @Column
    private int deptId;

    public Course() {
    }

    public Course(int courseId, String courseName, int deptId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.deptId = deptId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
}