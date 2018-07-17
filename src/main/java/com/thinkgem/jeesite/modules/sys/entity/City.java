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
public class City extends DataEntity<City> {
	
	private static final long serialVersionUID = 1L;
	private Long cityId;		// 城市id
	private Long provinceId;		// 省份id
	private String cityName;		// 城市名
	private String remark;		// 备注
	
	public City() {
		super();
	}

	public City(String id){
		super(id);
	}

	@NotNull(message="城市id不能为空")
	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
	@NotNull(message="省份id不能为空")
	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	
	@Length(min=0, max=100, message="城市名长度必须介于 0 和 100 之间")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Length(min=0, max=500, message="备注长度必须介于 0 和 500 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}