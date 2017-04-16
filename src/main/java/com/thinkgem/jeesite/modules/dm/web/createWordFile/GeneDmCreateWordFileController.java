package com.thinkgem.jeesite.modules.dm.web.createWordFile;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dm.entity.dmbasegene.GeneDmBaseGene;
import com.thinkgem.jeesite.modules.dm.entity.dmloci.GeneDmLoci;
import com.thinkgem.jeesite.modules.dm.service.dmbasegene.GeneDmBaseGeneService;
import com.thinkgem.jeesite.modules.dm.service.dmloci.GeneDmLociService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 基因word生成
 *
 * @author wangshuo
 * @version 2017-04-15
 */
@Controller
@RequestMapping(value = "${adminPath}/dm/createWordFile/geneDmCreateWordFile")
public class GeneDmCreateWordFileController extends BaseController {

    //用户基因的controller
    @Autowired
    private GeneDmLociService geneDmLociService;
    //数据基础表基因的controller
    @Autowired
    private GeneDmBaseGeneService geneDmBaseGeneService;

    @RequiresPermissions("dm:createWordFile:geneDmCreateWordFile:view")
    @RequestMapping(value = {"list", ""})
    public String list(GeneDmLoci geneDmLoci, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/dm/createwordfile/geneDmCreateWordFilePage";
    }


    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String importFile(GeneDmLoci geneDmLoci, RedirectAttributes redirectAttributes) {
        if(StringUtils.isEmpty(geneDmLoci.getTubeId())) {
            addMessage(redirectAttributes, "请输入非空试管编号");
            return "redirect:" + adminPath + "/dm/createWordFile/geneDmCreateWordFile/list?repage";
        }
        ArrayList<GeneDmBaseGene> result = new ArrayList<GeneDmBaseGene>();
        List<GeneDmLoci> list = geneDmLociService.findList(geneDmLoci);
        if(list == null || list.size() == 0) {
            addMessage(redirectAttributes, "您输入的试管编码没有找到对应的用户信息，请确认用户基因表中是否存在输入的试管编码"+geneDmLoci.getTubeId());
            return "redirect:" + adminPath + "/dm/createWordFile/geneDmCreateWordFile/list?repage";
        }
        StringBuilder failureMsg = new StringBuilder();
        for(GeneDmLoci mGeneDmLoci : list) {
            GeneDmBaseGene geneDmBaseGene = new GeneDmBaseGene();
            geneDmBaseGene.setGeneLoci(mGeneDmLoci.getLoci());
            geneDmBaseGene.setGeneType(mGeneDmLoci.getGeneType());
            List<GeneDmBaseGene> baseGeneList = geneDmBaseGeneService.findList(geneDmBaseGene);
            if(baseGeneList == null || baseGeneList.size() == 0) {
                failureMsg.append("<br/>用户基因位点: " + mGeneDmLoci.getLoci() +"基因类型: "+mGeneDmLoci.getGeneType() +"未匹配到基础数据！");
                continue;
            } else {
                result.add(baseGeneList.get(0));
            }
        }

        if(result.size()>0) {
            //do something
            failureMsg.append("<br/>成功生成数据："+result.size()+"页报表");
        } else {
            failureMsg.append("<br/>报表生成失败！");
        }
        addMessage(redirectAttributes, ""+failureMsg);
        return "redirect:" + adminPath + "/dm/createWordFile/geneDmCreateWordFile/list?repage";
    }
}