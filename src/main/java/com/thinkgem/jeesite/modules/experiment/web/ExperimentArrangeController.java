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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.experiment.entity.Course;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentArrange;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentArrangeService;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentInfoService;
import com.thinkgem.jeesite.modules.experiment.utils.DateUtils;
import com.thinkgem.jeesite.modules.experiment.utils.ExperimentUtils;
import com.thinkgem.jeesite.modules.experiment.utils.StringUtil;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentVO;
import com.thinkgem.jeesite.modules.experiment.vo.TeacherVO;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.TeacherService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 锟斤拷锟斤拷锟斤拷锟斤拷Controller
 * @author ThinkGem
 * @version 2018-01-23
 */
@Controller
@RequestMapping(value = "${adminPath}/experiment/experimentArrange")
public class ExperimentArrangeController extends BaseController {
	
	@Autowired
	private ExperimentArrangeService experimentArrangeService;
	@Autowired
	private ExperimentInfoService experimentInfoService;
	@Autowired
	private TeacherService teacherService;
	
	private SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
	private SimpleDateFormat dateFormatymd = new SimpleDateFormat("yyyy-mm-dd");
	
	@ModelAttribute
	public ExperimentArrange get(@RequestParam(required=false) String experimentInfoId) {
		ExperimentArrange entity = null;
		if (StringUtils.isNotBlank(experimentInfoId)){
			entity = experimentArrangeService.get(experimentInfoId);
		}
		if (entity == null){
			entity = new ExperimentArrange();
		}
		return entity;
	}
	
	@RequiresPermissions("experiment:experimentArrange:view")
	@RequestMapping(value = {"list", ""})
	public String list(ExperimentArrange experimentArrange, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ExperimentVO> experiments = null;
		User user = UserUtils.getUser();
		String experimentId = request.getParameter("experimentId");
		String endTime = request.getParameter("endTime");
		String startTime = request.getParameter("startTime");
			try {
				if(experimentArrange.getEndExperimentStarttime() == null && experimentArrange.getExperimentEndtime() == null) {
				experimentArrange.setExperimentEndtime(StringUtils.isBlank(endTime) ? null : sdf1.parse(endTime));
				experimentArrange.setExperimentStarttime(StringUtils.isBlank(startTime) ? null : sdf1.parse(startTime));
				}
				experiments = ExperimentUtils.getExperimentInfosByAdmin();
				experiments = StringUtil.exchangeList(experimentId, experiments);
				if (experimentArrange.getExperimentInfoId() == null && experiments != null && !experiments.isEmpty()) {
				String experimentInfoId = experiments.get(0).getExperimentInfoId();
				experimentArrange.setExperimentInfoId(experimentInfoId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		Page<ExperimentArrange> page = experimentArrangeService.findExperimentArrangePageByOffice(new Page<ExperimentArrange>(request, response), experimentArrange,user.getOffice().getId());
		model.addAttribute("page", page);
		model.addAttribute("experiments", experiments);
		return "modules/experiment/experimentArrangeList";
	}

	@RequiresPermissions("experiment:experimentArrange:view")
	@RequestMapping(value = "form")
	public String form(ExperimentArrange experimentArrange, Model model) {
		
		String officeId = UserUtils.getUser().getOffice().getId();
		List<Course> courseList = experimentArrangeService.listCourseByOfficeId(officeId);
		
		List<ExperimentInfo> experimentInfosList = null;
		if (!org.springframework.util.StringUtils.isEmpty(courseList.get(0))) {
			experimentInfosList = experimentArrangeService.listExperimentByCourseId(courseList.get(0).getId());
		}
		
		model.addAttribute("courseList", courseList);        
		model.addAttribute("experimentInfosList", experimentInfosList);        
		return "modules/experiment/experimentArrangeForm";
	}
	
	
	@RequiresPermissions("experiment:experimentArrange:view")
	@RequestMapping(value = "editage")
	public String editage(ExperimentArrange experimentArrange, Model model,HttpServletRequest request) {
		String experimentStarttime = request.getParameter("experimentStarttime");
		String experimentEndtime = request.getParameter("experimentEndtime");
		String id = request.getParameter("id");
		try {
			experimentArrange = experimentArrangeService.getExperimentArrangeById(id);
			experimentArrange.setBeginExperimentStarttime(dateFormatymd.parse(experimentStarttime));
			experimentArrange.setEndExperimentStarttime(dateFormatymd.parse(experimentEndtime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("experimentArrange", experimentArrange);
		return "modules/experiment/experimentArrangeUpdateForm";
	}

	@RequiresPermissions("experiment:experimentArrange:edit")
	@RequestMapping(value = "save")
	public String save(ExperimentArrange experimentArrange, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, experimentArrange)){
			return form(experimentArrange, model);
		}
		
		String experimentInFoId = request.getParameter("experimentName");
		experimentArrange.setExperimentInfoId(experimentInFoId);
		experimentArrange.setExperimentName(experimentArrangeService.getExperimentNameById(experimentInFoId));
		experimentArrange.setTeacherName(experimentArrangeService.getTeacherByCourse(experimentArrange.getCourse().getId()).getTeacherName());
		experimentArrange.setTeacherId(experimentArrangeService.getTeacherByCourse(experimentArrange.getCourse().getId()).getTeacherId());
		experimentArrangeService.save(experimentArrange);
		addMessage(redirectAttributes, "实验安排成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/experimentArrange/?repage";
	}
	
	@RequiresPermissions("experiment:experimentArrange:edit")
	@RequestMapping(value = "update")
	public String update(ExperimentArrange experimentArrange, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request,RedirectAttributes attr) {
		if (!beanValidator(model, experimentArrange)){
			return form(experimentArrange, model);
		}
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			experimentArrange.setUpdateBy(user);
		}
		String startTime = request.getParameter("beginExperimentStarttime");
		String endTime = request.getParameter("endExperimentStarttime");
		String experimentInfoId = request.getParameter("experimentInfoId");
		experimentArrange.setUpdateDate(new Date());
		experimentArrangeService.updateExperimentArrange(experimentArrange);
		redirectAttributes.addAttribute("experimentId",experimentArrange.getExperimentInfoId());
		addMessage(redirectAttributes, "实验修改成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/experimentArrange?experimentId="+experimentInfoId+"&endTime="+endTime+"&startTime="+startTime;
	}
	
	@RequiresPermissions("experiment:experimentArrange:edit")
	@RequestMapping(value = "delete")
	public String delete(ExperimentArrange experimentArrange, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		String id = request.getParameter("id");
		String experimentInfoId = request.getParameter("experimentInfoId");
		experimentArrange.setId(id);
		experimentArrangeService.delete(experimentArrange);
		redirectAttributes.addAttribute("experimentId",experimentArrange.getExperimentInfoId());
		addMessage(redirectAttributes, "实验删除成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/experimentArrange?experimentId="+experimentInfoId+"&endTime="+experimentArrange.getExperimentEndtime()+"&startTime="+experimentArrange.getExperimentStarttime();
	}
	
	@RequiresPermissions("experiment:experimentArrange:edit")
	@RequestMapping(value = "getCourseListByOfficeIdAjax")
	@ResponseBody
	public String getCourseListByOfficeIdAjax(ExperimentArrange experimentArrange, HttpServletRequest request) {
		String courseId = request.getParameter("courseId");
		List<ExperimentInfo> courselist = experimentArrangeService.listExperimentByCourseId(courseId);
		Gson gson = new Gson();
		String json = gson.toJson(courselist);
		return json;
	}

}