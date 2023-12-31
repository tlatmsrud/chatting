package org.ssk.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.ssk.entity.MemberEntity;
import org.ssk.repository.custom.MemberRepositoryCustom;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-01
 * description  :
 */
public interface MemberRepository extends JpaRepository<MemberEntity, Long>, MemberRepositoryCustom {


}
