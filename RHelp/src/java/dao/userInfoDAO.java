package dao;

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
public class userInfoDAO {

    @PersistenceContext(unitName = "RHelpPU")
    private EntityManager em;
    
    public List<Userinfo> getAll(){
        Query query = em.createNamedQuery("Userinfo.findAll");
        return query.getResultList();
    }
    
    public Userinfo getOneUserinfo(String user){
        Query query = em.createNamedQuery("Userinfo.findByUser").setParameter("user", user);
        try{
            Userinfo var = (Userinfo) query.getSingleResult();
            return var;
        } catch (NoResultException e){
            System.err.println("pas de user avec cet id");
            return null;
        }
    }
       
    
    public void updateUserInfo(Userinfo ui){
        em.merge(ui);
        em.flush();
    }
}
