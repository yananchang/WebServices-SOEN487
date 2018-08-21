/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soen487.g4.sc.manufacturer3;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import soen487.g4.sc.manufacturer3.service.ManufacturerWebService3;

/**
 * @Desc Updates the goods' prices and initial inventory of the Manufacturer to be randomly generated.
 */
@WebServlet(name = "InitializeInventory", urlPatterns = {"/InitializeInventory"})
public class InitializeInventory  extends HttpServlet{
        
    public static final int MAX_INITIAL_INVENTORY = 200; // upper bound for the initial inventory values when they are randomly generated
    public static final int MAX_PRODUCT_UNIT_PRICE = 1000; // upper bound for product unit prices when they are randomly generated
    private int numOfDVDPlayers; // number of DVD players the manufacturer has in stock
    private int numOfVideoCameras; // number of video cameras the manufacturer has in stock
    private int numOfTVs; // number of TVs the manufacturer has in stock
    
    /**
     * Default constructor
     * Initializes the stock levels and prices for each of the products to random numbers.
     */
    public InitializeInventory(){
        Random _random = new Random();
        //  Pick intial inventory randomly
        numOfDVDPlayers = _random.nextInt(MAX_INITIAL_INVENTORY + 1);
        numOfVideoCameras = _random.nextInt(MAX_INITIAL_INVENTORY + 1);
        numOfTVs = _random.nextInt(MAX_INITIAL_INVENTORY + 1);
        initializeProductQuantity();
        initializeProductUnitPrices();
    }
    private void initializeProductQuantity(){
        Document _productList = openXMLFile(ManufacturerWebService3.PRODUCT_FILE_PATH);
        NodeList _quantityNodes = _productList.getElementsByTagName("quantity");
        for (int i = 0; i < _quantityNodes.getLength(); i++) {
            Node _unitPriceNode = _quantityNodes.item(i);
            Random random = new Random();
            String _randomQuatity = Integer.toString(random.nextInt(MAX_INITIAL_INVENTORY)+1);
            _unitPriceNode.setTextContent(_randomQuatity);
            updateXMLFile(_productList, ManufacturerWebService3.PRODUCT_FILE_PATH);
        }
    }
    private void initializeProductUnitPrices() {
        Document _productList = openXMLFile(ManufacturerWebService3.PRODUCT_FILE_PATH);
        NodeList _unitPriceNodes = _productList.getElementsByTagName("unitprice");
        for (int i = 0; i < _unitPriceNodes.getLength(); i++) {
            Node _unitPriceNode = _unitPriceNodes.item(i);
            Random random = new Random();
            String randomUnitPrice = Integer.toString(random.nextInt(MAX_PRODUCT_UNIT_PRICE)) + ".0";
            _unitPriceNode.setTextContent(randomUnitPrice);
            updateXMLFile(_productList, ManufacturerWebService3.PRODUCT_FILE_PATH);
        }
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
