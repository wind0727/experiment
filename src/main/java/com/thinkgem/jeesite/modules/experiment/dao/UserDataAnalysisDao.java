package com.thinkgem.jeesite.modules.experiment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface UserDataAnalysisDao {

	int getUserCount();

	int getBoyCount();

	String getAgeSpread(@Param("startNum") String startNum,@Param("endNum") String endNum);

	String getUserCountByGrade(String gradeId);

	

}
