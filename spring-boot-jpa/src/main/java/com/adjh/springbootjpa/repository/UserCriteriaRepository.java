package com.adjh.springbootjpa.repository;

import com.adjh.springbootjpa.dto.UserDto;
import com.adjh.springbootjpa.entity.PassportEntity;
import com.adjh.springbootjpa.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserCriteriaRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * SELECT * FROM tb_user t1 INNER JOIN tb_passport t2 ON t1.userId = t2.passportId;
     *
     * @return
     */
    public List<UserEntity> selectUser() {
        List<UserEntity> result = new ArrayList<>();

        // 1. CriteriaBuilder 인스턴스를 생성합니다.
        CriteriaBuilder cb = em.getCriteriaBuilder();


        // 2. UserEntity 타입의 CriteriaBuilder를 구성합니다.
        CriteriaQuery<UserEntity> userEntityCq = cb.createQuery(UserEntity.class);
        Root<UserEntity> userEntityRoot = userEntityCq.from(UserEntity.class);

        // 3. PassportEntity 타입의 CriteriaBuilder를 구성합니다.
        CriteriaQuery<PassportEntity> passportEntityCq = cb.createQuery(PassportEntity.class);
        Root<PassportEntity> passportEntityRoot = passportEntityCq.from(PassportEntity.class);


//        // PassportEntity와 UserEntity를 조인합니다.
//        Join<UserEntity, PassportEntity> passportJoin = userEntityRoot.join("passportEntity", JoinType.INNER);

        userEntityCq
                .select(userEntityRoot)
                .where(cb.equal(userEntityRoot.get("userId"), passportEntityRoot.get("passportId")));

        // 마지막으로, 쿼리를 실행합니다.
        return em.createQuery(userEntityCq).getResultList().isEmpty() ? null : em.createQuery(userEntityCq).getResultList();
    }


    /**
     * 사용자 리스트를 조회합니다.
     *
     * @return
     */
    public List<UserEntity> selectUserList(UserDto userDto) {

        // 1. CriteriaBuilder 인스턴스를 생성합니다.
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // 2. UserEntity 타입의 CriteriaBuilder를 구성합니다.
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);

        // 3. 데이터를 조회하는 Query의 Root를 지정합니다.
        Root<UserEntity> root = cq.from(UserEntity.class);

        // 4. [전체 조회] CreateQuery 내에서 root라는 엔티티 전체 검색을 합니다.
        cq = cq.select(root);

        // 5. CreateQuery 내에 조건절을 추가합니다.
        cq = cq.where(
                root.get("userSt").in("A", "I", "D"),             // userSt IN ("A", "I", "D")
                cb.equal(root.get("delYn"), false),                     // delYn = false
                cb.equal(root.get("userNm"), userDto.getUserNm()),         // userNm = #{userNm}
                cb.equal(root.get("userId"), userDto.getUserId())          // userId = #{userId}
        );

        // 6. CreateQuery 내에 정렬 부분을 추가합니다.
        cq.orderBy(
                cb.asc(root.get("delYn")),
                cb.desc(root.get("userNm"))
        );

        // 7. 구성한 CriteriaQuery를 기반으로 쿼리를 생성하여 다건으로 반환받습니다.
        return em.createQuery(cq).getResultList();
    }

    /**
     * Criteria API를 이용한 동적 ORDER 조회
     *
     * @param userDto
     * @return
     */
    public List<UserEntity> testDynamicOrderQuery(UserDto userDto) {

        // 1. CriteriaBuilder 인스턴스를 생성합니다.
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // 2. UserEntity 타입의 CriteriaBuilder를 구성합니다.
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        // 3. Order 리스트 객체를 구성합니다.
        List<Order> orders = new ArrayList<>();
        orders.add(cb.desc(root.get("userSq")));

        // 4.1. [동적 ORDER] 정렬 옵션 정보가 "NAME_ASC"로 들어온 경우 이름을 오름차순으로 정렬
        if (!userDto.getSortOptions().isEmpty() && userDto.getSortOptions().equals("NAME_ASC")) {
            orders.add(cb.asc(root.get("userNm")));
        }

        // 4.2. [동적 ORDER] 정렬 옵션 정보가 "NAME_DESC"로 들어온 경우 이름을 내림차순으로 정렬
        if (!userDto.getSortOptions().isEmpty() && userDto.getSortOptions().equals("NAME_DESC")) {
            orders.add(cb.desc(root.get("userNm")));
        }

        // 4.3. [동적 ORDER] 정렬 옵션 정보가 "ID_ASC"로 들어온 경우 아이디를 오름차순으로 정렬
        if (!userDto.getSortOptions().isEmpty() && userDto.getSortOptions().equals("ID_ASC")) {
            orders.add(cb.asc(root.get("userId")));
        }

        // 4.4. [동적 ORDER] 정렬 옵션 정보가 "ID_DESC"로 들어온 경우 아이디를 내림차순으로 정렬
        if (!userDto.getSortOptions().isEmpty() && userDto.getSortOptions().equals("ID_DESC")) {
            orders.add(cb.desc(root.get("userId")));
        }

        // 5. 최종 결과 출력
        cq.select(root).orderBy(orders);
        List<UserEntity> results = em.createQuery(cq).getResultList();
        return results.isEmpty() ? null : results;
    }


    /**
     * Criteria API를 이용한 동적 WHERE절 조회
     *
     * @param userDto
     * @return
     */
    public List<UserEntity> selectUserInfo(UserDto userDto) {

        // 1. CriteriaBuilder 인스턴스를 생성합니다.
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // 2. UserEntity 타입의 CriteriaBuilder를 구성합니다.
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        // 3. [고정 WHERE] 사용자 상태가 "A"이고 delYn이 false인 조건
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("userSt"), "A"));
        predicates.add(cb.equal(root.get("delYn"), false));

        // 4. [동적 WHERE] 사용자 아이디가 존재하는 경우 조건문에 추가
        if (userDto.getUserId() != null && !userDto.getUserId().isEmpty()) {
            predicates.add(cb.and(cb.equal(root.get("userId"), userDto.getUserId())));
        }

        // 4. [동적 WHERE] 사용자 이름이 존재하는 경우 조건문 추가
        if (userDto.getUserNm() != null && !userDto.getUserNm().isEmpty()) {
            predicates.add(cb.and(cb.equal(root.get("userNm"), userDto.getUserNm())));
        }

        // 5. 최종 결과 출력
        cq.select(root).where(predicates.toArray(new Predicate[0]));
        List<UserEntity> results = em.createQuery(cq).getResultList();

        return results.isEmpty() ? null : results;
    }

}