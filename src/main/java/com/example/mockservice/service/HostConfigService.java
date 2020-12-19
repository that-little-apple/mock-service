package com.example.mockservice.service;

import com.example.mockservice.domain.HostConfig;
import com.example.mockservice.domain.Result;

import java.util.List;

/**
 * @description:
 **/
public interface HostConfigService {


    Result<HostConfig> getByParam(HostConfig hostConfig);


    Result<HostConfig> add(HostConfig hostConfig);


    Result<HostConfig> getById(Integer id);


    Result deleteById(Integer id);

    Result<List<HostConfig>> getAll();


    Result update(HostConfig hostConfig);


}