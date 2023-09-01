package com.cos.blog.common;

import com.cos.blog.model.Routine;
import com.cos.blog.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class JsonBinder {
    public static <T> T JsonToModel(JsonNode node, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (node.get(mapToEnum(clazz)) == null) {
                return mapper.treeToValue(node, clazz);
            } else {
                return mapper.treeToValue(node.get(mapToEnum(clazz)), clazz);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> JsonListToModelList(JsonNode node, Class<T> clazz) {
        List<T> retList = new ArrayList<>();
        JsonNode listNode = node.get(mapToEnum(clazz));
        if (!listNode.isArray()) {
            throw new RuntimeException("Json element should be List");
        } else {
            listNode.forEach(json -> {
                retList.add(JsonToModel(json, clazz));
            });
        }
        return retList;
    }

    public static String getJsonValue(JsonNode node, String targetKey) {
        if (node.isObject()) {
            JsonNode value = node.get(targetKey);
            if (value != null) {
                return value.asText();
            }
            for (JsonNode child : node) {
                String result = getJsonValue(child, targetKey);
                if (result != null)
                    return result;
            }
        } else if (node.isArray()){
            for(JsonNode child: node){
                String result = getJsonValue(child, targetKey);
                if(result!=null)
                    return result;
            }
        }
        return null;
    }


    public static <T> String mapToEnum(Class<T> clazz) {
        for (JsonKeyName idx : JsonKeyName.values()) {
            String[] strings = clazz.getName().split("\\.");
            if (idx.getName().equalsIgnoreCase(strings[strings.length - 1])) {
                return idx.getName();
            }
        }
        System.out.println("에러");
        return null;
    }
}
