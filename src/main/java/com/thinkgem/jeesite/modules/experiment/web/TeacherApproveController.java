/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.web;

import java.util.Date;
import java.util.Enumeration;
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
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentAppointmentService;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentArrangeService;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentInfoService;
import com.thinkgem.jeesite.modules.experiment.utils.ExperimentUtils;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentVO;
import com.thinkgem.jeesite.modules.experiment.vo.StudentInfoVO;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2018-01-30
 */
@Controller
@RequestMapping(value = "${adminPath}/experiment/teacherApprove")
public class TeacherApproveController extends BaseController {

	@Autowired
	private ExperimentAppointmentService experimentAppointmentService;
	@Autowired
	private ExperimentInfoService experimentInfoService;
	@Autowired
	 private ExperimentArrangeService experimentArrangeService;
	
	@ModelAttribute
	public ExperimentAppointment get(@RequestParam(required=false) String experimentArrangeId) {
		ExperimentAppointment entity = null;
		if (StringUtils.isNotBlank(experimentArrangeId)){
			entity = experimentAppointmentService.get(experimentArrangeId);
		}
		if (entity == null){
			entity = new ExperimentAppointment();
		}
		return entity;
	}
	
	@RequiresPermissions("experiment:teacherApprove:view")
	@RequestMapping(value = {"list", ""})
	public String list(StudentInfoVO studentInfoVO, HttpServletRequest request, HttpServletResponse response, Model model) {
		User teacher = UserUtils.getUser();
		String id = teacher.getId();
		Page<StudentInfoVO> page = new Page<StudentInfoVO>(request, response);
		// 学生信息.
		List<StudentInfoVO> studentName = experimentAppointmentService.getStuedntInfoName(id);
		if (studentInfoVO.getExperimentInfoId() == null && !studentName.isEmpty() && studentName!=null) {
			studentInfoVO.setExperimentInfoId(studentName.get(0).getExperimentInfoId());
		}
		List<StudentInfoVO> studentInfos = experimentAppointmentService.getStuedntInfoVOByCondition(page,id,studentInfoVO);
		page.setList(studentInfos);
		model.addAttribute("page", page);
		model.addAttribute("studentInfos", studentInfos);
		model.addAttribute("studentName", studentName);
		return "modules/experiment/teacherApproveList";
	}
	
	@RequiresPermissions("experiment:teacherApprove:edit")
	@RequestMapping(value = "approve")
	public String approve(StudentInfoVO studentInfoVO, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		ExperimentAppointment appointment = new ExperimentAppointment();
		String statusString = request.getParameter("status");
	    String experimentEndtime = request.getParameter("experimentEndtime");
	    String experimentStarttime = request.getParameter("experimentStarttime");
		int status = (StringUtils.isEmpty(statusString)) ? 0 : Integer.valueOf(statusString);
		String experimentAppointmentId = request.getParameter("experimentAppointmentId");
		String fusufeReason = request.getParameter("result");
		appointment.setApproveStatus(status);
		appointment.setId(experimentAppointmentId);
		if (status == 2) {
			experimentAppointmentService.updateApproveStatus(appointment);
			experimentArrangeService.updateExperimentAppointmentCount(experimentAppointmentId);
			addMessage(redirectAttributes, "审核通过");
		} else if (status == 3) {
			appointment.setFusufeReason(fusufeReason);
			experimentAppointmentService.updateApproveStatus(appointment);
			addMessage(redirectAttributes, "审核拒绝");
		}
		return "redirect:"+Global.getAdminPath()+"/experiment/teacherApprove?experimentInfoId="+studentInfoVO.getExperimentInfoId()+"&experimentEndtime="+experimentEndtime+"&experimentStarttime="+experimentStarttime;
	}
}