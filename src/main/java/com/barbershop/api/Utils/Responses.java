package com.barbershop.api.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Responses<T> {
    private static final List<String> exclude = new ArrayList<>() {
        {
            add("modified_utc");
            add("created_utc");
        }
    };

    public static Map<String, Object> buildReturnObject(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        for(Field field : obj.getClass().getFields())
            if(!exclude.contains(field.getName()))
                map.put(field.getName(), field.get(obj));
        return map;
    }

    public static Map<String, Object> buildReturnObject(Object obj, List<String> exclude) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        for(Field field : obj.getClass().getFields())
            if(!exclude.contains(field.getName()))
                map.put(field.getName(), field.get(obj));
        return map;
    }

    public static List<Map<String,Object>> buildReturnList(List<Object> list) throws IllegalAccessException {
        List<Map<String,Object>> returnList = new ArrayList<>();
        for(Object o : list)
            returnList.add(buildReturnObject(o));
        return returnList;
    }

    public static List<Map<String,Object>> buildReturnList(List<Object> list, List<String> exclude) throws IllegalAccessException {
        List<Map<String,Object>> returnList = new ArrayList<>();
        for(Object o : list)
            returnList.add(buildReturnObject(o,exclude));
        return returnList;
    }

    public static List<Map<String,Object>> buildReturnListFromMap(List<Map<String,Object>> list){
        List<Map<String,Object>> returnList = new ArrayList<>();
        for(Map<String,Object> o : list) {
            o = new HashMap<>(o);
            for (String key : exclude)
                o.remove(key);
            returnList.add(o);
        }
        return returnList;
    }

    public static List<Map<String,Object>> buildReturnListFromMap(List<Map<String,Object>> list, List<String> exclude){
        List<Map<String,Object>> returnList = new ArrayList<>();
        for(Map<String,Object> o : list) {
            o = new HashMap<>(o);
            for (String key : exclude)
                o.remove(key);
            returnList.add(o);
        }
        return returnList;
    }
}
