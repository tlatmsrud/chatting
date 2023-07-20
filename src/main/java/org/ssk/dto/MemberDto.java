package org.ssk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */

@Getter
@AllArgsConstructor
public class MemberDto {

    private Long id;

    private String nickname;

    private String profileUrl;
}
