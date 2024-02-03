package io.renren.controller;

import java.util.*;

import io.renren.entity.TFilingImageEntity;
import io.renren.entity.TImageEntity;
import io.renren.service.TImageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.TFilingSeedEntity;
import io.renren.service.TFilingSeedService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author baligonglu
 * @email 2060736540@qq.com
 * @date 2023-11-06 19:35:31
 */
@RestController
@RequestMapping("seed-platform/tfilingseed")
public class TFilingSeedController {
    @Autowired
    private TFilingSeedService tFilingSeedService;
    @Autowired
    private TImageService tImageService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:tfilingseed:list")
    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = tFilingSeedService.queryPage(params);
        Map<String,Object> map = new HashMap<>();
        map.put("currPage",params.get("page"));
        map.put("list",tFilingSeedService.getAll(Integer.parseInt((String) params.get("page"))-1, Integer.parseInt((String) params.get("limit"))));
        map.put("pageSize",params.get("limit"));
        map.put("totalCount",tFilingSeedService.getCount());
        map.put("totalPage",(int)Math.ceil((double)tFilingSeedService.getCount()/Integer.parseInt(String.valueOf(params.get("limit")))));
        return R.ok().put("page", map);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seed-platform:tfilingseed:info")
    public R info(@PathVariable("id") Long id){
		TFilingSeedEntity tFilingSeed = tFilingSeedService.getById(id);

        return R.ok().put("tFilingSeed", tFilingSeed);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:tfilingseed:save")
    public R save(@RequestBody TFilingSeedEntity tFilingSeed){
        List<Integer> list = new ArrayList<>();
        tFilingSeedService.save(tFilingSeed);
        tFilingSeed.getImageList().forEach(ele -> {
            TImageEntity tImageEntity = new TImageEntity();
            tImageEntity.setImageUrl(ele);
            tImageService.returnIdForInsert(tImageEntity);
            list.add(tImageEntity.getId());
        });
        list.forEach(ele -> {
            tFilingSeedService.insertById(ele, Math.toIntExact(tFilingSeed.getId()));
        });


        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:tfilingseed:update")
    public R update(@RequestBody TFilingSeedEntity tFilingSeed){
        TImageEntity tImageEntity = new TImageEntity();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < tFilingSeed.getImageList().size(); i++) {
            tImageEntity.setImageUrl(tFilingSeed.getImageList().get(i));
            tImageService.returnIdForInsert(tImageEntity);
            integerList.add(tImageEntity.getId());
        }
        List<TFilingImageEntity> productImageEntities;
        productImageEntities = tFilingSeedService.queryProductImage(Math.toIntExact(tFilingSeed.getId()));
        if (productImageEntities.size() == integerList.size()){
            for (int i = 0; i < productImageEntities.size(); i++) {
                tFilingSeedService.updateProductImageById(integerList.get(i), Math.toIntExact(productImageEntities.get(i).getId()));
            }
        }else{
            if (productImageEntities.size() > integerList.size()){
                for (int i = 0; i < integerList.size(); i++) {
                    tFilingSeedService.updateProductImageById(integerList.get(i), Math.toIntExact(productImageEntities.get(i).getId()));
                }
                for (int i = integerList.size();i< productImageEntities.size();i++){
                    tFilingSeedService.deleteById(Math.toIntExact(productImageEntities.get(i).getId()));
                }
            }else {
                for (int i = 0;i < productImageEntities.size();i++){
                    tFilingSeedService.updateProductImageById(integerList.get(i), Math.toIntExact(productImageEntities.get(i).getId()));
                }
                for (int i = productImageEntities.size();i < integerList.size();i++){
                    tFilingSeedService.insertById(integerList.get(i), Math.toIntExact(tFilingSeed.getId()));
                }
            }
        }
        tFilingSeedService.updateById(tFilingSeed);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:tfilingseed:delete")
    public R delete(@RequestBody Long[] ids){
		tFilingSeedService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
