package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class VentaEspontaneaValidaCodDESCJson extends PojoPrimary {
		private String guia = "";
		private String rut = "";
		private String terminal = "";
		private String numero_camion = "";
		private String telefono = "";
		private String latitud = "";
		private String longitud = "";
		private String fecha = "";
		private String hora = "";
		private String id_transaccion_desc = "";
		private List<DetalleVentaProducto> productos = new ArrayList<DetalleVentaProducto>();
		private List<DetalleVenta> detalle = new ArrayList<DetalleVenta>();
		private List<ProductSLL> stock = new ArrayList<ProductSLL>();
		private String id = "";
		private String mail = "";
		private String name = "";
		private Afinidad afinidad = new Afinidad();
		private String folio = "";
		private String desc = "";
		private String cliente = "";
		private String imgb64 = "";
		private String imgVoucher = "";
		private String pdf417 = "";
		private String descuento_pedido = "";

		public String getGuia() {
			
			return guia;
		}

		public void setGuia(String guia) {
			this.guia = guia;
		}

		public String getRut() {
			
			return rut;
		}

		public void setRut(String rut) {
			this.rut = rut;
		}

		public String getTerminal() {
			
			return terminal;
		}

		public void setTerminal(String terminal) {
			this.terminal = terminal;
		}

		public String getNumero_camion() {
			
			return numero_camion;
		}

		public void setNumero_camion(String numero_camion) {
			this.numero_camion = numero_camion;
		}

		public String getTelefono() {
			
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public String getLatitud() {
			return latitud;
		}

		public void setLatitud(String latitud) {
			this.latitud = latitud;
		}

		public String getLongitud() {
			return longitud;
		}

		public void setLongitud(String longitud) {
			this.longitud = longitud;
		}

		public String getFecha() {
			
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public String getHora() {
			
			return hora;
		}

		public void setHora(String hora) {
			this.hora = hora;
		}

		public List<DetalleVentaProducto> getProductos() {
			
			return productos;
		}

		public void setProductos(List<DetalleVentaProducto> productos) {
			this.productos = productos;
		}

		public List<DetalleVenta> getDetalle() {
			
			return detalle;
		}

		public void setDetalle(List<DetalleVenta> detalle) {
			this.detalle = detalle;
		}

		public List<ProductSLL> getStock() {
			
			return stock;
		}

		public void setStock(List<ProductSLL> stock) {
			this.stock = stock;
		}

		public String getId() {
			
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getMail() {
			
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

		public String getName() {
			
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Afinidad getAfinidad() {
			
			return afinidad;
		}

		public void setAfinidad(Afinidad afinidad) {
			this.afinidad = afinidad;
		}

		public String getFolio() {
			
			return folio;
		}

		public void setFolio(String folio) {
			this.folio = folio;
		}

		public String getDesc() {
			
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getCliente() {
			
			return cliente;
		}

		public void setCliente(String cliente) {
			this.cliente = cliente;
		}

		public String getImgb64() {
			
			return imgb64;
		}

		public void setImgb64(String imgb64) {
			this.imgb64 = imgb64;
		}

		public String getImgVoucher() {
			
			return imgVoucher;
		}

		public void setImgVoucher(String imgVoucher) {
			this.imgVoucher = imgVoucher;
		}

		public String getPdf417() {
			
			return pdf417;
		}

		public void setPdf417(String pdf417) {
			this.pdf417 = pdf417;
		}

		public String getId_transaccion_desc() {
			return id_transaccion_desc;
		}

		public void setId_transaccion_desc(String id_transaccion_desc) {
			this.id_transaccion_desc = id_transaccion_desc;
		}

		public String getDescuento_pedido() {
			return descuento_pedido;
		}

		public void setDescuento_pedido(String descuento_pedido) {
			this.descuento_pedido = descuento_pedido;
		}
	
}
