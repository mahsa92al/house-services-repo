package ir.maktab.dao;

import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Order;
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

    public void save(Order order) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Order order) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query hql = session.createQuery("delete Order o where o.id=:id");
        hql.setParameter("id", order.getId());
        hql.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public Optional<Order> findOrderById(Order order) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Order> hql = session.createQuery("from Order o where o.id=:id");
        hql.setParameter("id", order.getId());
        List<Order> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(list.isEmpty() ? null : list.get(0));
    }

    public void update(Order order) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(order);
        session.getTransaction().commit();
        session.close();
    }

    public List<Order> findAllOrders() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Order> hql = session.createQuery("from Order");
        List<Order> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
