package ir.maktab.dao;

import ir.maktab.model.entity.SubService;
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
public class SubServiceDao {
    private final SessionFactory sessionFactory;

    public void save(SubService subService) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(subService);
        session.getTransaction().commit();
        session.close();
    }

    public Optional<SubService> findByTitle(String title) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<SubService> hql = session.createQuery("from SubService ss where ss.title=:title");
        hql.setParameter("title", title);
        List<SubService> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(list.isEmpty() ? null : list.get(0));
    }

    public List<SubService> findAllSubServices() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<SubService> hql = session.createQuery("from SubService");
        List<SubService> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public void update(SubService subService) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(subService);
        session.getTransaction().commit();
        session.close();
    }
}
