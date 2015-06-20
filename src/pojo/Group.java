package pojo;

import javax.persistence.*;

@Entity
@Table( name = "st_group" )

public class Group {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "group_id")
    private int groupId;

    @Column(name = "group_name")
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "group_semester")
    private Semester groupSemester;

    public Group(){}

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Semester getGroupSemester() {
        return groupSemester;
    }

    public void setGroupSemester(Semester groupSemester) {
        this.groupSemester = groupSemester;
    }
}
