/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.entity.dmloci;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 糖尿病用户基因表Entity
 * @author wangshuo
 * @version 2017-04-04
 */
public class GeneDmLoci extends DataEntity<GeneDmLoci> {
	
	private static final long serialVersionUID = 1L;
	private String loci;		// 基因位点
	private String geneType;		// 基因类型
	private String tubeId;		// 试管编码
	
	public GeneDmLoci() {
		super();
	}

	public GeneDmLoci(String id){
		super(id);
	}

	@Length(min=1, max=100, message="基因位点长度必须介于 1 和 100 之间")
	@ExcelField(title="位点")
	public String getLoci() {
		return loci;
	}

	public void setLoci(String loci) {
		this.loci = loci;
	}
	
	@Length(min=1, max=64, message="基因类型长度必须介于 1 和 64 之间")
	@ExcelField(title="基因类型")
	public String getGeneType() {
		return geneType;
	}

	public void setGeneType(String geneType) {
		this.geneType = geneType;
	}
	
	@Length(min=1, max=100, message="试管编码长度必须介于 1 和 100 之间")
	@ExcelField(title="试管编码")
	public String getTubeId() {
		return tubeId;
	}

	public void setTubeId(String tubeId) {
		this.tubeId = tubeId;
	}
	
}