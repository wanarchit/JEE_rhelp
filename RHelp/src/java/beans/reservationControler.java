/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import NoyauFonctionnel.Employes;
import NoyauFonctionnel.Reservations;
import dao.reservationDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Paul
 */
@Named(value = "reservationControler")
@ViewScoped
public class reservationControler implements Serializable {

    @EJB
    private reservationDAO dao;
    private Reservations selectedResa;
    private Reservations selectedResaRendre;

    /**
     * Creates a new instance of reservationControler
     */
    public reservationControler() {
    }

    public List<Reservations> getReservations() {
        return dao.getAll();
    }

    public List<Reservations> getReservationsEmp(String secu) {
        return dao.getReservationEmp(secu);
    }

    public ArrayList<Reservations> getVoitARendreEmp(String secu) {
        ArrayList<Reservations> voirARendreEmpre = new ArrayList();
        List<Reservations> resaEmp = getReservationsEmp(secu);
        Date currentDate = new Date();
        for (Reservations resa : resaEmp) {
            if (resa.getEtat().equals("Demande validée")) {
                if (resa.getDateD().before(currentDate)) {
                    voirARendreEmpre.add(resa);
                }
            }
        }
        if (!voirARendreEmpre.isEmpty()) {
            this.selectedResaRendre = voirARendreEmpre.get(0);
        } else {
            this.selectedResaRendre = null;
        }
        return voirARendreEmpre;
    }

    public void addResaAdmin(Reservations resa) {
        resa.setEtat("Demande validée");
        dao.saveReservation(resa);
    }

    public void addResaEmp(Reservations resa) {
        resa.setEtat("Demande à valider");
        dao.saveReservation(resa);
    }

    public void removeResa(int idR) {
        dao.remove(idR);
    }

    public void cancelResa(Reservations selectResa) {
        if (selectResa.getEtat().equals("Demande à valider")) {
            dao.remove(selectResa.getIdResa());
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Vous ne pouvez annuler qu'une demande 'A valider'. Pour une demande déjà validée, adressez vous directement à votre RH.", ""));
        }
    }

    public Reservations getSelectedResa() {
        if (selectedResa == null) {
            selectedResa = getReservations().get(0);
        }
        return selectedResa;
    }

    public int getResaDemAVal() {
        int i = 0;
        List<Reservations> listResa = getReservations();
        for (Reservations resa : listResa) {
            if (resa.getEtat().equals("Demande à valider")) {
                i++;
            }
        }
        return i;
    }

    public int getResaRetAVal() {
        int i = 0;
        List<Reservations> listResa = getReservations();
        for (Reservations resa : listResa) {
            if (resa.getEtat().equals("Retour à valider")) {
                i++;
            }
        }
        return i;
    }

    public void setSelectedResa(Reservations selectedResa) {
        this.selectedResa = selectedResa;
    }

    public void updateResa(Reservations resa) {
        dao.updateResa(resa);
    }

    public void rendreVoit(Reservations selectResa) {
        if (selectResa != null) {
            try {
                selectResa.setEtat("Retour à valider");
                dao.updateResa(selectResa);
                FacesContext.getCurrentInstance().addMessage("btnRendre", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "La réstitution du véhicule c'est correctement déroulée. Une validation par la RH sera effectuée.", ""));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Une erreur s'est produite...", ""));
            }

        } else {
            FacesContext.getCurrentInstance().addMessage("btnRendre", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Vous n'avez aucune voiture à rendre", ""));
        }
    }

    public Reservations getSelectedResaRendre() {
        return selectedResaRendre;
    }

    public void setSelectedResaRendre(Reservations selectedResaRendre) {
        this.selectedResaRendre = selectedResaRendre;
    }
}
