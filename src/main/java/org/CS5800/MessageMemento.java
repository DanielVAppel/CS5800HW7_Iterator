package org.CS5800;

import java.util.Date;

// MessageMemento.java
public class MessageMemento {
    private final String content;
    private final Date timestamp;

    public MessageMemento(Message message) {
        this.content = message.getContent();
        this.timestamp = message.getTimestamp();
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}