package com.adjh.springbootjpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : UserClubMapEntity
 * @since : 2024. 5. 8.
 */
@Entity
@Table(name = "tb_user_club_map")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserClubMapEntity {

    @Id
    private long userSq;

    @Id
    private long clubSq;

}
