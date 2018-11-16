package com.demo.tools.codegen.mybatis.plugins;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.util.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * XxMapperExt.xml文件生成插件, 用于自定义Sql
 *
 * @author wude
 * @version 1.0.0
 * @create 2018-04-19 17:10
 */
public class DemoMapperExtGeneratorPlugin extends PluginAdapter {

    /**
     * XxMapper.xml文件所在Project和Package
     */
    private String sqlMapTargetProject;
    private String sqlMapTargetPackage;

    private ShellCallback shellCallback = null;

    public DemoMapperExtGeneratorPlugin() {
        shellCallback = new DefaultShellCallback(false);
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        if (sqlMapTargetProject == null) {
            this.sqlMapTargetProject = properties.getProperty("sqlMapTargetProject");
        }
        if (sqlMapTargetPackage == null) {
            this.sqlMapTargetPackage = properties.getProperty("sqlMapTargetPackage");
        }
    }

    public boolean validate(List<String> warnings) {
        Assert.notNull(sqlMapTargetProject, "sqlMapTargetProject is null!");
        Assert.notNull(sqlMapTargetPackage, "sqlMapTargetPackage is null!");
        return true;
    }

    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable) {

        List<GeneratedXmlFile> generatedXmlFiles = new ArrayList<GeneratedXmlFile>();
        for (GeneratedXmlFile generatedXmlFile : introspectedTable.getGeneratedXmlFiles()) {
            String mapperExtFileName = introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "MapperExt.xml";
            File mapperExtFile = new File(sqlMapTargetProject + "/" + sqlMapTargetPackage + "/" + mapperExtFileName);
            // 如果XxMapperExt.xml文件已存在, 直接跳过
            if (mapperExtFile.exists()) {
                return null;
            }

            XmlElement mapper = new XmlElement("mapper");
            mapper.addAttribute(new Attribute("namespace", introspectedTable.getMyBatis3SqlMapNamespace()));
            Document document = new Document("-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
            document.setRootElement(mapper);

            // 追加空白文本，防止mapper标签以/结尾
            TextElement blankElement = new TextElement("");
            document.getRootElement().addElement(blankElement);

            GeneratedXmlFile generatedExtXmlFile = new GeneratedXmlFile(document, mapperExtFileName.toString(), sqlMapTargetPackage, sqlMapTargetProject, true, context.getXmlFormatter());
            generatedXmlFiles.add(generatedExtXmlFile);
        }
        return generatedXmlFiles;
    }


}
