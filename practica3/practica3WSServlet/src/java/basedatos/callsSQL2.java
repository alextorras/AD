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
    
    public boolean existeix(String usuario) throws SQLException {
        String aux = usuario;
        System.out.println("Entro aqui");
        String query = "select * from usuarios where id_usuario='" + aux + "'";
        System.out.println(query);
        PreparedStatement st = cn.prepareCall(query);
        ResultSet rs = st.executeQuery();
        return rs.next();
    }
    
    public boolean newUser(String usuario, String passwd) throws SQLException {
        String aux1 = usuario;
        String aux2 = passwd;
        PreparedStatement st = cn.prepareStatement("insert into usuarios VALUES(?,?)");
        st.setString(1, aux1);
        st.setString(2, aux2);
        int count = st.executeUpdate();
        if(count > 0) return true;
        else return false;
    }
    
    public void cerrarConexion() throws SQLException {
        cn.close();
    }
    
}
