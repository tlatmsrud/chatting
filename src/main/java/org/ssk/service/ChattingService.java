package org.ssk.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.ssk.dto.ChatDto;
import org.ssk.dto.ChatHistoryDto;
import org.ssk.dto.MemberDto;
import org.ssk.entity.ChattingRoomEntity;
import org.ssk.entity.MemberEntity;
import org.ssk.repository.ChattingRepository;
import org.ssk.repository.ChattingRoomRepository;
import org.ssk.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    private final MemberRepository memberRepository;

    private final ChattingRepository chattingRepository;

    private final RedisTemplate<String, ChatDto> redisTemplate;
    /**
     * 채팅방 조회하기
     * @param loginId - 로그인 ID
     * @param memberId - 대화 상대방 ID
     * @return 조회 또는 생성된 채팅방 ID
     */
    public Long getChattingRoom(Long loginId, Long memberId){

        Long roomId = findChattingRoom(loginId, memberId);

        if(roomId != null){
            return roomId;
        }

        ChattingRoomEntity chattingRoom = ChattingRoomEntity.builder()
            .fromMemberId(loginId)
            .toMemberId(memberId)
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

    /**
     * 모든 사용자 조회
     * @return 모든 사용자 리스트
     */
    public List<MemberDto> getMemberList(){
        List<MemberEntity> memberEntity = memberRepository.findAll();
        return memberEntity.stream().map(
                entity -> new MemberDto(entity.getId(), entity.getNickname(), entity.getProfileUrl()))
                .collect(Collectors.toList());
    }

    public List<ChatHistoryDto> getChatHistory(Long roomId) {
        List<ChatHistoryDto> dbList = getChatHistoryForDB(roomId);
        List<ChatHistoryDto> cachingList = getChatHistoryForRedis(roomId);

        dbList.addAll(cachingList);
        return dbList;
    }

    public List<ChatHistoryDto> getChatHistoryForDB(Long roomId){
        return chattingRepository.findByChattingRoomId(roomId);
    }

    public List<ChatHistoryDto> getChatHistoryForRedis(Long roomId){
        ListOperations<String, ChatDto> listOperations = redisTemplate.opsForList();
        List<ChatDto> list = listOperations.range(String.valueOf(roomId), 0, -1);

        return list.stream().map(
                chatDto -> new ChatHistoryDto(
                        chatDto.getWriterId()
                        , chatDto.getMessage()
                        , chatDto.getTime()))
                .collect(Collectors.toList());
    }

}
