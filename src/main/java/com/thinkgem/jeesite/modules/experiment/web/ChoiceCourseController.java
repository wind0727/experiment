/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.experiment.entity.ChoiceCourse;
import com.thinkgem.jeesite.modules.experiment.entity.Course;
import com.thinkgem.jeesite.modules.experiment.service.ChoiceCourseService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 单标生成Controller
 * @author lzp
 * @version 2018-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/experiment/choiceCourse")
public class ChoiceCourseController extends BaseController {
    private static final String CHOICE_TYPE_GRADE = "1";
    private static final String CHOICE_TYPE_STUDENT = "2";
    private static final String OFFICE_TYPE_GRADE = "7";
    private static final String OFFICE_TYPE_STUDENT = "8";
    private static final String OFFICE_TYPE_COURSE = "10";
    
	@Autowired
	private ChoiceCourseService choiceCourseService;
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute
	public ChoiceCourse get(@RequestParam(required=false) String id) {
		ChoiceCourse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = choiceCourseService.get(id);
		}
		if (entity == null){
			entity = new ChoiceCourse();
		}
		return entity;
	}
	//TODO : 2018年改变课程拼接方法
	@RequiresPermissions("experiment:choiceCourse:view")
	@RequestMapping(value = {"list", ""})
	public String list(ChoiceCourse choiceCourse, HttpServletRequest request, HttpServletResponse response, Model model,Office office) {
		List<Office> courseOfficeList = new ArrayList<Office>();
		CourseController course = new CourseController();
		String officeId = UserUtils.getUser().getOffice().getId();
		String type = choiceCourseService.getTypeByOfficeId(officeId);
		Page<ChoiceCourse> page = choiceCourseService.findPage(new Page<ChoiceCourse>(request, response), choiceCourse); 
     	List<ChoiceCourse> courseList = choiceCourseService.listAdminCourseByUser(officeId);
     	List<Office> officeList = new LinkedList<Office>();
     	if(type.equals(CHOICE_TYPE_GRADE)) {
     		officeList = choiceCourseService.listAllOffice();
     	}else if (type.equals(OFFICE_TYPE_GRADE)){
     		officeList = choiceCourseService.listOfficeById(officeId);
     	}else {
     		officeList = choiceCourseService.listOfficeById(UserUtils.getUser().getOffice().getParentId());
     	}
     	courseOfficeList.addAll(officeList);
    	int i = 1;
    	for (Office returnOffice : courseOfficeList) {
     		if(returnOffice.getType().equals(OFFICE_TYPE_GRADE)) {
     			List<Course> courseGrodList;
     			if(type.equals(OFFICE_TYPE_STUDENT)) {
     				courseGrodList = choiceCourseService.listStudentCourseByOfficeIdAndUserId(returnOffice.getId(),UserUtils.getUser().getId());
     			}else{
     				courseGrodList = choiceCourseService.listCourseByOfficeId(returnOffice.getId());
     			}
	     		if (!courseGrodList.isEmpty()) {
	     			Office courseOffice = null;
	     			for (Course returnCourse : courseGrodList) {
	     				courseOffice = new Office();
	     				courseOffice.setParentIds(returnOffice.getId());
	     				courseOffice.setType(OFFICE_TYPE_COURSE);
	     				courseOffice.setName(returnCourse.getCourseName());
	     				courseOffice.setId(returnOffice.getId()+returnCourse.getId());
	     				courseOffice.setIntroduce(returnCourse.getIntroduce());
	     				courseOffice.setTeacherName(returnCourse.getTeacherName());
	     				officeList.add(i,courseOffice);
	     				courseOffice = null; 
	     				i++;
					}
	     		}
     		}
     		i++;
		}
    	officeList.get(0).setParentIds("0");
     	page.setList(courseList);
    	model.addAttribute("page", page);

    	model.addAttribute("officeList", officeList);
		model.addAttribute("type", type);
		return "modules/experiment/choiceCourseList";
	}

	@RequiresPermissions("experiment:choiceCourse:view")
	@RequestMapping(value = "form")
	public String form(ChoiceCourse choiceCourse, Model model) {
		model.addAttribute("choiceCourse", choiceCourse);
		return "modules/experiment/choiceCourseForm";
	}

	@RequiresPermissions("experiment:choiceCourse:edit")
	@RequestMapping(value = "save")
	public String save(ChoiceCourse choiceCourse, Model model, RedirectAttributes redirectAttributes) {
		String officeId = UserUtils.getUser().getOffice().getId();
		CourseController course = new CourseController();
		String id = choiceCourse.getId();
		id = id.substring(1, id.length());
		choiceCourse.setCourseId(id);
		choiceCourse.setChoiceType(CHOICE_TYPE_GRADE);
		choiceCourse.setGradeStudentId(officeId);
		choiceCourse.setId(null);
	    choiceCourse.setCourseName(choiceCourseService.getAdminCourseNameById(id));
	   
		if (!beanValidator(model, choiceCourse)){
			return form(choiceCourse, model);
		}
		
		choiceCourseService.save(choiceCourse);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/adminChoiceCourse/?repage";
	}
	
	@RequiresPermissions("experiment:choiceCourse:edit")
	@RequestMapping(value = "delete")
	public String delete(ChoiceCourse choiceCourse, RedirectAttributes redirectAttributes) {
		choiceCourseService.delete(choiceCourse);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/experiment/choiceCourse/?repage";
	}
	
	@RequestMapping(value = "getCourseByOffice")
	public List<Map<String, Object>> getCourseByOffice(HttpServletResponse response,HttpServletRequest request) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		String officeId = request.getParameter("officeId");
		List<Course> courseList = choiceCourseService.getCourseByOffice(officeId);
		for (Course e : courseList) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", 0);
			map.put("name", e.getCourseName());
			mapList.add(map);			
		}
		return mapList;
	}
	
	@RequestMapping(value = "insertGordCourse")
	public String insertGordCourse(HttpServletRequest request,ChoiceCourse choiceCourse) {
		String ids = request.getParameter("ids");
		String officeId = request.getParameter("officeId");
		String arrangeIds = request.getParameter("arrangeIds");
		String type = request.getParameter("type");
		List<String> idList = Arrays.asList(ids.split(",")); 
		ArrayList<String> integers = new ArrayList<String>(idList);
		if(type.equals("8")) {
			List<String> arrangeIdList = Arrays.asList(arrangeIds.split(","));
			integers.removeAll(arrangeIdList);
			choiceCourseService.deleteChoiceCourseByGrod(UserUtils.getUser().getId());
			choiceCourse.setGradeStudentId(UserUtils.getUser().getId());
			choiceCourse.setChoiceType(CHOICE_TYPE_STUDENT);
		}else {
			choiceCourseService.deleteChoiceCourseByGrod(officeId);
			choiceCourse.setGradeStudentId(officeId);
			choiceCourse.setChoiceType(CHOICE_TYPE_GRADE);
		}
		
		for (String id : integers) {
			choiceCourse.setCourseName(choiceCourseService.getAdminCourseNameById(id));
			choiceCourse.setCourseId(id);
			choiceCourseService.save(choiceCourse);
			choiceCourse.setId(null);
		}
		return "redirect:"+Global.getAdminPath()+"/experiment/choiceCourse/?repage";
	}
	
	@RequestMapping(value = "cleanGordCourse")
	public String cleanGordCourse(HttpServletRequest request,ChoiceCourse choiceCourse) {
		String officeId = request.getParameter("officeId");
		choiceCourseService.deleteChoiceCourseByGrod(officeId);
		return "redirect:"+Global.getAdminPath()+"/experiment/choiceCourse/?repage";
	}
}