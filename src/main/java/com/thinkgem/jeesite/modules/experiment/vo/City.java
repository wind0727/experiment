package com.thinkgem.jeesite.modules.experiment.vo;

import java.io.Serializable;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.Page;

public class City implements Serializable {

	private String cityId;
	private String cityName;
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
