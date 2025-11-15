package com.btp.e2e.servlets.Structures4Jsons;

import java.util.List;

public class Contingencia {
	
	public static class SelectUserByTerminal{
		private String terminal;

		public String getTerminal() {
			return terminal;
		}

		public void setTerminal(String terminal) {
			this.terminal = terminal;
		}
		
	}
	
	
	public static class Material{
		private String matnr;
		private String cantidad;
		public String getMatnr() {
			return matnr;
		}
		public void setMatnr(String matnr) {
			this.matnr = matnr;
		}
		public String getCantidad() {
			return cantidad;
		}
		public void setCantidad(String cantidad) {
			this.cantidad = cantidad;
		}
	}
	
	public static class LOAD_USRSCP_DATA{
		public String DISPLAYNAME = "dummy";
		public String EMAIL = "dummy";
		public String FIRSTNAME = "dummy";
		public String LASTNAME = "dummy";
		public String NAME;
	}
	
	
	public static class KEY{
		private String KEY;
		private String TEXT;
		public String getKEY() {
			return KEY;
		}
		public void setKEY(String kEY) {
			KEY = kEY;
		}
		public String getTEXT() {
			return TEXT;
		}
		public void setTEXT(String tEXT) {
			TEXT = tEXT;
		}
	}
	
	public static class ROLEITEM{
		private String TEXT;
		private String VARBL;
		private List<KEY> KEYS;
		public String getTEXT() {
			return TEXT;
		}
		public void setTEXT(String tEXT) {
			TEXT = tEXT;
		}
		public String getVARBL() {
			return VARBL;
		}
		public void setVARBL(String vARBL) {
			VARBL = vARBL;
		}
		public List<KEY> getKEYS() {
			return KEYS;
		}
		public void setKEYS(List<KEY> kEYS) {
			KEYS = kEYS;
		}
	}
	
	public static class ROLEITEMS{
		public List<ROLEITEM> ROLEITEMS;
	}
	

	public static class Rol{
		String key;
		String text;
	}

	
	public static class Retorno{
		public String lotno = "";
		public String bokno = "";
		public String printer = "";
		
	}
	
	
	public static class CREATE_SELL_SD_KB{
		public String Userid;
		public String Method;
		public String Item;
	}
	
	public static class MSG{
		public String MSG;
	}
	
	public static class DOCTO{
		public String DOCTO;
		public String DTYPE;
	}
	
	public static class ResponseMensaje{
		public List<MSG> MSGS;
		public List<DOCTO> DOCTOS;
		
	}
	
	public static class QUERY_STOCK_CONSIGNADO{
		public String rut;
		public String terminal;
		public String centro;
	}
	
	public static class ResponseStockConsignado{
		public String RESULT;
	}
	
}
