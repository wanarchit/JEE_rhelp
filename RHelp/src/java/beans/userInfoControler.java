package beans;

import NoyauFonctionnel.Employes;
import NoyauFonctionnel.Userinfo;
import dao.employeDAO;
import dao.userInfoDAO;
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
@Named(value = "userInfoControler")
@ViewScoped
public class userInfoControler implements Serializable{

    @EJB
    private userInfoDAO dao;
    private Userinfo selectedUserInfo;
    /**
     * Creates a new instance of employeControler
     */
    public userInfoControler() {
    }
    
    public List<Userinfo> getUsersInfos() {
        return dao.getAll();
    }
    
    
    public Userinfo getSelectedUser() {
        if (selectedUserInfo == null){
            selectedUserInfo = getUsersInfos().get(0);
        }
        return selectedUserInfo;
    }

    public void setSelectedEmp(Userinfo selectedUI) {
        this.selectedUserInfo = selectedUI;
    }
    
    public void updateUserInfo(Userinfo ui){
        dao.updateUserInfo(ui);
    }
    
    public Userinfo getUserConnecte(String user){
        return dao.getOneUserinfo(user);
    }
    
}
