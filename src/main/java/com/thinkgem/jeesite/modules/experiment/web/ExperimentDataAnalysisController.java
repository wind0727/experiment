package com.thinkgem.jeesite.modules.experiment.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.thinkgem.jeesite.modules.experiment.entity.ExperimentInfo;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentInfoService;
import com.thinkgem.jeesite.modules.experiment.service.ExperimentRecordService;
import com.thinkgem.jeesite.modules.experiment.vo.ClassVO;
import com.thinkgem.jeesite.modules.experiment.vo.ExperimentFinishVO;
import com.thinkgem.jeesite.modules.experiment.vo.GradeVO;
import com.thinkgem.jeesite.modules.experiment.vo.MajorVO;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "${adminPath}/report/experimentDataAnalysis")
public class ExperimentDataAnalysisController extends BaseController {
	private static final int SIX = 6; //专业
	private static final int SEVEN = 7; //年级
	private static final int EIGHT = 8; //班级
	private static final int TWO = 2; //学生
	
	private static final String BAR = "bar";
	private static final String PIE = "pie";
	private static final String LINE = "line";
	
	@Autowired
	private OfficeService officeService;
	@Autowired
	private ExperimentInfoService experimentInfoService;
	@Autowired
	private ExperimentRecordService experimentRecordService;
	
	@RequiresPermissions("report:experimentDataAnalysis:view")
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
//		model.addAttribute("grades", grades);
//		model.addAttribute("defaultMajorId", defaultMajorId);
		model.addAttribute("defaultGradeId", defaultGradeId);
		return "modules/experiment/experimentDataAnalysis";
	}
	
	@RequiresPermissions("report:experimentDataAnalysis:view")
	@RequestMapping(value ="ajax")
	public void ajax(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String majorId = request.getParameter("majorId");
		List<GradeVO> grades = officeService.getGradesByMajor(majorId, SEVEN);
		Gson gson = new Gson();
		String json = gson.toJson(grades);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/experimentData",method=RequestMethod.GET)
	@ResponseBody
	public String experimentData(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		List<String> axis=new LinkedList<String>();
		List<String> legend=new ArrayList<String>();
		Map<String, Object> countMap = new HashMap<String, Object>();
		List<Map<String, Object>> countList = new ArrayList<Map<String, Object>>();
		
		List<MajorVO> majors = officeService.getMajorsByType(SIX);
		
		if (!majors.isEmpty()) {
			
			for (MajorVO major : majors) {
				countMap = new HashMap<String,Object>();
				
				legend.add(major.getMajorName());
				
				countMap.put("name", major.getMajorName());
				int count = experimentInfoService.getExperimentCountByOffice(major.getMajorId());
				if (count == 0) count = 1; ////////////////
				countMap.put("value", count);
				countList.add(countMap);
				
			}
			
		}
	    
		List<Series> series=new ArrayList<Series>();
	    JSONArray jsonArray = JSONArray.fromObject(countList);
	    List<Map<String,Object>> mapListJson = (List)jsonArray;  
	    series.add(new Series("","pie", "", null, null, mapListJson));
	    
		Echarts echarts = new Echarts(legend, axis, series);
		
		Gson gson = new Gson();
		String json = gson.toJson(echarts);
		gson = null;
		
		return json;
	}
	
	@RequestMapping(value="/experimentGradeData",method=RequestMethod.GET)
	@ResponseBody
	public String experimentGradeData(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		List<String> axis=new LinkedList<String>();
		List<String> legend=new LinkedList<String>();
		List<Integer> countList = new LinkedList<Integer>();
		Map<String, List<Integer>> countMap = new LinkedHashMap<String, List<Integer>>();
		List<GradeVO> grades = new ArrayList<GradeVO>();
		List<MajorVO> majors = officeService.getMajorsByType(SIX);
		if (!majors.isEmpty()) {
			
			for (MajorVO major : majors) {
				
				countList = new LinkedList<Integer>();
				axis.add(major.getMajorName());
				grades = officeService.getGradesByMajor(major.getMajorId(), SEVEN);
				for (GradeVO grade : grades) {
					
					int count = experimentInfoService.getExperimentCountByGrade(grade.getGradeId());
					if (count == 0) count = 1; ////////////////
					String key = grade.getGradeName();
					if (countMap.containsKey(key)) {
						
						countList = countMap.get(key);
						countList.add(count);
						
					} else {
						
						countList = new LinkedList<Integer>();
						countList.add(count);
						countMap.put(key, countList);
						
					}
					
				}
				
			}
			
		}
	    
		List<Series> series=new ArrayList<Series>();
		
		Map<String, Object> showMap = new HashMap<String, Object>();
		Map<String, Map<String, Object>> normalMap = new HashMap<String, Map<String, Object>>();
		showMap.put("show", true);
		showMap.put("position", "insideRight");
		normalMap.put("normal", showMap);
		
		//
		if (!countMap.isEmpty()) {
			
			Set<Entry<String, List<Integer>>> entrySet = countMap.entrySet();
			for (Entry<String, List<Integer>> entry : entrySet) {
				String key = entry.getKey();
				legend.add(key);
				series.add(new Series(key,BAR,"总量",normalMap, entry.getValue()));
				
			}
			
		}
		
		Echarts echarts = new Echarts(legend, axis, series);
		
		Gson gson = new Gson();
		String json = gson.toJson(echarts);
		gson = null;
		
		return json;
	}
	
	@RequestMapping(value="/experimentGradeSingleData",method=RequestMethod.GET)
	@ResponseBody
	public String experimentGradeSingleData(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		String majorId = request.getParameter("majorId");
		String gradeId = request.getParameter("gradeId");
		
		List<String> legend = new LinkedList<String>();
		List<Integer> finishCount = new LinkedList<Integer>();
		List<Double> avgCount = new LinkedList<Double>();
		
		legend.add("完成次数");
		legend.add("平均得分");
		
		List<String> axis=new LinkedList<String>();
		List<Series> series=new ArrayList<Series>();
		List<ExperimentFinishVO> experimentFinishs = experimentRecordService.getExperimentFinishsByGrade(gradeId, TWO);
		if (!experimentFinishs.isEmpty()) {
			for (ExperimentFinishVO experimentFinish : experimentFinishs) {
				ExperimentInfo info = experimentInfoService.get(experimentFinish.getExperimentInfoId());
				String experimentName = info == null ? "" : info.getExperimentName();
				axis.add(experimentName);
				finishCount.add(experimentFinish.getFinishCount());
				avgCount.add(experimentFinish.getAvgScore());
			}
		}
		
		Map<String, Object> showMap = new HashMap<String, Object>();
		Map<String, Map<String, Object>> normalMap = new HashMap<String, Map<String, Object>>();
		showMap.put("show", true);
		showMap.put("position", "insideRight");
		normalMap.put("normal", showMap);
		
		series.add(new Series("完成次数",BAR,"", 0, 0, finishCount));
		series.add(new Series("平均得分",LINE,"", 0, 1, avgCount));
	    
		Echarts echarts = new Echarts(legend, axis, series);
		
		Gson gson = new Gson();
		String json = gson.toJson(echarts);
		gson = null;
		
		return json;
	}
	@RequestMapping(value="/experimentClassSingleData",method=RequestMethod.GET)
	@ResponseBody
	public String experimentClassSingleData(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		List<String> legend=new LinkedList<String>();
		
		String majorId = request.getParameter("majorId");
		String gradeId = request.getParameter("gradeId");
		
		List<String> axis=new LinkedList<String>();
		List<Series> series=new ArrayList<Series>();
		List<Integer> finishCount = new LinkedList<Integer>();
		List<ClassVO> classes = officeService.getClassByGrade(gradeId, EIGHT);
		if(!classes.isEmpty()) {
			for (ClassVO cls : classes) {
				finishCount = new LinkedList<Integer>();
				String className = cls.getClassName();
				legend.add(className);
				
				
				List<ExperimentFinishVO> experimentFinishs = experimentRecordService.getExperimentFinishsByClass(cls.getClassId(), TWO);
				if (!experimentFinishs.isEmpty()) {
					for (ExperimentFinishVO experimentFinish : experimentFinishs) {
						ExperimentInfo info = experimentInfoService.get(experimentFinish.getExperimentInfoId());
						String experimentName = info == null ? "" : info.getExperimentName();
						axis.add(experimentName);
						finishCount.add(experimentFinish.getFinishCount());
					}
				}
				
				// 随机数
				int max=4;
		        int min=1;
		        Random random = new Random();
		        int ranData = random.nextInt(max)%(max-min+1) + min;
				
				series.add(new Series(className,"bar",ranData+"", 0, 0, finishCount));
				
			}
		}
		
		
//		legend.add("1501班");
//		legend.add("1502班");
//		legend.add("1503班");
//		legend.add("1504班");
//		legend.add("1505班");
//		legend.add("1506班");
		
//		List<String> axis=new ArrayList<String>(Arrays.asList(new String[]{"HDFS大数据互动实验","Hive大数据互动实验","HBase大数据互动实验","Spark大数据互动实验","Sqoop大数据互动实验","Flume大数据互动实验"}));
	    
//		List<Series> series=new ArrayList<Series>();
//		series.add(new Series("1501班","bar","差", 0, 0, new ArrayList<Integer>(Arrays.asList(320, 332, 301, 334, 390, 330))));
//		series.add(new Series("1502班","bar","一般", 0, 0, new ArrayList<Integer>(Arrays.asList(120, 132, 101, 134, 90, 230))));
//		series.add(new Series("1503班","bar","一般", 0, 0, new ArrayList<Integer>(Arrays.asList(220, 182, 191, 234, 290, 330))));
//		series.add(new Series("1504班","bar","一般", 0, 0, new ArrayList<Integer>(Arrays.asList(150, 232, 201, 154, 190, 330))));
//		series.add(new Series("1505班","bar","", 0, 0, new ArrayList<Integer>(Arrays.asList(862, 1018, 964, 1026, 1679, 1600))));
//		series.add(new Series("1506班","bar","好", 0, 0, new ArrayList<Integer>(Arrays.asList(620, 732, 701, 734, 1090, 1130))));
	    
		Echarts echarts = new Echarts(legend, axis, series);
		
		Gson gson = new Gson();
		String json = gson.toJson(echarts);
		gson = null;
		
		return json;
	}
	
}
