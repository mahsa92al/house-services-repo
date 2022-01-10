package ir.maktab.service.mapper;

import ir.maktab.model.dto.CategoryDto;
import ir.maktab.model.dto.OfferDto;
import ir.maktab.model.entity.Category;
import ir.maktab.model.entity.Offer;

/**
 * @author Mahsa Alikhani m-58
 */
public class OfferMapper {

    public Offer toOffer(OfferDto offerDto){
        return Offer.builder()
                .id(offerDto.getId())
                .offerSubmissionDate(offerDto.getOfferSubmissionDate())
                .proposedPrice(offerDto.getProposedPrice())
                .workDuration(offerDto.getWorkDuration())
                .startTime(offerDto.getStartTime())
                .order(offerDto.getOrder())
                .expert(offerDto.getExpert())
                .build();
    }

    public OfferDto toOfferDto(Offer offer){
        return OfferDto.builder()
                .id(offer.getId())
                .offerSubmissionDate(offer.getOfferSubmissionDate())
                .proposedPrice(offer.getProposedPrice())
                .workDuration(offer.getWorkDuration())
                .startTime(offer.getStartTime())
                .order(offer.getOrder())
                .expert(offer.getExpert())
                .build();
    }
}
