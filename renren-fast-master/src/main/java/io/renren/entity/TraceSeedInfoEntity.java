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
@TableName("trace_seed_info")
public class TraceSeedInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 种子id
	 */
	@TableId
	private Integer seedId;
	/**
	 * 种子名称
	 */
	private String seedName;
	/**
	 * 品类
	 */
	private String seedCategory;
	/**
	 * 产地
	 */
	private String seedSource;
	/**
	 * 生产商
	 */
	private String seedProducer;
	/**
	 * 地址
	 */
	private String seedArea;
	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 审定编号
	 */
	private String approvalNumber;
	/**
	 * 生产日期
	 */
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

	private Date productionDate;
	/**
	 * 创建日期
	 */
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

	private Date createDate;
	/**
	 * 防伪码
	 */
	private String seedTraceCode;

}
