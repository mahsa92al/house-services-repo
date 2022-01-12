package ir.maktab.service;

import ir.maktab.model.dto.ExpertDto;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.Order;
import ir.maktab.model.entity.SubCategory;

import java.util.List;
import java.util.Map;

/**
 * @author Mahsa Alikhani m-58
 */
public interface ExpertService {

    void add(Expert expert);

    void update(Expert expert);

    List<ExpertDto> getAllExperts();

    void remove(Expert expert);

    Map<String, List<Order>> getExpertSubCategoryOrdersList(Expert expert);
}
