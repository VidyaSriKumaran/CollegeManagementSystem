package in.mecw.collegemanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class Topper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer topperId;
    private String studentName;
    private String courseName;
    private Double score;
    private String academicYear;
    private Integer topperRank;

    public Topper(){}

    public Integer getTopperId() {
        return topperId;
    }
    public void setTopperId(Integer topperId) {
        this.topperId = topperId;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }

    public String getAcademicYear() {
        return academicYear;
    }
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Integer getTopperRank() {
        return topperRank;
    }
    public void setTopperRank(Integer topperRank) {
        this.topperRank = topperRank;
    }
}
