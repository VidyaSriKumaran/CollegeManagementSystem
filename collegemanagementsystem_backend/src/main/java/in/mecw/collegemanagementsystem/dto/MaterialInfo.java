package in.mecw.collegemanagementsystem.dto;

import java.time.LocalDateTime;

public interface MaterialInfo {
    Integer getMaterialId();
    String getSubject();
    String getTitle();
    String getFileName();
    Long getFileSize();
    String getUploadedBy();
    LocalDateTime getCreatedAt();
}
