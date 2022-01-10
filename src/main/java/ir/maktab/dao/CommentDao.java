package ir.maktab.dao;

import ir.maktab.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {

    List<Comment> findAllByExpert_IdOrderByRate(Long expertId);
}
