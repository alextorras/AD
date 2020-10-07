
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
}
