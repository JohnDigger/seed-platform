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
@TableName("t_plant_characteristics")
public class TPlantCharacteristicsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 植株特征编号
	 */
	@TableId
	private Long id;
	/**
	 * 植株高度
	 */
	private Double plantHeight;
	/**
	 * 叶片形态
	 */
	private String leafShape;
	/**
	 * 果实大小
	 */
	private Double fruitSize;
	/**
	 * 果实颜色
	 */
	private String fruitColor;
	/**
	 * 果实产量
	 */
	private Double fruitProduction;
	/**
	 * 耐逆性
	 */
	private Double stressTolerance;
	/**
	 * 生长周期
	 */
	private String growCycle;
	private Long seedId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@TableField(exist = false)
	private String seedName;

}
