package in.mecw.collegemanagementsystem.controller;

import in.mecw.collegemanagementsystem.entity.Faculty;
import in.mecw.collegemanagementsystem.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService){
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty){
        return facultyService.addFaculty(faculty);
    }

    @GetMapping
    public List<Faculty> getAllFaculty(){
        return facultyService.getAllFaculty();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable int id){
        Optional<Faculty> result = facultyService.getFacultyById(id);
        if(result.isPresent()){
            Faculty facultyexist = result.get();
            return ResponseEntity.ok(facultyexist);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> deleteFacultyById(@PathVariable int id){
        Optional<Faculty> result = facultyService.getFacultyById(id);

        if(result.isPresent()){
            Faculty facultyexists = result.get();
            facultyService.deleteFacultyById(id);
            return ResponseEntity.ok(facultyexists);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFacultyById(@PathVariable int id,@RequestBody Faculty faculty ){
        Optional<Faculty> result = facultyService.updateFacultyById(id,faculty);

        if(result.isPresent()){
            Faculty currentFaculty = result.get();
            return ResponseEntity.ok(currentFaculty);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
