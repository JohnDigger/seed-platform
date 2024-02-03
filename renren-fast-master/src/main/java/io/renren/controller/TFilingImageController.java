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

import io.renren.entity.TFilingImageEntity;
import io.renren.service.TFilingImageService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author baligonglu
 * @email 2060736540@qq.com
 * @date 2023-11-06 20:50:36
 */
@RestController
@RequestMapping("seed-platform/tfilingimage")
public class TFilingImageController {
    @Autowired
    private TFilingImageService tFilingImageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:tfilingimage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tFilingImageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seed-platform:tfilingimage:info")
    public R info(@PathVariable("id") Long id){
		TFilingImageEntity tFilingImage = tFilingImageService.getById(id);

        return R.ok().put("tFilingImage", tFilingImage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:tfilingimage:save")
    public R save(@RequestBody TFilingImageEntity tFilingImage){
		tFilingImageService.save(tFilingImage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:tfilingimage:update")
    public R update(@RequestBody TFilingImageEntity tFilingImage){
		tFilingImageService.updateById(tFilingImage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:tfilingimage:delete")
    public R delete(@RequestBody Long[] ids){
		tFilingImageService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
