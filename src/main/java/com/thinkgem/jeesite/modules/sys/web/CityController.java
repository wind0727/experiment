/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.City;
import com.thinkgem.jeesite.modules.sys.service.CityService;

/**
 * 单表生成Controller
 * @author wind
 * @version 2018-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/city")
public class CityController extends BaseController {

	@Autowired
	private CityService cityService;
	
	@ModelAttribute
	public City get(@RequestParam(required=false) String id) {
		City entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cityService.get(id);
		}
		if (entity == null){
			entity = new City();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:city:view")
	@RequestMapping(value = {"list", ""})
	public String list(City city, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<City> page = cityService.findPage(new Page<City>(request, response), city); 
		model.addAttribute("page", page);
		return "modules/sys/cityList";
	}

	@RequiresPermissions("sys:city:view")
	@RequestMapping(value = "form")
	public String form(City city, Model model) {
		model.addAttribute("city", city);
		return "modules/sys/cityForm";
	}

	@RequiresPermissions("sys:city:edit")
	@RequestMapping(value = "save")
	public String save(City city, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, city)){
			return form(city, model);
		}
		cityService.save(city);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/city/?repage";
	}
	
	@RequiresPermissions("sys:city:edit")
	@RequestMapping(value = "delete")
	public String delete(City city, RedirectAttributes redirectAttributes) {
		cityService.delete(city);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/city/?repage";
	}

}