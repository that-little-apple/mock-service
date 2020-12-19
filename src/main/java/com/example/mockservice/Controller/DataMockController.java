package com.example.mockservice.Controller;

import com.alibaba.fastjson.JSON;
import com.example.mockservice.domain.MockData;
import com.example.mockservice.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mockservice.service.MockDataService;

import javax.annotation.Resource;

/**
 * @description:
 **/
@Slf4j
@RestController
@RequestMapping("/mockData")
public class DataMockController {

    // 启用状态
    private static final int ENABLE_STATE = 1;

    @Resource
    private MockDataService mockDataService;

    /**
     * 获取uri响应数据
     * @param mockDataReq
     * @return
     */
    @RequestMapping(value = "getResponse")
    public String getResponse(@ModelAttribute MockData mockDataReq, String callback) {
        Result<MockData> result = mockDataService.getByParam(mockDataReq);
        String content = null;
        if (result.isSuccess() || result.getData() != null) {
            MockData mockData = result.getData();
            // 只返回启用状态的数据模拟
            if (mockData.getState() != null && mockData.getState() == ENABLE_STATE) {
                content = mockData.getResponse();
            }
        }
        if (StringUtils.isNotBlank(callback)) {
            content = callback + "(" + content + ")";
        }
        return content;
    }

    /**
     * 获取uri的mock数据状态
     * @param mockDataReq
     * @return
     */
    @RequestMapping(value = "getMockState")
    public String getMockState(@ModelAttribute MockData mockDataReq) {
        Result<MockData> result = mockDataService.getByParam(mockDataReq);
        String state = "false";
        if (result.isSuccess() || result.getData() != null) {
            MockData mockData = result.getData();
            // 只返回启用状态的数据模拟
            if (mockData.getState() != null && mockData.getState() == ENABLE_STATE) {
                state = "true";
            }
        }
        return state;
    }


}