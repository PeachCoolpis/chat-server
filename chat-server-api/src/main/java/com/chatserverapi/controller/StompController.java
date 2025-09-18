package com.chatserverapi.controller;

import com.chatserverapi.dto.ChatMessageReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StompController {
    
    private final SimpMessageSendingOperations messageTemplate;
    
    // 방법1. MessageMapping(수신)과 Sento(topic에 메시지전달) 한꺼번에 처리
    
    // DestinationVariable : @MessageMapping 어노테이션으로 정의된 websocket controller 내에서만 사용
//    @MessageMapping("{roomId}")
//    @SendTo("/topic/{roomId}") // 해당 roomId에 메시지를 발행하여 구독중인 클라이언트에게 메시지 전송
//    public String sendMessage(@DestinationVariable Long roomId, String message) {
//        log.info("message: {}", message);
//        return message;
//    }
    
    //방법2. MessageMapping어노테이션만 활용
    @MessageMapping("/{roomId}")
    public void sendMessage(@DestinationVariable Long roomId, ChatMessageReqDto reqDto) {
        log.info("sendMessage: {}", reqDto);
        messageTemplate.convertAndSend("/api/topic/" + roomId, reqDto);
    }
}
