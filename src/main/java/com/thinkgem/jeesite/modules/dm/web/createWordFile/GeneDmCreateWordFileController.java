package com.thinkgem.jeesite.modules.dm.web.createWordFile;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtilsNew;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.word.GeneWordReport;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dm.entity.dmbasegene.GeneDmBaseGene;
import com.thinkgem.jeesite.modules.dm.entity.dmloci.GeneDmLoci;
import com.thinkgem.jeesite.modules.dm.service.createWordFile.GeneDmCreateWordFileService;
import com.thinkgem.jeesite.modules.dm.service.dmbasegene.GeneDmBaseGeneService;
import com.thinkgem.jeesite.modules.dm.service.dmloci.GeneDmLociService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.STBrType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    GeneDmCreateWordFileService geneGeneDmCreateWordFileService;

    @RequiresPermissions("dm:createWordFile:geneDmCreateWordFile:view")
    @RequestMapping(value = {"list", ""})
    public String list(GeneDmLoci geneDmLoci, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/dm/createwordfile/geneDmCreateWordFilePage";
    }


    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String importFile(GeneDmLoci geneDmLoci, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (StringUtils.isEmpty(geneDmLoci.getTubeId())) {
            addMessage(redirectAttributes, "请输入非空试管编号");
            return "redirect:" + adminPath + "/dm/createWordFile/geneDmCreateWordFile/list?repage";
        }
        ArrayList<GeneDmBaseGene> result = new ArrayList<GeneDmBaseGene>();
        List<GeneDmLoci> list = geneDmLociService.findList(geneDmLoci);
        if (list == null || list.size() == 0) {
            addMessage(redirectAttributes, "您输入的试管编码没有找到对应的用户信息，请确认用户基因表中是否存在输入的试管编码" + geneDmLoci.getTubeId());
            return "redirect:" + adminPath + "/dm/createWordFile/geneDmCreateWordFile/list?repage";
        }
        StringBuilder failureMsg = new StringBuilder();
        String fileName = "xx";
        try {
            WordprocessingMLPackage wordMLPackage = null;
            wordMLPackage = WordprocessingMLPackage.createPackage();
//            for (GeneDmLoci mGeneDmLoci : list) {
            for (int i = 0; i < list.size(); i++) {
                GeneDmLoci mGeneDmLoci = list.get(i);
                GeneDmBaseGene geneDmBaseGene = new GeneDmBaseGene();
                geneDmBaseGene.setGeneLoci(mGeneDmLoci.getLoci());
                geneDmBaseGene.setGeneComposite(mGeneDmLoci.getGeneType());
                List<GeneDmBaseGene> baseGeneList = geneDmBaseGeneService.findList(geneDmBaseGene);
                if (baseGeneList == null || baseGeneList.size() == 0) {
                    failureMsg.append("<br/>用户基因位点: " + mGeneDmLoci.getLoci() + "基因类型: " + mGeneDmLoci.getGeneType() + "未匹配到基础数据！");
                    continue;
                } else {
                    result.add(baseGeneList.get(0));
                    org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
                    GeneWordReport.alterStyleSheet(wordMLPackage);
                    geneGeneDmCreateWordFileService.exportDMWord(wordMLPackage, baseGeneList.get(0), request);
                    logger.debug("试管编码是：" + geneDmLoci.getTubeId() + "位点：" + mGeneDmLoci.getLoci() + "基因类型是" + mGeneDmLoci.getGeneType() + "报表生成成功");
                    if (i != list.size() - 1) {
                        GeneWordReport.addPageBreak(wordMLPackage, factory, STBrType.PAGE);
                    }
                }
            }
            fileName = geneDmLoci.getTubeId() + "_" + DateUtilsNew.getCurrentTime("yyyy-MM-dd") + ".docx";

            String filePath = Global.getreateWordPath() + "/" + fileName;

            wordMLPackage.save(new java.io.File(filePath));
        } catch (Exception e) {
            logger.debug("原因：" + e.getMessage());
            e.printStackTrace();
        }

        if (result.size() > 0) {
            //do something
            failureMsg.append("<br/>成功生成数据：" + result.size() + "页报表" + "<br/> <a href=downloadwordfile?filename=" + fileName + "> 下载文件" + fileName + " </a>");
        } else {
            failureMsg.append("<br/>报表生成失败！");
        }
        addMessage(redirectAttributes, "" + failureMsg);
        return "redirect:" + adminPath + "/dm/createWordFile/geneDmCreateWordFile/list?repage";
    }

    @RequestMapping(value = "downloadwordfile", method = RequestMethod.GET)
    public String downLoadWordFile(String filename, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (StringUtils.isEmpty(filename)) {
            addMessage(redirectAttributes, "获取文件名失败");
            return "redirect:" + adminPath + "/dm/createWordFile/geneDmCreateWordFile/list?repage";
        }
        String filePath = Global.getreateWordPath() + "/" + filename;
        ServletOutputStream out = null;
        InputStream input = null;
        StringBuilder failureMsg = new StringBuilder();
        try {

            response.setContentType("application/msword");
            //response.setContentType("images/x-dcx");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            File resFile = new File(filePath);
            input = new FileInputStream(resFile);
            out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int i = 0;
            while ((i = input.read(buffer)) != -1) {
                out.write(buffer, 0, i);
            }
            failureMsg.append("<br/>下载报表成功！");
        } catch (Exception e) {
            failureMsg.append("<br/>下载报表失败！错误信息" + e.getMessage());
            e.printStackTrace();
        } finally {
            input.close();
            out.flush();
            out.close();
        }

        return "redirect:" + adminPath + "/dm/createWordFile/geneDmCreateWordFile/list?repage";
    }


}