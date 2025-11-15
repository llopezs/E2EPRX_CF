package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class UserStatus extends PojoPrimary {

	private String motivo = "";
	private String terminal = "";
	private String guia = "";
	private String tpromdesc = "";
	private String telefono = "";
	private String nombre_chofer = "";
	// private String cantidad1 = "";
	// private String producto1 = "";
	// private String cantidad2 = "";
	// private String producto2 = "";
	// private String cantidad3 = "";
	// private String producto3 = "";
	// private String cantidad4 = "";
	// private String producto4 = "";
	// private String cantidad5 = "";
	// private String producto5 = "";
	// private String cantidad6 = "";
	// private String producto6 = "";
	// private String cantidad7 = "";
	// private String producto7 = "";
	// private String cantidad8 = "";
	// private String producto8 = "";
	// private String cantidad9 = "";
	// private String producto9 = "";
	// private String cantidad10 = "";
	// private String producto10 = "";
	// private String cantidad11 = "";
	// private String producto11 = "";
	// private String cantidad12 = "";
	// private String producto12 = "";
	// private String cantidad13 = "";
	// private String producto13 = "";
	// private String cantidad14 = "";
	// private String producto14 = "";
	// private String cantidad15 = "";
	// private String producto15 = "";
	private List<ProductSLL> productos = new ArrayList<ProductSLL>();
	
	public String getNombreChofer() {
		
		return nombre_chofer;
	}

	public void setNombreChofer(String nombre_chofer) {
		this.nombre_chofer = nombre_chofer;
	}
	
	public String getTelefono() {
		
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTerminal() {
		
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	public String getTpromdesc() {
		
		return tpromdesc;
	}

	public void setTpromdesc(String tpromdesc) {
		this.tpromdesc = tpromdesc;
	}

	public String getGuia() {
		
		return guia;
	}

	public void setGuia(String guia) {
		this.guia = guia;
	}

	public List<ProductSLL> getProductos() {
		
		return productos;
	}

	public void setProductos(List<ProductSLL> productos) {
		this.productos = productos;
	}

	//DES0011PITS - 20112020
	/*public String getMotivo() {
		if (motivo == null || motivo.length() == 0
				|| (!motivo.equals("1") && !motivo.equals("2") && !motivo.equals("3")))
			motivo = "1";

		
		return motivo;
	}*/
	
	public String getMotivo(){
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}
