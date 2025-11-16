package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;

/**
 * LoginTokenData: Response after successful login with JWT token
 * Contains authenticated user info and token details
 */
public class LoginTokenData {
    private boolean resultado = false;
    private LoginOutData user = new LoginOutData();
    private String acces_token = "";
    private String expires = "";
    private String token_type = "bearer";
    private String mensaje = "";
    private ArrayList<String> mensajesToken = new ArrayList<>();

    public LoginTokenData() {
        super();
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public LoginOutData getUser() {
        return user;
    }

    public void setUser(LoginOutData user) {
        this.user = user;
    }

    public String getAcces_token() {
        return acces_token;
    }

    public void setAcces_token(String acces_token) {
        this.acces_token = acces_token;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ArrayList<String> getMensajesToken() {
        return mensajesToken;
    }

    public void setMensajesToken(ArrayList<String> mensajesToken) {
        this.mensajesToken = mensajesToken;
    }
}
