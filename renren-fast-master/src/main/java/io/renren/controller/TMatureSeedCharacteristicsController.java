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

import io.renren.entity.TMatureSeedCharacteristicsEntity;
import io.renren.service.TMatureSeedCharacteristicsService;
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
@RequestMapping("seed-platform/tmatureseedcharacteristics")
public class TMatureSeedCharacteristicsController {
    @Autowired
    private TMatureSeedCharacteristicsService tMatureSeedCharacteristicsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:tmatureseedcharacteristics:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tMatureSeedCharacteristicsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seed-platform:tmatureseedcharacteristics:info")
    public R info(@PathVariable("id") Long id){
		TMatureSeedCharacteristicsEntity tMatureSeedCharacteristics = tMatureSeedCharacteristicsService.getById(id);

        return R.ok().put("tMatureSeedCharacteristics", tMatureSeedCharacteristics);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:tmatureseedcharacteristics:save")
    public R save(@RequestBody TMatureSeedCharacteristicsEntity tMatureSeedCharacteristics){
		tMatureSeedCharacteristicsService.save(tMatureSeedCharacteristics);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:tmatureseedcharacteristics:update")
    public R update(@RequestBody TMatureSeedCharacteristicsEntity tMatureSeedCharacteristics){
		tMatureSeedCharacteristicsService.updateById(tMatureSeedCharacteristics);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:tmatureseedcharacteristics:delete")
    public R delete(@RequestBody Long[] ids){
		tMatureSeedCharacteristicsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
