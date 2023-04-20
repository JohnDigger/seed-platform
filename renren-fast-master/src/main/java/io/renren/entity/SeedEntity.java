package io.renren.entity;

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
@TableName("seed")
public class SeedEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer seedId;
	/**
	 * 
	 */
	private String seedName;
	/**
	 * 
	 */
	private String description;
	/**
	 * 
	 */
	private String origin;
	/**
	 * 
	 */
	private String traceCode;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date createdAt;

}
