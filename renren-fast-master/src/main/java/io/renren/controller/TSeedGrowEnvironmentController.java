package io.renren.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.renren.service.TraceCodeInfoService;
import io.renren.service.TraceInfoService;
import io.renren.service.TraceSeedInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.entity.TSeedGrowEnvironmentEntity;
import io.renren.service.TSeedGrowEnvironmentService;
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
@RequestMapping("seed-platform/tseedgrowenvironment")
public class TSeedGrowEnvironmentController {
    @Autowired
    private TSeedGrowEnvironmentService tSeedGrowEnvironmentService;
    @Autowired
    private TraceSeedInfoService traceSeedInfoService;
    @Autowired
    private TraceCodeInfoService traceCodeInfoService;
    @Autowired
    private TraceInfoService traceInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:tseedgrowenvironment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tSeedGrowEnvironmentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seed-platform:tseedgrowenvironment:info")
    public R info(@PathVariable("id") Long id){
		TSeedGrowEnvironmentEntity tSeedGrowEnvironment = tSeedGrowEnvironmentService.getById(id);

        return R.ok().put("tSeedGrowEnvironment", tSeedGrowEnvironment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:tseedgrowenvironment:save")
    public R save(@RequestBody TSeedGrowEnvironmentEntity tSeedGrowEnvironment){
		tSeedGrowEnvironmentService.save(tSeedGrowEnvironment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:tseedgrowenvironment:update")
    public R update(@RequestBody TSeedGrowEnvironmentEntity tSeedGrowEnvironment){
		tSeedGrowEnvironmentService.updateById(tSeedGrowEnvironment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:tseedgrowenvironment:delete")
    public R delete(@RequestBody Long[] ids){
		tSeedGrowEnvironmentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @GetMapping("/top")
    public R getTop(){
        Integer newSeedCount = tSeedGrowEnvironmentService.count();
        Integer seedInfoCount = traceSeedInfoService.count();
        Integer codeCount = traceCodeInfoService.count();
        Integer traceCount = traceInfoService.count();
        Map<String,Object> map = new HashMap<>();
        map.put("newSeedCount",newSeedCount);
        map.put("seedInfoCount",seedInfoCount);
        map.put("codeCount",codeCount);
        map.put("traceCount",traceCount);
        return R.ok(map);
    }

}
