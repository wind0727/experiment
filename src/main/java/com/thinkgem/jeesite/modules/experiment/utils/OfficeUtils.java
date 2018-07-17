package com.thinkgem.jeesite.modules.experiment.utils;

import java.util.List;

import com.thinkgem.jeesite.modules.BeanFactory;
import com.thinkgem.jeesite.modules.experiment.vo.GradeVO;
import com.thinkgem.jeesite.modules.experiment.vo.MajorVO;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;

public class OfficeUtils {
	
	private static final String OFFICE_SERVICE = "officeService";

	//专业
	public static List<MajorVO> getMajorsByType(int type) throws Exception {
		
		OfficeService officeService = (OfficeService)BeanFactory.getBean(OFFICE_SERVICE);
		
		return officeService.getMajorsByType(type);
		
	}
	
	
	//年级
	public static List<GradeVO> getGradesByType(String majorId, int type) throws Exception {
		
		OfficeService officeService = (OfficeService)BeanFactory.getBean(OFFICE_SERVICE);
		
		return officeService.getGradesByMajor(majorId, type);
		
	}
	
	
	
}
 	 