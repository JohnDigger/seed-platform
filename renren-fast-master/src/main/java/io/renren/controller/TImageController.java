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

import io.renren.entity.TImageEntity;
import io.renren.service.TImageService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author baligonglu
 * @email 2060736540@qq.com
 * @date 2023-11-06 20:50:35
 */
@RestController
@RequestMapping("seed-platform/timage")
public class TImageController {
    @Autowired
    private TImageService tImageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:timage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tImageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seed-platform:timage:info")
    public R info(@PathVariable("id") Integer id){
		TImageEntity tImage = tImageService.getById(id);

        return R.ok().put("tImage", tImage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:timage:save")
    public R save(@RequestBody TImageEntity tImage){
		tImageService.save(tImage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:timage:update")
    public R update(@RequestBody TImageEntity tImage){
		tImageService.updateById(tImage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:timage:delete")
    public R delete(@RequestBody Integer[] ids){
		tImageService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
