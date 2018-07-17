/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.experiment.entity.Course;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.sys.entity.Teacher;
import com.thinkgem.jeesite.modules.experiment.dao.CourseDao;

/**
 * 单表生成Service
 * @author wind
 * @version 2018-03-27
 */
@Service
@Transactional(readOnly = true)
public class CourseService extends CrudService<CourseDao, Course> {

	@Autowired
	private CourseDao courseDao;
	public Course get(String id) {
		return super.get(id);
	}
	
	public List<Course> findList(Course course) {
		return super.findList(course);
	}
	
	public Page<Course> findPage(Page<Course> page, Course course) {
		return super.findPage(page, course);
	}
	
	@Transactional(readOnly = false)
	public void save(Course course) {
		super.save(course);
	}
	
	@Transactional(readOnly = false)
	public void delete(Course course) {
		super.delete(course);
	}

	public List<Teacher> listTeacherName(String USER_TYPE) {
		return courseDao.listTeacherName(USER_TYPE);
	}

	public List<Course> listCourseTreeData(String DEL_FLAG) {
		return courseDao.listCourseTreeData(DEL_FLAG);
	}

	public String getTeacherNameById(String teacherId) {
		return courseDao.getTeacherNameById(teacherId);
	}

	public String getCourseParentNameByid(String id) {
		return courseDao.getCourseParentNameByid(id);
	}

	public List<Course> listSonCoursesByCourseId(String id) {
		return courseDao.listSonCoursesByCourseId(id);
	}
	@Transactional(readOnly = false)
	public void deleteAboutSon(StringBuffer idList,String DEL_FLAG_DELETE) {
		courseDao.deleteAboutSon(idList,DEL_FLAG_DELETE);		
	}

	public List<Course> listCourseName() {
		return courseDao.listCourseName();
	}

	public List<ExperimentInfo> listExperimentName() {
		return courseDao.listExperimentName();
	}

	public Integer countExperiment() {
		return courseDao.countExperiment();
	}
	
	@Transactional(readOnly = false)
	public void updateExperimentAndCourseById(String experimentId, String courseId) {
		
		courseDao.updateExperimentAndCourseById(experimentId,courseId);
	}
    
	@Transactional(readOnly = false)
	public void insertExperimentAndCourseById(String experimentId, String courseId) {
		courseDao.insertExperimentAndCourseById(experimentId,courseId);
	}

	public List<String> listCourseAndExperiment(String id) {
		
		return courseDao.listCourseAndExperiment(id);
	}
    
	@Transactional(readOnly = false)
	public void cleanCourseExperimentByCourseId(String courseId) {
		 courseDao.cleanCourseExperimentByCourseId(courseId);
	}
    
	@Transactional(readOnly = false)
	public void deleteCourseExperimentByExperimentId(StringBuffer experimentList) {
		courseDao.deleteCourseExperimentByExperimentId(experimentList);
		
	}

	public List<Course> listAlreadyCourseByOfficeId(String delFlag, String officeId) {
		return courseDao.listAlreadyCourseByOfficeId(delFlag,officeId);
	}

	public List<String> listExperimentByCourse(String courseId) {
		return courseDao.listExperimentByCourse(courseId);
	}

	public List<Course> listAllCourse() {
		return courseDao.listAllCourse();
	}

	public String getParentCourseById(String id) {
		return courseDao.getParentCourseById(id);
	}

	public String listExperimentByCourseId(String id) {
		List<String> experimentList = courseDao.listExperimentByCourseId(id);
	    String experiments = org.apache.commons.lang.StringUtils.join(experimentList.toArray(), ",");
		return experiments;
	}

}