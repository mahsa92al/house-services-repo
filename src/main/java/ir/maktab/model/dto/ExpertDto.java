package ir.maktab.model.dto;

import ir.maktab.model.enumaration.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Mahsa Alikhani m-58
 */
@Setter
@Getter
public class ExpertDto {
    private String name;
    private String lastName;
    private String email;
    private Date registrationDate;
    private UserStatus userStatus;
    private Double credit;
    private byte[] imageData;
    private Double meanRate;
}
