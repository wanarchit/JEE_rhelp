/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NoyauFonctionnel;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Paul
 */
@Named(value = "employeControler")
@ViewScoped
public class employeControler implements Serializable{

    @EJB
    private employeDAO dao;
    private Employes selectedEmp;
    /**
     * Creates a new instance of employeControler
     */
    public employeControler() {
    }
    
    public List<Employes> getEmployes() {
        return dao.getAll();
    }
    
    public void addEmp(Employes emp){
        dao.saveEmploye(emp);
    }
    
    public void removeEmp(String idE){
        dao.remove(idE);
    }
    
    public Employes getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(Employes selectedEmp) {
        this.selectedEmp = selectedEmp;
    }
    
    public void updateEmp(Employes emp){
        dao.updateEmp(emp);
    }
    
    
}
