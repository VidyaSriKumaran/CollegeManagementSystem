package in.mecw.collegemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import in.mecw.collegemanagementsystem.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
