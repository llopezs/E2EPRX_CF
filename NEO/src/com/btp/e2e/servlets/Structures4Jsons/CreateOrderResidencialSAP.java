package com.btp.e2e.servlets.Structures4Jsons;

import java.util.ArrayList;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class CreateOrderResidencialSAP extends PojoPrimary {
	private String id_pedido = "";
	private String pedido_sll = "";
	private String tipo_pedido = "";
	private String web = "";
	private String tipo_usuario = "";
	private String ds_deudor = "";
	private String ds_acreedor = "";
	private String descuento_condicion = "";
	private String monto_descuento_cilindro = "";
	private String monto_difvta = "";
	private String descuento_pedido = "";
	private String monto_descuento_dev_garantia = "";
	private String total_pedido = "";
	private String monto_descuento_gas = "";
	private String codigo_descuento = "";
	private String cod_condicion = "";
	private String total_pagar = "";
	private String monto_prepagado = "";
	private String cant_dev_garantia = "";
	private String folioguia = "";
	private String terminal = "";
	private String rut = "";
	private String num_oferta = "";
	private List<MaterialOrder> materials = new ArrayList<MaterialOrder>();
	private String werks = "";
	private String cliente = "";
	private String vkorg = "";
	private String vtweg = "";
	private String spart = "";
	private String tipo_cliente = "";
	private String stcd1 = "";
	private String mail = "";
	private String name = "";
	private String name2 = "";
	private String name3 = "";
	private String ciudad = "";
	private String tlf = "";
	private String direccion = "";
	private String folio = "";
	private String desc = "";
	private String rut_op = "";
	private String efectivo = "";
	private String cheque = "";
	private String transbank = "";
	private String id_venta = "";
	private String id_cierre = "";
	private String id_turno = "";
	private String fecha_comp_pvm = "";
	private String hora_comp_pvm = "";
	private String medio_pago = "";
	private String monto_medio_pago = "";
	private String coordenadas = "";
	private String bsark = "";
	private String comuna = "";
	private List<DetalleVenta> detalle = new ArrayList<DetalleVenta>();

	public List<DetalleVenta> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleVenta> detalle) {
		this.detalle = detalle;
	}

	private String xy = "";
	private String contado = "";
	/* consulta para cliente comercial */
	private String accion = "";
	private String dm_especial = "";
	private String dm = "";
	private String codigo_cliente = "";
	private String imgb64 = "";

	private correlativoAWSCORRJson foliado = new correlativoAWSCORRJson();

	public String getId_pedido() {

		return id_pedido;
	}

	public void setId_pedido(String id_pedido) {
		this.id_pedido = id_pedido;
	}

	public String getPedido_sll() {

		return pedido_sll;
	}

	public void setPedido_sll(String pedido_sll) {
		this.pedido_sll = pedido_sll;
	}

	public String getTipo_pedido() {

		return tipo_pedido;
	}

	public void setTipo_pedido(String tipo_pedido) {
		this.tipo_pedido = tipo_pedido;
	}

	public String getWeb() {

		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getTipo_usuario() {

		return tipo_usuario;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public String getDs_deudor() {

		return ds_deudor;
	}

	public void setDs_deudor(String ds_deudor) {
		this.ds_deudor = ds_deudor;
	}

	public String getDs_acreedor() {

		return ds_acreedor;
	}

	public void setDs_acreedor(String ds_acreedor) {
		this.ds_acreedor = ds_acreedor;
	}

	public String getDescuento_condicion() {

		return descuento_condicion;
	}

	public void setDescuento_condicion(String descuento_condicion) {
		this.descuento_condicion = descuento_condicion;
	}

	public String getMonto_descuento_cilindro() {

		return monto_descuento_cilindro;
	}

	public void setMonto_descuento_cilindro(String monto_descuento_cilindro) {
		this.monto_descuento_cilindro = monto_descuento_cilindro;
	}

	public String getMonto_difvta() {

		return monto_difvta;
	}

	public void setMonto_difvta(String monto_difvta) {
		this.monto_difvta = monto_difvta;
	}

	public String getDescuento_pedido() {

		return descuento_pedido;
	}

	public void setDescuento_pedido(String descuento_pedido) {
		this.descuento_pedido = descuento_pedido;
	}

	public String getMonto_descuento_dev_garantia() {

		return monto_descuento_dev_garantia;
	}

	public void setMonto_descuento_dev_garantia(String monto_descuento_dev_garantia) {
		this.monto_descuento_dev_garantia = monto_descuento_dev_garantia;
	}

	public String getTotal_pedido() {

		return total_pedido;
	}

	public void setTotal_pedido(String total_pedido) {
		this.total_pedido = total_pedido;
	}

	public String getMonto_descuento_gas() {

		return monto_descuento_gas;
	}

	public void setMonto_descuento_gas(String monto_descuento_gas) {
		this.monto_descuento_gas = monto_descuento_gas;
	}

	public String getCodigo_descuento() {

		return codigo_descuento;
	}

	public void setCodigo_descuento(String codigo_descuento) {
		this.codigo_descuento = codigo_descuento;
	}

	public String getCod_condicion() {

		return cod_condicion;
	}

	public void setCod_condicion(String cod_condicion) {
		this.cod_condicion = cod_condicion;
	}

	public String getTotal_pagar() {

		return total_pagar;
	}

	public void setTotal_pagar(String total_pagar) {
		this.total_pagar = total_pagar;
	}

	public String getMonto_prepagado() {

		return monto_prepagado;
	}

	public void setMonto_prepagado(String monto_prepagado) {
		this.monto_prepagado = monto_prepagado;
	}

	public String getCant_dev_garantia() {

		return cant_dev_garantia;
	}

	public void setCant_dev_garantia(String cant_dev_garantia) {
		this.cant_dev_garantia = cant_dev_garantia;
	}

	public String getFolioguia() {

		return folioguia;
	}

	public void setFolioguia(String folioguia) {
		this.folioguia = folioguia;
	}

	public String getTerminal() {

		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getRut() {

		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNum_oferta() {

		return num_oferta;
	}

	public void setNum_oferta(String num_oferta) {
		this.num_oferta = num_oferta;
	}

	public List<MaterialOrder> getMaterials() {

		return materials;
	}

	public void setMaterials(List<MaterialOrder> materials) {
		this.materials = materials;
	}

	public String getWerks() {

		return werks;
	}

	public void setWerks(String werks) {
		this.werks = werks;
	}

	public String getCliente() {

		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getVkorg() {

		return vkorg;
	}

	public void setVkorg(String vkorg) {
		this.vkorg = vkorg;
	}

	public String getVtweg() {

		return vtweg;
	}

	public void setVtweg(String vtweg) {
		this.vtweg = vtweg;
	}

	public String getSpart() {

		return spart;
	}

	public void setSpart(String spart) {
		this.spart = spart;
	}

	public String getTipo_cliente() {

		return tipo_cliente;
	}

	public void setTipo_cliente(String tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}

	public String getStcd1() {

		return stcd1;
	}

	public void setStcd1(String stcd1) {
		this.stcd1 = stcd1;
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

	public String getName2() {

		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {

		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getCiudad() {

		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTlf() {

		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public String getDireccion() {

		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public String getRut_op() {

		return rut_op;
	}

	public void setRut_op(String rut_op) {
		this.rut_op = rut_op;
	}

	public String getEfectivo() {

		return efectivo;
	}

	public void setEfectivo(String efectivo) {
		this.efectivo = efectivo;
	}

	public String getCheque() {

		return cheque;
	}

	public void setCheque(String cheque) {
		this.cheque = cheque;
	}

	public String getTransbank() {

		return transbank;
	}

	public void setTransbank(String transbank) {
		this.transbank = transbank;
	}

	public String getId_venta() {

		return id_venta;
	}

	public void setId_venta(String id_venta) {
		this.id_venta = id_venta;
	}

	public String getId_cierre() {

		return id_cierre;
	}

	public void setId_cierre(String id_cierre) {
		this.id_cierre = id_cierre;
	}

	public String getId_turno() {

		return id_turno;
	}

	public void setId_turno(String id_turno) {
		this.id_turno = id_turno;
	}

	public String getFecha_comp_pvm() {

		return fecha_comp_pvm;
	}

	public void setFecha_comp_pvm(String fecha_comp_pvm) {
		this.fecha_comp_pvm = fecha_comp_pvm;
	}

	public String getHora_comp_pvm() {

		return hora_comp_pvm;
	}

	public void setHora_comp_pvm(String hora_comp_pvm) {
		this.hora_comp_pvm = hora_comp_pvm;
	}

	public String getMedio_pago() {

		return medio_pago;
	}

	public void setMedio_pago(String medio_pago) {
		this.medio_pago = medio_pago;
	}

	public String getMonto_medio_pago() {

		return monto_medio_pago;
	}

	public void setMonto_medio_pago(String monto_medio_pago) {
		this.monto_medio_pago = monto_medio_pago;
	}

	public String getXy() {

		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getContado() {

		return contado;
	}

	public void setContado(String contado) {
		this.contado = contado;
	}

	public String getAccion() {

		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getDm_especial() {

		return dm_especial;
	}

	public void setDm_especial(String dm_especial) {
		this.dm_especial = dm_especial;
	}

	public String getDm() {

		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getCodigo_cliente() {

		return codigo_cliente;
	}

	public void setCodigo_cliente(String codigo_cliente) {
		this.codigo_cliente = codigo_cliente;
	}

	public String getImgb64() {

		return imgb64;
	}

	public void setImgb64(String imgb64) {
		this.imgb64 = imgb64;
	}

	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	public String getBsark() {
		return bsark;
	}

	public void setBsark(String bsark) {
		this.bsark = bsark;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public correlativoAWSCORRJson getFoliado() {
		return foliado;
	}

	public void setFoliado(correlativoAWSCORRJson foliado) {
		this.foliado = foliado;
	}

	private String cu_mail = "";
	private String cu_ruc = "";
	private String cu_name = "";

	public String getCu_mail() {
		return cu_mail;
	}

	public void setCu_mail(String cu_mail) {
		this.cu_mail = cu_mail;
	}

	public String getCu_ruc() {
		return cu_ruc;
	}

	public void setCu_ruc(String cu_ruc) {
		this.cu_ruc = cu_ruc;
	}

	public String getCu_name() {
		return cu_name;
	}

	public void setCu_name(String cu_name) {
		this.cu_name = cu_name;
	}

	private String vtweg_selected = "";

	public String getVtweg_selected() {
		return vtweg_selected;
	}

	public void setVtweg_selected(String vtweg_selected) {
		this.vtweg_selected = vtweg_selected;
	}

	private String gratuidad = "";

	public String getGratuidad() {
		return gratuidad;
	}

	public void setGratuidad(String gratuidad) {
		this.gratuidad = gratuidad;
	}
}