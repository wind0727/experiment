/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.City;
import com.thinkgem.jeesite.modules.sys.dao.CityDao;

/**
 * 单表生成Service
 * @author wind
 * @version 2018-03-15
 */
@Service
@Transactional(readOnly = true)
public class CityService extends CrudService<CityDao, City> {

	public City get(String id) {
		return super.get(id);
	}
	
	public List<City> findList(City city) {
		return super.findList(city);
	}
	
	public Page<City> findPage(Page<City> page, City city) {
		return super.findPage(page, city);
	}
	
	@Transactional(readOnly = false)
	public void save(City city) {
		super.save(city);
	}
	
	@Transactional(readOnly = false)
	public void delete(City city) {
		super.delete(city);
	}
	
}