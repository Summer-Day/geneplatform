/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.service.dmbasegene;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dm.entity.dmbasegene.GeneDmBaseGene;
import com.thinkgem.jeesite.modules.dm.dao.dmbasegene.GeneDmBaseGeneDao;

/**
 * 糖尿病用户基因基础表Service
 * @author wangshuo
 * @version 2017-04-15
 */
@Service
@Transactional(readOnly = true)
public class GeneDmBaseGeneService extends CrudService<GeneDmBaseGeneDao, GeneDmBaseGene> {

	public GeneDmBaseGene get(String id) {
		return super.get(id);
	}
	
	public List<GeneDmBaseGene> findList(GeneDmBaseGene geneDmBaseGene) {
		return super.findList(geneDmBaseGene);
	}
	
	public Page<GeneDmBaseGene> findPage(Page<GeneDmBaseGene> page, GeneDmBaseGene geneDmBaseGene) {
		return super.findPage(page, geneDmBaseGene);
	}
	
	@Transactional(readOnly = false)
	public void save(GeneDmBaseGene geneDmBaseGene) {
		super.save(geneDmBaseGene);
	}
	
	@Transactional(readOnly = false)
	public void delete(GeneDmBaseGene geneDmBaseGene) {
		super.delete(geneDmBaseGene);
	}
	
}