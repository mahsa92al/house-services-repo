package ir.maktab.model.entity;

import ir.maktab.model.enumaration.TransactionType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.security.PrivateKey;
import java.util.Date;

/**
 * @author Mahsa Alikhani m-58
 */
@Setter
@Getter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date transactionDate;
    @OneToOne
    private Client client;
    @OneToOne
    private Expert expert;
    @OneToOne
    private Order order;
}
