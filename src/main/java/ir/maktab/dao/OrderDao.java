package ir.maktab.dao;

import ir.maktab.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mahsa Alikhani m-58
 */
@Repository
public interface OrderDao extends JpaRepository<Order,Long> {

}
