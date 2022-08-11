package uz.spring.service;

import uz.spring.model.Message;
import uz.spring.model.Post;
import uz.spring.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostService implements BaseService{
    ArrayList<Post> posts = new ArrayList<>();

    public ArrayList<Post> getPosts() {
        return posts;
    }

    @Override
    public boolean add(Object o) {
        return true;
    }

    public boolean addPost(Post post, User user) {
        ArrayList<Post> userPosts = user.getPosts();
        this.posts.add(post);
        userPosts.add(post);
        return true;
    }

    @Override
    public void list() {
        System.out.println("---- POSTS ----");
        int cnt = 0;
        for (int i = this.posts.size() - 1; i >= 0 ; i--) {
            Post post = this.posts.get(i);
            System.out.println(++cnt + " - post");
            System.out.println("\tpost writer id :       " + post.getUserId());
            System.out.println("\tpost writer username : " + post.getUserName());
            //System.out.println("\tpost id:     " + post.getId());
            System.out.println("\tpost theme:  " + post.getName());
            //System.out.println("\tpost text:   " + post.getText());
            System.out.println("\tpost posted: " + post.getDate());
            System.out.println("\tpost liked:  " + post.getLikes());
            List<Message> messages = post.getPostMessages();
            System.out.println("\treplied by:  " + post.getPostMessages().size() + " user");
            System.out.println("\t\t-----------------");
        }
        System.out.println();
    }

    public int list(User user) {
        System.out.println("---- POSTS ----");
        ArrayList<Post> posts = user.getPosts();
        int cnt = 0;
        for (int i = posts.size() - 1; i >= 0 ; i--) {
            Post post = posts.get(i);
            System.out.println(++cnt + " - post");
            System.out.println("\tpost id:     " + post.getId());
            System.out.println("\tpost theme:  " + post.getName());
            System.out.println("\tpost text:   " + post.getText());
            System.out.println("\tpost posted: " + post.getDate());
            System.out.println("\tpost liked:  " + post.getLikes() + " by " + post.getLikes().size() + " others");
            System.out.println("\tpost writer id :       " + post.getUserId());
            System.out.println("\tpost writer username : " + post.getUserName());
            List<Message> messages = post.getPostMessages();
            int cnt2 = 0;
            for (int j = messages.size() - 1; j >= 0 ; j--) {
                Message comment = messages.get(i);
                System.out.println("\t\t" + ++cnt2 + " - comment");
                System.out.println("\t\tcomment:            " + comment.getName());
                System.out.println("\t\tcomment id:         " + comment.getId());
                System.out.println("\t\tcomment replied:    " + comment.getDate());
                System.out.println("\t\tcommented by:            " + comment.getSenderUserName());
                System.out.println("\t\tcommented user id:       " + comment.getSenderId());
            }
            System.out.println("\t\t-----------------");
        }
        System.out.println();
        return cnt;
    }

    public int friendsPostList(User currentUser) {
        System.out.println("---- FRIENDS' POSTS ----");
        ArrayList<User> following = currentUser.getFollowing();
        ArrayList<User> followers = currentUser.getFollowers();
        ArrayList<User> temp = new ArrayList<>();
        temp.addAll(following);
        temp.addAll(followers);
        temp.add(currentUser);

        int cnt = 0;
        for (int k = 0; k < temp.size(); k++) {
            User user = temp.get(k);
            ArrayList<Post> posts = user.getPosts();

            for (int i = posts.size() - 1; i >= 0; i--) {
                Post post = posts.get(i);
                System.out.println(++cnt + " - post");
                System.out.println("\tpost id:     " + post.getId());
                System.out.println("\tpost theme:  " + post.getName());
                System.out.println("\tpost text:   " + post.getText());
                System.out.println("\tpost posted: " + post.getDate());
                System.out.println("\tpost liked:            " + post.getLikes());
                System.out.println("\tpost writer id :       " + post.getUserId());
                System.out.println("\tpost writer username : " + post.getUserName());
                List<Message> messages = post.getPostMessages();
                int cnt2 = 0;

                for (int j = messages.size() - 1; j >= 0; j--) {
                    Message comment = messages.get(j);
                    System.out.println("\t\t" + ++cnt2 + " - comment");
                    System.out.println("\t\t\tcomment:        " + comment.getName());
                    System.out.println("\t\t\tcomment id:         " + comment.getId());
                    System.out.println("\t\t\tcomment replied:    " + comment.getDate());
                    System.out.println("\t\t\tcommented by:       " + comment.getSenderUserName());
                    System.out.println("\t\t\tcommented user id:       " + comment.getSenderId());
                }
                System.out.println("\t\t-----------------");
            }
            System.out.println();
        }
        return cnt;
    }

    public int addLike(User user, UUID postId){
        for (int i = 0; i < this.posts.size(); i++) {
            Post post = this.posts.get(i);
            if (post.getId().equals(postId)){
                ArrayList<UUID> userLikes = user.getLikes();
                ArrayList<String> postLikes = post.getLikes();
                if (!userLikes.contains(postId)){
                    userLikes.add(postId);
                    postLikes.add(user.getUserName());
                    return 1;
                }
                else {
                    userLikes.remove(postId);
                    postLikes.remove(user.getUserName());
                    return -1;
                }
            }
        }
        return 0;
    }
}
