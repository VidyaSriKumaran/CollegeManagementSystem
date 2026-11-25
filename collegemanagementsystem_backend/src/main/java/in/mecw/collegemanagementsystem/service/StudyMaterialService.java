package in.mecw.collegemanagementsystem.service;

import in.mecw.collegemanagementsystem.dto.MaterialInfo;
import in.mecw.collegemanagementsystem.entity.StudyMaterial;
import in.mecw.collegemanagementsystem.repository.StudyMaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudyMaterialService {
    private static final long MAX_SIZE = 5 * 1024 * 1024;
    private static final List<String> ALLOWED = List.of("pdf","doc","docx","ppt","pptx");
    private final StudyMaterialRepository studyMaterialRepository;

    public StudyMaterialService(StudyMaterialRepository studyMaterialRepository){
        this.studyMaterialRepository = studyMaterialRepository;
    }

    public List<MaterialInfo> getAll(){
        return studyMaterialRepository.findAllByOrderByCreatedAtDesc();
    }

    public StudyMaterial upload(String subject, String title, MultipartFile file, String username) throws IOException {
        String original = file.getOriginalFilename() == null ? "file" : file.getOriginalFilename();
        String name = original.replaceAll("[^a-zA-Z0-9._ -]", "_");
        String ext = name.contains(".") ? name.substring(name.lastIndexOf('.') + 1).toLowerCase() : "";
        if(file.isEmpty() || file.getSize() > MAX_SIZE || !ALLOWED.contains(ext)){
            throw new IllegalArgumentException("Only PDF, Word or PowerPoint files up to 5 MB are allowed");
        }
        StudyMaterial material = new StudyMaterial();
        material.setSubject(subject.trim());
        material.setTitle(title.trim());
        material.setFileName(name);
        material.setContentType("application/octet-stream");
        material.setFileSize(file.getSize());
        material.setUploadedBy(username);
        material.setCreatedAt(LocalDateTime.now());
        material.setData(file.getBytes());
        return studyMaterialRepository.save(material);
    }

    public Optional<StudyMaterial> getFile(Integer id){
        return studyMaterialRepository.findById(id);
    }

    public boolean delete(Integer id){
        if(studyMaterialRepository.existsById(id)){
            studyMaterialRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
