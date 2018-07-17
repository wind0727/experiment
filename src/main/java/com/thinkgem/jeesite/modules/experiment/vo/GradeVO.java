package com.thinkgem.jeesite.modules.experiment.vo;

import java.io.Serializable;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.Page;

public class GradeVO implements Serializable {

	private String gradeId;
	private String gradeName;

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

}
