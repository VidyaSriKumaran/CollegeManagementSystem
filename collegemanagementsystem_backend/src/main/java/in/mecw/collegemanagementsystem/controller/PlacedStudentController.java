package in.mecw.collegemanagementsystem.controller;

import in.mecw.collegemanagementsystem.entity.PlacedStudent;
import in.mecw.collegemanagementsystem.service.PlacedStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/placements")
public class PlacedStudentController {
    private final PlacedStudentService placedStudentService;
    public PlacedStudentController(PlacedStudentService placedStudentService){ this.placedStudentService = placedStudentService; }

    @GetMapping
    public List<PlacedStudent> getAllPlaced(){ return placedStudentService.getAllPlaced(); }

    @PostMapping
    public PlacedStudent addPlaced(@RequestBody PlacedStudent placed){ return placedStudentService.addPlaced(placed); }

    @PutMapping("/{id}")
    public ResponseEntity<PlacedStudent> updatePlaced(@PathVariable Integer id, @RequestBody PlacedStudent placed){
        return placedStudentService.updatePlaced(id, placed).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlacedStudent> deletePlaced(@PathVariable Integer id){
        return placedStudentService.deletePlaced(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
