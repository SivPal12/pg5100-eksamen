package no.nith.sivpal12.pg5100.eksamen.beans;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextBean {

    @RequestScoped
    @Produces
    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }
}
