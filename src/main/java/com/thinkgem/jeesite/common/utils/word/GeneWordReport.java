package com.thinkgem.jeesite.common.utils.word;

import org.apache.fop.fo.flow.Inline;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.openpackaging.parts.WordprocessingML.StyleDefinitionsPart;
import org.docx4j.wml.*;

import java.io.*;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by wangshuo on 17/4/22.
 */
public class GeneWordReport {
    public static void alterStyleSheet(WordprocessingMLPackage wordMLPackage) {
        StyleDefinitionsPart styleDefinitionsPart =
                wordMLPackage.getMainDocumentPart().getStyleDefinitionsPart();
        Styles styles = styleDefinitionsPart.getJaxbElement();

        List<Style> stylesList = styles.getStyle();
        for (Style style : stylesList) {
            if (style.getStyleId().equals("Normal")) {
                alterNormalStyle(style);
            } else if (style.getStyleId().equals("Heading2")) {
                alterHeading2Style(style);
            } else if (style.getStyleId().equals("Heading3") ||
                    style.getStyleId().equals("Title") ||
                    style.getStyleId().equals("Subtitle")) {
                getRunPropertiesAndRemoveThemeInfo(style);
            } else if (style.getStyleId().equals("Heading1")) {
                //设置标题1
                alterTitleOne(style);
            }
        }
    }

    /**
     * 正文
     * @param style
     */
    private static void alterNormalStyle(Style style) {
        // we want to change (or remove) almost all the run properties of the
        // normal style, so we create a new one.
        RPr rpr = new RPr();
        changeFontToArial(rpr);
        changeFontSize(rpr, 21);
        DocxUtils.setFontColor(rpr,"#000000");
        style.setRPr(rpr);
    }

    /**
     * 设标题一样式
     *
     * @param style
     */
    private static void alterTitleOne(Style style) {
        // we want to change (or remove) almost all the run properties of the
        // normal style, so we create a new one.
        RPr rpr = new RPr();
        changeFontToArial(rpr);
        changeFontSize(rpr, 48);
        DocxUtils.setFontColor(rpr,"#000000");
        DocxUtils.addRPrBoldStyle(rpr);
        style.setRPr(rpr);
    }

    private static void changeFontToArial(RPr runProperties) {
        RFonts runFont = new RFonts();
        runFont.setAscii("Arial");
        runFont.setHAnsi("Arial");
        runProperties.setRFonts(runFont);
    }

    private static void changeFontSize(RPr runProperties, int fontSize) {
        HpsMeasure size = new HpsMeasure();
        size.setVal(BigInteger.valueOf(fontSize));
        runProperties.setSz(size);
    }

    /**
     * 标题2
     * @param style
     */
    private static void alterHeading2Style(Style style) {
        RPr rpr = getRunPropertiesAndRemoveThemeInfo(style);
        removeBoldStyle(rpr);
        changeFontSize(rpr, 32);
        DocxUtils.addRPrBoldStyle(rpr);
        DocxUtils.setFontColor(rpr,"#000000");
//        addUnderline(rpr); 下划线
    }


    private static void addUnderline(RPr runProperties) {
        U underline = new U();
        underline.setVal(UnderlineEnumeration.SINGLE);
        runProperties.setU(underline);
    }

    private static void removeBoldStyle(RPr runProperties) {
        runProperties.getB().setVal(false);
    }


    private static RPr getRunPropertiesAndRemoveThemeInfo(Style style) {
        // We only want to change some settings, so we get the existing run
        // properties from the style.
        RPr rpr = style.getRPr();
        removeThemeFontInformation(rpr);
        return rpr;
    }

    private static void removeThemeFontInformation(RPr runProperties) {
        runProperties.getRFonts().setAsciiTheme(null);
        runProperties.getRFonts().setHAnsiTheme(null);
    }

    /**
     * 将图片从文件对象转换成字节数组.
     *
     * @param file  将要转换的文件
     * @return      包含图片字节数据的字节数组
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static byte[] convertImageToByteArray(File file)
            throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(file );
        long length = file.length();
        // 不能使用long类型创建数组, 需要用int类型.
        if (length > Integer.MAX_VALUE) {
            System.out.println("File too large!!");
        }
        byte[] bytes = new byte[(int)length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
        // 确认所有的字节都没读取
        if (offset < bytes.length) {
            System.out.println("Could not completely read file " +file.getName());
        }
        is.close();
        return bytes;
    }
    /**
     *  Docx4j拥有一个由字节数组创建图片部件的工具方法, 随后将其添加到给定的包中. 为了能将图片添加
     *  到一个段落中, 我们需要将图片转换成内联对象. 这也有一个方法, 方法需要文件名提示, 替换文本,
     *  两个id标识符和一个是嵌入还是链接到的指示作为参数.
     *  一个id用于文档中绘图对象不可见的属性, 另一个id用于图片本身不可见的绘制属性. 最后我们将内联
     *  对象添加到段落中并将段落添加到包的主文档部件.
     *
     *  @param wordMLPackage 要添加图片的包
     *  @param bytes         图片对应的字节数组
     *  @throws Exception    不幸的createImageInline方法抛出一个异常(没有更多具体的异常类型)
     */
    public static void addImageToPackage(WordprocessingMLPackage wordMLPackage,
                                          byte[] bytes) throws Exception {
        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

        int docPrId = 1;
        int cNvPrId = 2;
        org.docx4j.dml.wordprocessingDrawing.Inline inline = imagePart.createImageInline("Filename hint","Alternative text", docPrId, cNvPrId, false);

        P paragraph = addInlineImageToParagraph(inline);

        wordMLPackage.getMainDocumentPart().addObject(paragraph);
    }

    /**
     *  创建一个对象工厂并用它创建一个段落和一个可运行块R.
     *  然后将可运行块添加到段落中. 接下来创建一个图画并将其添加到可运行块R中. 最后我们将内联
     *  对象添加到图画中并返回段落对象.
     *
     * @param   inline 包含图片的内联对象.
     * @return  包含图片的段落
     */
    private static P addInlineImageToParagraph(org.docx4j.dml.wordprocessingDrawing.Inline inline) {
        // 添加内联对象到一个段落中
        ObjectFactory factory = new ObjectFactory();
        P paragraph = factory.createP();
        R run = factory.createR();
        paragraph.getContent().add(run);
        Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return paragraph;
    }

    // 分页
    public static void addPageBreak(WordprocessingMLPackage wordMLPackage,
                             ObjectFactory factory, STBrType sTBrType) {
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
        Br breakObj = new Br();
        breakObj.setType(sTBrType);
        P paragraph = factory.createP();
        paragraph.getContent().add(breakObj);
        documentPart.addObject(paragraph);
    }
}
