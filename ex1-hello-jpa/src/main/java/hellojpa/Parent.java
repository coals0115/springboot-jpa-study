package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    // 이 메서드는 parent 엔티티에서 자식을 추가하는 메서드. (양방향 연관관계)
    public void addChild(Child child) {
        // 1. 부모가 가지고 있는 자식 리스트에 자식을 넣는다. | 부모 -> 자식
        childList.add(child);
        // 2. 반대로 자식에도 부모를 세팅한다. | 자식 -> 부모
        child.setParent(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }
}
