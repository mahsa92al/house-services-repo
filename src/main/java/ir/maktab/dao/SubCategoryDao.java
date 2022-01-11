package ir.maktab.dao;

import ir.maktab.model.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mahsa Alikhani m-58
 */
@Repository
public interface SubCategoryDao extends JpaRepository<SubCategory, Integer> {

    Optional<SubCategory> findByTitleIgnoreCase(String title);
}
