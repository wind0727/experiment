package com.thinkgem.jeesite.modules.echarts;
import java.util.List;
import java.util.Map;

public class Series {
	private String name;
    private String type;
    private Boolean smooth;
    private String stack;
    private Map<String, Map<String, Object>> label;
    private Integer xAxisIndex;//0表示使用:x下坐标; 1表示使用：x上坐标
    private Integer yAxisIndex;//0表示使用，y左坐标; 1表示使用：y右坐标
    private List<? extends Object> data;
    private String coordinateSystem;

    public Series(String name, String type,Integer xAxisIndex, Integer yAxisIndex, List<? extends Object> data) {
        this.name = name;
        this.type = type;
        this.smooth = true;
        this.xAxisIndex = xAxisIndex;
        this.yAxisIndex = yAxisIndex;
        this.data = data;
    }
    
    public Series(String name, String type,String stack,Integer xAxisIndex, Integer yAxisIndex,List<? extends Object> data) {
        this.name = name;
        this.type = type;
        this.smooth = true;
        this.stack = stack;
        this.xAxisIndex = xAxisIndex;
        this.yAxisIndex = yAxisIndex;
        this.data = data;
    }
    
    public Series(String name, String type,String stack,Map<String, Map<String, Object>> label,List<? extends Object> data) {
        this.name = name;
        this.type = type;
        this.smooth = true;
        this.stack = stack;
        this.label = label;
        this.data = data;
    }
    
//    public Series(String name, String type, String coordinateSystem, String stack, List<? extends Object> data) {
//		this.name = name;
//		this.type = type;
//		this.stack = stack;
//		this.data = data;
//		this.coordinateSystem = coordinateSystem;
//	}

	public Series(String name,List<? extends Object> data) { 
    	this.name = name;
        this.data = data;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getxAxisIndex() {
		return xAxisIndex;
	}

	public void setxAxisIndex(Integer xAxisIndex) {
		this.xAxisIndex = xAxisIndex;
	}

	public Integer getyAxisIndex() {
		return yAxisIndex;
	}

	public void setyAxisIndex(Integer yAxisIndex) {
		this.yAxisIndex = yAxisIndex;
	}

	public List<? extends Object> getData() {
		return data;
	}

	public void setData(List<? extends Object> data) {
		this.data = data;
	}

	public Map<String, Map<String, Object>> getLabel() {
		return label;
	}

	public void setLabel(Map<String, Map<String, Object>> label) {
		this.label = label;
	}

	public String getCoordinateSystem() {
		return coordinateSystem;
	}

	public void setCoordinateSystem(String coordinateSystem) {
		this.coordinateSystem = coordinateSystem;
	}
    
}