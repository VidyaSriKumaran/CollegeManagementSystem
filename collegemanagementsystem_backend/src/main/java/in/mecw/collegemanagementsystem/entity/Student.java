package in.mecw.collegemanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Student {
    @Id
    private int studentId;
    private String studentName;
    private int studentAge;
    private Integer studentYear;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course ;

    public int getStudentId(){
        return studentId;
    }
    public void setStudentId(int studentId){
        this.studentId = studentId;
    }

    public String getStudentName(){
        return studentName;
    }
    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

    public int getStudentAge(){
        return studentAge;
    }
    public void setStudentAge(int studentAge){
        this.studentAge = studentAge;
    }

    public Integer getStudentYear() {
        return studentYear;
    }public void setStudentYear(Integer studentYear) {
        this.studentYear = studentYear;
    }

    public Course getCourse(){
        return course;
    }
    public void setCourse(Course course){
        this.course = course;
    }

    //JPA/Hibernate needs no arguments constructor because they use it to instantiate entity objects.
    public Student(){

    }

    public Student(int studentId,String studentName, int studentAge,int studentYear, Course course){
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentYear = studentYear;
        this.course = course;
    }
}
