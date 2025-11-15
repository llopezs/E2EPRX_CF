package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class CambiarEstadoPedido extends PojoPrimary {
	private String tipoanot;
	private String usuariotipo;
	private String pedidotipo;
	private String observatipo;
	private String fecha;
	private String terminal;
	private String latitud;
	private String longitud;
	private String cli_longitud;
	private String cli_latitud;
	private String sistema;
	private String pedido;
	public String getTipoanot() {
		
		return tipoanot;
	}
	public void setTipoanot(String tipoanot) {
		this.tipoanot = tipoanot;
	}
	public String getUsuariotipo() {
		
		return usuariotipo;
	}
	public void setUsuariotipo(String usuariotipo) {
		this.usuariotipo = usuariotipo;
	}
	public String getPedidotipo() {
		
		return pedidotipo;
	}
	public void setPedidotipo(String pedidotipo) {
		this.pedidotipo = pedidotipo;
	}
	public String getObservatipo() {
		
		return observatipo;
	}
	public void setObservatipo(String observatipo) {
		this.observatipo = observatipo;
	}
	public String getFecha() {
		
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTerminal() {
		
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getLatitud() {
		
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getSistema() {
		
		if	(sistema != null) {
			return sistema;
		} else {
			return "";
		}
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public String getPedido() {
		
		return pedido;
	}
	public void setPedido(String pedido) {
		this.pedido = pedido;
	}
	public String getCli_longitud() {
		return cli_longitud;
	}
	public void setCli_longitud(String cli_longitud) {
		this.cli_longitud = cli_longitud;
	}
	public String getCli_latitud() {
		return cli_latitud;
	}
	public void setCli_latitud(String cli_latitud) {
		this.cli_latitud = cli_latitud;
	}
	
}
