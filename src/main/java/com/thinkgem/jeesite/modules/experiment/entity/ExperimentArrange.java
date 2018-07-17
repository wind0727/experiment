package com.thinkgem.jeesite.modules.experiment.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2018-01-23
 */
public class ExperimentArrange extends DataEntity<ExperimentArrange> {
	
	private static final long serialVersionUID = 1L;
	private String experimentInfoId;				// 实验名称
	private String experimentName;          		//实验真实名称
	private String teacherName;             		//老师名称
	private String experimentArrangeId;
	private String laboratoryName;		    		// 实验室名称
	private String laboratoryAddress;				// 实验室地址
	private Date experimentStarttime;				// 实验开始时间
	private Date experimentEndtime;		    		// 实验结束时间
	private String teacherId;		        		// 值班老师
	private String count;		            		// 参加人数
	private Date beginExperimentStarttime;			// 开始 实验开始时间
	private Integer approveStatus;		    		//审批状态(1待审核/2已通过/3已拒绝/4已取消)
	private String experimentContent;      	 		//实验内容
	private Date endExperimentStarttime;    		// 结束 实验开始时间
	private String approveStatusList;       		// 页面展示状态
	private Integer experimentAppointmentCount=0;   //实验预约次数
	private String officeId;   						
	private String userId;
	private String userName;
	private String password;
	private Course course;                      	 //课程ID
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String getPassword) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public Integer getExperimentAppointmentCount() {
		return experimentAppointmentCount;
	}

	public void setExperimentAppointmentCount(Integer experimentAppointmentCount) {
		this.experimentAppointmentCount = experimentAppointmentCount;
	}

	public String getApproveStatusList() {
		return approveStatusList;
	}

	public void setApproveStatusList(String approveStatusList) {
		this.approveStatusList = approveStatusList;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}
	
	public String getExperimentContent() {
		return experimentContent;
	}

	public void setExperimentContent(String experimentContent) {
		this.experimentContent = experimentContent;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public String getExperimentName() {
		return experimentName;
	}

	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}

	public ExperimentArrange() {
		super();
	}

	public ExperimentArrange(String id){
		super(id);
	}
	
	public String getExperimentArrangeId() {
		return experimentArrangeId;
	}

	public void setExperimentArrangeId(String experimentArrangeId) {
		this.experimentArrangeId = experimentArrangeId;
	}

	@Length(min=1, max=64, message="实验名称长度必须介于 1 和 64 之间")
	public String getExperimentInfoId() {
		return experimentInfoId;
	}

	public void setExperimentInfoId(String experimentInfoId) {
		this.experimentInfoId = experimentInfoId;
	}
	
	@Length(min=1, max=200, message="实验室名称长度必须介于 1 和 200 之间")
	public String getLaboratoryName() {
		return laboratoryName;
	}

	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}
	
	@Length(min=1, max=500, message="实验室地址长度必须介于 1 和 500 之间")
	public String getLaboratoryAddress() {
		return laboratoryAddress;
	}

	public void setLaboratoryAddress(String laboratoryAddress) {
		this.laboratoryAddress = laboratoryAddress;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="实验开始时间不能为空")
	public Date getExperimentStarttime() {
		return experimentStarttime;
	}

	public void setExperimentStarttime(Date experimentStarttime) {
		this.experimentStarttime = experimentStarttime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="实验结束时间不能为空")
	public Date getExperimentEndtime() {
		return experimentEndtime;
	}

	public void setExperimentEndtime(Date experimentEndtime) {
		this.experimentEndtime = experimentEndtime;
	}
	
	@Length(min=1, max=64, message="值班老师长度必须介于 1 和 64 之间")
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	@Length(min=1, max=11, message="参加人数长度必须介于 1 和 11 之间")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	public Date getBeginExperimentStarttime() {
		return beginExperimentStarttime;
	}

	public void setBeginExperimentStarttime(Date beginExperimentStarttime) {
		this.beginExperimentStarttime = beginExperimentStarttime;
	}
	
	public Date getEndExperimentStarttime() {
		return endExperimentStarttime;
	}

	public void setEndExperimentStarttime(Date endExperimentStarttime) {
		this.endExperimentStarttime = endExperimentStarttime;
	}
		
}