package pojo;

import javax.persistence.*;

@Entity
@Table( name = "faculty" )

public class Faculty {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "faculty_id")
    private int facultyId;

    @Column(name = "faculty_name")
    private String facultyName;

    @Column(name = "faculty_semesters")
    private int facultySemesters;


    public Faculty(){}

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getFacultySemesters() {
        return facultySemesters;
    }

    public void setFacultySemesters(int facultySemesters) {
        this.facultySemesters = facultySemesters;
    }
}
