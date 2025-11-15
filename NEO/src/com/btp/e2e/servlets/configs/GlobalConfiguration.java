package com.btp.e2e.servlets.configs;

import java.util.List;

import com.btp.e2e.servlets.authentication.UserExternal;

public class GlobalConfiguration {
	public static String Usuario = "9000"; // 9000 :PRD 5007 :DEV anterior
	
//	private static String UsuarioWSSLLProd = "Pits"; // Pits :PRD
//	private static String ClaveWSSLLProd = "Pits#2018.,"; // Pits#2018., :PRD
//	public static String UsuarioWS = UsuarioWSSLLProd;
//	public static String ClaveWS = ClaveWSSLLProd;
//
//	private final static String urlBasePrd = "http://wsserver.lipigas.cl";
//	public final static String urlActive = urlBasePrd;
//
//	private static String prdDest = "GWLipigasProd";
//	public static final String door = prdDest;
//
//	private static String prdDB = "PITS_PRD";
//	public static final String now = prdDB;
//
//	public static List<UserExternal> load(List<UserExternal> usersExternal) {
//		UserExternal user2 = new UserExternal();
//		user2.username = "sll001";
//		user2.password = "b3fe488a7543bc83c207a4d8ab7de38f";
//		user2.password = "b3fe488a7543bc83c207a4d8ab7de38$f";
//		usersExternal.add(user2);
//		UserExternal user3 = new UserExternal();
//		user3.username = "sitrack001";
//		// user3.password = "9afee4892fc04f5a1323375d32e1410b";
//		user3.password = "9afee4892fc04f5a1323375d32e1410#b";
//		usersExternal.add(user3);
//		return usersExternal;
//	}
	
	
	//test
	
	private static String UsuarioSLLDev = "pruebas"; // pruebas :DEV
	private static String ClaveWSSLLDev = "pruebas"; // pruebas :DEV
	public static String UsuarioWS = UsuarioSLLDev;
	public static String ClaveWS = ClaveWSSLLDev;
	
	//private final static String urlBaseDev = "http://200.68.17.65";
	private final static String urlBaseDev = "https://wsserverdev.lipigas.cl/";
	//private final static String urlBaseDev = "https://wsserver.lipigas.cl/";
	public final static String urlActive = urlBaseDev;

	private static String devDest = "GW";
	private static String devDestPost = "GW";
	public static final String door = devDest;
	public static final String doorPost = devDestPost;

	private static String devDB = "E2E_DEV";
	public static final String now = devDB;

	public static List<UserExternal> load(List<UserExternal> usersExternal) {
	UserExternal user1 = new UserExternal();
	user1.username = "test";
	user1.password = "test";
	usersExternal.add(user1);
	return usersExternal;
	}
	
}
