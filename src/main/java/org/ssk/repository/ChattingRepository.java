package org.ssk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssk.entity.ChattingEntity;
import org.ssk.repository.custom.ChattingRepositoryCustom;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */
public interface ChattingRepository extends JpaRepository<ChattingEntity, Long>, ChattingRepositoryCustom {
}
