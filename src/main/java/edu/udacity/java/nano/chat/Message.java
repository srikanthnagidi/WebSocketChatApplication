package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.

    private String fromUserName;
    private String content;
    private String type;
    private int onlineCount;

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

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public Message(String fromUserName, String content, String type) {
        this.fromUserName = fromUserName;
        this.content = content;
        this.type = type;
    }

    public Message(String fromUserName, int onlineCount) {
        this.fromUserName = fromUserName;
        this.onlineCount = onlineCount;
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
