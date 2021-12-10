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

    //########### - Fullfiller Services - #############

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



    //########### - Project Services - #############
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
        CollectionReference addedProjectRef = firestore.collection("posts");

        System.out.println("Added project with ID: " + addedProjectRef.document().getId());
                                                                            //.document(fullfiller.getEmail()).set(fullfiller)
        ApiFuture<WriteResult> writeResult = addedProjectRef.document().set(project);
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

    /** **REMOVE list for testing*** (or create the fullfiller's lists first)
     * clamProject updates the post document's claimedBy field with the fullfiller's email.
     * its currently set up to also update a projectsClaimedList if we choose to keep a list of claimed projects
     * we may want to consider just searching firestore whenever we want a list of projects
     *
     */
    public void claimProject(String id, String email) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        //--get the project to be claimed:

        DocumentReference claimedProjectReference = firestore.collection("post").document(id);
        ApiFuture<DocumentSnapshot> potentialProject = claimedProjectReference.get();
        Boolean hasBeenClaimed = potentialProject.get().getBoolean("isClaimed");
        hasBeenClaimed = false;
        System.out.println("is claimed? " + hasBeenClaimed);
        //--get the user to claim project
        DocumentReference claimedUserReference = firestore.collection("users").document(email);
        ApiFuture<DocumentSnapshot> potentialClaimer = claimedUserReference.get();
       // String claimer = potentialClaimer.get().getString(email);

//        ApiFuture<WriteResult> writeResult = addedProjectRef.document().set(project);

        System.out.println(claimedUserReference + " is trying to claim project: " + claimedProjectReference );

        if (!hasBeenClaimed || hasBeenClaimed==null) {
            firestore.collection("posts").document(id).set(new Project("Washington D.C.")).get();
            // [START fs_update_doc]
            // [START firestore_data_set_field]
            // Update an existing document
            DocumentReference docRef = db.collection("cities").document("DC");

            // (async) Update one field
            ApiFuture<WriteResult> future = docRef.update("capital", true);

            // ...
            WriteResult result = future.get();
            System.out.println("Write result: " + result);
            // [END firestore_data_set_field]
            // [END fs_update_doc]




            ApiFuture<WriteResult> futureClaimer = claimedProjectReference.update("claimedBy", email);
            ApiFuture<WriteResult> futureIsClaimed = claimedProjectReference.update("isClaimed", true);
            WriteResult resultClaimer = futureClaimer.get();
            WriteResult resultIsClaimed = futureIsClaimed.get();
            System.out.println("Claim project write result: " + resultClaimer);
            System.out.println("Claim project write result: " + resultIsClaimed);
            System.out.println("project has been claimed successfully");
        } else {
            System.out.println("project has been claimed already");
        }
    }

}
//other option for user query: https://firebase.google.com/docs/firestore/query-data/get-data
//    ApiFuture<QuerySnapshot> future =
//               firestore.collection("users").whereEqualTo(email, true).get()


//***previous version of claimProject -> takes a list which I don't think we'll be needing
//public List<Project> claimProject(Project project, List<Project> projectsClaimedList, Fullfiller fullfiller) throws ExecutionException, InterruptedException {
//    Firestore firestore = FirestoreClient.getFirestore();
//    DocumentReference claimedProjectReference = firestore.collection("post").document(project.getProjectName());
//    System.out.println("Trying to claimed project with ID: " + claimedProjectReference.getId());
//
//    //check to see if project has already been claimed
//    Boolean claimed = false;
//    Iterable<CollectionReference> collections =claimedProjectReference.listCollections();
//    for (CollectionReference collRef : collections) {
//        if(collRef.whereEqualTo("claimedBy",null).equals(false)){
//            System.out.println("project has been claimed, collRef id: " + collRef.getId());
//            claimed = true;
//        }
//    }
//    if(!claimed) {
//        ApiFuture<WriteResult> future = claimedProjectReference.update("claimedBy", fullfiller.getEmail());
//        WriteResult result = future.get();
//        System.out.println("Claim project write result: " + result);
//        //Todo: add to claimed list:
//    }
//
//    return projectsClaimedList;
//}