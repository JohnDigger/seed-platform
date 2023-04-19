package io.renren.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.ConsumerDao;
import io.renren.entity.ConsumerEntity;
import io.renren.service.ConsumerService;


@Service("consumerService")
public class ConsumerServiceImpl extends ServiceImpl<ConsumerDao, ConsumerEntity> implements ConsumerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ConsumerEntity> page = this.page(
                new Query<ConsumerEntity>().getPage(params),
                new QueryWrapper<ConsumerEntity>()
        );

        return new PageUtils(page);
    }

}