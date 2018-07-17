/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单标生成Entity
 * @author lzp
 * @version 2018-03-27
 */
public class ChoiceCourse extends DataEntity<ChoiceCourse> {
	
	private static final long serialVersionUID = 1L;
	private String courseId;		  	 			 	// 课程ID
	private String gradeStudentId;					 	// 年级/个人ID
	private String choiceType;		   				 	// 选课类型(1年级/2个人)
	private String courseName;
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public ChoiceCourse() {
		super();
	}

	public ChoiceCourse(String id){
		super(id);
	}

	@Length(min=1, max=64, message="课程ID长度必须介于 1 和 64 之间")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	@Length(min=1, max=64, message="年级/个人ID长度必须介于 1 和 64 之间")
	public String getGradeStudentId() {
		return gradeStudentId;
	}

	public void setGradeStudentId(String gradeStudentId) {
		this.gradeStudentId = gradeStudentId;
	}
	
	@Length(min=1, max=11, message="选课类型(1年级/2个人)长度必须介于 1 和 11 之间")
	public String getChoiceType() {
		return choiceType;
	}

	public void setChoiceType(String choiceType) {
		this.choiceType = choiceType;
	}
	
}