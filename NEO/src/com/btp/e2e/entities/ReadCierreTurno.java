package com.btp.e2e.entities;

import java.util.List;

public class ReadCierreTurno {
	private String ventas;
	private Ventas ventasobj;
	private CilVendidos cil_vendidos;
	private String terminal;
	private String nombre_chofer;
	
	

	public CilVendidos getCil_vendidos() {
		return cil_vendidos;
	}

	public void setCil_vendidos(CilVendidos cil_vendidos) {
		this.cil_vendidos = cil_vendidos;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	
	public String getVentas() {
		return ventas;
	}

	public void setVentas(String ventas) {
		this.ventas = ventas;
	}


	public Ventas getVentasobj() {
		return ventasobj;
	}

	public void setVentasobj(Ventas ventasobj) {
		this.ventasobj = ventasobj;
	}


	public String getNombre_chofer() {
		return nombre_chofer;
	}

	public void setNombre_chofer(String nombre_chofer) {
		this.nombre_chofer = nombre_chofer;
	}


	public class Detalle{
		public String cant;
		public String mediopago;
		public String total;
		
	}
	
	public class Ventas{
		public List<Detalle> detalle;
		public String total;
	}

}
