package in.mecw.collegemanagementsystem.service;

import in.mecw.collegemanagementsystem.entity.Announcement;
import in.mecw.collegemanagementsystem.entity.Student;
import in.mecw.collegemanagementsystem.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public AnnouncementService(AnnouncementRepository announcementRepository){
        this.announcementRepository = announcementRepository;
    }

    public List<Announcement> getAllAnnouncements(){
        return announcementRepository.findAllByOrderByCreatedAtDesc();
    }

    public Announcement addAnnouncement(Announcement announcement, String username){
        announcement.setCreatedAt(LocalDateTime.now());
        announcement.setPostedBy(username);
        return announcementRepository.save(announcement);
    }

    public Optional<Announcement> updateAnnouncement(Integer id, Announcement newData){
        Optional<Announcement> result = announcementRepository.findById(id);
        if(result.isPresent()){
            Announcement announcement = result.get();
            announcement.setTitle(newData.getTitle());
            announcement.setLink(newData.getLink());
            announcement.setCategory(newData.getCategory());
            announcement.setDescription(newData.getDescription());
            return Optional.of(announcementRepository.save(announcement));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Announcement> deleteAnnouncement(Integer id){
        Optional<Announcement> result = announcementRepository.findById(id);
        if(result.isPresent()){
            Announcement announcementExist = result.get();
            announcementRepository.deleteById(id);
            return Optional.of(announcementExist);
        }
        else{
            return Optional.empty();
        }
    }
}
