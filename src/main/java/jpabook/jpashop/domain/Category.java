package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name ="category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name= "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name ="item_id"))
    private List<Item> items = new ArrayList<>();   //다대다 관계 예시 현직에서는 잘안씀 이렇게 쓸 수 있다고 보여줌

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name ="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();  // 셀프로 양방향 연관관계

    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
