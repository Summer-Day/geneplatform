/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.web.dmbasegene;

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
import com.thinkgem.jeesite.modules.dm.entity.dmbasegene.GeneDmBaseGene;
import com.thinkgem.jeesite.modules.dm.service.dmbasegene.GeneDmBaseGeneService;

/**
 * 糖尿病用户基因基础表Controller
 * @author wangshuo
 * @version 2017-04-15
 */
@Controller
@RequestMapping(value = "${adminPath}/dm/dmbasegene/geneDmBaseGene")
public class GeneDmBaseGeneController extends BaseController {

	@Autowired
	private GeneDmBaseGeneService geneDmBaseGeneService;
	
	@ModelAttribute
	public GeneDmBaseGene get(@RequestParam(required=false) String id) {
		GeneDmBaseGene entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = geneDmBaseGeneService.get(id);
		}
		if (entity == null){
			entity = new GeneDmBaseGene();
		}
		return entity;
	}
	
	@RequiresPermissions("dm:dmbasegene:geneDmBaseGene:view")
	@RequestMapping(value = {"list", ""})
	public String list(GeneDmBaseGene geneDmBaseGene, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GeneDmBaseGene> page = geneDmBaseGeneService.findPage(new Page<GeneDmBaseGene>(request, response), geneDmBaseGene); 
		model.addAttribute("page", page);
		return "modules/dm/dmbasegene/geneDmBaseGeneList";
	}

	@RequiresPermissions("dm:dmbasegene:geneDmBaseGene:view")
	@RequestMapping(value = "form")
	public String form(GeneDmBaseGene geneDmBaseGene, Model model) {
		model.addAttribute("geneDmBaseGene", geneDmBaseGene);
		return "modules/dm/dmbasegene/geneDmBaseGeneForm";
	}

	@RequiresPermissions("dm:dmbasegene:geneDmBaseGene:edit")
	@RequestMapping(value = "save")
	public String save(GeneDmBaseGene geneDmBaseGene, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, geneDmBaseGene)){
			return form(geneDmBaseGene, model);
		}
		geneDmBaseGeneService.save(geneDmBaseGene);
		addMessage(redirectAttributes, "保存基础数据成功");
		return "redirect:"+Global.getAdminPath()+"/dm/dmbasegene/geneDmBaseGene/?repage";
	}
	
	@RequiresPermissions("dm:dmbasegene:geneDmBaseGene:edit")
	@RequestMapping(value = "delete")
	public String delete(GeneDmBaseGene geneDmBaseGene, RedirectAttributes redirectAttributes) {
		geneDmBaseGeneService.delete(geneDmBaseGene);
		addMessage(redirectAttributes, "删除基础数据成功");
		return "redirect:"+Global.getAdminPath()+"/dm/dmbasegene/geneDmBaseGene/?repage";
	}

}