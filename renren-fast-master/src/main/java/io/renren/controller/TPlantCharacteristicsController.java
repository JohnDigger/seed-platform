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

import io.renren.entity.TPlantCharacteristicsEntity;
import io.renren.service.TPlantCharacteristicsService;
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
@RequestMapping("seed-platform/tplantcharacteristics")
public class TPlantCharacteristicsController {
    @Autowired
    private TPlantCharacteristicsService tPlantCharacteristicsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:tplantcharacteristics:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tPlantCharacteristicsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seed-platform:tplantcharacteristics:info")
    public R info(@PathVariable("id") Long id){
		TPlantCharacteristicsEntity tPlantCharacteristics = tPlantCharacteristicsService.getById(id);

        return R.ok().put("tPlantCharacteristics", tPlantCharacteristics);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:tplantcharacteristics:save")
    public R save(@RequestBody TPlantCharacteristicsEntity tPlantCharacteristics){
		tPlantCharacteristicsService.save(tPlantCharacteristics);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:tplantcharacteristics:update")
    public R update(@RequestBody TPlantCharacteristicsEntity tPlantCharacteristics){
		tPlantCharacteristicsService.updateById(tPlantCharacteristics);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:tplantcharacteristics:delete")
    public R delete(@RequestBody Long[] ids){
		tPlantCharacteristicsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
