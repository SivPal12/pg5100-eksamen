package no.nith.sivpal12.pg5100.eksamen.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

//@Named
@SessionScoped
public class Session implements Serializable {
    private static final long serialVersionUID = 1L;

    private int currentConcertId;

    public int getCurrentConcertId() {
        return currentConcertId;
    }

    public void setCurrentConcertId(int currentConcertId) {
        this.currentConcertId = currentConcertId;
    }
}
