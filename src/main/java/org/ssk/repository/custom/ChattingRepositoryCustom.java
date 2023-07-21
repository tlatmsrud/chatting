package org.ssk.repository.custom;

import org.ssk.dto.ChatHistoryDto;

import java.util.List;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */
public interface ChattingRepositoryCustom {

    List<ChatHistoryDto> findByChattingRoomId(Long roomId);
}
