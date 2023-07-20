package attributes;

import org.ssk.dto.ChattingRoomDto;
import org.ssk.entity.ChattingRoomEntity;

/**
 * title        : Text Fixture
 * author       : sim
 * date         : 2023-07-20
 * description  : 테스트 픽스처 관리 인터페이스
 */
public interface TestFixture {
    Long LOGIN_MEMBER_ID = 1L;
    Long VALID_MEMBER_ID = 2L;
    ChattingRoomDto CHATTING_ROOM_DTO = new ChattingRoomDto(LOGIN_MEMBER_ID, VALID_MEMBER_ID);
    Long CHATTING_ROOM_ID_FOR_LOGIN_MEMBER_AND_VALID_MEMBER = 1L;
    ChattingRoomEntity CHATTING_ROOM_ENTITY = new ChattingRoomEntity(CHATTING_ROOM_ID_FOR_LOGIN_MEMBER_AND_VALID_MEMBER
            , LOGIN_MEMBER_ID, VALID_MEMBER_ID);
}
