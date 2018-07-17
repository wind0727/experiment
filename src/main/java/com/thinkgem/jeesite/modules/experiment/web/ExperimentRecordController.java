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
import javax.servlet.http.HttpSession;

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
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentRecord;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentAppointmentService;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentInfoService;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentRecordService;
import com.thinkgem.jeesite.modules.experiment.service.TeacherCommentService;
import com.thinkgem.jeesite.modules.experiment.utils.DateUtils;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentAppointmentVO;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentVO;
import com.thinkgem.jeesite.modules.gen.util.ExperimentUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 单表生成Controller
 * @author wind
 * @version 2018-02-02
 */
@Controller
@RequestMapping(value = "${adminPath}/experiment/experimentRecord")
public class ExperimentRecordController extends BaseController {

	@Autowired
	private ExperimentRecordService experimentRecordService;
	@Autowired
	private ExperimentInfoService experimentInfoService;
	@Autowired
	private ExperimentAppointmentService appointmentService;
	@Autowired
	private TeacherCommentService teacherCommentService;
	
	private SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
	private SimpleDateFormat dateFormatymd = new SimpleDateFormat("yyyy-MM-dd");
	
	@ModelAttribute
	public ExperimentRecord get(@RequestParam(required=false) String experimentInfoId) {
		ExperimentRecord entity = null;
		if (StringUtils.isNotBlank(experimentInfoId)){
			entity = experimentRecordService.get(experimentInfoId);
		}
		if (entity == null){
			entity = new ExperimentRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("experiment:experimentRecord:view")
	@RequestMapping(value = {"list", ""})
	public String List(ExperimentAppointmentVO experimentAppointmentVO, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Page<ExperimentAppointmentVO> page = new Page<ExperimentAppointmentVO>(request, response);
		List<ExperimentAppointmentVO> appointments = new ArrayList<ExperimentAppointmentVO>();
		List<ExperimentRecord> studentExperiment = new ArrayList<ExperimentRecord>();
		
		if (experimentAppointmentVO.getExperimentEndtime() == null && experimentAppointmentVO.getExperimentStarttime() == null) {
			experimentAppointmentVO.setExperimentEndtime(DateUtils.getFirstOrEndDayOfMonth(false));
			experimentAppointmentVO.setExperimentStarttime(DateUtils.getFirstOrEndDayOfMonth(true));
		}
		
		studentExperiment = experimentRecordService.getStudentExperimentRecordByStudent(user.getId());
		if ((StringUtils.isBlank(experimentAppointmentVO.getExperimentInfoId()) || experimentAppointmentVO.getExperimentInfoId().equals("null")) 
				&& studentExperiment != null && !studentExperiment.isEmpty()) {
			experimentAppointmentVO.setExperimentInfoId(studentExperiment.get(0).getId());
		}
		
	    experimentAppointmentVO.setPage(page);
	    experimentAppointmentVO.setUserId(user.getId());
	    appointments = experimentRecordService.getStudentExperimentRecordByStudentId(experimentAppointmentVO);
		page.setList(appointments);
		model.addAttribute("page", page);
		model.addAttribute("studentExperiment", studentExperiment);
		return "modules/experiment/experimentRecordList";
	}

	@RequiresPermissions("experiment:experimentRecord:view")
	@RequestMapping(value = "form")
	public String form(ExperimentRecord experimentRecord, Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		String experimentStarttime = request.getParameter("experimentStarttime");
		String experimentEndtime = request.getParameter("experimentEndtime");
		String experimentInfoId = request.getParameter("experimentInfoId");
		String userType = null;
		experimentRecord = experimentRecordService.getExperimentRecord(id);
		List<ExperimentAppointmentVO> appointments = new ArrayList<ExperimentAppointmentVO>();
        User user = UserUtils.getUser();
		try {
			if (StringUtils.isNoneBlank(id)) {
			experimentRecord.setExperimentEndtime(dateFormatymd.parse(experimentEndtime));
			experimentRecord.setExperimentStarttime(dateFormatymd.parse(experimentStarttime));
			experimentRecord.setExperimentInfoId(experimentInfoId);
			}
			appointments = ExperimentUtils.getAppointmentByCondition(appointmentService);
			userType = experimentRecordService.getUserTypeById(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StringUtils.isNoneBlank(id)) {
			model.addAttribute("userType", userType);
			model.addAttribute("experimentRecord", experimentRecord);
			return "modules/experiment/experimentRecordForm";
		}else {
			model.addAttribute("appointments", appointments);
			return "modules/experiment/experimentRecordSaveForm";
		}
	}

	@RequiresPermissions("experiment:experimentRecord:edit")
	@RequestMapping(value = "save")
	public String save(ExperimentRecord experimentRecord, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, experimentRecord)){
			return form(experimentRecord, model,request);
		}
		User user = UserUtils.getUser();
		String experimentStarttime = request.getParameter("experimentStarttime");
		String experimentEndtime = request.getParameter("experimentEndtime");
		String experimentInfoId = request.getParameter("experimentInfoId");
		String experimentAppointmentId = experimentRecord.getExperimentAppointmentId();
		ExperimentRecord experimentRecordList = experimentRecordService.getExperimentInfoIdNameById(experimentAppointmentId);
		
		experimentRecord.setTeacherName(experimentRecordList.getTeacherName());
		experimentRecord.setExperimentName(experimentRecordList.getExperimentName());
		experimentRecord.setStudentId(user.getId());
		experimentRecord.setExperimentAppointmentId(experimentAppointmentId);
		experimentRecord.setExperimentStarttime(experimentRecordList.getExperimentStarttime());
		experimentRecord.setExperimentEndtime(experimentRecordList.getExperimentEndtime());
	    experimentRecord.setExperimentInfoId(experimentRecordList.getExperimentInfoId());
	    experimentRecord.setTeacherId(experimentRecordList.getTeacherId());
		experimentRecord.setCreateBy(user);
		experimentRecord.setCreateDate(new Date());
		
		experimentRecordService.save(experimentRecord);
		addMessage(redirectAttributes, "保存实验记录成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/experimentRecord?experimentInfoId="+experimentInfoId+"&experimentEndtime="+experimentEndtime+"&experimentStarttime="+experimentStarttime;
	}
	
	@RequiresPermissions("experiment:experimentRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(ExperimentRecord experimentRecord, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		String id = request.getParameter("id");
		String experimentStarttime = request.getParameter("experimentStarttime");
		String experimentEndtime = request.getParameter("experimentEndtime");
		experimentRecord.setId(id);
		experimentRecordService.delete(experimentRecord);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/experimentRecord?experimentInfoId="+experimentRecord.getExperimentInfoId()+"&experimentEndtime="+experimentEndtime+"&experimentStarttime="+experimentStarttime;
	}
	
	@RequestMapping(value = "result")
	public String result(ExperimentRecord experimentRecord, Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		ExperimentRecord experimentResult = experimentRecordService.getExperimentResultById(id);
		model.addAttribute("experimentRecord", experimentResult);
		HttpSession session = request.getSession();
		session.setAttribute("experimentResult",experimentResult);
		return "modules/experiment/experimentResultForm";
	}
}

