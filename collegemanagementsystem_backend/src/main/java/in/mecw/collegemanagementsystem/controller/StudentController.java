package in.mecw.collegemanagementsystem.controller;

import in.mecw.collegemanagementsystem.entity.Student;
import in.mecw.collegemanagementsystem.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//it tells spring that this is a controller
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id){
        Optional<Student> result = studentService.getStudentById(id);
        if(result.isPresent()){
            Student student = result.get();
            return ResponseEntity.ok(student);
        }
        else{
            //here the build() , ok(), notFound() methods are built-in method that is given by the ResponseEntity
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id , @RequestBody Student student){

        Optional<Student> result = studentService.updateStudent(id,student);
        if(result.isPresent()){
            Student existingStudent = result.get();
            return ResponseEntity.ok(existingStudent);
        }
        else{
            //here the build() , ok(), notFound() methods are built-in method that is given by the ResponseEntity
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id){
         Optional<Student> result = studentService.deleteStudent(id);
         if(result.isPresent()){
             Student studentDelete = result.get();
             System.out.println("Deleted Successfully 🎉🎉🎉");
             return ResponseEntity.ok(studentDelete);
         }
         else {
             System.out.println("Data not found ☹️☹️☹️");
             return ResponseEntity.notFound().build();
         }
    }
    }
