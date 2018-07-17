/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.experiment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.experiment.entity.Course;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.sys.entity.Teacher;

/**
 * 单表生成DAO接口
 * @author wind
 * @version 2018-03-27
 */
@MyBatisDao
public interface CourseDao extends CrudDao<Course> {

	List<Teacher> listTeacherName(String USER_TYPE);

	List<Course> listCourseTreeData(@Param("DEL_FLAG") String DEL_FLAG);

	String getTeacherNameById(String teacherId);

	String getCourseParentNameByid(String id);

	List<Course> listSonCoursesByCourseId(String id);

	void deleteAboutSon(@Param("idList") StringBuffer idList,@Param("DEL_FLAG_DELETE") String DEL_FLAG_DELETE);

	List<Course> listCourseName();

	List<ExperimentInfo> listExperimentName();

	Integer countExperiment();

	void updateExperimentAndCourseById(@Param("experimentId") String experimentId,@Param("courseId") String courseId);

	void insertExperimentAndCourseById(@Param("experimentId") String experimentId,@Param("courseId") String courseId);

	List<String> listCourseAndExperiment(String id);

	void cleanCourseExperimentByCourseId(String courseId);

	void deleteCourseExperimentByExperimentId(@Param("experimentList") StringBuffer experimentList);

	List<Course> listAlreadyCourseByOfficeId(@Param("delFlag") String delFlag,@Param("officeId") String officeId);

	List<String> listExperimentByCourse(String courseId);

	List<Course> listAllCourse();

	String getParentCourseById(String id);

	List<String> listExperimentByCourseId(String id);

}