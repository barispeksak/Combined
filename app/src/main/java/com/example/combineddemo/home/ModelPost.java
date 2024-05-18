package com.example.combineddemo.home;

import androidx.lifecycle.ViewModel;

import com.example.combineddemo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ModelPost extends ViewModel {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<ModelComment> postComment;
    final ModelUser sharer;
    String description;

    static int postId = 0;

    int sharerId;
    int profileImage,postImage;
    String sharerName;
    double score;
    public ModelPost(ModelUser sharer, String description, int postImage, double score) {

        postId++;
        this.sharerId = sharer.getUserId();
        this.description = description;
        this.profileImage = sharer.getProfilePhoto();
        this.postImage = postImage;
        this.sharerName = sharer.getUserName();
        this.score = score;
        this.postComment = new ArrayList<>();
        this.sharer = sharer;
        if(!description.isEmpty())
        {
            postComment.add(new ModelComment(this.sharer ,description ,false,false,true));
        }
    }

    public void addPostToUser(ModelUser sharer)
    {
        sharer.addSharedPosts(this);
    }

    public ArrayList<ModelComment> getPostComment() {
        return postComment;
    }

    public void addPostComment(ModelComment postComment) {
        this.postComment.add(postComment);
    }


    public int getSharerId() {
        return sharerId;
    }

    public void setSharerId(int sharerId) {
        this.sharerId = sharerId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public String getSharerName() {
        return sharerName;
    }

    public void setSharerName(String sharerName) {
        this.sharerName = sharerName;
    }


    public String getScore() {
        return String.format( "%.1f", this.score);
    }

    public void addScore(int score)
    {
        this.score += score;
    }

    public void removeScore(int score){this.score -= score;}

    public static ArrayList<ModelPost> getPostData()
    {
        ModelUser user1 = new ModelUser( "sebnem","jkfh","12345","helloooo",R.drawable.profilebackground);
        ModelUser user2 = new ModelUser( "user2","jkfh","12345","bio kdlsjf\ndjskj",R.drawable.ic_dashboard_black_24dp);
        ModelUser user3 = new ModelUser( "user3","jkfh","12345","helloooo\n sjdkdjh",R.drawable.ic_launcher_background);
        ModelUser user4 = new ModelUser( "user4","jkfh","12345","djjfj",R.drawable.testbackground);
        ModelUser user5 = new ModelUser( "user5","jkfh","12345","jslkjd",R.drawable.ic_launcher_background);

        user1.addFriend(user2);
        user1.addFriend(user3);
        user1.addFriend(user4);
        user1.addFriend(user5);

        user2.addFriend(user1);
        user2.addFriend(user5);
        user2.addFriend(user3);

        user4.addFriend(user1);
        user4.addFriend(user5);

        ArrayList<ModelComment> comments = new ArrayList<>();
        comments.add(new ModelComment(user4,"tesettestfriend",true,false,false));
        comments.add(new ModelComment(user3,"tesettestadvisor",false,true,false));
        comments.add(new ModelComment(user2,"tesettestRegular",false,false,false));
        comments.add(new ModelComment(user5,"tesettestadvisorandfriend",true,true,false));
        comments.add(new ModelComment(user3,"friendtest",true,false,false));
        comments.add(new ModelComment(user2,"tesettestfriendcomment",true,false,false));


        ArrayList<ModelPost> dataList = new ArrayList<>();
        ModelPost androidData1 = new ModelPost(user1,"123",  R.drawable.testbackground, 0);
        dataList.add(androidData1);
        androidData1.addPostComment(comments.get(0));
        androidData1.addPostComment(comments.get(1));
        androidData1.addPostComment(comments.get(2));
        androidData1.addPostComment(comments.get(3));
        androidData1.addPostComment(comments.get(4));
        androidData1.addPostComment(comments.get(5));

        ModelPost androidData2 = new ModelPost(user2,"123", R.drawable.counter_1_24px, 0);
        dataList.add(androidData2);
        ModelPost androidData3 = new ModelPost(user3,"123",   R.drawable.counter_2_24px,  0);
        dataList.add(androidData3);
        ModelPost androidData4 = new ModelPost(user4, "213",  R.drawable.counter_3_24px,  0 );
        dataList.add(androidData4);
        ModelPost androidData5 = new ModelPost(user5, "123",  R.drawable.counter_4_24px,  0 );
        dataList.add(androidData5);

        ModelPost androidData6 = new ModelPost(user1,"234",  R.drawable.testbackground, 0);
        dataList.add(androidData6);
        ModelPost androidData7 = new ModelPost(user1,"345",  R.drawable.testbackground, 0);
        dataList.add(androidData7);
        ModelPost androidData8 = new ModelPost(user1,"566",  R.drawable.testbackground, 0);
        dataList.add(androidData8);

        user1.addSharedPosts(androidData1);
        user2.addSharedPosts(androidData2);
        user3.addSharedPosts(androidData3);
        user4.addSharedPosts(androidData4);
        user5.addSharedPosts(androidData5);
        user1.addSharedPosts(androidData6);
        user1.addSharedPosts(androidData7);
        user1.addSharedPosts(androidData8);

        return dataList;
    }

    public ModelUser getSharer() {
        return sharer;
    }

    public void setScore(double score) {
        this.score = score;
    }
}