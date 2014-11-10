package no.nith.sivpal12.pg5100.eksamen.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(
        name = Genre.NAMED_QUERY_ALL,
        query = "SELECT g FROM Genre g")
public class Genre {

    public static final String NAMED_QUERY_ALL = "all-genres";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String genre;

    @Override
    public String toString() {
        return String.format("Genre {id=%s, genre=%s}", id, genre);
    }

}
