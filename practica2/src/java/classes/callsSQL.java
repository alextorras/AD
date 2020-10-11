package classes;

import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
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
    
    public callsSQL(String database) throws ClassNotFoundException, SQLException {        
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection(database);
    }
    
    public int getID() throws SQLException {
            PreparedStatement getid = cn.prepareStatement("SELECT MAX(id) FROM image");
            ResultSet rs = getid.executeQuery();
            int id;
            if(rs.next()) {
                //System.out.println("fins aqui arriba al if");
                id = rs.getInt(1)+1;
            }
            else {
                //System.out.println("fins aqui arriba al else");
                id=0;
            }
            return id;
    }
    
    public void newImage(int id, String titol, String descripcio, String keywords, String autor, String datac, String nom) throws SQLException {
        String datas = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        PreparedStatement uploader = cn.prepareStatement("INSERT into IMAGE VALUES(?,?,?,?,?,?,?,?)");            
        uploader.setInt(1,id);
        uploader.setString(2,titol);
        uploader.setString(3,descripcio);
        uploader.setString(4,keywords);
        uploader.setString(5,autor);
        uploader.setString(6,datac);
        uploader.setString(7,datas);
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
            //System.out.println("Entra en la condicio");
            aux = rs.getString(1);
        }
        //System.out.println(aux);
        return aux;
    }
    
    
    public List<imagenData> listarImagenes() throws SQLException {
        List<imagenData> data = new ArrayList<imagenData>();
        PreparedStatement getImages = cn.prepareStatement("SELECT * FROM image ORDER BY creation_date DESC");
        ResultSet rs = getImages.executeQuery();  
        while (rs.next()) {
            imagenData imagen = new imagenData();
            imagen.setId(rs.getInt("id"));
            imagen.setTitol(rs.getString("title"));
            imagen.setDescripcio(rs.getString("description"));
            imagen.setKeywords(rs.getString("keywords"));
            imagen.setAutor(rs.getString("author"));
            imagen.setDatac(rs.getString("creation_date"));
            imagen.setDatas(rs.getString("storage_date"));
            imagen.setFilename(rs.getString("filename"));
            data.add(imagen);
        }
        return data;
    }
    

    public boolean eliminar_imagen(Integer aux) throws SQLException
    {
        if(aux == null) return false;
        String query = "delete from image where id = " + aux;
        PreparedStatement st = cn.prepareStatement(query);
        int num = st.executeUpdate();
        if(num == 0) return false;
        else return true;
    }
    
    public void updateImage( String titol, String descripcio, String keywords, String autor, String datac) throws SQLException {
            PreparedStatement uploader = cn.prepareStatement("UPDATE Imatges SET titol = ?, descripcio = ?,  keywords = ?,  autor = ?, datac = ? WHERE id = ?");            
            uploader.setString(1,titol);
            uploader.setString(2,descripcio);
            uploader.setString(3,keywords);
            uploader.setString(4,autor);
            uploader.setString(5,datac);

            uploader.executeUpdate();
    }
    

    public void cerrarConexion() throws SQLException {
        cn.close();
    }

}
