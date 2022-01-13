package ir.maktab.service;

import ir.maktab.dao.PersonDao;
import ir.maktab.model.dto.PersonDto;
import ir.maktab.model.entity.Person;
import ir.maktab.model.enumaration.Role;
import ir.maktab.service.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    private final PersonDao personDao;
    private final PersonMapper personMapper;

    @Override
    public List<PersonDto> getAllUsers(Role role, String name, String lastName, String email, String speciality){
        List<Person> allUsers = personDao.findAll(PersonDao.findPersonByCriteria(role, name, lastName, email, speciality));
        return allUsers.stream().map(personMapper::toPersonDto).collect(Collectors.toList());
    }
}
