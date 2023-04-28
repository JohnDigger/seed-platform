package io.renren.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.entity.TraceCodeInfoEntity;
import io.renren.service.TraceCodeInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.entity.TraceCodeInfoEntity;
import io.renren.service.TraceCodeInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 *
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-23 21:36:46
 */
@RestController
@RequestMapping("seed-platform/tracecodeinfo")
public class TraceCodeInfoController {
    @Autowired
    private TraceCodeInfoService traceCodeInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:tracecodeinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = traceCodeInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{traceCodeId}")
    @RequiresPermissions("seed-platform:tracecodeinfo:info")
    public R info(@PathVariable("traceCodeId") Integer traceCodeId){
		TraceCodeInfoEntity traceCodeInfo = traceCodeInfoService.getById(traceCodeId);

        return R.ok().put("traceCodeInfo", traceCodeInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:tracecodeinfo:save")
    public R save(@RequestBody TraceCodeInfoEntity traceCodeInfo){
		traceCodeInfoService.save(traceCodeInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:tracecodeinfo:update")
    public R update(@RequestBody TraceCodeInfoEntity traceCodeInfo){
		traceCodeInfoService.updateById(traceCodeInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:tracecodeinfo:delete")
    public R delete(@RequestBody Integer[] traceCodeIds){
		traceCodeInfoService.removeByIds(Arrays.asList(traceCodeIds));

        return R.ok();
    }

    @GetMapping("/getTraceCodeCount")
    public R getTraceCodeCount(){
        Integer traceCodeCount = traceCodeInfoService.getTraceCodeCount();
        return R.ok().put("traceCodeCount", traceCodeCount);
    }

    @GetMapping("/getTraceCodeCountByYear")
    public R getTraceCodeCountByYear(){
        List<Map<String,Integer>> traceCodeCountByYear = traceCodeInfoService.getTraceCodeCountByYear();
        return R.ok().put("traceCodeCountByYear", traceCodeCountByYear);
    }

    @GetMapping("/getTraceCodeCountByWarningLevel")
    public R getTraceCodeCountByWarningLevel(){
        List<Map<String,Integer>> traceCodeCountByWarningLevel = traceCodeInfoService.getTraceCodeCountByWarningLevel();
        return R.ok().put("traceCodeCountByWarningLevel", traceCodeCountByWarningLevel);
    }

}
