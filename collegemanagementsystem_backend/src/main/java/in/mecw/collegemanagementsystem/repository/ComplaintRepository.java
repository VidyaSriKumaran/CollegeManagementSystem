package in.mecw.collegemanagementsystem.repository;

import in.mecw.collegemanagementsystem.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {
    List<Complaint> findAllByOrderByCreatedAtDesc();
    List<Complaint> findAllBySubmittedByOrderByCreatedAtDesc(String submittedBy);
}
