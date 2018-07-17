/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentAppointment;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.utils.DateUtils;
import com.thinkgem.jeesite.modules.experiment.utils.StringUtil;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentVO;
import com.thinkgem.jeesite.modules.cms.dao.CategoryDao;
import com.thinkgem.jeesite.modules.experiment.dao.ExperimentInfoDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-01-22
 */
@Service
@Transactional(readOnly = true)
public class ExperimentInfoService extends CrudService<ExperimentInfoDao, ExperimentInfo> {
	
	@Autowired
	private ExperimentInfoDao experimentInfoDao;

	public ExperimentInfo get(String id) {
		return super.get(id);
	}
	
	public List<ExperimentInfo> findList(ExperimentInfo experimentInfo) {
		return super.findList(experimentInfo);
	}
	
	public Page<ExperimentInfo> findPage(Page<ExperimentInfo> page, ExperimentInfo experimentInfo) {
		return super.findPage(page, experimentInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(ExperimentInfo experimentInfo) {
		super.save(experimentInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(ExperimentInfo experimentInfo) {
		super.delete(experimentInfo);
	}
	
	public List<ExperimentVO> getExperimentVOByOffice(Page<ExperimentVO> page, String officeId) {
		return experimentInfoDao.getExperimentVOByOffice(page, officeId);
	}
	public List<ExperimentInfo> getExperimentInfoByOffice(String officeId) {
		return experimentInfoDao.getExperimentInfoByOffice(officeId);
	}

	public List<ExperimentVO> getExperimentInfosByAdmin(String officeId) {
		
		return experimentInfoDao.getExperimentInfosByAdmin(officeId);
	}

	public List<ExperimentVO> getExperimentInfosByStudent(String officeId) {
		
		return experimentInfoDao.getExperimentInfosByStudent(officeId);
	}


	public List<ExperimentVO> getExperimentInfosByTeacher(String officeId) {
		
		return experimentInfoDao.getExperimentInfosByTeacher(officeId);
	}

	public List<ExperimentVO> getExperimentVOByGradeId(String officeId) {
		return experimentInfoDao.getExperimentVOByGradeId(officeId);
	}

	public List<ExperimentAppointment> getExperimentVOByGrad(ExperimentAppointment experimentAppointment) {
		Date experimentStarttime = experimentAppointment.getExperimentStarttime();
		Date experimentEndtime = experimentAppointment.getExperimentEndtime();
		if (experimentStarttime == null && experimentEndtime == null) {
			experimentAppointment.setExperimentStarttime(DateUtils.getFirstOrEndDayOfMonth(true));
			experimentAppointment.setExperimentEndtime(DateUtils.getFirstOrEndDayOfMonth(false));
		}
		List<ExperimentAppointment> list = experimentInfoDao.getExperimentVOByGrad(experimentAppointment);
		return list;
	}

	public int getExperimentCountByOffice(String officeId) {
		return experimentInfoDao.getExperimentCountByOffice(officeId);
	}
	
	public int getExperimentCountByGrade(String gradeId) {
		return experimentInfoDao.getExperimentCountByGrade(gradeId);
	}

	public List<ExperimentVO> getExperimentName(String officeId,String userId) {
		
		return experimentInfoDao.getExperimentName(officeId,userId);
	}

	public Page<ExperimentInfo> findPageByOffice(Page<ExperimentInfo> page, ExperimentInfo experimentInfo) {
		experimentInfo.setPage(page);
		List<ExperimentInfo> list = experimentInfoDao.findPageByOffice(experimentInfo);
		for (ExperimentInfo returnExperimentInfo : list) {
			if(returnExperimentInfo.getExperimentContent()!=null) {
			returnExperimentInfo.setExperimentContent(StringUtil.getSubString(returnExperimentInfo.getExperimentContent()));
			}
			if(returnExperimentInfo.getExperimentObjective()!=null){
			returnExperimentInfo.setExperimentObjective(StringUtil.getSubString(returnExperimentInfo.getExperimentObjective()));
			}
		}
		page.setList(list);
	//	page.setCount(list.size());
		return page;
	}

}