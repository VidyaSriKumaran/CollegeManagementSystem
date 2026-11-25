package in.mecw.collegemanagementsystem.repository;

import in.mecw.collegemanagementsystem.entity.Topper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopperRepository extends JpaRepository<Topper,Integer> {
    List<Topper> findAllByOrderByAcademicYearDescTopperRankAsc();
}
