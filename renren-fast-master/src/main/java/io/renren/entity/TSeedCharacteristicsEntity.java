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
 * @date 2023-04-18 21:59:39
 */
@Data
@TableName("t_seed_characteristics")
public class TSeedCharacteristicsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 种子特征编号
	 */
	@TableId
	private Long id;
	/**
	 * 种子大小
	 */
	private Double seedSize;
	/**
	 * 种子颜色
	 */
	private String seedColor;
	/**
	 * 种子形状
	 */
	private String seedShape;
	/**
	 * 种子重量
	 */
	private Double seedWeight;
	/**
	 * 种子营养成分
	 */
	private String seedNutrient;
	/**
	 * 种子表面特征
	 */
	private String seedSurface;
	/**
	 * 种子硬度
	 */
	private String seedHard;
	/**
	 * 种子含水量
	 */
	private Double seedWaterContent;
	/**
	 * 种子发芽率
	 */
	private Double seedGerminationRate;
	/**
	 * 种子生长速度
	 */
	private Double seedGrowthRate;

}
