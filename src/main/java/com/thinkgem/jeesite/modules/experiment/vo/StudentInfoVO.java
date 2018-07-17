package com.thinkgem.jeesite.modules.experiment.vo;

import java.io.Serializable;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentAppointment;

public class StudentInfoVO extends DataEntity<StudentInfoVO> {
    private String id;
	private String experimentAppointmentId;
	private String experimentArrangeId;
	private String studentName;
	private String officeName;
	private String parentId;  //学生所在年级
	private String experimentName;  //实验名称
	private String laboratoryAddress;
	private Date experimentStarttime;
	private String experimentInfoId;
	private int count;
	private Date experimentEndtime;
	private String officeId;
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public Date getExperimentEndtime() {
		return experimentEndtime;
	}

	public void setExperimentEndtime(Date experimentEndtime) {
		this.experimentEndtime = experimentEndtime;
	}

	public String getExperimentInfoId() {
		return experimentInfoId;
	}

	public void setExperimentInfoId(String experimentInfoId) {
		this.experimentInfoId = experimentInfoId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public String getLaboratoryAddress() {
		return laboratoryAddress;
	}

	public void setLaboratoryAddress(String laboratoryAddress) {
		this.laboratoryAddress = laboratoryAddress;
	}

	public Date getExperimentStarttime() {
		return experimentStarttime;
	}

	public void setExperimentStarttime(Date experimentStarttime) {
		this.experimentStarttime = experimentStarttime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getExperimentArrangeId() {
		return experimentArrangeId;
	}

	public void setExperimentArrangeId(String experimentArrangeId) {
		this.experimentArrangeId = experimentArrangeId;
	}
}
