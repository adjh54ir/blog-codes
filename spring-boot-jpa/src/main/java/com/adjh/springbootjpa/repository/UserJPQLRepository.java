package com.adjh.springbootjpa.repository;

import com.adjh.springbootjpa.dto.UserDto;
import com.adjh.springbootjpa.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPQL을 사용한 예시
 *
 * @author : lee
 * @fileName : UserJPQLRepository
 * @since : 2024. 5. 10.
 */
@Repository
public class UserJPQLRepository {

    @PersistenceContext
    private EntityManager em;


    /**
     * [JPQL 쿼리방식-1] TypedQuery 타입으로 데이터 반환 예시
     *
     * @param userDto 사용자 전달 객체
     * @return List<UserEntity>
     */
    public List<UserEntity> selectTypedQueryUserList(UserDto userDto) {
        // 쿼리를 수행합니다.
        TypedQuery<UserEntity> typedQuery = em
                .createQuery("select u from UserEntity u where u.id = :id", UserEntity.class)
                .setParameter("id", userDto.getUserId());

        // 리스트 형태로 결과값을 반환 받습니다.
        List<UserEntity> resultList = typedQuery.getResultList();

        return resultList;
    }

    /**
     * [JPQL 쿼리방식-2] Query 타입으로 데이터 반환 반환 예시
     *
     * @param userDto 사용자 전달 객체
     * @return List<UserEntity>
     */
    public List<UserEntity> selectQueryUserList(UserDto userDto) {
        Query query = em
                .createQuery("select u from UserEntity u where u.id = :id")
                .setParameter("id", userDto.getUserId());

        // 단일한 결과를 반환 받습니다 : NoResultException, NonUniqueResultException 오류가 발생 할 수 있음.
        return query.getResultList();
    }

    /**
     * [JPQL 파라미터 방식-1] 이름 기준 파라미터 바인딩
     *
     * @param userDto 사용자 전달 객체
     * @return List<UserEntity>
     */
    public List<UserEntity> selectNameBaseUserList(UserDto userDto) {
        TypedQuery<UserEntity> typedQuery = em
                .createQuery("select u from UserEntity u where u.id = :id and u.userNm = :userNm", UserEntity.class)
                .setParameter("id", userDto.getUserId())
                .setParameter("userNm", userDto.getUserNm());
        return typedQuery.getResultList();
    }


    /**
     * [JPQL 파라미터 방식-2] 위치 기준 파라미터 바인딩
     *
     * @param userDto 사용자 전달 객체
     * @return List<UserEntity>
     */
    public List<UserEntity> selectPlaceBaseUserList(UserDto userDto) {
        TypedQuery<UserEntity> typedQuery = em
                .createQuery("select u from UserEntity u where u.id = ?1 and u.userNm = ?2", UserEntity.class)
                .setParameter(1, userDto.getUserId())
                .setParameter(2, userDto.getUserNm());
        return typedQuery.getResultList();
    }

    /**
     * [JPQL 프로젝션 방식-1] 엔티티 프로젝션을 이용한 데이터 조회
     *
     * @param userDto 사용자 전달 객체
     * @return List<UserEntity>
     */
    public List<UserEntity> selectEntityProjectionUserList(UserDto userDto) {
        // [CASE1] 엔티티(테이블)의 모든 필드(컬럼)을 조회합니다.
        TypedQuery<UserEntity> typedQuery = em
                .createQuery("select u from UserEntity u where u.id = :id and u.userNm = :userNm", UserEntity.class)
                .setParameter("id", userDto.getUserId())
                .setParameter("userNm", userDto.getUserNm());
        return typedQuery.getResultList();
    }

    /**
     * [JPQL 프로젝션 방식-2] 임베디드 프로젝션을 이용한 데이터 조회
     *
     * @param userDto 사용자 전달 객체
     * @return List<UserEntity>
     */
    public List<UserEntity> selectEmbeddedProjectionUserList(UserDto userDto) {
        // [CASE1] 엔티티(테이블)의 모든 필드(컬럼)을 조회합니다.
        List<UserEntity> resultList = em
                .createQuery("select u.userId from UserEntity u", UserEntity.class)
                .getResultList();
        return resultList;
    }


    /**
     * [JPQL 프로젝션 방식-3] 스칼라 프로젝션을 이용한 데이터 조회
     *
     * @param userDto 사용자 전달 객체
     * @return List<UserEntity>
     */
    public List<UserEntity> selectScalarProjectionUserList(UserDto userDto) {
        TypedQuery<UserEntity> typedQuery = em
                // UserEntity 내에서 userId, userNm의 특정 엔티티 필드만 가져옵니다.
                .createQuery("select u.userId, u.userNm from UserEntity u where u.id = :id and u.userNm = :userNm", UserEntity.class)
                .setParameter("id", userDto.getUserId())
                .setParameter("userNm", userDto.getUserNm());
        return typedQuery.getResultList();
    }


    /**
     * [JPQL 조인 방식-1] INNER JOIN 방식
     *
     * @param userDto 사용자 전달 객체
     * @return List<UserEntity>
     */
    public List<UserEntity> selectInnerJoinUserList(UserDto userDto) {
        List<UserEntity> resultList = em
                .createQuery("SELECT t1, t2 FROM UserEntity t1 JOIN PassportEntity t2 ON t1.userId = t2.passportId")
                .getResultList();

        return resultList;
    }


    /**
     * [JPQL 조인 방식-2] OUTER LEFT JOIN 방식
     *
     * @param userDto 사용자 전달 객체
     * @return List<UserEntity>
     */
    public List<UserEntity> selectOuterLeftJoinUserList(UserDto userDto) {
        List<UserEntity> resultList = em
                .createQuery("SELECT t1, t2 FROM UserEntity t1 LEFT JOIN OrderEntity t2 ON t1.userId = t2.userId")
                .getResultList();

        return resultList;
    }

    /**
     * [JPQL 반환값 방식 -1] 단건 조회 방식
     *
     * @param userDto 사용자 전달 객체
     * @return UserEntity
     */
    public UserEntity selectOneUser(UserDto userDto) {
        List<UserEntity> userList = em
                .createQuery("select u from UserEntity u where u.id = :id and u.userNm = :userNm", UserEntity.class)
                .setParameter("id", userDto.getUserId())
                .setParameter("userNm", userDto.getUserNm())
                .getResultList();

        UserEntity user = userList.isEmpty() ? null : userList.get(0);
        return user;
    }

    /**
     * [JPQL 반환값 방식 -2] 다건 조회 방식
     *
     * @param userDto 사용자 전달 객체
     * @return List<UserEntity>
     */
    public List<UserEntity> selectUsers(UserDto userDto) {
        List<UserEntity> userList = em
                .createQuery("select u from UserEntity u where u.id = :id and u.userNm = :userNm", UserEntity.class)
                .setParameter("id", 1234)
                .setParameter("userNm", "admin")
                .getResultList();
        return userList;
    }


}
