package org.ssk.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.ssk.dto.UserDto;

import static org.ssk.entity.QUser.user;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-19
 * description  :
 */

@RequiredArgsConstructor
public class LoginRepositoryImpl implements LoginRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public UserDto findByNickname(String nickname) {
        return queryFactory.select(
                    Projections.constructor(UserDto.class
                    ,user.id
                    ,user.nickname)
                ).from(user)
                .where(
                        eqNickname(nickname)
                ).fetchOne();
    }

    public BooleanExpression eqNickname(String nickname){
        return nickname == null ? null : user.nickname.eq(nickname);
    }
}
