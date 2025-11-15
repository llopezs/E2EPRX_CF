package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class SAPLogVenta extends PojoPrimary {
	private List<SAPLogVentaMensajeAlmacenado> MSGS = new ArrayList<SAPLogVentaMensajeAlmacenado>();
	private List<SAPLogVentaDocumentosAlmacenado> DOCTOS = new ArrayList<SAPLogVentaDocumentosAlmacenado>();

	public List<SAPLogVentaMensajeAlmacenado> getMSGS() {
		
		return MSGS;
	}

	public void setMSGS(List<SAPLogVentaMensajeAlmacenado> mSGS) {
		MSGS = mSGS;
	}

	public List<SAPLogVentaDocumentosAlmacenado> getDOCTOS() {
		
		return DOCTOS;
	}

	public void setDOCTOS(List<SAPLogVentaDocumentosAlmacenado> dOCTOS) {
		DOCTOS = dOCTOS;
	}
}