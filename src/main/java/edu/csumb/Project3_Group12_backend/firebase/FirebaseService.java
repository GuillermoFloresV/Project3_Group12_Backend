package edu.csumb.Project3_Group12_backend.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.csumb.Project3_Group12_backend.Fullfiller;
import edu.csumb.Project3_Group12_backend.Project;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

//The class that will hold all the helper methods to update objs in our DB
@Service
public class FirebaseService {
    public List<Fullfiller> getUsers() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = firestore.collection("users");
        ApiFuture<QuerySnapshot> apiFuture = collectionReference.get();

        List<QueryDocumentSnapshot> documentSnapshots = apiFuture.get().getDocuments();

        List<Fullfiller> users = new ArrayList<Fullfiller>();
        for (DocumentSnapshot documentSnapshot : documentSnapshots){
            users.add(documentSnapshot.toObject(Fullfiller.class));
        }
        return users;
    }

    public void saveNewUser(Fullfiller fullfiller) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture = firestore.collection("users").document(fullfiller.getEmail()).set(fullfiller);
    }

/**
 *
 * @param email
 * @return user object with their data.
 */
    public Fullfiller adminGetUserDocument(String email) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("users").document(email);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        // future.get() blocks on response
        DocumentSnapshot userDocument = future.get();
        Fullfiller userDetails = null;
        if (userDocument.exists()) {
            System.out.println("User data: " + userDocument.getData());
            userDetails = userDocument.toObject(Fullfiller.class);
        } else {
            System.out.println("No such user");
        }
    return userDetails;
    }

/**
 * Retrieves document in collection as map.
 * @return map (string => object) *For non-admin use*
 */
    //Todo: remove password field from map
    public Map<String, Object> getUserDocumentAsMap(String email) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference userDocRef = firestore.collection("users").document(email);

        ApiFuture<DocumentSnapshot> future = userDocRef.get();
        DocumentSnapshot userDetails = future.get();

        if (userDetails.exists()) {
            System.out.println("User data: " + userDetails.getData());
        } else {
            System.out.println("No such User!");
        }
        return (userDetails.exists()) ? userDetails.getData() : null;
    }



    /**
     * @param project
     * create a new project.
     */
    public void saveNewProject(Project project) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture = firestore.collection("posts").document(String.valueOf(project.getProject_id())).set(project);
    }
    /**
     *retrieve all projects
     */
    public List<Project> getProjects() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = firestore.collection("posts");
        ApiFuture<QuerySnapshot> apiFuture = collectionReference.get();

        List<QueryDocumentSnapshot> documentSnapshots = apiFuture.get().getDocuments();

        List<Project> projects = new ArrayList<Project>();
        for (DocumentSnapshot documentSnapshot : documentSnapshots){
            projects.add(documentSnapshot.toObject(Project.class));
        }
        return projects;
    }

}
//other option for user query: https://firebase.google.com/docs/firestore/query-data/get-data
//    ApiFuture<QuerySnapshot> future =
//               firestore.collection("users").whereEqualTo(email, true).get()