package org.ssk.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssk.dto.ChattingRoomDto;
import org.ssk.entity.ChattingRoomEntity;
import org.ssk.repository.ChattingRoomRepository;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class ChattingService {

    private final ChattingRoomRepository chattingRoomRepository;

    /**
     * 채팅방 조회하기
     * @param request - 채팅방 조회 요청 Dto
     * @return 조회 또는 생성된 채팅방 ID
     */
    public Long getChattingRoom(ChattingRoomDto request){

        Long roomId = findChattingRoom(request.getFromMemberId(), request.getToMemberId());

        if(roomId != null){
            return roomId;
        }

        ChattingRoomEntity chattingRoom = ChattingRoomEntity.builder()
            .fromMemberId(request.getFromMemberId())
            .toMemberId(request.getToMemberId())
            .build();

        return chattingRoomRepository.save(chattingRoom).getId();
    }

    /**
     * 채팅방 찾기
     * @param fromMemberId - 본인 유저 ID
     * @param toMemberId - 상대방 유저 ID
     * @return 채팅방 ID
     */
    public Long findChattingRoom(Long fromMemberId, Long toMemberId){
        return chattingRoomRepository.findIdByMemberId(fromMemberId, toMemberId);
    }

}
