package uz.spring.front;

import uz.spring.model.Post;
import uz.spring.model.User;
import uz.spring.service.BaseService;
import uz.spring.service.PostService;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class PostFront implements BaseService {
    PostService postService = new PostService();
    Scanner scanInt = new Scanner(System.in);
    Scanner scanStr = new Scanner(System.in);

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public void list() {
        postService.list();
    }

    public int list(User user) {
        return postService.list(user);
    }

    public int friendsPostList(User currentUser){
        return postService.friendsPostList(currentUser);
    }

    public boolean addPost(User user) {
        Post post = new Post();
        post.setId();
        post.setDate();
        post.setUserId(user.getId());
        post.setUserName(user.getUserName());
        System.out.print("enter post theme -> ");
        post.setName(scanStr.nextLine());
        System.out.print("enter post text ->  ");
        post.setText(scanStr.nextLine());
        if (postService.addPost(post, user)){
            System.out.println();
            System.out.println("\tSUCCESSFULLY ADDED");
            System.out.println();
            return true;
        }
        else{
            System.out.println();
            System.out.println("\tFAILED");
            System.out.println();
            return false;
        }
    }

    public boolean addLike(User currentUser){
        System.out.print("Enter post id-> ");
        String id = scanStr.nextLine();
        int like = postService.addLike(currentUser, UUID.fromString(id));
        if (like == 1){
            System.out.println();
            System.out.println("\tLIKE SUCCESSFULLY ADDED");
            System.out.println();
            return true;
        }
        else if (like == -1){
            System.out.println();
            System.out.println("\tLIKE REMOVED");
            System.out.println();
            return true;
        }
        else{
            System.out.println();
            System.out.println("\tFAILED");
            System.out.println();
            return true;
        }
    }

    public ArrayList<Post> getPosts(){
        return postService.getPosts();
    }
}
