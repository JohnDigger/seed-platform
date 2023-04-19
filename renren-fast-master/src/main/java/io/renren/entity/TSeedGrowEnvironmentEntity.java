package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-18 21:59:40
 */
@Data
@TableName("t_seed_grow_environment")
public class TSeedGrowEnvironmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 生长环境特征编号
	 */
	@TableId
	private Long id;
	/**
	 * 生长气温
	 */
	private Double growTemp;
	/**
	 * 生长湿度
	 */
	private Double growHumidity;
	/**
	 * 生长光照
	 */
	private Double growLight;
	/**
	 * 生长土壤PH值
	 */
	private Double growSoilPh;
	/**
	 * 土壤有机质含量
	 */
	private Double growSoilOrganicContent;
	/**
	 * 土壤质地
	 */
	private Double growSoilQuality;
	/**
	 * 土壤营养元素含量
	 */
	private String growSoilNutrientContent;

}
