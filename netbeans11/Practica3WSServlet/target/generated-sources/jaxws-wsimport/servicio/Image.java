
package servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para image complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="image">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="autor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contenido" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="datac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="keywords" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "image", propOrder = {
    "autor",
    "contenido",
    "datac",
    "datas",
    "descripcio",
    "filename",
    "id",
    "keywords",
    "titol"
})
public class Image {

    protected String autor;
    protected byte[] contenido;
    protected String datac;
    protected String datas;
    protected String descripcio;
    protected String filename;
    protected int id;
    protected String keywords;
    protected String titol;

    /**
     * Obtiene el valor de la propiedad autor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Define el valor de la propiedad autor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutor(String value) {
        this.autor = value;
    }

    /**
     * Obtiene el valor de la propiedad contenido.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getContenido() {
        return contenido;
    }

    /**
     * Define el valor de la propiedad contenido.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setContenido(byte[] value) {
        this.contenido = value;
    }

    /**
     * Obtiene el valor de la propiedad datac.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatac() {
        return datac;
    }

    /**
     * Define el valor de la propiedad datac.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatac(String value) {
        this.datac = value;
    }

    /**
     * Obtiene el valor de la propiedad datas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatas() {
        return datas;
    }

    /**
     * Define el valor de la propiedad datas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatas(String value) {
        this.datas = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Define el valor de la propiedad descripcio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcio(String value) {
        this.descripcio = value;
    }

    /**
     * Obtiene el valor de la propiedad filename.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Define el valor de la propiedad filename.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilename(String value) {
        this.filename = value;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad keywords.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * Define el valor de la propiedad keywords.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeywords(String value) {
        this.keywords = value;
    }

    /**
     * Obtiene el valor de la propiedad titol.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitol() {
        return titol;
    }

    /**
     * Define el valor de la propiedad titol.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitol(String value) {
        this.titol = value;
    }

}
