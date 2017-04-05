/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.dao.dmuser;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dm.entity.dmuser.GeneDmUser;

/**
 * 糖尿病用户表DAO接口
 * @author wangshuo
 * @version 2017-04-04
 */
@MyBatisDao
public interface GeneDmUserDao extends CrudDao<GeneDmUser> {
	
}