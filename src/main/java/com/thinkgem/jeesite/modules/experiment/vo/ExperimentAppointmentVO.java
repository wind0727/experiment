package com.thinkgem.jeesite.modules.experiment.vo;

import java.io.Serializable;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class ExperimentAppointmentVO  extends DataEntity<ExperimentAppointmentVO> {
    
	private String experimentInfoId;
	private String experimentAppointmentId;
	private String experimentName;
	private String teacherId; 
	private String teacherName;//老师的名字
	private Date experimentStarttime;    //实验开始时间
	private Date experimentEndtime;      //实验结束时间
	private String userId;               //用户ID
	private String experimentCompletion;	
	private String experimentProblem;
	public String getExperimentProblem() {
		return experimentProblem;
	}

	public void setExperimentProblem(String experimentProblem) {
		this.experimentProblem = experimentProblem;
	}

	public String getExperimentCompletion() {
		return experimentCompletion;
	}

	public void setExperimentCompletion(String experimentCompletion) {
		this.experimentCompletion = experimentCompletion;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getExperimentInfoId() {
		return experimentInfoId;
	}

	public void setExperimentInfoId(String experimentInfoId) {
		this.experimentInfoId = experimentInfoId;
	}

	public String getExperimentAppointmentId() {
		return experimentAppointmentId;
	}

	public void setExperimentAppointmentId(String experimentAppointmentId) {
		this.experimentAppointmentId = experimentAppointmentId;
	}

	public String getExperimentName() {
		return experimentName;
	}

	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

}
