package ir.maktab.dao;

import ir.maktab.model.dto.PersonDto;
import ir.maktab.model.entity.Person;
import ir.maktab.model.enumaration.Role;
import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Repository
@RequiredArgsConstructor
public class PersonDao {
    private final SessionFactory sessionFactory;

    public List<PersonDto> findAllUsers(Role role, String name, String lastName, String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = getCriteria(role, name, lastName, email, session);
        List<PersonDto> allUsers = criteria.list();
        transaction.commit();
        session.close();
        return allUsers;
    }
    private Criteria getCriteria(Role role, String name, String lastName, String email, Session session) {

        Criteria criteria = session.createCriteria(Person.class, "p");

        if (role != null) {
            criteria.add(Restrictions.eq("p.role", role));
        }
        if (name != null) {
            criteria.add(Restrictions.eq("p.name", name));
        }
        if (lastName != null) {
            criteria.add(Restrictions.eq("p.lastName", lastName));
        }
        if (email != null) {
            criteria.add(Restrictions.eq("p.email", email));
        }

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("p.role").as("role"))
                .add(Projections.property("p.name").as("name"))
                .add(Projections.property("p.lastName").as("lastName"))
                .add(Projections.property("p.email").as("email"))
                .add(Projections.property("p.registrationDate").as("registrationDate"))
                .add(Projections.property("p.userStatus").as("userStatus")));
        criteria.setResultTransformer(Transformers.aliasToBean(PersonDto.class));
        return criteria;
    }
}
