package in.mecw.collegemanagementsystem.repository;

import in.mecw.collegemanagementsystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course , Integer> {
}
