package org.ssk.repository.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.ssk.dto.ChatHistoryDto;

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

    public BooleanExpression eqRoomId(Long roomId){
        return roomId == null ? null : chattingEntity.chattingRoom.id.eq(roomId);
    }
}
