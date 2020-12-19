package com.example.mockservice.domain;

/**
 * @description:
 **/

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * host配置
 */
@Data
public class HostConfig implements Serializable {
    private static final long serialVersionUID = -7731651384071045153L;

    /**
     * 记录id
     */
    private Integer id;

    /**
     * host域名，eg mall.jd.com
     */
    private String host;

    /**
     * 系统标识
     */
    private String systemKey;

    /**
     * 回源ip
     */
    private String backIp;

    /**
     * 回源端口
     */
    private String backPort;

    private Timestamp gmtCreate;

    private Timestamp gmtModify;

    public HostConfig() {}

    public HostConfig(Integer id, String host, String systemKey, String backIp, String backPort, Timestamp gmtCreate, Timestamp gmtModify) {
        this.id = id;
        this.host = host;
        this.systemKey = systemKey;
        this.backIp = backIp;
        this.backPort = backPort;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    public String getBackIp() {
        return backIp;
    }

    public void setBackIp(String backIp) {
        this.backIp = backIp;
    }

    public String getBackPort() {
        return backPort;
    }

    public void setBackPort(String backPort) {
        this.backPort = backPort;
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
}
