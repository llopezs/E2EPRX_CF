package com.btp.e2e.servlets.Structures4Jsons;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public abstract class UserUpdate {
	
	public class ByTerminalRequest{
		public String terminal;
		public List<Property> properties;
		
	}
	
	public class Property{
		public String property;
		public String value;
	}
	
	/** copia adaptata Usuario **/
	public class Usuario extends PojoPrimary {

		/* PUBLIC */
		public String id;
		public String rut;
		public String clave;
		public String nombre;
		public String telefono;
		public String email;
		public String topeDeuda;
		public String token;
		public String imei;
		public String tipo;
		public String rol;
		public String numero_camion;
		public String terminal;
		public String idpush;
		public String lista_pedido;
		public String bloqueado;
		public String usadescuentos;
		public String centro;
		public String centroDest;
		public String oferta;
		public String timestamp;
		public String ultima_guia;
		public String ultimo_cierre;

		public String deudorsap;
		public String acreedorsap;
		public String transbank;
		
		public String motivoT;
		
		public String getMotivoT() {		
			return motivoT;
		}

		public void setMotivoT(String motivoT) {
			this.motivoT = motivoT;
		}

		public String getUltima_guia() {
			
			return ultima_guia;
		}

		public void setUltima_guia(String ultima_guia) {
			this.ultima_guia = ultima_guia;
		}

		public String getUltimo_cierre() {
			
			return ultimo_cierre;
		}

		public void setUltimo_cierre(String ultimo_cierre) {
			this.ultimo_cierre = ultimo_cierre;
		}

		public String getDeudorsap() {
			
			return deudorsap;
		}

		public void setDeudorsap(String deudorsap) {
			this.deudorsap = deudorsap;
		}

		public String getAcreedorsap() {
			
			return acreedorsap;
		}

		public void setAcreedorsap(String acreedorsap) {
			this.acreedorsap = acreedorsap;
		}

		public String getTransbank() {
			
			return transbank;
		}

		public void setTransbank(String transbank) {
			this.transbank = transbank;
		}

		/**
		 * @return the ncamion
		 */
		public String getNcamion() {
			
			return numero_camion;
		}

		/**
		 * @param ncamion
		 *            the ncamion to set
		 */
		public void setNcamion(String numero_camion) {
			this.numero_camion = numero_camion;
		}

		/**
		 * @return the terminal
		 */
		public String getTerminal() {
			
			return terminal;
		}

		/**
		 * @param terminal
		 *            the terminal to set
		 */
		public void setTerminal(String terminal) {
			this.terminal = terminal;
		}

		/**
		 * @return the idpush
		 */
		public String getIdpush() {
			
			return idpush;
		}

		/**
		 * @param idpush
		 *            the idpush to set
		 */
		public void setIdpush(String idpush) {
			this.idpush = idpush;
		}

		/* PUBLIC */

		public Usuario() {
			super();
		}

		/**
		 * @return the token
		 */
		public String getToken() {
			
			return token;
		}

		public String getId() {
			
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the listaPedidos
		 */
		public String getListaPedidos() {
			
			return lista_pedido;
		}

		/**
		 * @param listaPedidos
		 *            the listaPedidos to set
		 */
		public void setListaPedidos(String lista_pedido) {
			this.lista_pedido = lista_pedido;
		}

		/**
		 * @return the topDeuda
		 */
		public String getTopeDeuda() {
			
			return topeDeuda;
		}

		/**
		 * @param topDeuda
		 *            the topDeuda to set
		 */
		public void setTopeDeuda(String topDeuda) {
			this.topeDeuda = topDeuda;
		}

		/**
		 * @return the rut
		 */
		public String getRut() {
			
			return rut;
		}

		/**
		 * @param rut
		 *            the rut to set
		 */
		public void setRut(String rut) {
			this.rut = rut;
		}

		/**
		 * @return the nombre
		 */
		public String getNombre() {
			
			return nombre;
		}

		/**
		 * @param nombre
		 *            the nombre to set
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		/**
		 * @return the clave
		 */
		public String getClave() {
			
			return clave;
		}

		/**
		 * @param clave
		 *            the clave to set
		 * @throws NoSuchAlgorithmException
		 */
		public void setClave(String clave, boolean offMd5) throws NoSuchAlgorithmException {
			if (!(offMd5)) {
				this.clave = this.toMD5(clave);
			} else {
				this.clave = clave;
			}
		}

		/**
		 * @return the telefono
		 */
		public String getTelefono() {
			
			return telefono;
		}

		/**
		 * @param telefono
		 *            the telefono to set
		 */
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		/**
		 * @return the bloqueado
		 */
		public String getBloqueado() {
			
			return bloqueado;
		}

		/**
		 * @param bloqueado
		 *            the bloqueado to set
		 */
		public void setBloqueado(String bloqueado) {
			this.bloqueado = bloqueado;
		}

		/**
		 * @return the tipo
		 */
		public String getTipo() {
			
			return tipo;
		}

		/**
		 * @param tipo
		 *            the tipo to set
		 */
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		/**
		 * @return the rol
		 */
		public String getRol() {
			
			return rol;
		}

		/**
		 * @param rol
		 *            the rol to set
		 */
		public void setRol(String rol) {
			this.rol = rol;
		}

		/**
		 * @param token
		 *            the token to set
		 */
		public void setToken(String token) {
			this.token = token;
		}

		/**
		 * @return the imei
		 */
		public String getImei() {
			
			return imei;
		}

		/**
		 * @param imei
		 *            the imei to set
		 */
		public void setImei(String imei) {
			this.imei = imei;
		}

		public String getEmail() {
			
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getUsadescuento() {
			
			return usadescuentos;
		}

		public void setUsadescuento(String usadescuentos) {
			this.usadescuentos = usadescuentos;
		}

		public String getTimestamp() {
			
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		private String toMD5(String clave) throws NoSuchAlgorithmException {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(clave.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			return number.toString(16);

		}
		
		/*public boolean checkVersionTerminal(String versionName, String camion, List<VersionHandler> versions) throws NoSuchAlgorithmException {
			boolean bReturn = false;
			//CAMION ASD ES REPARTO DIRECTO
			//DISTRIBUIDOR ES CAMION VACIO
			//SE IMPLANTA PRIMERO REPARTO DIRECTO
			if(camion.equals("")){
				for(VersionHandler verh : versions){
					if(verh.getVersionName().equals(versionName)){
						bReturn = true;
						return bReturn;
					}
				}
			} else {
				bReturn = true;
			}

			return bReturn;
		}*/
		
		public boolean checkCanalVenta(String tipoUser, String camion) throws NoSuchAlgorithmException {
			boolean bReturn = false;
			
			if(((tipoUser.equals("001") || tipoUser.equals("002")) && camion.equals("asd")) || (tipoUser.equals("003") && camion.equals(""))){
				bReturn = true;
			}

			return bReturn;
		}

		public boolean authClave(String clave) throws NoSuchAlgorithmException {

			boolean bReturn = false;
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(clave.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String claveHashed = number.toString(16);
			bReturn = claveHashed.equals(this.getClave());
			return bReturn;

		}

		public boolean newAuthClave(String clave) throws NoSuchAlgorithmException {

			boolean bReturn = false;
			// MessageDigest md = MessageDigest.getInstance("MD5");
			// byte[] messageDigest = md.digest(clave.getBytes());
			// BigInteger number = new BigInteger(1, messageDigest);
			// String claveHashed = number.toString(16);
			String claveHashed = clave;
			bReturn = claveHashed.equals(this.getClave());
			return bReturn;

		}

		/**
		 * @return the numero_camion
		 */
		public String getNumero_camion() {
			
			return numero_camion;
		}

		/**
		 * @param numero_camion
		 *            the numero_camion to set
		 */
		public void setNumero_camion(String numero_camion) {
			this.numero_camion = numero_camion;
		}

		/**
		 * @return the lista_pedido
		 */
		public String getLista_pedido() {
			
			return lista_pedido;
		}

		/**
		 * @param lista_pedido
		 *            the lista_pedido to set
		 */
		public void setLista_pedido(String lista_pedido) {
			this.lista_pedido = lista_pedido;
		}

		/**
		 * @return the usadescuentos
		 */
		public String getUsadescuentos() {
			
			return usadescuentos;
		}

		/**
		 * @param usadescuentos
		 *            the usadescuentos to set
		 */
		public void setUsadescuentos(String usadescuentos) {
			this.usadescuentos = usadescuentos;
		}

		/**
		 * @return the centro
		 */
		public String getCentro() {
			
			return centro;
		}

		/**
		 * @param centro
		 *            the centro to set
		 */
		public void setCentro(String centro) {
			this.centro = centro;
		}

		/**
		 * @return the centroDest
		 */
		public String getCentroDest() {
			return centroDest;
		}

		/**
		 * @param centroDest
		 *            the centroDest to set
		 */
		public void setCentroDest(String centroDest) {
			this.centroDest = centroDest;
		}

		/**
		 * @return the oferta
		 */
		public String getOferta() {
			
			return oferta;
		}

		/**
		 * @param oferta
		 *            the oferta to set
		 */
		public void setOferta(String oferta) {
			this.oferta = oferta;
		}
		

	}

}
