package ir.maktab.dao;

import ir.maktab.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mahsa Alikhani m-58
 */
@Repository
public interface ClientDao extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

}
