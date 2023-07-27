package io.renren.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.entity.TTitleEntity;
import io.renren.service.TTitleService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-07-27 19:38:37
 */
@RestController
@RequestMapping("seed-platform/ttitle")
public class TTitleController {
    @Autowired
    private TTitleService tTitleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:ttitle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tTitleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seed-platform:ttitle:info")
    public R info(@PathVariable("id") Long id){
		TTitleEntity tTitle = tTitleService.getById(id);

        return R.ok().put("tTitle", tTitle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:ttitle:save")
    public R save(@RequestBody TTitleEntity tTitle){
        QueryWrapper<TTitleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("short_title", tTitle.getShortTitle());
        TTitleEntity entity = tTitleService.getOne(wrapper);
        if (entity != null) {
            // 如果已经存在数据，则不进行添加
            return R.error("只能存在一个平台名称");
        } else {
            // 如果不存在数据，则进行添加
            tTitleService.save(tTitle);
            return R.ok();
        }

    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:ttitle:update")
    public R update(@RequestBody TTitleEntity tTitle){
		tTitleService.updateById(tTitle);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:ttitle:delete")
    public R delete(@RequestBody Long[] ids){
		tTitleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @GetMapping("/getTitle")
    public R getTitle(){
        QueryWrapper<TTitleEntity> wrapper = new QueryWrapper<>();
        List<TTitleEntity> list = tTitleService.list(wrapper);
        if (list.size() != 0){

            return R.ok().put("data",list);
        }
        return R.error("数据为空");
    }

}
