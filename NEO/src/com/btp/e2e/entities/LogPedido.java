package com.btp.e2e.entities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.btp.e2e.implementations.PojoPrimary;

public class LogPedido extends PojoPrimary {

	public LogPedido() {
		super();

	}

	private String id;
	private String pedido;
	private String estado;
	private String observacion;
	private String terminal;
	private String id_usuario;
	private String id_seguimiento;
	private String timestamp;
	private String sy_origen;

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPedido() {
		
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getEstado() {
		
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservacion() {
		
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTerminal() {
		
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getId_usuario() {
		
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getId_seguimiento() {
		
		return id_seguimiento;
	}

	public void setId_seguimiento(String id_seguimiento) {
		this.id_seguimiento = id_seguimiento;
	}

	public String getTimestamp() {
		
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSy_origen() {
		
		return sy_origen;
	}

	public void setSy_origen(String sy_origen) {
		this.sy_origen = sy_origen;
	}

	@SuppressWarnings("unused")
	private String toMD5(String clave) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(clave.getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		return number.toString(16);

	}

}
