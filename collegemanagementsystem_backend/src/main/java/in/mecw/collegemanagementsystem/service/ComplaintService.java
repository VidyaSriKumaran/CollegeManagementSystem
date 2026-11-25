package in.mecw.collegemanagementsystem.service;

import in.mecw.collegemanagementsystem.entity.Complaint;
import in.mecw.collegemanagementsystem.entity.ComplaintStatus;
import in.mecw.collegemanagementsystem.repository.ComplaintRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ComplaintService {
    private final ComplaintRepository complaintRepository;
    public ComplaintService(ComplaintRepository complaintRepository){ this.complaintRepository = complaintRepository; }

    public List<Complaint> getAll(boolean isAdmin){
        List<Complaint> all = complaintRepository.findAllByOrderByCreatedAtDesc();
        if(isAdmin){ return all; }
        List<Complaint> hidden = new ArrayList<>();
        for(Complaint c : all){
            Complaint copy = new Complaint();
            copy.setComplaintId(c.getComplaintId());
            copy.setSubject(c.getSubject());
            copy.setDescription(c.getDescription());
            copy.setStatus(c.getStatus());
            copy.setReply(c.getReply());
            copy.setCreatedAt(c.getCreatedAt());
            copy.setSubmittedBy("Anonymous");
            hidden.add(copy);
        }
        return hidden;
    }
    public List<Complaint> getMine(String username){
        return complaintRepository.findAllBySubmittedByOrderByCreatedAtDesc(username);
    }
    public Complaint add(Complaint complaint, String username){
        complaint.setComplaintId(null);
        complaint.setCreatedAt(LocalDateTime.now());
        complaint.setSubmittedBy(username);
        complaint.setStatus(ComplaintStatus.OPEN);
        complaint.setReply(null);
        return complaintRepository.save(complaint);
    }
    public Optional<Complaint> update(Integer id, Complaint newData){
        Optional<Complaint> result = complaintRepository.findById(id);
        if(result.isPresent()){
            Complaint complaint = result.get();
            complaint.setStatus(newData.getStatus());
            complaint.setReply(newData.getReply());
            return Optional.of(complaintRepository.save(complaint));
        }
        return Optional.empty();
    }
    public Optional<Complaint> delete(Integer id){
        Optional<Complaint> result = complaintRepository.findById(id);
        result.ifPresent(c -> complaintRepository.deleteById(id));
        return result;
    }
}
