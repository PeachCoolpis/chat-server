package com.chatserverapi.chat.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SimpleWebSocketHandler extends TextWebSocketHandler {
    
    private final Set<WebSocketSession> socketSessions =  ConcurrentHashMap.newKeySet();
    
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        socketSessions.add(session);
        log.info("Connected to = {}", session.getId());
    }
 
    
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("Message received: {}", payload);
        
        socketSessions.stream()
                .filter(WebSocketSession::isOpen)
                .forEach(item -> {
                    try {
                        item.sendMessage(new TextMessage(payload));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
    
    
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    
        socketSessions.remove(session);
        log.info("Disconnected from = {}", session.getId());
    }
    
}
