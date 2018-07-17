package com.thinkgem.jeesite.modules.experiment.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentAppointment;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentRecord;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentAppointmentVO;
import com.thinkgem.jeesite.modules.experiment.vo.StudentInfoVO;
@MyBatisDao
public interface TeacherCommentDao extends CrudDao<ExperimentRecord>{

	List<ExperimentRecord> getTeacherCommentByCondition(@Param("officeId") String officeId);

	public List<ExperimentRecord> getStuedntInfoVOByCondition(ExperimentRecord experimentRecord);
	
	public String getTeacherNameById(String teacherId);
	
	public String getExperimentInfoIdNameById(String experimentInfoId);
	
	public ExperimentAppointmentVO findExperimentAndTeacherInfoByCondition(@Param("experimentAppointmentId") String experimentAppointmentId);

	public String getExperimentInfoIdByappointmentId(String experimentAppointmentId);

	void saveHistory(ExperimentRecord experimentRecord);

	void updateHistory(ExperimentRecord experimentRecord);

	public List  finshSave(ExperimentRecord experimentRecord);

	public ExperimentRecord getExperimetRecordById(ExperimentRecord experimentRecord);

	void setAppointmentIdFinsh(ExperimentRecord experimentRecord);

	ExperimentAppointment getExperimentAppointmentByRecordId(ExperimentRecord experimentRecord);

	void saveAppoimentHistory(ExperimentAppointment experimentAppointment);

	List<ExperimentInfo> getExperimentInfoName(String teacherId);

	void updateScore(ExperimentRecord experimentRecord);

	String getStudentNameById(String studentId);
}
