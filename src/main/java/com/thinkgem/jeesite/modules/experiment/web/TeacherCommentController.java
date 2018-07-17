/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentArrange;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentRecord;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentAppointmentService;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentRecordService;
import com.thinkgem.jeesite.modules.experiment.service.TeacherCommentService;
import com.thinkgem.jeesite.modules.experiment.utils.DateUtils;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentAppointmentVO;
import com.thinkgem.jeesite.modules.experiment.vo.StudentInfoVO;
import com.thinkgem.jeesite.modules.gen.util.ExperimentUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 单表生成Controller
 * @author wind
 * @version 2018-02-02
 */
@Controller
@RequestMapping(value = "${adminPath}/experiment/teacherComment")
public class TeacherCommentController extends BaseController {
	@Autowired
	private ExperimentRecordService	experimentRecordService;
	@Autowired(required=true)
	private TeacherCommentService teacherCommentService;
	
	private SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
	
	
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
	
	@RequiresPermissions("experiment:teacherComment:view")
	@RequestMapping(value = {"list", ""})
	public String list(ExperimentRecord experimentRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		User teacher = UserUtils.getUser();
		String id = teacher.getId();
		Page<ExperimentRecord> page = new Page<ExperimentRecord>(request, response);
		experimentRecord.setTeacherId(teacher.getId());
		List<ExperimentInfo> experiments = teacherCommentService.getExperimentInfoName(id);
		
		if (experimentRecord.getExperimentInfoId() == null && !experiments.isEmpty() && experiments != null) {
			experimentRecord.setExperimentInfoId(experiments.get(0).getId());
		}
		
		if (experimentRecord.getExperimentStarttime() == null && experimentRecord.getExperimentEndtime() == null) {
			experimentRecord.setExperimentStarttime(DateUtils.getFirstOrEndDayOfMonth(true));
			experimentRecord.setExperimentEndtime(DateUtils.getFirstOrEndDayOfMonth(false));
		}	
		List<ExperimentRecord> studentInfos = teacherCommentService.getStuedntInfoVOByCondition(page,experimentRecord);
		
		page.setList(studentInfos);
		model.addAttribute("experiments", experiments);
		model.addAttribute("page", page);
		return "modules/experiment/teacherCommentList";
	}

	@RequiresPermissions("experiment:teacherComment:view")
	@RequestMapping(value = "form")
	public String form(ExperimentRecord experimentRecord, Model model, HttpServletRequest request) {
		SimpleDateFormat dateFormatymd = new SimpleDateFormat("yyyy-MM-dd");
		User teacher = UserUtils.getUser();
		String officeId = teacher.getId();
		String id = request.getParameter("id");
		experimentRecord = experimentRecordService.getExperimentRecord(id);
		String studentId = experimentRecord.getStudentId();
		String studentName = experimentRecordService.getStudentName(studentId);
		experimentRecord.setStudentName(studentName);
		experimentRecord.setExperimentInfoId(request.getParameter("experimentInfoId"));
		experimentRecord.setId(id);
		if (StringUtils.isNotBlank(request.getParameter("experimentEndtime")) && StringUtils.isNotBlank(request.getParameter("experimentStarttime"))) {
			try {
				experimentRecord.setExperimentEndtime(dateFormatymd.parse(request.getParameter("experimentEndtime")));
				experimentRecord.setExperimentStarttime(dateFormatymd.parse(request.getParameter("experimentStarttime")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<ExperimentRecord> studentInfos = teacherCommentService.getTeacherCommentByCondition(officeId);
		model.addAttribute("studentInfos", studentInfos);
		model.addAttribute("experimentRecord", experimentRecord);
		return "modules/experiment/teacherCommentForm";
	}

	@RequiresPermissions("experiment:teacherComment:edit")
	@RequestMapping(value = "save")
	public String save(ExperimentRecord experimentRecord, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, experimentRecord)){
			return form(experimentRecord, model,request);
		}
		String experimentStarttime = request.getParameter("experimentStarttime");
		String experimentEndtime = request.getParameter("experimentEndtime");
		String experimentInfo = request.getParameter("experimentInfoId");
		teacherCommentService.updateScore(experimentRecord);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/teacherComment?experimentInfoId="+experimentInfo+"&experimentEndtime="+experimentEndtime+"&experimentStarttime="+experimentStarttime;
	}
	
	@RequiresPermissions("experiment:teacherComment:edit")
	@RequestMapping(value = "finshSave")
	public String finshSave(ExperimentRecord experimentRecord, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		String experimentStarttime = request.getParameter("experimentStarttime");
		String experimentEndtime = request.getParameter("experimentEndtime");
		String experimentInfo = request.getParameter("experimentInfoId");
		teacherCommentService.finshSave(experimentRecord);
		addMessage(redirectAttributes, "完成测评成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/teacherComment?experimentInfoId="+experimentInfo+"&experimentEndtime="+experimentEndtime+"&experimentStarttime="+experimentStarttime;
	}
	
	

}