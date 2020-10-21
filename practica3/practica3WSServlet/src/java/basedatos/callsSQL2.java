/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author admin
 */
public class callsSQL2 {
    
    Connection cn = null;
    
    public callsSQL2(String database) throws ClassNotFoundException, SQLException {        
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection(database);
    }
    
    public boolean login(String usuario, String password) throws SQLException
    {
        String query = "select * from usuarios where id_usuario='" + usuario + "' and password='" + password + "'";
        PreparedStatement st = cn.prepareCall(query);
        ResultSet rs = st.executeQuery();
        
        return rs.next();
    }
    
    public void cerrarConexion() throws SQLException {
        cn.close();
    }
    
}
