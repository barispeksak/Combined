package com.example.combineddemo.home;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelUser {
    String userName,email, password, biography;

    static int userId = 0;
    int rank, profilePhoto;

    private FirebaseAuth auth;
    private FirebaseStorage firebaseStorage;

    private FirebaseFirestore firebaseFirestore;

    private StorageReference storageReference;
    ArrayList<ModelUser> friendList;
    ArrayList<ModelPost> sharedPosts;
    boolean isAdvistor;

    public ModelUser(String userName, String email, String password, String biography, int profilePhoto) {
        userId++;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.biography = biography;
        this.rank = -1;
        this.profilePhoto = profilePhoto;
        this.friendList = new ArrayList<>();
        this.sharedPosts = new ArrayList<>();
        this.isAdvistor = false;

        firebaseStorage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = firebaseStorage.getReference();

        FirebaseUser firebaseUser = auth.getCurrentUser();
        if (firebaseUser != null) {

                HashMap<String, Object> postData = new HashMap<>();
                postData.put("id", userId);
                postData.put("username", userName);
                postData.put("useremail", email);
                postData.put("password", password);
                postData.put("profilePhotoUrl", profilePhoto);
                postData.put("biography", biography);
                postData.put("posts", null);

                firebaseFirestore.collection("users").add(postData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println("Succes");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Fail");
                    }
                });

        }
        else {
            System.out.println("KFmsdkfmkdsmfkasmfkaslda");
        }
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
