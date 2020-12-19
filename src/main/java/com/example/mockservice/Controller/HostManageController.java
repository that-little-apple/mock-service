package com.example.mockservice.Controller;

import com.alibaba.fastjson.JSONArray;
import com.example.mockservice.domain.HostConfig;
import com.example.mockservice.domain.Result;
import com.example.mockservice.service.HostConfigService;
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
@RequestMapping("/host")
public class HostManageController {

    @Resource
    private HostConfigService hostConfigService;

    @RequestMapping("/index")
    public String index() {
        return "hostConfig/index";
    }


    @RequestMapping("/manage")
    public String manage(ModelMap map) {
        Result<List<HostConfig>> result = hostConfigService.getAll();
        map.addAttribute("list", result.getData());
        return "hostConfig/manage";
    }


    @RequestMapping("/update")
    public String update(Integer id, ModelMap map) {
        Result<HostConfig> result = hostConfigService.getById(id);
        map.addAttribute("item", result.getData());
        return "hostConfig/update";
    }

    @RequestMapping("/doUpdate")
    @ResponseBody
    public String doUpdate(HostConfig hostConfig) {
        Result result = hostConfigService.update(hostConfig);
        return JSONArray.toJSONString(result);
    }

    @RequestMapping("/doAdd")
    @ResponseBody
    public String doAdd(HostConfig hostConfig) {
        Result result = hostConfigService.add(hostConfig);
        return JSONArray.toJSONString(result);
    }

    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(Integer id) {
        Result result = hostConfigService.deleteById(id);
        return JSONArray.toJSONString(result);
    }

}