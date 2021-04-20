package server;

import entity.Message;
import server.decoder.MessageDecoder;
import server.encoder.MessageEncoder;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint(value = "/users",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class)
public class Server {

    private static List<Session> sessions = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(Message message) {
        sessions.forEach(s -> {
            try {
                s.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        });
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }
}