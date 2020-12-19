package com.example.mockservice.domain;

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
     * 相应结果
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
     * 系统标识，如jshop2013/mall2013
     */
    private String system;


    private Timestamp gmtCreate;

    private Timestamp gmtModify;

    /**
     * 状态，-1删除，0-未启用，1启用
     */
    private Integer state;

    public MockData() {
    }

    public MockData(Integer id, String uri, String request, String response, Integer statusCode, String methodType, String system, Timestamp gmtCreate, Timestamp gmtModify, Integer state) {
        this.id = id;
        this.uri = uri;
        this.request = request;
        this.response = response;
        this.statusCode = statusCode;
        this.methodType = methodType;
        this.system = system;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Timestamp gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
}