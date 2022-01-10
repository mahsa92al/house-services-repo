package ir.maktab.dao;

import ir.maktab.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mahsa Alikhani m-58
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

    Optional<Category> findByTitleIgnoreCase(String title);
}
