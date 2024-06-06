package com.adjh.springbootshort.modules.sorting;

import com.adjh.springbootshort.dto.UserDto;
import lombok.Builder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 리스트를 정렬하는 모듈
 *
 * @author : jonghoon
 * @fileName : SortArrModule
 * @link : https://adjh54.tistory.com/121
 * @since : 6/6/24
 */
public class SortArrListModule {

    /**
     * 리스트를 정렬하는 방법 -1
     * Collections(), .sort()를 이용한 방식
     *
     * @param objectList
     * @return
     */
    public List<Object> sortingIntArr(List<Object> objectList) {
        List<String> sortedStrList = new ArrayList<>(Arrays.asList("apple", "banana", "cherry"));

        // [CASE1] 문자열 오름차순 정렬 -1 : Collections.sort() 이용
        Collections.sort(sortedStrList);                                    // [apple, banana, cherry]

        // [CASE2] 문자열 오름차순 정렬 -2 : List.sort() 이용
        sortedStrList.sort(Comparator.naturalOrder());                      // [apple, banana, cherry]

        // [CASE1] 문자열 내림차순 정렬 : Collections.reverse() 이용
        Collections.reverse(sortedStrList);                                 // [cherry, banana, apple]

        // [CASE2] 문자열 내림차순 정렬 : Collections.sort() 이용
        Collections.sort(sortedStrList, Collections.reverseOrder());        // [cherry, banana, apple]

        // [CASE3] 문자열 내림차순 정렬 : List.sort() 이용
        sortedStrList.sort(Comparator.reverseOrder());                      // [cherry, banana, apple]

        // [CASE4] 문자열 내림차순 정렬 : List.sort() 이용
        Comparator<Object> reverseOrder = Collections.reverseOrder();       // [cherry, banana, apple]

        return objectList;
    }


    /**
     * 리스트를 정렬하는 방법 -2 : Stream을 이용한 정렬하는 방법
     * stream().sorted()를 이용한 방법
     *
     * @param objectList
     * @return
     */
    public List<Object> sortingIntArr3(List<Object> objectList) {
        /*
         * [CASE2] ArrayList 이용한 방법
         */
        // 1. ArrayList Initialize
        List<String> sortedStrList1 = new ArrayList<>(Arrays.asList("aaa", "bbb", "ccc", "ddd", "AAA"));
        List<String> sortedStrList2 = new ArrayList<>(Arrays.asList("apple", "banana", "cherry"));
        List<String> sortedStrList3 = new ArrayList<>(Arrays.asList("f", "b", "z"));

        // 2.1. ArrayList to Stream<String> : 리스트를 오름 차순으로 정렬합니다.
        Stream<String> sortedStrStream1 = sortedStrList1.stream().sorted(Comparator.naturalOrder());

        // 2.2. ArrayList to Stream<String> : 리스트를 오름 차순으로 정렬합니다.
        Stream<String> sortedStrStream2 = sortedStrList2.stream().sorted(Comparator.reverseOrder());
        Stream<String> sortedStrStream3 = sortedStrList3.stream().sorted((a, b) -> b.compareTo(a));

        // 3.1. Stream<String> to List<String>
        sortedStrList1 = sortedStrStream1.collect(Collectors.toList());     // 결과값 반환(오름차순) : ["AAA", "aaa", "bbb", "ccc", "ddd"]
        sortedStrList2 = sortedStrStream2.collect(Collectors.toList());     // 결과값 반환(오름차순) : ["cherry", "banana", "apple"]
        sortedStrList3 = sortedStrStream3.collect(Collectors.toList());     // 결과값 반환(오름차순) : ["z", "f", "d"]

        return objectList;
    }


    /**
     * 리스트를 정렬하는 방법 -3 : List<Object> 정렬 방법
     * Collections.sort, List.sort()를 이용하는 방법
     *
     * @param objectList
     * @return
     */
    public List<Object> sortingIntArr2(List<Object> objectList) {
        List<UserDto> sortedUserDtoList = Arrays.asList(
                UserDto.builder().userId("adjh54").userNm("lee").userPw("1234").userSt("S").build(),
                UserDto.builder().userId("ckask123").userNm("kim").userPw("4321").userSt("S").build(),
                UserDto.builder().userId("fjdsl3").userNm("park").userPw("5678").userSt("S").build()
        );

        // [CASE1] 객체 오름차순 정렬 -1 : Collections.sort() 이용
        Collections.sort(sortedUserDtoList, Comparator.comparing(UserDto::getUserNm));                  // [lee, kim, park]

        // [CASE2] 객체 오름차순 정렬 -2 : List.sort() 이용
        sortedUserDtoList.sort(Comparator.comparing(UserDto::getUserNm));                               // [lee, kim, park]

        // [CASE3] 객체 내림차순 정렬 -1 : Collections.sort() 이용
        Collections.sort(sortedUserDtoList, Comparator.comparing(UserDto::getUserNm).reversed());       // [park, kim, lee]

        // [CASE4] 객체 내림차순 정렬 -2 : List.sort() 이용
        sortedUserDtoList.sort(Comparator.comparing(UserDto::getUserNm).reversed());

        return objectList;
    }

    /**
     * 리스트를 정렬하는 방법 -4 : List<Object> Stream을 이용한 정렬 방법
     * stream().sorted()를 이용한 방법
     *
     * @param objectList
     * @return
     */
    public List<Object> sortingIntArr4(List<Object> objectList) {

        // 1. Object ArrayList Initialize
        List<UserDto> sortedUserDtoList = Arrays.asList(
                UserDto.builder().userId("adjh54").userNm("lee").userPw("1234").userSt("S").build(),
                UserDto.builder().userId("ckask123").userNm("kim").userPw("4321").userSt("S").build(),
                UserDto.builder().userId("fjdsl3").userNm("park").userPw("5678").userSt("S").build()
        );

        // 2.1. Object to Stream<Object> : 리스트 내에서 userNm을 기준으로 오름차순으로 정렬합니다.
        Stream<UserDto> sortedAscUserDtoStream = sortedUserDtoList.stream().sorted(Comparator.comparing(UserDto::getUserNm));

        // 2.2. Object to Stream<Object> : 리스트 내에서 userNm을 기준으로 내림차순으로 정렬합니다.
        Stream<UserDto> sortedDescUserDtoStream = sortedUserDtoList.stream().sorted(Comparator.comparing(UserDto::getUserNm).reversed());

        // 3. Stream<Object> to ArrayList
        List<UserDto> sortedAscUserDtoList = sortedAscUserDtoStream.collect(Collectors.toList());           // 결과값 반환: List<UserDto>
        List<UserDto> sortedDescUserDtoList = sortedDescUserDtoStream.collect(Collectors.toList());         // 결과값 반환: List<UserDto>

        return objectList;

    }
}
