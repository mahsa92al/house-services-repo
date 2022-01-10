package ir.maktab.dao;

import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.Offer;
import ir.maktab.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Repository
public interface OfferDao extends JpaRepository<Offer, Long> {

    @Query(value = "select o from Offer o where o.expert = :expert and o.order = :order")
    List<Offer> findAllOffers(@Param("expert") Expert expert, @Param("order") Order order);
}
