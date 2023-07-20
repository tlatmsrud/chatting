package org.ssk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.ssk.dto.ChatDto;
import org.ssk.service.WebSocketService;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-19
 * description  :
 */

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebSocketController {

    private final WebSocketService webSocketService;

    @MessageMapping("/send")
    public void send(@RequestBody ChatDto chatDto){
        webSocketService.send(chatDto);
   }
}
