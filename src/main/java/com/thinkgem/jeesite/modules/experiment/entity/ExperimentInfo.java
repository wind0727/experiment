/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-01-22
 */
public class ExperimentInfo extends DataEntity<ExperimentInfo> {
	
	private static final long serialVersionUID = 1L;
	private Office office;								// 所属专业
	private String experimentName;						// 实验名称
	private String experimentObjective;					// 实验目的
	private String experimentContent;					// 实验内容
	private String experimentRequire;					// 实验要求
	
	public ExperimentInfo() {
		super();
	}

	public ExperimentInfo(String id){
		super(id);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=1, max=200, message="实验名称长度必须介于 1 和 200 之间")
	public String getExperimentName() {
		return experimentName;
	}

	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}
	
	@Length(min=1, max=2000, message="实验目的长度必须介于 1 和 2000 之间")
	public String getExperimentObjective() {
		return experimentObjective;
	}

	public void setExperimentObjective(String experimentObjective) {
		this.experimentObjective = experimentObjective;
	}
	
	@Length(min=1, max=2000, message="实验内容长度必须介于 1 和 2000 之间")
	public String getExperimentContent() {
		return experimentContent;
	}

	public void setExperimentContent(String experimentContent) {
		this.experimentContent = experimentContent;
	}
	
	@Length(min=1, max=1000, message="实验要求长度必须介于 1 和 1000 之间")
	public String getExperimentRequire() {
		return experimentRequire;
	}

	public void setExperimentRequire(String experimentRequire) {
		this.experimentRequire = experimentRequire;
	}
	
}