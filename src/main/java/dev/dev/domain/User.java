package dev.dev.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(length = 5, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String phone;

    @Column(length = 10, nullable = false, unique = true)
    private String loginId;

    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "author", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    protected User() {}

    public void addPost(Post post) {
        this.posts.add(post);
        post.changeAuthor(this);
    }

}
