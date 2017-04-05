/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.web.dmuser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dm.entity.dmuser.GeneDmUser;
import com.thinkgem.jeesite.modules.dm.service.dmuser.GeneDmUserService;

/**
 * 糖尿病用户表Controller
 * @author wangshuo
 * @version 2017-04-04
 */
@Controller
@RequestMapping(value = "${adminPath}/dm/dmuser/geneDmUser")
public class GeneDmUserController extends BaseController {

	@Autowired
	private GeneDmUserService geneDmUserService;
	
	@ModelAttribute
	public GeneDmUser get(@RequestParam(required=false) String id) {
		GeneDmUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = geneDmUserService.get(id);
		}
		if (entity == null){
			entity = new GeneDmUser();
		}
		return entity;
	}
	
	@RequiresPermissions("dm:dmuser:geneDmUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(GeneDmUser geneDmUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GeneDmUser> page = geneDmUserService.findPage(new Page<GeneDmUser>(request, response), geneDmUser); 
		model.addAttribute("page", page);
		return "modules/dm/dmuser/geneDmUserList";
	}

	@RequiresPermissions("dm:dmuser:geneDmUser:view")
	@RequestMapping(value = "form")
	public String form(GeneDmUser geneDmUser, Model model) {
		model.addAttribute("geneDmUser", geneDmUser);
		return "modules/dm/dmuser/geneDmUserForm";
	}

	@RequiresPermissions("dm:dmuser:geneDmUser:edit")
	@RequestMapping(value = "save")
	public String save(GeneDmUser geneDmUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, geneDmUser)){
			return form(geneDmUser, model);
		}
		geneDmUserService.save(geneDmUser);
		addMessage(redirectAttributes, "保存用户成功");
		return "redirect:"+Global.getAdminPath()+"/dm/dmuser/geneDmUser/?repage";
	}
	
	@RequiresPermissions("dm:dmuser:geneDmUser:edit")
	@RequestMapping(value = "delete")
	public String delete(GeneDmUser geneDmUser, RedirectAttributes redirectAttributes) {
		geneDmUserService.delete(geneDmUser);
		addMessage(redirectAttributes, "删除用户成功");
		return "redirect:"+Global.getAdminPath()+"/dm/dmuser/geneDmUser/?repage";
	}

}