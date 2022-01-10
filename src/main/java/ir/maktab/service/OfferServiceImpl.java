package ir.maktab.service;

import ir.maktab.dao.OfferDao;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.dto.OfferDto;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.Offer;
import ir.maktab.model.entity.Order;
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

    @Override
    public void addOffer(OfferDto offerDto){
        Offer offer = offerMapper.toOffer(offerDto);
        offerDao.save(offer);
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
