package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class GDEJson extends PojoPrimary{
	
	private List<GDE_SOLICITANTES> solicitantes = new ArrayList<GDE_SOLICITANTES>();

	public List<GDE_SOLICITANTES> getSolicitantes() {
		return solicitantes;
	}

	public void setSolicitantes(List<GDE_SOLICITANTES> solicitantes) {
		this.solicitantes = solicitantes;
	}
	
}
