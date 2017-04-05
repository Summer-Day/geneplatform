/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.entity.dmuser;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 糖尿病用户表Entity
 * @author wangshuo
 * @version 2017-04-04
 */
public class GeneDmUser extends DataEntity<GeneDmUser> {
	
	private static final long serialVersionUID = 1L;
	private String userName;		// 用户姓名
	private String userAge;		// 用户年龄
	private String userTele;		// 用户电话
	private String tubeId;		// 试管编号
	private String medicalHistory;		// 病史
	
	public GeneDmUser() {
		super();
	}

	public GeneDmUser(String id){
		super(id);
	}

	@Length(min=0, max=100, message="用户姓名长度必须介于 0 和 100 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=64, message="用户年龄长度必须介于 0 和 64 之间")
	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	
	@Length(min=0, max=64, message="用户电话长度必须介于 0 和 64 之间")
	public String getUserTele() {
		return userTele;
	}

	public void setUserTele(String userTele) {
		this.userTele = userTele;
	}
	
	@Length(min=0, max=100, message="试管编号长度必须介于 0 和 100 之间")
	public String getTubeId() {
		return tubeId;
	}

	public void setTubeId(String tubeId) {
		this.tubeId = tubeId;
	}
	
	@Length(min=0, max=500, message="病史长度必须介于 0 和 500 之间")
	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	
}