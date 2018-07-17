/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentVO;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-01-30
 */
public class ExperimentAppointment extends DataEntity<ExperimentAppointment> {
	
	private static final long serialVersionUID = 1L;
	private String studentId;								// 学生ID
	private String experimentInfoId;						// 实验ID
	private String experimentArrangeId;
	private String experimentAppointmentId;
	private int approveStatus;								// 审批状态(1已通过/2已拒绝/3已取消)
	private String isJoin;									// 是否参加
	private Date beginCreateDate;							// 开始 创建时间
	private Date endCreateDate;								// 结束 创建时间
	private int isFinish;
	private Date experimentStarttime; 						// 实验开始时间
	private Date experimentEndtime;    						// 实验创建时间
	private String fusufeReason;       						// 教师拒绝原因
	private String officeId;           						// 用户ID   
	List<ExperimentVO> studentExperimentApptime;  			// 学生预约实验实验
	private String experimentContent;     					// 实验内容
	private String laboratoryAddress;     					// 实验室地址
	private String experimentName;       					// 实验名字
	private Integer  surplusPeopleNum;   					// 实验剩余人数
	private String  laboratoryName;   					   // 实验室地址
	 
	public String getLaboratoryName() {
		return laboratoryName;
	}

	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}

	public Integer getSurplusPeopleNum() {
		return surplusPeopleNum;
	}

	public void setSurplusPeopleNum(Integer surplusPeopleNum) {
		this.surplusPeopleNum = surplusPeopleNum;
	}

	public String getExperimentName() {
		return experimentName;
	}

	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}

	public String getExperimentContent() {
		return experimentContent;
	}

	public void setExperimentContent(String experimentContent) {
		this.experimentContent = experimentContent;
	}

	public String getLaboratoryAddress() {
		return laboratoryAddress;
	}

	public void setLaboratoryAddress(String laboratoryAddress) {
		this.laboratoryAddress = laboratoryAddress;
	}

	public List<ExperimentVO> getStudentExperimentApptime() {
		return studentExperimentApptime;
	}

	public void setStudentExperimentApptime(List<ExperimentVO> studentExperimentApptime) {
		this.studentExperimentApptime = studentExperimentApptime;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getFusufeReason() {
		return fusufeReason;
	}

	public void setFusufeReason(String fusufeReason) {
		this.fusufeReason = fusufeReason;
	}
	
	public Date getExperimentStarttime() {
		return experimentStarttime;
	}

	public void setExperimentStarttime(Date experimentStarttime) {
		this.experimentStarttime = experimentStarttime;
	}

	public Date getExperimentEndtime() {
		return experimentEndtime;
	}

	public void setExperimentEndtime(Date experimentEndtime) {
		this.experimentEndtime = experimentEndtime;
	}

	public int getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(int isFinish) {
		this.isFinish = isFinish;
	}

	public ExperimentAppointment() {
		super();
	}

	public ExperimentAppointment(String id){
		super(id);
	}

	@Length(min=1, max=64, message="学生ID长度必须介于 1 和 64 之间")
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	@Length(min=1, max=64, message="实验ID长度必须介于 1 和 64 之间")
	public String getExperimentInfoId() {
		return experimentInfoId;
	}

	public void setExperimentInfoId(String experimentInfoId) {
		this.experimentInfoId = experimentInfoId;
	}
	
	public String getExperimentArrangeId() {
		return experimentArrangeId;
	}

	public void setExperimentArrangeId(String experimentArrangeId) {
		this.experimentArrangeId = experimentArrangeId;
	}

	@Length(min=1, max=11, message="审批状态(1已通过/2已拒绝/3已取消)长度必须介于 1 和 11 之间")
	public int getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(int approveStatus) {
		this.approveStatus = approveStatus;
	}
	
	@Length(min=1, max=1, message="是否参加长度必须介于 1 和 1 之间")
	public String getIsJoin() {
		return isJoin;
	}

	public void setIsJoin(String isJoin) {
		this.isJoin = isJoin;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public String getExperimentAppointmentId() {
		return experimentAppointmentId;
	}

	public void setExperimentAppointmentId(String experimentAppointmentId) {
		this.experimentAppointmentId = experimentAppointmentId;
	}
	
	
}