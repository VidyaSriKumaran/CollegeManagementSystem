package in.mecw.collegemanagementsystem.service;

import in.mecw.collegemanagementsystem.Exception.CourseNotFoundException;
import in.mecw.collegemanagementsystem.entity.Course;
import in.mecw.collegemanagementsystem.entity.Faculty;
import in.mecw.collegemanagementsystem.repository.CourseRepository;
import in.mecw.collegemanagementsystem.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final CourseRepository courseRepository;

    public FacultyService(FacultyRepository facultyRepository,CourseRepository courseRepository){
        this.facultyRepository = facultyRepository;
        this.courseRepository = courseRepository;
    }

    //add faculty
    public Faculty addFaculty(Faculty faculty){
        return facultyRepository.save(faculty);
    }

    //get all faculty
    public List<Faculty> getAllFaculty(){
        return facultyRepository.findAll();
    }

    //get faculty by id
    public Optional<Faculty> getFacultyById(int id){
        Optional<Faculty> result = facultyRepository.findById(id);

        if(result.isPresent()){
            Faculty facultyexist = result.get();
            return Optional.of(facultyexist);
        }
        else {
            return Optional.empty();
        }
    }

    //delete by id
    public Optional<Faculty> deleteFacultyById(int id){
        Optional<Faculty> result = facultyRepository.findById(id);

        if(result.isPresent()){
            Faculty facultyexists = result.get();
            facultyRepository.deleteById(id);
            return Optional.of(facultyexists);
        }
        else{
            return Optional.empty();
        }
    }

    //update faculty by id
    public Optional<Faculty> updateFacultyById(int id, Faculty faculty){
        Optional<Faculty> result = facultyRepository.findById(id);

        if(result.isPresent()){
            Faculty currentFaculty = result.get();
            int courseID = faculty.getCourse().getCourseId();
            Optional<Course> course = courseRepository.findById(courseID);

            if(course.isPresent()){
                currentFaculty.setCourse(course.get());
            }
            else{
                throw new CourseNotFoundException("Course Not Found");
            }
            currentFaculty.setFacultyName(faculty.getFacultyName());
            currentFaculty.setFacultyPosition(faculty.getFacultyPosition());
            facultyRepository.save(currentFaculty);
            return Optional.of(currentFaculty);
        }
        return Optional.empty();
    }

    }

