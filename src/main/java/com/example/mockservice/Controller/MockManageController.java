package com.example.mockservice.Controller;

import com.alibaba.fastjson.JSONArray;
import com.example.mockservice.domain.MockData;
import com.example.mockservice.domain.Result;
import com.example.mockservice.service.MockDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 **/
@Controller
@RequestMapping("/mock")
public class MockManageController {

    @Resource
    private MockDataService mockDataService;

    @RequestMapping("/index")
    public String index() {
        return "mockData/index";
    }


    @RequestMapping("/manage")
    public String manage(ModelMap map) {
        Result<List<MockData>> result = mockDataService.getAll();
        map.addAttribute("list", result.getData());
        return "mockData/manage";
    }


    @RequestMapping("/update")
    public String update(Integer id, ModelMap map) {
        Result<MockData> result = mockDataService.getById(id);
        map.addAttribute("item", result.getData());
        return "mockData/update";
    }

    @RequestMapping("/doUpdate")
    @ResponseBody
    public String doUpdate(MockData mockData) {
        Result result = mockDataService.update(mockData);
        return JSONArray.toJSONString(result);
    }

    @RequestMapping("/doAdd")
    @ResponseBody
    public String doAdd(MockData mockData) {
        Result result = mockDataService.addMockData(mockData);
        return JSONArray.toJSONString(result);
    }

    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(Integer id) {
        Result result = mockDataService.deleteById(id);
        return JSONArray.toJSONString(result);
    }

}