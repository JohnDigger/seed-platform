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
 * @date 2023-04-28 11:26:23
 */
@Data
@TableName("planting_area")
public class PlantingAreaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 *
	 */
	private String seedType;
	/**
	 *
	 */
	private String year;
	/**
	 *
	 */
	private String area;
	/**
	 *
	 */
	private String region;
	/**
	 *
	 */
	private String yield;
	/**
	 *
	 */
	private String income;

}
