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

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public boolean isClaimed() {
        return isClaimed;
    }

    public void setClaimed(boolean claimed) {
        isClaimed = claimed;
    }

    public DocumentReference getClaimedBy() {
        return claimedBy;
    }

    public void setClaimedBy(DocumentReference claimedBy) {
        this.claimedBy = claimedBy;
    }
}
