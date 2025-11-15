
package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.entities.CilVendidos;
import com.btp.e2e.implementations.PojoPrimary;

public class JSONCierreT3 extends PojoPrimary {
	private String terminal = "";
	private String imgVoucher = "";
	private String rut = "";
	private String ventas = "";
	private String motivoT = "";
	private String password = "";
	private String imgvoucher = "";
	private String pdf417 = "";
	private String imgb64 = "";
	private List<ProductSLL> stock = new ArrayList<ProductSLL>();
	private String id_turno = "";
	private CilVendidos cil_vendidos;
	private String nombre_chofer = "";

	
	
	public String getPassword() {
		return password;
	
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMotivoT() {		
		return motivoT;
	}

	public void setMotivoT(String motivoT) {
		this.motivoT = motivoT;
	}

	public String getTerminal() {
		
		return terminal;
	}

	public void setTerminal(String value) {
		this.terminal = value;
	}

	public String getImgVoucher() {
		
		return imgVoucher;
	}

	public void setImgVoucher(String value) {
		this.imgVoucher = value;
	}

	public String getRut() {
		
		return rut;
	}

	public void setRut(String value) {
		this.rut = value;
	}

	public String getVentas() {
		
		return ventas;
	}

	public void setVentas(String value) {
		this.ventas = value;
	}

	
	public String getImgvoucher() {
		
		return imgvoucher;
	}

	public void setImgvoucher(String imgvoucher) {
		this.imgvoucher = imgvoucher;
	}

	public String getPdf417() {
		
		return pdf417;
	}

	public void setPdf417(String pdf417) {
		this.pdf417 = pdf417;
	}

	public String getImgb64() {
		
		return imgb64;
	}

	public void setImgb64(String imgb64) {
		this.imgb64 = imgb64;
	}

	public List<ProductSLL> getStock() {
		
		return stock;
	}

	public void setStock(List<ProductSLL> stock) {
		this.stock = stock;
	}

	public String getId_turno() {
		return id_turno;
	}

	public void setId_turno(String id_turno) {
		this.id_turno = id_turno;
	}

	public CilVendidos getCil_vendidos() {
		return cil_vendidos;
	}

	public void setCil_vendidos(CilVendidos cil_vendidos) {
		this.cil_vendidos = cil_vendidos;
	}

	public String getNombre_chofer() {
		return nombre_chofer;
	}

	public void setNombre_chofer(String nombre_chofer) {
		this.nombre_chofer = nombre_chofer;
	}

	
	
}