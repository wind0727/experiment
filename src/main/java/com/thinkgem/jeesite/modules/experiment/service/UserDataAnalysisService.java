package com.thinkgem.jeesite.modules.experiment.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.experiment.dao.UserDataAnalysisDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

@Service
public class UserDataAnalysisService {
	@Autowired
    private UserDataAnalysisDao userDataAnalysisDao;
	public int getUserCount() {
		return userDataAnalysisDao.getUserCount();
	}
	public int getBoyCount() {
		return userDataAnalysisDao.getBoyCount();
	}
	public String getAgeSpread(String startNum,String endNum) {
		String ageSpread = userDataAnalysisDao.getAgeSpread(startNum,endNum);
	return ageSpread;
	}
	public String getUserCountByGrade(String gradeId) {
		
		return userDataAnalysisDao.getUserCountByGrade(gradeId);
	}

}
