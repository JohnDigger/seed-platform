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

import io.renren.entity.ConsumerEntity;
import io.renren.service.ConsumerService;
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
@RequestMapping("seed-platform/consumer")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:consumer:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = consumerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{consumerId}")
    @RequiresPermissions("seed-platform:consumer:info")
    public R info(@PathVariable("consumerId") Integer consumerId){
		ConsumerEntity consumer = consumerService.getById(consumerId);

        return R.ok().put("consumer", consumer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:consumer:save")
    public R save(@RequestBody ConsumerEntity consumer){
		consumerService.save(consumer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:consumer:update")
    public R update(@RequestBody ConsumerEntity consumer){
		consumerService.updateById(consumer);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:consumer:delete")
    public R delete(@RequestBody Integer[] consumerIds){
		consumerService.removeByIds(Arrays.asList(consumerIds));

        return R.ok();
    }

}
