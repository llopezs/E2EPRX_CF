package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.implementations.PojoPrimary;

public class RespuestaCambiarEstadoPedidoSLLString extends PojoPrimary {
	private String content;
	private String xmlns;

	public String getContent() {
		
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getXmlns() {
		
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}
}
