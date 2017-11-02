/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
public class reservationControler implements Serializable{

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
    
    public List<Reservations> getReservationsEmp(String secu){
        return dao.getReservationEmp(secu);
    }
    
    public void addResa(Reservations resa){
        resa.setEtat("Validé");
        dao.saveReservation(resa);
    }
    
    public void removeResa(int idR){
        dao.remove(idR);
    }
    
    public Reservations getSelectedResa() {
        return selectedResa;
    }

    public void setSelectedResa(Reservations selectedResa) {
        this.selectedResa = selectedResa;
    }
    
    public void updateResa(Reservations resa){
        dao.updateResa(resa);
    }
}
