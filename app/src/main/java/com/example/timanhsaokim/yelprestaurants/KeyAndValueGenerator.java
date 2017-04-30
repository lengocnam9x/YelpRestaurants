package com.example.timanhsaokim.yelprestaurants;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TimAnhSaoKim on 4/29/2017.
 */

public abstract class KeyAndValueGenerator {

    public Map<String, String> getFieldNameAndValue(){
        Map<String, String> keyAndValue = new HashMap<>();
        Class currentClass = this.getClass();
        Field[] fields = currentClass.getDeclaredFields();
        Map<String, Method> fieldNameAndMethod = this.getFieldAndGetterMethodMap(fields);
        for(Field field : fields){
            String fieldName = field.getName();
            Method getter = fieldNameAndMethod.get(fieldName);
            if(getter != null){
                try {
                    Object value = getter.invoke(this);
                    if(value != null){
                        keyAndValue.put(fieldName, value.toString());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return keyAndValue;
    }

    private Map<String, Method> getFieldAndGetterMethodMap(Field[] fields){

        Map<String, Method> fieldNameAndMethod = new HashMap<>();
        final String METHOD_TYPE = "get";
        for(Field field : fields){
            String fieldName = field.getName();
            Method getter = findGetterAndSetterMethod(METHOD_TYPE, fieldName);
            if(getter != null){
                fieldNameAndMethod.put(fieldName, getter);
            }
        }
        return fieldNameAndMethod;
    }

    private Method findGetterAndSetterMethod(String type, String fieldName){
        Method[] methods = this.getClass().getDeclaredMethods();
        for(Method method : methods){
            String methodName = method.getName();
            if(methodName.equalsIgnoreCase(type+fieldName)){
                return method;
            }
        }
        return null;
    }
}
