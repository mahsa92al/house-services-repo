package ir.maktab.dao;

import ir.maktab.model.entity.SubCategory;
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
public class SubCategoryDao {
    private final SessionFactory sessionFactory;

    public void save(SubCategory subCategory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(subCategory);
        session.getTransaction().commit();
        session.close();
    }

    public Optional<SubCategory> findByTitle(String title) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<SubCategory> hql = session.createQuery("from SubCategory ss where ss.title=:title");
        hql.setParameter("title", title);
        List<SubCategory> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(list.isEmpty() ? null : list.get(0));
    }

    public List<SubCategory> findAllSubCategories() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<SubCategory> hql = session.createQuery("from SubCategory");
        List<SubCategory> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public void update(SubCategory subCategory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(subCategory);
        session.getTransaction().commit();
        session.close();
    }
}
