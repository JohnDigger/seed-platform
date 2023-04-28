package io.renren.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.x.protobuf.Mysqlx;
import io.renren.entity.TraceInfoEntity;
import io.renren.service.TraceInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.entity.TraceInfoEntity;
import io.renren.service.TraceInfoService;
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
@RequestMapping("seed-platform/traceinfo")
public class TraceInfoController {
    @Autowired
    private TraceInfoService traceInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:traceinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = traceInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{traceId}")
    @RequiresPermissions("seed-platform:traceinfo:info")
    public R info(@PathVariable("traceId") Integer traceId){
		TraceInfoEntity traceInfo = traceInfoService.getById(traceId);

        return R.ok().put("traceInfo", traceInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:traceinfo:save")
    public R save(@RequestBody TraceInfoEntity traceInfo){
		traceInfoService.save(traceInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:traceinfo:update")
    public R update(@RequestBody TraceInfoEntity traceInfo){
		traceInfoService.updateById(traceInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:traceinfo:delete")
    public R delete(@RequestBody Integer[] traceIds){
		traceInfoService.removeByIds(Arrays.asList(traceIds));

        return R.ok();
    }

    @GetMapping("/getTraceCount")
    public R getTraceCount(){
        Integer traceCount = traceInfoService.getTraceCount();
        return R.ok().put("traceCount", traceCount);
    }

    @GetMapping("/getTraceCountByYear")
    public R getTraceCountByYear(){
        List<Map<String,Integer>> traceCountByYear = traceInfoService.getTraceCountByYear();
        return R.ok().put("traceCountByYear", traceCountByYear);
    }

    @GetMapping("/getTraceCountByNY")
    public R getTraceCountByNY(String name){
        List<Map<String,Integer>> traceCountByNY = traceInfoService.getTraceCountByNY(name);
        return R.ok().put("traceCountByNY",traceCountByNY);
    }

    @GetMapping("/getTraceAreaByName")
    public R getTraceAreaByName(String name){
        List<String> traceAreaByName = traceInfoService.getTraceAreaByName(name);
        return R.ok().put("traceAreaByName", traceAreaByName);
    }

    @GetMapping("/getTraceSeedName")
    public R getTraceSeedName(){
        List<String> traceSeedName = traceInfoService.getTraceSeedName();
        return R.ok().put("traceSeedName", traceSeedName);
    }
    @GetMapping("/getSeven")
    public R getSeven(){
        Map<String,Object>map = new HashMap<>();
        map.put("data",traceInfoService.getRecentSeven());
        return R.ok(map);
    }


}
