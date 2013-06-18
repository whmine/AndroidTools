package com.actionbarsherlock.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import android.util.Log;

public class AppException extends GenericException {

    private static final long serialVersionUID = 1L;

    public AppException(String errId, String errOwnMsg) {
        this.errId = errId;
        this.errMsg = errOwnMsg;
        this.writeAppException();
    }

    private void writeAppException() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream p = new PrintStream(os);
        this.printStackTrace(p);
        Log.e(this.errId, os.toString());

    }
}
