package pojo;

import javax.persistence.*;

@Entity
@Table( name = "time" )

public class Time {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "time_id")
    private int timeId;

    @Column(name = "time_number")
    private int timeNumber;

    @Column(name = "time_day")
    private String timeDay;

    public Time(){}

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public int getTimeNumber() {
        return timeNumber;
    }

    public void setTimeNumber(int timeNumber) {
        this.timeNumber = timeNumber;
    }

    public String getTimeDay() {
        return timeDay;
    }

    public void setTimeDay(String timeDay) {
        this.timeDay = timeDay;
    }
}
