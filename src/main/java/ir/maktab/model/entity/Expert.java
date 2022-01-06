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
public class Expert extends Person{
    @Lob
    @Column(unique = true, columnDefinition = "BLOB", length = 3072)
    private byte[] imageData;
    private int rate;
    private double credit;
    @ManyToMany
    private List<SubService> subServices = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "expert")
    private List<Offer> offers = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "expert")
    private List<Comment> comments = new ArrayList<>();
}
