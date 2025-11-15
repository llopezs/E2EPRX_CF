package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;

public class LoginTokenData {
	private String access_token = "";
	private String token_type = "bearer";
	private String expires = "";
	private Boolean resultado = false;
	private String mensaje = "";
	private ArrayList<String> mensajesToken = new ArrayList<String>();

	private LoginOutData user = new LoginOutData();

	public LoginOutData getUser() {
		return user;
	}

	public void setUser(LoginOutData user) {
		this.user = user;
	}

	public String getAcces_token() {
		return access_token;
	}

	public void setAcces_token(String acces_token) {
		this.access_token = acces_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public Boolean getResultado() {
		return resultado;
	}

	public void setResultado(Boolean resultado) {
		this.resultado = resultado;
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
