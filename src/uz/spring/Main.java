package uz.spring;

import uz.spring.front.PostFront;
import uz.spring.front.UserFront;
import uz.spring.front.MessageFront;
import uz.spring.model.User;
import uz.spring.service.BaseService;
//test un
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanInt = new Scanner(System.in);
        Scanner scanStr = new Scanner(System.in);
        UserFront userFront = new UserFront();
        PostFront postFront = new PostFront();
        MessageFront messageFront = new MessageFront();

        byte step = 1;
        while (step != 0){
            System.out.println("1-> Sign up\n2-> Sign in\n3-> User list\n");
            step = scanInt.nextByte();
            switch (step){
                case 1->{
                    userFront.add(null);
                    break;
                }
                case 2->{
                    User currenUser = userFront.signIn();
                    if (currenUser == null){
                        break;
                    }
                    byte step1 = 1;
                    while (step1 != 0){
                        System.out.println("\t\t\t\tUSER ----> " + currenUser.getName());
                        System.out.println("1-> Home page\n2-> Search\n3-> Add post" +
                                            "\n4-> Last activities\n5-> Profile\n0-> Exit");
                        step1 = scanInt.nextByte();
                        switch (step1){
                            case 1->{
                                System.out.println("\t\tHOME PAGE :  USER ----> " + currenUser.getName());
                                int cnt = postFront.friendsPostList(currenUser);
                                if (cnt > 0) {
                                    System.out.print("\t\t\t\tlike / reply / no \n\tchoose actions ---> ");
                                    String action = scanStr.nextLine().toLowerCase();
                                    if ( action.equals("like")){
                                        postFront.addLike(currenUser);
                                        break;
                                    }else if ( action.equals("reply")){
                                        messageFront.addComment(currenUser, postFront.getPosts());
                                        break;
                                    }
                                }
                                break;
                            }
                            case 2->{
                                System.out.println("\t\tSEARCH :  USER ----> " + currenUser.getName());
                                postFront.list();
                                System.out.print("\t\t\t\tsearch / no \n\tchoose actions ---> ");
                                String action = scanStr.nextLine().toLowerCase();
                                if ( action.equals("search")){
                                    userFront.searchUser(currenUser, userFront.getUsers());
                                }
                                break;
                            }
                            case 3->{
                                System.out.println("\t\tADD P0STS :  USER ----> " + currenUser.getName());
                                postFront.addPost(currenUser);
                                break;
                            }
                            case 4->{
                                System.out.println("\t\tLAST ACTIVITIES :  USER ----> " + currenUser.getName());
                                break;
                            }
                            case 5->{
                                System.out.println("\t\tPROFILE :  USER ----> " + currenUser.getName());
                                byte step2 = 1;
                                while (step2 != 0){
                                    System.out.println("1-> Posts\n2-> Followers\n3-> Following" +
                                                    "\n4-> Profile\n0-> Exit");
                                    step2 = scanInt.nextByte();
                                    switch (step2){
                                        case 1->{
                                            System.out.println("\t\tPROFILE / POSTS :  USER ----> " + currenUser.getName());
                                            int cnt = postFront.list(currenUser);
                                            if (cnt > 0) {
                                                System.out.print("\t\t\t\tlike / reply / no \n\tchoose actions ---> ");
                                                String action = scanStr.nextLine().toLowerCase();
                                                if ( action.equals("like")){
                                                    postFront.addLike(currenUser);
                                                    break;
                                                }else if ( action.equals("reply")){
                                                    messageFront.addComment(currenUser, postFront.getPosts());
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        case 2->{
                                            System.out.println("\t\tPROFILE / FOLLOWERS :  USER ----> " + currenUser.getName());
                                            userFront.listFollowers(currenUser);
                                            System.out.println("follow / message / no ==> choose to follow back some one");
                                            String action = scanStr.nextLine();
                                            if (action.equals("follow")){
                                                userFront.addFollowing(currenUser);
                                            } else if (action.toLowerCase().equals("message")){
                                                messageFront.addMessage(currenUser, userFront.getUsers());
                                            }
                                            break;
                                        }
                                        case 3->{
                                            System.out.println("\t\tPROFILE / FOLLOWING :  USER ----> " + currenUser.getName());
                                            userFront.listFollowing(currenUser);
                                            System.out.println("follow / message / no ==> choose to follow some one");
                                            String action = scanStr.nextLine();
                                            if (action.equals("follow")){
                                                userFront.addFollowing(currenUser);
                                            } else if (action.toLowerCase().equals("message")){
                                                messageFront.addMessage(currenUser, userFront.getUsers());
                                            }
                                            break;
                                        }
                                        case 4->{
                                            System.out.println("\t\tPROFILE / PROFILE :  USER ----> " + currenUser.getName());
                                            System.out.println(4);
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                            default -> {
                                currenUser.setDate();
                            }
                        }
                    }
                    break;
                }
                case 3->{
                    userFront.list();
                    break;
                }
            }
        }
    }
}
