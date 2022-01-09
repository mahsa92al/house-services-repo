package ir.maktab.dao;

import ir.maktab.model.entity.Category;
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
public class CategoryDao {
    private final SessionFactory sessionFactory;

    public void save(Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
        session.close();
    }

    public Optional<Category> findByTitle(String title) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Category> hql = session.createQuery("from Category c where c.title=:title");
        hql.setParameter("title", title);
        List<Category> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(list.isEmpty() ? null : list.get(0));
    }

    public void update(Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(category);
        session.getTransaction().commit();
        session.close();
    }

    public List<Category> findAllCategories() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Category> hql = session.createQuery("from Category ");
        List<Category> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public void delete(Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query hql = session.createQuery("delete Category c where c.title=:title");
        hql.setParameter("title", category.getTitle());
        hql.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
