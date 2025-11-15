package com.btp.e2e.servlets.Structures4Jsons;

public class CentrosAWS {
	
	private InstruccionAWSJson INSTRUCCION = new InstruccionAWSJson();
	private CentrosAWSJson data = new CentrosAWSJson();
	
	public InstruccionAWSJson getINSTRUCCION() {
		return INSTRUCCION;
	}
	public void setINSTRUCCION(InstruccionAWSJson iNSTRUCCION) {
		INSTRUCCION = iNSTRUCCION;
	}
	public CentrosAWSJson getData() {
		return data;
	}
	public void setData(CentrosAWSJson data) {
		this.data = data;
	}


}
