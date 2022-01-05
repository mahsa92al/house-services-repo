package ir.maktab.dao;

import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Person;
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
public class ClientDao {
    private final SessionFactory sessionFactory;

    public void save(Client client){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    public Optional<Client> findByEmail(String email) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Client> hql = session.createQuery("from Client c where c.email=:email");
        hql.setParameter("email", email);
        List<Client> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(list.isEmpty() ? null : list.get(0));
    }

    public void update(Client client) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(client);
        session.getTransaction().commit();
        session.close();
    }

    public List<Client> findAllClients() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Client> hql = session.createQuery("from Client");
        List<Client> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public void delete(Client client) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query hql = session.createQuery("delete Client c where c.email=:email");
        hql.setParameter("email", client.getEmail());
        hql.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
