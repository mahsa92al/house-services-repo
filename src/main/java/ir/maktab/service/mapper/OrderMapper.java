package ir.maktab.service.mapper;

import ir.maktab.model.dto.OrderDto;
import ir.maktab.model.entity.Order;

/**
 * @author Mahsa Alikhani m-58
 */
public class OrderMapper {

    public Order toOrder(OrderDto orderDto){
        return Order.builder()
                .id(orderDto.getId())
                .proposedPrice(orderDto.getProposedPrice())
                .description(orderDto.getDescription())
                .orderPlacingDate(orderDto.getOrderPlacingDate())
                .proposedDateToDo(orderDto.getProposedDateToDo())
                .address(orderDto.getAddress())
                .orderStatus(orderDto.getOrderStatus())
                .client(orderDto.getClient())
                .subCategory(orderDto.getSubCategory())
                .build();
    }

    public OrderDto toOrderDto(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .proposedPrice(order.getProposedPrice())
                .description(order.getDescription())
                .orderPlacingDate(order.getOrderPlacingDate())
                .proposedDateToDo(order.getProposedDateToDo())
                .address(order.getAddress())
                .orderStatus(order.getOrderStatus())
                .client(order.getClient())
                .subCategory(order.getSubCategory())
                .expert(order.getExpert())
                .offers(order.getOffers())
                .build();
    }
}
