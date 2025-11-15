package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class Administrador extends PojoPrimary {

	private String id;
	private String rut;
	private String clave;
	private String nombre;
	private String telefono;
	private String email;
	private String tipo;
	private String rol;
	private String bloqueado;
	private String timestamp;

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRut() {
		
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getClave() {
		
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipo() {
		
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRol() {
		
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getBloqueado() {
		
		return bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}

	public String getTimestamp() {
		
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int size() {
		return 8;
	}

	public String get(int index) {
		
		String str = "";
		switch (index) {
		case 1:
			str = rut;
			break;
		case 2:
			str = clave;
			break;
		case 3:
			str = nombre;
			break;
		case 4:
			str = telefono;
			break;
		case 5:
			str = email;
			break;
		case 6:
			str = tipo;
			break;
		case 7:
			str = rol;
			break;
		case 8:
			str = bloqueado;
			break;
		}
		return str;
	}

}
