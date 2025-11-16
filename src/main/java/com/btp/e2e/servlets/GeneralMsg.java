package com.btp.e2e.servlets;

/**
 * GeneralMsg: Generic error/message response
 * Used for exception handling and error reporting
 */
public class GeneralMsg {
    private String msg = "";
    private String c_technical_msg = "";

    public GeneralMsg() {
        super();
    }

    public GeneralMsg(String msg) {
        super();
        this.msg = msg;
    }

    public GeneralMsg(String msg, String c_technical_msg) {
        super();
        this.msg = msg;
        this.c_technical_msg = c_technical_msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getC_technical_msg() {
        return c_technical_msg;
    }

    public void setC_technical_msg(String c_technical_msg) {
        this.c_technical_msg = c_technical_msg;
    }
}
