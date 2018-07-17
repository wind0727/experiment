package com.thinkgem.jeesite.modules.experiment.web;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentAppointment;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentAppointmentService;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentArrangeService;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentInfoService;
import com.thinkgem.jeesite.modules.experiment.utils.StringUtil;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentVO;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-01-30
 */
@Controller
@RequestMapping(value = "${adminPath}/experiment/experimentAppointment")
public class ExperimentAppointmentController extends BaseController {
	
    private static final String APPROVE_STTUS = "2"; //已通过
    
	@Autowired
	private ExperimentAppointmentService experimentAppointmentService;
	@Autowired
	private ExperimentInfoService experimentInfoService;
	@Autowired
	private ExperimentArrangeService experimentArrangeService;
	
	@ModelAttribute
	public ExperimentAppointment get(@RequestParam(required=false) String experimentInfoId) {
		ExperimentAppointment entity = null;
		if (StringUtils.isNotBlank(experimentInfoId)){
			entity = experimentAppointmentService.get(experimentInfoId);
		}
		if (entity == null){
			entity = new ExperimentAppointment();
		}
		return entity;
	}
	
	@RequiresPermissions("experiment:experimentAppointment:view")
	@RequestMapping(value = {"list", ""})
	public String list(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		String officeId = user.getOffice().getParentId();
		List<ExperimentVO> experimentNameList = experimentInfoService.getExperimentName(officeId,user.getId());
		if (experimentAppointment.getExperimentInfoId() == null && experimentNameList != null && experimentNameList.size() > 0) {
			experimentAppointment.setExperimentInfoId(experimentNameList.get(0).getId());
		}else if (experimentNameList != null && experimentNameList.size() > 0) {
			experimentNameList = StringUtil.exchangeList(experimentAppointment.getExperimentInfoId(), experimentNameList);
		}
		Page<ExperimentAppointment> page = new Page<ExperimentAppointment>(request, response);
		experimentAppointment.setPage(page);
		experimentAppointment.setOfficeId(officeId);
		experimentAppointment.setStudentId(user.getId());
		List<ExperimentAppointment> experiments = experimentInfoService.getExperimentVOByGrad(experimentAppointment);
		page.setList(experiments);
    	
		model.addAttribute("experiments", experiments);
		model.addAttribute("page", page);
		model.addAttribute("experimentNameList", experimentNameList);
		return "modules/experiment/experimentAppointmentList";
	}


	@RequiresPermissions("experiment:experimentAppointment:view")
	@RequestMapping(value = "form")
	public String form(ExperimentAppointment experimentAppointment, Model model) {
		model.addAttribute("experimentAppointment", experimentAppointment);
		return "modules/experiment/experimentAppointmentForm";
	}
	
	@RequiresPermissions("experiment:experimentAppointment:edit")
	@RequestMapping(value = "appointment")
	public String save( HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		String experimentInfoId = request.getParameter("experimentInfoId");
		String experimentArrangeId = request.getParameter("experimentArrangeId");
		String experimentStarttime = request.getParameter("experimentStarttime");
		String experimentEndtime = request.getParameter("experimentEndtime");
		ExperimentAppointment experimentAppointment = experimentAppointmentService.getExperimentArrangeById(experimentArrangeId);
		experimentAppointment.setApproveStatus(1);
		experimentAppointment.setIsFinish(1);
		experimentAppointment.setIsJoin("1");
		experimentAppointment.setStudentId(user.getId());
		experimentAppointmentService.save(experimentAppointment);
		addMessage(redirectAttributes, "预约成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/experimentAppointment?experimentInfoId="+experimentInfoId+"&experimentEndtime="+experimentEndtime+"&experimentStarttime="+experimentStarttime;
	}
	
	
}