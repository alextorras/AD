import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Àlex
 */
public class callsSQL {
    Connection cn = null;
    
    callsSQL(String database) throws ClassNotFoundException, SQLException {        
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection(database);
    }
    
    public int getID() throws SQLException {
            PreparedStatement getid = cn.prepareStatement("SELECT MAX(id) FROM image");
            ResultSet rs = getid.executeQuery();
            int id;
            if(rs.next()) {
                System.out.println("fins aqui arriba al if");
                id = rs.getInt(1)+1;
            }
            else {
                System.out.println("fins aqui arriba al else");
                id=0;
            }
            return id;
    }
    
    public void newImage(int id, String titol, String descripcio, String keywords, String autor, String datac, String nom) throws SQLException {
            PreparedStatement uploader = cn.prepareStatement("INSERT into IMAGE VALUES(?,?,?,?,?,?,?,?)");            
            uploader.setInt(1,id);
            uploader.setString(2,titol);
            uploader.setString(3,descripcio);
            uploader.setString(4,keywords);
            uploader.setString(5,autor);
            uploader.setString(6,datac);
            uploader.setString(7,"");
            uploader.setString(8,nom);
            uploader.executeUpdate();
    }
    
    public boolean login(String usuario, String password) throws SQLException
    {
        String query = "select * from usuarios where id_usuario='" + usuario + "' and password='" + password + "'";
        PreparedStatement st = cn.prepareCall(query);
        ResultSet rs = st.executeQuery();
        
        return rs.next();
    }
    
    public String nom_eliminar_imagen(Integer id) throws SQLException
    {
        String query = "select filename from image where id =" + id;
        PreparedStatement st = cn.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        String aux = null;
        while(rs.next())
        {
            aux = rs.getString(1);
        }
        return aux;
    }
    
    public boolean eliminar_imagen(Integer aux) throws SQLException
    {
        if(aux == null) return false;
        String query = "delete from image where id = '" + aux + "'";
        PreparedStatement st = cn.prepareStatement(query);
        int num = st.executeUpdate();
        if(num == 0) return false;
        else return true;
    }
    
    
    
    
    
}
