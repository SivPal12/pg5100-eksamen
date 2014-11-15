package no.nith.sivpal12.pg5100.eksamen.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(
                name = Artist.NAMED_QUERY_ALL,
                query = "SELECT a FROM Artist a"
        ),
        @NamedQuery(
                name = Artist.NAMED_QUERY_BY_NAME,
                query = "SELECT a FROM Artist a WHERE a.name = ?1"
        )
})
public class Artist {

    public static final String NAMED_QUERY_ALL = "all-artists";
    public static final String NAMED_QUERY_BY_NAME = "one-artist";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    private Genre genre;
    private String name;

    @Override
    public String toString() {
        return String.format("Artist {id=%s, genre=%s, name=%s}", id, genre,
                name);
    }

    public int getId() {
        return id;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (!(obj instanceof Artist)) {
            return false;
        }
        Artist other = (Artist) obj;
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
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
