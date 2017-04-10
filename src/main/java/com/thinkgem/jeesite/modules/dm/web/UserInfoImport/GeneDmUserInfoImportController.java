/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dm.web.UserInfoImport;

import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.ExcelReadHelper;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dm.entity.dmloci.GeneDmLoci;
import com.thinkgem.jeesite.modules.dm.service.UserInfoImport.GeneDmUserInfoImportService;
import com.thinkgem.jeesite.modules.dm.service.dmloci.GeneDmLociService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 用户基因导入Controller
 *
 * @author wangshuo
 * @version 2017-04-04
 */
@Controller
@RequestMapping(value = "${adminPath}/dm/UserInfoImport/geneUserInfoImport")
public class GeneDmUserInfoImportController extends BaseController {


    private static Logger logger = LoggerFactory.getLogger(GeneDmUserInfoImportController.class);


    @Autowired
    private GeneDmUserInfoImportService geneDmUserInfoImportService;
    @Autowired
    private GeneDmLociService geneDmLociService;

    @RequiresPermissions("dm:UserInfoImport:geneUserInfoImport:view")
    @RequestMapping(value = {"list", ""})
    public String list(GeneDmLoci geneDmLoci, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/dm/UserInfoImport/geneDmUserInfoImportMain";
    }

    /**
     * 导入用户数据
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
//    @RequiresPermissions("dm:UserInfoImport:geneUserInfoImport:edit")
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        logger.debug(((CommonsMultipartFile) file).getStorageDescription());
//		if(Global.isDemoMode()){
//			addMessage(redirectAttributes, "演示模式，不允许操作！");
//			return "redirect:" + adminPath + "/sys/user/list?repage";
//		}

        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<GeneDmLoci> list = ei.getDataList(GeneDmLoci.class);
            for (GeneDmLoci user : list) {
                try {
                    geneDmLociService.save(user);
                    successNum++;
                } catch (ConstraintViolationException ex) {
                    failureMsg.append("<br/>试管编码: " + user.getTubeId()
                            + " 基因位点: " + user.getLoci() +
                            "基因类型 :" + user.getGeneType() + " 导入失败!：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                    for (String message : messageList) {
                        failureMsg.append(message + "; ");
                        failureNum++;
                    }
                } catch (Exception ex) {
                    failureMsg.append("<br/>试管编码: " + user.getTubeId()
                            + " 基因位点: " + user.getLoci() +
                            "基因类型: " + user.getGeneType() + " 导入失败!：");
                }
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条用户，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条用户" + failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入用户失败！失败信息：" + e.getMessage());
        }
//        return "redirect:" + adminPath + "/dm/dmloci/geneDmLoci/list?repage";
        return "redirect:" + adminPath + "/dm/UserInfoImport/geneUserInfoImport/list?repage";
    }
}