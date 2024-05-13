package com.example.combineddemo.home;

import java.util.ArrayList;

public class ModelUser {
    String userName,email, password, biography;

    int userId;
    int rank, profilePhoto;
    ArrayList<ModelUser> friendList;
    ArrayList<ModelPost> sharedPosts;
    boolean isAdvistor;

    public ModelUser(int userId, String userName, String email, String password, String biography, int profilePhoto) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.biography = biography;
        this.rank = -1;
        this.profilePhoto = profilePhoto;
        this.friendList = new ArrayList<>();
        this.sharedPosts = new ArrayList<>();
        this.isAdvistor = false;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public ArrayList<ModelUser> getFriendList() {
        return friendList;
    }

    public void addFriend(ModelUser newFriend) {
        this.friendList.add(newFriend);
    }

    public ArrayList<ModelPost> getSharedPosts() {
        return sharedPosts;
    }

    public void addSharedPosts(ModelPost newSharedPost) {
        this.sharedPosts.add(newSharedPost);
    }

    public boolean isAdvistor() {
        return isAdvistor;
    }

    public void setAdvistor(boolean advistor) {
        isAdvistor = advistor;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
