package ir.maktab.model.dto;

import ir.maktab.model.enumaration.UserStatus;
import ir.maktab.model.enumaration.Role;

import java.util.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class PersonDto {
    private String name;
    private String lastName;
    private String email;
    private Date registrationDate;
    private UserStatus userStatus;
    private Role role;
}
