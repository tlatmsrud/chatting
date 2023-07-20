package org.ssk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssk.entity.ChattingRoomEntity;
import org.ssk.repository.custom.ChattingRoomRepositoryCustom;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */
public interface ChattingRoomRepository extends JpaRepository<ChattingRoomEntity, Long>
        , ChattingRoomRepositoryCustom {
}
