package in.mecw.collegemanagementsystem.service;

import in.mecw.collegemanagementsystem.entity.Topper;
import in.mecw.collegemanagementsystem.repository.TopperRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopperService {
    private final TopperRepository topperRepository;
    public TopperService(TopperRepository topperRepository){ this.topperRepository = topperRepository; }

    public List<Topper> getAllToppers(){
        return topperRepository.findAllByOrderByAcademicYearDescTopperRankAsc();
    }
    public Topper addTopper(Topper topper){
        topper.setTopperId(null);
        return topperRepository.save(topper);
    }
    public Optional<Topper> updateTopper(Integer id, Topper newData){
        Optional<Topper> result = topperRepository.findById(id);
        if(result.isPresent()){
            Topper topper = result.get();
            topper.setStudentName(newData.getStudentName());
            topper.setCourseName(newData.getCourseName());
            topper.setScore(newData.getScore());
            topper.setAcademicYear(newData.getAcademicYear());
            topper.setTopperRank(newData.getTopperRank());
            return Optional.of(topperRepository.save(topper));
        }
        return Optional.empty();
    }
    public Optional<Topper> deleteTopper(Integer id){
        Optional<Topper> result = topperRepository.findById(id);
        result.ifPresent(t -> topperRepository.deleteById(id));
        return result;
    }
}
