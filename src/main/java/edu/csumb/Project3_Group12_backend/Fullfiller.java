package edu.csumb.Project3_Group12_backend;

import com.google.cloud.firestore.GeoPoint;

public class Fullfiller {
    private String username;
    private float rating;
    private GeoPoint city;
    private boolean isFullfiller;
    private String email;
    private String password;
    private String imageURL;

    public Fullfiller(){}

    public Fullfiller(String email, String username, String password) {
        this.username = username;
        this.rating = 0;
        this.isFullfiller = false;
        this.city = null;
        this.email = email;
        this.password = password;
        this.imageURL = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";
    }

    //For Users updating their own profile
    public Fullfiller(String email, String username, String password, String imageURL) {
        this.username = username;
        this.imageURL = imageURL;
        this.password = password;
    }

    public Fullfiller(String email, String username, String password ,float rating, GeoPoint city, boolean isFullfiller) {
        this.username = username;
        this.rating = rating;
        this.isFullfiller = isFullfiller;
        this.city = city;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setAge(int rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public GeoPoint getCity() {
        return city;
    }

    public void setCity(GeoPoint city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFullfiller() {
        return isFullfiller;
    }

    public void setFullfiller(boolean fullfiller) {
        isFullfiller = fullfiller;
    }
}
