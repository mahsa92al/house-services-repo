package ir.maktab.service;

import ir.maktab.dao.PersonDao;
import ir.maktab.model.dto.PersonDto;
import ir.maktab.model.enumaration.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonDao personDao;

    public List<PersonDto> getAllUsers(Role role, String name, String lastName, String email){
        List<PersonDto> allUsers = personDao.findAllUsers(role, name, lastName, email);
        return allUsers;
    }
}
