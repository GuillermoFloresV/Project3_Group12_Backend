package edu.csumb.Project3_Group12_backend;

import com.google.cloud.firestore.GeoPoint;

public class Project {

    private Integer project_id;
    private String projectName;
    private float budget;
    private String currency;
    private boolean isOpen;
    private String urlString;
    private String datePublished;
    private boolean anon;

    public Project(){}

    public Project(Integer project_id, String projectName, float budget, String currency, boolean isOpen, String urlString, String datePublished, boolean anon) {
        this.project_id = project_id;
        this.projectName = projectName;
        this.budget = budget;
        this.currency = currency;
        this.isOpen = isOpen;
        this.urlString = urlString;
        this.datePublished = datePublished;
        this.anon = anon;
    }

    //anonymous projects
    public Project(Integer project_id, String projectName, String currency, boolean isOpen, boolean anon) {
        this.project_id = project_id;
        this.projectName = projectName;
        this.currency = currency;
        this.anon = anon;
    }



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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
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


    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }
}

