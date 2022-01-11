package ir.maktab.service.mapper;

import ir.maktab.model.dto.PersonDto;
import ir.maktab.model.entity.Person;

/**
 * @author Mahsa Alikhani m-58
 */
public class PersonMapper {

    private Person person;

    public Person toPerson(PersonDto personDto){
        person = new Person();
        person.setName(personDto.getName());
        person.setLastName(personDto.getLastName());
        person.setEmail(personDto.getEmail());
        person.setUserStatus(personDto.getUserStatus());
        person.setRegistrationDate(personDto.getRegistrationDate());
        person.setRole(personDto.getRole());
        return person;
    }

    public PersonDto toPersonDto(Person person){
        return PersonDto.builder()
                .name(person.getName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .userStatus(person.getUserStatus())
                .registrationDate(person.getRegistrationDate())
                .role(person.getRole())
                .build();
    }
}
