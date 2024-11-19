package org.CS5800;

// ChatServer.java - Mediator class
import java.util.*;

public class ChatServer {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Set<String>> blockedUsers = new HashMap<>();

    public void registerUser(User user) {
        users.put(user.getName(), user);
        blockedUsers.put(user.getName(), new HashSet<>());
    }

    public void unregisterUser(User user) {
        users.remove(user.getName());
        blockedUsers.remove(user.getName());
    }

    public void blockUser(String blocker, String blockee) {
        if (blockedUsers.containsKey(blocker)) {
            blockedUsers.get(blocker).add(blockee);
        }
    }

    public void sendMessage(String sender, String recipient, String content) {
        if (blockedUsers.getOrDefault(recipient, Collections.emptySet()).contains(sender)) {
            System.out.println("Message blocked: " + sender + " -> " + recipient);
            return;
        }
        User senderUser = users.get(sender);
        User recipientUser = users.get(recipient);
        if (recipientUser != null && senderUser != null) {
            Message message = new Message(sender, recipient, content, new Date());
            //senderUser.getChatHistory().addMessage(message); // Add to sender's ChatHistory
            recipientUser.getChatHistory().addMessage(message); // Add to recipient's ChatHistory
            recipientUser.receiveMessage(message); // Notify recipient of the message
        }
    }
}