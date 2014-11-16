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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return String.format("Genre {id=%s, genre=%s}", id, genre);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) obj;
        if (genre == null) {
            if (other.genre != null) {
                return false;
            }
        } else if (!genre.equals(other.genre)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        return true;
    }

}
