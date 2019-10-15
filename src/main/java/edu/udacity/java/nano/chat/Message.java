package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.

    private String fromUserName;
    private String content;
    private String type;

    public  Message(){}
    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Message(String fromUserName, String content) {
        this.fromUserName = fromUserName;
        this.content = content;
    }

    public Message(String fromUserName, String content, String type) {
        this.fromUserName = fromUserName;
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
