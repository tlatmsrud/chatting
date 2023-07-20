package org.ssk.dto;

import lombok.Getter;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-19
 * description  :
 */

@Getter
public class ChatDto {
    private Long roomId;
    private Long writerID;
    private String message;
}
