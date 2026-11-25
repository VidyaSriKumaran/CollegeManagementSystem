package in.mecw.collegemanagementsystem.repository;

import in.mecw.collegemanagementsystem.dto.MaterialInfo;
import in.mecw.collegemanagementsystem.entity.StudyMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudyMaterialRepository extends JpaRepository<StudyMaterial,Integer> {
        List<MaterialInfo> findAllByOrderByCreatedAtDesc();
}

