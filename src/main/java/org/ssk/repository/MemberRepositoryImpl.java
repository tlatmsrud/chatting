package org.ssk.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.ssk.entity.MemberEntity;

import static org.ssk.entity.QMemberEntity.memberEntity;


/**
 * title        : MemberRepositoryImpl
 * author       : sim
 * date         : 2023-07-14
 * description  : MemberRepositoryCustom 구현체 클래스
 */

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public MemberEntity findByEmail(String email) {
        return queryFactory.selectFrom(memberEntity)
                .where(
                        eqEmail(email)
                ).fetchOne();
    }

    public BooleanExpression eqEmail(String email){
        return email == null ? null : memberEntity.email.eq(email);
    }
}
