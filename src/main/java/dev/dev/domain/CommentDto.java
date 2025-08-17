package dev.dev.domain;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private String content;
    private Long authorId;
    private String authorNickname;

    public static CommentDto from(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.id = comment.getId();
        commentDto.content = comment.getContent();
        commentDto.authorId = comment.getAuthor().getId();
        commentDto.authorNickname = comment.getAuthor().getNickname();
        return commentDto;
    }
}
