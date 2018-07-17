package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.City;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 字段类型转换
 * @author ThinkGem
 * @version 2013-03-10
 */
public class CityType {

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		for (City e : UserUtils.getCityList()){
			if (StringUtils.trimToEmpty(val).equals(e.getCityName())){
				return e;
			}
		}
		return null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((City)val).getCityName() != null){
			return ((City)val).getCityName();
		}
		return "";
	}
}
