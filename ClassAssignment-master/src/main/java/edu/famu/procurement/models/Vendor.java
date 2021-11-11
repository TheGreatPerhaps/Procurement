package edu.famu.procurement.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.cloud.firestore.DocumentReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Vendor {
    private String vendorId;
    private String company;
    private ArrayList<String> street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String contact;
    private String phoneNumber;
    private String emailAddress;
    private String paymentTerms;

    public Vendor()
    {

    }
    public Vendor(String vendorId, String company, ArrayList<String> street, String city, String state, String postalCode, String country, String contact, String phoneNumber, String emailAddress, String paymentTerms) {
        this.vendorId = vendorId;
        this.company = company;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.contact = contact;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.paymentTerms = paymentTerms;
    }

    public Vendor(JsonNode vendorId, JsonNode company, JsonNode street , JsonNode city, JsonNode state, JsonNode postalCode, JsonNode country,JsonNode contact, JsonNode phoneNumber, JsonNode emailAddress, JsonNode paymentTerms) {
        this.vendorId = vendorId.asText();
        this.company = company.asText();
        ArrayList<String> mapper = new ObjectMapper().convertValue(street,ArrayList.class);
        this.street = mapper;
        this.city = city.asText();
        this.state = state.asText();
        this.postalCode = postalCode.asText();
        this.country =country.asText();
        this.contact = contact.asText();
        this.phoneNumber = phoneNumber.asText();
        this.emailAddress = emailAddress.asText();
        this.paymentTerms = paymentTerms.asText();
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public ArrayList<String> getStreet() {
        return street;
    }

    public void setStreet(ArrayList<String> street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }


    @Override
    public String toString() {
        return "Vendor{" +
                "vendorId='" + vendorId + '\'' +
                ", company='" + company + '\'' +
                ", street=" + street +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", contact='" + contact + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", paymentTerms='" + paymentTerms + '\'' +
                '}';
    }
}
