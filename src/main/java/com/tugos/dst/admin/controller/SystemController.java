package com.tugos.dst.admin.controller;

import com.tugos.dst.admin.common.ResultVO;
import com.tugos.dst.admin.service.SystemService;
import com.tugos.dst.admin.vo.ScheduleVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author qinming
 * @date 2020-10-25 10:57:45
 * <p> 系统服务器控制器 </p>
 */
@Controller
@Slf4j
@RequestMapping("/system")
public class SystemController {

    private SystemService systemService;

    /**
     * 系统设置页
     */
    @GetMapping("/index")
    @RequiresAuthentication
    public String index() {
        return "system/index";
    }

    /**
     * 向导页面
     */
    @GetMapping("/guide")
    public String toGuide() {
        return "system/guide";
    }

    @GetMapping("/getDstLog")
    @ResponseBody
    @RequiresAuthentication
    public ResultVO<List<String>> getDstLog(@RequestParam(required = false, defaultValue = "0") Integer type,
                                            @RequestParam(required = false, defaultValue = "100") Integer rowNum) {
        log.info("拉取饥荒的日志：type={},rowNum={}", type, rowNum);
        return ResultVO.data(systemService.getDstLog(type, rowNum));
    }

    /**
     * 获取任务时间列表
     */
    @GetMapping("/getScheduleList")
    @ResponseBody
    @RequiresAuthentication
    public ResultVO<ScheduleVO> getScheduleList(){
        return ResultVO.data(systemService.getScheduleList());
    }

    @PostMapping("/saveSchedule")
    @ResponseBody
    @RequiresAuthentication
    public ResultVO<String> saveSchedule(@RequestBody ScheduleVO vo){
        systemService.saveSchedule(vo);
        return ResultVO.success();
    }


    @GetMapping("/getVersion")
    @ResponseBody
    @RequiresAuthentication
    public ResultVO<Map<String,String>> getVersion(){
        return ResultVO.data(systemService.getVersion());
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }
}
