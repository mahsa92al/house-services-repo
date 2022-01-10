package ir.maktab.dao;

import ir.maktab.model.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mahsa Alikhani m-58
 */
@Repository
public interface ExpertDao extends JpaRepository<Expert, Long> {

    Optional<Expert> findByEmail(String email);
}
