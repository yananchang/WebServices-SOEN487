
package soen487.g4.sc.manufacturer.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ManufacturerWebService", targetNamespace = "http://service.manufacturer.sc.g4.soen487/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ManufacturerWebService {


    /**
     * 
     * @param totalPrice
     * @param orderNum
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "receivePayment", targetNamespace = "http://service.manufacturer.sc.g4.soen487/", className = "soen487.g4.sc.manufacturer.service.ReceivePayment")
    @ResponseWrapper(localName = "receivePaymentResponse", targetNamespace = "http://service.manufacturer.sc.g4.soen487/", className = "soen487.g4.sc.manufacturer.service.ReceivePaymentResponse")
    @Action(input = "http://service.manufacturer.sc.g4.soen487/ManufacturerWebService/receivePaymentRequest", output = "http://service.manufacturer.sc.g4.soen487/ManufacturerWebService/receivePaymentResponse")
    public boolean receivePayment(
        @WebParam(name = "orderNum", targetNamespace = "")
        String orderNum,
        @WebParam(name = "totalPrice", targetNamespace = "")
        float totalPrice);

    /**
     * 
     * @param aProdName
     * @return
     *     returns soen487.g4.sc.manufacturer.service.Product
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getProductInfo", targetNamespace = "http://service.manufacturer.sc.g4.soen487/", className = "soen487.g4.sc.manufacturer.service.GetProductInfo")
    @ResponseWrapper(localName = "getProductInfoResponse", targetNamespace = "http://service.manufacturer.sc.g4.soen487/", className = "soen487.g4.sc.manufacturer.service.GetProductInfoResponse")
    @Action(input = "http://service.manufacturer.sc.g4.soen487/ManufacturerWebService/getProductInfoRequest", output = "http://service.manufacturer.sc.g4.soen487/ManufacturerWebService/getProductInfoResponse")
    public Product getProductInfo(
        @WebParam(name = "aProdName", targetNamespace = "")
        String aProdName);

    /**
     * 
     * @param aPO
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "processPurchaseOrder", targetNamespace = "http://service.manufacturer.sc.g4.soen487/", className = "soen487.g4.sc.manufacturer.service.ProcessPurchaseOrder")
    @ResponseWrapper(localName = "processPurchaseOrderResponse", targetNamespace = "http://service.manufacturer.sc.g4.soen487/", className = "soen487.g4.sc.manufacturer.service.ProcessPurchaseOrderResponse")
    @Action(input = "http://service.manufacturer.sc.g4.soen487/ManufacturerWebService/processPurchaseOrderRequest", output = "http://service.manufacturer.sc.g4.soen487/ManufacturerWebService/processPurchaseOrderResponse")
    public boolean processPurchaseOrder(
        @WebParam(name = "aPO", targetNamespace = "")
        PurchaseOrder aPO);

}
