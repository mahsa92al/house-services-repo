package ir.maktab.service;

import ir.maktab.dao.PersonDao;
import ir.maktab.model.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonDao personDao;

    public void add(Person person){
        personDao.save(person);
    }
}
