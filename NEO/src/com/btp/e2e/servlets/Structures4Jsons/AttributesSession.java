package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class AttributesSession extends PojoPrimary {

	public AttributesSession() {

	}

	private String rut;
	private String email;
	private String nombre;
	private String topeDeuda;
	private String lista_pedido;
	private String bloqueado;
	private String tipo;
	private String terminal;
	private String usadescuentos;
	private String rol;

	private String folioguia = "";
	private String stock = "";
	private String centro = "";
	private String id_turno = "";

	public String getRut() {
		
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getEmail() {
		
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTopeDeuda() {
		
		return topeDeuda;
	}

	public void setTopeDeuda(String topeDeuda) {
		this.topeDeuda = topeDeuda;
	}

	public String getLista_pedido() {
		
		return lista_pedido;
	}

	public void setLista_pedido(String lista_pedido) {
		this.lista_pedido = lista_pedido;
	}

	public String getBloqueado() {
		
		return bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}

	public String getTipo() {
		
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTerminal() {
		
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getUsadescuentos() {
		
		return usadescuentos;
	}

	public void setUsadescuentos(String usadescuentos) {
		this.usadescuentos = usadescuentos;
	}

	public String getRol() {
		
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getFolioguia() {
		
		return folioguia;
	}

	public void setFolioguia(String folioguia) {
		this.folioguia = folioguia;
	}

	public String getStock() {
		
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getCentro() {
		
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getId_turno() {
		
		return id_turno;
	}

	public void setId_turno(String id_turno) {
		this.id_turno = id_turno;
	}

}
