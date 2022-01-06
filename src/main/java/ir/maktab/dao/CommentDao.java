package ir.maktab.dao;

import ir.maktab.model.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Mahsa Alikhani m-58
 */
@Repository
@RequiredArgsConstructor
public class CommentDao {
    private final SessionFactory sessionFactory;

    public void save(Comment comment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(comment);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Comment comment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query hql = session.createQuery("delete Comment c where c.id=:id");
        hql.setParameter("id", comment.getId());
        hql.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public Optional<Comment> findCommentById(Comment comment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Comment> hql = session.createQuery("from Comment c where c.id=:id");
        hql.setParameter("id", comment.getId());
        List<Comment> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(list.isEmpty() ? null : list.get(0));
    }

    public void update(Comment comment) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(comment);
        session.getTransaction().commit();
        session.close();
    }

    public List<Comment> findAllComments() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Comment> hql = session.createQuery("from Comment ");
        List<Comment> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
