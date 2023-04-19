package io.renren.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TraceRecordEntity;
import io.renren.service.TraceRecordService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-18 21:59:40
 */
@RestController
@RequestMapping("seed-platform/tracerecord")
public class TraceRecordController {
    @Autowired
    private TraceRecordService traceRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:tracerecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = traceRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{recordId}")
    @RequiresPermissions("seed-platform:tracerecord:info")
    public R info(@PathVariable("recordId") Integer recordId){
		TraceRecordEntity traceRecord = traceRecordService.getById(recordId);

        return R.ok().put("traceRecord", traceRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:tracerecord:save")
    public R save(@RequestBody TraceRecordEntity traceRecord){
		traceRecordService.save(traceRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:tracerecord:update")
    public R update(@RequestBody TraceRecordEntity traceRecord){
		traceRecordService.updateById(traceRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:tracerecord:delete")
    public R delete(@RequestBody Integer[] recordIds){
		traceRecordService.removeByIds(Arrays.asList(recordIds));

        return R.ok();
    }

}
