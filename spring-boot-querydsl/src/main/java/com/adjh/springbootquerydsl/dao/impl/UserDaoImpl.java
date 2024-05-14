package com.adjh.springbootquerydsl.dao.impl;

import com.adjh.springbootquerydsl.dao.UserDao;
import com.adjh.springbootquerydsl.dto.UserClubDto;
import com.adjh.springbootquerydsl.dto.UserDto;
import com.adjh.springbootquerydsl.dto.UserOrderDto;
import com.adjh.springbootquerydsl.dto.UserPassportDto;
import com.adjh.springbootquerydsl.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserDaoImpl
 * @since : 2/9/24
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final JPAQueryFactory queryFactory;
    private final QUserEntity qUser = QUserEntity.userEntity;
    private final QPassportEntity qPassport = QPassportEntity.passportEntity;
    private final QOrderEntity qOrder = QOrderEntity.orderEntity;
    private final QClubEntity qClub = QClubEntity.clubEntity;
    private final QUserClubMapEntity qUserClubMap = QUserClubMapEntity.userClubMapEntity;

    @PersistenceContext
    private EntityManager em;

    public UserDaoImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    /**
     * @param userDto
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> selectUserInfo(UserDto userDto) {
        return queryFactory
                .selectFrom(qUser)
//                .join(qUser.orders)
//                .fetchJoin()
                .where(qUser.userSq.eq(userDto.getUserSq()))
                .fetch();
    }


    @Override
    public List<UserEntity> selectUserClubAllList(UserDto userDto) {
        return queryFactory
                .select(qUser)
                .from(qUser, qClub)
                .where(qUser.userNm.eq(qClub.clubCaptainNm))
                .fetch();
    }


    /**
     * 사용자와 동아리 정보를 조회합니다(RIGHT JOIN)
     *
     * @param userDto
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserClubDto> selectUserClubListRight(UserDto userDto) {
        return queryFactory
                .select(Projections.fields(UserClubDto.class,
                        qUser.userId,
                        qUser.userNm,
                        qUser.userSt,
                        qClub.clubNm,
                        qClub.estDate,
                        qClub.clubDesc
                ))
                .from(qClub)
                .rightJoin(qUserClubMap).on(qClub.clubSq.eq(qUserClubMap.clubSq))
                .rightJoin(qUser).on(qUserClubMap.userSq.eq(qUser.userSq))
                .fetch();
    }


    /**
     * 사용자와 동아리 정보를 조회합니다.(LEFT JOIN)
     *
     * @param userDto
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserClubDto> selectUserClubList(UserDto userDto) {

        return queryFactory
                .select(Projections.fields(UserClubDto.class,
                        qUser.userId,
                        qUser.userNm,
                        qUser.userSt,
                        qClub.clubNm,
                        qClub.estDate,
                        qClub.clubDesc
                ))
                .from(qUser)
                .leftJoin(qUserClubMap).on(qUser.userSq.eq(qUserClubMap.userSq))
                .leftJoin(qClub).on(qUserClubMap.clubSq.eq(qClub.clubSq))
                .where(qUser.userSq.eq(userDto.getUserSq()))
                .fetch();
    }


    /**
     * 사용자의 주문정보를 조회합니다.
     *
     * @param userDto
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserOrderDto> selectUserOrderList(UserDto userDto) {
        return queryFactory
                .select(Projections.fields(UserOrderDto.class,
                        qUser.userId,
                        qUser.userNm,
                        qUser.userSt,
                        qOrder.orderSq,
                        qOrder.orderDate
                ))
                .from(qOrder)
                .innerJoin(qUser).on(qUser.userSq.eq(qOrder.userSq))
                .where(qUser.userSq.eq(userDto.getUserSq()))
                .fetch();
    }


    /**
     * 사용자 정보를 조회합니다.
     *
     * @param userDto
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public UserPassportDto selectUserPassport(UserDto userDto) {
        return queryFactory
                .select(Projections.fields(UserPassportDto.class,
                        qUser.userId,
                        qUser.userNm,
                        qUser.userSt,
                        qPassport.passportId,
                        qPassport.expiredDate,
                        qPassport.issueDate
                ))
                .from(qPassport)
                .innerJoin(qUser).on(qUser.userSq.eq(qPassport.userSq))
                .fetchJoin()
                .where(qUser.userSq.eq(userDto.getUserSq()))
                .fetchOne();
    }


    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> selectUserList(UserEntity userEntity) {
        List<UserEntity> userList = new ArrayList<>();
        return userList;
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity selectUserBySq(UserEntity userEntity) {

        return null;
    }

    @Override
    @Transactional
    public int insertUser(UserEntity userEntity) {
        int result = 0;
        return result;
    }

    @Override
    @Transactional
    public int updateUser(UserEntity userEntity) {
        int result = 0;
        try {
            em.persist(userEntity);
            result = 1;
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteUser(UserEntity userEntity) {
        int result = 0;
        try {
            em.remove(em.contains(userEntity) ? userEntity : em.merge(userEntity));
            result = 1;
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }
        return result;
    }


}
