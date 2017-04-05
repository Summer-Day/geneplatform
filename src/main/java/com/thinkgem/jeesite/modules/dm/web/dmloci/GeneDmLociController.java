/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.web.dmloci;

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
import com.thinkgem.jeesite.modules.dm.entity.dmloci.GeneDmLoci;
import com.thinkgem.jeesite.modules.dm.service.dmloci.GeneDmLociService;

/**
 * 糖尿病用户基因表Controller
 * @author wangshuo
 * @version 2017-04-04
 */
@Controller
@RequestMapping(value = "${adminPath}/dm/dmloci/geneDmLoci")
public class GeneDmLociController extends BaseController {

	@Autowired
	private GeneDmLociService geneDmLociService;
	
	@ModelAttribute
	public GeneDmLoci get(@RequestParam(required=false) String id) {
		GeneDmLoci entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = geneDmLociService.get(id);
		}
		if (entity == null){
			entity = new GeneDmLoci();
		}
		return entity;
	}
	
	@RequiresPermissions("dm:dmloci:geneDmLoci:view")
	@RequestMapping(value = {"list", ""})
	public String list(GeneDmLoci geneDmLoci, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GeneDmLoci> page = geneDmLociService.findPage(new Page<GeneDmLoci>(request, response), geneDmLoci); 
		model.addAttribute("page", page);
		return "modules/dm/dmloci/geneDmLociList";
	}

	@RequiresPermissions("dm:dmloci:geneDmLoci:view")
	@RequestMapping(value = "form")
	public String form(GeneDmLoci geneDmLoci, Model model) {
		model.addAttribute("geneDmLoci", geneDmLoci);
		return "modules/dm/dmloci/geneDmLociForm";
	}

	@RequiresPermissions("dm:dmloci:geneDmLoci:edit")
	@RequestMapping(value = "save")
	public String save(GeneDmLoci geneDmLoci, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, geneDmLoci)){
			return form(geneDmLoci, model);
		}
		geneDmLociService.save(geneDmLoci);
		addMessage(redirectAttributes, "保存用户基因添加成功");
		return "redirect:"+Global.getAdminPath()+"/dm/dmloci/geneDmLoci/?repage";
	}
	
	@RequiresPermissions("dm:dmloci:geneDmLoci:edit")
	@RequestMapping(value = "delete")
	public String delete(GeneDmLoci geneDmLoci, RedirectAttributes redirectAttributes) {
		geneDmLociService.delete(geneDmLoci);
		addMessage(redirectAttributes, "删除用户基因添加成功");
		return "redirect:"+Global.getAdminPath()+"/dm/dmloci/geneDmLoci/?repage";
	}

}