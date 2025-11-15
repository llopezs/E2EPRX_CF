package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class OdataItemGuiaProducto extends PojoPrimary {
	private String CODIGO_PRODUCTO = "";
	private String DESCRIPCION = "";
	private String PRECIO = "";
	private String CANTIDAD = "";
	private String UNIDAD = "";

	public String getCODIGO_PRODUCTO() {
		
		return CODIGO_PRODUCTO;
	}

	public void setCODIGO_PRODUCTO(String cODIGO_PRODUCTO) {
		CODIGO_PRODUCTO = cODIGO_PRODUCTO;
	}

	public String getDESCRIPCION() {
		
		return DESCRIPCION;
	}

	public void setDESCRIPCION(String dESCRIPCION) {
		DESCRIPCION = dESCRIPCION;
	}

	public String getPRECIO() {
		
		return PRECIO;
	}

	public void setPRECIO(String pRECIO) {
		PRECIO = pRECIO;
	}

	public String getCANTIDAD() {
		
		return CANTIDAD;
	}

	public void setCANTIDAD(String cANTIDAD) {
		CANTIDAD = cANTIDAD;
	}

	public String getUNIDAD() {
		
		return UNIDAD;
	}

	public void setUNIDAD(String uNIDAD) {
		UNIDAD = uNIDAD;
	}
}
