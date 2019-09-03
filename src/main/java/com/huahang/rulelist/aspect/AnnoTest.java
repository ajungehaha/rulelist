package com.huahang.rulelist.aspect;

import ch.qos.logback.core.joran.spi.ElementSelector;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huahang.rulelist.dataobject.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 利用反射机制 实现对restapi的接口说明
 * @Author: 阿俊哥
 * @Date: 2019/9/3 16:30
 * @Version 1.0
 */

public class AnnoTest {


    public static void main(String[] args) throws JsonProcessingException {
        getRequestMappingMethod("com.huahang.rulelist.controller");
    }

    private static void getRequestMappingMethod(String scanBasePackage) throws JsonProcessingException {
        //设置包的扫描路径
        Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(scanBasePackage)).setScanners(new MethodAnnotationsScanner()));

        //扫描包内带有@requestPermission注解的所有方法集合
        Set<Method> methods = reflections.getMethodsAnnotatedWith(RequiresPermissions.class);

        List<Auth> auths = new ArrayList<>();
        Date now  = new Date();

        //循环获取方法
        methods.forEach(method -> {
            //用于保存请求的类型
            String methodType = "";

            //获取类上注解的值作为请求的基本路径
            String authUrl = method.getDeclaringClass().getAnnotation(RequestMapping.class).value()[0];

            //获取方法上的@getmapping @PostMapping @PutMapping @DeleteMapping 的值
            if(method.getAnnotation(PutMapping.class)!=null){
                methodType="put";
                if(method.getAnnotation(PutMapping.class).value().length>0){
                    authUrl = method.getAnnotation(PutMapping.class).value()[0];
                }
            }
            else if(method.getAnnotation(PostMapping.class)!=null){
                methodType="post";
                if(method.getAnnotation(PostMapping.class).value().length>0){
                    authUrl = method.getAnnotation(PostMapping.class).value()[0];
                }
            }
            else if(method.getAnnotation(DeleteMapping.class)!=null){
                methodType="delete";
                if(method.getAnnotation(DeleteMapping.class).value().length>0){
                    authUrl = method.getAnnotation(DeleteMapping.class).value()[0];
                }
            }
            else if(method.getAnnotation(GetMapping.class)!=null){
                methodType="get";
                if(method.getAnnotation(GetMapping.class).value().length>0){
                    authUrl = method.getAnnotation(GetMapping.class).value()[0];
                }
            }
            Auth auth = new Auth();
            auth.setAuthName(method.getDeclaringClass().getAnnotation(Api.class).value()+"-"+method.getAnnotation(ApiOperation.class).value());
            auth.setAuthUniqueMark(method.getAnnotation(RequiresPermissions.class).value()[0]);
            auth.setAuthUrl(authUrl);
            auth.setCreateDate(now);
            auth.setMethodType(methodType);
            auths.add(auth);
        });
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(auths));

    }


}
