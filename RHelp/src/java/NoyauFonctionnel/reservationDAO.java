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
public class reservationDAO {
    @PersistenceContext(unitName = "RHelpPU")
    private EntityManager em;
    
    public List<Reservations> getAll(){
        Query query = em.createNamedQuery("Reservations.findAll");
        return query.getResultList();
    }
    
    public Reservations getOneReservation(int id){
        Query query = em.createNamedQuery("Reservations.findByIdResa").setParameter("idResa", id);
        try{
            return (Reservations) query.getSingleResult();
        } catch (NoResultException e){
            System.err.println("pas de réservation avec cet id");
            return null;
        }
    }
    
    public void saveReservation(Reservations resa) {
        em.persist(resa);
        em.flush();
    }
    
    public void remove(int idR){
        em.remove(getOneReservation(idR));
        em.flush();
    }
    
    public void updateResa(Reservations resa){
        em.merge(resa);
        em.flush();
    }
}
