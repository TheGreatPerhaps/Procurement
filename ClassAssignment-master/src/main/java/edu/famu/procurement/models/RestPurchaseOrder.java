package edu.famu.procurement.models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;

import java.util.ArrayList;

public class RestPurchaseOrder {
    private Number poNumber;
    private Timestamp poDate;
    private Timestamp promisedDate;
    private Address shipTo;
    private ArrayList<Items> items;
    private String shippingMethod;
    private Number freightCost;
    private Number taxRate;
    private Number discountRate;
    private String specialInstructions;
    private Number invoiceNumber;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private DocumentReference vendor;
    private DocumentReference createdBy;
    private DocumentReference approvedBy;

    public RestPurchaseOrder(Number poNumber, Timestamp poDate, Timestamp promisedDate, Address shipTo,
                             ArrayList<Items> items, String shippingMethod, Number freightCost, Number taxRate,
                             Number discountRate, String specialInstructions, Number invoiceNumber, String status,
                             Timestamp createdAt, Timestamp updatedAt, DocumentReference vendor, DocumentReference createdBy,
                             DocumentReference approvedBy) {
        this.poNumber = poNumber;
        this.poDate = poDate;
        this.promisedDate = promisedDate;
        this.shipTo = shipTo;
        this.items = items;
        this.shippingMethod = shippingMethod;
        this.freightCost = freightCost;
        this.taxRate = taxRate;
        this.discountRate = discountRate;
        this.specialInstructions = specialInstructions;
        this.invoiceNumber = invoiceNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.vendor = vendor;
        this.createdBy = createdBy;
        this.approvedBy = approvedBy;
    }

    public DocumentReference getVendor() {
        return vendor;
    }

    public void setVendor(DocumentReference vendor) {
        this.vendor = vendor;
    }

    public Number getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(Number poNumber) {
        this.poNumber = poNumber;
    }

    public Timestamp getPoDate() {
        return poDate;
    }

    public void setPoDate(Timestamp poDate) {
        this.poDate = poDate;
    }

    public Timestamp getPromisedDate() {
        return promisedDate;
    }

    public void setPromisedDate(Timestamp promisedDate) {
        this.promisedDate = promisedDate;
    }

    public Address getShipTo() {
        return shipTo;
    }

    public void setShipTo(Address shipTo) {
        this.shipTo = shipTo;
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Number getFreightCost() {
        return freightCost;
    }

    public void setFreightCost(Number freightCost) {
        this.freightCost = freightCost;
    }

    public Number getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Number taxRate) {
        this.taxRate = taxRate;
    }

    public Number getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Number discountRate) {
        this.discountRate = discountRate;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Number getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Number invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public DocumentReference getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(DocumentReference createdBy) {
        this.createdBy = createdBy;
    }

    public DocumentReference getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(DocumentReference approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Override
    public String toString() {
        return "RestPurchaseOrder{" +
                "poNumber=" + poNumber +
                ", poDate=" + poDate +
                ", promisedDate=" + promisedDate +
                ", shipTo=" + shipTo +
                ", items=" + items +
                ", shippingMethod='" + shippingMethod + '\'' +
                ", freightCost=" + freightCost +
                ", taxRate=" + taxRate +
                ", discountRate=" + discountRate +
                ", specialInstructions='" + specialInstructions + '\'' +
                ", invoiceNumber=" + invoiceNumber +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", vendorRef=" + vendor +
                ", userCreateByRef=" + createdBy +
                ", userApprovedByRef=" + approvedBy +
                '}';
    }
}
