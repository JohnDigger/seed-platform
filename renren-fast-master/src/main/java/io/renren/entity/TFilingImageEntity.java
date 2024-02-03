package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author baligonglu
 * @email 2060736540@qq.com
 * @date 2023-11-06 20:50:36
 */
@Data
@TableName("t_filing_image")
public class TFilingImageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 图片编号
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Integer imageId;
	/**
	 * 
	 */
	private Integer filingId;

}
