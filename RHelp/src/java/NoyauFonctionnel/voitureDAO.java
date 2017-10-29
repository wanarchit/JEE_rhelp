/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NoyauFonctionnel;

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
public class voitureDAO {
    @PersistenceContext(unitName = "RHelpPU")
    private EntityManager em;
    
    public List<Voiture> getAll(){
        Query query = em.createNamedQuery("Voiture.findAll");
        return query.getResultList();
    }
    
    public Voiture getOneVoiture(String id){
        Query query = em.createNamedQuery("Voiture.findByPlaque").setParameter("plaque", id);
        try{
            return (Voiture) query.getSingleResult();
        } catch (NoResultException e){
            System.err.println("pas de voiture avec cet id");
            return null;
        }
    }
    
    public void saveVoiture(Voiture voiture) {
        em.persist(voiture);
        em.flush();
    }
    
    public void remove(String idV){
        em.remove(getOneVoiture(idV));
        em.flush();
    }
    
    public void updateVoi(Voiture voi){
        em.merge(voi);
        em.flush();
    }
}
