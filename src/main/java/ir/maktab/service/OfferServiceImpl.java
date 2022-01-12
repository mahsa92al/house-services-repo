package ir.maktab.service;

import ir.maktab.dao.OfferDao;
import ir.maktab.dao.OrderDao;
import ir.maktab.exception.NotFoundException;
import ir.maktab.exception.OfferException;
import ir.maktab.exception.ProposedPriceException;
import ir.maktab.model.dto.OfferDto;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.Offer;
import ir.maktab.model.entity.Order;
import ir.maktab.model.enumaration.OrderStatus;
import ir.maktab.service.mapper.OfferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService{
    private final OfferDao offerDao;
    private final OfferMapper offerMapper;
    private final OrderDao orderDao;

    @Override
    public Offer addOffer(OfferDto offerDto){
        if(!offerDto.getOrder().getOrderStatus().equals(OrderStatus.WAITING_FOR_EXPERT_OFFER))
            throw new OfferException("the order status is not match!");
        if(offerDto.getProposedPrice() < offerDto.getOrder().getSubCategory().getBasePrice())
            throw new ProposedPriceException("the proposed price is less than base price!");
        Offer offer = offerMapper.toOffer(offerDto);
        Offer registeredOffer = offerDao.save(offer);
        Order markedOrder = offer.getOrder();
        markedOrder.setOrderStatus(OrderStatus.WAITING_TO_CHOOSE_AN_EXPERT);
        orderDao.save(markedOrder);
        return registeredOffer;
    }

    @Override
    public void removeOffer(OfferDto offerDto){
        Offer offer = offerMapper.toOffer(offerDto);
        Optional<Offer> foundOffer = offerDao.findById(offer.getId());
        if(foundOffer.isEmpty())
            throw new NotFoundException("offer not found!");
        offerDao.delete(offer);
    }

    @Override
    public void update(OfferDto offerDto){
        Offer offer = offerMapper.toOffer(offerDto);
        Optional<Offer> foundOffer = offerDao.findById(offer.getId());
        if(foundOffer.isEmpty())
            throw new NotFoundException("offer not found!");
        offerDao.save(offer);
    }

    @Override
    public List<OfferDto> getAllOrders(Expert expert, Order order){
        List<Offer> offers = offerDao.findAllOffers(expert, order);
        if(offers.isEmpty())
            throw new NotFoundException("there is no offer!");
        return offers.stream().map(offerMapper::toOfferDto).collect(Collectors.toList());
    }
}
