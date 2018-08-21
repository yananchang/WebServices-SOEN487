package soen487.g4.sc.manufacturer.test.service;

import soen487.g4.sc.manufacturer3.business.PurchaseOrder;
import soen487.g4.sc.manufacturer3.business.Product;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import soen487.g4.sc.manufacturer3.service.ManufacturerWebService3;

/**
 * Unit tests for the public methods in the ManufacturerWebService class.
 * The process method is tested via console output for the processPurchaseOrder tests.
 * After these tests run, the purchase-orders.xml file needs to be restored to its original state.
 */
public class Manufacturer3WebServiceTests {
    
    private final ManufacturerWebService3 testService;
    
    public Manufacturer3WebServiceTests() {
        testService = new ManufacturerWebService3();
    }

    /**
     * Tests that getProductInfo runs correctly when provided with a valid parameter that matches existing data.
     */
    @Test
    public void getProductInfoTestDVDPlayer() { 
        Product _returnedProduct = testService.getProductInfo("DVD player");
        String _expectedProductType = "DVD player";
        String _expectedManufacturer = ManufacturerWebService3.MANUFACTURER_NAME;
        // float _expectedUnitPrice = 100;
        assertTrue(_returnedProduct.getProductType().equals(_expectedProductType));
        assertTrue(_returnedProduct.getManufacturerName().equals(_expectedManufacturer));
        // assertTrue(_returnedProduct.getUnitPrice() == _expectedUnitPrice); // can no longer be tested since it's initialized to a random value
    }
    
    /**
     * Tests that getProductInfo returns null when an invalid product type is passed.
     */
    @Test
    public void getProductInfoTestInvalidType() {
        Product _returnedProduct = testService.getProductInfo("Microwave");
        // the expected return value is null
        assertTrue(_returnedProduct == null);
    } 
    
    /**
     * Tests that getProductInfo successfully parses the XML file, but returns null when the product type exists, but it isn't found in the XML file.
     */
    /* @Test
    public void getProductInfoTestNotFound() {
        Product _returnedProduct = testService.getProductInfo("TV");
        // the expected return value is null
        assertTrue(_returnedProduct == null);
    } */
    
    /**
     * Tests that processPurchaseOrder runs correctly and updates the purchase order XML file when provided with a valid purchase order for more than 100 units.
     * This test should print the following text in the console:
     * --------------------------------------------------
     * processPurchaseOrderTestBigOrder output:
     * 100 DVD players produced
     * 100 DVD players produced
     * 100 DVD players produced
     * 10 DVD players produced
     */
    @Test
    public void processPurchaseOrderTestBigOrder() throws TransformerException {
        PurchaseOrder _validPOforBigOrder = new PurchaseOrder("1002", "SD100", new Product(ManufacturerWebService3.MANUFACTURER_NAME, "DVD player", 100), 310, 1000);
        System.out.println("--------------------------------------------------");
        System.out.println("processPurchaseOrderTest output:");
        assertTrue(testService.processPurchaseOrder(_validPOforBigOrder));
        assertTrue(wasPurchaseOrderRecorded(_validPOforBigOrder, "false"));
    }
    
    /**
     * Tests that processPurchaseOrder runs correctly and updates the purchase order XML file when provided with a valid purchase order less than 100 units.
     * This test should print the following text in the console:
     * --------------------------------------------------
     * processPurchaseOrderTestSmallOrder output:
     * 60 DVD players produced
     */
    @Test
    public void processPurchaseOrderTestSmallOrder() throws TransformerException {
        PurchaseOrder _validPOforSmallOrder = new PurchaseOrder("1003", "SD100", new Product(ManufacturerWebService3.MANUFACTURER_NAME, "DVD player", 100), 60, 1000);
        System.out.println("--------------------------------------------------");
        System.out.println("processPurchaseOrderTest output:");
        assertTrue(testService.processPurchaseOrder(_validPOforSmallOrder));
        assertTrue(wasPurchaseOrderRecorded(_validPOforSmallOrder, "false"));
    }
    
    /**
     * Tests that processPurchaseOrder returns false when the unit price in the PO is lower than the unit price for the Product.
     */
    @Test
    public void processPurchaseOrderTestInsufficientPrice() throws TransformerException {
        PurchaseOrder _insufficientPricePO = new PurchaseOrder("1004", "SD100", new Product(ManufacturerWebService3.MANUFACTURER_NAME, "DVD player", 100), 310, 0);
        assertFalse(testService.processPurchaseOrder(_insufficientPricePO));
    }
    
    /**
     * Tests that receivePayment correctly updates the purchase order XML file when provided with a valid purchase order number and total price.
     */
    @Test
    public void receivePaymentTest() {
        assertTrue(testService.receivePayment("1001", 23100));
        assertTrue(wasPurchaseOrderRecorded(new PurchaseOrder("1001", "SC100", new Product(ManufacturerWebService3.MANUFACTURER_NAME, "DVD player", 100), 220, 105), "true"));
    }
    
    /**
     * Tests that receivePayment returns false when the purchase order number isn't found.
     */
    @Test
    public void receivePaymentTestOrderNotFound() {
        assertFalse(testService.receivePayment("999", 23100));
    }
    
    /**
     * Tests that receivePayment returns false when the purchase order exists, but the total price doesn't match.
     */
    @Test
    public void receivePaymentTestTotalPriceMismatch() {
        assertFalse(testService.receivePayment("1001", 23000));
    }
    
    /**
     * Verifies if the specified purchase order is in the purchase-orders.xml file and all of its attributes were correctly recorded.
     * @param aPO A PurchaseOrder object.
     * @return True if an exact match of the purchase order is in purchase-orders.xml.
     */
    private boolean wasPurchaseOrderRecorded(PurchaseOrder aPO, String paidValue) {
        DocumentBuilderFactory _dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder _dBuilder;
        Document _purchaseOrderList = null;
        try {
            _dBuilder = _dbFactory.newDocumentBuilder();
            _purchaseOrderList = _dBuilder.parse(ManufacturerWebService3.PO_FILE_PATH);
            NodeList _purchaseOrderNodes = _purchaseOrderList.getElementsByTagName("purchaseorder");
            for (int i = 0; i < _purchaseOrderNodes.getLength(); i++) {
                Node _purchaseOrderNode = _purchaseOrderNodes.item(i);
                Element _purchaseOrder = (Element)_purchaseOrderNode;
                if (_purchaseOrder.getAttribute("num").equals(aPO.getOrderNum())) {
                    return (_purchaseOrder.getElementsByTagName("customerref").item(0).getTextContent().equals(aPO.getCustomerRef())) &&
                           (_purchaseOrder.getElementsByTagName("product").item(0).getTextContent().equals(aPO.getProduct().getProductType())) &&
                           (_purchaseOrder.getElementsByTagName("quantity").item(0).getTextContent().equals(Integer.toString(aPO.getQuantity()))) &&
                           (_purchaseOrder.getElementsByTagName("unitprice").item(0).getTextContent().equals(Float.toString(aPO.getUnitPrice()))) &&
                           (_purchaseOrder.getElementsByTagName("paid").item(0).getTextContent().equals(paidValue));
                }     
            }    
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            System.err.println("Fatal error: Couldn't parse XML file containing product or purchase order data.");
        }
        return false; // if no element with the matching PO number is found
    }
}
