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
 * @date 2023-04-25 21:03:12
 */
@Data
@TableName("trace_code_info")
public class TraceCodeInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 溯源码id
	 */
	@TableId
	private Integer traceCodeId;
	/**
	 * 防伪码
	 */
	private String seedTraceCode;
	/**
	 * 防伪码地址
	 */
	private String seedTraceUrl;
	/**
	 * 被扫次数
	 */
	private Integer codeScanCount;
	/**
	 * 预警等级
	 */
	private String warningLevel;
	/**
	 * 创建时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date createTime;

}
