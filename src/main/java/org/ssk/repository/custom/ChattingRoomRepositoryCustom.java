package org.ssk.repository.custom;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */
public interface ChattingRoomRepositoryCustom {

    Long findIdByMemberId(Long fromMemberId, Long toMemberId);
}
