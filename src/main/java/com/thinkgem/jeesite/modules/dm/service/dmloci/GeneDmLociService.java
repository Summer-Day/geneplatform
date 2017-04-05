/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.service.dmloci;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dm.entity.dmloci.GeneDmLoci;
import com.thinkgem.jeesite.modules.dm.dao.dmloci.GeneDmLociDao;

/**
 * 糖尿病用户基因表Service
 * @author wangshuo
 * @version 2017-04-04
 */
@Service
@Transactional(readOnly = true)
public class GeneDmLociService extends CrudService<GeneDmLociDao, GeneDmLoci> {

	public GeneDmLoci get(String id) {
		return super.get(id);
	}
	
	public List<GeneDmLoci> findList(GeneDmLoci geneDmLoci) {
		return super.findList(geneDmLoci);
	}
	
	public Page<GeneDmLoci> findPage(Page<GeneDmLoci> page, GeneDmLoci geneDmLoci) {
		return super.findPage(page, geneDmLoci);
	}
	
	@Transactional(readOnly = false)
	public void save(GeneDmLoci geneDmLoci) {
		super.save(geneDmLoci);
	}
	
	@Transactional(readOnly = false)
	public void delete(GeneDmLoci geneDmLoci) {
		super.delete(geneDmLoci);
	}
	
}