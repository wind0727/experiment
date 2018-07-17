package com.thinkgem.jeesite.modules.experiment.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.druid.util.StringUtils;
import com.thinkgem.jeesite.modules.BeanFactory;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentAppointmentService;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentInfoService;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentAppointmentVO;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentVO;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.DictService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

public class ExperimentUtils {

	
	// 学生对应的实验
	public static List<ExperimentVO> getExperimentInfosByStudent() throws Exception {
		ExperimentInfoService experimentInfoService = (ExperimentInfoService) BeanFactory.getBean("experimentInfoService");
		List<ExperimentVO> experiments = new ArrayList<ExperimentVO>();
		User user = UserUtils.getUser();
		String userId = user.getOffice().getParentIds();
		String officeId = stringUtils(userId);
		List<ExperimentVO> experiment = experimentInfoService.getExperimentInfosByStudent(officeId);
		return experiments;
		
	}
	
	// 老师对应的实验
	public static List<ExperimentVO> getExperimentInfosByTeacher() throws Exception {
		ExperimentInfoService experimentInfoService = (ExperimentInfoService) BeanFactory.getBean("experimentInfoService");
		List<ExperimentVO> experiments = new ArrayList<ExperimentVO>();
		User user = UserUtils.getUser();
		String officeId = user.getOffice().getId();
		experimentInfoService.getExperimentInfosByTeacher(officeId);
		return experiments;
		
	}
	
	
	// 年级管理员对应的实验
	public static List<ExperimentVO> getExperimentInfosByAdmin() throws Exception {
		ExperimentInfoService experimentInfoService = (ExperimentInfoService) BeanFactory.getBean("experimentInfoService");
		User user = UserUtils.getUser();
		String officeId = user.getOffice().getId();
		return experimentInfoService.getExperimentInfosByAdmin(officeId);
	}
	
	
	
	public static List<ExperimentInfo> getExperimentInfosByOffice(ExperimentInfoService experimentInfoService) throws Exception {
		User user = UserUtils.getUser();
		String officeId = user.getOffice().getParentId();
//		List<String> officeList = Arrays.asList(officeIds);
//		Map<String, List<String>> map = new HashMap<String, List<String>>();
//		map.put("officeList", officeList);
		List<ExperimentInfo> experiments = experimentInfoService.getExperimentInfoByOffice(officeId);
		
		return experiments;
	}
	
	public static List<ExperimentAppointmentVO> getAppointmentByCondition(ExperimentAppointmentService appointmentService) throws Exception {
		
		User user = UserUtils.getUser();
		
		return appointmentService.getAppointmentByCondition(user.getId(), 2);
		
	}
    public static String stringUtils(String string) {
    	int length = string.length();
    	
     	String substring = string.substring(0,length-1);
     	
    	return substring;
    }
        // 字典信息
 		public static Dict getSysDictByType(String label, String type) throws Exception {
 			
 			DictService dictService = (DictService)BeanFactory.getBean("dictService");
 			
 			return dictService.findByCondition(label, type);
 			
 		}
}
