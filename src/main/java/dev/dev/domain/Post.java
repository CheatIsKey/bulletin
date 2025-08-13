package dev.dev.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Post extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    private Boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostCategory> postCategories = new ArrayList<>();

    protected Post() {}

    public void changeAuthor(User author) {
        this.author = author;
    }

    public void addCategory(Category category) {
        PostCategory link = new PostCategory(this, category);
        this.postCategories.add(link);
        category.getPostCategories().add(link);
    }

    public void removeCategory(Category category) {
        postCategories.removeIf(pc -> {
            boolean hit = pc.getCategory().equals(category);
            if (hit) {
                category.getPostCategories().remove(pc);
                pc.unlink();
            }
            return hit;
        });
    }

    public void addComment(User author, String content) {
        Comment comment = Comment.of(this, author, content);
        this.comments.add(comment);
    }
}
