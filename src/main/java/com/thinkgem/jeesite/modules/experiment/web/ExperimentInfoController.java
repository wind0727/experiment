/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.web;

import java.util.ArrayList;
import java.util.List;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentInfoService;
import com.thinkgem.jeesite.modules.experiment.utils.StringUtil;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-01-22
 */
@Controller
@RequestMapping(value = "${adminPath}/experiment/experimentInfo")
public class ExperimentInfoController extends BaseController {

	@Autowired
	private ExperimentInfoService experimentInfoService;
	private static final char OFFICEID = '1';
	
	@ModelAttribute
	public ExperimentInfo get(@RequestParam(required=false) String id) {
		ExperimentInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = experimentInfoService.get(id);
		}
		if (entity == null){
			entity = new ExperimentInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("experiment:experimentInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(ExperimentInfo experimentInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		experimentInfo.setOffice(new Office());
		experimentInfo.getOffice().setId("1");
		experimentInfo.setDelFlag("0");
		Page<ExperimentInfo> page = experimentInfoService.findPageByOffice(new Page<ExperimentInfo>(request, response), experimentInfo);
		model.addAttribute("page", page);
		return "modules/experiment/experimentInfoList";
	}

	@RequiresPermissions("experiment:experimentInfo:view")
	@RequestMapping(value = "form")
	public String form(ExperimentInfo experimentInfo, Model model) {
		model.addAttribute("experimentInfo", experimentInfo);
		return "modules/experiment/experimentInfoForm";
	}

	@RequiresPermissions("experiment:experimentInfo:edit")
	@RequestMapping(value = "save")
	public String save(ExperimentInfo experimentInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, experimentInfo)){
			return form(experimentInfo, model);
		}
		experimentInfoService.save(experimentInfo);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/experimentInfo/?repage";
	}
	
	@RequiresPermissions("experiment:experimentInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(ExperimentInfo experimentInfo, RedirectAttributes redirectAttributes) {
		experimentInfoService.delete(experimentInfo);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/experimentInfo/?repage";
	}

}