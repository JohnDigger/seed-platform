package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.google.zxing.WriterException;
import io.renren.common.utils.PageUtils;
import io.renren.entity.TraceSeedInfoEntity;
import io.renren.entity.TraceSeedInfoEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-23 21:36:46
 */
public interface TraceSeedInfoService extends IService<TraceSeedInfoEntity> {
    List<Map<String,String>> getSeedInfoByCode(String code);

    int updateTraceCode(String name,String producer) throws IOException, WriterException;

    PageUtils queryPage(Map<String, Object> params);
}

