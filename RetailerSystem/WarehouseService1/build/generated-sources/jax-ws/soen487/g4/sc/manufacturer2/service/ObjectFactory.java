
package soen487.g4.sc.manufacturer2.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soen487.g4.sc.manufacturer2.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ProcessPurchaseOrderResponse_QNAME = new QName("http://service.manufacturer2.sc.g4.soen487/", "processPurchaseOrderResponse");
    private final static QName _GetProductInfoResponse_QNAME = new QName("http://service.manufacturer2.sc.g4.soen487/", "getProductInfoResponse");
    private final static QName _GetProductInfo_QNAME = new QName("http://service.manufacturer2.sc.g4.soen487/", "getProductInfo");
    private final static QName _ProcessPurchaseOrder_QNAME = new QName("http://service.manufacturer2.sc.g4.soen487/", "processPurchaseOrder");
    private final static QName _ReceivePayment_QNAME = new QName("http://service.manufacturer2.sc.g4.soen487/", "receivePayment");
    private final static QName _ReceivePaymentResponse_QNAME = new QName("http://service.manufacturer2.sc.g4.soen487/", "receivePaymentResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soen487.g4.sc.manufacturer2.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProductInfoResponse }
     * 
     */
    public GetProductInfoResponse createGetProductInfoResponse() {
        return new GetProductInfoResponse();
    }

    /**
     * Create an instance of {@link ProcessPurchaseOrderResponse }
     * 
     */
    public ProcessPurchaseOrderResponse createProcessPurchaseOrderResponse() {
        return new ProcessPurchaseOrderResponse();
    }

    /**
     * Create an instance of {@link ReceivePaymentResponse }
     * 
     */
    public ReceivePaymentResponse createReceivePaymentResponse() {
        return new ReceivePaymentResponse();
    }

    /**
     * Create an instance of {@link ReceivePayment }
     * 
     */
    public ReceivePayment createReceivePayment() {
        return new ReceivePayment();
    }

    /**
     * Create an instance of {@link ProcessPurchaseOrder }
     * 
     */
    public ProcessPurchaseOrder createProcessPurchaseOrder() {
        return new ProcessPurchaseOrder();
    }

    /**
     * Create an instance of {@link GetProductInfo }
     * 
     */
    public GetProductInfo createGetProductInfo() {
        return new GetProductInfo();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link PurchaseOrder }
     * 
     */
    public PurchaseOrder createPurchaseOrder() {
        return new PurchaseOrder();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessPurchaseOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.manufacturer2.sc.g4.soen487/", name = "processPurchaseOrderResponse")
    public JAXBElement<ProcessPurchaseOrderResponse> createProcessPurchaseOrderResponse(ProcessPurchaseOrderResponse value) {
        return new JAXBElement<ProcessPurchaseOrderResponse>(_ProcessPurchaseOrderResponse_QNAME, ProcessPurchaseOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.manufacturer2.sc.g4.soen487/", name = "getProductInfoResponse")
    public JAXBElement<GetProductInfoResponse> createGetProductInfoResponse(GetProductInfoResponse value) {
        return new JAXBElement<GetProductInfoResponse>(_GetProductInfoResponse_QNAME, GetProductInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.manufacturer2.sc.g4.soen487/", name = "getProductInfo")
    public JAXBElement<GetProductInfo> createGetProductInfo(GetProductInfo value) {
        return new JAXBElement<GetProductInfo>(_GetProductInfo_QNAME, GetProductInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessPurchaseOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.manufacturer2.sc.g4.soen487/", name = "processPurchaseOrder")
    public JAXBElement<ProcessPurchaseOrder> createProcessPurchaseOrder(ProcessPurchaseOrder value) {
        return new JAXBElement<ProcessPurchaseOrder>(_ProcessPurchaseOrder_QNAME, ProcessPurchaseOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceivePayment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.manufacturer2.sc.g4.soen487/", name = "receivePayment")
    public JAXBElement<ReceivePayment> createReceivePayment(ReceivePayment value) {
        return new JAXBElement<ReceivePayment>(_ReceivePayment_QNAME, ReceivePayment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceivePaymentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.manufacturer2.sc.g4.soen487/", name = "receivePaymentResponse")
    public JAXBElement<ReceivePaymentResponse> createReceivePaymentResponse(ReceivePaymentResponse value) {
        return new JAXBElement<ReceivePaymentResponse>(_ReceivePaymentResponse_QNAME, ReceivePaymentResponse.class, null, value);
    }

}
