package com.btp.e2e.servlets.Structures4Jsons;

/**
 * LoginOutData: User data returned from OData login call
 * Represents authenticated user information
 */
public class LoginOutData {
    private String USUARIO = "";
    private String EMAIL = "";
    private String PERFIL = "";
    private String ACTIVO = "";

    public LoginOutData() {
        super();
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String uSUARIO) {
        USUARIO = uSUARIO;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String eMAIL) {
        EMAIL = eMAIL;
    }

    public String getPERFIL() {
        return PERFIL;
    }

    public void setPERFIL(String pERFIL) {
        PERFIL = pERFIL;
    }

    public String getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(String aCTIVO) {
        ACTIVO = aCTIVO;
    }
}
