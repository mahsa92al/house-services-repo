package ir.maktab.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Mahsa Alikhani m-58
 */
@Setter
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private Double rate;
    private String comment;
    @ManyToOne
    @Column(nullable = false)
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @Column(nullable = false)
    @JoinColumn(name = "expert_id")
    private Expert expert;

}
