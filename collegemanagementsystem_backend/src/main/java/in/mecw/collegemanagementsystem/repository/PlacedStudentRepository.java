package in.mecw.collegemanagementsystem.repository;

import in.mecw.collegemanagementsystem.entity.PlacedStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlacedStudentRepository extends JpaRepository<PlacedStudent,Integer> {
    List<PlacedStudent> findAllByOrderByAcademicYearDescStudentNameAsc();
}
