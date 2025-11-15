package com.btp.e2e.servlets.Structures4Jsons;

public class UserTokenSave {

	private String USUARIO = "";
	private String EMAIL = "";
	private String TOKEN = "";

	public UserTokenSave(String usuario, String email, String token) {
		super();
		// TODO Auto-generated constructor stub
		USUARIO=usuario;
		TOKEN=token;
		EMAIL=email;
		
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

	public String getTOKEN() {
		return TOKEN;
	}

	public void setTOKEN(String tOKEN) {
		TOKEN = tOKEN;
	}

}
