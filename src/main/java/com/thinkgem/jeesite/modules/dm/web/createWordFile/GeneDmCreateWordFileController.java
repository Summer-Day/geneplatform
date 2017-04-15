package com.thinkgem.jeesite.modules.dm.web.createWordFile;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dm.entity.dmloci.GeneDmLoci;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基因word生成
 *
 * @author wangshuo
 * @version 2017-04-15
 */
@Controller
@RequestMapping(value = "${adminPath}/dm/createWordFile/geneDmCreateWordFile")
public class GeneDmCreateWordFileController extends BaseController {

    @RequiresPermissions("dm:createWordFile:geneDmCreateWordFile:view")
    @RequestMapping(value = {"list", ""})
    public String list(GeneDmLoci geneDmLoci, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/dm/createwordfile/geneDmCreateWordFilePage";
    }


    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String importFile(String textInput, RedirectAttributes redirectAttributes) {
        addMessage(redirectAttributes, "你选择的是" + textInput);
//        return "modules/dm/createwordfile/geneDmCreateWordFilePage";
        return "redirect:" + adminPath + "/dm/createWordFile/geneDmCreateWordFile/list?repage";
    }
}