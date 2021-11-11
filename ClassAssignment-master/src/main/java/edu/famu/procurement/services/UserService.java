package edu.famu.procurement.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.procurement.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        Firestore db  = FirestoreClient.getFirestore();
        List<User> userList = new ArrayList<>();

     ApiFuture<QuerySnapshot> querySnapshot = db.collection("User").get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        for(int i = 0; i < documents.size(); i++)
        {
            String employeeId = documents.get(i).getId();
            String firstname = documents.get(i).getString("firstName");
            String lastname = documents.get(i).getString("lastName");
            String phoneNumber = documents.get(i).getString("phoneNumber");
            Boolean approver =  documents.get(i).getBoolean("approver");
            Boolean active =  documents.get(i).getBoolean("active");
            User temp = new User(employeeId,firstname,lastname,phoneNumber,approver,active);

            userList.add(temp);
        }

        return userList;
    }

    public User getUserByID(String id) throws ExecutionException, InterruptedException {
        Firestore db  = FirestoreClient.getFirestore();
        DocumentReference querySnapshot = db.collection("User").document(id);

        DocumentSnapshot doc = querySnapshot.get().get();

        String employeeId = doc.getId();
        String firstname = doc.getString("firstName");
        String lastname = doc.getString("lastName");
        String phoneNumber = doc.getString("phoneNumber");
        Boolean approver =  doc.getBoolean("approver");
        Boolean active =  doc.getBoolean("active");

        return new User(employeeId,firstname,lastname,phoneNumber,approver,active);

    }

    public String postNewUser(User user) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference productPostRef =  db.collection("User");
        ApiFuture<DocumentReference> future = productPostRef.add(user);
        setUserObjectId(future.get().getId());
        return  future.get().getId();
    }

    private void setUserObjectId(String id)  {
        // Update id of recently added document to collection because it can't be updated at time its being stored
        // updating at time it stored causes it to great a different id then one for the collection
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future =  db.collection("User").document(id).update("employeeId",id);
    }
}
