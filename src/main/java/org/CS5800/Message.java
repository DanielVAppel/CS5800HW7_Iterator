package org.CS5800;

import java.util.Date;

public class Message {
    private final String sender;
    private final String recipient;
    private final String content;
    private final Date timestamp;

    public Message(String sender, String recipient, String content, Date timestamp) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}