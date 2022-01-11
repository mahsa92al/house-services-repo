package ir.maktab.model.dto;

import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.Offer;
import ir.maktab.model.entity.SubCategory;
import ir.maktab.model.enumaration.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Setter
@Getter
@Builder
public class OrderDto {
    private long id;
    private Double proposedPrice;
    private String description;
    private Date orderPlacingDate;
    private Date proposedDateToDo;
    private String Address;
    private OrderStatus orderStatus;
    private Client client;
    private Expert expert;
    private SubCategory subCategory;
    private List<Offer> offers;
}
