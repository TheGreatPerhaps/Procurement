package edu.famu.procurement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.procurement.models.Address;
import edu.famu.procurement.models.Items;
import edu.famu.procurement.models.PurchaseOrder;
import edu.famu.procurement.models.RestPurchaseOrder;
import edu.famu.procurement.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api")
@RestController
public class PurchaseOrderRestController {

    public PurchaseOrderService purchaseOrderService;

    @Autowired
    public PurchaseOrderRestController(PurchaseOrderService purchaseOrderService) {this.purchaseOrderService = purchaseOrderService;}

    @RequestMapping(value = "/purchaseOrder/{user_id}", method = RequestMethod.GET)
    public List<PurchaseOrder> getPurchaseOrderById(@PathVariable("user_id") String user_id) throws ExecutionException, InterruptedException, ParseException {
        return purchaseOrderService.getAllPurchaseOrdersById(user_id);
    }

    @GetMapping("/purchaseOrder/{user_id}/pending")
    public List<PurchaseOrder> getPendingPurchaseOrdersById(@PathVariable("user_id") String id) throws ExecutionException, InterruptedException, ParseException {
        return purchaseOrderService.getPendingPurchaseOrders(id);
    }

    @PostMapping("/purchaseOrder")
    public String createNewPurchaseOrder(@RequestBody String json) throws JsonProcessingException, ExecutionException, InterruptedException, ParseException {
        Firestore db = FirestoreClient.getFirestore();
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

        JsonNode rootNode = mapper.readTree(json);
        Map mapObj = mapper.convertValue(rootNode.get("shipTo"), Map.class);

        Address tempAddress = new Address(mapObj.get("company").toString(),
                (ArrayList<String>)mapObj.get("street"),mapObj.get("city").toString(),mapObj.get("state").toString(),
                mapObj.get("postalCode").toString(),mapObj.get("country").toString(),
                mapObj.get("phoneNumber").toString(),mapObj.get("emailAddress").toString());

        List itemsMap = mapper.convertValue(rootNode.get("items"), List.class);

        ArrayList<Items> items = (ArrayList<Items>) itemsMap;

        Timestamp createdAt = Timestamp.parseTimestamp(rootNode.get("createdAt").asText());
        Timestamp updatedAt = Timestamp.parseTimestamp(rootNode.get("updatedAt").asText());

        Timestamp poDate = Timestamp.parseTimestamp(rootNode.get("poDate").asText());

        Timestamp promisedDate = Timestamp.parseTimestamp(rootNode.get("promisedDate").asText());

        // Create document refs
        DocumentReference vendorDocRef = db.document(rootNode.get("vendor").asText());
        DocumentReference userCreatedByDocRef = db.document(rootNode.get("createdBy").asText());
        DocumentReference userApprovedByDocRef = db.document(rootNode.get("approvedBy").asText());


        RestPurchaseOrder tempRestPurchaseOrder = new RestPurchaseOrder(rootNode.get("poNumber").numberValue(),poDate,
                promisedDate,tempAddress,items,rootNode.get("shippingMethod").asText(),
                rootNode.get("freightCost").asDouble(),rootNode.get("taxRate").asDouble(),
                rootNode.get("discountRate").asDouble(),rootNode.get("specialInstructions").asText(),
                rootNode.get("invoiceNumber").asDouble(),rootNode.get("status").asText(),createdAt,updatedAt,vendorDocRef,
                userCreatedByDocRef,userApprovedByDocRef);


        return purchaseOrderService.createNewPurchaseOrder(tempRestPurchaseOrder);
    }

    @RequestMapping(value = "/purchaseOrder/pO/{number:\\d+}", method = RequestMethod.GET)
    public PurchaseOrder getPurchaseOrderByPoNumber(@PathVariable("number") Integer number) throws ExecutionException, InterruptedException {
        return  purchaseOrderService.getPurchaseOrderByPoNumber(number);
    }

    @RequestMapping(value = "/purchaseOrder/pO/{number:\\d+}", method = RequestMethod.PUT)
    public PurchaseOrder getPurchaseOrderByPoNumber(@PathVariable("number") Integer number, @RequestBody String json) throws ExecutionException, InterruptedException, JsonProcessingException {
        return purchaseOrderService.updatePurchaseOrder(json, number);
    }



}
