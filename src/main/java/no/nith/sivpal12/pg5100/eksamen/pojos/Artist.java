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

    @Override
    public String toString() {
        return String.format("Artist {id=%s, genre=%s, name=%s}", id, genre,
                name);
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
