package org.ssk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-19
 * description  :
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto implements Serializable {

    @NotBlank
    private Long roomId;

    @NotBlank
    private Long writerId;

    @NotNull
    private String message;
    private String time = LocalDateTime.now().format(
            DateTimeFormatter.ISO_LOCAL_DATE_TIME
    );
}
