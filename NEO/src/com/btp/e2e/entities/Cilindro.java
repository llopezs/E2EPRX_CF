package com.btp.e2e.entities;

public class Cilindro {
	
	private String CANTIDAD;
	private String CANT_KG;
	private String DESCRIPCION;
	private String PRECIO;
	private String PRODUCTO;
	
	public String getCANTIDAD() {
		return CANTIDAD;
	}

	public void setCANTIDAD(String cANTIDAD) {
		CANTIDAD = cANTIDAD;
	}

	public String getCANT_KG() {
		return CANT_KG;
	}

	public void setCANT_KG(String cANT_KG) {
		CANT_KG = cANT_KG;
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

	public String getPRODUCTO() {
		return PRODUCTO;
	}

	public void setPRODUCTO(String pRODUCTO) {
		PRODUCTO = pRODUCTO;
	}

	public Cilindro(String cANTIDAD, String cANT_KG, String dESCRIPCION, String pRECIO, String pRODUCTO) {
		super();
		CANTIDAD = cANTIDAD;
		CANT_KG = cANT_KG;
		DESCRIPCION = dESCRIPCION;
		PRECIO = pRECIO;
		PRODUCTO = pRODUCTO;
	}

}
