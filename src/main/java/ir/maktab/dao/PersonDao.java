package ir.maktab.dao;

import ir.maktab.model.entity.Category;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.Person;
import ir.maktab.model.entity.SubCategory;
import ir.maktab.model.enumaration.Role;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Repository
public interface PersonDao extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    static Specification<Person> findPersonByCriteria(Role role, String name, String lastName, String email, String speciality) {

        List<Predicate> predicates = new ArrayList<>();
        return (root, cq, cb) -> {
            if (role != null) {
                predicates.add(cb.equal(root.get("role"), role));
            }
            if (name != null) {
                predicates.add(cb.equal(root.get("name"), name));
            }
            if (lastName != null) {
                predicates.add(cb.equal(root.get("lastName"), lastName));
            }
            if (email != null) {
                predicates.add(cb.equal(root.get("email"), email));
            }
            if (speciality != null) {
                Root<Expert> fromExperts = cq.from(Expert.class);
                Join<Expert, SubCategory> subCategories = fromExperts.join("subCategories");
                Join<SubCategory, Category> category = subCategories.join("category");
                predicates.add(cb.equal(category.get("title"), speciality));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
