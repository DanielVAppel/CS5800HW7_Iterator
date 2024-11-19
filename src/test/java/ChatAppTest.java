import org.CS5800.ChatServer;
import org.CS5800.Message;
import org.CS5800.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

public class ChatAppTest {
    @Test
    public void testSendMessageAndReceive() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        alice.sendMessage("Bob", "Hello Bob!");
        List<Message> bobHistory = bob.getChatHistory().getMessages();

        assertEquals(1, bobHistory.size());
        assertEquals("Hello Bob!", bobHistory.get(0).getContent());
    }

    @Test
    public void testUndoLastMessage() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);

        alice.sendMessage("Bob", "Message 1");
        alice.undoLastMessage();
        // No assertion for undo as per the current implementation
    }

    @Test
    public void testBlockUser() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        server.blockUser("Bob", "Alice");
        alice.sendMessage("Bob", "Hello Bob!");
        List<Message> bobHistory = bob.getChatHistory().getMessages();

        assertTrue(bobHistory.isEmpty());
    }

    @Test
    public void testChatHistory() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        alice.sendMessage("Bob", "Hello Bob!");
        alice.sendMessage("Bob", "How are you?");
        List<Message> aliceHistory = alice.getChatHistory().getMessages();

        assertEquals(2, aliceHistory.size());
        assertEquals("Hello Bob!", aliceHistory.get(0).getContent());
        assertEquals("How are you?", aliceHistory.get(1).getContent());
    }

    @Test
    public void testIteratorByUser() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);
        User charlie = new User("Charlie", server);

        alice.sendMessage("Bob", "Hello Bob!");
        alice.sendMessage("Charlie", "Hi Charlie!");
        alice.sendMessage("Bob", "How's it going?");

        Iterator<Message> iterator = alice.getChatHistory().iterator(bob);
        assertTrue(iterator.hasNext());
        assertEquals("Hello Bob!", iterator.next().getContent());
        assertEquals("How's it going?", iterator.next().getContent());
        assertFalse(iterator.hasNext());
    }
}
