package com.thinkgem.jeesite.modules.experiment.utils;

import java.util.List;

import com.thinkgem.jeesite.modules.experiment.entity.ExperimentRecord;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentVO;

public class StringUtil {
      public static String getSubString(String string) {
    	 
				if(string!=null) {
				if(string.length()>20) { 
					string = string.substring(0, 20);
					string+="..........";
				}
				
				}	
			
    	  return string;
      }
      
      //把集合中某一项放到集合第一个位子
      public static List<ExperimentVO>  exchangeList(String experimentId,List<ExperimentVO> experiment) {
    	  ExperimentVO firstExperimentVO = experiment.get(0);
    	  String experimentInfoId = firstExperimentVO.getExperimentInfoId();
    	  String experimentName = firstExperimentVO.getExperimentName();
    	  for(int i = 0;i < experiment.size();i++) {
     	 		if(experiment != null && experiment.get(i).getExperimentInfoId() !=null && experiment.get(i).getExperimentInfoId().equals(experimentId)) {
     	 			experiment.get(0).setExperimentInfoId(experiment.get(i).getExperimentInfoId());
     	 			experiment.get(0).setExperimentName(experiment.get(i).getExperimentName());
     	 			experiment.get(i).setExperimentInfoId(experimentInfoId);
     	 			experiment.get(i).setExperimentName(experimentName);
     	 		}
			}
		
	  return experiment;
}
}
