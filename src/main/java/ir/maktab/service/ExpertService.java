package ir.maktab.service;

import ir.maktab.model.dto.ExpertDto;
import ir.maktab.model.entity.Expert;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public interface ExpertService {

    void add(Expert expert);

    void update(Expert expert);

    List<ExpertDto> getAllExperts();

    void remove(Expert expert);
}
