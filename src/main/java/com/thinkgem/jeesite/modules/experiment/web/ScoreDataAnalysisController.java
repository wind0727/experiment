package com.thinkgem.jeesite.modules.experiment.web;

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

@Controller
@RequestMapping(value = "${adminPath}/report/scoreDataAnalysis")
public class ScoreDataAnalysisController extends BaseController {
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
	private ExperimentRecordService experimentRecordService;
	@Autowired
	private ExperimentInfoService experimentInfoService;
	
	@RequiresPermissions("report:socreDataAnalysis:view")
	@RequestMapping(value = {"list", ""})
	public String list(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String defaultGradeId = "";
		List<GradeVO> grades = new ArrayList<GradeVO>();
		List<MajorVO> majors = officeService.getMajorsByType(SIX);
		if(!majors.isEmpty()) {
			MajorVO major = majors.get(0);
			String defaultMajorId = major.getMajorId();
			grades = officeService.getGradesByMajor(defaultMajorId, SEVEN);
			if (!grades.isEmpty()) {
				defaultGradeId = grades.get(0).getGradeId();
			}
		}
		
		
		model.addAttribute("majors", majors);
		model.addAttribute("defaultGradeId", defaultGradeId);
		
		return "modules/experiment/scoreDataAnalysis";
	}
	
	@RequestMapping(value="/experimentMajorAvgScoreData",method=RequestMethod.GET)
	@ResponseBody
	public String experimentMajorAvgScoreData(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		List<String> axis=new LinkedList<String>();
		List<Series> series=new ArrayList<Series>();
		List<String> legend=new ArrayList<String>();
		List<Double> avgScoreList = new LinkedList<Double>();
		Map<String, List<Double>> avgScoreMap = new LinkedHashMap<String, List<Double>>();
		List<GradeVO> grades = new ArrayList<GradeVO>();
		List<MajorVO> majors = officeService.getMajorsByType(SIX);
		if (!majors.isEmpty()) {
			
			for (MajorVO major : majors) {
				avgScoreList = new LinkedList<Double>();
				
				axis.add(major.getMajorName());
				grades = officeService.getGradesByMajor(major.getMajorId(), SEVEN);
				for (GradeVO grade : grades) {
					
					double avgScore = experimentRecordService.getExperimentAvgScoreByGrade(grade.getGradeId(), TWO);
					String key = grade.getGradeName();
					if (avgScoreMap.containsKey(key)) {
						
						avgScoreList = avgScoreMap.get(key);
						avgScoreList.add(avgScore);
						
					} else {
						
						avgScoreList = new LinkedList<Double>();
						avgScoreList.add(avgScore);
						avgScoreMap.put(key, avgScoreList);
						
					}
				}
			}
			
			if (!avgScoreMap.isEmpty()) {
				Series tmpSeries = null;
				Set<Entry<String, List<Double>>> entrySet = avgScoreMap.entrySet();
				for (Entry<String, List<Double>> entry : entrySet) {
					String key = entry.getKey();
					legend.add(key);
					tmpSeries = new Series(key, "bar", "a", null, entry.getValue());
					tmpSeries.setCoordinateSystem("polar");
					series.add(tmpSeries);
					tmpSeries = null;
				}
				
			}
			
		}
	    
		Echarts echarts = new Echarts(legend, axis, series);
		
		Gson gson = new Gson();
		String json = gson.toJson(echarts);
		gson = null;
		echarts = null;
		
		return json;
	}
	
	@RequestMapping(value="/experimentGradeAvgScoreData",method=RequestMethod.GET)
	@ResponseBody
	public String experimentGradeAvgScoreData(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		String majorId = request.getParameter("majorId");
		String gradeId = request.getParameter("gradeId");
		
		List<String> legend = new LinkedList<String>();
		List<Integer> finishCount = new LinkedList<Integer>();
		List<Double> avgCount = new LinkedList<Double>();
		
		legend.add("平均得分");
		legend.add("完成次数");
		
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
		
		series.add(new Series("平均得分",BAR,"", 0, 0, avgCount));
		series.add(new Series("完成次数",LINE,"", 0, 1, finishCount));
	    
		Echarts echarts = new Echarts(legend, axis, series);
		
		Gson gson = new Gson();
		String json = gson.toJson(echarts);
		gson = null;
		
		return json;
	}
	@RequestMapping(value="/experimentClassAvgScoreData",method=RequestMethod.GET)
	@ResponseBody
	public String experimentClassAvgScoreData(ExperimentAppointment experimentAppointment, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		List<String> legend=new LinkedList<String>();
		
		String majorId = request.getParameter("majorId");
		String gradeId = request.getParameter("gradeId");
		
		List<String> axis=new LinkedList<String>();
		List<Series> series=new ArrayList<Series>();
		List<Double> avgScoreCount = new LinkedList<Double>();
		List<ClassVO> classes = officeService.getClassByGrade(gradeId, EIGHT);
		if(!classes.isEmpty()) {
			for (ClassVO cls : classes) {
				avgScoreCount = new LinkedList<Double>();
				String className = cls.getClassName();
				legend.add(className);
				
				
				List<ExperimentFinishVO> experimentFinishs = experimentRecordService.getExperimentFinishsByClass(cls.getClassId(), TWO);
				if (!experimentFinishs.isEmpty()) {
					for (ExperimentFinishVO experimentFinish : experimentFinishs) {
						ExperimentInfo info = experimentInfoService.get(experimentFinish.getExperimentInfoId());
						String experimentName = info == null ? "" : info.getExperimentName();
						axis.add(experimentName);
						avgScoreCount.add(experimentFinish.getAvgScore());
					}
				}
				
				// 随机数
				int max=4;
		        int min=1;
		        Random random = new Random();
		        int ranData = random.nextInt(max)%(max-min+1) + min;
				
				series.add(new Series(className,"bar",ranData+"", 0, 0, avgScoreCount));
				
			}
		}
	    
		Echarts echarts = new Echarts(legend, axis, series);
		
		Gson gson = new Gson();
		String json = gson.toJson(echarts);
		gson = null;
		
		return json;
	}

}
