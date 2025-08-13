package dev.dev.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class PostCategory {

    @Id @GeneratedValue
    @Column(name = "post_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    protected PostCategory() {}

    public PostCategory(Post post, Category category) {
        this.post = post;
        this.category = category;
    }

    public void unlink() {
        this.post = null;
        this.category = null;
    }
}
