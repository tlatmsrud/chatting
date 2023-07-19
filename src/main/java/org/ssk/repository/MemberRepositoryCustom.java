package org.ssk.repository;


import org.ssk.entity.MemberEntity;

/**
 * title        : MemberRepositoryCustom
 * author       : sim
 * date         : 2023-07-14
 * description  : MemberRepositoryCustom
 */
public interface MemberRepositoryCustom {
    MemberEntity findByEmail(String email);
}
