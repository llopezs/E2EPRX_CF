package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class CierreCaja extends PojoPrimary {
	private String rut = "";
	private String terminal = "";
	private String imgvoucher = "";
	private String pdf417 = "";
	private String imgb64 = "";
	private List<ProductSLL> stock = new ArrayList<ProductSLL>();
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
}
