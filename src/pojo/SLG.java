package pojo;

import javax.persistence.*;

@Entity
@Table( name = "subject_lecturer_group" )

public class SLG {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "slg_id")
    private int slgId;

    @ManyToOne
    @JoinColumn(name = "slg_subject")
    private Subject slgSubject;

    @ManyToOne
    @JoinColumn(name = "slg_lecturer")
    private Lecturer slgLecturer;

    @ManyToOne
    @JoinColumn(name = "slg_group")
    private Group slgGroup;

    public SLG(){}

    public int getSlgId() {
        return slgId;
    }

    public void setSlgId(int slgId) {
        this.slgId = slgId;
    }

    public Subject getSlgSubject() {
        return slgSubject;
    }

    public void setSlgSubject(Subject slgSubject) {
        this.slgSubject = slgSubject;
    }

    public Lecturer getSlgLecturer() {
        return slgLecturer;
    }

    public void setSlgLecturer(Lecturer slgLecturer) {
        this.slgLecturer = slgLecturer;
    }

    public Group getSlgGroup() {
        return slgGroup;
    }

    public void setSlgGroup(Group slgGroup) {
        this.slgGroup = slgGroup;
    }
}
