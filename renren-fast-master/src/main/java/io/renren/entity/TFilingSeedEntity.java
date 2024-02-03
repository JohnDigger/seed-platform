package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * 
 * @author baligonglu
 * @email 2060736540@qq.com
 * @date 2023-11-06 19:35:31
 */
@Data
@TableName("t_filing_seed")
public class TFilingSeedEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 备案id
	 */
	@TableId
	private Long id;
	/**
	 * 备案种子品种
	 */
	private String filingSeedName;
	/**
	 * 备案种子观察点(产地)
	 */
	private String filingSeedOrigin;
	/**
	 * 备案种子生产商
	 */
	private String filingSeedProducer;
	/**
	 * 备案种子经销商
	 */
	private String filingSeedSeller;
	/**
	 * 备案种子采集时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date filingSeedTime;
	/**
	 * 备案种子价格
	 */
	private BigDecimal filingSeedPrice;
	/**
	 * 
	 */
	private Integer filingSeedWeight;

	@TableField(value = "image_url", exist = false)
	private List<TImageEntity> TImags;

	@TableField(exist = false)
	private List<String> imageList;

}
