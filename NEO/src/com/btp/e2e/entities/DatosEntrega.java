package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class DatosEntrega extends PojoPrimary {
	private String fecha_entrega;
	private String hora_entrega_cli;
	private String tiempo_comp_entrega;
	private String terminal;
	private String eta;

	public String getFecha_entrega() {

		return fecha_entrega;
	}

	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public String getHora_entrega_cli() {

		return hora_entrega_cli;
	}

	public void setHora_entrega_cli(String hora_entrega_cli) {
		this.hora_entrega_cli = hora_entrega_cli;
	}

	public String getTiempo_comp_entrega() {

		return tiempo_comp_entrega;
	}

	public void setTiempo_comp_entrega(String tiempo_comp_entrega) {
		this.tiempo_comp_entrega = tiempo_comp_entrega;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

}