package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 种子全流程信息表
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-25 21:39:34
 */
@Data
@TableName("seed_process_management")
public class SeedProcessManagementEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 品种
	 */
	private String varieties;
	/**
	 * 种属
	 */
	private String speciesGenera;
	/**
	 * 种子大小
	 */
	private String size;
	/**
	 * 种子颜色
	 */
	private String color;
	/**
	 * 种子产地
	 */
	private String origin;
	/**
	 * 种子品种
	 */
	private String variety;
	/**
	 * 种植方式
	 */
	private String plantingMethod;
	/**
	 * 主要种植区域
	 */
	private String plantingArea;
	/**
	 * 播种时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date sowingTime;
	/**
	 * 生长周期
	 */
	private String growthCycle;
	/**
	 * 环境温度（℃）
	 */
	private BigDecimal environmentTemperature;
	/**
	 * 平均产量
	 */
	private String averageYield;

}
