package com.bookstore.controller.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 获取BaseDAO<T>中T的类型。
 */
public class ReflectionUtils {
    /**
     * 获取父类的第一个参数，为参数类型，如String、Integer等
     * @param clazz
     * @return
     * @param <T>
     */
    public static<T> Class<T> getSuperGenericType(Class clazz){
        return getSuperClassGenericType(clazz, 0); //返回第一个参数类型，本项目中为Account、Book、Trade等类型
    }

    /**
     * 获取父类中的参数
     * @param clazz 子类的Class
     * @param index 获取第几个参数，第一个参数从0开始
     * @return 泛型T的类型
     */
    public static Class getSuperClassGenericType(Class clazz, int index) {
        Type genericSuperclass = clazz.getGenericSuperclass();

        //强转前先判断
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass; //强转

            // Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
            Type[] parameters = parameterizedType.getActualTypeArguments(); //获取所有参数类型

            if (index >= parameters.length || index < 0) return Object.class; //index需在参数类型数组范围内
            if (parameters[index] instanceof Class) { //强转前先判断
                return (Class) parameters[index]; //返回index位置的参数类型
            }
        }

        return Object.class;
    }
}
