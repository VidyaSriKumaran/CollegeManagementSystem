package in.mecw.collegemanagementsystem.controller;

import in.mecw.collegemanagementsystem.entity.Complaint;
import in.mecw.collegemanagementsystem.service.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {
    private final ComplaintService complaintService;
    public ComplaintController(ComplaintService complaintService){ this.complaintService = complaintService; }

    @GetMapping
    public List<Complaint> getAll(Authentication authentication){
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        return complaintService.getAll(isAdmin);
    }
    @GetMapping("/mine")
    public List<Complaint> getMine(Authentication authentication){
        return complaintService.getMine(authentication.getName());
    }

    @PostMapping
    public Complaint add(@RequestBody Complaint complaint, Authentication authentication){
        return complaintService.add(complaint, authentication.getName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Complaint> update(@PathVariable Integer id, @RequestBody Complaint complaint){
        return complaintService.update(id, complaint).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Complaint> delete(@PathVariable Integer id){
        return complaintService.delete(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
