package edu.famu.procurement.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.procurement.models.Vendor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class VendorService {

    public List<Vendor> getAllVendors() throws ExecutionException, InterruptedException {
        List<Vendor> vendors = new ArrayList<>();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //retrieves a reference to a list of documents in collection
        //ApiFuture allows us to make async calls to the database
        ApiFuture<QuerySnapshot> future = db.collection("Vendor").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            ArrayList<String> streetAddress = (ArrayList<String>) document.get("street");
            vendors.add(new Vendor(document.getId(), document.getString("company"), streetAddress
                    ,document.getString("city"), document.getString("state") ,
                    document.getString("postalCode"), document.getString("country") ,
                    document.getString("contact"),document.getString("phoneNumber"),
                    document.getString("emailAddress"),document.getString("paymentTerms") ));
        }

        return vendors;

    }


    public String createVendor(Vendor vendor) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference document = db.collection("Vendor");
        ApiFuture<DocumentReference> vendorPostRef = document.add(vendor);
        setVendorObjectId(vendorPostRef.get().getId());
        return vendorPostRef.get().getId();
    }

    private void setVendorObjectId(String id) {
        Firestore db = FirestoreClient.getFirestore();
       ApiFuture<WriteResult> future =  db.collection("Vendor").document(id).update("vendorId",id);
    }
}
