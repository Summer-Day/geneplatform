/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.service.dmuser;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dm.entity.dmuser.GeneDmUser;
import com.thinkgem.jeesite.modules.dm.dao.dmuser.GeneDmUserDao;

/**
 * 糖尿病用户表Service
 * @author wangshuo
 * @version 2017-04-04
 */
@Service
@Transactional(readOnly = true)
public class GeneDmUserService extends CrudService<GeneDmUserDao, GeneDmUser> {

	public GeneDmUser get(String id) {
		return super.get(id);
	}
	
	public List<GeneDmUser> findList(GeneDmUser geneDmUser) {
		return super.findList(geneDmUser);
	}
	
	public Page<GeneDmUser> findPage(Page<GeneDmUser> page, GeneDmUser geneDmUser) {
		return super.findPage(page, geneDmUser);
	}
	
	@Transactional(readOnly = false)
	public void save(GeneDmUser geneDmUser) {
		super.save(geneDmUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(GeneDmUser geneDmUser) {
		super.delete(geneDmUser);
	}
	
}