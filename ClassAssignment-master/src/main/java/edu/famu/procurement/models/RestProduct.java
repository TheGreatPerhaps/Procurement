package edu.famu.procurement.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

public class RestProduct {
    protected String productId;
    protected Number sku;
    protected String name;
    protected String description;
    protected String unit;
    protected Number unitPrice;
    private DocumentReference productVendor;

    public RestProduct() {
    }

    public RestProduct(String productId, Number sku, String name, String description, String unit, Number unitPrice, DocumentReference productVendor) {
        this.productId = productId;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.productVendor = productVendor;
    }

    public RestProduct(JsonNode productId, JsonNode sku, JsonNode name, JsonNode description, JsonNode unit, JsonNode unitPrice, JsonNode productVendor) {
        this.productId = String.valueOf(productId);
        this.setProductVendorDoc(String.valueOf(productVendor).replace("\"", ""));
        this.sku = Double.valueOf(String.valueOf(sku));
        this.name = String.valueOf(name).replace("\"", "");
        this.description = String.valueOf(description).replace("\"", "");
        this.unit = String.valueOf(unit).replace("\"", "");
        this.unitPrice = Double.valueOf(String.valueOf(unitPrice));
    }

    public void setProductVendorDoc(String productVendor) {
        Firestore db = FirestoreClient.getFirestore();
        this.productVendor = db.document(productVendor);
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

    public DocumentReference getProductVendor() {
        return productVendor;
    }

    public void setProductVendor(DocumentReference productVendor) {
        this.productVendor = productVendor;
    }

    @Override
    public String toString() {
        return "RestProduct{" +
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
