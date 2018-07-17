/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author wind
 * @version 2018-02-02
 */
public class ExperimentRecord extends DataEntity<ExperimentRecord> {
	
	private static final long serialVersionUID = 1L;
	private String studentId;									// 学生名称
	private String experimentInfoId;							// 实验名称
	private String experimentAppointmentId;
	private String teacherId;									// 值班老师
	private String experimentProblem;							// 实验出现的问题及解决方案
	private String experimentCompletion;						// 实验完成情况
	private String experimentScore;								// 实验得分
	private String experimentComment;							// 实验评语
	private String experimentName;        						// 实验名字
	private String teacherName;           						// 值班老师名称
	private String studentName;           						// 学生名称
	private String count;                 						// 试验完成次数
	private String experimentResultUrl;   						// 实验结果地址
	private Date experimentStarttime;    	 				    // 实验开始时间
	private Date experimentEndtime;     						// 实验结束时间
	private String experimentArrangeId;
	private String officeId;
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getExperimentArrangeId() {
		return experimentArrangeId;
	}

	public void setExperimentArrangeId(String experimentArrangeId) {
		this.experimentArrangeId = experimentArrangeId;
	}

	public String getExperimentResultUrl() {
		return experimentResultUrl;
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

	public void setExperimentResultUrl(String experimentResultUrl) {
		this.experimentResultUrl = experimentResultUrl;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	               //实验完成的次数
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}	

	private String[] teacherIds;
	
	
	public String[] getTeacherIds() {
		return teacherIds;
	}

	public void setTeacherIds(String[] teacherIds) {
		this.teacherIds = teacherIds;
	}

	public String getExperimentName() {
		return experimentName;
	}

	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public ExperimentRecord() {
		super();
	}

	public ExperimentRecord(String id){
		super(id);
	}

	@Length(min=1, max=64, message="学生名称长度必须介于 1 和 64 之间")
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	@Length(min=1, max=64, message="实验名称长度必须介于 1 和 64 之间")
	public String getExperimentInfoId() {
		return experimentInfoId;
	}

	public void setExperimentInfoId(String experimentInfoId) {
		this.experimentInfoId = experimentInfoId;
	}
	
	@Length(min=1, max=64, message="值班老师长度必须介于 1 和 64 之间")
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	@Length(min=0, max=2000, message="实验出现的问题及解决方案长度必须介于 0 和 2000 之间")
	public String getExperimentProblem() {
		return experimentProblem;
	}

	public void setExperimentProblem(String experimentProblem) {
		this.experimentProblem = experimentProblem;
	}
	
	@Length(min=0, max=2000, message="实验完成情况长度必须介于 0 和 2000 之间")
	public String getExperimentCompletion() {
		return experimentCompletion;
	}

	public void setExperimentCompletion(String experimentCompletion) {
		this.experimentCompletion = experimentCompletion;
	}
	
	public String getExperimentScore() {
		return experimentScore;
	}

	public void setExperimentScore(String experimentScore) {
		this.experimentScore = experimentScore;
	}
	
	@Length(min=0, max=2000, message="实验评语长度必须介于 0 和 2000 之间")
	public String getExperimentComment() {
		return experimentComment;
	}

	public void setExperimentComment(String experimentComment) {
		this.experimentComment = experimentComment;
	}

	public String getExperimentAppointmentId() {
		return experimentAppointmentId;
	}

	public void setExperimentAppointmentId(String experimentAppointmentId) {
		this.experimentAppointmentId = experimentAppointmentId;
	}
	
}