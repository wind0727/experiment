/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.Province;
import com.thinkgem.jeesite.modules.sys.dao.ProvinceDao;

/**
 * 单表生成Service
 * @author wind
 * @version 2018-03-15
 */
@Service
@Transactional(readOnly = true)
public class ProvinceService extends CrudService<ProvinceDao, Province> {

	public Province get(String id) {
		return super.get(id);
	}
	
	public List<Province> findList(Province province) {
		return super.findList(province);
	}
	
	public Page<Province> findPage(Page<Province> page, Province province) {
		return super.findPage(page, province);
	}
	
	@Transactional(readOnly = false)
	public void save(Province province) {
		super.save(province);
	}
	
	@Transactional(readOnly = false)
	public void delete(Province province) {
		super.delete(province);
	}
	
}