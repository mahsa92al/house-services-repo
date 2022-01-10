package ir.maktab.service;

import ir.maktab.model.dto.OfferDto;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.Order;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public interface OfferService {

    void addOffer(OfferDto offerDto);

    void removeOffer(OfferDto offerDto);

    void update(OfferDto offerDto);

    List<OfferDto> getAllOrders(Expert expert, Order order);
}
