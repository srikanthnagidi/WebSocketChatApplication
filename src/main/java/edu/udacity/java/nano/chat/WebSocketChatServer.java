package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {
    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String msg) {
        //TODO: add send message method.

    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        onlineSessions.put(username,session);
        Message message = new Message();
        message.setFromUserName(username);
        message.setContent("!Connected");
        message.setOnlineCount(onlineSessions.size());
        sendMessageToAll(username + "is Connected");
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) throws JSONException {
        //TODO: add send message.
        final JSONObject obj = new JSONObject(jsonStr);
        String userName = obj.getString("username");
        String msg = obj.getString("msg");
        Message message = new Message(userName, msg);
        sendMessageToAll(message.getContent());
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        //TODO: add close connection.
        onlineSessions.remove(username);
        Message message = new Message();
        message.setFromUserName(username);
        message.setContent("Disconnected");
        message.setOnlineCount(onlineSessions.size());
        sendMessageToAll(username + "Disconnected");
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}