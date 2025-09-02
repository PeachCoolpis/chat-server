package com.chatserverapi.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CHAT_ROOM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {
    
    // 채팅 방 ID
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAT_ROOM_ID")
    private Long id;
    
    // 그룹 오픈 방 여부
    private String groupsOpenRoomIs;
    
    // 채팅 방 이름
    private String chatRoomName;
}
