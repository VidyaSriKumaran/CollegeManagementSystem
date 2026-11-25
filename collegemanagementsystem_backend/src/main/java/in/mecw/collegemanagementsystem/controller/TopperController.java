package in.mecw.collegemanagementsystem.controller;

import in.mecw.collegemanagementsystem.entity.Topper;
import in.mecw.collegemanagementsystem.service.TopperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toppers")
public class TopperController {
    private final TopperService topperService;
    public TopperController(TopperService topperService){ this.topperService = topperService; }

    @GetMapping
    public List<Topper> getAllToppers(){ return topperService.getAllToppers(); }

    @PostMapping
    public Topper addTopper(@RequestBody Topper topper){ return topperService.addTopper(topper); }

    @PutMapping("/{id}")
    public ResponseEntity<Topper> updateTopper(@PathVariable Integer id, @RequestBody Topper topper){
        return topperService.updateTopper(id, topper).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Topper> deleteTopper(@PathVariable Integer id){
        return topperService.deleteTopper(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
