package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.entities.*;
import com.btp.e2e.implementations.PojoPrimary;

public class ReadUsersDataAll extends PojoPrimary {
	private String rut = "";
	private String terminal = "";
	private List<Turno> turnos = new ArrayList<Turno>();
	private List<LogVenta> ventas = new ArrayList<LogVenta>();
	private List<Cierre> cierres = new ArrayList<Cierre>();
	private List<LogGuia> guias = new ArrayList<LogGuia>();
	private List<LiquidacionStock> liquidaciones = new ArrayList<LiquidacionStock>();
	private StockActual stockActual = new StockActual();
	private String turnoSelected = "";
	public String getRut() {
		 return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getTerminal() {
		 return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public List<Turno> getTurnos() {
		 return turnos;
	}
	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}
	public List<LogVenta> getVentas() {
		 return ventas;
	}
	public void setVentas(List<LogVenta> ventas) {
		this.ventas = ventas;
	}
	public List<Cierre> getCierres() {
		 return cierres;
	}
	public void setCierres(List<Cierre> cierres) {
		this.cierres = cierres;
	}
	public List<LogGuia> getGuias() {
		 return guias;
	}
	public void setGuias(List<LogGuia> guias) {
		this.guias = guias;
	}
	public List<LiquidacionStock> getLiquidaciones() {
		 return liquidaciones;
	}

	public void setLiquidaciones(List<LiquidacionStock> liquidaciones) {
		this.liquidaciones = liquidaciones;
	}
	public StockActual getStockActual() {
		 return stockActual;
	}
	public void setStockActual(StockActual stockActual) {
		this.stockActual = stockActual;
	}
	public String getTurnoSelected() {
		 return turnoSelected;
	}
	public void setTurnoSelected(String turnoSelected) {
		this.turnoSelected = turnoSelected;
	}
}