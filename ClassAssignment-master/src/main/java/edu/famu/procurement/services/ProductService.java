package edu.famu.procurement.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.procurement.models.Products;
import edu.famu.procurement.models.RestProduct;
import edu.famu.procurement.models.Vendor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

    public List<Products> getAllProducts() throws ExecutionException, InterruptedException, JsonProcessingException {
        List<Products> allProducts = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();

        //ApiFuture allows us to make async calls to the database
        ApiFuture<QuerySnapshot> future = db.collection("Products").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments(); // Get all products
        for (QueryDocumentSnapshot document : documents) {
            Number sku = document.getDouble("sku"); // sku Field
            Number unitPrice = document.getDouble("unitPrice"); // Unit Price Field
            DocumentReference vendorRef = (DocumentReference) document.get("vendor"); // get document reference
            String vendorPath = vendorRef.getPath(); // get path to document
            Vendor vendorDoc = db.document(vendorPath).get().get().toObject(Vendor.class);// get data and convert to Vendor class


            // add products to list of products
            allProducts.add(new Products(document.getString("productId"),sku,document.getString("name"),document.getString("description"),
                            document.getString("unit"),unitPrice,vendorDoc));
        }
            return allProducts;

        }

        public String createProduct(RestProduct product) throws ExecutionException, InterruptedException {
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference productPostRef =  db.collection("Products");
            ApiFuture<DocumentReference> future = productPostRef.add(product);
            setProductObjectId(future.get().getId());
            return  future.get().getId();
        }

        private void setProductObjectId(String id)  {
            // Update id of recently added document to collection because it can't be updated at time its being stored
            // updating at time it stored causes it to great a different id then one for the collection
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> future =  db.collection("Products").document(id).update("productId",id);
        }
    }


