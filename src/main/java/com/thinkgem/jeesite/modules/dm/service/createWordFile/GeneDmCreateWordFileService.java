/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.service.createWordFile;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.word.GeneWordReport;
import com.thinkgem.jeesite.modules.dm.dao.dmuser.GeneDmUserDao;
import com.thinkgem.jeesite.modules.dm.entity.dmbasegene.GeneDmBaseGene;
import com.thinkgem.jeesite.modules.dm.entity.dmuser.GeneDmUser;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * 糖尿病用户表Service
 *
 * @author wangshuo
 * @version 2017-04-04
 */
@Service
@Transactional(readOnly = true)
public class GeneDmCreateWordFileService {


    public void exportDMWord(WordprocessingMLPackage wordMLPackage, GeneDmBaseGene baseGene, HttpServletRequest request) throws Exception {

        String path = request.getSession().getServletContext().getRealPath("/") + "genedmimage/" + baseGene.getGeneLoci() + "_" + baseGene.getGeneComposite() + ".jpg";

        //位点信息
        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading1",
                baseGene.getGeneLoci());
        //位点图片   todo

        File file = new File(path);
        if (file.exists()) {
//            if(file.exists()) {
//            System.setProperty("java.awt.headless", "false");
            byte[] bytes = GeneWordReport.convertImageToByteArray(file);
            GeneWordReport.addImageToPackage(wordMLPackage, bytes);
        }

        //基因
        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading2",
                "基因\n");
        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Normal",
                baseGene.getGeneLogogram());

        // 用户基因型
        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading2",
                "用户基因型\n");
        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Normal",
                baseGene.getGeneTypeFlag());


        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading2",
                "风险型\n");
        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Normal",
                baseGene.getGeneRpn());


        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading2",
                "相关途径\n");
        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Normal",
                baseGene.getGeneCorrelation());


        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading2",
                "机理阐述\n");
        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Normal",
                baseGene.getGeneMechanism());


        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading2",
                "健康建议\n");
        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Normal",
                baseGene.getGeneHealthSuggest());

        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading2",
                "用药建议\n");
        wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Normal",
                baseGene.getGeneMedicineSuggest());


    }

}