package org.ssk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * title        : Member entity
 * author       : sim
 * date         : 2023-06-30
 * description  : 회원 엔티티
 */

@Entity
@Table(name = "TBL_MEMBER")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberEntity extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "NICKNAME", nullable = false)
    private String nickname;

    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PROFILE_URL")
    private String profileUrl;

}
