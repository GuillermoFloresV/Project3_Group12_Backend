package edu.csumb.Project3_Group12_backend.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.csumb.Project3_Group12_backend.objects.Fullfiller;
import edu.csumb.Project3_Group12_backend.objects.Project;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public void saveNewProject(Project project) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> apiFuture = firestore.collection("posts").add(project);
    }
}
