package in.mecw.collegemanagementsystem.controller;

import in.mecw.collegemanagementsystem.dto.MaterialInfo;
import in.mecw.collegemanagementsystem.service.StudyMaterialService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/materials")
public class StudyMaterialController {
    private final StudyMaterialService studyMaterialService;

    public StudyMaterialController(StudyMaterialService studyMaterialService){
        this.studyMaterialService = studyMaterialService;
    }

    @GetMapping
    public List<MaterialInfo> getAll(){
        return studyMaterialService.getAll();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestParam("subject") String subject,
                                    @RequestParam("title") String title,
                                    @RequestParam("file") MultipartFile file,
                                    Authentication authentication){
        try {
            studyMaterialService.upload(subject, title, file, authentication.getName());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Could not read the file");
        }
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){
        return studyMaterialService.getFile(id)
                .map(m -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + m.getFileName() + "\"")
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(m.getData()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        return studyMaterialService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
