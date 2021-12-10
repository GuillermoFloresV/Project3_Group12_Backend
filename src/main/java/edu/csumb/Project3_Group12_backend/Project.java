package edu.csumb.Project3_Group12_backend;

import com.google.cloud.firestore.GeoPoint;
//TODO: Clean up Project class.
//  - Currently has unnecessary fields, getters, setters, and constructor arguments.
public class Project {


    private String projectName;
    private float budget;
    private String currency;
    private String description;
    private String email;
    private String proposer;
    private boolean isClaimed;
    private String claimedBy;
    private String urlString;
    private String datePublished;
    private boolean anon;

    //required by firestore
    public Project(){}

//    @RequestParam String projectName, @RequestParam float budget, @RequestParam String currency,
//    @RequestParam String description, @RequestParam String email, @RequestParam String proposer,
//    @RequestParam String urlString, @RequestParam String datePublished, @RequestParam boolean isClaimed,
//    @RequestParam String claimedBy, @RequestParam boolean anon

    public Project(String projectName, float budget,String currency, String description, String email,
                   String proposer, String urlString, String datePublished,
                    boolean isClaimed, String claimedBy, boolean anon) {
        this.projectName = projectName;
        this.budget = budget;
        this.currency = currency;
        this.description = description;
        this.email = email;
        this.proposer = proposer;
        this.claimedBy = claimedBy;
        this.urlString = urlString;
        this.datePublished = datePublished;
        this.isClaimed=false;
        this.anon = anon;
    }

    //anonymous projects
    public Project(String projectName, String currency, boolean isClaimed, boolean anon) {
        this.projectName = projectName;
        this.isClaimed = false;
        this.claimedBy = null;
        this.currency = currency;
        this.anon = anon;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public void isClaimed() {
        this.isClaimed = isClaimed;
    }

    public void setIsClaimed(boolean isClaimed){this.isClaimed = isClaimed;}

    public boolean getIsClaimed(){return isClaimed;}

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public boolean isAnon() {
        return anon;
    }

    public void setAnon(boolean anon) {
        this.anon = anon;
    }

    public String getClaimedBy() {
        return claimedBy;
    }

    public void setClaimedBy(String claimedBy) {
        this.claimedBy = claimedBy;
    }
}

