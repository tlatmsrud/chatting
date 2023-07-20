package org.ssk.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
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
@Table(name = "TBL_CHATTING")
public class ChattingEntity extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHATTING_ROOM_ID")
    private ChattingRoomEntity chattingRoom;

    @Column(name = "FROM_MEMBER_ID")
    private Long fromMemberId;

    @Column(name = "SEND_MESSAGE")
    private String sendMessage;

    @Column(name = "TO_MEMBER_ID")
    private Long toMemberId;

    @Column(name = "RECEIVE_MESSAGE")
    private String receiveMessage;
}
