package ir.maktab.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Setter
@Getter
@Entity
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String title;
    @Column(nullable = false)
    private Double basePrice;
    private String description;
    @ManyToMany(mappedBy = "subCategories")
    private List<Expert> experts = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subCategory")
    private List<Order> orders = new ArrayList<>();

}
