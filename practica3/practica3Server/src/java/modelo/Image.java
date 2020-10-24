/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


//import javax.servlet.http.Part;

/**
 *
 * @author Dani
 */
public class Image {
    private String titol;
    private String descripcio;
    private String keywords;
    private String autor;
    private String datac;
    private String datas;
    private String filename;
    private int id;
    private boolean proc;
    
    public void Image() {
        titol = null;
        descripcio = null;
        keywords = null;
        autor = null;
        datac = null;
        datas = null;
        filename = null;
        id = -1;
        proc = false;
        
    }
    
    

    /**
     * @return the titol
     */
    public String getTitol() {
        return titol;
    }

    /**
     * @param titol the titol to set
     */
    public void setTitol(String titol) {
        this.titol = titol;
    }

    /**
     * @return the descripcio
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * @param descripcio the descripcio to set
     */
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    /**
     * @return the keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the datac
     */
    public String getDatac() {
        return datac;
    }

    /**
     * @param datac the datac to set
     */
    public void setDatac(String datac) {
        this.datac = datac;
    }

    /**
     * @return the datas
     */
    public String getDatas() {
        return datas;
    }

    /**
     * @param datas the datas to set
     */
    public void setDatas(String datas) {
        this.datas = datas;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the proc
     */
    public boolean isProc() {
        return proc;
    }

    /**
     * @param proc the proc to set
     */
    public void setProc(boolean proc) {
        this.proc = proc;
    }
    
    
}