package in.mecw.collegemanagementsystem.service;

import in.mecw.collegemanagementsystem.entity.Course;
import in.mecw.collegemanagementsystem.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class
CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public Course addCourse(Course course){
        return courseRepository.save(course);
    }

    public List<Course> getCourse(){
        return courseRepository.findAll();
    }
    //get by id
    public Optional<Course> getCourseById(int id){
        return courseRepository.findById(id);
    }
    //update by id
    public Optional<Course> updateCourseById(int id, Course course){
        Optional<Course> result = courseRepository.findById(id);

        if(result.isPresent()){
            Course existingCourse = result.get();
            existingCourse.setCourseName(course.getCourseName());
            courseRepository.save(existingCourse);
            return Optional.of(existingCourse);
        }
        else {
            return Optional.empty();
        }
    }
    //delete by id
    public Optional<Course> deleteCourseById(int id){
        Optional<Course> result = courseRepository.findById(id);

        if(result.isPresent()){
            Course courseExist = result.get();
            courseRepository.deleteById(id);
            return Optional.of(courseExist);
        }
        else{
            return Optional.empty();
        }
    }
}
