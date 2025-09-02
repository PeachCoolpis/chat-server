package com.chatserverapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveReqDto {
    
    private String name;
    
    private String email;
    
    private String password;
}
