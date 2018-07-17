/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.entity;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author wind
 * @version 2018-03-27
 */
public class Course extends DataEntity<Course> {
	
	private static final long serialVersionUID = 1L;
	private String courseName;							// 课程名称
	private Course parent;								// 课程父ID
	private String introduce;							// 课程介绍
	private String teacherId;							// 教师ID
	private String teacherName;		                    // 教师名称
    private String code; 
    private String experiments;                         //课程所关联的实验
    
	public String getCode() {
		return code;
	}

	public String getExperiments() {
		return experiments;
	}

	public void setExperiments(String experiments) {
		this.experiments = experiments;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Course() {
		super();
	}

	public Course(String id){
		super(id);
	}

	@Length(min=1, max=64, message="课程名称长度必须介于 1 和 64 之间")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	@JsonBackReference
	@NotNull(message="课程父ID不能为空")
	public Course getParent() {
		return parent;
	}

	public void setParent(Course parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=1000, message="课程介绍长度必须介于 1 和 1000 之间")
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Length(min=0, max=64, message="教师ID长度必须介于 0 和 64 之间")
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	@Length(min=0, max=500, message="教师名称长度必须介于 0 和 500 之间")
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
}