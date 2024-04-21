package jqpl;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String usename;
    private int age;
    @ManyToOne
    @JoinColumn(name = "TEAM_ID") // 그냥 member가 가지고 있는 team의 fk 컬럼명 적어준다고 생각하면 될듯?
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
