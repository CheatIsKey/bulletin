package dev.dev.domain;

import lombok.Data;

import java.util.List;

@Data
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private AuthorDto author;
    private List<CommentDto> comments;

    public static PostDto from(Post post) {
        PostDto postDto = new PostDto();
        postDto.id = post.getId();
        postDto.title = post.getTitle();
        postDto.content = post.getContent();
        postDto.author = AuthorDto.from(post.getAuthor());
        postDto.comments = post.getComments().stream()
                .map(CommentDto::from)
                .toList();
        return postDto;
    }
}
