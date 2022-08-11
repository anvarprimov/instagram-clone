package uz.spring.service;

import uz.spring.model.Message;
import uz.spring.model.Post;
import uz.spring.model.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class MessageService{
    Scanner scanStr = new Scanner(System.in);

    public boolean addComment(Message message, User senderUser, ArrayList<Post> posts) {
        UUID receiverId = message.getReceiverId();
        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            if (post.getId().equals(receiverId)){
                ArrayList<Message> messages = senderUser.getMessages();
                ArrayList<Message> comments = post.getPostMessages();
                messages.add(message);
                comments.add(message);
                return true;
            }
        }
        return false;
    }

    public boolean addMessage(User currentUser, User receiverUser, Message message) {
        ArrayList<Message> senderUserMessages = currentUser.getMessages();
        ArrayList<Message> receiverUserMessages = receiverUser.getMessages();
        senderUserMessages.add(message);
        receiverUserMessages.add(message);
        return true;
    }

    public void printMessage(User currentUser, ArrayList<Message> senderUserMessages, User receiverUser){
        int cnt2 = 0;
        for (int j = 0; j < senderUserMessages.size(); j++) {
            Message message = senderUserMessages.get(j);
            if (message.getReceiverId().equals(receiverUser.getId())){
                System.out.println("\t\t\t\t\t\t\t\t\t\t" + ++cnt2 + " - message");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\tmessage:    " + message.getName());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\ttime:       " + message.getDate());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\tsent by:    " + message.getSenderUserName());
            }else if (message.getReceiverId().equals(currentUser.getId())){
                System.out.println("\t" + ++cnt2 + " - message");
                System.out.println("\t\tmessage:    " + message.getName());
                System.out.println("\t\ttime:       " + message.getDate());
                System.out.println("\t\tsent by:    " + message.getSenderUserName());
            }
        }
    }

    public User getUserById(UUID receiverId, ArrayList<User> users){
        for (int i = 0; i < users.size(); i++) {
            User receiverUser = users.get(i);
            if (receiverUser.getId().equals(receiverId)){
                return receiverUser;
            }
        }
        return null;
    }
}
