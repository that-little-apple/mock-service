package com.example.mockservice.Controller;

import com.alibaba.fastjson.JSON;
import com.example.mockservice.domain.HostConfig;
import com.example.mockservice.domain.Result;
import com.example.mockservice.service.HostConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 **/
@Slf4j
@RestController
@RequestMapping("/hostConfig")
public class HostConfigController {

    @Resource
    private HostConfigService hostConfigService;

    /**
     * 获取所有配置数据
     * @return
     */
    @RequestMapping(value = "getAll")
    public String getAll() {
        Result<List<HostConfig>> result = hostConfigService.getAll();
        if (result.isSuccess() || result.getData() != null) {
            return JSON.toJSONString(result.getData());
        } else {
            return "";
        }
    }


    /**
     * 获取所有配置数据
     * @return
     */
    @RequestMapping(value = "getByHost")
    public String getByHost(@ModelAttribute HostConfig hostConfig) {
        if (hostConfig == null || StringUtils.isBlank(hostConfig.getHost())) {
            return "";
        }
        Result<HostConfig> result = hostConfigService.getByParam(hostConfig);
        if (result.isSuccess() || result.getData() != null) {
            return JSON.toJSONString(result.getData());
        } else {
            return "";
        }
    }

}
