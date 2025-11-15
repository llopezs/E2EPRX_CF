package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class LogErrorSLL extends PojoPrimary {

	private String id;
	private String json;
	private String servicio;
	private String error;
	private String centro;
	private String terminal;
	private String pedido_sll;
	private String timestamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getCentro() {
		return centro;
	}
	public void setCentro(String centro) {
		this.centro = centro;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getPedido_sll() {
		return pedido_sll;
	}
	public void setPedido_sll(String pedido_sll) {
		this.pedido_sll = pedido_sll;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	

}
