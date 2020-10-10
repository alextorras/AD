package classes;
<<<<<<< HEAD


import java.util.Date;
=======
>>>>>>> b3635aa05092e57315e11f689877be11f4645b4f
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
=======
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
>>>>>>> b3635aa05092e57315e11f689877be11f4645b4f

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
    
<<<<<<< HEAD
    public callsSQL(String database) throws ClassNotFoundException, SQLException {        
=======
    callsSQL(String database) throws ClassNotFoundException, SQLException {        
>>>>>>> b3635aa05092e57315e11f689877be11f4645b4f
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
<<<<<<< HEAD
            String datas = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
=======
>>>>>>> b3635aa05092e57315e11f689877be11f4645b4f
            PreparedStatement uploader = cn.prepareStatement("INSERT into IMAGE VALUES(?,?,?,?,?,?,?,?)");            
            uploader.setInt(1,id);
            uploader.setString(2,titol);
            uploader.setString(3,descripcio);
            uploader.setString(4,keywords);
            uploader.setString(5,autor);
            uploader.setString(6,datac);
<<<<<<< HEAD
            uploader.setString(7,datas);
=======
            uploader.setString(7,"");
>>>>>>> b3635aa05092e57315e11f689877be11f4645b4f
            uploader.setString(8,nom);
            uploader.executeUpdate();
    }
    
<<<<<<< HEAD
=======
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
    
>>>>>>> b3635aa05092e57315e11f689877be11f4645b4f
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
    
<<<<<<< HEAD
        public void updateImage( String titol, String descripcio, String keywords, String autor, String datac) throws SQLException {
=======
    
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
>>>>>>> b3635aa05092e57315e11f689877be11f4645b4f
            PreparedStatement uploader = cn.prepareStatement("UPDATE Imatges SET titol = ?, descripcio = ?,  keywords = ?,  autor = ?, datac = ? WHERE id = ?");            
            uploader.setString(1,titol);
            uploader.setString(2,descripcio);
            uploader.setString(3,keywords);
            uploader.setString(4,autor);
            uploader.setString(5,datac);

            uploader.executeUpdate();
    }
    
<<<<<<< HEAD
    public void cerrarConexion() throws SQLException {
        cn.close();
    }
=======
    
    public void cerrarConexion() throws SQLException {
        cn.close();
    }
   
    
    
    
    
    
>>>>>>> b3635aa05092e57315e11f689877be11f4645b4f
}
