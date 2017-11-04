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
import java.util.List;
import javax.ejb.EJB;
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

    public void addResaAdmin(Reservations resa) {
        resa.setEtat("Demande validée");
        dao.saveReservation(resa);
    }
    
    public void addResaEmp(Reservations resa, Employes emp){
        System.out.println("addResaEmp");
        resa.setNumSS(emp);
        System.out.println(resa.getNumSS().getNumSS());
        
        System.out.println("set 1 ok");
        System.out.println("Etat = "+resa.getEtat());
        resa.setEtat("Demande à valider");
        System.out.println("Etat = "+resa.getEtat());
        System.out.println("set 2 ok");
        dao.saveReservation(resa);
        System.out.println("add ok");
    }

    public void removeResa(int idR) {
        dao.remove(idR);
    }

    public void cancelResa(Reservations selectResa) {
        if (selectResa.getEtat().equals("Demande à valider")) {
            dao.remove(selectResa.getIdResa());
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
            if (resa.getEtat().equals("Demande à valider")){
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
}
