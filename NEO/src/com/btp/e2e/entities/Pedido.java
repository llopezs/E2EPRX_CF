package com.btp.e2e.entities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.btp.e2e.implementations.PojoPrimary;

public class Pedido extends PojoPrimary {

	private String id = "";

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPedido() {
		
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getFecha_toma() {
		
		return fecha_toma;
	}

	public void setFecha_toma(String fecha_toma) {
		this.fecha_toma = fecha_toma;
	}

	public String getPedido_sap() {
		
		return pedido_sap;
	}

	public void setPedido_sap(String pedido_sap) {
		this.pedido_sap = pedido_sap;
	}

	public String getTipo_pedido() {
		
		return tipo_pedido;
	}

	public void setTipo_pedido(String tipo_pedido) {
		this.tipo_pedido = tipo_pedido;
	}

	public String getTipo_sol() {
		
		return tipo_sol;
	}

	public void setTipo_sol(String tipo_sol) {
		this.tipo_sol = tipo_sol;
	}

	public String getEstado() {
		
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservacion() {
		
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getSy_origen() {
		
		return sy_origen;
	}

	public void setSy_origen(String sy_origen) {
		this.sy_origen = sy_origen;
	}

	public DatosCliente getCliente() {
		return cliente;
	}

	public void setCliente(DatosCliente cliente) {
		this.cliente = cliente;
	}

	public DatosEntrega getDatos_entrega() {
		return datos_entrega;
	}

	public void setDatos_entrega(DatosEntrega datos_entrega) {
		this.datos_entrega = datos_entrega;
	}

	public DatosPago getDatos_pago() {
		return datos_pago;
	}

	public void setDatos_pago(DatosPago datos_pago) {
		this.datos_pago = datos_pago;
	}

	public DatosDetalle[] getDetalle() {
		return detalle;
	}

	public void setDetalle(DatosDetalle[] detalle) {
		this.detalle = detalle;
	}

	public DatosDetalle[] getDetalles() {
		return detalles;
	}

	public void setDetalles(DatosDetalle[] detalles) {
		this.detalles = detalles;
	}

	public String getTimestamp() {
		
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	private String pedido = "";
	private String fecha_toma = "";
	private String pedido_sap = "";
	private String tipo_pedido = "";
	private String tipo_sol = "";
	private String oc = "";
	private String estado = "";
	private String observacion = "";
	private String sy_origen = "";
	private DatosCliente cliente = new DatosCliente();
	private DatosEntrega datos_entrega = new DatosEntrega();
	private DatosPago datos_pago = new DatosPago();
	private DatosDetalle[] detalle = null;
	private DatosDetalle[] detalles = null;
	private String timestamp = "";
	private String terminal;
	private String codigo_emer;
	private String grupo_emer;
	private String tipo_emer;
	private String cod_tipo_emer;
	private String precinto;
	public boolean pedidoModificado = false;
	
	

	public Pedido() {
		super();
	}

	public Pedido(String pedido, String fecha_toma, String pedido_sap, String tipo_pedido, String tipo_sol,
			String estado, String observacion, DatosCliente cliente, DatosEntrega datos_entrega, DatosPago datos_pago,
			DatosDetalle[] detalle) {
		super();
		this.pedido = pedido;
		this.fecha_toma = fecha_toma;
		this.pedido_sap = pedido_sap;
		this.tipo_pedido = tipo_pedido;
		this.tipo_sol = tipo_sol;
		this.estado = estado;
		this.observacion = observacion;
		this.cliente = cliente;
		this.datos_entrega = datos_entrega;
		this.datos_pago = datos_pago;
		this.detalle = detalle;
	}

	public Pedido(String id, String pedido, String fecha_toma, String pedido_sap, String tipo_pedido, String tipo_sol,
			String estado, String observacion, DatosCliente cliente, DatosEntrega datos_entrega, DatosPago datos_pago,
			DatosDetalle[] detalle, String timestamp) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.fecha_toma = fecha_toma;
		this.pedido_sap = pedido_sap;
		this.tipo_pedido = tipo_pedido;
		this.tipo_sol = tipo_sol;
		this.estado = estado;
		this.observacion = observacion;
		this.cliente = cliente;
		this.datos_entrega = datos_entrega;
		this.datos_pago = datos_pago;
		this.detalle = detalle;
		this.timestamp = timestamp;
	}

	public String getTerminal() {
		
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String toMD5(String clave) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(clave.getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		return number.toString(16);

	}
	
	
	
	public String getCodigo_emer() {
		return codigo_emer;
	}

	public void setCodigo_emer(String codigo_emer) {
		this.codigo_emer = codigo_emer;
	}

	public String getGrupo_emer() {
		return grupo_emer;
	}

	public void setGrupo_emer(String grupo_emer) {
		this.grupo_emer = grupo_emer;
	}

	public String getTipo_emer() {
		return tipo_emer;
	}

	public void setTipo_emer(String tipo_emer) {
		this.tipo_emer = tipo_emer;
	}

	public String getCod_tipo_emer() {
		return cod_tipo_emer;
	}

	public void setCod_tipo_emer(String cod_tipo_emer) {
		this.cod_tipo_emer = cod_tipo_emer;
	}

	public String getPrecinto() {
		return precinto;
	}

	public void setPrecinto(String precinto) {
		this.precinto = precinto;
	}

	public void checkChars() {

		if (this.getObservacion() != null && this.getObservacion().length() > 0)
			this.setObservacion(this.getObservacion().replaceAll("[^a-zA-Z0-9, ]+", " "));
		
		if (this.getCliente().getBlock() != null && this.getCliente().getBlock().length() > 0)
			this.getCliente().setBlock(this.getCliente().getBlock().replaceAll("[^a-zA-Z0-9, ]+", " "));
		
		if (this.getCliente().getCalle() != null && this.getCliente().getCalle().length() > 0)
			this.getCliente().setCalle(this.getCliente().getCalle().replaceAll("[^a-zA-Z0-9, ]+", " "));
		
		if (this.getCliente().getCasa() != null && this.getCliente().getCasa().length() > 0)
			this.getCliente().setCasa(this.getCliente().getCasa().replaceAll("[^a-zA-Z0-9, ]+", " "));
		
		if (this.getCliente().getCluster() != null && this.getCliente().getCluster().length() > 0)
			this.getCliente().setCluster(this.getCliente().getCluster().replaceAll("[^a-zA-Z0-9, ]+", " "));
		
		if (this.getCliente().getComuna() != null && this.getCliente().getComuna().length() > 0)
			this.getCliente().setComuna(this.getCliente().getComuna().replaceAll("[^a-zA-Z0-9, ]+", " "));
		
		if (this.getCliente().getDepto() != null && this.getCliente().getDepto().length() > 0)
			this.getCliente().setDepto(this.getCliente().getDepto().replaceAll("[^a-zA-Z0-9, ]+", " "));
		
		if (this.getCliente().getLocal() != null && this.getCliente().getLocal().length() > 0)
			this.getCliente().setLocal(this.getCliente().getLocal().replaceAll("[^a-zA-Z0-9, ]+", " "));
		
		if (this.getCliente().getNumero() != null && this.getCliente().getNumero().length() > 0)
			this.getCliente().setNumero(this.getCliente().getNumero().replaceAll("[^a-zA-Z0-9, ]+", " "));
		
		if (this.getCliente().getOtros() != null && this.getCliente().getOtros().length() > 0)
			this.getCliente().setOtros(this.getCliente().getOtros().replaceAll("[^a-zA-Z0-9, ]+", " "));
		
		if (this.getCliente().getPoblacion() != null && this.getCliente().getPoblacion().length() > 0)
			this.getCliente().setPoblacion(this.getCliente().getPoblacion().replaceAll("[^a-zA-Z0-9, ]+", " "));
		
		if (this.getCliente().getReferencia() != null && this.getCliente().getReferencia().length() > 0)
			this.getCliente().setReferencia(this.getCliente().getReferencia().replaceAll("[^a-zA-Z0-9, ]+", " "));
		
	}

	public String getOc() {
		return oc;
	}

	public void setOc(String oc) {
		this.oc = oc;
	}
}