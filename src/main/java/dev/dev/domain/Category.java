package dev.dev.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> child = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostCategory> postCategories = new ArrayList<>();

    protected Category() {}

    public Category(String name) { this.name = name; }

    public void addChild(Category child) {
        this.child.add(child);
        child.parent = this;
    }

    public void removeChild(Category child) {
        this.child.remove(child);
        child.parent = null;
    }
}
