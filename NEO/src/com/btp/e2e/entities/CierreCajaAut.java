package com.btp.e2e.entities;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.servlets.Structures4Jsons.ProductSLL;

public class CierreCajaAut {
	
	public CierreCajaAut() {
		super();
	}

	public String rut = "";
	public String terminal = "";
	public String tipo = "";
	public String folioguia = "";

	public String centro = "";
	public String centroDest = "";
	public String ind_pm = "";
	public String pm = "";
	public String usuario = "";
	public String ventas = "";
	public String password = "";
	public String imgVoucher = "";
	public List<ProductSLL> stock = new ArrayList<ProductSLL>();
	public String id_turno = "";
	public CilVendidos cil_vendidos ;
	public String nombre_chofer;
	
}
