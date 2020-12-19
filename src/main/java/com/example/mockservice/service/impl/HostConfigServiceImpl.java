package com.example.mockservice.service.impl;

import com.example.mockservice.dao.HostConfigMapper;
import com.example.mockservice.domain.HostConfig;
import com.example.mockservice.domain.Result;
import com.example.mockservice.service.HostConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 **/
@Service
@Slf4j
public class HostConfigServiceImpl implements HostConfigService {

    @Resource
    private HostConfigMapper hostConfigMapper;

    @Override
    public Result<HostConfig> getByParam(HostConfig hostConfig) {
        Result<HostConfig> result = new Result<>();
        try {
            if (hostConfig == null || StringUtils.isBlank(hostConfig.getHost())) {
                result.fail("host参数为空");
                return result;
            }
            result.setData(hostConfigMapper.getByParam(hostConfig));
        } catch (Exception e) {
            log.error("根据查询失败", e);
            result.fail("根据参数查询失败");
        }
        return result;
    }

    @Override
    public Result<HostConfig> add(HostConfig hostConfig) {
        Result<HostConfig> result = new Result<>();
        try {
            if (hostConfig == null || StringUtils.isBlank(hostConfig.getHost())
                    || StringUtils.isBlank(hostConfig.getSystemKey())
                    || StringUtils.isBlank(hostConfig.getBackIp())
                    || StringUtils.isBlank(hostConfig.getBackPort())) {
                result.fail("保存参数为空");
                return result;
            }
            hostConfigMapper.add(hostConfig);
        } catch (Exception e) {
            log.error("添加失败", e);
            result.fail("添加失败");
        }
        return result;
    }

    @Override
    public Result<HostConfig> getById(Integer id) {
        Result<HostConfig> result = new Result<>();
        try {
            if (id == null) {
                result.setData(new HostConfig());
                return result;
            }
            result.setData(hostConfigMapper.getById(id));
        } catch (Exception e) {
            log.error("根据查询失败", e);
            result.fail("根据参数查询失败");
        }
        return result;
    }

    @Override
    public Result deleteById(Integer id) {
        Result result = new Result<>();
        try {
            if (id == null) {
                result.fail("id参数为空");
                return result;
            }
            hostConfigMapper.delete(id);
        } catch (Exception e) {
            log.error("删除失败", e);
            result.fail("删除失败");
        }
        return result;
    }

    @Override
    public Result<List<HostConfig>> getAll() {
        Result<List<HostConfig>> result = new Result<>();
        try {
            List<HostConfig> list = hostConfigMapper.getAll();
            result.setData(list);
        } catch (Exception e) {
            log.error("获取数据失败", e);
            result.fail("获取数据失败");
        }
        return result;
    }

    @Override
    public Result update(HostConfig hostConfig) {
        Result result = new Result();
        if (hostConfig == null || hostConfig.getId() == null) {
            result.fail("更新失败");
            return result;
        }
        try {
            hostConfigMapper.update(hostConfig);
        } catch (Exception e) {
            log.error("更新失败", e);
            result.fail("更新失败");
        }
        return result;
    }


}