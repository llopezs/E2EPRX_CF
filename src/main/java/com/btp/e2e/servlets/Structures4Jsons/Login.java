package com.btp.e2e.servlets.Structures4Jsons;

public class Login {

	private String MANDT = "";
	private String USUARIO = "";
	private String PASSWORD = "";
	private String EMAIL = "";
	private String PERFIL = "";
	private String ACTIVO = "";
	private String IDPUSH = "";

	public Login() {
		super();
	}

	public String getMANDT() {
		return MANDT;
	}

	public void setMANDT(String mANDT) {
		MANDT = mANDT;
	}

	public String getUSUARIO() {
		return USUARIO;
	}

	public void setUSUARIO(String uSUARIO) {
		USUARIO = uSUARIO;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getPERFIL() {
		return PERFIL;
	}

	public void setPERFIL(String pERFIL) {
		PERFIL = pERFIL;
	}

	public String getACTIVO() {
		return ACTIVO;
	}

	public void setACTIVO(String aCTIVO) {
		ACTIVO = aCTIVO;
	}

	public String getIDPUSH() {
		return IDPUSH;
	}

	public void setIDPUSH(String iDPUSH) {
		IDPUSH = iDPUSH;
	}
}
