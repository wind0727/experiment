package com.thinkgem.jeesite.modules.experiment.vo;

import java.io.Serializable;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.Page;

public class ExperimentScoreVO implements Serializable {

	private String experimentInfoId;
	private int experimentScore;

	public String getExperimentInfoId() {
		return experimentInfoId;
	}

	public void setExperimentInfoId(String experimentInfoId) {
		this.experimentInfoId = experimentInfoId;
	}

	public int getExperimentScore() {
		return experimentScore;
	}

	public void setExperimentScore(int experimentScore) {
		this.experimentScore = experimentScore;
	}

}
