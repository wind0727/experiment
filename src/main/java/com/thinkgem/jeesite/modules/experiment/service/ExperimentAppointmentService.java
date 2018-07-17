/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.service;

import java.util.Date;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentAppointment;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentArrange;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.utils.DateUtils;
import com.thinkgem.jeesite.modules.experiment.utils.StringUtil;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentAppointmentVO;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentVO;
import com.thinkgem.jeesite.modules.experiment.vo.StudentInfoVO;
import com.thinkgem.jeesite.modules.experiment.dao.ExperimentAppointmentDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2018-01-30
 */
@Service
@Transactional(readOnly = true)
public class ExperimentAppointmentService extends CrudService<ExperimentAppointmentDao, ExperimentAppointment> {
	

	private static final Logger log = LoggerFactory.getLogger(ExperimentAppointmentService.class);

	
	@Autowired
	private ExperimentAppointmentDao experimentAppointmentDao;

	public ExperimentAppointment get(String id) {
		return super.get(id);
	}
	
	public List<ExperimentAppointment> findList(ExperimentAppointment experimentAppointment) {
		return super.findList(experimentAppointment);
	}
	
	public Page<ExperimentAppointment> findPage(Page<ExperimentAppointment> page, ExperimentAppointment experimentAppointment) {
		return super.findPage(page, experimentAppointment);
	}
	
	@Transactional(readOnly = false)
	public void save(ExperimentAppointment experimentAppointment) {
		super.save(experimentAppointment);
	}
	
	@Transactional(readOnly = false)
	public void delete(ExperimentAppointment experimentAppointment) {
		super.delete(experimentAppointment);
	}
	
	public List<StudentInfoVO> getStuedntInfoVOByCondition(Page<StudentInfoVO> page, String id, StudentInfoVO studentInfoVO) {
		Date endDate = studentInfoVO.getExperimentEndtime();
		if(endDate == null) {
			endDate=DateUtils.getFirstOrEndDayOfMonth(false);
			studentInfoVO.setExperimentEndtime(endDate);
		}
		Date beginDate = studentInfoVO.getExperimentStarttime();
		if(beginDate == null) {
			beginDate=DateUtils.getFirstOrEndDayOfMonth(true);
			studentInfoVO.setExperimentStarttime(beginDate);
		}
		studentInfoVO.setId(id);
		studentInfoVO.setPage(page);
		return experimentAppointmentDao.getStuedntInfoVOByCondition(studentInfoVO);
	}
	
	public List<ExperimentAppointmentVO> getAppointmentByCondition(String studentId,int approveStatus) {
		return experimentAppointmentDao.getAppointmentByCondition(studentId, approveStatus);
	}
	
	public ExperimentAppointmentVO findExperimentAndTeacherInfoByCondition(String experimentAppointmentId) {
		return experimentAppointmentDao.findExperimentAndTeacherInfoByCondition(experimentAppointmentId);
	}

	public List<StudentInfoVO> getStuedntInfoName(String id) {
		return experimentAppointmentDao.getStuedntInfoName(id);
	}
	
	//审批状态(1待审核/2已通过/3已拒绝/4已取消)
	public List<ExperimentArrange> getMyAppointmentByUser(ExperimentArrange experimentArrange,String userId) {
		experimentArrange.setUserId(userId);
		List<ExperimentArrange> appoimentlist = experimentAppointmentDao.getMyAppointmentByUser(experimentArrange);
		for (ExperimentArrange experiment : appoimentlist) {
			if(experiment.getExperimentContent() != null) {
				experiment.setExperimentContent(StringUtil.getSubString(experiment.getExperimentContent()));
			}
			if(experiment.getApproveStatus()==1) {
				experiment.setApproveStatusList("待审核");
			}if(experiment.getApproveStatus()==2) {
				experiment.setApproveStatusList("已通过");
				}if(experiment.getApproveStatus()==3) {
					experiment.setApproveStatusList("已拒绝");
					}if(experiment.getApproveStatus()==4) {
						experiment.setApproveStatusList("已取消");
						}
		}
		 return appoimentlist;
	}
	
	@Transactional(readOnly = false)
	public void updateApproveStatus(ExperimentAppointment appointment) {
		experimentAppointmentDao.updateApproveStatus(appointment);
	}

	public List<ExperimentVO> getStudentExperimentAppointments(String id) {
		
		return experimentAppointmentDao.getStudentExperimentAppointments(id);
	}

	public List<ExperimentInfo> getMyAppointmentByStudentId(String userId) {
		
		return experimentAppointmentDao.getMyAppointmentByStudentId(userId);
	}

	public List<String> listViaExperimentNameByStudent(String approveSttus, String id) {
		return experimentAppointmentDao.listViaExperimentNameByStudent(approveSttus , id);
	}

	public ExperimentAppointment getExperimentArrangeById(String experimentArrangeId) {
		
		return experimentAppointmentDao.getExperimentArrangeById(experimentArrangeId);
	}
	
}