package com.jd.jshop.mockservice.service.impl;

import com.jd.jshop.mockservice.dao.MockDataMapper;
import com.jd.jshop.mockservice.domain.MockData;
import com.jd.jshop.mockservice.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.jd.jshop.mockservice.service.MockDataService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 **/
@Service
@Slf4j
public class MockDataServiceImpl implements MockDataService {

    @Resource
    private MockDataMapper mockDataMapper;

    @Override
    public Result<MockData> getByParam(MockData mockData) {
        Result<MockData> result = new Result<>();
        try {
            if (mockData == null || StringUtils.isBlank(mockData.getUri())) {
                result.fail("uri参数为空");
                return result;
            }
            result.setData(mockDataMapper.getByParam(mockData));
        } catch (Exception e) {
            log.error("根据查询失败", e);
            result.fail("根据参数查询失败");
        }
        return result;
    }

    @Override
    public Result<MockData> addMockData(MockData mockData) {
        Result<MockData> result = new Result<>();
        try {
            if (mockData == null || StringUtils.isBlank(mockData.getUri())
                    || StringUtils.isBlank(mockData.getResponse())) {
                result.fail("保存参数为空");
                return result;
            }
            mockDataMapper.add(mockData);
        } catch (Exception e) {
            log.error("添加失败", e);
            result.fail("添加失败");
        }
        return result;
    }

    @Override
    public Result<MockData> getById(Integer id) {
        Result<MockData> result = new Result<>();
        try {
            if (id == null) {
                result.setData(new MockData());
                return result;
            }
            result.setData(mockDataMapper.getById(id));
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
            mockDataMapper.delete(id);
        } catch (Exception e) {
            log.error("删除失败", e);
            result.fail("删除失败");
        }
        return result;
    }

    @Override
    public Result<List<MockData>> getAll() {
        Result<List<MockData>> result = new Result<>();
        try {
            List<MockData> list = mockDataMapper.getAll();
            result.setData(list);
        } catch (Exception e) {
            log.error("删除失败", e);
            result.fail("删除失败");
        }
        return result;
    }

    @Override
    public Result update(MockData mockData) {
        Result result = new Result();
        if (mockData == null || mockData.getId() == null) {
            result.fail("更新失败");
            return result;
        }
        try {
            mockDataMapper.update(mockData);
        } catch (Exception e) {
            log.error("更新失败", e);
            result.fail("更新失败");
        }
        return result;
    }


}
