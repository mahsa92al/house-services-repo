package ir.maktab.service.mapper;

import ir.maktab.model.dto.SubCategoryDto;
import ir.maktab.model.entity.SubCategory;

/**
 * @author Mahsa Alikhani m-58
 */
public class SubCategoryMapper {

    public SubCategory toSubCategory(SubCategoryDto subCategoryDto){
        return SubCategory.builder()
                .title(subCategoryDto.getTitle())
                .basePrice(subCategoryDto.getBasePrice())
                .description(subCategoryDto.getDescription())
                .category(subCategoryDto.getCategory())
                .build();
    }

    public SubCategoryDto toSubCategoryDto(SubCategory subCategory){
        return SubCategoryDto.builder()
                .title(subCategory.getTitle())
                .basePrice(subCategory.getBasePrice())
                .description(subCategory.getDescription())
                .experts(subCategory.getExperts())
                .category(subCategory.getCategory())
                .orders(subCategory.getOrders())
                .build();
    }
}
