package classes;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro Capella
 */
public class imagenData {

    String titol;
    String descripcio;
    String keywords;
    String autor;
    String datac;
    String datas;
    String filename;
    int id;

    public imagenData() {
        titol = null;
        descripcio = null;
        keywords = null;
        autor = null;
        datac = null;
        datas = null;
        filename = null;
        id = -1;
    }

    public String getTitol() {
        return titol;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getAutor() {
        return autor;
    }

    public String getDatac() {
        return datac;
    }

    public String getDatas() {
        return datas;
    }

    public int getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setDatac(String datac) {
        this.datac = datac;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void printData() {
        System.out.println("Primer elemento" + titol);
        System.out.println("Primer elemento" + descripcio);
        System.out.println("Primer elemento" + autor);
        System.out.println("Primer elemento" + keywords);
        System.out.println("Primer elemento" + datac);
        System.out.println("Primer elemento" + datas);
        System.out.println("Primer elemento" + filename);

    }
}
