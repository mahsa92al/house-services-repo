package ir.maktab.model.entity;

import ir.maktab.model.enumaration.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Double proposedPrice;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date orderPlacingDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date proposedDateToDo;
    private String Address;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Offer> offers = new ArrayList<>();
    @ManyToOne
    @Column(nullable = false)
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @Column(nullable = false)
    @JoinColumn(name = "expert_id")
    private Expert expert;
    @ManyToOne
    @Column(nullable = false)
    @JoinColumn(name = "sub-category_id")
    private SubService subService;

}
