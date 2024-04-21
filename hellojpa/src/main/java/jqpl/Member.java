package jqpl;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID") // 그냥 member가 가지고 있는 team의 fk 컬럼명 적어준다고 생각하면 될듯?
    private Team team;

    public void changeTeam(Team team) {
        this.team = team; // 1. 멤버에 팀을 세팅한다.
        team.getMembers().add(this); // 2. 팀에 멤버를 세팅한다.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
