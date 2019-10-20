package edu.udacity.java.nano.chat;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */
@Component
@ServerEndpoint(value="/chat/{username}")
public class WebSocketChatServer {
    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String msg) {
        //TODO: add send message method.
        onlineSessions.forEach((k, v) -> {
            try {
                onlineSessions.get(k).getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        onlineSessions.put(username,session);
        Message message = new Message();
        message.setFromUserName(username);
        message.setContent("is Connected");
        message.setType("ENTER");
        message.setOnlineCount(onlineSessions.size());
        sendMessageToAll(convertToJSON(message));
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) throws JSONException {
        //TODO: add send message.

        JSONObject obj = new JSONObject(jsonStr);
        String userName = obj.getString("username");
        String msg = obj.getString("msg");
        Message message = new Message(userName, msg);
        message.setType("SPEAK");
        message.setOnlineCount(onlineSessions.size());
        sendMessageToAll(convertToJSON(message));
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
        message.setContent("left from chat");
        message.setType("LEAVE");
        message.setOnlineCount(onlineSessions.size());
        sendMessageToAll(convertToJSON(message));
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
    static String convertToJSON(Message message){
        return new Gson().toJson(message);
    }
}