package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class SAPCierreEntregasDetalles extends PojoPrimary {
	private List<SAPLogVentaMensajeAlmacenado> MSGS = new ArrayList<SAPLogVentaMensajeAlmacenado>();
	private List<DocumentoEntregaDetalleVenta> DOCTOS = new ArrayList<DocumentoEntregaDetalleVenta>();
	private String TIPOOPERADOR = "";
	private String TERMINAL = "";

	public List<SAPLogVentaMensajeAlmacenado> getMSGS() {
		
		return MSGS;
	}

	public void setMSGS(List<SAPLogVentaMensajeAlmacenado> mSGS) {
		MSGS = mSGS;
	}

	public List<DocumentoEntregaDetalleVenta> getDOCTOS() {
		
		return DOCTOS;
	}

	public void setDOCTOS(List<DocumentoEntregaDetalleVenta> dOCTOS) {
		DOCTOS = dOCTOS;
	}

	public String getTIPOOPERADOR() {
		
		return TIPOOPERADOR;
	}

	public void setTIPOOPERADOR(String tIPOOPERADOR) {
		TIPOOPERADOR = tIPOOPERADOR;
	}

	public String getTERMINAL() {
		
		return TERMINAL;
	}

	public void setTERMINAL(String tERMINAL) {
		TERMINAL = tERMINAL;
	}
}