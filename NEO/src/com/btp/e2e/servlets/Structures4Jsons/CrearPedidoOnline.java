package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class CrearPedidoOnline extends PojoPrimary {
	private String guia = "";
	private String rut = "";
	private String terminal = "";
	private String numero_camion = "";
	private String telefono = "";
	private String cli_latitud = "";
	private String cli_longitud = "";
	private String fecha = "";
	private String hora = "";
	private List<DetalleVentaProducto> productos = new ArrayList<DetalleVentaProducto>();
	private List<DetalleVenta> detalle = new ArrayList<DetalleVenta>();
	private List<ProductSLL> stock = new ArrayList<ProductSLL>();
	private String pedido = "";
	private String id = "";
	private String folio = "";
	private String desc = "";
	private String cliente = "";
	private String imgb64 = "";
	private String imgVoucher = "";
	private String pdf417 = "";

	private correlativoAWSCORRJson foliado = new correlativoAWSCORRJson();

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

	public String getCli_latitud() {

		return cli_latitud;
	}

	public void setCli_latitud(String cli_latitud) {
		this.cli_latitud = cli_latitud;
	}

	public String getCli_longitud() {

		return cli_longitud;
	}

	public void setCli_longitud(String cli_longitud) {
		this.cli_longitud = cli_longitud;
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

	public String getPedido() {

		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public correlativoAWSCORRJson getFoliado() {
		return foliado;
	}

	public void setFoliado(correlativoAWSCORRJson foliado) {
		this.foliado = foliado;
	}

	private String cu_mail = "";
	private String cu_name = "";
	private String cu_ruc = "";

	public String getCu_mail() {
		return cu_mail;
	}

	public void setCu_mail(String cu_mail) {
		this.cu_mail = cu_mail;
	}

	public String getCu_name() {
		return cu_name;
	}

	public void setCu_name(String cu_name) {
		this.cu_name = cu_name;
	}

	public String getCu_ruc() {
		return cu_ruc;
	}

	public void setCu_ruc(String cu_ruc) {
		this.cu_ruc = cu_ruc;
	}

	private String vtweg_selected = "";

	public String getVtweg_selected() {
		return vtweg_selected;
	}

	public void setVtweg_selected(String vtweg_selected) {
		this.vtweg_selected = vtweg_selected;
	}
}
