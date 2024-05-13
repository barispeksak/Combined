package com.example.combineddemo.home;

public class ModelComment {
    static int commentId = 0;

    String commentText;
    boolean isFriendComment, isAdvisorComment, isDesciption;
    ModelUser sharer;
    int sharerId;
    String sharerName;

    public ModelComment( ModelUser sharer, String commentText, boolean isFriendComment, boolean isAdvisorComment, boolean isDesciption) {

        commentId++;
        this.sharer = sharer;
        this.commentText = commentText;
        this.isFriendComment = isFriendComment;
        this.isAdvisorComment = isAdvisorComment;
        this.isDesciption = isDesciption;
        this.sharerId = sharer.getUserId();
        this.sharerName = sharer.getUserName();
    }


    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public boolean getIsFriendComment() {
        return isFriendComment;
    }

    public void setFriendComment(boolean friendComment) {
        isFriendComment = friendComment;
    }

    public boolean getIsAdvisorComment() {
        return isAdvisorComment;
    }

    public void setAdvisorComment(boolean advisorComment) {
        isAdvisorComment = advisorComment;
    }

    public boolean getIsDesciption() {
        return isDesciption;
    }

    public void setDesciption(boolean desciption) {
        isDesciption = desciption;
    }

    public int getSharerId() {
        return sharerId;
    }

    public void setSharerId(int sharerId) {
        this.sharerId = sharerId;
    }

    public String getSharerName() {
        return sharerName;
    }

    public void setSharerName(String sharerName) {
        this.sharerName = sharerName;
    }

    public ModelUser getSharer() {
        return sharer;
    }
}
