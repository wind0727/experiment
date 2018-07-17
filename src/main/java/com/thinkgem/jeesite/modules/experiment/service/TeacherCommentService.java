package com.thinkgem.jeesite.modules.experiment.service;

import java.util.Date;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.experiment.dao.ExperimentAppointmentDao;
import com.thinkgem.jeesite.modules.experiment.dao.ExperimentRecordDao;
import com.thinkgem.jeesite.modules.experiment.dao.TeacherCommentDao;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentAppointment;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentRecord;
import com.thinkgem.jeesite.modules.experiment.utils.StringUtil;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentAppointmentVO;
import com.thinkgem.jeesite.modules.experiment.vo.StudentInfoVO;
@Service
@Transactional(readOnly = true)
public class TeacherCommentService extends CrudService<TeacherCommentDao, ExperimentRecord>{
	
    @Autowired
	private  TeacherCommentDao teacherCommentDao;
    @Autowired
	private ExperimentAppointmentDao experimentAppointmentDao;
    
    public ExperimentRecord get(String id) {
		return super.get(id);
	}
	
	public List<ExperimentRecord> findList(ExperimentRecord experimentRecord) {
		return super.findList(experimentRecord);
	}
	
	public Page<ExperimentRecord> findPage(Page<ExperimentRecord> page, ExperimentRecord experimentRecord) {
		return super.findPage(page, experimentRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(ExperimentRecord experimentRecord) {
		super.save(experimentRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(ExperimentRecord experimentRecord) {
		super.delete(experimentRecord);
	}

	public List<ExperimentRecord> getStuedntInfoVOByCondition(Page<ExperimentRecord> page,ExperimentRecord experimentRecord) {
		String experimentAppointmentId = experimentRecord.getExperimentAppointmentId();
		experimentRecord.setExperimentAppointmentId(experimentAppointmentId);
		experimentRecord.setPage(page);
		List<ExperimentRecord> list = teacherCommentDao.getStuedntInfoVOByCondition(experimentRecord);
		for (ExperimentRecord experiment : list) {
			if (experiment.getExperimentComment() != null) {
				experiment.setExperimentComment(StringUtil.getSubString(experiment.getExperimentComment()));
		    } 
			if (experiment.getExperimentCompletion() != null) {
				experiment.setExperimentCompletion(StringUtil.getSubString(experiment.getExperimentCompletion()));
			} 
		    if (StringUtils.isNotBlank(experiment.getStudentId())) {
		    	experiment.setStudentName(teacherCommentDao.getStudentNameById(experiment.getStudentId()));
			}
			
		}	
		return list;
	}

	public List<ExperimentRecord> getTeacherCommentByCondition(String officeId) {
		return teacherCommentDao.getTeacherCommentByCondition(officeId);
	}
	
	public ExperimentAppointmentVO findExperimentAndTeacherInfoByCondition(@Param("experimentAppointmentId") String experimentAppointmentId) {
		return teacherCommentDao.findExperimentAndTeacherInfoByCondition(experimentAppointmentId);
	}

	public String getExperimentInfoIdByappointmentId(String experimentAppointmentId) {
		return teacherCommentDao.getExperimentInfoIdByappointmentId(experimentAppointmentId);
	}
	
	@Transactional(readOnly = false)
	public void saveHistory(ExperimentRecord experimentRecord) {
		 teacherCommentDao.saveHistory(experimentRecord);
	}
	
	@Transactional(readOnly = false)
	public void updateHistory(ExperimentRecord experimentRecord) {
		teacherCommentDao.updateHistory(experimentRecord);
	}
	
	@Transactional(readOnly = false)
	public void finshSave(ExperimentRecord experimentRecord) {
		teacherCommentDao.setAppointmentIdFinsh(experimentRecord);
	 // 获取同一个实验安排完成情况
			List<String> list = teacherCommentDao.finshSave(experimentRecord);
        	ExperimentRecord experimentRecordHis = teacherCommentDao.getExperimetRecordById(experimentRecord);
        	saveHistory(experimentRecordHis);
        	ExperimentAppointment experimentAppointment = teacherCommentDao.getExperimentAppointmentByRecordId(experimentRecord);
        	teacherCommentDao.saveAppoimentHistory(experimentAppointment);
        	super.delete(experimentRecord);
        	experimentAppointmentDao.delete(experimentAppointment);
	}

	public List<ExperimentInfo> getExperimentInfoName(String teacherId) {
		return  teacherCommentDao.getExperimentInfoName(teacherId);
	}
	
	@Transactional(readOnly = false)
	public void updateScore(ExperimentRecord experimentRecord) {
		 teacherCommentDao.updateScore( experimentRecord);
	}

	public String getTeacherNameById(String teacherId) {
		return teacherCommentDao.getTeacherNameById(teacherId);
	}

	public String getExperimentInfoIdNameById(String experimentInfoId) {
		return teacherCommentDao.getExperimentInfoIdNameById(experimentInfoId);
	}
}
