package org.ssk.repository.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.ssk.dto.ChatDto;
import org.ssk.dto.ChatHistoryDto;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.ssk.entity.QChattingEntity.chattingEntity;


/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */

@RequiredArgsConstructor
public class ChattingRepositoryImpl implements ChattingRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<ChatHistoryDto> findByChattingRoomId(Long roomId) {
        return queryFactory.select(Projections.constructor(
                ChatHistoryDto.class
                ,chattingEntity.fromMemberId
                ,chattingEntity.sendMessage
                ,chattingEntity.sendTime))
                .from(chattingEntity)
                .where(eqRoomId(roomId))
                .fetch();
    }

    @Override
    public void saveAllChatting(List<ChatDto> list) {

        jdbcTemplate.batchUpdate(
                "INSERT INTO TBL_CHATTING(ID, CHATTING_ROOM_ID, FROM_MEMBER_ID, SEND_MESSAGE, SEND_DATE, CREATE_DATE)"+
                        "VALUES(NEXTVAL('TBL_CHATTING_SEQ'),?, ?, ?, ?, ?)"
                ,list
                ,10
                ,(PreparedStatement ps, ChatDto chatDto) -> {
                    ps.setLong(1, chatDto.getRoomId());
                    ps.setLong(2, chatDto.getWriterId());
                    ps.setString(3, chatDto.getMessage());
                    ps.setTimestamp(4, Timestamp.valueOf(chatDto.getTime()));
                    ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
                });

    }


    public BooleanExpression eqRoomId(Long roomId){
        return roomId == null ? null : chattingEntity.chattingRoom.id.eq(roomId);
    }
}
