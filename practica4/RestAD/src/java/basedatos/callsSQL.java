/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.util.Date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import modelo.Image;




/**
 *
 * @author admin
 */
public class callsSQL {
    Connection cn = null;
    
    public callsSQL(String database) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection(database);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getID() throws SQLException {
            PreparedStatement getid = cn.prepareStatement("SELECT MAX(id) FROM image");
            ResultSet rs = getid.executeQuery();
            int id;
            if(rs.next()) {                
                id = rs.getInt(1)+1;
            }
            else {
                id=0;
            }
            return id;
    }
    
    public boolean newImage(int id, String titol, String descripcio, String keywords, String autor, String datac, String nom) throws SQLException {

            String datas = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

            PreparedStatement uploader = cn.prepareStatement("INSERT into IMAGE VALUES(?,?,?,?,?,?,?,?)");            
            uploader.setInt(1,id);
            uploader.setString(2,titol);
            uploader.setString(3,descripcio);
            uploader.setString(4,keywords);
            uploader.setString(5,autor);
            uploader.setString(6,datac);
            uploader.setString(7,datas);
            uploader.setString(8,nom);
            int count = uploader.executeUpdate();
            if(count > 0 ) return true;
            else return false;
    }
    

    public boolean login(String usuario, String password) throws SQLException
    {
        String query = "select * from usuarios where id_usuario='" + usuario + "' and password='" + password + "'";
        System.out.println(query);
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
    public Image buscarImagenId(int id) throws SQLException {
        
        
        ResultSet rs = null;
        PreparedStatement statement = null;
         Image biler = new Image();
        //try {
            boolean ok;
            String consulta = "SELECT * from IMAGE where id = ?";

           statement = cn.prepareStatement(consulta);
            statement.setInt(1,id);
            
            rs = statement.executeQuery();
            if (rs.next()) {
               
                biler.setId(rs.getInt("id"));
                biler.setTitol(rs.getString("title"));
                biler.setDescripcio(rs.getString("description"));
                biler.setKeywords(rs.getString("keywords"));
                biler.setAutor(rs.getString("author"));
                biler.setDatac(rs.getString("creation_date"));
                biler.setDatas(rs.getString("storage_date"));
                biler.setFilename(rs.getString("filename"));
                
                //
            }
            return biler;
        }

    
    public List<Image> buscarImagen(String titol, String descripcio, String keywords, String autor, String datac, String datas, String filename) throws SQLException {
        List<Image> bilers;
        bilers = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement statement = null;
        
        //try {
            boolean ok;
            String consulta = "SELECT * from IMAGE where (title like '%'||?||'%')"
                    + " OR (description like '%'||?||'%')"
                    + " OR (keywords like '%'||?||'%')"
                    + " OR (author like '%'||?||'%')"
                    + " OR (creation_date like '%'||?||'%')"
                    + " OR (storage_date like '%'||?||'%')"
                    + " OR (filename like '%'||?||'%')";

            statement = cn.prepareStatement(consulta);
            statement.setString(1,titol);
            statement.setString(2, descripcio);
            statement.setString(3,keywords);
            statement.setString(4,autor);
            statement.setString(5,datac);
            statement.setString(6,datas);
            statement.setString(7,filename);
            rs = statement.executeQuery();
            
            while (rs.next()) {
                Image biler = new Image();
                biler.setId(rs.getInt("id"));
                biler.setTitol(rs.getString("title"));
                biler.setDescripcio(rs.getString("description"));
                biler.setKeywords(rs.getString("keywords"));
                biler.setAutor(rs.getString("author"));
                biler.setDatac(rs.getString("creation_date"));
                biler.setDatas(rs.getString("storage_date"));
                biler.setFilename(rs.getString("filename"));
                ok = bilers.add(biler);
                //
            }
            return bilers;
        }

    
    public List<Image> listarImagenes() throws SQLException {
        List<Image> data = new ArrayList<Image>();
        PreparedStatement getImages = cn.prepareStatement("SELECT * FROM image ORDER BY creation_date DESC");
        ResultSet rs = getImages.executeQuery();  
        while (rs.next()) {
            Image imagen = new Image();
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
    
    public void updateImage(String titol, String descripcio, String keywords, String autor, String datac, int id) throws SQLException {
            PreparedStatement uploader = cn.prepareStatement("UPDATE IMAGE SET title = ?, description = ?,  keywords = ?,  author = ?, creation_date = ? WHERE id = ?");            
            uploader.setString(1,titol);
            uploader.setString(2,descripcio);
            uploader.setString(3,keywords);
            uploader.setString(4,autor);
            uploader.setString(5,datac);
            uploader.setInt(6, id);

            uploader.executeUpdate();
    }
    
    public boolean existeix(String usuario) throws SQLException {
        String aux = usuario;
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
