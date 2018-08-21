
package soen487.g4.sc.manufacturer3.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProductInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getProductInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aProdName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProductInfo", propOrder = {
    "aProdName"
})
public class GetProductInfo {

    protected String aProdName;

    /**
     * Gets the value of the aProdName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAProdName() {
        return aProdName;
    }

    /**
     * Sets the value of the aProdName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAProdName(String value) {
        this.aProdName = value;
    }

}
