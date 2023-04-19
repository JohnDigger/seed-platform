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
 * @date 2023-04-18 21:59:40
 */
@Data
@TableName("trace_record")
public class TraceRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer recordId;
	/**
	 * 
	 */
	private Integer seedId;
	/**
	 * 
	 */
	private Date traceDate;
	/**
	 * 
	 */
	private String traceInfo;
	/**
	 * 
	 */
	private String tracePerson;
	/**
	 * 
	 */
	private Date createdAt;

}
