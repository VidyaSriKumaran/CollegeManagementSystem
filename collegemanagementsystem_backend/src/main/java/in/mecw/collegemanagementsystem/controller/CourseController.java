package in.mecw.collegemanagementsystem.controller;

import in.mecw.collegemanagementsystem.entity.Course;
import in.mecw.collegemanagementsystem.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @GetMapping
    public List<Course> getCourse(){
        return courseService.getCourse();
    }
    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable int id){
        return courseService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int id , @RequestBody Course course){
        Optional<Course> result = courseService.updateCourseById(id,course);

        if(result.isPresent()){
            Course existingCourse = result.get();
            return ResponseEntity.ok(existingCourse);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteById(@PathVariable int id){
        Optional<Course> result = courseService.getCourseById(id);

        if(result.isPresent()){
            Course courseExist = result.get();
            courseService.deleteCourseById(id);
            return ResponseEntity.ok(courseExist);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
