package com.jd.jshop.mockservice.service;

import com.jd.jshop.mockservice.domain.MockData;
import com.jd.jshop.mockservice.domain.Result;

import java.util.List;

/**
 * @description:
 **/
public interface MockDataService {


    Result<MockData> getByParam(MockData mockData);


    Result<MockData> addMockData(MockData mockData);


    Result<MockData> getById(Integer id);


    Result deleteById(Integer id);

    Result<List<MockData>> getAll();


    Result update(MockData mockData);


}