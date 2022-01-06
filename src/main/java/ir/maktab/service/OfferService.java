package ir.maktab.service;

import ir.maktab.dao.OfferDao;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.Offer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferDao offerDao;

    public void addOffer(Offer offer){
        offerDao.save(offer);
    }

    public void removeOffer(Offer offer){
        Optional<Offer> foundOffer = findOfferById(offer);
        if(foundOffer.isEmpty())
            throw new NotFoundException("offer not found!");
        offerDao.delete(offer);
    }

    private Optional<Offer> findOfferById(Offer offer) {
        Optional<Offer> foundOffer = offerDao.findOfferById(offer);
        return foundOffer;
    }

    public void update(Offer offer){
        Optional<Offer> foundOffer = findOfferById(offer);
        if(foundOffer.isEmpty())
            throw new NotFoundException("offer not found!");
        offerDao.update(offer);
    }

    public List<Offer> getAllOrders(){
        List<Offer> offers = offerDao.findAllOffers();
        if(offers.isEmpty())
            throw new NotFoundException("there is no offer!");
        return offers;
    }
}
