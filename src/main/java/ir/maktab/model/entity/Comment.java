package ir.maktab.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Mahsa Alikhani m-58
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private Double rate;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "expert_id", nullable = false)
    private Expert expert;

}
