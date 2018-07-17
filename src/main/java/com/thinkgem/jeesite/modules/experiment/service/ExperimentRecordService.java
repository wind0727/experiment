/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.service;

import java.util.ArrayList;

import java.util.List;


import org.apache.commons.lang.StringUtils;


import org.apache.ibatis.annotations.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentArrange;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentRecord;
import com.thinkgem.jeesite.modules.experiment.utils.StringUtil;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentAppointmentVO;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentFinishVO;
import com.thinkgem.jeesite.modules.experiment.vo.StudentInfoVO;
import com.thinkgem.jeesite.modules.experiment.dao.ExperimentInfoDao;
import com.thinkgem.jeesite.modules.experiment.dao.ExperimentRecordDao;

/**
 * 单表生成Service
 * @author wind
 * @version 2018-02-02
 */
@Service
@Transactional(readOnly = true)
public class ExperimentRecordService extends CrudService<ExperimentRecordDao, ExperimentRecord> {
     
	@Autowired
	private ExperimentRecordDao experimentRecordDao;
	
	
	public ExperimentRecord get(String id) {
		return super.get(id);
	}
	
	public List<ExperimentRecord> findList(ExperimentRecord experimentRecord) {
		return super.findList(experimentRecord);
	}
	
	public Page<ExperimentRecord> findPage(Page<ExperimentRecord> page, ExperimentRecord experimentRecord) {
		Page<ExperimentRecord> findPage = super.findPage(page, experimentRecord);
		List<ExperimentRecord> list = findPage.getList();
		for (ExperimentRecord record : list) {
			if(record.getExperimentProblem()!=null) {
				record.setExperimentProblem(StringUtil.getSubString(record.getExperimentProblem()));
			}
			if(record.getExperimentCompletion()!=null) {
				record.setExperimentCompletion(StringUtil.getSubString(record.getExperimentCompletion()));
			}
			
		}
		
		return findPage;
	}
	
	@Transactional(readOnly = false)
	public void save(ExperimentRecord experimentRecord) {
		super.save(experimentRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(ExperimentRecord experimentRecord) {
		super.delete(experimentRecord);
	}

	public String getTeacherNameById(String teacherId) {
	  return experimentRecordDao.getTeacherNameById(teacherId);
	}
	
	public ExperimentRecord getExperimentInfoIdNameById(String experimentInfoId) {	
	 return experimentRecordDao.getExperimentInfoIdNameById(experimentInfoId);
	}                  

	public List<ExperimentRecord> getStuedntHistoryExperiemnt(Page<ExperimentRecord> page, String officeId,
		ExperimentRecord experimentRecord) {
		List<ExperimentRecord> list = new ArrayList<ExperimentRecord>();
		experimentRecord.setPage(page);
		experimentRecord.setOfficeId(officeId);
		List<ExperimentRecord> experiemntList = experimentRecordDao.getStuedntHistoryExperiemntList(experimentRecord);
		for (ExperimentRecord returnExperimentRecord : experiemntList) {
			
			if (returnExperimentRecord.getExperimentProblem() != null) {
				returnExperimentRecord.setExperimentProblem(StringUtil.getSubString(returnExperimentRecord.getExperimentProblem()));
			}if (returnExperimentRecord.getExperimentComment() != null) {
				returnExperimentRecord.setExperimentComment(StringUtil.getSubString(returnExperimentRecord.getExperimentComment()));
			}
			
			list.add(returnExperimentRecord);
		}
		return list;
	}
	
	public ExperimentRecord getMinuteStuedntHistoryExperiemnt(String officeId,
		ExperimentRecord experimentRecord,String id) {
		ExperimentRecord experiemnt = experimentRecordDao.getMinuteStuedntHistoryExperiemntList(officeId,experimentRecord,id);
			/*if(experiemnt.getExperimentComment() != null) {
				experiemnt.setExperimentComment(StringUtil.getSubString(experiemnt.getExperimentComment()));
			}
			if(experiemnt.getExperimentCompletion() != null) {
				experiemnt.setExperimentCompletion(StringUtil.getSubString(experiemnt.getExperimentCompletion()));
			}
			if(experiemnt.getExperimentProblem() != null) {
				experiemnt.setExperimentProblem(StringUtil.getSubString(experiemnt.getExperimentProblem()));
			}*/
		return experiemnt;
	}

	public List<ExperimentInfo> getHistoryExperiemntName(String officeId) {
		return experimentRecordDao.getHistoryExperiemntName(officeId);
	}
    
	public List<ExperimentFinishVO> getExperimentFinishsByGrade(String gradeId, int userType) {
		return experimentRecordDao.getExperimentFinishsByGrade(gradeId, userType);
	}
	
	public List<ExperimentFinishVO> getExperimentScoreByGrade(String gradeId, int userType) {
		return experimentRecordDao.getExperimentScoreByGrade(gradeId, userType);
	}
	
	public List<ExperimentFinishVO> getExperimentFinishsByClass(String classId, int userType) {
		return experimentRecordDao.getExperimentFinishsByClass(classId, userType);
	}

	
	public double getExperimentAvgScoreByGrade(String gradeId, int userType) {
		return experimentRecordDao.getExperimentAvgScoreByGrade(gradeId, userType);
	}

	public ExperimentRecord getExperimentRecord(String id) {
		
		return experimentRecordDao.getExperimentRecord(id);
	}

	public String getStudentName(String studentId) {
		
		return experimentRecordDao.getStudentName(studentId);
	}

	public List<ExperimentRecord> getStudentExperimentRecordByStudent(String id) {
		
		return experimentRecordDao.getStudentExperimentRecordByStudent(id);
	}

	public List<ExperimentInfo> getExperimentNameByStudent(String userId, String status ) {
		
		return experimentRecordDao.getExperimentNameByStudent(userId,status);
	}

	public List<ExperimentArrange> getTeacherNameByExperimentAppointment(
			String userId, String status) {
		
		return experimentRecordDao.getTeacherNameByExperimentAppointment(userId,status);
	}

	public List<ExperimentAppointmentVO> getStudentExperimentRecordByStudentId(ExperimentAppointmentVO experimentAppointmentVO) {
		
		
		return experimentRecordDao.getStudentExperimentRecordByStudentId(experimentAppointmentVO);
	}

	public ExperimentRecord getExperimentResultById(String id) {
		return experimentRecordDao.getExperimentResultById(id);
	}

	public String getUserTypeById(String id) {
		
		return experimentRecordDao.getUserTypeById(id);
	}

}