package edu.csumb.Project3_Group12_backend.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
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
        List<Fullfiller> fullfillers = getUsers();
        //simple back-end error checking for duplicate emails & usernames
        for (Fullfiller fullfiller1 : fullfillers){
            if (fullfiller1.getEmail().equals(fullfiller.getEmail()) || fullfiller1.getUsername().equals(fullfiller.getUsername())){
                return;
            }
        }
        ApiFuture<WriteResult> apiFuture = firestore.collection("users").document(fullfiller.getEmail()).set(fullfiller);
    }

    public void updateUser(String email, String username, String password, String imageURL) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        List<Fullfiller> fullfillers =  getUsers();
        //loop through the usersn until we find a match, once found, implement that user's new changes (email cannot be changed, just username and everything else)
        for (Fullfiller fullfiller1 : fullfillers){
            if(fullfiller1.getEmail().equals(email)){
                //match has been found
                ApiFuture<WriteResult> apiFuture = firestore.collection("users").document(email).update("username", username);
                ApiFuture<WriteResult> apiFuture2 = firestore.collection("users").document(email).update("password", password);
                ApiFuture<WriteResult> apiFuture1 = firestore.collection("users").document(email).update("imageURL", imageURL);
            }
        }
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
     *
     *
     */
    public void claimTheProject(String id, String email) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference dc = firestore.collection("posts").document(id);
        dc.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }
                // IF the document exists
                if (snapshot != null && snapshot.exists()) {
                    System.out.println("Current data: " + snapshot.getData());

                    // Get the isClaimed field of the document
                    Boolean document_claimed = snapshot.getBoolean("isClaimed");
                    // Convert the value to boolean
                    Boolean claimed1 = Boolean.valueOf(document_claimed);
                    boolean claimedProject = claimed1.booleanValue();
                    // Or you can use the boolean primitive type (Uncomment the following line if you want boolean primitive type)
                    // boolean claimed = Boolean.parseBoolean(docmument_claimed);
                    System.out.println("claimedProject value:"+claimedProject);
                    // Then check if the boolean isClaimed contains the value true or false
                    if (claimedProject) {
                        System.out.println("project has been claimed already");
                        System.err.println("Listen failed: " + e);
                        return;
                    } else {
                        firestore.collection("posts").document(id).update("isClaimed", true);
                        firestore.collection("posts").document(id).update("claimedBy", email);
                        System.out.println("project has been claimed successfully");
                    }
                } else {
                    System.out.print("Current data may be null...but...");
                    firestore.collection("posts").document(id).update("isClaimed", true);
                    firestore.collection("posts").document(id).update("claimedBy", email);
                    System.out.println("project has been claimed successfully");
                }
            }
        });
    }}

