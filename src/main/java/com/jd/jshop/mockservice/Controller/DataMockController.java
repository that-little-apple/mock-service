package com.jd.jshop.mockservice.Controller;

import com.alibaba.fastjson.JSON;
import com.jd.jshop.mockservice.domain.MockData;
import com.jd.jshop.mockservice.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jd.jshop.mockservice.service.MockDataService;

import javax.annotation.Resource;

/**
 * @description:
 **/
@Slf4j
@RestController
@RequestMapping("/mockData")
public class DataMockController {

    @Resource
    private MockDataService mockDataService;

    /**
     * 获取uri响应数据
     * @param mockDataReq
     * @return
     */
    @RequestMapping(value = "getResponse")
    public String getResponse(@ModelAttribute MockData mockDataReq) {
        Result<MockData> result = mockDataService.getByParam(mockDataReq);
        String content = null;
        if (result.isSuccess()) {
            MockData mockData = result.getData();
            content = mockData.getResponse();
        }
        return content;
    }


    /**
     * 根据参数查询
     * @param mockDataReq
     * @return
     */
    @RequestMapping(value = "get")
    public String getData(@ModelAttribute MockData mockDataReq) {
        Result<MockData> result = mockDataService.getByParam(mockDataReq);
        return JSON.toJSONString(result);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "getById")
    public String getDataById(Integer id) {
        Result<MockData> result = mockDataService.getById(id);
        return JSON.toJSONString(result);
    }


    /**
     * 根据id查询
     * @param mockDataReq
     * @return
     */
    @RequestMapping(value = "add")
    public String addData(@ModelAttribute MockData mockDataReq) {
        Result<MockData> result = mockDataService.addMockData(mockDataReq);
        return JSON.toJSONString(result);
    }


    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "delete")
    public String delete(Integer id) {
        Result<MockData> result = mockDataService.deleteById(id);
        return JSON.toJSONString(result);
    }

}
