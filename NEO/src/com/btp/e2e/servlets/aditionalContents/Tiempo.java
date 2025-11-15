package com.btp.e2e.servlets.aditionalContents;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Tiempo {

	public static String getNTPDateCL(String parametroCambioHorario) {
		String rtrn = "";
		Timestamp timestamp = null;
		if (parametroCambioHorario == null) {
			parametroCambioHorario = "3";
		}
		if (parametroCambioHorario.equals("0")) {
			parametroCambioHorario = "3";
		}
		if (parametroCambioHorario.equals("")) {
			parametroCambioHorario = "3";
		}
		try {
			long offsetTime = (60000 * (60 * Integer.parseInt(parametroCambioHorario))); // + ( 60000 * (3) ) + ( 36000
			Calendar calendar = new GregorianCalendar();
			TimeZone timeZone = TimeZone.getTimeZone("America/Santiago");
			calendar.setTimeZone(timeZone);
			timestamp = new Timestamp(calendar.getTimeInMillis() - offsetTime);
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			long offsetTime = (60000 * (60 * Integer.parseInt(parametroCambioHorario)));
			Calendar calendar = new GregorianCalendar();
			TimeZone timeZone = TimeZone.getTimeZone("America/Santiago");
			calendar.setTimeZone(timeZone);
			timestamp = new Timestamp(calendar.getTimeInMillis() - offsetTime);
		}
//		System.out.println("GETTING_TIMESTAMP_TO_STRING=>" + timestamp.toString());

		rtrn = timestamp.toString();
		String tmp1 = rtrn.substring(0,10)+"T";
		String tmp2 = rtrn.substring(11,rtrn.length())+"Z";
		return tmp1+tmp2;
	}

	public static String getNTPDateCL4SLL(String parametroCambioHorario) {
		if (parametroCambioHorario == null) {
			parametroCambioHorario = "3";
		}
		if (parametroCambioHorario.equals("0")) {
			parametroCambioHorario = "3";
		}
		if (parametroCambioHorario.equals("")) {
			parametroCambioHorario = "3";
		}
		// String[] hosts = new String[] { "ntp.shoa.cl" };
		//
		// NTPUDPClient client = new NTPUDPClient();
		// client.setDefaultTimeout(10000);
		// SimpleDateFormat OutPutFormat = new SimpleDateFormat(
		// "dd/MM/yyyy HH:mm:ss", java.util.Locale.getDefault());
		// for (String host : hosts) {
		// try {
		long offsetTime = (60000 * (60 * Integer.parseInt(parametroCambioHorario))); // + ( 60000 * (3) ) + ( 36000 ); 3
																						// horario de verano y 4 horario
																						// de invierno
		// InetAddress hostAddr = InetAddress.getByName(host);
		// Date date = null;
		// try {
		// TimeInfo info = client.getTime(hostAddr);
		// date = (new Date(info.getReturnTime() - offsetTime));
		// client.close();
		// }
		// catch (Exception e) {
		// date = new Date();
		// }
		// String out = OutPutFormat.format(date);
		// return out;
		// } catch (IOException e) {
		// client.close();
		// e.printStackTrace();
		// return "";
		// }
		// }
		// client.close();
		// return "";
		SimpleDateFormat OutPutFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", java.util.Locale.getDefault());
		Calendar calendar = new GregorianCalendar();
		TimeZone timeZone = TimeZone.getTimeZone("America/Santiago");
		calendar.setTimeZone(timeZone);
		// Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
		Date date = new Date(calendar.getTimeInMillis() - offsetTime);
		return OutPutFormat.format(date);
	}

	public static String getOutDatedTimePlusParameter(int days, String parametroCambioHorario) {
		if (parametroCambioHorario == null) {
			parametroCambioHorario = "3";
		}
		if (parametroCambioHorario.equals("0")) {
			parametroCambioHorario = "3";
		}
		if (parametroCambioHorario.equals("")) {
			parametroCambioHorario = "3";
		}
		// String[] hosts = new String[] { "ntp.shoa.cl" };
		//
		// NTPUDPClient client = new NTPUDPClient();
		// client.setDefaultTimeout(10000);
		// for (String host : hosts) {
		// try {
		// long offsetTime = ( 60000 * (60 * Integer.parseInt(parametroCambioHorario))
		// ); //+ ( 60000 * (3) ) + ( 36000 );
		// long plusDaysLong = ( 60000 * (60 * 24) ) * days;
		// InetAddress hostAddr = InetAddress.getByName(host);
		// Date date = null;
		// try {
		// TimeInfo info = client.getTime(hostAddr);
		// date = (new Date(info.getReturnTime() - offsetTime + plusDaysLong));
		// client.close();
		// }
		// catch (Exception e) {
		// date = new Date();
		// }
		// java.sql.Timestamp tm = new java.sql.Timestamp(date.getTime());
		// String out = tm.toString();
		// return out;
		// } catch (IOException e) {
		// client.close();
		// e.printStackTrace();
		// return "";
		// }
		// }
		// client.close();
		// return "";

		Calendar calendar = new GregorianCalendar();
		TimeZone timeZone = TimeZone.getTimeZone("America/Santiago");
		calendar.setTimeZone(timeZone);
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
		return timestamp.toString();
	}

	public static Timestamp getOutDatedTimePlusParameterT(int days, String parametroCambioHorario) {
		if (parametroCambioHorario == null) {
			parametroCambioHorario = "3";
		}
		if (parametroCambioHorario.equals("0")) {
			parametroCambioHorario = "3";
		}
		if (parametroCambioHorario.equals("")) {
			parametroCambioHorario = "3";
		}
		// String[] hosts = new String[] { "ntp.shoa.cl" };
		// java.sql.Timestamp tm = null;
		// NTPUDPClient client = new NTPUDPClient();
		// client.setDefaultTimeout(10000);
		// for (String host : hosts) {
		// try {
		long offsetTime = (60000 * (60 * Integer.parseInt(parametroCambioHorario))); // + ( 60000 * (3) ) + ( 36000 );
		long plusDaysLong = (60000 * (60 * 24)) * days;
		// InetAddress hostAddr = InetAddress.getByName(host);
		// Date date = null;
		// try {
		// TimeInfo info = client.getTime(hostAddr);
		Calendar calendar = new GregorianCalendar();
		TimeZone timeZone = TimeZone.getTimeZone("America/Santiago");
		calendar.setTimeZone(timeZone);
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis() - offsetTime + plusDaysLong);
		// date = (new Date(info.getReturnTime() - offsetTime + plusDaysLong));
		// client.close();
		// }
		// catch (Exception e) {
		// date = new Date();
		// }
		// tm = new java.sql.Timestamp(date.getTime());
		return timestamp;
		// } catch (IOException e) {
		// client.close();
		// e.printStackTrace();
		// return null;
		// }
		// }
		// client.close();
		// return null;
	}

	public static Timestamp getActualDateTimestamp(String parametroCambioHorario) {
		if (parametroCambioHorario == null) {
			parametroCambioHorario = "3";
		}
		if (parametroCambioHorario.equals("0")) {
			parametroCambioHorario = "3";
		}
		if (parametroCambioHorario.equals("")) {
			parametroCambioHorario = "3";
		}
		// String[] hosts = new String[] { "ntp.shoa.cl" };
		// Timestamp tm = null;
		// NTPUDPClient client = new NTPUDPClient();
		// client.setDefaultTimeout(10000);
		// for (String host : hosts) {
		// try {
		long offsetTime = (60000 * (60 * Integer.parseInt(parametroCambioHorario))); // + ( 60000 * (3) ) + ( 36000 ); 3
																						// horario de verano y 4 horario
																						// de invierno
		// InetAddress hostAddr = InetAddress.getByName(host);
		// Date date = null;
		// try {
		// TimeInfo info = client.getTime(hostAddr);
		// date = (new Date(info.getReturnTime() - offsetTime));
		// client.close();
		// }
		// catch (Exception e) {
		// date = new Date();
		// }client.close();
		// tm = new Timestamp(date.getTime());
		// return tm;
		// } catch (IOException e) {
		// client.close();
		// e.printStackTrace();
		// return tm;
		// }
		// }
		// client.close();
		// return tm;
		Calendar calendar = new GregorianCalendar();
		TimeZone timeZone = TimeZone.getTimeZone("America/Santiago");
		calendar.setTimeZone(timeZone);
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis() - offsetTime);
		return timestamp;
	}

}
