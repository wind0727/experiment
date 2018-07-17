/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.experiment.entity.ChoiceCourse;
import com.thinkgem.jeesite.modules.experiment.entity.Course;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.experiment.dao.ChoiceCourseDao;

/**
 * 单标生成Service
 * @author lzp
 * @version 2018-03-27
 */
@Service
@Transactional(readOnly = true)
public class ChoiceCourseService extends CrudService<ChoiceCourseDao, ChoiceCourse> {
	@Autowired
    private ChoiceCourseDao choiceCourseDao;
	
	public ChoiceCourse get(String id) {
		return super.get(id);
	}
	
	public Page<ChoiceCourse> findPage(Page<ChoiceCourse> page, ChoiceCourse choiceCourse) {
		return super.findPage(page, choiceCourse);
	}
	
	@Transactional(readOnly = false)
	public void save(ChoiceCourse choiceCourse) {
		super.save(choiceCourse);
	}
	
	@Transactional(readOnly = false)
	public void delete(ChoiceCourse choiceCourse) {
		super.delete(choiceCourse);
	}

	public List<Course> listAdminExperimentName() {
		return choiceCourseDao.listAdminExperimentName();
	}

	public List<ChoiceCourse> listAdminCourseByUser(String officeId) {
		return choiceCourseDao.listAdminCourseByUser(officeId);
	}

	public String getAdminCourseNameById(String id) {
		return choiceCourseDao.getAdminCourseNameById(id);
	}

	public List<Office> listAllOffice() {
		return choiceCourseDao.listAllOffice();
	}
    
	@Transactional(readOnly = false)
	public List<Course> getCourseByOffice(String officeId) {
		return choiceCourseDao.getCourseByOffice(officeId);
	}
    
	@Transactional(readOnly = false)
	public void deleteChoiceCourseByGrod(String officeId) {
		choiceCourseDao.deleteChoiceCourseByGrod(officeId);
	}

	public void insertGordCourse(String id, String officeId) {
		choiceCourseDao.insertGordCourse(id,officeId);	
	}

	public List<Course> listCourseByOfficeId(String id) {
		return choiceCourseDao.listCourseByOfficeId(id);
	}

	public String getTypeByOfficeId(String officeId) {
		return choiceCourseDao.getTypeByOfficeId(officeId);
	}

	public List<Office> listOfficeById(String officeId) {
		return choiceCourseDao.listOfficeById(officeId);
	}

	public List<Course> listStudentCourseByOfficeIdAndUserId(String officeId, String userId) {
		
		return choiceCourseDao.listStudentCourseByOfficeIdAndUserId(officeId,userId);
	}
	
}