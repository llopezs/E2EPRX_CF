package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class CierreCajaUsr extends PojoPrimary {
	private List<SAPLogVentaMensajeAlmacenado> MSGS = new ArrayList<SAPLogVentaMensajeAlmacenado>();
	private List<DocumentoEntregaDetalleVenta> DOCTOS = new ArrayList<DocumentoEntregaDetalleVenta>();
	private String TERMINAL = "";
	private String TIPOOPERADOR = "";
	private String ID_TURNO = "";
	private String VENTAS = "";

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

	public String getTERMINAL() {
		
		return TERMINAL;
	}

	public void setTERMINAL(String tERMINAL) {
		TERMINAL = tERMINAL;
	}

	public String getTIPOOPERADOR() {
		
		return TIPOOPERADOR;
	}

	public void setTIPOOPERADOR(String tIPOOPERADOR) {
		TIPOOPERADOR = tIPOOPERADOR;
	}

	public String getID_TURNO() {
		
		return ID_TURNO;
	}

	public void setID_TURNO(String iD_TURNO) {
		ID_TURNO = iD_TURNO;
	}

	public String getVENTAS() {
		
		return VENTAS;
	}

	public void setVENTAS(String vENTAS) {
		VENTAS = vENTAS;
	}
}
