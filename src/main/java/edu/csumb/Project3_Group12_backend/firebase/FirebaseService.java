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
        ApiFuture<WriteResult> apiFuture = firestore.collection("posts").document(keyGenerator(project.getProjectName(), project.getProposer())).set(project);
    }

    public void updateProject(Project project) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture = firestore.collection("posts").document("proj:"+project.getProjectName()+" user:"+project.getProposer()).update("name", project.getProjectName());
        ApiFuture<WriteResult> apiFuture2 = firestore.collection("posts").document("proj:"+project.getProjectName()+" user:"+project.getProposer()).update("budget", project.getBudget());
        ApiFuture<WriteResult> apiFuture3 = firestore.collection("posts").document("proj:"+project.getProjectName()+" user:"+project.getProposer()).update("proposer", project.getProposer());
        ApiFuture<WriteResult> apiFuture4 = firestore.collection("posts").document("proj:"+project.getProjectName()+" user:"+project.getProposer()).update("isOpen", project.isClaimed());
        ApiFuture<WriteResult> apiFuture5 = firestore.collection("posts").document("proj:"+project.getProjectName()+" user:"+project.getProposer()).update("description", project.getDescription());
        ApiFuture<WriteResult> apiFuture6 = firestore.collection("posts").document("proj:"+project.getProjectName()+" user:"+project.getProposer()).update("claimedBy", project.getClaimedBy());
    }

    public String keyGenerator(String name, String email){
        return "proj:"+name+" user:"+email;
    }
}
