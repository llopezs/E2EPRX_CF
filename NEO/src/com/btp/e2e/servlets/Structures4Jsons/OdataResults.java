package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class OdataResults extends PojoPrimary {
	private List<OdataStructure> results = new ArrayList<OdataStructure>();

	public List<OdataStructure> getResults() {
		
		return results;
	}

	public void setResults(List<OdataStructure> results) {
		this.results = results;
	}
}
