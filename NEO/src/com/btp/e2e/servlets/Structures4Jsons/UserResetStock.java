package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class UserResetStock extends PojoPrimary {
	private String rut = "";
	private String name = "";
	private String email = "";
	private String scpid = "";

	public String getRut() {
		
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getScpid() {
		
		return scpid;
	}

	public void setScpid(String scpid) {
		this.scpid = scpid;
	}
}
