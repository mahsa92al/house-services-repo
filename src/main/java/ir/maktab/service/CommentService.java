package ir.maktab.service;

import ir.maktab.dao.CommentDao;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentDao commentDao;

    public void addComment(Comment comment){
        commentDao.save(comment);
    }

    public void removeComment(Comment comment){
        Optional<Comment> foundComment = findCommentById(comment);
        if(foundComment.isEmpty())
            throw new NotFoundException("comment not found!");
        commentDao.delete(comment);
    }

    private Optional<Comment> findCommentById(Comment comment) {
        Optional<Comment> foundComment = commentDao.findCommentById(comment);
        return foundComment;
    }

    public void update(Comment comment){
        Optional<Comment> foundComment = findCommentById(comment);
        if(foundComment.isEmpty())
            throw new NotFoundException("comment not found!");
        commentDao.update(comment);
    }

    public List<Comment> getAllComments(){
        List<Comment> comments = commentDao.findAllComments();
        if(comments.isEmpty())
            throw new NotFoundException("there is no comment!");
        return comments;
    }
}
