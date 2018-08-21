
package soen487.g4.sc.manufacturer2.service;

import soen487.g4.sc.manufacturer2.business.Product;
import soen487.g4.sc.manufacturer2.business.PurchaseOrder;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Web service for a manufacturer. 
 */
@WebService(serviceName = "ManufacturerWebService2")
public class ManufacturerWebService2 {

    public static final String MANUFACTURER_NAME = "Brand2"; // the name of the manufacturer as specified in Appendix A
    public static final int PRODUCTION_LIMIT = 100; // maximum number of units that can be produced at one time
    public static final String[] PRODUCT_TYPES = {"DVD player", "video camera", "TV"}; // product types produced by the manufacturer
    public static final String PO_FILE_PATH = "C:\\Users\\Yanan Chang\\Desktop\\General Folder\\Concordia\\2018 Winter\\SOEN487\\code\\purchase-orders2.xml"; // stores the purchase orders sent to the manufacturer
    public static final String PRODUCT_FILE_PATH = "C:\\Users\\Yanan Chang\\Desktop\\General Folder\\Concordia\\2018 Winter\\SOEN487\\code\\products2.xml"; // stores the information on the products offered by the manufacturer
    
    
    
    /**
     * default Constructor
     */
    public ManufacturerWebService2() {
        
    }
    
    /**
     * Web Service Operation 1: Receives and processes purchase orders sent by a warehouse's web service.
     * Verifies the unit price in the PO and if it is higher than the product unit price on file, initiates production of the ordered units.
     * For simplicity, currently uses the DOM parser to write to the XML file containing the purchase orders. Should be refactored to use SAX when performance becomes a concern.
     * @param aPO A purchase order from a warehouse.
     * @return True after the order is successfully processed.
     */
    @WebMethod(operationName = "processPurchaseOrder")
    public boolean processPurchaseOrder(@WebParam(name = "aPO") PurchaseOrder aPO) {
        // verify purchase order unit price
        String _productTypeOrdered = aPO.getProduct().getProductType();
        float _salePrice = aPO.getUnitPrice();       
        // get unit price (requiredPrice) for the corresponding product type from that product's XML file
        Product _productOrdered = getProductInfo(_productTypeOrdered);
        // compare the salePrice with the requiredPrice
        float _requiredPrice = _productOrdered.getUnitPrice();
        // if it's lower, return false
        if (_salePrice < _requiredPrice) {
            return false;
        } 
        // if it's greater, initiate production       
        int _quantity;
        if (aPO.getQuantity() > PRODUCTION_LIMIT) {
            int _maxOrders = aPO.getQuantity() / PRODUCTION_LIMIT; // number of full production runs to initiate
            for (int n = 0; n < _maxOrders; n++) {
                produce(_productTypeOrdered, PRODUCTION_LIMIT);
            }
            _quantity = aPO.getQuantity() % PRODUCTION_LIMIT; // number of remaining units to produce after finishing full production runs
        } else {
            _quantity = aPO.getQuantity();
        }
        produce(_productTypeOrdered, _quantity);           
        // record purchase order in an XML file
        Document _purchaseOrderList = openXMLFile(PO_FILE_PATH);
        // create the new purchase order element and append child elements for its attributes
        Element _rootElement = _purchaseOrderList.getDocumentElement();
        Element _newPurchaseOrder = _purchaseOrderList.createElement("purchaseorder");
        _newPurchaseOrder.setAttribute("num", aPO.getOrderNum());
        _newPurchaseOrder.appendChild(newPurchaseOrderNode(_purchaseOrderList, _newPurchaseOrder, "customerref", aPO.getCustomerRef()));
        _newPurchaseOrder.appendChild(newPurchaseOrderNode(_purchaseOrderList, _newPurchaseOrder, "product", _productTypeOrdered));
        _newPurchaseOrder.appendChild(newPurchaseOrderNode(_purchaseOrderList, _newPurchaseOrder, "quantity", Integer.toString(aPO.getQuantity())));
        _newPurchaseOrder.appendChild(newPurchaseOrderNode(_purchaseOrderList, _newPurchaseOrder, "unitprice", Float.toString(aPO.getUnitPrice())));
        _newPurchaseOrder.appendChild(newPurchaseOrderNode(_purchaseOrderList, _newPurchaseOrder, "paid", "false"));
        _rootElement.appendChild(_newPurchaseOrder);
        // write it to the purchase order XML file
        return updateXMLFile(_purchaseOrderList, PO_FILE_PATH);
    }

    /**
     * Web Service Operation 2: Retrieves the requested product's information from the corresponding product XML file and returns it in a Product object.
     * Currently uses the DOM parser to retrieve the product info. Should be refactored to use SAX when performance becomes a concern.
     * @param aProdName Should be "DVD player", "video camera" or "TV".
     * @return The requested product's information in a Product object or null
     */
    @WebMethod(operationName = "getProductInfo")
    public Product getProductInfo(@WebParam(name = "aProdName") String aProdName) {
        if (isValidProductType(aProdName)) {
            // find element for requested product type in the XML
            Document _productList = openXMLFile(PRODUCT_FILE_PATH);
            NodeList _productNodes = _productList.getElementsByTagName("product");
            for (int i = 0; i < _productNodes.getLength(); i++) {
                Node _productNode = _productNodes.item(i);
                Element _product = (Element)_productNode;
                if (_product.getAttribute("type").equals(aProdName)) {
                    // read the element's info
                    String manufacturerName = _product.getElementsByTagName("manufacturer").item(0).getTextContent();
                    String productType = _product.getAttribute("type");
                    String unitPrice = _product.getElementsByTagName("unitprice").item(0).getTextContent();
                    // create and return a Product object containing the element's info
                    return new Product(manufacturerName, productType, Float.parseFloat(unitPrice));  
                }
            }     
        }
        return null; // if the product type is invalid or it isn't found in the XML file
    }

    /**
     * Web Service Operation 3: Marks a purchase order as "paid".
     * Note: I initially misspelled param orderNum as orderNam, so if I run into issues later, check there.
     * @param orderNum The number of the purchase order that was paid.
     * @param totalPrice The payment received.
     * @return True if a matching purchase order was found. 
     */
    @WebMethod(operationName = "receivePayment")
    public boolean receivePayment(@WebParam(name = "orderNum") String orderNum, @WebParam(name = "totalPrice") float totalPrice) {
        // search the XML containing the purchase orders for a matching order number
        Document _purchaseOrderList = openXMLFile(PO_FILE_PATH);
        NodeList _purchaseOrderNodes = _purchaseOrderList.getElementsByTagName("purchaseorder");
            for (int i = 0; i < _purchaseOrderNodes.getLength(); i++) {
                Node _purchaseOrderNode = _purchaseOrderNodes.item(i);
                Element _purchaseOrder = (Element)_purchaseOrderNode;
                // if one is found, read the purchase order's quantity and unit price, then multiply them and compare with the totalPrice
                if (_purchaseOrder.getAttribute("num").equals(orderNum)) {
                    String _quantity = _purchaseOrder.getElementsByTagName("quantity").item(0).getTextContent();
                    String _unitPrice = _purchaseOrder.getElementsByTagName("unitprice").item(0).getTextContent();
                    float _parsedTotalPrice = Integer.parseInt(_quantity) * Float.parseFloat(_unitPrice);
                    // if they're the same, mark the purchase order as paid in the XML file, then return true
                    if (_parsedTotalPrice == totalPrice) {
                        Node _paidNode = _purchaseOrder.getElementsByTagName("paid").item(0);
                        _paidNode.setTextContent("true");
                        return updateXMLFile(_purchaseOrderList, PO_FILE_PATH);                   
                    }
                }
            }    
        return false; // when the orderNum isn't found or the totalPrice doesn't match
    }

    /**
     * Simulates production. 
     * @param productType The type of product to produce.
     * @param quantity Number of units to produce.
     * @return True if the specified Product is produced by the manufacturer and the quantity is less than the production limit.
     */
    private boolean produce(String productType, int quantity) {        
        if (isValidProductType(productType) && quantity <= PRODUCTION_LIMIT) {
            System.out.println(quantity + " " + productType + "s produced");
            return true;
        } else {
            return false;
        }
    }
       
    /**
     * Checks if a specified product type is one of the product types offered by the manufacturer, which are stored in the array PRODUCT_TYPES.
     * @param specifiedProductType The specified product type.
     * @return True if the specified product type is one of the product types offered by the manufacturer.
     */
    private boolean isValidProductType(String specifiedProductType) {
        for (String validProductType : PRODUCT_TYPES) {
            if (specifiedProductType.equals(validProductType)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Opens the specified XML file, which serves as a database table for the manufacturer, and parses it into a Document.
     * @param productFile Opens the product info file when true or the purchase order file when false.
     * @return Document object that can be read using the DOM parser.
     */
    private Document openXMLFile(String xmlFilePath) {  
        DocumentBuilderFactory _dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder _dBuilder;
        Document _xmlDocument = null;
        try {
            _dBuilder = _dbFactory.newDocumentBuilder();
            _xmlDocument = _dBuilder.parse(xmlFilePath);
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            System.err.println("Fatal error: Couldn't parse XML file containing product or purchase order data.");
        }
        return _xmlDocument;
    }
    
    /**
     * Creates the nodes for each of a purchase order's attributes.
     * Used by the processPurchaseOrder method when adding a PO to the XML file containing all the POs.
     * @param purchaseOrderList The XML file containing the purchase orders as a Document.
     * @param purchaseOrderElement A "purchaseorder" element.
     * @param name The tag name of the new element (<customerref>, <product>, <quantity>, <unitprice>, <paid>, etc).
     * @param content The text content of the new element.
     * @return A new Node containing the specified tags and content. 
     */
    private Node newPurchaseOrderNode(Document purchaseOrderList, Element purchaseOrderElement, String tagName, String content) {
        Element _newNode = purchaseOrderList.createElement(tagName);
        _newNode.appendChild(purchaseOrderList.createTextNode(content));
        return _newNode;
    }
    
    /**
     * Replaces the content of the purchase-orders.xml file after changes were made to the copy in memory (Document object) by the DOM parser.
     * @param newXMLFile The modified copy of the content purchase-orders.xml.
     * @return True if the file was updated without triggering any exceptions.
     */
    private boolean updateXMLFile(Document newXMLFile, String oldXMLFilePath) {
        TransformerFactory _cybertron = TransformerFactory.newInstance();
        Transformer _megatron = null;
        try {
            _megatron = _cybertron.newTransformer();
            _megatron.setOutputProperty(OutputKeys.INDENT, "yes"); // puts each tag on its own line
            DOMSource _source = new DOMSource(newXMLFile);
            StreamResult _file = new StreamResult(new File(oldXMLFilePath));
            _megatron.transform(_source, _file);
            return true;
        } catch (IllegalArgumentException | TransformerException ex) {
            System.err.println("Error: Couldn't update XML file.");
        }
        return false;
    }
    
     
}
