package org.ssk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssk.annotation.LoginMemberId;
import org.ssk.dto.ChatHistoryDto;
import org.ssk.dto.MemberDto;
import org.ssk.service.ChattingService;

import java.util.List;

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

    @GetMapping("/room")
    @ResponseStatus(HttpStatus.OK)
    public Long getChattingRoom(@LoginMemberId Long loginId, @RequestParam Long memberId){
        return chattingService.getChattingRoom(loginId, memberId);
    }

    @GetMapping("/member")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDto> getMemberList(){
        return chattingService.getMemberList();
    }

    @GetMapping("/history")
    @ResponseStatus(HttpStatus.OK)
    public List<ChatHistoryDto> getChatHistory(@RequestParam Long roomId){
        return chattingService.getChatHistory(roomId);
    }
}
