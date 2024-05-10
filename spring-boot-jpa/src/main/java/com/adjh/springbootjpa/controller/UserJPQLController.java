package com.adjh.springbootjpa.controller;

import com.adjh.springbootjpa.dto.UserDto;
import com.adjh.springbootjpa.entity.UserEntity;
import com.adjh.springbootjpa.repository.UserJPQLRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * JPQL을 이용한 Controller 예시
 *
 * @author : lee
 * @fileName : UserJPQLController
 * @since : 2024. 5. 10.
 */
@RequestMapping("/api/v1/userJpql")
@RestController
public class UserJPQLController {

    private final UserJPQLRepository userService;

    public UserJPQLController(UserJPQLRepository userService) {
        this.userService = userService;
    }

    /**
     * [JPQL 쿼리방식-1] TypedQuery 타입으로 데이터 반환 예시
     *
     * @return
     */
    @PostMapping("/userTypedQuery")
    public ResponseEntity<Object> selectTypedQueryUserList(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.selectTypedQueryUserList(userDto);
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    /**
     * [JPQL 쿼리방식-2] Query 타입으로 데이터 반환 반환 예시
     *
     * @return
     */
    @PostMapping("/userQuery")
    public ResponseEntity<Object> selectQueryUserList(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.selectQueryUserList(userDto);
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    /**
     * [JPQL 파라미터 방식-1] 이름 기준 파라미터 바인딩
     *
     * @return
     */
    @PostMapping("/nameBaseUser")
    public ResponseEntity<Object> selectNameBaseUserList(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.selectNameBaseUserList(userDto);
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    /**
     * [JPQL 파라미터 방식-2] 위치 기준 파라미터 바인딩
     *
     * @return
     */
    @PostMapping("/placeBaseUser")
    public ResponseEntity<Object> selectPlaceBaseUserList(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.selectPlaceBaseUserList(userDto);
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    /**
     * [JPQL 프로젝션 방식-1] 엔티티 프로젝션을 이용한 데이터 조회
     *
     * @return
     */
    @PostMapping("/entityProjectUser")
    public ResponseEntity<Object> selectEntityProjectionUserList(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.selectEntityProjectionUserList(userDto);
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    /**
     * [JPQL 프로젝션 방식-2] 임베디드 프로젝션을 이용한 데이터 조회
     *
     * @return
     */
    @PostMapping("/embeddedProjectUser")
    public ResponseEntity<Object> selectEmbeddedProjectionUserList(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.selectEmbeddedProjectionUserList(userDto);
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }


    /**
     * [JPQL 프로젝션 방식-3] 스칼라 프로젝션을 이용한 데이터 조회
     *
     * @return
     */
    @PostMapping("/scalarProjectUser")
    public ResponseEntity<Object> selectScalarProjectionUserList(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.selectScalarProjectionUserList(userDto);
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    /**
     * [JPQL 조인 방식-1] INNER JOIN 방식
     *
     * @return
     */
    @PostMapping("/innerJoinUser")
    public ResponseEntity<Object> selectInnerJoinUserList(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.selectInnerJoinUserList(userDto);
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    /**
     * [JPQL 조인 방식-2] OUTER LEFT JOIN 방식
     *
     * @return
     */
    @PostMapping("/outerLeftJoinUser")
    public ResponseEntity<Object> selectOuterLeftJoinUserList(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.selectOuterLeftJoinUserList(userDto);
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    /**
     * [JPQL 반환값 방식 -1] 단건 조회 방식
     *
     * @return
     */
    @PostMapping("/oneUser")
    public ResponseEntity<Object> selectOneUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = userService.selectOneUser(userDto);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    /**
     * [JPQL 반환값 방식 -2] 다건 조회 방식
     *
     * @return
     */
    @PostMapping("/manyUser")
    public ResponseEntity<Object> selectUsers(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.selectUsers(userDto);
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }


}
