package org.ssk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBL_CHATTING_ROOM")
public class ChattingRoomEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "FROM_MEMBER_ID")
    private Long fromMemberId;

    @Column(name = "TO_MEMBER_ID")
    private Long toMemberId;

}
