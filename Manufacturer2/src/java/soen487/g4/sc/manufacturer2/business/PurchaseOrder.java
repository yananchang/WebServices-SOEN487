
package soen487.g4.sc.manufacturer2.business;

/**
 * Represents a purchase order of Products.
 * Doesn't include setters for orderNum or customerRef, since normally those shouldn't change.
 */
public class PurchaseOrder {
    
    private String orderNum; // the purchase order number
    private String customerRef; // the reference number of the customer placing the order
    private Product product; // the Product the purchase order is for
    private int quantity; // the number of units of the Product that were ordered
    private float unitPrice; // the purchase price per unit
    
    /**
     * Default Constructor for product.
     */
    public PurchaseOrder(){
        
    }
    /**
     * Constructor for purchase orders.
     * @param orderNum The purchase order number.
     * @param customerRef The reference number of the customer placing the order.
     * @param product The Product the purchase order is for.
     * @param quantity The number of units of the Product that were ordered.
     * @param unitPrice The purchase price per unit.
     */
    public PurchaseOrder(String orderNum, String customerRef, Product product, int quantity, float unitPrice) {
        this.orderNum = orderNum;
        this.customerRef = customerRef;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    
    /**
     * Returns the purchase order's number.
     * @return The String orderNum.
     */
    public String getOrderNum() {
        return orderNum;
    }
    
    /**
     * Set the order number of the Product.
     * @param orderNum The order number.
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    
    /**
     * Returns the reference number of the customer placing the order.
     * @return The String customerRef.
     */
    public String getCustomerRef() {
        return customerRef;
    }
    
    /**
     * Set the customer reference number of the Product.
     * @param customerRef The customer reference number.
     */
    public void setCustomerRef(String customerRef) {
        this.customerRef = customerRef;
    }
    
    /**
     * Returns the Product the purchase order is for.
     * Should possibly be refactored to return a deep copy.
     * @return The Product object product.
     */
    public Product getProduct() {
        return product;
    }
    
    /**
     * Changes the Product the order is for.
     * @param newProduct The new Product.
     */
    public void setProduct(Product newProduct) {
        this.product = newProduct;
    }
    
    /**
     * Returns the number of units of the Product that were ordered.
     * @return The value of quantity.
     */    
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Changes the number of units of the Product ordered.
     * @param newQuantity The new number of units.
     */
    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    /**
     * Returns the purchase price per unit of the Product.
     * @return The value of unitPrice.
     */            
    public float getUnitPrice() {
        return unitPrice;
    }
    
    /**
     * Changes the purchase price for 1 unit of Product.
     * @param newPrice The new purchase price.
     */
    public void setUnitPrice(float newPrice) {
        this.unitPrice = newPrice;
    }
}
