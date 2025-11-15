package com.btp.e2e.servlets.Structures4Jsons;

import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

import java.util.ArrayList;

public class ProductWithCoupon extends PojoPrimary {
	private String PRODUCTO = "";
	private String CANTIDAD = "";
	private List<String> CUPONES = new ArrayList<String>();

	public String getPRODUCTO() {
		
		return PRODUCTO;
	}

	public void setPRODUCTO(String pRODUCTO) {
		PRODUCTO = pRODUCTO;
	}

	public String getCANTIDAD() {
		
		return CANTIDAD;
	}

	public void setCANTIDAD(String cANTIDAD) {
		CANTIDAD = cANTIDAD;
	}

	public List<String> getCUPONES() {
		
		return CUPONES;
	}

	public void setCUPONES(List<String> cUPONES) {
		CUPONES = cUPONES;
	}

}
