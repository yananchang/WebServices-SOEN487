package soen487.g4.sc.retailer.business;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc Represents an item available at the warehouse Used by the Warehouse
 * unit
 */
@XmlRootElement
public class Item {

    private String manufacturerName;
    private int productId;
    private String productType;
    private float unitPrice;
    private int quantity;

    /**.
     * Default Constructor
     */
    public Item(){
        
    }
    /**
     * @Desc Another constructor for Item
     * @param manufacturerName the name of the manufacturer of the item
     * @param productId a unique Id for the Item
     * @param productType the type of the product
     * @param unitPrice the price of the item
     * @param quantity the quantity available
     */
    public Item(String manufacturerName, int productId, String productType, float unitPrice, int quantity) {
        this.manufacturerName = manufacturerName;
        this.productId = productId;
        this.productType = productType;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
    /**
     * @Desc Constructor for Item
     * @param manufacturerName the name of the manufacturer of the item
     * @param productType the type of the product
     * @param unitPrice the price of the item
     * @param quantity the quantity available
     */
    public Item(String manufacturerName, String productType, float unitPrice, int quantity) {
        this.manufacturerName = manufacturerName;
        this.productType = productType;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
    // @Desc toString for Item
    public String toString() {
        return this.manufacturerName + ", " + this.productType + ", " + this.unitPrice + ", " + this.quantity;
    }
    
    // @Desc equals() for Item
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Item)) return false;
        
        Item _otherItem = (Item) other;
        return this.manufacturerName.equalsIgnoreCase(_otherItem.getManufacturerName()) &&
                this.productType.equalsIgnoreCase(_otherItem.getProductType())          &&
                this.unitPrice == _otherItem.getUnitPrice()                             &&
                this.quantity == _otherItem.getQuantity();
    }
    
    // Getters and Setters
    /**
     * @return the name of the manufacturer of the item
     */
    public String getManufacturerName() {
        return manufacturerName;
    }
    /**
     * @return the id of the product
     */
    public int getProductId(){
        return this.productId;
    }
    /**
     * @return the type of the product
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @return the price of the item
     */
    public float getUnitPrice() {
        return unitPrice;
    }

    /**
     * @return the quantity available
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Set the id of the product
     * @param id 
     */
    public void setProductId(int id){
        this.productId = id;
    }
    /**
     * @param manufacturerName the manufacturerName name
     */
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    /**
     * @param productType the product type
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * @param unitPrice the unit price of the item
     */
    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @param quantity the quantity available
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
