package io.renren.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.zxing.WriterException;
import io.renren.entity.TraceSeedInfoEntity;
import io.renren.service.TraceSeedInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.entity.TraceSeedInfoEntity;
import io.renren.service.TraceSeedInfoService;
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
@RequestMapping("seed-platform/traceseedinfo")
public class TraceSeedInfoController {
    @Autowired
    private TraceSeedInfoService traceSeedInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:traceseedinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = traceSeedInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{seedId}")
    @RequiresPermissions("seed-platform:traceseedinfo:info")
    public R info(@PathVariable("seedId") Integer seedId){
		TraceSeedInfoEntity traceSeedInfo = traceSeedInfoService.getById(seedId);

        return R.ok().put("traceSeedInfo", traceSeedInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:traceseedinfo:save")
    public R save(@RequestBody TraceSeedInfoEntity traceSeedInfo){
		traceSeedInfoService.save(traceSeedInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:traceseedinfo:update")
    public R update(@RequestBody TraceSeedInfoEntity traceSeedInfo){
		traceSeedInfoService.updateById(traceSeedInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:traceseedinfo:delete")
    public R delete(@RequestBody Integer[] seedIds){
		traceSeedInfoService.removeByIds(Arrays.asList(seedIds));

        return R.ok();
    }

    @PostMapping("/getSeedInfoByCode")
    public R getSeedInfoByCode(String code) {
        List<Map<String, String>> seedInfoByCode = traceSeedInfoService.getSeedInfoByCode(code);
        return R.ok().put("seedInfoByCode", seedInfoByCode);
    }

    @PostMapping("/updateTraceCode")
    public R updateTraceCode(String name,String producer) throws IOException, WriterException {
        System.out.println(name);
        System.out.println("98"+producer);
        int traceCodeStates = traceSeedInfoService.updateTraceCode(name, producer);
        return R.ok().put("traceCodeStates",traceCodeStates);
    }


}
