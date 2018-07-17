package com.thinkgem.jeesite.modules.experiment.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.echarts.Echarts;
import com.thinkgem.jeesite.modules.echarts.Series;
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentAppointment;
import com.thinkgem.jeesite.modules.experiment.service.UserDataAnalysisService;
import com.thinkgem.jeesite.modules.experiment.vo.ClassVO;
import com.thinkgem.jeesite.modules.experiment.vo.GradeVO;
import com.thinkgem.jeesite.modules.experiment.vo.MajorVO;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import net.sf.json.JSONArray;



@Controller
@RequestMapping(value = "${adminPath}/report/userDataAnalysis")
public class UserDataAnalysisController extends BaseController {
	
	private static final int SIX = 6; //专业
	private static final int SEVEN = 7; //年级
	private static final int EIGHT = 8; //班级
	private static final int TWO = 2; //学生
	
	
	
	@Autowired
	public OfficeService officeService;
	@Autowired
	private UserDataAnalysisService userDataAnalysisService;
	
	@RequiresPermissions("report:userDataAnalysis:view")
	@RequestMapping(value = {"list", ""})
	public String list(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) {
		String defaultMajorId = "";
		String defaultGradeId = "";
		List<GradeVO> grades = new ArrayList<GradeVO>();
		List<MajorVO> majors = officeService.getMajorsByType(SIX);
		if(!majors.isEmpty()) {
			MajorVO major = majors.get(0);
			defaultMajorId = major.getMajorId();
			grades = officeService.getGradesByMajor(defaultMajorId, SEVEN);
			if (!grades.isEmpty()) {
				defaultGradeId = grades.get(0).getGradeId();
			}
		}
		
		
		model.addAttribute("majors", majors);
		model.addAttribute("grades", grades);
		model.addAttribute("defaultMajorId", defaultMajorId);
		model.addAttribute("defaultGradeId", defaultGradeId);
		
		return "modules/experiment/userDataAnalysis";
	}
	
	@RequestMapping(value="/userData",method=RequestMethod.GET)
	@ResponseBody
	public String userData(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		User user = UserUtils.getUser();
		List<String> axis=new LinkedList<String>();
		List<Long> allUserCount = new LinkedList<Long>();
		List<Long> boyUserCount = new LinkedList<Long>();
		List<Long> girlUserCount = new LinkedList<Long>();
		
		allUserCount.add(new Long(80));
		boyUserCount.add(new Long(20));
		girlUserCount.add(new Long(60));
		
		List<String> legend=new ArrayList<String>(Arrays.asList(new String[]{"全部","男生","女生"}));
	    
		List<Series> series=new ArrayList<Series>();
		int sum=userDataAnalysisService.getUserCount();
		int boySum=userDataAnalysisService.getBoyCount();
		int girlSum=sum-boySum;
	    String jsonString = "[{value:"+sum+", name:'全部'},{value:"+boySum+", name:'男生'},{value:"+girlSum+", name:'女生'}]";      
	    JSONArray jsonArray = JSONArray.fromObject(jsonString);
	    List<Map<String,Object>> mapListJson = (List)jsonArray;  
	    series.add(new Series("人员分布","pie", "", null, null, mapListJson));
	    
		Echarts echarts = new Echarts(legend, axis, series);
		
		Gson gson = new Gson();
		String json = gson.toJson(echarts);
		gson = null;
		
		return json;
	}
	
	@RequestMapping(value="/userGradeData",method=RequestMethod.GET)
	@ResponseBody
	public String userGradeData(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		User user = UserUtils.getUser();
		List<String> axis=new LinkedList<String>();
		List<GradeVO> grades = new ArrayList<GradeVO>();
		List<String> countList = new LinkedList<String>();
		Map<String, List<String>> countMap = new LinkedHashMap<String, List<String>>();
		List<MajorVO> majors = officeService.getMajorsByType(SIX);
		if(!majors.isEmpty()) {
			for (MajorVO major : majors) {
				axis.add(major.getMajorName());
				grades = officeService.getGradesByMajor(major.getMajorId(), SEVEN);
				for (GradeVO grade : grades) {
				String userCount=userDataAnalysisService.getUserCountByGrade(grade.getGradeId());
				String key = grade.getGradeName();
				if (countMap.containsKey(key)) {
					
					countList = countMap.get(key);
					countList.add(userCount);
					
				} else {
					
					countList = new LinkedList<String>();
					countList.add(userCount);
					countMap.put(key, countList);
					
				}
				}
			}
			
		}
		List<String> legend=new ArrayList<String>();
	    
		List<Series> series=new ArrayList<Series>();
		
		Map<String, Object> showMap = new HashMap<String, Object>();
		Map<String, Map<String, Object>> normalMap = new HashMap<String, Map<String, Object>>();
		showMap.put("show", true);
		showMap.put("position", "insideRight");
		normalMap.put("normal", showMap);
		
		/*series.add(new Series("15届","bar","总量",normalMap, Count15));
		series.add(new Series("16届","bar","总量",normalMap, Count16));
		series.add(new Series("17届","bar","总量",normalMap, Count17));
		series.add(new Series("18届","bar","总量",normalMap, Count18));*/
	    
		if (!countMap.isEmpty()) {
					
					Set<Entry<String, List<String>>> entrySet = countMap.entrySet();
					for (Entry<String, List<String>> entry : entrySet) {
						String key = entry.getKey();
						legend.add(key);
						series.add(new Series(key,"bar","总量",normalMap, entry.getValue()));
						
					}
					
				}
		Echarts echarts = new Echarts(legend, axis, series);
		
		Gson gson = new Gson();
		String json = gson.toJson(echarts);
		gson = null;
		
		return json;
	}
	
	@RequestMapping(value="/userClassData",method=RequestMethod.GET)
	@ResponseBody
	public String userClassData(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		List<Long> classCount = new LinkedList<Long>();
		List<Float> ageCount = new LinkedList<Float>();
		String majorId = request.getParameter("gradeId");
		List<ClassVO> classList=officeService.getClassByMajor(majorId);
		for (ClassVO list : classList) {
		  Long peoNum =officeService.getStudentNumByClass(list.getClassId());
		  classCount.add(peoNum);
		  float ageNum=officeService.getAveAgeByClass(list.getClassId());
		  ageCount.add(ageNum);
		}
		User user = UserUtils.getUser();
		List<String> axis=new LinkedList<String>();
		for (ClassVO className : classList) {
			axis.add(className.getClassName());
		}
		
		/*
		classCount.add(new Long(30));
		classCount.add(new Long(40));
		classCount.add(new Long(60));
		classCount.add(new Long(90));
		classCount.add(new Long(50));
		classCount.add(new Long(70));
		ageCount.add(new Long(20));
		ageCount.add(new Long(22));
		ageCount.add(new Long(18));
		ageCount.add(new Long(25));
		ageCount.add(new Long(21));
		ageCount.add(new Long(26));
		*/
		List<String> legend=new ArrayList<String>(Arrays.asList(new String[]{"班级人数","平均年龄"}));
	    
		List<Series> series=new ArrayList<Series>();
		
		series.add(new Series("班级人数","bar","", null, classCount));
		series.add(new Series("平均年龄","line","",0, 1, ageCount));
	    
		Echarts echarts = new Echarts(legend, axis, series);
		
		Gson gson = new Gson();
		String json = gson.toJson(echarts);
		gson = null;
		
		return json;
	}
	
	@RequestMapping(value="/userAgeData",method=RequestMethod.GET)
	@ResponseBody
	public String userAgeData(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String startNum=null;
		String endNum=null;
		List<Long> agelist = new LinkedList<Long>();
		User user = UserUtils.getUser();
		List<String> axis=new LinkedList<String>();
		List<Long> allUserCount = new LinkedList<Long>();
		List<Long> boyUserCount = new LinkedList<Long>();
		List<Long> girlUserCount = new LinkedList<Long>();
		
		allUserCount.add(new Long(80));
		boyUserCount.add(new Long(20));
		girlUserCount.add(new Long(60));
		
		String stringAge="\" 0~18岁 \" ";
		for(int j=18;j<26;j++) {
			stringAge+=", \" "+j+"~"+(j+1)+"岁 \"";
		}
		stringAge+=",\"27岁以上 \"";
		List<String> legend=new ArrayList<String>(Arrays.asList(new String[]{"0~18岁" , "18~19岁", "19~20岁", "20~21岁", "21~22岁", "22~23岁", "23~24岁", "24~25 ", "25~26岁","27岁以上"}));
		legend.size();
        List<Series> series=new ArrayList<Series>();
        String age1 = userDataAnalysisService.getAgeSpread("0","18");
		String jsonString="[{value:"+age1+", name:'0~18岁'}";
		for(int i=18;i<=26;i++) {
			String list = userDataAnalysisService.getAgeSpread(Integer.toString(i),Integer.toString(i+1));
			 jsonString += ",{value:"+list+", name:'"+i+"~"+(i+1)+"岁'}";
		}
		String age6 = userDataAnalysisService.getAgeSpread("26","200");
		 jsonString+=",{value:"+age6+", name:'27岁以上'}]";
		 
	    JSONArray jsonArray = JSONArray.fromObject(jsonString);
	    List<Map<String,Object>> mapListJson = (List)jsonArray;  
	    series.add(new Series("面积模式","pie", "", null, null, mapListJson));
	    
		Echarts echarts = new Echarts(legend, legend, series);
		
		Gson gson = new Gson();
		String json = gson.toJson(echarts);
		gson = null;
		
		return json;
	}

}
