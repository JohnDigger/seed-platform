package io.renren.service.impl;

import io.renren.dao.TFilingImageDao;
import io.renren.entity.TFilingImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TFilingSeedDao;
import io.renren.entity.TFilingSeedEntity;
import io.renren.service.TFilingSeedService;


@Service("tFilingSeedService")
public class TFilingSeedServiceImpl extends ServiceImpl<TFilingSeedDao, TFilingSeedEntity> implements TFilingSeedService {
    @Autowired
    private TFilingSeedDao tFilingSeedDao;

    @Autowired
    private TFilingImageDao filingImageDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TFilingSeedEntity> page = this.page(
                new Query<TFilingSeedEntity>().getPage(params),
                new QueryWrapper<TFilingSeedEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<TFilingSeedEntity> getAll(int page_cur, int page_size) {
        return tFilingSeedDao.getWithImg(page_cur,page_size);
    }

    @Override
    public int getCount() {
        return tFilingSeedDao.getCount();
    }

    @Override
    public TFilingSeedEntity getByids(Integer id) {
        return tFilingSeedDao.getById(id);
    }

    @Override
    public List<TFilingImageEntity> queryProductImage(int filingId) {
        return filingImageDao.queryByFilingId(filingId);
    }

    @Override
    public int updateProductImageById(int image_id, int id) {
        return filingImageDao.updateFilingImage(image_id,id);
    }

    @Override
    public int deleteById(int id) {
        return filingImageDao.deleteById(id);
    }

    @Override
    public int insertById(int image_id, int product_id) {
        return filingImageDao.insertById(image_id, product_id);
    }

    @Override
    public List<Map<String, Object>> getProductPercentage() {
        return tFilingSeedDao.getProductPercentage();
    }

}