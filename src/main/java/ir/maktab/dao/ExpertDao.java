package ir.maktab.dao;

import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Expert;
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
public class ExpertDao {
    private final SessionFactory sessionFactory;

    public void save(Expert expert) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(expert);
        session.getTransaction().commit();
        session.close();
    }

    public Optional<Expert> findByEmail(String email) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Expert> hql = session.createQuery("from Expert e where e.email=:email");
        hql.setParameter("email", email);
        List<Expert> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(list.isEmpty() ? null : list.get(0));
    }

    public void update(Expert expert) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(expert);
        session.getTransaction().commit();
        session.close();
    }

}
