package com.jd.jshop.mockservice.domain;

/**
 * @description:
 **/

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * mock数据类
 */
@Data
public class MockData implements Serializable {

    private static final long serialVersionUID = 294493981859512155L;

    /**
     * 记录id
     */
    private Integer id;

    /**
     * 请求uri
     */
    private String uri;

    /**
     * 请求参数
     */
    private String request;

    /**
     * 相应结果哦哦
     */
    private String response;

    /**
     * 状态码
     */
    private Integer statusCode;

    /**
     * 请求方式， GET/POST
     */
    private String methodType;

    /**
     * 系统标志
     */
    private String systemId;

    /**
     * 是否走mock服务
     */
    private Integer mockState;


    private Timestamp gmtCreate;

    private Timestamp gmtModify;

    public MockData() {}

    public MockData(Integer id, String uri, String request, String response, Integer statusCode, String methodType, String systemId, Integer mockState, Timestamp gmtCreate, Timestamp gmtModify) {
        this.id = id;
        this.uri = uri;
        this.request = request;
        this.response = response;
        this.statusCode = statusCode;
        this.methodType = methodType;
        this.systemId = systemId;
        this.mockState = mockState;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
    }
}