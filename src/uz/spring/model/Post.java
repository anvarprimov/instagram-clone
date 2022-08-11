package uz.spring.model;

import java.util.ArrayList;
import java.util.UUID;

public class Post extends BaseModel{
    private UUID userId;
    private String text;
    private String userName;
    private ArrayList<String> likes = new ArrayList<>();
    private ArrayList<Message> postMessages = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<String> likes) {
        this.likes = likes;
    }

    public ArrayList<Message> getPostMessages() {
        return postMessages;
    }

    public void setPostMessages(ArrayList<Message> postMessages) {
        this.postMessages = postMessages;
    }
}
