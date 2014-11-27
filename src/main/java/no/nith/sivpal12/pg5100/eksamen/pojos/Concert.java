package no.nith.sivpal12.pg5100.eksamen.pojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@NamedQueries({
        @NamedQuery(
                name = Concert.NAMED_QUERY_ALL,
                query = "SELECT c FROM Concert c"
        ),
        @NamedQuery(
                name = Concert.NAMED_QUERY_BY_NAME,
                query = "SELECT c FROM Concert c WHERE c.name = ?1"
        ),
        @NamedQuery(
                name = Concert.NAMED_QUERY_RANGE_FULL,
                query = "SELECT c FROM Concert c WHERE c.date >= ?1 AND c.date < ?2"
        ),
        @NamedQuery(
                name = Concert.NAMED_QUERY_RANGE_FROM,
                query = "SELECT c FROM Concert c WHERE c.date >= ?1"
        )
})
public class Concert {
    public static final String NAMED_QUERY_ALL = "all-concerts";
    public static final String NAMED_QUERY_BY_NAME = "concert-by-name";
    public static final String NAMED_QUERY_RANGE_FULL = "range-full";
    public static final String NAMED_QUERY_RANGE_FROM = "range-from";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @NotNull
    private Artist artist;
    @Future
    @NotNull
    private Date date;
    @DecimalMin(value = "1")
    private int price;
    @NotEmpty
    private String location;
    @NotEmpty
    private String description;
    @DecimalMin(value = "1")
    private int numTickets;
    private int ticketsSold;
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((artist == null) ? 0 : artist.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + id;
        result = prime * result
                + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + numTickets;
        result = prime * result + price;
        result = prime * result + ticketsSold;
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
        if (!(obj instanceof Concert)) {
            return false;
        }
        Concert other = (Concert) obj;
        if (artist == null) {
            if (other.artist != null) {
                return false;
            }
        } else if (!artist.equals(other.artist)) {
            return false;
        }
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (location == null) {
            if (other.location != null) {
                return false;
            }
        } else if (!location.equals(other.location)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (numTickets != other.numTickets) {
            return false;
        }
        if (price != other.price) {
            return false;
        }
        if (ticketsSold != other.ticketsSold) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String
                .format(
                        "Concert {id=%s, artist=%s, date=%s, price=%s, "
                                + "location=%s, description=%s, numTickets=%s, "
                                + "ticketsSold=%s, name=%s}",
                        id, artist, date, price, location, description,
                        numTickets, ticketsSold, name);
    }
}
