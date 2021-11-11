package edu.famu.procurement.models;

public class Products {
    protected String productId;
    protected Number sku;
    protected String name;
    protected String description;
    protected String unit;
    protected Number unitPrice;
    private Vendor productVendor;

    public Products() {
    }

    public Products(String productId, Number sku, String name, String description, String unit, Number unitPrice, Vendor productVendor) {
        this.productId = productId;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.productVendor = productVendor;
    }

    public Products(String productId, Number sku, String name, String description, String unit, Number unitPrice) {
        this.productId = productId;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.unitPrice = unitPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Number getSku() {
        return sku;
    }

    public void setSku(Number sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Number getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Number unitPrice) {
        this.unitPrice = unitPrice;
    }


    public Vendor getVendor()
    {
        return productVendor;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productId='" + productId + '\'' +
                ", sku=" + sku +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice=" + unitPrice +
                ", productVendor=" + productVendor +
                '}';
    }
}
