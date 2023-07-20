package org.ssk.repository.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static org.ssk.entity.QChattingRoomEntity.chattingRoomEntity;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */

@RequiredArgsConstructor
public class ChattingRoomRepositoryImpl implements ChattingRoomRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Long findIdByMemberId(Long fromMemberId, Long toMemberId) {
        return queryFactory.select(chattingRoomEntity.id)
                .from(chattingRoomEntity)
                .where(
                        eqMemberId(fromMemberId, toMemberId)
                ).fetchOne();
    }

    public BooleanExpression eqMemberId(Long fromMemberId, Long toMemberId){
        return fromMemberId == null || toMemberId == null ? null :
                chattingRoomEntity.fromMemberId.eq(fromMemberId).and(chattingRoomEntity.toMemberId.eq(toMemberId))
                        .or(chattingRoomEntity.fromMemberId.eq(toMemberId).and(chattingRoomEntity.toMemberId.eq(fromMemberId)));
    }
}
