package in.mecw.collegemanagementsystem.service;

import in.mecw.collegemanagementsystem.entity.Course;
import in.mecw.collegemanagementsystem.entity.Student;
import in.mecw.collegemanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;
import in.mecw.collegemanagementsystem.Exception.CourseNotFoundException;
import in.mecw.collegemanagementsystem.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    //post
    public Student addStudent(Student student){
        int courseID = student.getCourse().getCourseId();
        Optional<Course> course = courseRepository.findById(courseID);
        if(course.isPresent()){
           return studentRepository.save(student);
        }
        else{
            throw new CourseNotFoundException("Course not found☹️☹️☹️");
        }
    }

    //get
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id){
        return studentRepository.findById(id);
    }

    public Optional<Student> updateStudent(int id , Student student){
         Optional<Student> result = studentRepository.findById(id);

        if(result.isPresent()){
            Student existingStudent = result.get();
            int courseID = student.getCourse().getCourseId();
            Optional<Course> course = courseRepository.findById(courseID);
            if(course.isPresent()){
                existingStudent.setCourse(course.get());
            }
            else{
                throw new CourseNotFoundException("Course Not Found");
            }
            existingStudent.setStudentAge(student.getStudentAge());
            existingStudent.setStudentName(student.getStudentName());
            existingStudent.setStudentYear(student.getStudentYear());
            studentRepository.save(existingStudent);
            return Optional.of(existingStudent);
        }
        return Optional.empty();
    }

    public Optional<Student> deleteStudent(int id){
        Optional<Student> result = studentRepository.findById(id);

        if(result.isPresent()){
            Student studentExist = result.get();
            studentRepository.deleteById(id);
            return Optional.of(studentExist);
        }
        else{
            return Optional.empty();
        }
    }
}
