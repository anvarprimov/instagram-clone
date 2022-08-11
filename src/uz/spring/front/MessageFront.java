package uz.spring.front;

import uz.spring.model.Message;
import uz.spring.model.Post;
import uz.spring.model.User;
import uz.spring.service.MessageService;
import uz.spring.service.PostService;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class MessageFront {
    MessageService messageService = new MessageService();
    PostService postService = new PostService();
    Scanner scanInt = new Scanner(System.in);
    Scanner scanStr = new Scanner(System.in);

    public void addComment(User senderUser, ArrayList<Post> posts){
        Message message = new Message();
        message.setId();
        message.setDate();
        message.setSenderId(senderUser.getId());
        message.setSenderUserName(senderUser.getUserName());
        System.out.print("enter post id  ->     ");
        String id = scanStr.nextLine();
        message.setReceiverId(UUID.fromString(id));
        System.out.print("enter comment text -> ");
        message.setName(scanStr.nextLine());
        if (messageService.addComment(message, senderUser, posts)){
            System.out.println();
            System.out.println("\tCOMMENT SUCCESSFULLY ADDED");
            System.out.println();
        } else{
            System.out.println();
            System.out.println("\tFAILED");
            System.out.println();
        }
    }

    public void addMessage(User currentUser, ArrayList<User> users){
        System.out.print("enter receiver user id  -> ");
        String id = scanStr.nextLine();
        UUID receiverId = UUID.fromString(id);
        User receiverUser = messageService.getUserById(receiverId, users);
        if (receiverUser != null && currentUser != receiverUser) {
            ArrayList<Message> senderUserMessages = currentUser.getMessages();
            messageService.printMessage(currentUser, senderUserMessages, receiverUser);
            int step = 1;
            while (step == 1) {
                Message message = new Message();
                message.setId();
                message.setDate();
                message.setSenderId(currentUser.getId());
                message.setSenderUserName(currentUser.getUserName());
                message.setReceiverId(receiverId);
                message.setReceiverUserName(receiverUser.getUserName());
                System.out.print("enter message text ->      ");
                message.setName(scanStr.nextLine());
                if (messageService.addMessage(currentUser, receiverUser, message)) {
                    messageService.printMessage(currentUser, senderUserMessages, receiverUser);
                    System.out.print("1-> again/0-> stop ------> ");
                    step = scanInt.nextInt();
                } else {
                    System.out.println("FAILED");
                    step = 0;
                }
            }
        }
    }
}
