package com.thinkgem.jeesite.modules.experiment.vo;

import java.io.Serializable;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.Page;

public class ExperimentFinishVO implements Serializable {

	private String experimentInfoId;
	private Integer finishCount;
	private Double avgScore;

	public String getExperimentInfoId() {
		return experimentInfoId;
	}

	public void setExperimentInfoId(String experimentInfoId) {
		this.experimentInfoId = experimentInfoId;
	}

	public Integer getFinishCount() {
		return finishCount;
	}

	public void setFinishCount(Integer finishCount) {
		this.finishCount = finishCount;
	}

	public Double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

}
