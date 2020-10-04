
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            String autor ;
            String datac ;
            String datas;
            Connection cn = null;
public ConsultaBase(String titol,String descripcio,String keywords,String autor,String datac,String datas) throws ClassNotFoundException, SQLException
{
            this.titol = titol;
            this.descripcio = descripcio;
            this.keywords = keywords;
            this.autor = autor ;
            this.datac = datac;
            this.datas = datas;
             Class.forName("org.apache.derby.jdbc.ClientDriver");
            cn = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");

}

public LinkedList<> getImageData()
{
    String consulta ="SELECT from imatges where"++;
}
public getRowFromId(int id)
{
    
    
}
}
