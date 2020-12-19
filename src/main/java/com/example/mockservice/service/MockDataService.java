package com.example.mockservice.service;

import com.example.mockservice.domain.MockData;
import com.example.mockservice.domain.Result;

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