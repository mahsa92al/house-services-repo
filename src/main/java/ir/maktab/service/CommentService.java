package ir.maktab.service;

import ir.maktab.model.dto.CommentDto;
import ir.maktab.model.entity.Expert;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public interface CommentService {

    void addComment(CommentDto commentDto);

    void removeComment(CommentDto commentDto);

    void update(CommentDto commentDto);

    List<CommentDto> getAllComments(Expert expert);
}
