package pojo;

import javax.persistence.*;

@Entity
@Table( name = "semester" )

public class Semester {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "semester_id")
    private int semesterId;

    @Column(name = "semester_number")
    private int semesterNumber;

    @ManyToOne
    @JoinColumn(name = "semester_faculty")
    private Faculty semesterFacultyId;

    public Semester(){}

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(int semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public Faculty getSemesterFacultyId() {
        return semesterFacultyId;
    }

    public void setSemesterFacultyId(Faculty facultyId) {
        this.semesterFacultyId = facultyId;
    }
}
