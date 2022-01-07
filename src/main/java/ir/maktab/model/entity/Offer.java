package ir.maktab.model.entity;

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
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date offerSubmissionDate;
    private Double proposedPrice;
    private String workDuration;
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @ManyToOne
    @Column(nullable = false)
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @Column(nullable = false)
    @JoinColumn(name = "expert_id")
    private Expert expert;
}
