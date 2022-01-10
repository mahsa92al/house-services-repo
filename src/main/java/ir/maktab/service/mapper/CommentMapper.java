package ir.maktab.service.mapper;

import ir.maktab.model.dto.CommentDto;
import ir.maktab.model.entity.Comment;

/**
 * @author Mahsa Alikhani m-58
 */
public class CommentMapper {

    public Comment toComment(CommentDto commentDto){
        return Comment.builder()
                .id(commentDto.getId())
                .comment(commentDto.getComment())
                .rate(commentDto.getRate())
                .client(commentDto.getClient())
                .expert(commentDto.getExpert())
                .build();
    }

    public CommentDto toCommentDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .rate(comment.getRate())
                .client(comment.getClient())
                .expert(comment.getExpert())
                .build();
    }
}
