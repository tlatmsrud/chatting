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
                        eqFromMemberId(fromMemberId)
                        ,eqToMemberId(toMemberId)
                ).fetchOne();
    }

    public BooleanExpression eqFromMemberId(Long fromMemberId){
        return fromMemberId == null ? null : chattingRoomEntity.fromMemberId.eq(fromMemberId);
    }

    public BooleanExpression eqToMemberId(Long toMemberId){
        return toMemberId == null ? null : chattingRoomEntity.toMemberId.eq(toMemberId);
    }
}
