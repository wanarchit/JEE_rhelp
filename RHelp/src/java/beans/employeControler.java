/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import NoyauFonctionnel.Employes;
import NoyauFonctionnel.Userinfo;
import dao.employeDAO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        ""+emp.getPrenom()+" "+emp.getNom()+" a bien été enregistré(e).", ""));
    }
    
    public void removeEmp(String idE){
        boolean valid = true;
        for (Userinfo ui : selectedEmp.getUserinfoList()) {
            if (ui.getUser().equals("admin")){
                valid = false;
            }
        }
        if(valid){
            dao.remove(idE);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        ""+selectedEmp.getPrenom()+" "+selectedEmp.getNom()+" a bien été supprimé(e).", ""));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                    ""+selectedEmp.getPrenom()+" "+selectedEmp.getNom()+" à un profil ADMIN lié. Il est impossible de supprimer cet(te) employé(e)."));
        }
    }
    
    public Employes getSelectedEmp() {
        if (selectedEmp == null){
            selectedEmp = getEmployes().get(0);
        }
        return selectedEmp;
    }

    public void setSelectedEmp(Employes selectedEmp) {
        this.selectedEmp = selectedEmp;
    }
    
    public void updateEmp(Employes emp){
        dao.updateEmp(emp);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Les informations de "+emp.getPrenom()+" "+emp.getNom()+" ont bien été mises à jour.", ""));
        
    }
    
    
}
