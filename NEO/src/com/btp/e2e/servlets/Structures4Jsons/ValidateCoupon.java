package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class ValidateCoupon extends PojoPrimary {
	private List<MsgProductWithCoupon> MSGS = new ArrayList<MsgProductWithCoupon>();
	private List<ProductWithCoupon> PRODUCTOS_ASIGNADOS = new ArrayList<ProductWithCoupon>();
	private boolean RESULT = false;

	public List<MsgProductWithCoupon> getMSGS() {
		
		return MSGS;
	}

	public void setMSGS(List<MsgProductWithCoupon> mSGS) {
		MSGS = mSGS;
	}

	public List<ProductWithCoupon> getPRODUCTOS_ASIGNADOS() {
		
		return PRODUCTOS_ASIGNADOS;
	}

	public void setPRODUCTOS_ASIGNADOS(List<ProductWithCoupon> pRODUCTOS_ASIGNADOS) {
		PRODUCTOS_ASIGNADOS = pRODUCTOS_ASIGNADOS;
	}

	public boolean isRESULT() {
		
		return RESULT;
	}

	public void setRESULT(boolean rESULT) {
		RESULT = rESULT;
	}
}
