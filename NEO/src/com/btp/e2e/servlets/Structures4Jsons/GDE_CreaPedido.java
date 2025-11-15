package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class GDE_CreaPedido extends PojoPrimary {
	private String id_turno = "";
	private String werks = "";
	private String cliente = "";
	private String vkorg = "";
	private String vtweg = "";
	private String spart = "";
	private String folioguia = "";
	private String terminal = "";
	private String stcd1 = "";
	private String matnr = "";
	private String monto = "";
	private String cantidad = "";
	private List<GDE_DetalleVentaProducto> materials = new ArrayList<GDE_DetalleVentaProducto>();
	private GDE_Personalizado personalizados = new GDE_Personalizado();
	public String getId_turno() {
		return id_turno;
	}
	public void setId_turno(String id_turno) {
		this.id_turno = id_turno;
	}
	public String getWerks() {
		return werks;
	}
	public void setWerks(String werks) {
		this.werks = werks;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getVkorg() {
		return vkorg;
	}
	public void setVkorg(String vkorg) {
		this.vkorg = vkorg;
	}
	public String getVtweg() {
		return vtweg;
	}
	public void setVtweg(String vtweg) {
		this.vtweg = vtweg;
	}
	public String getSpart() {
		return spart;
	}
	public void setSpart(String spart) {
		this.spart = spart;
	}
	public String getFolioguia() {
		return folioguia;
	}
	public void setFolioguia(String folioguia) {
		this.folioguia = folioguia;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getStcd1() {
		return stcd1;
	}
	public void setStcd1(String stcd1) {
		this.stcd1 = stcd1;
	}
	public String getMatnr() {
		return matnr;
	}
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public List<GDE_DetalleVentaProducto> getMaterials() {
		return materials;
	}
	public void setMaterials(List<GDE_DetalleVentaProducto> materials) {
		this.materials = materials;
	}
	public GDE_Personalizado getPersonalizados() {
		return personalizados;
	}
	public void setPersonalizados(GDE_Personalizado personalizados) {
		this.personalizados = personalizados;
	}
	
	

	
	
	
}
