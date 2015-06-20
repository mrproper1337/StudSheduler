package pojo;

import javax.persistence.*;

@Entity
@Table( name = "schedule" )

public class Schedule {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "schedule_id")
    private int scheduleId;

    @ManyToOne
    @JoinColumn(name = "schedule_time")
    private Time scheduleTime;

    @ManyToOne
    @JoinColumn(name = "schedule_slg")
    private SLG scheduleSlg;

    @ManyToOne
    @JoinColumn(name = "schedule_audience")
    private Audience scheduleAudience;

    public Schedule(){}

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Time getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Time scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public SLG getScheduleSlg() {
        return scheduleSlg;
    }

    public void setScheduleSlg(SLG scheduleSlg) {
        this.scheduleSlg = scheduleSlg;
    }

    public Audience getScheduleAudience() {
        return scheduleAudience;
    }

    public void setScheduleAudience(Audience scheduleAudience) {
        this.scheduleAudience = scheduleAudience;
    }
}
