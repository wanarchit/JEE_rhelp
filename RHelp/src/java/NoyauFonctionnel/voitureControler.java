/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NoyauFonctionnel;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Paul
 */
@Named(value = "voitureControler")
@ViewScoped
public class voitureControler implements Serializable{

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
    
    public void addVoi(Voiture voi){
        voi.setDisponibilite(true);
        dao.saveVoiture(voi);
    }
    
    public void removeVoi(String idV){
        dao.remove(idV);
    }
    
    public Voiture getSelectedVoi() {
        if (selectedVoi == null){
            selectedVoi = getVoitures().get(0);
        }
        return selectedVoi;
    }

    public void setSelectedVoi(Voiture selectedVoi) {
        this.selectedVoi = selectedVoi;
    }
    
    public void updateVoi(Voiture voi){
        dao.updateVoi(voi);
    }
    
    public Voiture getOneVoiture(String pla){
        return dao.getOneVoiture(pla);
    }
}
