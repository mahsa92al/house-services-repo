package ir.maktab.model.dto;

import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.Order;
import ir.maktab.model.entity.Service;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class SubServiceDto {
    private String title;
    private Double basePrice;
    private String description;
    private List<Expert> experts;
    private Service service;
    private List<Order> orders;
}
