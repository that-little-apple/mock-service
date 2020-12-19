package com.example.mockservice.dao;


import com.example.mockservice.domain.HostConfig;

import java.util.List;

/**
 * @description:
 **/
public interface HostConfigMapper {

    /**
     * 添加记录
     *
     * @param hostConfig
     * @return
     */
    int add(HostConfig hostConfig);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    HostConfig getById(Integer id);

    /**
     * 根据示例查询
     *
     * @param hostConfig
     * @return
     */
    HostConfig getByParam(HostConfig hostConfig);

    /**
     * 根据id删除
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 更新记录
     *
     * @param hostConfig
     */
    void update(HostConfig hostConfig);

    /**
     * 查询所有数据
     *
     * @return
     */
    List<HostConfig> getAll();

}

