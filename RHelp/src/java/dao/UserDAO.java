package dao;
import NoyauFonctionnel.Userinfo;
import java.sql.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
  
public class UserDAO {
    
    @PersistenceContext(unitName = "RHelpPU")
    private EntityManager em;
    
     public static boolean login(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select user, mdp from userinfo where user= ? and mdp= ? ");
            ps.setString(1, user);
            ps.setString(2, password);
  
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                System.out.println(rs.getString("user"));
                return true;
            }
            else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }
}