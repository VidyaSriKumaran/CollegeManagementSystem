package in.mecw.collegemanagementsystem.service;

import in.mecw.collegemanagementsystem.entity.PlacedStudent;
import in.mecw.collegemanagementsystem.repository.PlacedStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlacedStudentService {
    private final PlacedStudentRepository placedStudentRepository;
    public PlacedStudentService(PlacedStudentRepository placedStudentRepository){ this.placedStudentRepository = placedStudentRepository; }

    public List<PlacedStudent> getAllPlaced(){
        return placedStudentRepository.findAllByOrderByAcademicYearDescStudentNameAsc();
    }
    public PlacedStudent addPlaced(PlacedStudent placed){
        placed.setPlacedId(null);
        return placedStudentRepository.save(placed);
    }
    public Optional<PlacedStudent> updatePlaced(Integer id, PlacedStudent newData){
        Optional<PlacedStudent> result = placedStudentRepository.findById(id);
        if(result.isPresent()){
            PlacedStudent placed = result.get();
            placed.setStudentName(newData.getStudentName());
            placed.setCourseName(newData.getCourseName());
            placed.setCompanyName(newData.getCompanyName());
            placed.setAcademicYear(newData.getAcademicYear());
            return Optional.of(placedStudentRepository.save(placed));
        }
        return Optional.empty();
    }
    public Optional<PlacedStudent> deletePlaced(Integer id){
        Optional<PlacedStudent> result = placedStudentRepository.findById(id);
        result.ifPresent(p -> placedStudentRepository.deleteById(id));
        return result;
    }
}
