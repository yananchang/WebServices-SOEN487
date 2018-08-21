
package soen487.g4.sc.manufacturer.service;

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
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ManufacturerWebService", targetNamespace = "http://service.manufacturer.sc.g4.soen487/", wsdlLocation = "http://localhost:8080/Manufacturer/ManufacturerWebService?wsdl")
public class ManufacturerWebService_Service
    extends Service
{

    private final static URL MANUFACTURERWEBSERVICE_WSDL_LOCATION;
    private final static WebServiceException MANUFACTURERWEBSERVICE_EXCEPTION;
    private final static QName MANUFACTURERWEBSERVICE_QNAME = new QName("http://service.manufacturer.sc.g4.soen487/", "ManufacturerWebService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/Manufacturer/ManufacturerWebService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MANUFACTURERWEBSERVICE_WSDL_LOCATION = url;
        MANUFACTURERWEBSERVICE_EXCEPTION = e;
    }

    public ManufacturerWebService_Service() {
        super(__getWsdlLocation(), MANUFACTURERWEBSERVICE_QNAME);
    }

    public ManufacturerWebService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), MANUFACTURERWEBSERVICE_QNAME, features);
    }

    public ManufacturerWebService_Service(URL wsdlLocation) {
        super(wsdlLocation, MANUFACTURERWEBSERVICE_QNAME);
    }

    public ManufacturerWebService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MANUFACTURERWEBSERVICE_QNAME, features);
    }

    public ManufacturerWebService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ManufacturerWebService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ManufacturerWebService
     */
    @WebEndpoint(name = "ManufacturerWebServicePort")
    public ManufacturerWebService getManufacturerWebServicePort() {
        return super.getPort(new QName("http://service.manufacturer.sc.g4.soen487/", "ManufacturerWebServicePort"), ManufacturerWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ManufacturerWebService
     */
    @WebEndpoint(name = "ManufacturerWebServicePort")
    public ManufacturerWebService getManufacturerWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.manufacturer.sc.g4.soen487/", "ManufacturerWebServicePort"), ManufacturerWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MANUFACTURERWEBSERVICE_EXCEPTION!= null) {
            throw MANUFACTURERWEBSERVICE_EXCEPTION;
        }
        return MANUFACTURERWEBSERVICE_WSDL_LOCATION;
    }

}