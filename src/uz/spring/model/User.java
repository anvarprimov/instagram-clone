package uz.spring.model;

import java.util.ArrayList;
import java.util.UUID;

public class User extends BaseModel{
    private String number;
    private String userName;
    private String password;
    private ArrayList<Post> posts = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<UUID> likes = new ArrayList<>();
    private ArrayList<User> followers = new ArrayList<>();
    private ArrayList<User> following = new ArrayList<>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<UUID> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<UUID> likes) {
        this.likes = likes;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public ArrayList<User> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<User> following) {
        this.following = following;
    }

}
