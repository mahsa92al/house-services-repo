package ir.maktab.model.dto;

import ir.maktab.model.entity.SubCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Setter
@Getter
@Builder
public class CategoryDto {
    private String title;
    private List<SubCategory> subCategories;
}
