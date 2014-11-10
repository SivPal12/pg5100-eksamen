package no.nith.sivpal12.pg5100.eksamen.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @OneToOne
    private Genre genre;
    private String name;

}
