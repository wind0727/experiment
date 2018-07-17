
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.experiment.vo.TeacherVO;
import com.thinkgem.jeesite.modules.sys.entity.Teacher;

/**
 * 单表生成DAO接口
 * @author wind
 * @version 2018-01-31
 */
@MyBatisDao
public interface TeacherDao extends CrudDao<Teacher> {
	
	public Teacher findByUser(String userId);
	
	public List<TeacherVO> getTeacherVOByOffice(String officeId);
	
}