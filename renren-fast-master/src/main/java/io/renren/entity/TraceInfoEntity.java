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
 * @date 2023-04-23 21:36:46
 */
@Data
@TableName("trace_info")
public class TraceInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 溯源id
	 */
	@TableId
	private Integer traceId;
	/**
	 * 溯源种子
	 */
	private String traceSeedName;
	/**
	 * 
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date traceDate;
	/**
	 * 
	 */
	private String traceArea;

}
