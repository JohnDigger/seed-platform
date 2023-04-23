package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 *
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-18 21:59:40
 */
@Data
@TableName("t_mature_seed_characteristics")
public class TMatureSeedCharacteristicsEntity implements Serializable {
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
	 * 种子硬度
	 */
	private String seedHard;
	/**
	 * 种子含水量
	 */
	private Double seedWaterContent;
	/**
	 * 种子产量
	 */
	private Double seedProduction;
	/**
	 * 种子油脂含量
	 */
	private Double seedFatContent;
	/**
	 * 种子的耐储存性
	 */
	private String seedStorageResistence;
	/**
	 * 种子蛋白质含量
	 */
	private Double seedProteinContent;
	/**
	 * 种子编号
	 */

	private Long seedId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@TableField(exist = false)
	private String seedName;
}
