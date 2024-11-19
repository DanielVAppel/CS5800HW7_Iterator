package org.CS5800;

public class ChatAppDriver {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();

        User alice = new User("Alice", server);
        User bob = new User("Bob", server);
        User charlie = new User("Charlie", server);

        // Example: Sending messages
        alice.sendMessage("Bob", "Hello Bob!");
        bob.sendMessage("Alice", "Hi Alice!");
        alice.sendMessage("Charlie", "Hi Charlie!");

        // Example: Searching messages by user
        alice.searchMessagesByUser(bob); // Should show only messages between Alice and Bob
        alice.searchMessagesByUser(charlie); // Should show only messages between Alice and Charlie
    }
}

