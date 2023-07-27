package io.renren.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TTitleDao;
import io.renren.entity.TTitleEntity;
import io.renren.service.TTitleService;


@Service("tTitleService")
public class TTitleServiceImpl extends ServiceImpl<TTitleDao, TTitleEntity> implements TTitleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TTitleEntity> page = this.page(
                new Query<TTitleEntity>().getPage(params),
                new QueryWrapper<TTitleEntity>()
        );

        return new PageUtils(page);
    }

}