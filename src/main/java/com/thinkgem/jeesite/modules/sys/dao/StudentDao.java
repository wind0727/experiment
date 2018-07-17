/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Student;

/**
 * 单表生成DAO接口
 * @author wind
 * @version 2018-01-31
 */
@MyBatisDao
public interface StudentDao extends CrudDao<Student> {
	
	public Student findByUser(String userId);
	
}