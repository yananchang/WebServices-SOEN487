
package application;

import java.util.Scanner;
import soen487.g4.sc.manufacturer.service.ObjectFactory;
import soen487.g4.sc.manufacturer.service.Product;
import soen487.g4.sc.manufacturer.service.PurchaseOrder;

/**
 * Mock client application to demo the Manufacturer web service.
 * In the proposed designed of the Supply Chain Management Application, the client web app doesn't directly call the Manufacturer web service. 
 * Therefore, we designed this simple client for demo and test the functions made available by the Manufacturer web service.
 * In the final version of the application, these functions will be called by the Retailer System's Warehouse web service.
 */
public class ManufacturerTestClient {  
    
    /**
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner _keyboard = new Scanner(System.in);
        String _command; // 1, 2 or 3
        
        System.out.println("Manufacturer web service test client started");
        while(true) {
            System.out.println("Select a command:");
            System.out.println("1 - Get product information");
            System.out.println("2 - Send a purchase order");
            System.out.println("3 - Send a payment");
            System.out.print("> ");
            _command = _keyboard.nextLine();
            
            if (_command.equals("1")) { // Get product information
                processGetProductInfoCommand();
            } else if (_command.equals("2")) {
                processSendPurchaseOrderCommand();
            } else if (_command.equals("3")) {
                processSendPaymentCommand();
            } else {
                System.out.println("Invalid command");
                System.out.println();
            }
        }
    }

    /**
     * Prompts the user to select one of the products, then retrives it from the Manufacturer web service and displays its unit price.
     */
    private static void processGetProductInfoCommand() {
        Scanner _keyboard = new Scanner(System.in);
        String _selection; // A, B, C
        boolean _isInvalidSelection = true; // used to prompt for a selection until a valid one is received
        Product _selectedProduct; // Product object retrived from the Manufacturer web service
        
        System.out.println("For which product?");
        System.out.println("A - DVD player");
        System.out.println("B - video camera");
        System.out.println("C - TV");
        do {
            System.out.print("> ");
            _selection = _keyboard.nextLine();
            if (_selection.equalsIgnoreCase("A")) {
                _selectedProduct = getProductInfo("DVD player");
                System.out.println("The DVD player's unit price is " + _selectedProduct.getUnitPrice());
                _isInvalidSelection = false;
            } else if (_selection.equalsIgnoreCase("B")) {
                _selectedProduct = getProductInfo("video camera");
                System.out.println("The video camera's unit price is " + _selectedProduct.getUnitPrice());
                _isInvalidSelection = false;
            } else if (_selection.equalsIgnoreCase("C")) {
                _selectedProduct = getProductInfo("TV");
                System.out.println("The TV's unit price is " + _selectedProduct.getUnitPrice());
                _isInvalidSelection = false;
            } else {
                System.out.println("Invalid selection");
            }
        } while (_isInvalidSelection);
        System.out.println();
    }
    
    private static void processSendPurchaseOrderCommand() {
        Scanner _keyboard = new Scanner(System.in);
        
        // get the value for String orderNum
        System.out.print("Enter the order number: ");
        String _orderNum = _keyboard.nextLine();
        
        // get the value for String customerRef
        System.out.print("Enter the customer reference number: ");
        String _customerRef = _keyboard.nextLine();
        
        // get the values needed to create a Project object
        // hardcoding the manufacturer value for simplicity
        String _manufacturer = "Magnetbox";
        // get the value for String productType
        System.out.println("For which product?");
        System.out.println("A - DVD player");
        System.out.println("B - video camera");
        System.out.println("C - TV");
        boolean _isInvalidSelection = true; // used to prompt for a selection until a valid one is received
        String _productType = null;
        do {
            System.out.print("> ");
            String _selection = _keyboard.nextLine(); // A, B, C

            if (_selection.equalsIgnoreCase("A")) {
                _productType = "DVD player";
                _isInvalidSelection = false;
            } else if (_selection.equalsIgnoreCase("B")) {
                _productType = "video camera";
                _isInvalidSelection = false;
            } else if (_selection.equalsIgnoreCase("C")) {
                _productType = "TV";
                _isInvalidSelection = false;
            } else {
                System.out.println("Invalid selection");
            }
        } while (_isInvalidSelection);
        // get the value for unitPrice
        System.out.print("What is the cost price per unit? ");
        float _unitManufacturerPrice = Float.parseFloat(_keyboard.nextLine());
        // create the Product object 
        ObjectFactory _factory = new ObjectFactory();
        Product _product = _factory.createProduct();
        _product.setManufacturerName(_manufacturer);
        _product.setProductType(_productType);
        _product.setUnitPrice(_unitManufacturerPrice);
        
        // get the value for int quantity
        System.out.print("How many do you want to order? ");
        int _quantity = Integer.parseInt(_keyboard.nextLine());
        
        // get the value for float unitPrice
        System.out.print("What is the purchase price per unit? ");
        float _unitPurchaseOrderPrice = Float.parseFloat(_keyboard.nextLine());
        
        // create the PurchaseOrder object
        PurchaseOrder _aPO = _factory.createPurchaseOrder();
        _aPO.setOrderNum(_orderNum);
        _aPO.setCustomerRef(_customerRef);
        _aPO.setProduct(_product);
        _aPO.setQuantity(_quantity);
        _aPO.setUnitPrice(_unitPurchaseOrderPrice);
        
        if (processPurchaseOrder(_aPO) == true) {
            System.out.println("Purchase order processed");
        } else {
            System.out.println("The purchase order has missing or incorrect information");
        }
    }
    
    private static void processSendPaymentCommand() {
        Scanner _keyboard = new Scanner(System.in);
        
        // get the value for String orderNum
        System.out.print("Enter the order number (e.g. 1001): ");
        String _orderNum = _keyboard.nextLine();
        
        // get the value for float totalPrice
        System.out.print("What is the total price of the order (e.g. 23100)? ");
        float _totalPrice = Float.parseFloat(_keyboard.nextLine());
        
        if (receivePayment(_orderNum, _totalPrice) == true) {
            System.out.println("The payment was received and order " + _orderNum + " is now paid in full");
        } else {
            System.out.println("The number or amount entered doesn't match the order");
        }
    }

    private static Product getProductInfo(java.lang.String aProdName) {
        soen487.g4.sc.manufacturer.service.ManufacturerWebService_Service service = new soen487.g4.sc.manufacturer.service.ManufacturerWebService_Service();
        soen487.g4.sc.manufacturer.service.ManufacturerWebService port = service.getManufacturerWebServicePort();
        return port.getProductInfo(aProdName);
    }

    private static boolean processPurchaseOrder(soen487.g4.sc.manufacturer.service.PurchaseOrder aPO) {
        soen487.g4.sc.manufacturer.service.ManufacturerWebService_Service service = new soen487.g4.sc.manufacturer.service.ManufacturerWebService_Service();
        soen487.g4.sc.manufacturer.service.ManufacturerWebService port = service.getManufacturerWebServicePort();
        return port.processPurchaseOrder(aPO);
    }

    private static boolean receivePayment(java.lang.String orderNum, float totalPrice) {
        soen487.g4.sc.manufacturer.service.ManufacturerWebService_Service service = new soen487.g4.sc.manufacturer.service.ManufacturerWebService_Service();
        soen487.g4.sc.manufacturer.service.ManufacturerWebService port = service.getManufacturerWebServicePort();
        return port.receivePayment(orderNum, totalPrice);
    }
}
