package com.thinkgem.jeesite.modules.experiment.vo;

import java.io.Serializable;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.Page;

public class ExperimentVO implements Serializable {
    private String id;
	private String experimentInfoId;
	private String experimentArrangeId;
	private String experimentName;
	private String experimentContent;
	private String laboratoryAddress;	
	private Date experimentStarttime;
	private Date experimentEndtime;
	private Integer surplusPeopleNum;
	public Integer getSurplusPeopleNum() {
		return surplusPeopleNum;
	}

	public void setSurplusPeopleNum(Integer surplusPeopleNum) {
		this.surplusPeopleNum = surplusPeopleNum;
	}

	public Date getExperimentEndtime() {
		return experimentEndtime;
	}

	public void setExperimentEndtime(Date experimentEndtime) {
		this.experimentEndtime = experimentEndtime;
	}

	private String teacherName;
	protected Page<ExperimentVO> page;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getExperimentInfoId() {
		return experimentInfoId;
	}

	public void setExperimentInfoId(String experimentInfoId) {
		this.experimentInfoId = experimentInfoId;
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

	public Date getExperimentStarttime() {
		return experimentStarttime;
	}

	public void setExperimentStarttime(Date experimentStarttime) {
		this.experimentStarttime = experimentStarttime;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Page<ExperimentVO> getPage() {
		return page;
	}

	public void setPage(Page<ExperimentVO> page) {
		this.page = page;
	}

	public String getExperimentArrangeId() {
		return experimentArrangeId;
	}

	public void setExperimentArrangeId(String experimentArrangeId) {
		this.experimentArrangeId = experimentArrangeId;
	}

}
