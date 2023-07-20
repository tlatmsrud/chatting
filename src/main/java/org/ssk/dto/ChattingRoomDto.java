package org.ssk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */

@Getter
public class ChattingRoomDto {


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{
        private Long fromMemberId;
        private Long toMemberId;
    }
}
