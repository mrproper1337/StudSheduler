package pojo;

import javax.persistence.*;

@Entity
@Table( name = "subject" )

public class Subject {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "subject_hours")
    private int subjectHours;

    @ManyToOne
    @JoinColumn(name = "subject_semester")
    private Semester subjectSemester;

    public Subject(){}

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectHours() {
        return subjectHours;
    }

    public void setSubjectHours(int subjectHours) {
        this.subjectHours = subjectHours;
    }

    public Semester getSubjectSemester() {
        return subjectSemester;
    }

    public void setSubjectSemester(Semester subjectSemester) {
        this.subjectSemester = subjectSemester;
    }
}
