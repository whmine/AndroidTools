package com.actionbarsherlock.tools.pub.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AppException extends GenericException {

    private static final long serialVersionUID = 1L;

    public AppException(String errId, String errOwnMsg) {
        this.errId = errId;
        this.errMsg = errOwnMsg;
        this.writeAppException();
    }

    private void writeAppException() {
        Log log = LogFactory.getLog(AppException.class);
        log.error("应用错误号：" + this.errId);
        log.error("应用错误信息：" + this.errMsg);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream p = new PrintStream(os);
        this.printStackTrace(p);
        log.error(os.toString());

    }
}