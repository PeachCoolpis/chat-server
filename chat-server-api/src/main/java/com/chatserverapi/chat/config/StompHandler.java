package com.chatserverapi.chat.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {
    
    @Value("${jwt.secret}")
    private String secretKey;
    
    private final JwtDecoder jwtDecoder;
    
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String bearerToken = accessor.getFirstNativeHeader("Authorization");
        
        if (StompCommand.CONNECT == accessor.getCommand()) {
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                log.info("connect 요청시 토큰 유효성 검증");
                String token = bearerToken.substring(7);
                //토큰 검증
                jwtDecoder.decode(token);
                log.info("토큰 검증 완료");
            }
            
        }
        
        return message;
    }
}
