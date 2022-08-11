package uz.spring.service;

import uz.spring.model.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class UserService implements BaseService<User>{
    public UserService() {
        this.addAutomaticly();
    }

    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }


    @Override
    public boolean add(User o) {
        this.users.add(o);
        return true;
    }

    @Override
    public void list() {
        System.out.println("---- USERS ----");
        for (int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);
            System.out.println(i + 1 + " - user");
            System.out.println("\tuser id:           " + user.getId());
            System.out.println("\tuser name:         " + user.getName());
            System.out.println("\tuser username:     " + user.getUserName());
            System.out.println("\tuser password:     " + user.getPassword());
            System.out.println("\tuser phone number: " + user.getNumber());
            System.out.println("\tuser last seen:    " + user.getDate());
            System.out.println();
        }
    }

    public void listFollowing(User currentUser) {
        System.out.println("---- FOLLOWING ----");
        ArrayList<User> following = currentUser.getFollowing();
        int cnt = 0;
        for (int i = following.size() - 1; i >= 0; i--) {
            User user = following.get(i);
            System.out.println(++cnt + " - user");
            System.out.println("\tuser id:           " + user.getId());
            System.out.println("\tuser name:         " + user.getName());
            System.out.println("\tuser username:     " + user.getUserName());
            System.out.println("\tuser phone number: " + user.getNumber());
            System.out.println("\tuser last seen:    " + user.getDate());
            System.out.println();
        }
    }

    public void listFollowers(User currentUser) {
        System.out.println("---- FOLLOWERS ----");
        ArrayList<User> followers = currentUser.getFollowers();
        int cnt = 0;
        for (int i = followers.size() - 1; i >= 0; i--) {
            User user = followers.get(i);
            System.out.println(++cnt + " - user");
            System.out.println("\tuser id:           " + user.getId());
            System.out.println("\tuser name:         " + user.getName());
            System.out.println("\tuser username:     " + user.getUserName());
            System.out.println("\tuser phone number: " + user.getNumber());
            System.out.println("\tuser last seen:    " + user.getDate());
            System.out.println();
        }
    }

    private void addAutomaticly(){
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId();
            user.setDate();
            user.setName(String.valueOf(i + 1) + this.getRandom());
            user.setUserName(String.valueOf(i + 1) + this.getRandom());
            user.setNumber(String.valueOf(i + 1) + this.getRandom());
            user.setPassword(String.valueOf(i + 1) + this.getRandom());
            this.add(user);
        }
    }
    private String getRandom(){
        Random random = new Random();
        String str = "men bu satrni ishning unumdorligini oshirish uchun yaratayapman va sizlarga ham shunday qilishni tafsiya qilaman";
        StringBuilder satr = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            satr.append(str.charAt(random.nextInt(str.length())));
        }
        return satr.toString().replace(' ', 'a');
    }
    public User signIn(UUID currentId, String password){
        for (int i = 0; i < this.users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(currentId) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public boolean addFollowing(UUID userId, User currentUser){
        for (int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);
            if (user.getId().equals(userId)){
                ArrayList<User> myFollowing = currentUser.getFollowing();
                ArrayList<User> hisFollowers = user.getFollowers();
                if(!myFollowing.contains(user) && currentUser != user){
                    myFollowing.add(user);          // add user my following list
                    hisFollowers.add(currentUser);  // add me user's followers list
                    return true;
                }
            }
        }
        return false;
    }
}
