/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.experiment.entity.Course;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentArrange;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.vo.TeacherVO;

/**
 * 单表生成DAO接口
 * @author ThinkGem
 * @version 2018-01-23
 */
@MyBatisDao
public interface ExperimentArrangeDao extends CrudDao<ExperimentArrange> {

	String getExperimentNameById(String infoId);

	String getTeacherNameById(String teacherId);

	void updateExperimentAppointmentCount(String experimentArrangeId);

	Integer getPeopleNumByArrangeId(String experimentArrangeId);

	ExperimentArrange getExperimentArrangeById(String id);

	void updateExperimentArrange(ExperimentArrange experimentArrange);

	List<ExperimentArrange> findExperimentArrangePageByOffice( ExperimentArrange experimentArrange);

	List<Course> listCourseByOfficeId(String officeId);

	List<ExperimentInfo> listExperimentByCourseId(String id);

	TeacherVO getTeacherByCourse(String courseId);
	
}