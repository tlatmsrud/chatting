package org.ssk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-21
 * description  :
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatHistoryDto {

    private Long memberId;

    private String message;

    private LocalDateTime time;

}
