/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import NoyauFonctionnel.Voiture;
import dao.voitureDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Paul
 */
@Named(value = "voitureControler")
@ViewScoped
public class voitureControler implements Serializable {

    @EJB
    private voitureDAO dao;
    private Voiture selectedVoi;
    private List<Voiture> listVoiture;

    /**
     * Creates a new instance of voitureControler
     */
    public voitureControler() {

    }

    public List<Voiture> getVoitures() {
        return this.listVoiture;
    }

    @PostConstruct
    public void init() {
        this.listVoiture = dao.getAll();
    }

    public void addVoi(Voiture voi) {
        voi.setDisponibilite(true);
        dao.saveVoiture(voi);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Le véhicule " + voi.getMarque() + " " + voi.getModele() + " à bien été ajouté.", ""));
    }

    public void removeVoi(String idV) {
        dao.remove(idV);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Le véhicule " + selectedVoi.getMarque() + " " + selectedVoi.getModele() + " à bien été supprimé.", ""));
    }

    public Voiture getSelectedVoi() {
        if (selectedVoi == null) {
            selectedVoi = getVoitures().get(0);
        }
        return selectedVoi;
    }

    public void setSelectedVoi(Voiture selectedVoi) {
        this.selectedVoi = selectedVoi;
    }

    public void updateVoi(Voiture voi) {
        dao.updateVoi(voi);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Les informations de la voiture " + selectedVoi.getMarque() + " " + selectedVoi.getModele() + " ont bien été mises à jour.", ""));
    }

    public Voiture getOneVoiture(String pla) {
        return dao.getOneVoiture(pla);
    }

    public ArrayList<Voiture> getVoitureRevis() {
        ArrayList<Voiture> voitRevis = new ArrayList();
        List<Voiture> listvoit = getVoitures();

        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, +15);
        Date dateFinale = cal.getTime();

        for (Voiture voi : listvoit) {
            if (voi.getDateRevision().before(dateFinale)) {
                voitRevis.add(voi);
            }
        }
        return voitRevis;
    }

    public String getAVAPRevis(Voiture voit) {

        Date currentDate = new Date();
        if (voit.getDateRevision().before(currentDate)) {
            return "<font color = '#A90024'>(Attention, la date est déjà passée)</font>";
        }
        else return "(Date à venir dans moins de 15 jours)";
    }
}
