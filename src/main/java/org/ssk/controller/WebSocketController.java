package org.ssk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ssk.dto.ChatDto;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-19
 * description  :
 */

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/chat-page")
    public String getChatPage(){
        return "chat.html";
    }

    @MessageMapping("/send")
    public void send(@RequestBody ChatDto chatDto){
        simpMessagingTemplate.convertAndSend("/topic/"+chatDto.getRoomId(), chatDto.getChat());
    }
}
