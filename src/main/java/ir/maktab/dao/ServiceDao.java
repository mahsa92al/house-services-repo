package ir.maktab.dao;

import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Service;
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
public class ServiceDao {
    private final SessionFactory sessionFactory;

    public void save(Service service) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(service);
        session.getTransaction().commit();
        session.close();
    }

    public Optional<Service> findByTitle(String title) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Service> hql = session.createQuery("from Service s where s.title=:title");
        hql.setParameter("title", title);
        List<Service> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(list.isEmpty() ? null : list.get(0));
    }

    public void update(Service service) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(service);
        session.getTransaction().commit();
        session.close();
    }

    public List<Service> findAllServices() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Service> hql = session.createQuery("from Service ");
        List<Service> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
