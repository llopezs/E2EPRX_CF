package com.btp.e2e.entities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.btp.e2e.implementations.PojoPrimary;

public class ClaseMensaje extends PojoPrimary {

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMensaje() {
		
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getTimestamp() {
		
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getId_externo() {
		
		return id_externo;
	}

	public void setId_externo(String id_externo) {
		this.id_externo = id_externo;
	}

	public ClaseMensaje() {
		super();

	}

	private String id = "";
	private String mensaje = "";
	private String timestamp = "";
	private String id_externo = "";

	@SuppressWarnings("unused")
	private String toMD5(String clave) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(clave.getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		return number.toString(16);

	}

}
