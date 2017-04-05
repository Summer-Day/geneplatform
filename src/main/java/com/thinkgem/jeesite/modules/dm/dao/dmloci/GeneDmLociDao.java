/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.dao.dmloci;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dm.entity.dmloci.GeneDmLoci;

/**
 * 糖尿病用户基因表DAO接口
 * @author wangshuo
 * @version 2017-04-04
 */
@MyBatisDao
public interface GeneDmLociDao extends CrudDao<GeneDmLoci> {
	
}