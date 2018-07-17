/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentAppointment;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentArrange;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentAppointmentService;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentInfoService;
import com.thinkgem.jeesite.modules.experiment.utils.DateUtils;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentVO;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-01-30
 */
@Controller
@RequestMapping(value = "${adminPath}/experiment/myAppointment")
public class MyAppointmentController extends BaseController {
	private static final String ZEPPELIN_URL = "zeppelin.url";

	@Autowired
	private ExperimentAppointmentService experimentAppointmentService;
	@Autowired
	private ExperimentInfoService experimentInfoService;
	
	@ModelAttribute
	public ExperimentAppointment get(@RequestParam(required=false) String id) {
		ExperimentAppointment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = experimentAppointmentService.get(id);
		}
		if (entity == null){
			entity = new ExperimentAppointment();
		}
		return entity;
	}
	
	@RequiresPermissions("experiment:myAppointment:view")
	@RequestMapping(value = {"list", ""})
	public String list(ExperimentArrange experimentArrange, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		String userId = user.getId();
		String name = user.getLoginName();
		String password = (String) UserUtils.getCache(UserUtils.USER_CACHE_PLAIN_PASSWORD_ + user.getLoginName());
		String zeppelinUrl = Global.getConfig(ZEPPELIN_URL);
		
		if (experimentArrange.getExperimentStarttime() == null && experimentArrange.getExperimentEndtime() == null) {
			experimentArrange.setExperimentStarttime(DateUtils.getFirstOrEndDayOfMonth(true));
			experimentArrange.setExperimentEndtime(DateUtils.getFirstOrEndDayOfMonth(false));
		}
		
		Page<ExperimentArrange> page = new Page<ExperimentArrange>(request, response);
		List<ExperimentInfo> experimentNames = experimentAppointmentService.getMyAppointmentByStudentId(userId);
		if (experimentArrange.getExperimentInfoId() == null && !experimentNames.isEmpty() && experimentNames != null) {
			experimentArrange.setExperimentInfoId(experimentNames.get(0).getId());
		}
		
		experimentArrange.setPage(page);
		List<ExperimentArrange> myExperimentAppointments = experimentAppointmentService.getMyAppointmentByUser(experimentArrange,userId);
		for (ExperimentArrange experiment : myExperimentAppointments) {
			experiment.setUserName(name);
			experiment.setPassword(password);
		}
		
		page.setList(myExperimentAppointments);
		model.addAttribute("page", page);
		model.addAttribute("loginName", user.getLoginName());
		model.addAttribute("password", password);
		model.addAttribute("zeppelinUrl", zeppelinUrl);
		model.addAttribute("experimentNames", experimentNames);
		return "modules/experiment/myAppointmentList";
	}

	@RequiresPermissions("experiment:myAppointment:edit")
	@RequestMapping(value = "appointment")
	public String save( HttpServletRequest request, Model model, RedirectAttributes redirectAttributes,ExperimentArrange experimentArrange) {
		String experimentStarttime = request.getParameter("experimentStarttime");
		String experimentEndtime = request.getParameter("experimentEndtime");
		String experimentInfoId = request.getParameter("experimentInfoId");
		String id = request.getParameter("id");
		ExperimentAppointment appointment = new ExperimentAppointment();
		appointment.setApproveStatus(4);
		appointment.setId(id);
		experimentAppointmentService.updateApproveStatus(appointment);
		addMessage(redirectAttributes, "取消成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/myAppointment?experimentInfoId="+experimentInfoId+"&experimentEndtime="+experimentEndtime+"&experimentStarttime="+experimentStarttime;
	}
	
	@RequiresPermissions("experiment:experimentAppointment:edit")
	@RequestMapping(value = "updateStatus")
	public String updateApproveStatus( HttpServletRequest request, Model model, RedirectAttributes redirectAttributes,ExperimentArrange experimentArrange) {
		String experimentStarttime = request.getParameter("experimentStarttime");
		String experimentEndtime = request.getParameter("experimentEndtime");
		String experimentInfoId = request.getParameter("experimentInfoId");
		String id = request.getParameter("id");
		ExperimentAppointment appointment = new ExperimentAppointment();
		appointment.setApproveStatus(1);
		appointment.setId(id);
		addMessage(redirectAttributes, "预约成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/myAppointment?experimentInfoId="+experimentInfoId+"&experimentEndtime="+experimentEndtime+"&experimentStarttime="+experimentStarttime;
	}

}