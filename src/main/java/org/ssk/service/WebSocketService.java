package org.ssk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.ssk.dto.ChatDto;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */

@Service
@RequiredArgsConstructor
public class WebSocketService {

    private final static String TOPIC = "/topic/";
    private final SimpMessagingTemplate simpMessagingTemplate;

    private final RedisTemplate<String ,ChatDto> redisTemplate;

    public void send(ChatDto chatDto) {
        simpMessagingTemplate.convertAndSend(TOPIC+chatDto.getRoomId(), chatDto.getMessage());
        redisTemplate.opsForList().rightPush(String.valueOf(chatDto.getRoomId()), chatDto);
    }


}
