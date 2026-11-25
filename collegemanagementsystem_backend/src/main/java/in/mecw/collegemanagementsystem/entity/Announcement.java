package in.mecw.collegemanagementsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer announcementId;
    private String title;
    @Column(length = 2000)
    private String description;
    @Enumerated(EnumType.STRING)
    private AnnouncementCategory category;
    private String link;
    private LocalDateTime createdAt;
    private String postedBy;

    public Announcement(){}

    public String getPostedBy() {
        return postedBy;
    }
    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public AnnouncementCategory getCategory() {
        return category;
    }
    public void setCategory(AnnouncementCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAnnouncementId() {
        return announcementId;
    }
    public void setAnnouncementId(Integer announcementId) {
        this.announcementId = announcementId;
    }
}
