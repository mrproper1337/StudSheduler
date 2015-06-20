package pojo;

import javax.persistence.*;

@Entity
@Table( name = "lecturer" )

public class Lecturer {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "lecturer_id")
    private int lecturerId;

    @Column(name = "lecturer_name")
    private String lecturerName;

    public Lecturer(){}

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }
}
