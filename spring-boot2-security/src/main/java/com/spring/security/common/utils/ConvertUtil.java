package com.spring.security.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 변환을 해주는 공통 유틸
 */
public class ConvertUtil {

    /**
     * [공통함수] Object 형을 Map 형태로 변환 함수
     *
     * @param obj {Object}
     * @return Map 형태로 반환함
     */
    public static HashMap<String, Object> convertObjectToMap(Object obj) {
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            HashMap<String, Object> resultMap = new HashMap<String, Object>();
            for (int i = 0; i <= fields.length - 1; i++) {
                fields[i].setAccessible(true);
                resultMap.put(fields[i].getName(), fields[i].get(obj));
            }
            return resultMap;
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [공통함수] Map 형을 JSON Object 형태로 변환 함수
     *
     * @param param {Map}
     * @return {JSONObject}
     */
    public static JSONObject convertMapToJsonObject(Map<String, Object> param) {
        return new JSONObject(param);
    }

    /**
     * [공통함수] Map 형을 Object 형태로 변환 함수
     *
     * @param map {Map<String, Object}
     * @param obj {Object}
     * @return {Object}
     */
    public static Object convertMapToObject(Map<String, Object> map, Object obj) {
        String keyAttribute = null;
        String setMethodString = "set";
        String methodString = null;

        for (String s : map.keySet()) {
            keyAttribute = s;
            methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1);
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (methodString.equals(method.getName())) {
                    try {
                        method.invoke(obj, map.get(keyAttribute));
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        return obj;
    }


    /**
     * [공통함수] Object(VO)형을 JSON Object 형태로 변환 함수
     *
     * @param obj {Object}
     * @return {Object}
     */
    public static Object convertObjectToJsonObject(Object obj) {

        ObjectMapper om = new ObjectMapper();
        JSONParser parser = new JSONParser();
        String convertJsonString = "";
        Object convertObj = new Object();

        // VO ==> JSON(String) 파싱
        try {
            convertJsonString = om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // JSON(String) => JSON 파싱
        try {
            convertObj = parser.parse(convertJsonString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertObj;
    }

}
