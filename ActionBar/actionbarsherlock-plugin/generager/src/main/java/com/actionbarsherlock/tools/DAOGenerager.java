package com.actionbarsherlock.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DAOGenerager {
    private static Log log = LogFactory.getLog(DAOGenerager.class);
    public String codeTemplate;
    public String outPutFile;
    public String codeISDAOName;
    public String codeSDAOName;
    public String codeSVOName;
    public String codeModel;
    public String codeTableName;

    public DAOGenerager() {
    }

    public DAOGenerager(String codeTemplate, String outPutFile,
            String codeISDAOName, String codeSDAOName, String codeSVOName,
            String codeModel, String codeTableName) {
        this.codeTemplate = codeTemplate;
        this.outPutFile = outPutFile;
        this.codeISDAOName = codeISDAOName;
        this.codeSDAOName = codeSDAOName;
        this.codeSVOName = codeSVOName;
        this.codeModel = codeModel;
        this.codeTableName = codeTableName;
        new File(outPutFile+codeModel+"/"+"mode/").mkdirs();
        new File(outPutFile+codeModel+"/"+"dao/").mkdirs();

    }

    public boolean generateSDAOImplFile(DaoMetaData daoMeta) {
        log.debug("begin generate table " + daoMeta.getTableName()
                + " SDAO interface implements");
        try {

            Configuration cfg = new Configuration();
            cfg.setClassForTemplateLoading(DAOGenerager.class, codeTemplate);// 指定模板所在的classpath目录
            Template t = cfg.getTemplate(codeSDAOName);// 指定模板
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");

            String fileName = daoMeta.getClassName() + "SDAOImpl.java";
            FileOutputStream fos = new FileOutputStream(new File(outPutFile+codeModel+"/"+"dao/"
                    + fileName));// java文件的生成目录

            Map data = new HashMap();
            data.put("daoMeta", daoMeta);// 包名
            data.put("generatTime", sdf.format(new Date()));
            data.put("convert2ClassMethodName", new Convert2ClassMethodName());
            data.put("convert2ObjVarName", new Convert2ObjVarName());
            data.put("convertColumnTypeName", new ConvertColumnTypeName());
            // data.put("primaryKey", daoMeta.getPkCols());

            t.process(data, new BufferedWriter(new OutputStreamWriter(fos)));
            log.debug("complete generate table " + daoMeta.getTableName()
                    + " SDAO interface implements");
        } catch (TemplateException e) {
            log.error("Error while processing FreeMarker template \n" + e);
            return false;
        } catch (IOException e) {
            log.error("Error while generate Static Html File \n " + e);
            return false;
        }

        return true;
    }

    public boolean generateSVOFile(DaoMetaData daoMeta) {
        log.debug("begin generate table " + daoMeta.getTableName() + " SVO");
        try {
            Configuration cfg = new Configuration();
            cfg.setClassForTemplateLoading(DAOGenerager.class, codeTemplate);// 指定模板所在的classpath目录
            Template t = cfg.getTemplate(codeSVOName);// 指定模板
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String fileName = daoMeta.getClassName() + "SVO.java";
            FileOutputStream fos = new FileOutputStream(new File(outPutFile+codeModel+"/"+"mode/"
                    + fileName));// java文件的生成目录

            Map data = new HashMap();
            data.put("daoMeta", daoMeta);// 包名
            data.put("generatTime", sdf.format(new Date()));
            data.put("convert2ClassMethodName", new Convert2ClassMethodName());
            data.put("convert2ObjVarName", new Convert2ObjVarName());
            data.put("convertColumnTypeName", new ConvertColumnTypeName());
            // data.put("primaryKey", daoMeta.getPkCols());

            t.process(data, new BufferedWriter(new OutputStreamWriter(fos)));
            log.debug("complete generate table " + daoMeta.getTableName()
                    + "SVO ");
        } catch (TemplateException e) {
            log.error("Error while processing FreeMarker template \n" + e);
            return false;
        } catch (IOException e) {
            log.error("Error while generate Static Html File \n " + e);
            return false;
        }

        return true;
    }
}
