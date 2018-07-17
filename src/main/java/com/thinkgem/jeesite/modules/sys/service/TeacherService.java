/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.Student;
import com.thinkgem.jeesite.modules.sys.entity.Teacher;
import com.thinkgem.jeesite.modules.experiment.vo.TeacherVO;
import com.thinkgem.jeesite.modules.sys.dao.TeacherDao;

/**
 * 单表生成Service
 * @author wind
 * @version 2018-01-31
 */
@Service
@Transactional(readOnly = true)
public class TeacherService extends CrudService<TeacherDao, Teacher> {
	
	@Autowired
	private TeacherDao teacherDao;

	public Teacher get(String id) {
		return super.get(id);
	}
	
	public List<Teacher> findList(Teacher teacher) {
		return super.findList(teacher);
	}
	
	public Page<Teacher> findPage(Page<Teacher> page, Teacher teacher) {
		return super.findPage(page, teacher);
	}
	
	@Transactional(readOnly = false)
	public void save(Teacher teacher) {
		super.save(teacher);
	}
	
	@Transactional(readOnly = false)
	public void delete(Teacher teacher) {
		super.delete(teacher);
	}
	
	public Teacher findByUser(String userId) {
		return teacherDao.findByUser(userId);
	}
	
	public List<TeacherVO> getTeacherVOByOffice(String officeId) {
		return teacherDao.getTeacherVOByOffice(officeId);
	}

	
}