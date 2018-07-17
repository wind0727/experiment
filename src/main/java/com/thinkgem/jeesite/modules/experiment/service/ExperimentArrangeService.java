/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.experiment.entity.Course;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentArrange;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.utils.DateUtils;
import com.thinkgem.jeesite.modules.experiment.vo.TeacherVO;
import com.thinkgem.jeesite.modules.experiment.dao.ExperimentAppointmentDao;
import com.thinkgem.jeesite.modules.experiment.dao.ExperimentArrangeDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-01-23
 */
@Service
@Transactional(readOnly = true)
public class ExperimentArrangeService extends CrudService<ExperimentArrangeDao, ExperimentArrange> {
	@Autowired
	private ExperimentArrangeDao experimentArrangeDao;
	public ExperimentArrange get(String id) {
		return super.get(id);
	}
	
	public List<ExperimentArrange> findList(ExperimentArrange experimentArrange) {
		return super.findList(experimentArrange);
	}
	
	public Page<ExperimentArrange> findPage(Page<ExperimentArrange> page, ExperimentArrange experimentArrange) {
		
		return super.findPage(page, experimentArrange);
	}
	
	@Transactional(readOnly = false)
	public void save(ExperimentArrange experimentArrange) {
		super.save(experimentArrange);
	}
	
	@Transactional(readOnly = false)
	public void delete(ExperimentArrange experimentArrange) {
		super.delete(experimentArrange);
	}


	public String getExperimentNameById(String infoId) {
		return experimentArrangeDao.getExperimentNameById(infoId);
	}

	public String getTeacherNameById(String teacherId) {
		return experimentArrangeDao.getTeacherNameById(teacherId);
	}
	@Transactional(readOnly = false)
	public void updateExperimentAppointmentCount(String experimentArrangeId) {
		
		experimentArrangeDao.updateExperimentAppointmentCount(experimentArrangeId);
	}

	public Integer getPeopleNumByArrangeId(String experimentArrangeId) {
		
		return experimentArrangeDao.getPeopleNumByArrangeId(experimentArrangeId);
	}

	public ExperimentArrange getExperimentArrangeById(String id) {
		return experimentArrangeDao.getExperimentArrangeById(id);
	}
	@Transactional(readOnly = false)
	public void updateExperimentArrange(ExperimentArrange experimentArrange) {
		experimentArrangeDao.updateExperimentArrange(experimentArrange);
	}

	public Page<ExperimentArrange> findExperimentArrangePageByOffice(Page<ExperimentArrange> page,
		ExperimentArrange experimentArrange, String officeId) {
		if (experimentArrange.getExperimentStarttime() == null && experimentArrange.getExperimentEndtime() == null) {
			Date firstDayOfMonth = DateUtils.getFirstOrEndDayOfMonth(true);
			Date lastDayOfMonth = DateUtils.getFirstOrEndDayOfMonth(false);
			experimentArrange.setExperimentStarttime(firstDayOfMonth);
			experimentArrange.setExperimentEndtime(lastDayOfMonth);
		   }
		   experimentArrange.setPage(page);
		   experimentArrange.setOfficeId(officeId);
		List<ExperimentArrange> experimentArrangeList = experimentArrangeDao.findExperimentArrangePageByOffice(experimentArrange);
		page.setList(experimentArrangeList);
		return page;
	}

	public List<Course> listCourseByOfficeId(String officeId) {
	
		return experimentArrangeDao.listCourseByOfficeId(officeId);
	}

	public List<ExperimentInfo> listExperimentByCourseId(String id) {
		
		return experimentArrangeDao.listExperimentByCourseId(id);
	}

	public TeacherVO getTeacherByCourse(String courseId) {
		
		return experimentArrangeDao.getTeacherByCourse(courseId);
	}

	
}