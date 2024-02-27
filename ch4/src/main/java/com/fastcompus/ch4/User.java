package com.fastcompus.ch4;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // Entity 클래스는 Entity 애너테이션을 붙여야 한다.
public class User {
    @Id // PK로 지정
    @Column(name = "user_id")
    private String id;
    private String password;
    private String name;
    private String email;
    // FetchType.EAGER - 두 엔티티의 정보를 같이 가져오는 것(join)
    // FetchType.LAZY - 따로 가져오는 것. 나중에 getList(). default
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER) // User 하나에 여러 Board
    List<Board> list = new ArrayList<>();
    private Date inDate; // 입력일
    private Date upDate; // 변경일

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Board> getList() {
        return list;
    }

    public void setList(List<Board> list) {
        this.list = list;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", list=" + list +
                ", inDate=" + inDate +
                ", upDate=" + upDate +
                '}';
    }
}
