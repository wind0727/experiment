/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.experiment.vo.ClassVO;
import com.thinkgem.jeesite.modules.experiment.vo.GradeVO;
import com.thinkgem.jeesite.modules.experiment.vo.MajorVO;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
	public List<MajorVO> getMajorsByType(int type);
	
	public List<GradeVO> getGradesByMajor(@Param("majorId")String majorId, @Param("type")int type);
	
	public List<ClassVO> getClassByGrade(@Param("gradeId")String gradeId, @Param("type")int type);

	public List<ClassVO> getClassByMajor(String majorId);

	public Long getStudentNumByClass(String classId);

	public Float getAveAgeByClass(String classId);
	
}
