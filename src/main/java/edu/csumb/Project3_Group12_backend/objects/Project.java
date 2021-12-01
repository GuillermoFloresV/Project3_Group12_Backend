package edu.csumb.Project3_Group12_backend.objects;

import com.google.cloud.firestore.DocumentReference;

public class Project {
    private String proposer;
    private String description;
    private float budget;
    private boolean isClaimed;
    private DocumentReference claimedBy;

    public Project (){}

    public Project(String proposer, String description, float budget) {
        this.proposer = proposer;
        this.description = description;
        this.budget = budget;
        this.isClaimed = false;
    }

    public Project (String proposer, String description, float budget, boolean isClaimed, DocumentReference claimedBy){
        this.proposer = proposer;
        this.description = description;
        this.budget = budget;
        this.isClaimed = false;
        this.claimedBy = null;

    }


}
