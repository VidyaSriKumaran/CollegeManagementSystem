package in.mecw.collegemanagementsystem.controller;

import in.mecw.collegemanagementsystem.entity.Announcement;
import in.mecw.collegemanagementsystem.entity.Student;
import in.mecw.collegemanagementsystem.service.AnnouncementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService){
        this.announcementService = announcementService;
    }
    @PostMapping
    public Announcement addAnnouncement(@RequestBody Announcement announcement, Authentication authentication){
        return announcementService.addAnnouncement(announcement,authentication.getName());
    }

    @GetMapping
    public List<Announcement> getAllStudents(){
        return announcementService.getAllAnnouncements();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Announcement> updateAnnouncement(@PathVariable int id , @RequestBody Announcement announcement){
        Optional<Announcement> result = announcementService.updateAnnouncement(id,announcement);
        if(result.isPresent()){
            Announcement existingAnnouncement = result.get();
            return ResponseEntity.ok(existingAnnouncement);
        }
        else{
            //here the build() , ok(), notFound() methods are built-in method that is given by the ResponseEntity
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Announcement> deleteStudent(@PathVariable int id){
        Optional<Announcement> result = announcementService.deleteAnnouncement(id);
        if(result.isPresent()){
            Announcement deleteAnnouncement = result.get();
            System.out.println("Deleted Successfully 🎉🎉🎉");
            return ResponseEntity.ok(deleteAnnouncement);
        }
        else {
            System.out.println("Data not found ☹️☹️☹️");
            return ResponseEntity.notFound().build();
        }
    }

}
