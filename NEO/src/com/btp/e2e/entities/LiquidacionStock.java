package com.btp.e2e.entities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.btp.e2e.implementations.PojoPrimary;

public class LiquidacionStock extends PojoPrimary {

	public LiquidacionStock() {
		super();

	}

	private String id;
	private String rut;
	private String terminal;
	private String numero_camion;
	private String stock_detalle;
	private String stock_detalle_original;
	private String stock_detalle_liquidado;
	private String resultado_liquidacion;
	private String timestamp;
	private String folioguia;

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getStock_detalle() {
		
		return stock_detalle;
	}

	public void setStock_detalle(String stock_detalle) {
		this.stock_detalle = stock_detalle;
	}

	public String getStock_detalle_original() {
		
		return stock_detalle_original;
	}

	public void setStock_detalle_original(String stock_detalle_original) {
		this.stock_detalle_original = stock_detalle_original;
	}

	public String getStock_detalle_liquidado() {
		
		return stock_detalle_liquidado;
	}

	public void setStock_detalle_liquidado(String stock_detalle_liquidado) {
		this.stock_detalle_liquidado = stock_detalle_liquidado;
	}

	public String getResultado_liquidacion() {
		
		return resultado_liquidacion;
	}

	public void setResultado_liquidacion(String resultado_liquidacion) {
		this.resultado_liquidacion = resultado_liquidacion;
	}

	public String getTimestamp() {
		
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getFolioguia() {
		
		return folioguia;
	}

	public void setFolioguia(String folioguia) {
		this.folioguia = folioguia;
	}

	public int size() {
		return 7;
	}

	public String get(int index) {
		
		String str = "";
		switch (index) {
		case 1:
			str = rut;
			break;
		case 2:
			str = terminal;
			break;
		case 3:
			str = numero_camion;
			break;
		case 4:
			str = stock_detalle;
			break;
		case 5:
			str = stock_detalle_original;
			break;
		case 6:
			str = stock_detalle_liquidado;
			break;
		case 7:
			str = resultado_liquidacion;
			break;
		case 8:
			str = folioguia;
			break;

		}
		return str;
	}

	@SuppressWarnings("unused")
	private String toMD5(String clave) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(clave.getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		return number.toString(16);

	}

}
