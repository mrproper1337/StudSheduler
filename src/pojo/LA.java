package pojo;

import javax.persistence.*;

@Entity
@Table( name = "lecturer_audience" )

public class LA {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "la_id")
    private int laId;

    @ManyToOne
    @JoinColumn(name = "la_lecturer")
    private Lecturer laLecturer;

    @ManyToOne
    @JoinColumn(name = "la_audience")
    private Audience laAudience;

    public LA(){}

    public int getLaId() {
        return laId;
    }

    public void setLaId(int laId) {
        this.laId = laId;
    }

    public Lecturer getLaLecturer() {
        return laLecturer;
    }

    public void setLaLecturer(Lecturer laLecturer) {
        this.laLecturer = laLecturer;
    }

    public Audience getLaAudience() {
        return laAudience;
    }

    public void setLaAudience(Audience laAudience) {
        this.laAudience = laAudience;
    }
}
