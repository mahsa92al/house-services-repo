package ir.maktab.service;

import ir.maktab.model.dto.PersonDto;
import ir.maktab.model.enumaration.Role;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public interface PersonService {

    List<PersonDto> getAllUsers(Role role, String name, String lastName, String email, String speciality);
}
