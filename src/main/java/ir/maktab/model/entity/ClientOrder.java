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
public class ClientOrder {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientOrder")
    private List<Offer> offers = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "expert_id", nullable = false)
    private Expert expert;
    @ManyToOne
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

}
