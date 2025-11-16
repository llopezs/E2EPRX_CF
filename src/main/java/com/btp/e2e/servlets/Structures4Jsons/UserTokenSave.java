package com.btp.e2e.servlets.Structures4Jsons;

/**
 * UserTokenSave: Request payload for saving/validating JWT token in SAP backend
 */
public class UserTokenSave {
    private String usuario = "";
    private String email = "";
    private String token = "";

    public UserTokenSave() {
        super();
    }

    public UserTokenSave(String usuario, String email, String token) {
        super();
        this.usuario = usuario;
        this.email = email;
        this.token = token;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
