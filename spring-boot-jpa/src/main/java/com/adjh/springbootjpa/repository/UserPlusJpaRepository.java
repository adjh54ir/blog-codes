package com.adjh.springbootjpa.repository;

import com.adjh.springbootjpa.dto.UserDto;
import com.adjh.springbootjpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : UserPlusJpaRepository
 * @since : 2024. 5. 10.
 */
public interface UserPlusJpaRepository extends JpaRepository<UserEntity, Long> {

    /* ==============================================================================
     * =================================Query Method 사용예시
     * ==============================================================================*/
    // 메서드 명을 기준으로 사용자 아이디 기준으로 정렬하여 조회합니다.
    List<UserEntity> findByOrderByUserIdAsc();

    // 메서드 명을 기준으로 사용자 이름을 기준으로 조회합니다.
    List<UserEntity> findByUserNm(String userNm);


    /* ==============================================================================
     * ================================= @Query + NativeQuery 사용예시
     * ==============================================================================*/
    // @Query 활용 + JPQL 활용
    @Query(value = "SELECT t1 FROM UserEntity t1 WHERE t1.userId = : userId")
    List<UserEntity> queryFindByUserNm(String userNm);

    // @Query 활용 + NativeQuery 활용(SQL 쿼리 활용)
    @Query(value = "SELECT t1 FROM TB_USER t1 WHERE t1.userId = : userId", nativeQuery = true)
    List<UserEntity> selectUserId(@Param("userId") long userId);


    /* ==============================================================================
     * ================================= NamedQuery 사용예시
     * ==============================================================================*/
    List<UserEntity> selectNamedUserList(UserDto userDto);

    List<UserEntity> selectNamedUserDetail(UserDto userDto);
}
