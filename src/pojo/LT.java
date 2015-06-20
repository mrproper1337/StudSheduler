package pojo;

import javax.persistence.*;

@Entity
@Table( name = "lecturer_time" )

public class LT {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "lt_id")
    private int ltId;

    @ManyToOne
    @JoinColumn(name = "lt_lecturer")
    private Lecturer ltLecturer;

    @ManyToOne
    @JoinColumn(name = "lt_time")
    private Time ltTime;

    public LT(){}

    public int getLtId() {
        return ltId;
    }

    public void setLtId(int ltId) {
        this.ltId = ltId;
    }

    public Lecturer getLtLecturer() {
        return ltLecturer;
    }

    public void setLtLecturer(Lecturer ltLecturer) {
        this.ltLecturer = ltLecturer;
    }

    public Time getLtTime() {
        return ltTime;
    }

    public void setLtTime(Time ltTime) {
        this.ltTime = ltTime;
    }
}
