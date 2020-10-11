
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pellax
 */
public class ConsultaBase {

    String titol;
    String descripcio;
    String keywords;
    String autor;
    String datac;
    String datas;
    String filename;
    Connection cn = null;

    public ConsultaBase(String titol, String descripcio, String keywords, String autor, String datac, String datas,String filename) throws ClassNotFoundException, SQLException {
        this.titol = titol;
        this.descripcio = descripcio;
        this.keywords = keywords;
        this.autor = autor;
        this.datac = datac;
        this.datas = datas;
        this.filename = filename;
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        cn = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        formatData();
    }

    public List<imagenData> getImageData() throws SQLException {
        List<imagenData> bilers;
        bilers = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement statement = null;
        
        //try {
            boolean ok;
            String consulta = "SELECT * from IMAGE where title like CONCAT('%',?,'%')"
                    + " OR description like CONCAT('%',?,'%')"
                    + " OR keywords like CONCAT('%',?,'%')"
                    + " OR author like CONCAT('%',?,'%')"
                    + " OR creation_date like CONCAT('%',?,'%')"
                    + " OR storage_date like CONCAT('%',?,'%')"
                    + " OR filename like CONCAT('%',?,'%')";

           /* String consulta = "SELECT * from IMAGE where title like ?"
                    + " OR description like ?"
                    + " OR keywords like ?"
                    + " OR author like ?"
                    + " OR creation_date like' ?"
                    + " OR storage_date like ?"
                    + " OR filename like  ? {escape '!'}";

            titol = titol
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
            descripcio = descripcio
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
            keywords = keywords
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
            autor = autor
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
            datac = datac
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
            datas = datas
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
            filename = filename
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
            
            statement = cn.prepareStatement(consulta);
            statement.setString(1,"%" + titol + "%");
            statement.setString(2,"%" + descripcio+"%");
            statement.setString(3, "%" +keywords +"%");
            statement.setString(4,"%" + autor +"%");
            statement.setString(5,"%" +datac + "%");
            statement.setString(6,"%" + datas + "%");
            statement.setString(7,"%" + filename + "%");
            */
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
                imagenData biler = new imagenData();
                biler.setId(rs.getInt("id"));
                biler.setTitol(rs.getString("title"));
                biler.setDescripcio(rs.getString("description"));
                biler.setKeywords(rs.getString("keywords"));
                biler.setAutor(rs.getString("author"));
                biler.setDatac(rs.getString("creation_date"));
                biler.setDatas(rs.getString("storage_date"));
                biler.setFilename(rs.getString("filename"));
                ok = bilers.add(biler);
                biler.printData();
            }
            return bilers;
        }
    
   /* catch (SQLException e) 
    {      
            throw new SQLException();
    }
  
    
        finally {
            if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ignore) {
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } 
            catch (SQLException ignore) {
            }
        }
        if (cn != null) {
            try {
                cn.close();
            } catch (SQLException ignore) {
            }
        }
    
        }
    }*/
    private void formatData() 
    {
        if (titol.equals("")) {
            titol = "'#'";
        }
        if (descripcio.equals("")) {
            descripcio = "'#'";
        }
        if (keywords.equals("")) {
            keywords = "'#'";
        }
        if (autor.equals("")) {
            autor = "'#'";
        }
        if (datac.equals("")) {
            datac = "'#'";
        }
        if (datas.equals("")) {
            datac = "'#'";
        }
         if (filename.equals("")) {
            filename = "'#'";
        }
    }
}
