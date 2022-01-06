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
    private int rate;
    private String comment;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Expert expert;

}
