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
 * @date 2023-04-28 10:29:39
 */
@Data
@TableName("planting_method")
public class PlantingMethodEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 *
	 */
	private String method;
	/**
	 *
	 */
	private String seedType;
	/**
	 *
	 */
	private String proportion;
	/**
	 *
	 */
	private String yield;
	/**
	 *
	 */
	private String income;
	/**
	 *
	 */
	private String region;

}
