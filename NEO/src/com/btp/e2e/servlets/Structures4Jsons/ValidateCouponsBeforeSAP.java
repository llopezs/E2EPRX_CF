package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class ValidateCouponsBeforeSAP extends PojoPrimary {

	private String[] cupones;
	private Products4Coupon[] productos;

	public String[] getCupones() {
		
		return cupones;
	}

	public void setCupones(String[] value) {
		this.cupones = value;
	}

	public Products4Coupon[] getProductos() {
		
		return productos;
	}

	public void setProductos(Products4Coupon[] value) {
		this.productos = value;
	}

	public boolean allCouponsAreNumeric() {
		for (int i = 0; i < cupones.length; i++) {
			if (cupones[i].matches("[0-9]+") && cupones[i].length() > 0) {

			} else {
				
				return false;
			}
		}
		if (cupones.length > 0) {
			
			return true;
		} else {
			
			return false;
		}
	}

}
