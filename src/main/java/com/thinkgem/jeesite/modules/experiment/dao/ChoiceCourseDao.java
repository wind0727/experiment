/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.experiment.entity.ChoiceCourse;
import com.thinkgem.jeesite.modules.experiment.entity.Course;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 单标生成DAO接口
 * @author lzp
 * @version 2018-03-27
 */
@MyBatisDao
public interface ChoiceCourseDao extends CrudDao<ChoiceCourse> {

	List<Course> listAdminExperimentName();

	List<ChoiceCourse> listAdminCourseByUser(String officeId);

	String getAdminCourseNameById(String id);

	List<Office> listAllOffice();

	List<Course> getCourseByOffice(String officeId);

	void deleteChoiceCourseByGrod(String officeId);

	void insertGordCourse(String id, String officeId);

	List<Course> listCourseByOfficeId(String id);

	String getTypeByOfficeId(String officeId);

	List<Office> listOfficeById(String officeId);

	List<Course> listStudentCourseByOfficeIdAndUserId(@Param("officeId") String officeId,@Param("userId") String userId);

}