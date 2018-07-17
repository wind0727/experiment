/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author wind
 * @version 2018-01-31
 */
public class Teacher extends DataEntity<Teacher> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 学生ID
	private String skill;		// 擅长技能
	
	public Teacher() {
		super();
	}

	public Teacher(String id){
		super(id);
	}

	@NotNull(message="学生ID不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=1000, message="擅长技能长度必须介于 0 和 1000 之间")
	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}
	
}