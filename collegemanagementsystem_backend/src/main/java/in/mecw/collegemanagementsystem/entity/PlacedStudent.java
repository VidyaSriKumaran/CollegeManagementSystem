package in.mecw.collegemanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


    @Entity
    public class PlacedStudent {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer placedId;
        private String studentName;
        private String courseName;
        private String companyName;
        private String academicYear;

        public Integer getPlacedId() {
            return placedId;
        }
        public void setPlacedId(Integer placedId) {
            this.placedId = placedId;
        }

        public String getStudentName() {
            return studentName;
        }
        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getCourseName() {
            return courseName;
        }
        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCompanyName() {
            return companyName;
        }
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getAcademicYear() {
            return academicYear;
        }
        public void setAcademicYear(String academicYear) {
            this.academicYear = academicYear;
        }
    }

