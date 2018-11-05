package cn.guard.dwarf.common.utils;

import cn.guard.dwarf.common.exception.JsonParseException;
import com.alibaba.fastjson.JSON;

import java.util.List;

public class JsonUtils {

    public JsonUtils() {
    }

    public static <T> T parseObject(String json, Class<T> clazz){
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonParseException(e);
        }
    }

    public static <T> List<T> parseArray(String json, Class<T> clazz){
        try {
            return JSON.parseArray(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonParseException(e);
        }
    }
}
