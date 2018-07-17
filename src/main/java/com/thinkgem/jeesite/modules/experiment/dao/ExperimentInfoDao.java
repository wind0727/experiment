/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentAppointment;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentVO;

/**
 * 单表生成DAO接口
 * @author ThinkGem
 * @version 2018-01-22
 */
@MyBatisDao
public interface ExperimentInfoDao extends CrudDao<ExperimentInfo> {
	
	public List<ExperimentVO> getExperimentVOByOffice(@Param("page") Page<ExperimentVO> page, @Param("officeId") String officeId);
	
	public List<ExperimentInfo> getExperimentInfoByOffice(String officeId);

	public List<ExperimentVO> getExperimentInfosByAdmin(String officeId);

	public List<ExperimentVO> getExperimentInfosByStudent(String officeId);

	public List<ExperimentVO> getExperimentInfosByTeacher(String officeId);

	public List<ExperimentVO> getExperimentVOByGradeId(@Param("officeId") String officeId);

	public List<ExperimentAppointment> getExperimentVOByGrad(ExperimentAppointment experimentAppointment);

	public int getExperimentCountByOffice(String officeId);
	
	public int getExperimentCountByGrade(String gradeId);

	public List<ExperimentVO> getExperimentName(@Param("officeId") String officeId,@Param("userId") String userId);

	public List<ExperimentInfo> findPageByOffice(ExperimentInfo experimentInfo);

	
}