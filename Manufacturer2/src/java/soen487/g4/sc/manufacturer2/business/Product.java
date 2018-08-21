
package soen487.g4.sc.manufacturer2.business;

/**
 * Represents a product built by the Manufacturer. 
 * Doesn't include setters for manufacturerName or productName, since it's illogical for those to change.
 */
public class Product {
    
    private String manufacturerName; // the manufacturer of the Product
    private String productType; // can be "DVD player", "video camera" or "TV"
    private float unitPrice; // the minimum price for 1 unit
    
    /**
     * Default Constructor for product.
     */
    public Product(){
    
    }
    /**
     * Constructor for product.
     * @param manufacturerName The manufacturer of the Product.
     * @param productType The type of product; can be "DVD player", "video camera" or "TV".
     * @param unitPrice The minimum price for 1 unit.
     */
    public Product(String manufacturerName, String productType, float unitPrice) {
        this.manufacturerName = manufacturerName;
        this.productType = productType;
        this.unitPrice = unitPrice;
    }
    
    /**
     * Returns the name of the manufacturer of the Product.
     * @return The String manufacturerName.
     */
    public String getManufacturerName() {
        return manufacturerName;
    }
    
    /**
     * Set the manufacturer of a Product.
     * @param manufacturerName The manufacturer name
     */
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
    
    /**
     * Returns the type of the Product.
     * @return "DVD player", "video camera" or "TV".
     */
    public String getProductType() {
        return productType;
    }
    
    /**
     * Set the product type of a Product.
     * @param productType The product type
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    /**
     * The minimum price per unit of the Product.
     * @return The value of unitPrice.
     */
    public float getUnitPrice() {
        return unitPrice;
    }
    
    /**
     * Changes the minimum price for a unit of the Product.
     * @param newPrice The new minimum price per unit.
     */
    public void setUnitPrice(float newPrice) {
        this.unitPrice = newPrice;
    }
    
    
}
