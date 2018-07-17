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
import org.springframework.http.HttpRequest;
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
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentAppointment;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentRecord;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentAppointmentService;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentRecordService;
import com.thinkgem.jeesite.modules.experiment.service.TeacherCommentService;
import com.thinkgem.jeesite.modules.experiment.utils.DateUtils;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentAppointmentVO;
import com.thinkgem.jeesite.modules.experiment.vo.StudentInfoVO;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 单表生成Controller
 * @author wind
 * @version 2018-02-02
 */
@Controller
@RequestMapping(value = "${adminPath}/experiment/experimentHistory")
public class ExperimentHistoryController extends BaseController {

	@Autowired(required=true)
	private TeacherCommentService teacherCommentService;
	@Autowired(required=true)
	private ExperimentRecordService experimentRecordService;
	
	@ModelAttribute
	public ExperimentRecord get(@RequestParam(required=false) String experimentInfoId) {
		ExperimentRecord entity = null;
		if (StringUtils.isNotBlank(experimentInfoId)){
			entity = teacherCommentService.get(experimentInfoId);
		}
		if (entity == null){
			entity = new ExperimentRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("experiment:experimentHistory:view")
	@RequestMapping(value = {"list", ""})
	public String list(ExperimentRecord experimentRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		String officeId = user.getId();
		Page<ExperimentRecord> page = new Page<ExperimentRecord>(request, response);
		List<ExperimentInfo> experimentname=experimentRecordService.getHistoryExperiemntName(officeId);
		
			if (experimentRecord.getExperimentInfoId() == null && experimentname != null && !experimentname.isEmpty()) {
				experimentRecord.setExperimentInfoId(experimentname.get(0).getId());
			}
			if (experimentRecord.getExperimentEndtime() == null && experimentRecord.getExperimentStarttime() == null) {
				experimentRecord.setExperimentStarttime(DateUtils.getFirstOrEndDayOfMonth(true));
				experimentRecord.setExperimentEndtime(DateUtils.getFirstOrEndDayOfMonth(false));
			}
		List<ExperimentRecord> studentInfos = experimentRecordService.getStuedntHistoryExperiemnt(page,officeId,experimentRecord);
		page.setList(studentInfos); 
		model.addAttribute("experimentname", experimentname);
		model.addAttribute("studentInfos", studentInfos);
		model.addAttribute("page", page);
		return "modules/experiment/experimentHistoryList";
	}
	
	@RequiresPermissions("experiment:experimentHistory:view")
	@RequestMapping(value = "minuteList")
	public String miuteList(ExperimentAppointment experimentAppointment,ExperimentRecord experimentRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		String officeId = user.getId();
		String id = request.getParameter("id");
		// 学生信息.
		ExperimentRecord studentInfo = experimentRecordService.getMinuteStuedntHistoryExperiemnt(officeId,experimentRecord,id);
		List<ExperimentInfo> experimentname = experimentRecordService.getHistoryExperiemntName(officeId);
		
		model.addAttribute("experimentname", experimentname);
		model.addAttribute("studentInfo", studentInfo);
		return "modules/experiment/minuteExperimentRecordHistoryForm";
	}
	
	@RequiresPermissions("experiment:experimentHistory:view")
	@RequestMapping(value = "form")
	public String form(ExperimentRecord experimentRecord, Model model) {
		User teacher = UserUtils.getUser();
		String officeId = teacher.getId();
		List<ExperimentRecord> studentInfos = teacherCommentService.getTeacherCommentByCondition(officeId);
		
		model.addAttribute("studentInfos", studentInfos);
		model.addAttribute("experimentRecord", experimentRecord);
		return "modules/experiment/experimentHistory";
	}

/*	@RequiresPermissions("experiment:experimentHistory:edit")
	@RequestMapping(value = "save")
	public String save(ExperimentRecord experimentRecord, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, experimentRecord)){
			return form(experimentRecord, model);
		}
		String teacherId = experimentRecord.getTeacherId();
		String teacherName = teacherCommentService.getTeacherNameById(teacherId);
		String experimentAppointmentId = experimentRecord.getExperimentAppointmentId();
		String experimentInfoId = teacherCommentService.getExperimentInfoIdByappointmentId(experimentAppointmentId);
		String experimentInfoIdName = teacherCommentService.getExperimentInfoIdNameById(experimentInfoId);
		
		ExperimentAppointmentVO appointment = teacherCommentService.findExperimentAndTeacherInfoByCondition(experimentAppointmentId);
		
		experimentRecord.setTeacherName(teacherName);
		experimentRecord.setExperimentName(experimentInfoIdName);
		User user = UserUtils.getUser();
		
		experimentRecord.setExperimentAppointmentId(experimentAppointmentId);
		if (appointment != null) {
			experimentRecord.setExperimentInfoId(appointment.getExperimentInfoId());
			experimentRecord.setTeacherId(appointment.getTeacherId());
		}
		experimentRecord.setCreateBy(user);
		experimentRecord.setCreateDate(new Date());
		teacherCommentService.save(experimentRecord);
		teacherCommentService.saveHistory(experimentRecord);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/experimentHistory/?repage";
	}
	*/

}