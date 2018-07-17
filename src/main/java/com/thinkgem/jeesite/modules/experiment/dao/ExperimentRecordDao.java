/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentArrange;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentRecord;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentAppointmentService;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentAppointmentVO;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentFinishVO;

/**
 * 单表生成DAO接口
 * @author wind
 * @version 2018-02-02
 */
@MyBatisDao
public interface ExperimentRecordDao extends CrudDao<ExperimentRecord> {

	public String getTeacherNameById(String teacherId);

	public ExperimentRecord getExperimentInfoIdNameById(String experimentInfoId);

	public List<ExperimentRecord> getStuedntHistoryExperiemntList(ExperimentRecord experimentRecord);

	public ExperimentRecord getMinuteStuedntHistoryExperiemntList(@Param("officeId") String officeId,
			@Param("experimentRecord") ExperimentRecord experimentRecord, @Param("id") String id);
	
	public List<String> getStuedntHistoryArrangeId(String officeId);

	public String getStuedntExperimentCount(@Param("officeId") String officeId,@Param("experimmentArrangeId") String experimmentArrangeId);

	public List<ExperimentInfo> getHistoryExperiemntName(String officeId);
	
	public List<ExperimentRecord> getStuedntHistoryExperiemnt (@Param("page") Page<ExperimentRecord> page,@Param("officeId") String officeId,
			@Param("experimentRecord") ExperimentRecord experimentRecord);
	
	public List<ExperimentFinishVO> getExperimentFinishsByGrade(@Param("gradeId")String gradeId, @Param("userType")int userType);
	
	public List<ExperimentFinishVO> getExperimentFinishsByClass(@Param("classId")String classId, @Param("userType")int userType);
	
	public List<ExperimentFinishVO> getExperimentScoreByGrade(@Param("gradeId")String gradeId, @Param("userType")int userType);
	
	public double getExperimentAvgScoreByGrade(@Param("gradeId")String gradeId, @Param("userType")int userType);

	public ExperimentRecord getExperimentRecord(String id);

	public String getStudentName(String studentId);

	public List<ExperimentRecord> getStudentExperimentRecordByStudent(String id);

	public List<ExperimentInfo> getExperimentNameByStudent(@Param("userId")String userId,@Param("status") String status);

	public List<ExperimentArrange> getTeacherNameByExperimentAppointment(@Param("userId")String userId,@Param("status") String status);

	public List<ExperimentAppointmentVO> getStudentExperimentRecordByStudentId(ExperimentAppointmentVO experimentAppointmentVO);

	public ExperimentRecord getExperimentResultById(String id);

	public String getUserTypeById(String id);
	
}