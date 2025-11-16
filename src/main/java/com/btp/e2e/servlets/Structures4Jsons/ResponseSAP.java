package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;

/**
 * ResponseSAP: Wrapper for OData service responses from SAP backend
 * Contains results data and messaging list
 */
public class ResponseSAP {
    private String RESULTS = "";
    private ArrayList<String> MSGS = new ArrayList<>();

    public ResponseSAP() {
        super();
    }

    public String getRESULTS() {
        return RESULTS;
    }

    public void setRESULTS(String rESULTS) {
        RESULTS = rESULTS;
    }

    public ArrayList<String> getMSGS() {
        return MSGS;
    }

    public void setMSGS(ArrayList<String> mSGS) {
        MSGS = mSGS;
    }
}
