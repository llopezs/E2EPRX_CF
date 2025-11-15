package com.btp.e2e.entities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.btp.e2e.implementations.PojoPrimary;

public class Notificaciones extends PojoPrimary {


	public Notificaciones() {
		super();

	}
	
	private String id; 
	private String origen;
	private String mensaje;
	private String folio;
	private String usuario;
	private String timestamp_ingreso_notif;
	private String timestamp_actualizacion;
	private String minutos;
	private String centro;
	private String terminal;
	private String estado;
	private String modelo_vehiculo;
	private String nombre_chofer;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getTimestamp_ingreso_notif() {
		return timestamp_ingreso_notif;
	}
	public void setTimestamp_ingreso_notif(String timestamp_ingreso_notif) {
		this.timestamp_ingreso_notif = timestamp_ingreso_notif;
	}
	public String getTimestamp_actualizacion() {
		return timestamp_actualizacion;
	}
	public void setTimestamp_actualizacion(String timestamp_actualizacion) {
		this.timestamp_actualizacion = timestamp_actualizacion;
	}
	public String getMinutos() {
		return minutos;
	}
	public void setMinutos(String minutos) {
		this.minutos = minutos;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getModelo_vehiculo() {
		return modelo_vehiculo;
	}
	public void setModelo_vehiculo(String modelo_vehiculo) {
		this.modelo_vehiculo = modelo_vehiculo;
	}
	public String getNombre_chofer() {
		return nombre_chofer;
	}
	public void setNombre_chofer(String nombre_chofer) {
		this.nombre_chofer = nombre_chofer;
	}
	
}
