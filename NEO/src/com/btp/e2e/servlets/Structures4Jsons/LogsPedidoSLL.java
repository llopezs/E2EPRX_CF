package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.entities.LogPedido;
import com.btp.e2e.entities.LogRecepciones;
import com.btp.e2e.entities.LogVenta;
import com.btp.e2e.entities.Pedido;
import com.btp.e2e.implementations.PojoPrimary;

public class LogsPedidoSLL extends PojoPrimary {
	private Pedido pedido = new Pedido();
	private List<LogPedido> listLogPedido = new ArrayList<LogPedido>();
	private List<LogVenta> listLogVentas = new ArrayList<LogVenta>();
	private List<LogRecepciones> listLogRecepciones = new ArrayList<LogRecepciones>();

	public Pedido getPedido() {
		
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<LogPedido> getListLogPedido() {
		
		return listLogPedido;
	}

	public void setListLogPedido(List<LogPedido> listLogPedido) {
		this.listLogPedido = listLogPedido;
	}

	public List<LogVenta> getListLogVentas() {
		
		return listLogVentas;
	}

	public void setListLogVentas(List<LogVenta> listLogVentas) {
		this.listLogVentas = listLogVentas;
	}

	public List<LogRecepciones> getListLogRecepciones() {
		
		return listLogRecepciones;
	}

	public void setListLogRecepciones(List<LogRecepciones> listLogRecepciones) {
		this.listLogRecepciones = listLogRecepciones;
	}

}
