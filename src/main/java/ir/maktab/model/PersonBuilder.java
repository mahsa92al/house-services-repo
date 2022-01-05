package ir.maktab.model;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.entity.Person;
import ir.maktab.model.enumaration.ClientStatus;
import ir.maktab.model.enumaration.Role;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class PersonBuilder {
    Person person = new Person();

    static PersonBuilder getBuilder(){
        return new PersonBuilder();
    }
    public Person build(){
        return person;
    }
    public PersonBuilder withName(String name){
        person.setName(name);
        return this;
    }
    public PersonBuilder withLastName(String lastName){
        person.setLastName(lastName);
        return this;
    }
    public PersonBuilder withEmail(String email){
        person.setEmail(email);
        return this;
    }
    public PersonBuilder withPassword(String password){
        person.setPassword(password);
        return this;
    }
    public PersonBuilder withRegistrationDate(Date registrationDate){
        person.setRegistrationDate(registrationDate);
        return this;
    }
    public PersonBuilder withClientStatus(ClientStatus clientStatus){
        person.setClientStatus(clientStatus);
        return this;
    }
    public PersonBuilder withRole(Role role){
        person.setRole(role);
        return this;
    }

}
