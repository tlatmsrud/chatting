package org.ssk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssk.dto.ChattingRoomDto;
import org.ssk.service.ChattingService;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatting")
public class ChattingController {

    private final ChattingService chattingService;

    @PostMapping("/room")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createChattingRoom(@RequestBody ChattingRoomDto.Create request){
        return chattingService.createChattingRoom(request);
    }

}
