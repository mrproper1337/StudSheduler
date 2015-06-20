package pojo;

import javax.persistence.*;

@Entity
@Table( name = "audience" )

public class Audience {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "audience_id")
    private int audienceId;

    @Column(name = "audience_number")
    private int audienceNumber;

    @Column(name = "audience_pavilion")
    private int audiencePavilion;

    @Column(name = "audience_floor")
    private int audienceFloor;

    public Audience(){}

    public int getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(int audienceId) {
        this.audienceId = audienceId;
    }

    public int getAudienceNumber() {
        return audienceNumber;
    }

    public void setAudienceNumber(int audienceNumber) {
        this.audienceNumber = audienceNumber;
    }

    public int getAudiencePavilion() {
        return audiencePavilion;
    }

    public void setAudiencePavilion(int audiencePavilion) {
        this.audiencePavilion = audiencePavilion;
    }

    public int getAudienceFloor() {
        return audienceFloor;
    }

    public void setAudienceFloor(int audienceFloor) {
        this.audienceFloor = audienceFloor;
    }
}
