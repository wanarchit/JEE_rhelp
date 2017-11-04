package dao;
import java.sql.*;
  
public class UserDAO {
    
    public static boolean updatepwd(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "update userinfo set mdp= ? where user= ? ");
            ps.setString(1, password);
            ps.setString(2, user);
            int rs = ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Mise a jour du mot de passe : erreur -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }
    
    
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