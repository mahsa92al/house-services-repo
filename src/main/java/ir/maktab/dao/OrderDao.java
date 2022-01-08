package ir.maktab.dao;

import ir.maktab.model.entity.ClientOrder;
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
public class OrderDao {
    private final SessionFactory sessionFactory;

    public void save(ClientOrder clientOrder) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(clientOrder);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(ClientOrder clientOrder) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query hql = session.createQuery("delete ClientOrder o where o.id=:id");
        hql.setParameter("id", clientOrder.getId());
        hql.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public Optional<ClientOrder> findOrderById(ClientOrder clientOrder) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<ClientOrder> hql = session.createQuery("from ClientOrder o where o.id=:id");
        hql.setParameter("id", clientOrder.getId());
        List<ClientOrder> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(list.isEmpty() ? null : list.get(0));
    }

    public void update(ClientOrder clientOrder) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(clientOrder);
        session.getTransaction().commit();
        session.close();
    }

    public List<ClientOrder> findAllOrders() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<ClientOrder> hql = session.createQuery("from ClientOrder");
        List<ClientOrder> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
