package no.nith.sivpal12.pg5100.eksamen.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(
                name = Genre.NAMED_QUERY_ALL,
                query = "SELECT g FROM Genre g"),
        @NamedQuery(
                name = Genre.NAMED_QUERY_ONE,
                query = "SELECT g FROM Genre g WHERE g.genre = ?1")
})
public class Genre {

    public static final String NAMED_QUERY_ALL = "all-genres";
    public static final String NAMED_QUERY_ONE = "select-one";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String genre;

    /**
     * Case insensitive matching of genre.
     *
     * @param genreAsString
     * @return true if matching. Else false.
     */
    public boolean matchesString(String genreAsString) {
        if (genre == null) {
            return false;
        }
        return genre.equalsIgnoreCase(genreAsString);
    }

    @Override
    public String toString() {
        return String.format("Genre {id=%s, genre=%s}", id, genre);
    }

}
