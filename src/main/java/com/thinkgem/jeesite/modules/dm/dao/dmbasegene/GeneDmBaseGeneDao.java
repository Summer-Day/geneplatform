/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.dao.dmbasegene;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dm.entity.dmbasegene.GeneDmBaseGene;

/**
 * 糖尿病用户基因基础表DAO接口
 * @author wangshuo
 * @version 2017-04-15
 */
@MyBatisDao
public interface GeneDmBaseGeneDao extends CrudDao<GeneDmBaseGene> {
	
}