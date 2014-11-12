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
        @NamedQuery(name = Artist.NAMED_QUERY_ALL, query = "SELECT a FROM Artist a")
})
public class Artist {

    public static final String NAMED_QUERY_ALL = "all-artists";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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

    public static class Builder {
        private Artist artist = new Artist();

        public Builder setGenre(Genre genre) {
            artist.genre = genre;
            return this;
        }

        public Builder setName(String name) {
            artist.name = name;
            return this;
        }

        public Artist build() {
            return artist;
        }
    }

}
