package ir.maktab.model.entity;

import ir.maktab.model.enumaration.ClientStatus;
import ir.maktab.model.enumaration.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Mahsa Alikhani m-58
 */
@Setter
@Getter
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 8)
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registrationDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
