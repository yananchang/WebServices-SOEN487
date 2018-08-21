
package soen487.g4.sc.manufacturer2.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ManufacturerWebService2", targetNamespace = "http://service.manufacturer2.sc.g4.soen487/", wsdlLocation = "http://localhost:8080/Manufacturer2/ManufacturerWebService2?wsdl")
public class ManufacturerWebService2_Service
    extends Service
{

    private final static URL MANUFACTURERWEBSERVICE2_WSDL_LOCATION;
    private final static WebServiceException MANUFACTURERWEBSERVICE2_EXCEPTION;
    private final static QName MANUFACTURERWEBSERVICE2_QNAME = new QName("http://service.manufacturer2.sc.g4.soen487/", "ManufacturerWebService2");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/Manufacturer2/ManufacturerWebService2?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MANUFACTURERWEBSERVICE2_WSDL_LOCATION = url;
        MANUFACTURERWEBSERVICE2_EXCEPTION = e;
    }

    public ManufacturerWebService2_Service() {
        super(__getWsdlLocation(), MANUFACTURERWEBSERVICE2_QNAME);
    }

    public ManufacturerWebService2_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), MANUFACTURERWEBSERVICE2_QNAME, features);
    }

    public ManufacturerWebService2_Service(URL wsdlLocation) {
        super(wsdlLocation, MANUFACTURERWEBSERVICE2_QNAME);
    }

    public ManufacturerWebService2_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MANUFACTURERWEBSERVICE2_QNAME, features);
    }

    public ManufacturerWebService2_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ManufacturerWebService2_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ManufacturerWebService2
     */
    @WebEndpoint(name = "ManufacturerWebService2Port")
    public ManufacturerWebService2 getManufacturerWebService2Port() {
        return super.getPort(new QName("http://service.manufacturer2.sc.g4.soen487/", "ManufacturerWebService2Port"), ManufacturerWebService2.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ManufacturerWebService2
     */
    @WebEndpoint(name = "ManufacturerWebService2Port")
    public ManufacturerWebService2 getManufacturerWebService2Port(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.manufacturer2.sc.g4.soen487/", "ManufacturerWebService2Port"), ManufacturerWebService2.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MANUFACTURERWEBSERVICE2_EXCEPTION!= null) {
            throw MANUFACTURERWEBSERVICE2_EXCEPTION;
        }
        return MANUFACTURERWEBSERVICE2_WSDL_LOCATION;
    }

}