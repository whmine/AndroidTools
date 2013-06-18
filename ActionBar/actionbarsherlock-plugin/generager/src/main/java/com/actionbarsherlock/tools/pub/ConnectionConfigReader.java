package com.actionbarsherlock.tools.pub;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import com.actionbarsherlock.tools.pub.exception.SysException;

public class ConnectionConfigReader {
    public static final String CONNECT_CONFIG = "connection-config";
    public static final String CONNECTTION = "connection-config/connection";
    public static final String DATASOURCE = "connection-config/connection/datasource";
    public static final String DIRVER_CLASS = "connection-config/connection/driver-class";
    public static final String USERNAME = "connection-config/connection/username";
    public static final String PASSWORD = "connection-config/connection/password";
    public static final String URL = "connection-config/connection/url";
    public static final String POOL_MAX_ACTIVE = "connection-config/connection/pool-maxActive";
    public static final String POOL_MAX_WAIT = "connection-config/connection/pool-maxWait";
    public static final String POOL_MAX_IDLE = "connection-config/connection/pool-maxIdle";
    private ConnectionConfigDef configDef = new ConnectionConfigDef();
    private Digester digester;

    /**
     * 从 输入流中按添加的规则读取xml配置信息
     * @param in
     * @throws BeansDefinitionException
     */
    public void read(InputStream in) throws SysException {

        digester = new Digester();
        digester.push(this);
        digester.setValidating(false);
        this.addRules();
        try {
            digester.parse(in);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new SysException("xml parse exception", ioe);
        } catch (SAXException saxe) {
            saxe.printStackTrace();
            throw new SysException("xml parse exception", saxe);
        }

    }

    /**
     * 添加digester的解析规则
     */
    public void addRules() {
        digester.addObjectCreate(CONNECT_CONFIG, ConnectionConfigDef.class);
        digester.addSetProperties(CONNECT_CONFIG, "default", "defaultName");
        digester.addObjectCreate(CONNECTTION, ConnectionDef.class);
        digester.addSetProperties(CONNECTTION, "name", "name");
        digester.addCallMethod(DATASOURCE, "setDatasource", 0);
        digester.addCallMethod(DIRVER_CLASS, "setDriverClass", 0);
        digester.addCallMethod(USERNAME, "setUserName", 0);
        digester.addCallMethod(PASSWORD, "setPassword", 0);
        digester.addCallMethod(URL, "setUrl", 0);
        digester.addCallMethod(POOL_MAX_ACTIVE, "setPoolMaxActive", 0);
        digester.addCallMethod(POOL_MAX_IDLE, "setPoolMaxIdle", 0);
        digester.addCallMethod(POOL_MAX_WAIT, "setPoolMaxWait", 0);
        digester.addSetNext(CONNECTTION, "addConnection");
        digester.addSetNext(CONNECT_CONFIG, "setConfigDef");
    }

    public ConnectionConfigDef getConfigDef() {
        return configDef;
    }

    public void setConfigDef(ConnectionConfigDef configDef) {
        this.configDef = configDef;
    }

}
