
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
    Connection cn = null;

    public ConsultaBase(String titol, String descripcio, String keywords, String autor, String datac, String datas) throws ClassNotFoundException, SQLException {
        this.titol = titol;
        this.descripcio = descripcio;
        this.keywords = keywords;
        this.autor = autor;
        this.datac = datac;
        this.datas = datas;
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        cn = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        formatData();
    }

    public List<imagenData> getImageData() throws SQLException {
        List<imagenData> bilers;
        bilers = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            boolean ok;

            String consulta = "SELECT * from imatges where titol like %" + titol + "%"
                    + " OR descripcio like %" + descripcio + "%"
                    + " OR keywords like %" + keywords + "%"
                    + " OR autor like %" + autor + "%"
                    + " OR datac like %" + datac + "%"
                    + " OR datas like %" + autor + "%"
                    + " OR datac like %" + datac + "%";

            statement = cn.prepareStatement(consulta);
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
            }
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignore) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return bilers;
    }

    private void formatData() {
        if (titol.equals("")) {
            titol = "#";
        }
        if (descripcio.equals("")) {
            descripcio = "#";
        }
        if (keywords.equals("")) {
            keywords = "#";
        }
        if (autor.equals("")) {
            autor = "#";
        }
        if (datac.equals("")) {
            datac = "#";
        }
        if (datas.equals("")) {
            datac = "#";
        }
    }
}
