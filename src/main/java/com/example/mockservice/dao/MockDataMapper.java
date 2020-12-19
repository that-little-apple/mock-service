package com.example.mockservice.dao;

import com.example.mockservice.domain.MockData;

import java.util.List;

/**
 * @description:
 **/
public interface MockDataMapper {

    /**
     * 添加记录
     * @param mockData
     * @return
     */
    int add(MockData mockData);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    MockData getById(Integer id);

    /**
     * 根据示例查询
     * @param mockData
     * @return
     */
    MockData getByParam(MockData mockData);

    /**
     * 根据id删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 更新记录
     * @param mockData
     */
    void update(MockData mockData);

    /**
     * 查询所有数据
     * @return
     */
    List<MockData> getAll();

}