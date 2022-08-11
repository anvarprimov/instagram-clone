package uz.spring.front;

import uz.spring.model.User;
import uz.spring.service.BaseService;
import uz.spring.service.PostService;
import uz.spring.service.UserService;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class UserFront implements BaseService {
    Scanner scanInt = new Scanner(System.in);
    Scanner scanStr = new Scanner(System.in);
    UserService userService = new UserService();
    PostFront postFront = new PostFront();
    MessageFront messageFront = new MessageFront();

    @Override
    public boolean add(Object o) {
        User user = new User();
        user.setId();
        user.setDate();
        System.out.print("enter user name ->         ");
        user.setName(scanStr.nextLine());
        System.out.print("enter user username ->     ");
        user.setUserName(scanStr.nextLine());
        System.out.print("enter user phone number -> ");
        user.setNumber(scanStr.nextLine());

        System.out.print("enter password -> ");
        String password = scanStr.nextLine();

        String check = "n";
        char ch;
        boolean hasUpper;
        boolean hasLower;
        boolean hasDigit;
        boolean hasLength;
        while (!check.equalsIgnoreCase("y")){
            hasUpper = hasLower = hasDigit = hasLength = false;

            if (password.length() >= 8){
                hasLength = true;
            }
            for (int i = 0; i < password.length(); i++) {
                ch = password.charAt(i);
                if (Character.isUpperCase(ch)){
                    hasUpper = true;
                }
                if (Character.isLowerCase(ch)){
                    hasLower = true;
                }
                if (Character.isDigit(ch)){
                    hasDigit = true;
                }
            }

            if (hasLower && hasDigit && hasUpper && hasLength){
                System.out.print("GOOD PASSWORD-  choose apply password or no!!! -> y/n");
                check = scanStr.nextLine();
            }
            if (!check.equalsIgnoreCase("y")){
                System.out.print("enter strong password or 'help' -> ");
                password = scanStr.nextLine();
            }

            if (password.equalsIgnoreCase("help")){
                System.out.println("\t\tMust have \n" +
                                                "\t\t\tat least 8 characters\n" +
                                                "\t\t\tat least 1 LOWER case\n" +
                                                "\t\t\tat least 1 UPPER case\n" +
                                                "\t\t\tat least 1 DIGIT case");
            }
        }
        if (check.equalsIgnoreCase("y")){
            user.setPassword(password);
            userService.add(user);
            System.out.println();
            System.out.println("\tSUCCESSFULLY ADDED");
            System.out.println();
            return true;
        } else{
            System.out.println();
            System.out.println("\tFAILED");
            System.out.println();
            return false;
        }
    }

    @Override
    public void list() {
        userService.list();
    }

    public void listFollowing(User currentUser) {
        userService.listFollowing(currentUser);
    }

    public void listFollowers(User currentUser) {
        userService.listFollowers(currentUser);
    }

    public User signIn(){
        System.out.print("User Id-> ");
        String id = scanStr.nextLine();
        System.out.print("User password-> ");
        String password = scanStr.nextLine();
        User currenUser = userService.signIn(UUID.fromString(id),password);
        if (currenUser != null){
            System.out.println();
            System.out.println("\tSUCCESSFULLY ENTERED");
            System.out.println();
            return currenUser;
        }
        else{
            System.out.println();
            System.out.println("\tFAILED");
            System.out.println();
            return null;
        }
    }

    public boolean addFollowing(User currentUser){
        System.out.print("Enter chosen user id-> ");
        String id = scanStr.nextLine();
        if (userService.addFollowing(UUID.fromString(id), currentUser)){
            System.out.println();
            System.out.println("\tSUCCESSFULLY FOLLOWED");
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

    public ArrayList<User> getUsers(){
        return userService.getUsers();
    }

    public void searchUser(User currenUser, ArrayList<User> users){
        System.out.print("Enter chosen user id-> ");
        UUID userId = UUID.fromString(scanStr.nextLine());
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(userId)){
                int cnt = postFront.list(user);
                if (cnt > 0) {
                    System.out.print("\t\t\t\tlike / reply / follow / no \n\tchoose actions ---> ");
                    String action = scanStr.nextLine().toLowerCase();
                    if ( action.equals("like")){
                        postFront.addLike(currenUser);
                        return;
                    }else if ( action.equals("reply")){
                        messageFront.addComment(currenUser, postFront.getPosts());
                        return;
                    }else if ( action.equals("follow")){
                        this.addFollowing(currenUser);
                        return;
                    }
                }
            }
        }
        System.out.println("FAILED");
    }
}
