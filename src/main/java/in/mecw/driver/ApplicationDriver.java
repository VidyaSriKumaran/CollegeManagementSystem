package in.mecw.driver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import in.mecw.entity.Department;
import in.mecw.entity.Course;
import in.mecw.entity.Student;
import in.mecw.entity.Address;
import in.mecw.entity.Teacher;

public class ApplicationDriver {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("dev");

        EntityManager em = emf.createEntityManager();


// ADD DEPARTMENT
//
//        Department department1 = new Department(1, "BCA");
//        Department department2 = new Department(2, "Computer Science");
//        Department department3 = new Department(3, "B.Com");
//        Department department4 = new Department(4, "MCA");
//        Department department5 = new Department(5, "MBA");
//
//        em.getTransaction().begin();
//
//        em.persist(department1);
//        em.persist(department2);
//        em.persist(department3);
//        em.persist(department4);
//        em.persist(department5);
//
//        em.getTransaction().commit();
//
//        System.out.println("5 Departments added successfully");

//        //ADD COURSE
//        Course course1 = new Course(1, "Java", 1);
//        Course course2 = new Course(2, "Python", 1);
//        Course course3 = new Course(3, "DBMS", 1);
//        Course course4 = new Course(4, "Web Technology", 2);
//        Course course5 = new Course(5, "Data Structures", 2);
//        Course course6 = new Course(6, "Operating System", 2);
//        Course course7 = new Course(7, "Computer Networks", 2);
//        Course course8 = new Course(8, "Software Engineering", 3);
//        Course course9 = new Course(9, "Cloud Computing", 3);
//        Course course10 = new Course(10, "Artificial Intelligence", 3);
//
//        em.getTransaction().begin();
//
//        em.persist(course1);
//        em.persist(course2);
//        em.persist(course3);
//        em.persist(course4);
//        em.persist(course5);
//        em.persist(course6);
//        em.persist(course7);
//        em.persist(course8);
//        em.persist(course9);
//        em.persist(course10);
//
//        em.getTransaction().commit();

//        System.out.println("10 Courses added successfully");

// ADD STUDENT

//        Student student1 = new Student(1, "Arun", 21, 1);
//        Student student2 = new Student(2, "Priya", 20, 2);
//        Student student3 = new Student(3, "Kumar", 22, 3);
//        Student student4 = new Student(4, "Divya", 21, 4);
//        Student student5 = new Student(5, "Rahul", 23, 5);
//        Student student6 = new Student(6, "Anitha", 20, 6);
//        Student student7 = new Student(7, "Vijay", 22, 7);
//        Student student8 = new Student(8, "Meena", 21, 8);
//        Student student9 = new Student(9, "Karthik", 24, 9);
//        Student student10 = new Student(10, "Lakshmi", 22, 10);
//
//        em.getTransaction().begin();
//
//        em.persist(student1);
//        em.persist(student2);
//        em.persist(student3);
//        em.persist(student4);
//        em.persist(student5);
//        em.persist(student6);
//        em.persist(student7);
//        em.persist(student8);
//        em.persist(student9);
//        em.persist(student10);
//
//        em.getTransaction().commit();
//
//        System.out.println("10 Students added successfully");


// ADD ADDRESS

//        Address address1 = new Address(1, "Salem", "Tamil Nadu", "636001", 1);
//        Address address2 = new Address(2, "Chennai", "Tamil Nadu", "600001", 2);
//        Address address3 = new Address(3, "Madurai", "Tamil Nadu", "625001", 3);
//        Address address4 = new Address(4, "Coimbatore", "Tamil Nadu", "641001", 4);
//        Address address5 = new Address(5, "Trichy", "Tamil Nadu", "620001", 5);
//        Address address6 = new Address(6, "Erode", "Tamil Nadu", "638001", 6);
//        Address address7 = new Address(7, "Namakkal", "Tamil Nadu", "637001", 7);
//        Address address8 = new Address(8, "Thanjavur", "Tamil Nadu", "613001", 8);
//        Address address9 = new Address(9, "Vellore", "Tamil Nadu", "632001", 9);
//        Address address10 = new Address(10, "Tirunelveli", "Tamil Nadu", "627001", 10);
//
//        em.getTransaction().begin();
//
//        em.persist(address1);
//        em.persist(address2);
//        em.persist(address3);
//        em.persist(address4);
//        em.persist(address5);
//        em.persist(address6);
//        em.persist(address7);
//        em.persist(address8);
//        em.persist(address9);
//        em.persist(address10);
//
//        em.getTransaction().commit();
//
//        System.out.println("10 Addresses added successfully");



// ADD TEACHER

//        Teacher teacher1 = new Teacher(1, "Ravi Sir");
//        Teacher teacher2 = new Teacher(2, "Priya Madam");
//        Teacher teacher3 = new Teacher(3, "Kumar Sir");
//        Teacher teacher4 = new Teacher(4, "Anitha Madam");
//        Teacher teacher5 = new Teacher(5, "Suresh Sir");
//        Teacher teacher6 = new Teacher(6, "Divya Madam");
//        Teacher teacher7 = new Teacher(7, "Arun Sir");
//        Teacher teacher8 = new Teacher(8, "Meena Madam");
//        Teacher teacher9 = new Teacher(9, "Rajesh Sir");
//        Teacher teacher10 = new Teacher(10, "Lakshmi Madam");
//
//        em.getTransaction().begin();
//
//        em.persist(teacher1);
//        em.persist(teacher2);
//        em.persist(teacher3);
//        em.persist(teacher4);
//        em.persist(teacher5);
//        em.persist(teacher6);
//        em.persist(teacher7);
//        em.persist(teacher8);
//        em.persist(teacher9);
//        em.persist(teacher10);
//
//        em.getTransaction().commit();
//
//        System.out.println("10 Teachers added successfully");

//        // UPDATE DEPARTMENT
//
//        Department department = em.find(Department.class, 1);
//
//        em.getTransaction().begin();
//
//        department.setDeptName("BCA Computer Applications");
//
//        em.getTransaction().commit();
//
//        System.out.println("Department updated successfully");
//

        // DELETE DEPARTMENT

        Department department = em.find(Department.class, 4);
//
//        em.getTransaction().begin();
//
//        em.remove(department);
//
//        em.getTransaction().commit();
//
//        System.out.println("Department deleted successfully");

// DISPLAY ALL DEPARTMENTS

//        java.util.List<Department> deptList =
//                em.createQuery("SELECT d FROM Department d", Department.class)
//                        .getResultList();
//
//        for (Department dept : deptList) {
//            System.out.println(
//                    dept.getDeptId() + " - " +
//                            dept.getDeptName()
//            );
//        }

        // FIND ALL COURSES IN A DEPARTMENT

//        java.util.List<Course> courseList =
//                em.createQuery(
//                        "SELECT c FROM Course c WHERE c.deptId = 1",
//                        Course.class
//                ).getResultList();
//
//        for (Course course : courseList) {
//            System.out.println(
//                    course.getCourseId() + " - " +
//                            course.getCourseName()
//            );
//        }

//        // UPDATE COURSE
//
//        Course course = em.find(Course.class, 1);
//
//        em.getTransaction().begin();
//
//        course.setCourseName("Advanced Java");
//
//        em.getTransaction().commit();
//
//        System.out.println("Course updated successfully");


// DELETE COURSE

//        Course course = em.find(Course.class, 10);
//
//        em.getTransaction().begin();
//
//        em.remove(course);
//
//        em.getTransaction().commit();
//
//        System.out.println("Course deleted successfully");


//        // FIND COURSE BY ID
//
//        Course course = em.find(Course.class, 1);
//
//        System.out.println("Course ID: " + course.getCourseId());
//        System.out.println("Course Name: " + course.getCourseName());
//        System.out.println("Department ID: " + course.getDeptId());


        // DISPLAY ALL COURSES

//        java.util.List<Course> courseList =
//                em.createQuery("SELECT c FROM Course c", Course.class)
//                        .getResultList();

//        for (Course course : courseList) {
//            System.out.println(
//                    course.getCourseId() + " - " +
//                            course.getCourseName() + " - Department ID: " +
//                            course.getDeptId()
//            );
//        }


// FIND COURSE'S DEPARTMENT
//
//        Course course = em.find(Course.class, 1);
//
//        Department dept =
//                em.find(Department.class, course.getDeptId());
//
//        System.out.println("Course: " + course.getCourseName());
//        System.out.println("Department: " + dept.getDeptName());


        // FIND ALL STUDENTS IN A COURSE

//        java.util.List<Student> studentList =
//                em.createQuery(
//                        "SELECT s FROM Student s WHERE s.courseId = 1",
//                        Student.class
//                ).getResultList();
//
//        for (Student student : studentList) {
//            System.out.println(
//                    student.getStudentId() + " - " +
//                            student.getStudentName()
//            );
//        }

        // UPDATE STUDENT

//        Student student = em.find(Student.class, 1);

//        em.getTransaction().begin();
//
//        student.setStudentName("Arun Kumar");
//
//        em.getTransaction().commit();
//
//        System.out.println("Student updated successfully");

        // DELETE STUDENT

//        Student student = em.find(Student.class, 10);
//
//        em.getTransaction().begin();
//
//        em.remove(student);

//        em.getTransaction().commit();
//
//        System.out.println("Student deleted successfully");


        // FIND STUDENT BY ID

//        Student student = em.find(Student.class, 1);
//
//        System.out.println("Student ID: " + student.getStudentId());
//        System.out.println("Student Name: " + student.getStudentName());
//        System.out.println("Age: " + student.getAge());
//        System.out.println("Course ID: " + student.getCourseId());


        // DISPLAY ALL STUDENTS
//
//        java.util.List<Student> studentList =
//                em.createQuery("SELECT s FROM Student s", Student.class)
//                        .getResultList();
//
//        for (Student student : studentList) {
//            System.out.println(
//                    student.getStudentId() + " - " +
//                            student.getStudentName() + " - " +
//                            student.getAge() + " - Course ID: " +
//                            student.getCourseId()
//            );
//        }


        // FIND STUDENT'S COURSE
//
//        Student student = em.find(Student.class, 1);
//
//        Course course =
//                em.find(Course.class, student.getCourseId());
//
//        System.out.println("Student: " + student.getStudentName());
//        System.out.println("Course: " + course.getCourseName());


        // FIND STUDENT'S DEPARTMENT

//        Student student = em.find(Student.class, 1);
//
//        Course course =
//                em.find(Course.class, student.getCourseId());
//
//        Department dept =
//                em.find(Department.class, course.getDeptId());
//
//        System.out.println("Student: " + student.getStudentName());
//        System.out.println("Department: " + dept.getDeptName());



        // FIND STUDENT'S ADDRESS

//        Student student = em.find(Student.class, 1);
//
//        Address address =
//                em.createQuery(
//                        "SELECT a FROM Address a WHERE a.studentId = 1",
//                        Address.class
//                ).getSingleResult();

//        System.out.println("Student: " + student.getStudentName());
//        System.out.println("City: " + address.getCity());
//        System.out.println("State: " + address.getState());
//        System.out.println("Pincode: " + address.getPincode());


        // UPDATE ADDRESS

//        Address address = em.find(Address.class, 1);
//
//        em.getTransaction().begin();
//
//        address.setCity("Chennai");
//
//        em.getTransaction().commit();
//
//        System.out.println("Address updated successfully");


        // UPDATE ADDRESS

//        Address address = em.find(Address.class, 1);
//
//        em.getTransaction().begin();
//
//        address.setCity("Chennai");
//
//        em.getTransaction().commit();
//
//        System.out.println("Address updated successfully");



// DELETE ADDRESS

//        Address address = em.find(Address.class, 10);
//
//        em.getTransaction().begin();
//
//        em.remove(address);
//
//        em.getTransaction().commit();
//
//        System.out.println("Address deleted successfully");



// FIND ADDRESS BY STUDENT

//        Address address =
//                em.createQuery(
//                        "SELECT a FROM Address a WHERE a.studentId = 1",
//                        Address.class
//                ).getSingleResult();

//        System.out.println("Address ID: " + address.getAddressId());
//        System.out.println("City: " + address.getCity());
//        System.out.println("State: " + address.getState());
//        System.out.println("Pincode: " + address.getPincode());
//        System.out.println("Student ID: " + address.getStudentId());


        // UPDATE TEACHER

//        Teacher teacher = em.find(Teacher.class, 1);

//        em.getTransaction().begin();
//
//        teacher.setTeacherName("Ravi Kumar");
//
//        em.getTransaction().commit();
//
//        System.out.println("Teacher updated successfully");


        // DELETE TEACHER

//        Teacher teacher = em.find(Teacher.class, 10);

//        em.getTransaction().begin();
//
//        em.remove(teacher);
//
//        em.getTransaction().commit();
//
//        System.out.println("Teacher deleted successfully");


        // FIND TEACHER BY ID

//        Teacher teacher = em.find(Teacher.class, 1);

//        System.out.println("Teacher ID: " + teacher.getTeacherId());
//        System.out.println("Teacher Name: " + teacher.getTeacherName());


        // DISPLAY ALL TEACHERS

//        java.util.List<Teacher> teacherList =
//                em.createQuery("SELECT t FROM Teacher t", Teacher.class)
//                        .getResultList();
//
//        for (Teacher teacher : teacherList) {
//            System.out.println(
//                    teacher.getTeacherId() + " - " +
//                            teacher.getTeacherName()
//            );
//        }

        // ASSIGN TEACHER TO STUDENT

        Student student = em.find(Student.class, 1);
        Teacher teacher = em.find(Teacher.class, 1);


        student.getTeachers().add(teacher);


// ASSIGN TEACHER TO STUDENT
//
//        Student studentObj = em.find(Student.class, 1);
//        Teacher teacherObj = em.find(Teacher.class, 1);
//
//        em.getTransaction().begin();
//
//        studentObj.getTeachers().add(teacherObj);
//
//        em.getTransaction().commit();
//
//        System.out.println("Teacher assigned to student successfully");



// FIND ALL STUDENTS IN BCA

        java.util.List<Student> studentList =
                em.createQuery(
                        "SELECT s FROM Student s WHERE s.courseId IN " +
                                "(SELECT c.courseId FROM Course c WHERE c.deptId = 1)",
                        Student.class
                ).getResultList();

        for (Student studentObj : studentList) {
            System.out.println(
                    studentObj.getStudentId() + " - " +
                            studentObj.getStudentName()
            );
        }

        em.close();
        emf.close();
    }
}
