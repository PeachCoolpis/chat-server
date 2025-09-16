package com.chatserverapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class StompController {
    
    // DestinationVariable : @MessageMapping 어노테이션으로 정의된 websocket controller 내에서만 사용
    @MessageMapping("{roomId}")
    @SendTo("/topic/{roomId}") // 해당 roomId에 메시지를 발행하여 구독중인 클라이언트에게 메시지 전송
    public String sendMessage(@DestinationVariable Long roomId, String message) {
        log.info("message: {}", message);
        return message;
    }
}
