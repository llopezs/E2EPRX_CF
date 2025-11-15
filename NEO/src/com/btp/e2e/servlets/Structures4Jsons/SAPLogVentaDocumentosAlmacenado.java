package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class SAPLogVentaDocumentosAlmacenado extends PojoPrimary {
	private String DOCTO = "";
	private String DTYPE = "";
	private String CLDOC = "";

	public String getDOCTO() {
		
		return DOCTO;
	}

	public void setDOCTO(String dOCTO) {
		DOCTO = dOCTO;
	}

	public String getDTYPE() {
		
		return DTYPE;
	}

	public void setDTYPE(String dTYPE) {
		DTYPE = dTYPE;
	}

	public String getCLDOC() {
		
		return CLDOC;
	}

	public void setCLDOC(String cLDOC) {
		CLDOC = cLDOC;
	}
}