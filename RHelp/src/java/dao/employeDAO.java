/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import NoyauFonctionnel.Employes;
import NoyauFonctionnel.Userinfo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Paul
 */
@Stateless
public class employeDAO {

    @PersistenceContext(unitName = "RHelpPU")
    private EntityManager em;
    
    public List<Employes> getAll(){
        Query query = em.createNamedQuery("Employes.findAll");
        return query.getResultList();
    }
    
    public Employes getOneEmploye(String id){
        System.out.println("getOnEmp ok");
        Query query = em.createNamedQuery("Employes.findByNumSS").setParameter("numSS", id);
        System.out.println("query = "+query.toString());
        try{
            Employes var = (Employes) query.getSingleResult();
            return var;
        } catch (NoResultException e){
            System.err.println("pas d'employé avec cet id");
            return null;
        }
    }
    
    public void saveEmploye(Employes employe) {
        Userinfo newUser = new Userinfo();
        newUser.setMdp("rhelp");
        newUser.setUser(employe.getMail());
        newUser.setNumSS(employe);
        em.persist(newUser);
        em.persist(employe);
        em.flush();
    }
    
    public void remove(String idE){
        em.remove(getOneEmploye(idE));
        em.flush();
    }
    
    public void updateEmp(Employes e){
        em.merge(e);
        em.flush();
    }
}
