package dev.dev.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Comment extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean deleted = Boolean.FALSE;

    protected Comment() {}

    public static Comment of(Post post, User author, String content) {
        Comment c = new Comment();
        c.post = post;
        c.author = author;
        c.content = content;
        return c;
    }
}
