package com.thinkgem.jeesite.modules.experiment.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentAppointment;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentArrange;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentAppointmentVO;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentVO;
import com.thinkgem.jeesite.modules.experiment.vo.StudentInfoVO;

/**
 * 单表生成DAO接口
 * @author ThinkGem
 * @version 2018-01-30
 */
@MyBatisDao
public interface ExperimentAppointmentDao extends CrudDao<ExperimentAppointment> {
	
	public List<StudentInfoVO> getStuedntInfoVOByCondition(StudentInfoVO studentInfoVO);
	
	public List<ExperimentAppointmentVO> getAppointmentByCondition(@Param("studentId") String studentId, @Param("approveStatus") int approveStatus);
	
	public ExperimentAppointmentVO findExperimentAndTeacherInfoByCondition(@Param("experimentAppointmentId") String experimentAppointmentId);

	public List<StudentInfoVO> getStuedntInfoName(String id);

	public List<ExperimentArrange> getMyAppointmentByUser(ExperimentArrange experimentArrange);

	public void updateApproveStatus(ExperimentAppointment appointment);

	public List<ExperimentVO> getStudentExperimentAppointments(String id);

	public List<ExperimentInfo> getMyAppointmentByStudentId(String userId);

	public List<String> listViaExperimentNameByStudent(@Param("approveSttus") String approveSttus,@Param("id") String id);

	public ExperimentAppointment getExperimentArrangeById(String id);
	
}