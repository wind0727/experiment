/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.experiment.entity.Course;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.service.ChoiceCourseService;
import com.thinkgem.jeesite.modules.experiment.service.CourseService;
import com.thinkgem.jeesite.modules.experiment.utils.PageBean;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Teacher;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 单表生成Controller
 * @author wind
 * @version 2018-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/experiment/course")
public class CourseController extends BaseController {
    public static final String USER_TYPE = "3";
    public static final String DEL_FLAG = "0";
    private static final String DEL_FLAG_DELETE = "1";
    private StringBuffer idList = null;
    private List<String> list = new ArrayList<String>();
    private static final String code = "10000";
    
	@Autowired
	private CourseService courseService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private ChoiceCourseService choiceCourseService;
	
	@ModelAttribute
	public Course get(@RequestParam(required=false) String id) {
		Course entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseService.get(id);
		}
		if (entity == null){
			entity = new Course();
		}
		return entity;
	}
	
	@RequiresPermissions("experiment:course:view")
	@RequestMapping(value = {"list", ""})
	public String list(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Course> courseList = courseService.listAllCourse();
		for (Course returnCourses : courseList) {
			String experiments = courseService.listExperimentByCourseId(returnCourses.getId());
			returnCourses.setExperiments(experiments);
		}
		List<Teacher> teacherList = courseService.listTeacherName(USER_TYPE);
		List<Course> courseNameList = courseService.listCourseName();                    
		model.addAttribute("courseList", courseList);
		model.addAttribute("teacherList", teacherList);
		model.addAttribute("courseNameList", courseNameList);
		
		return "modules/experiment/courseList";
	}

	@RequiresPermissions("experiment:course:view")
	@RequestMapping(value = "form")
	public String form(Course course, Model model) {
		List<Teacher> teacherList = courseService.listTeacherName(USER_TYPE);
		if(StringUtils.isNotBlank(course.getId())) {
			String courseParentName = courseService.getCourseParentNameByid(course.getId());
			course.getParent().setCourseName(courseParentName);
		}
		model.addAttribute("teacherList", teacherList);
		model.addAttribute("course", course);
		return "modules/experiment/courseForm";
	}

	@RequiresPermissions("experiment:course:edit")
	@RequestMapping(value = "save")
	public String save(Course course, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, course)){
			return form(course, model);
		}
		if(StringUtils.isNotBlank(course.getParent().getId()) && !course.getParent().getId().equals(",")) {
			String[] split = course.getParent().getId().split(",");
			course.getParent().setId(split[0]);
		}else {
			course.getParent().setId("0");
		}
		// 自动获取排序号
		if (!course.getParent().getId().equals("0")){
			int size = 0;
			List<Course> list = courseService.listAllCourse();
			for (int i = 0; i < list.size(); i++){
				Course e = list.get(i);
				if (e.getTeacherId() !=null && e.getTeacherId().equals(course.getParent().getId())){
					size++;
				}
			}
			String code = courseService.getParentCourseById(course.getParent().getId());
			course.setCode(code + StringUtils.leftPad(String.valueOf(size > 0 ? size+1 : 1), 3, "0"));
		} else {
			course.setCode(code);
		}
		String name = courseService.getTeacherNameById(course.getTeacherId());
		course.setTeacherName(name);
		courseService.save(course);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/course/?repage";
	}
	
	@RequiresPermissions("experiment:course:edit")
	@RequestMapping(value = "delete")
	public String delete(Course course, RedirectAttributes redirectAttributes) {
		idList = new StringBuffer();
		getAllSonCourse(course.getId());
		idList.append("\""+course.getId()+"\"");
		courseService.deleteAboutSon(idList,DEL_FLAG_DELETE);
		//courseService.delete(course);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/course/?repage";
	}
	
	public StringBuffer getAllSonCourse(String id){
		if (idList == null) {
			idList = new StringBuffer();
		}
		if (org.springframework.util.StringUtils.isEmpty(courseService)) {
			courseService = new CourseService();
		}
		List<Course> courseLit = courseService.listSonCoursesByCourseId(id);
		
		if (courseLit != null && !courseLit.isEmpty()) {
			for (Course sonCourse : courseLit) {
				idList.append("\""+sonCourse.getId()+"\",");
				if (StringUtils.isNotBlank(sonCourse.getId())) {
				getAllSonCourse(sonCourse.getId());
				}
			}
		}
		return idList;
	}
    
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(HttpServletResponse response,String officeId) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Course> courseList;
		if (StringUtils.isBlank(officeId)) {
			courseList = courseService.listCourseTreeData(DEL_FLAG);
		}else {
			courseList = courseService.listAlreadyCourseByOfficeId(DEL_FLAG,officeId);
		}
		Course e;
		if (!courseList.isEmpty() && courseList != null) {
			for (int i = 0; i<courseList.size(); i++){
				e = courseList.get(i);
			    Map<String, Object> map = Maps.newHashMap();
			    map.put("id", e.getId());
			    map.put("pId", e.getTeacherId());
			    map.put("name", e.getCourseName());
			    mapList.add(map);
				}
		}
		return mapList;
	}
	
	@RequestMapping(value = "choiseExperiment")
	public String choiseExperiment(HttpServletResponse response,Model model,HttpServletRequest request) {
		List<ExperimentInfo> experimentName = courseService.listExperimentName();
		String id = request.getParameter("courseId");
		List<String> experimentIds = courseService.listCourseAndExperiment(id);
		model.addAttribute("experimentName",experimentName);
		model.addAttribute("id",id);
		model.addAttribute("experimentIds",experimentIds);
		return "modules/experiment/selectExperiment";
	}
	
	
	@RequestMapping(value = "getChoiseExperimentJson")
	@ResponseBody
	public String getChoiseExperimentJson(HttpServletResponse response,Model model) {
		List<ExperimentInfo> experimentName = courseService.listExperimentName();
		Integer experimentCount = courseService.countExperiment();
		PageBean pageBean = new PageBean();
		pageBean.setRows(experimentName);
		pageBean.setTotal(experimentCount);
		Gson gson = new Gson();
		String json = gson.toJson(pageBean);
		return json;
	}
	
	@RequestMapping(value = "setCourseAndExperiment")
	@ResponseBody
	public void setCourseAndExperiment(HttpServletResponse response,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String[] experimentIds = request.getParameterValues("ids");
		String courseId = request.getParameter("id");
		idList = new StringBuffer();
		if (experimentIds != null  && experimentIds.length > 0) {
		        List<String> asList = Arrays.asList(experimentIds);
		        List<String> experimentList = courseService.listExperimentByCourse(courseId);
            	if (!experimentList.isEmpty()) {
            		experimentList.removeAll(asList);
           		    for (int i = 0;i < experimentList.size();i++) {
           		    	
           			    if (i < experimentList.size()-1) {
           			    	idList.append("\""+experimentList.get(i)+"\",");
           			     }else {
           		            idList.append("\""+experimentList.get(i)+"\"");	
           			     }
				    }
           		    if (idList != null && idList.length()>0) {
            	          courseService.deleteCourseExperimentByExperimentId(idList);
           		     }
            	}
            	if (!asList.isEmpty() && asList != null) {
            		List<String> experimens = courseService.listExperimentByCourse(courseId);
            		ArrayList<String> integers = new ArrayList<String>(asList);
            		integers.removeAll(experimens);
                 	for (String experimentId : integers) {
                 		courseService.insertExperimentAndCourseById(experimentId,courseId);
     			    }
	             }
		  }else { 
			  courseService.cleanCourseExperimentByCourseId(courseId);
		  }
		  addMessage(redirectAttributes, "关联成功");
	}
	
	@RequestMapping(value = "ajaxCourseExperiment")
	@ResponseBody
	public String ajaxCourseExperiment(HttpServletResponse response,Model model,HttpServletRequest request) {
		String courseId = request.getParameter("courseId");
		List<String> experimentIds = courseService.listExperimentByCourse(courseId);
		Gson gson = new Gson();
		String json = gson.toJson(experimentIds); 
		return json;
	}
	
	@RequestMapping(value = "userToRole")
	public String userToRole(HttpServletResponse response,Model model,HttpServletRequest request) {
		String id = UserUtils.getUser().getOffice().getId();
		String officeId = request.getParameter("officeId");
		List<Map<String,Object>> treeData = treeData(response,null);
		List<Map<String,Object>> alreadyTreeData = treeData(response,officeId);
		String type=choiceCourseService.getTypeByOfficeId(id);
		List<Map<String,Object>> studentData=null;
		if(type.equals("8")) {
			studentData = treeData(response,UserUtils.getUser().getId());
		}
		model.addAttribute("officeList", officeService.findAll());
		model.addAttribute("treeData", treeData);
		model.addAttribute("alreadyTreeData", alreadyTreeData);
		model.addAttribute("type",type);
		model.addAttribute("studentData",studentData);
		return "modules/experiment/selectExperimentCourse";
	}
}