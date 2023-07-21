package org.ssk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


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
    private LocalDateTime time = LocalDateTime.now();

}
