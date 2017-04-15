/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.entity.dmbasegene;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 糖尿病用户基因基础表Entity
 * @author wangshuo
 * @version 2017-04-15
 */
public class GeneDmBaseGene extends DataEntity<GeneDmBaseGene> {
	
	private static final long serialVersionUID = 1L;
	private String sickness;		// 疾病名称
	private String geneName;		// 基因名称
	private String geneLogogram;		// 基因简写
	private String geneLoci;		// 基因位点
	private String geneType;		// 基因型
	private String geneComposite;		// 基因组合
	private String geneTypeFlag;		// 基因类型
	private String geneLiterature;		// 相关通路
	private String geneRpn;		// 风险系数
	private String geneCorrelation;		// 相关途径
	private String geneHealthSuggest;		// 健康建议
	private String geneMedicineSuggest;		// 用药建议
	private String geneMechanism;		// 机理阐述
	private String geneDetection;		// 相关检测
	
	public GeneDmBaseGene() {
		super();
	}

	public GeneDmBaseGene(String id){
		super(id);
	}

	@Length(min=1, max=100, message="疾病名称长度必须介于 1 和 100 之间")
	public String getSickness() {
		return sickness;
	}

	public void setSickness(String sickness) {
		this.sickness = sickness;
	}
	
	@Length(min=1, max=100, message="基因名称长度必须介于 1 和 100 之间")
	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}
	
	@Length(min=1, max=50, message="基因简写长度必须介于 1 和 50 之间")
	public String getGeneLogogram() {
		return geneLogogram;
	}

	public void setGeneLogogram(String geneLogogram) {
		this.geneLogogram = geneLogogram;
	}
	
	@Length(min=1, max=100, message="基因位点长度必须介于 1 和 100 之间")
	public String getGeneLoci() {
		return geneLoci;
	}

	public void setGeneLoci(String geneLoci) {
		this.geneLoci = geneLoci;
	}
	
	@Length(min=1, max=20, message="基因型长度必须介于 1 和 20 之间")
	public String getGeneType() {
		return geneType;
	}

	public void setGeneType(String geneType) {
		this.geneType = geneType;
	}
	
	@Length(min=1, max=20, message="基因组合长度必须介于 1 和 20 之间")
	public String getGeneComposite() {
		return geneComposite;
	}

	public void setGeneComposite(String geneComposite) {
		this.geneComposite = geneComposite;
	}
	
	@Length(min=1, max=20, message="基因类型长度必须介于 1 和 20 之间")
	public String getGeneTypeFlag() {
		return geneTypeFlag;
	}

	public void setGeneTypeFlag(String geneTypeFlag) {
		this.geneTypeFlag = geneTypeFlag;
	}
	
	@Length(min=0, max=1000, message="相关通路长度必须介于 0 和 1000 之间")
	public String getGeneLiterature() {
		return geneLiterature;
	}

	public void setGeneLiterature(String geneLiterature) {
		this.geneLiterature = geneLiterature;
	}
	
	@Length(min=1, max=100, message="风险系数长度必须介于 1 和 100 之间")
	public String getGeneRpn() {
		return geneRpn;
	}

	public void setGeneRpn(String geneRpn) {
		this.geneRpn = geneRpn;
	}
	
	@Length(min=0, max=1000, message="相关途径长度必须介于 0 和 1000 之间")
	public String getGeneCorrelation() {
		return geneCorrelation;
	}

	public void setGeneCorrelation(String geneCorrelation) {
		this.geneCorrelation = geneCorrelation;
	}
	
	@Length(min=0, max=1000, message="健康建议长度必须介于 0 和 1000 之间")
	public String getGeneHealthSuggest() {
		return geneHealthSuggest;
	}

	public void setGeneHealthSuggest(String geneHealthSuggest) {
		this.geneHealthSuggest = geneHealthSuggest;
	}
	
	@Length(min=0, max=1000, message="用药建议长度必须介于 0 和 1000 之间")
	public String getGeneMedicineSuggest() {
		return geneMedicineSuggest;
	}

	public void setGeneMedicineSuggest(String geneMedicineSuggest) {
		this.geneMedicineSuggest = geneMedicineSuggest;
	}
	
	@Length(min=0, max=1000, message="机理阐述长度必须介于 0 和 1000 之间")
	public String getGeneMechanism() {
		return geneMechanism;
	}

	public void setGeneMechanism(String geneMechanism) {
		this.geneMechanism = geneMechanism;
	}
	
	@Length(min=0, max=1000, message="相关检测长度必须介于 0 和 1000 之间")
	public String getGeneDetection() {
		return geneDetection;
	}

	public void setGeneDetection(String geneDetection) {
		this.geneDetection = geneDetection;
	}
	
}