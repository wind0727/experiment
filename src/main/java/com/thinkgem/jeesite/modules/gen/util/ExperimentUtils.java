package com.thinkgem.jeesite.modules.gen.util;

import java.util.ArrayList;

import java.util.List;

import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentAppointmentService;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentInfoService;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentAppointmentVO;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

public class ExperimentUtils {
	
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

}
