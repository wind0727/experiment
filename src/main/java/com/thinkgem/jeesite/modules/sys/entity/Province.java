/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author wind
 * @version 2018-03-15
 */
public class Province extends DataEntity<Province> {
	
	private static final long serialVersionUID = 1L;
	private Long provinceId;		// 省份id
	private String provinceName;		// 省份名称
	private String effective;		// 是否有效(0:无效，1:有效)
	
	public Province() {
		super();
	}

	public Province(String id){
		super(id);
	}

	@NotNull(message="省份id不能为空")
	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	
	@Length(min=1, max=500, message="省份名称长度必须介于 1 和 500 之间")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	@Length(min=0, max=11, message="是否有效(0:无效，1:有效)长度必须介于 0 和 11 之间")
	public String getEffective() {
		return effective;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}
	
}