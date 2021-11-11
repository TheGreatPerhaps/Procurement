package edu.famu.procurement.models;

import com.google.cloud.Timestamp;

import java.util.ArrayList;

public class PurchaseOrder {
    protected Number poNumber;
    protected Timestamp poDate;
    protected Timestamp promisedDate;
    protected Address shipTo;
    private Vendor productVendor;
    protected ArrayList<Items> items;
    protected String shippingMethod;
    protected Number freightCost;
    protected Number taxRate;
    protected Number discountRate;
    protected String specialInstructions;
    protected Number invoiceNumber;
    protected String status;
    private User createdBy;
    private User approvedBy;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Number poNumber, Timestamp poDate, Timestamp promisedDate, Address shipTo, Vendor productVendor, ArrayList<Items> items, String shippingMethod, Number freightCost, Number taxRate, Number discountRate, String specialInstructions, Number invoiceNumber, String status, User createdBy, User approvedBy, Timestamp createdAt, Timestamp updatedAt) {
        this.poNumber = poNumber;
        this.poDate = poDate;
        this.promisedDate = promisedDate;
        this.shipTo = shipTo;
        this.productVendor = productVendor;
        this.items = items;
        this.shippingMethod = shippingMethod;
        this.freightCost = freightCost;
        this.taxRate = taxRate;
        this.discountRate = discountRate;
        this.specialInstructions = specialInstructions;
        this.invoiceNumber = invoiceNumber;
        this.status = status;
        this.createdBy = createdBy;
        this.approvedBy = approvedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PurchaseOrder(Number poNumber, Timestamp poDate, Timestamp promisedDate, Address shipTo, ArrayList<Items> items, String shippingMethod, Number freightCost, Number taxRate, Number discountRate, String specialInstructions, Number invoiceNumber, String status, Timestamp createdAt, Timestamp updatedAt) {
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

    public Vendor getProductVendor() {
        return productVendor;
    }

    public void setProductVendor(Vendor productVendor) {
        this.productVendor = productVendor;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
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
}
