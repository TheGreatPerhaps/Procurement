package edu.famu.procurement.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.procurement.models.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class PurchaseOrderService {
   public List<PurchaseOrder> getAllPurchaseOrdersById(String id) throws ExecutionException, InterruptedException{
       Firestore db = FirestoreClient.getFirestore();
       List<PurchaseOrder> purchaseOrders = new ArrayList<>();
      DocumentReference createdByRef = db.document("User/"+id);  //create user document ref

      Query query = db.collection("Purchase Order").whereEqualTo("createdBy", createdByRef); // find document where field is equal
      return getPurchaseOrders(db, purchaseOrders, createdByRef, query);
   }


   public List<PurchaseOrder> getPendingPurchaseOrders(String id) throws ExecutionException, InterruptedException {
      Firestore db = FirestoreClient.getFirestore();
      List<PurchaseOrder> purchaseOrders = new ArrayList<>();
      DocumentReference createdByRef = db.document("User/"+id);  //create user document ref
     String [] possibleValues = {"pending","PENDING","Pending"};

      Query query = db.collection("Purchase Order").whereEqualTo("createdBy", createdByRef)
              .whereIn("status", Arrays.asList(possibleValues));// find document where field is equal

      return getPurchaseOrders(db, purchaseOrders, createdByRef, query);


   }

   private List<PurchaseOrder> getPurchaseOrders(Firestore db, List<PurchaseOrder> purchaseOrders, DocumentReference createdByRef, Query query) throws InterruptedException, ExecutionException {
      List<QueryDocumentSnapshot> docs =query.get().get().getDocuments(); // grab documents;


      for (QueryDocumentSnapshot document: docs) {
         Number poNumber = document.getDouble("poNumber");
         Timestamp poDate = document.getTimestamp("poDate");
         Timestamp promisedDate = document.getTimestamp("promisedDate");

         // Convert object to hashmap to grab fields
         Object shipTo =  document.get("shipTo");
         ObjectMapper mapObject = new ObjectMapper();
         Map< String, Object > mapObj = mapObject.convertValue(shipTo, Map.class);

         //Creating address map
         Address address = new Address(mapObj.get("company").toString(),
                 (ArrayList<String>)mapObj.get("street"),mapObj.get("city").toString(),mapObj.get("state").toString(),
                 mapObj.get("postalCode").toString(),mapObj.get("country").toString(),
                 mapObj.get("phoneNumber").toString(),mapObj.get("emailAddress").toString());

         // Getting vendor Document Ref
         DocumentReference vendorDocRefPath = (DocumentReference) document.get("vendor");
         DocumentReference vendorDocRef = db.document(vendorDocRefPath.getPath());
         DocumentSnapshot vendorQueryDocumentSnapshot = vendorDocRef.get().get();

         // Create Vendor Doc
         ArrayList<String> streetAddress = (ArrayList<String>) vendorQueryDocumentSnapshot .get("street");
         Vendor vendor = new Vendor(vendorQueryDocumentSnapshot.getId(), vendorQueryDocumentSnapshot .getString("company"), streetAddress
                 ,vendorQueryDocumentSnapshot .getString("city"), vendorQueryDocumentSnapshot .getString("state") ,
                 vendorQueryDocumentSnapshot .getString("postalCode"), vendorQueryDocumentSnapshot .getString("country") ,
                 vendorQueryDocumentSnapshot .getString("contact"),vendorQueryDocumentSnapshot .getString("phoneNumber"),
                 vendorQueryDocumentSnapshot .getString("emailAddress"),vendorQueryDocumentSnapshot.getString("paymentTerms"));

         String shippingMethod  = document.getString("shippingMethod");
         Number freightCost = document.getDouble("freightCost");
         Number taxRate = document.getDouble("taxRate");
         Number discountRate = document.getDouble("discountRate");
         String specialInstructions = document.getString("specialInstructions");
         Number invoiceNumber = document.getDouble("invoiceNumber");
         String status = document.getString("status");

         // Creating user object for createdBy
         String createdByUserDocPath = createdByRef.getPath();
         User createByDocument = db.document(createdByUserDocPath).get().get().toObject(User.class);

         //Creating user object for approved by
         DocumentReference approvedByUserDocRef  = (DocumentReference) document.get("approvedBy");
         User approvedByRef = db.document(approvedByUserDocRef.getPath()).get().get().toObject(User.class);

         // Converting object of array items to list
         ArrayList<Items> items = (ArrayList<Items>) document.get("items");

         // Getting Time stamps for document
         Timestamp createdAt = document.getTimestamp("createdAt");
         Timestamp updatedAt = document.getTimestamp("updatedAt");


         purchaseOrders.add(new PurchaseOrder(poNumber,poDate,promisedDate,address,vendor,items,shippingMethod,freightCost,
                 taxRate,discountRate,specialInstructions,invoiceNumber,status,createByDocument,approvedByRef,createdAt, updatedAt));

      }

      return  purchaseOrders;
   }

   public String createNewPurchaseOrder(RestPurchaseOrder restPurchaseOrder) throws ExecutionException, InterruptedException {
      Firestore db = FirestoreClient.getFirestore();
      CollectionReference collectionReference = db.collection("Purchase Order");
      ApiFuture<DocumentReference> future = collectionReference.add(restPurchaseOrder);
      return  future.get().getId();
   }

   public PurchaseOrder getPurchaseOrderByPoNumber(int number) throws ExecutionException, InterruptedException {
      Firestore db = FirestoreClient.getFirestore();
      PurchaseOrder purchaseOrders = null;
      Query query = db.collection("Purchase Order").whereEqualTo("poNumber",number);

      return  getPurchaseOrder(db,purchaseOrders,query);
   }


   private PurchaseOrder getPurchaseOrder(Firestore db, PurchaseOrder purchaseOrders, Query query) throws InterruptedException, ExecutionException {
      List<QueryDocumentSnapshot> docs = query.get().get().getDocuments(); // grab documents;


      for (QueryDocumentSnapshot document : docs) {
         Number poNumber = document.getDouble("poNumber");
         Timestamp poDate = document.getTimestamp("poDate");
         Timestamp promisedDate = document.getTimestamp("promiseDate");

         // Convert object to hashmap to grab fields
         Object shipTo = document.get("shipTo");
         ObjectMapper mapObject = new ObjectMapper();
         Map<String, Object> mapObj = mapObject.convertValue(shipTo, Map.class);

         //Creating address map
         Address address = new Address(mapObj.get("company").toString(),
                 (ArrayList<String>) mapObj.get("street"), mapObj.get("city").toString(), mapObj.get("state").toString(),
                 mapObj.get("postalCode").toString(), mapObj.get("country").toString(),
                 mapObj.get("phoneNumber").toString(), mapObj.get("emailAddress").toString());

         // Getting vendor Document Ref
         DocumentReference vendorDocRefPath = (DocumentReference) document.get("vendor");
         DocumentReference vendorDocRef = db.document(vendorDocRefPath.getPath());
         DocumentSnapshot vendorQueryDocumentSnapshot = vendorDocRef.get().get();

         // Create Vendor Doc
         ArrayList<String> streetAddress = (ArrayList<String>) vendorQueryDocumentSnapshot.get("street");
         Vendor vendor = new Vendor(vendorQueryDocumentSnapshot.getId(), vendorQueryDocumentSnapshot.getString("company"), streetAddress
                 , vendorQueryDocumentSnapshot.getString("city"), vendorQueryDocumentSnapshot.getString("state"),
                 vendorQueryDocumentSnapshot.getString("postalCode"), vendorQueryDocumentSnapshot.getString("country"),
                 vendorQueryDocumentSnapshot.getString("contact"), vendorQueryDocumentSnapshot.getString("phoneNumber"),
                 vendorQueryDocumentSnapshot.getString("emailAddress"), vendorQueryDocumentSnapshot.getString("paymentTerms"));

         String shippingMethod = document.getString("shippingMethod");
         Number freightCost = document.getDouble("freightCost");
         Number taxRate = document.getDouble("taxRate");
         Number discountRate = document.getDouble("discountRate");
         String specialInstructions = document.getString("specialInstructions");
         Number invoiceNumber = document.getDouble("invoiceNumber");
         String status = document.getString("status");

         DocumentReference createByRef = (DocumentReference) document.get("createdBy");
         // Creating user object for createdBy
         String createdByUserDocPath = createByRef.getPath();
         User createByDocument = db.document(createdByUserDocPath).get().get().toObject(User.class);

         //Creating user object for approved by
         DocumentReference approvedByUserDocRef = (DocumentReference) document.get("approvedBy");
         User approvedByRef = db.document(approvedByUserDocRef.getPath()).get().get().toObject(User.class);

         // Converting object of array items to list
         ArrayList<Items> items = (ArrayList<Items>) document.get("items");

         // Getting Time stamps for document
         Timestamp createdAt = document.getTimestamp("createdAt");
         Timestamp updatedAt = document.getTimestamp("updatedAt");


         purchaseOrders = new PurchaseOrder(poNumber, poDate, promisedDate, address, vendor, items, shippingMethod, freightCost,
                 taxRate, discountRate, specialInstructions, invoiceNumber, status, createByDocument, approvedByRef, createdAt, updatedAt);

      }

      return purchaseOrders;
   }


   public PurchaseOrder updatePurchaseOrder(String json, Integer number) throws ExecutionException, InterruptedException, JsonProcessingException {
      ObjectMapper mapObject = new ObjectMapper();
      JsonNode rootNode = mapObject.readTree(json);

      PurchaseOrder pO;
      Firestore db = FirestoreClient.getFirestore();
      Query query = db.collection("Purchase Order").whereEqualTo("poNumber", number);
      QueryDocumentSnapshot document = query.get().get().getDocuments().get(0);
      for (Iterator<Map.Entry<String, JsonNode>> it = rootNode.fields(); it.hasNext(); ) {
         Map.Entry<String, JsonNode> field = it.next();
         System.out.println(field.getValue());
         // Need to get PUT request working
//        ApiFuture<WriteResult> writeResult =  db.document(document.getReference().getPath()).u;

      }


      Number poNumber = document.getDouble("poNumber");
      Timestamp poDate = document.getTimestamp("poDate");
      Timestamp promisedDate = document.getTimestamp("promisedDate");

      // Convert object to hashmap to grab fields
      Object shipTo = document.get("shipTo");
      Map<String, Object> mapObj = mapObject.convertValue(shipTo, Map.class);

      //Creating address map
      Address address = new Address(mapObj.get("company").toString(),
              (ArrayList<String>) mapObj.get("street"), mapObj.get("city").toString(), mapObj.get("state").toString(),
              mapObj.get("postalCode").toString(), mapObj.get("country").toString(),
              mapObj.get("phoneNumber").toString(), mapObj.get("emailAddress").toString());

      // Getting vendor Document Ref
      DocumentReference vendorDocRefPath = (DocumentReference) document.get("vendor");
      DocumentReference vendorDocRef = db.document(vendorDocRefPath.getPath());
      DocumentSnapshot vendorQueryDocumentSnapshot = vendorDocRef.get().get();

      // Create Vendor Doc
      ArrayList<String> streetAddress = (ArrayList<String>) vendorQueryDocumentSnapshot.get("street");
      Vendor vendor = new Vendor(vendorQueryDocumentSnapshot.getId(), vendorQueryDocumentSnapshot.getString("company"), streetAddress
              , vendorQueryDocumentSnapshot.getString("city"), vendorQueryDocumentSnapshot.getString("state"),
              vendorQueryDocumentSnapshot.getString("postalCode"), vendorQueryDocumentSnapshot.getString("country"),
              vendorQueryDocumentSnapshot.getString("contact"), vendorQueryDocumentSnapshot.getString("phoneNumber"),
              vendorQueryDocumentSnapshot.getString("emailAddress"), vendorQueryDocumentSnapshot.getString("paymentTerms"));

      String shippingMethod = document.getString("shippingMethod");
      Number freightCost = document.getDouble("freightCost");
      Number taxRate = document.getDouble("taxRate");
      Number discountRate = document.getDouble("discountRate");
      String specialInstructions = document.getString("specialInstructions");
      Number invoiceNumber = document.getDouble("invoiceNumber");
      String status = document.getString("status");

      DocumentReference createByRef = (DocumentReference) document.get("createdBy");
      // Creating user object for createdBy
      String createdByUserDocPath = createByRef.getPath();
      User createByDocument = db.document(createdByUserDocPath).get().get().toObject(User.class);

      //Creating user object for approved by
      DocumentReference approvedByUserDocRef = (DocumentReference) document.get("approvedBy");
      User approvedByRef = db.document(approvedByUserDocRef.getPath()).get().get().toObject(User.class);

      // Converting object of array items to list
      ArrayList<Items> items = (ArrayList<Items>) document.get("items");

      // Getting Time stamps for document
      Timestamp createdAt = document.getTimestamp("createdAt");
      Timestamp updatedAt = document.getTimestamp("updatedAt");


      pO = new PurchaseOrder(poNumber, poDate, promisedDate, address, vendor, items, shippingMethod, freightCost,
              taxRate, discountRate, specialInstructions, invoiceNumber, status, createByDocument, approvedByRef, createdAt, updatedAt);



      return pO;
   }

}
