package ir.maktab.service;

import ir.maktab.dao.CommentDao;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.dto.CommentDto;
import ir.maktab.model.entity.Comment;
import ir.maktab.model.entity.Expert;
import ir.maktab.service.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentDao commentDao;
    private final CommentMapper commentMapper;

    public void addComment(CommentDto commentDto){
        Comment comment = commentMapper.toComment(commentDto);
        commentDao.save(comment);
    }

    public void removeComment(CommentDto commentDto){
        Comment comment = commentMapper.toComment(commentDto);
        Optional<Comment> foundComment = commentDao.findById(comment.getId());
        if(foundComment.isEmpty())
            throw new NotFoundException("comment not found!");
        commentDao.delete(comment);
    }

    public void update(CommentDto commentDto){
        Comment comment = commentMapper.toComment(commentDto);
        Optional<Comment> foundComment = commentDao.findById(comment.getId());
        if(foundComment.isEmpty())
            throw new NotFoundException("comment not found!");
        commentDao.save(comment);
    }

    public List<CommentDto> getAllComments(Expert expert){
        List<Comment> comments = commentDao.findAllByExpert_IdOrderByRate(expert.getId());
        if(comments.isEmpty())
            throw new NotFoundException("there is no comment!");
        return comments.stream().map(commentMapper::toCommentDto).collect(Collectors.toList());
    }
}
