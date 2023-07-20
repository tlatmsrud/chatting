package org.ssk.service;

import attributes.TestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ssk.entity.ChattingRoomEntity;
import org.ssk.repository.ChattingRoomRepository;
import org.ssk.repository.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */
class ChattingServiceTest implements TestFixture{

    private ChattingRoomRepository chattingRoomRepository = mock(ChattingRoomRepository.class);

    private MemberRepository memberRepository = mock(MemberRepository.class);
    private final ChattingService chattingService = new ChattingService(chattingRoomRepository,memberRepository);

    @BeforeEach
    void setUp(){
        given(chattingRoomRepository.findIdByMemberId(LOGIN_MEMBER_ID, VALID_MEMBER_ID))
                .willReturn(CHATTING_ROOM_ID_FOR_LOGIN_MEMBER_AND_VALID_MEMBER);

        given(chattingRoomRepository.save(any(ChattingRoomEntity.class)))
                .willReturn(CHATTING_ROOM_ENTITY);
    }
    @Test
    @DisplayName("채팅방 조회")
    void getChattingRoom() {
        assertThat(chattingService.getChattingRoom(LOGIN_MEMBER_ID, VALID_MEMBER_ID))
                .isEqualTo(CHATTING_ROOM_ID_FOR_LOGIN_MEMBER_AND_VALID_MEMBER);
    }

    @Test
    @DisplayName("채팅방 찾기")
    void findChattingRoom() {
        assertThat(chattingService.findChattingRoom(LOGIN_MEMBER_ID, VALID_MEMBER_ID))
                .isEqualTo(CHATTING_ROOM_ID_FOR_LOGIN_MEMBER_AND_VALID_MEMBER);
    }
}