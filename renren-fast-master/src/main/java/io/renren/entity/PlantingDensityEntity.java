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
 * @date 2023-04-26 04:22:39
 */
@Data
@TableName("planting_density")
public class PlantingDensityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String region;
	/**
	 * 
	 */
	private String density;
	/**
	 * 
	 */
	private String seedType;

}
