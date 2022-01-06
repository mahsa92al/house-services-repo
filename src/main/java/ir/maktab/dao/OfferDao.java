package ir.maktab.dao;

import ir.maktab.model.entity.Offer;
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
public class OfferDao {
    private final SessionFactory sessionFactory;

    public void save(Offer offer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(offer);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Offer offer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query hql = session.createQuery("delete Offer o where o.id=:id");
        hql.setParameter("id", offer.getId());
        hql.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public Optional<Offer> findOfferById(Offer offer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Offer> hql = session.createQuery("from Offer o where o.id=:id");
        hql.setParameter("id", offer.getId());
        List<Offer> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(list.isEmpty() ? null : list.get(0));
    }

    public void update(Offer offer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(offer);
        session.getTransaction().commit();
        session.close();
    }

    public List<Offer> findAllOffers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Offer> hql = session.createQuery("from Offer");
        List<Offer> list = hql.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
