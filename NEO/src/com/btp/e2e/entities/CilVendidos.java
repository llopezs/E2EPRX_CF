package com.btp.e2e.entities;

import java.util.List;

public class CilVendidos {

	private int cant_kg_total;
	private List<Cilindro> cilindros;

	public CilVendidos(int cant_kg_total, List<Cilindro> cilindros) {
		super();
		this.cant_kg_total = cant_kg_total;
		this.cilindros = cilindros;
	}

	public int getCant_kg_total() {
		return cant_kg_total;
	}

	public void setCant_kg_total(int cant_kg_total) {
		this.cant_kg_total = cant_kg_total;
	}

	public List<Cilindro> getCilindros() {
		return cilindros;
	}

	public void setCilindros(List<Cilindro> cilindros) {
		this.cilindros = cilindros;
	}

}
