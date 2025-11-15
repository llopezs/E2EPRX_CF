package com.btp.e2e.servlets.Structures4Jsons;


public class correlativoAWS {
	
	private InstruccionAWSJson INSTRUCCION = new InstruccionAWSJson();
	private CorrelativoAWSJson data = new CorrelativoAWSJson();
	
	public InstruccionAWSJson getINSTRUCCION() {
		return INSTRUCCION;
	}
	public void setINSTRUCCION(InstruccionAWSJson iNSTRUCCION) {
		INSTRUCCION = iNSTRUCCION;
	}
	public CorrelativoAWSJson getData() {
		return data;
	}
	public void setData(CorrelativoAWSJson data) {
		this.data = data;
	}

}
