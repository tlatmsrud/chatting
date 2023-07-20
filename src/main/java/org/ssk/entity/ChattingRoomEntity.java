package org.ssk.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */

@Entity
@Getter
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
