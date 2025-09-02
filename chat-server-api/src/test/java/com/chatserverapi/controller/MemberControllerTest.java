package com.chatserverapi.controller;

import com.chatserverapi.ControllerTestSupport;
import com.chatserverapi.dto.MemberLoginReqDto;
import com.chatserverapi.dto.MemberSaveReqDto;
import com.chatserverapi.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class MemberControllerTest extends ControllerTestSupport {
    
    
    @Autowired
    private MemberService memberService;
    
    @DisplayName("")
    @Test
    void test() throws Exception {
        
        
        MemberSaveReqDto memberSaveReqDto = new MemberSaveReqDto("박지수", "ddd@naver.com", "비번");
        String s = objectMapper.writeValueAsString(memberSaveReqDto);
        
        mockMvc.perform(post("/member/create")
                .contentType(APPLICATION_JSON)
                .content(s)
        )
                .andDo(print())
        ;
    }
    
    @DisplayName("")
    @Test
    void test2() throws Exception {
        
        MemberLoginReqDto loginReqDto = new MemberLoginReqDto("ddd@naver.com", "비번");
        
        String s = objectMapper.writeValueAsString(loginReqDto);
        mockMvc.perform(post("/member/doLogin")
                .contentType(APPLICATION_JSON)
                .content(s)
        ).andDo(print());
        
        
    }
    

}