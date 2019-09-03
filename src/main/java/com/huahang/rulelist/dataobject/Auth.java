package com.huahang.rulelist.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限管理类
 * @Author: 阿俊哥
 * @Date: 2019/9/3 15:53
 * @Version 1.0
 */
@Data
public class Auth implements Serializable {


    private static final long serialVersionUID = -2652952442620509053L;
    /*权限名称*/
    private String authName;

    /*权限地址*/
    private String authUrl;

    /*权限唯一标识*/
    private String authUniqueMark;

    /*创建时间*/
    private Date createDate;

    /*请求方式*/
    private String methodType;
}
