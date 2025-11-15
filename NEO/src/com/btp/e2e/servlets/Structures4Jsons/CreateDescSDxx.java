package com.btp.e2e.servlets.Structures4Jsons;

import java.util.*;

public class CreateDescSDxx {

	public String Userid;
	public String Method;
	public String Item;

	public static class Material {
		public String matnr;
		public String cantidad;
	}

	public static class Item {
		public String ind_pm;
		public String pm;
		public String id_turno;
		public String werks;
		public String werks_dest;
		public String cliente;
		public String vkorg;
		public String vtweg;
		public String spart;
		public String fecha;
		public String tipo_cliente;
		public String stcd1;
		public String num_oferta;
		public String folioguia;
		public String terminal;
		public String monto_pago;
		public String num_autorizacion;
		public String mail;
		public String m3;
		public String cantidad;
		public String monto_instalacion;
		public String material;
		public String name;
		public String name2;
		public String name3;
		public String ciudad;
		public String tlf;
		public String direccion;
		public List<Material> materials = new ArrayList<Material>();
		public List<String> discounts = new ArrayList<String>();

	}
}
