package dev.dev.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
public class User extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false, unique = true)
    private String nickname;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 10, nullable = false, unique = true)
    private String loginId;

    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "author", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Post> posts = new ArrayList<>();

    protected User() {}

    public User(String name, String nickname, String phone, String loginId, String password, UserRole role) {
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.loginId = loginId;
        this.password = password;
        this.role = role;
    }

    public void addPost(Post post) {
        this.posts.add(post);
        post.changeAuthor(this);
    }

    public void changeName(String name) {
        this.name = name;
    }
    public void changePhone(String phone) {
        this.phone = phone;
    }
    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
    public void changeNickname(String nickname) { this.nickname = nickname;}
}
