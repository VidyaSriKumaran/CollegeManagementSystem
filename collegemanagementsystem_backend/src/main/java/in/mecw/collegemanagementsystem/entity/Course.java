package in.mecw.collegemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    private int courseId;
    private String courseName;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents(){
        return students;
    }
    public void setStudents(List<Student> students){
        this.students = students;
    }

    public int getCourseId(){
        return courseId;
    }
    public void setCourseId(int courseId){
        this.courseId = courseId;
    }

    public String getCourseName(){
        return courseName;
    }
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public Course(){

    }
    public Course(int courseId, String courseName){
        this.courseId = courseId;
        this.courseName = courseName;
    }
}
