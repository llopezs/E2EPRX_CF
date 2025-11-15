package com.btp.e2e.servlets.pushNotifications;

import com.btp.e2e.entities.Pedido;
import com.btp.e2e.servlets.Structures4Jsons.clusterNotif;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class PushNot {

	private final static String sKey = "AAAA_X4Zloc:APA91bFCAvX7jZfg_ugZWnmdJ_V_ho3QqatV7nEFnSZQAX9455sghB7Ucg7CGiuy2zGRinYZBjg08ZPHEUQpCTh9Er7KjfO2ijTYcnV9M-8IRMC5sfdwJohop-6kBTufHSNOf3ywQn_B";// "AAAAsn8hgCY:APA91bGvX3zdPTrw3ee6qStMfKcHJuwIQKRYsZAAUiCA2ZxswerfnFNo5BOJfgqR4jSdQmGY9hdN3143TVZxQdjRm5I_PWxaecSDMeVoC1VkaIm_02m4S-ucfAMXL3sz-TabcZWrjQO6";
	private final static String urlService = "https://fcm.googleapis.com/fcm/send";
	@SuppressWarnings("unused")
	private static BASE64Decoder decoder = new BASE64Decoder();
	private static BASE64Encoder encoder = new BASE64Encoder();

	@SuppressWarnings({})
	public PushNot() {
		super();
	}

	public static String sendPushSuccessfulLogin(String idpush, String nombre, int parametroIntentoNotificacion,
			int parametroTiempoNotificacion) throws ClientProtocolException, IOException, InterruptedException {
		String objecToReturn = "";
		HttpPost postRequest = new HttpPost(urlService);

		PushNotRequest notificationRequestModel = new PushNotRequest();
		PushNotNotification notificationNotification = new PushNotNotification();

		notificationNotification.setBody("Bienvenido a la aplicacion de PVM Lipigas " + nombre);
		notificationNotification.setTitle("Acceso exitoso.");

		notificationRequestModel.setNotification(notificationNotification);
		notificationRequestModel.setTo(idpush);

		Gson gson = new Gson();
		Type type = new TypeToken<PushNotRequest>() {
		}.getType();

		String json = gson.toJson(notificationRequestModel, type);

		StringEntity input = new StringEntity(json);
		input.setContentType("application/json");
		input.setContentEncoding("ISO-8859-1");

		postRequest.addHeader("Authorization", "key=" + sKey);
		postRequest.setEntity(input);
		DefaultHttpClient httpClient = new DefaultHttpClient();

		TimeUnit.SECONDS.sleep(3);
		HttpResponse response = httpClient.execute(postRequest);

		if (response.getStatusLine().getStatusCode() != 200) {

			objecToReturn = "Failed : HTTP error code : " + response.getStatusLine().getStatusCode();

		} else if (response.getStatusLine().getStatusCode() == 200) {

			objecToReturn = "response:" + EntityUtils.toString(response.getEntity());

		}
		httpClient.close();
		return objecToReturn;
	}

	public static String sendPushFailureLogin(String idpush, String nombre, int parametroIntentoNotificacion,
			int parametroTiempoNotificacion) throws ClientProtocolException, IOException, InterruptedException {
		String objecToReturn = "";
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(urlService);

		PushNotRequest notificationRequestModel = new PushNotRequest();
		PushNotNotification notificationNotification = new PushNotNotification();

		notificationNotification.setBody("Error de autenticacion, usuario o contrase�a erroneos.");
		notificationNotification.setTitle("Acceso fallido.");

		notificationRequestModel.setNotification(notificationNotification);
		notificationRequestModel.setTo(idpush);

		Gson gson = new Gson();
		Type type = new TypeToken<PushNotRequest>() {
		}.getType();

		String json = gson.toJson(notificationRequestModel, type);

		StringEntity input = new StringEntity(json);
		input.setContentType("application/json");
		input.setContentEncoding("ISO-8859-1");

		postRequest.addHeader("Authorization", "key=" + sKey);
		postRequest.setEntity(input);

		TimeUnit.SECONDS.sleep(3);
		HttpResponse response = httpClient.execute(postRequest);

		if (response.getStatusLine().getStatusCode() != 200) {

			objecToReturn = "Failed : HTTP error code : " + response.getStatusLine().getStatusCode();

		} else if (response.getStatusLine().getStatusCode() == 200) {

			objecToReturn = "response:" + EntityUtils.toString(response.getEntity());

		}
		httpClient.close();
		return objecToReturn;
	}

	// public static void sendPushOrderAssigned(String idpush, String jsonOrder,
	// int parametroIntentoNotificacion, int parametroTiempoNotificacion, String
	// sy_proc) throws ClientProtocolException, IOException,
	// InterruptedException {
	public static String sendPushOrderAssigned(String idpush, String jsonOrder, int parametroIntentoNotificacion,
			int parametroTiempoNotificacion, String sy_proc)
			throws ClientProtocolException, IOException, InterruptedException {

		Gson gson = new Gson();
		Pedido oEntity = gson.fromJson(jsonOrder, Pedido.class);
		oEntity.checkChars();
		oEntity.getCliente().checkChars();
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(urlService);

		PushNotRequest notificationRequestModel = new PushNotRequest();
		PushNotData notificationData = new PushNotData();
		PushNotNotification notificationNotification = new PushNotNotification();

		String entregarA = "Entregar a: " + oEntity.getCliente().getNombre();

		String entregarEn = ", direccion: " + oEntity.getCliente().getComuna() + ", " + oEntity.getCliente().getCalle()
				+ " " + oEntity.getCliente().getNumero();

		if (oEntity.getCliente().getCasa() != null && oEntity.getCliente().getCasa().length() >= 0) {
			entregarEn += ", casa " + oEntity.getCliente().getCasa();
		} else if (oEntity.getCliente().getDepto() != null && oEntity.getCliente().getDepto().length() >= 0) {
			entregarEn += ", depto " + oEntity.getCliente().getDepto();
		} else {

		}

		notificationNotification.setBody(entregarA + entregarEn);
		notificationNotification.setTitle("Pedido Asignado");

		notificationData.setJson("{\"id\":\"" + sy_proc + "\",\"json\":\"" + encodeB64(jsonOrder) + "\"}");
		// notificationData.setJson("{\"id\":\""+sy_proc+"\",\"json\":\""+encodeB64(jsonOrder)+"\",\"android\":{\"priority\":\"high\",\"ttl\":\"0s\"}}");

		AndroidStructures android = new AndroidStructures();
		android.setPriority("High");
		android.setTtl("0s");

		notificationRequestModel.setAndroid(android);
		notificationRequestModel.setData(notificationData);
		notificationRequestModel.setNotification(notificationNotification);
		notificationRequestModel.setTo(idpush);

		Type type = new TypeToken<PushNotRequest>() {
		}.getType();

		String json = gson.toJson(notificationRequestModel, type);

		StringEntity input = new StringEntity(json);
		input.setContentType("application/json");
		input.setContentEncoding("ISO-8859-1");

		postRequest.addHeader("Authorization", "key=" + sKey);
		postRequest.setEntity(input);

		TimeUnit.SECONDS.sleep(10);
		HttpResponse response = httpClient.execute(postRequest);
		int count = 0;

		String sError = "";

		if (response.getStatusLine().getStatusCode() != 200) {
			// int count = 0;
			sError = response.getStatusLine().getReasonPhrase();

			while (response.getStatusLine().getStatusCode() != 200 || count < parametroIntentoNotificacion) {

				sError = sError + " - " + response.getStatusLine().getReasonPhrase();

				response = httpClient.execute(postRequest);
				count++;
				TimeUnit.SECONDS.sleep(parametroTiempoNotificacion);
			}
		}
		httpClient.close();
		if (sError.equals("")) {
			return "{\"count\":\"" + count + "\",\"status\":\"" + response + "\",\"json\":\"" + json + "\"}";
		} else {
			return "{\"count\":\"" + count + "\",\"status\":\"" + response + "\",\"error\":\"" + sError + "\"}";
		}

	}

	public static void sendPushOrderToRemove(String idpush, String jsonOrder, int parametroIntentoNotificacion,
			int parametroTiempoNotificacion, String sy_proc)
			throws ClientProtocolException, IOException, InterruptedException {

		Gson gson = new Gson();
		Pedido oEntity = gson.fromJson(jsonOrder, Pedido.class);
		oEntity.checkChars();
		oEntity.getCliente().checkChars();
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(urlService);

		PushNotRequest notificationRequestModel = new PushNotRequest();
		PushNotData notificationData = new PushNotData();
		PushNotNotification notificationNotification = new PushNotNotification();

		String entregarA = "Entregar a: " + oEntity.getCliente().getNombre();

		String entregarEn = ", direccion: " + oEntity.getCliente().getComuna() + ", " + oEntity.getCliente().getCalle()
				+ " " + oEntity.getCliente().getNumero();

		if (oEntity.getCliente().getCasa() != null && oEntity.getCliente().getCasa().length() >= 0) {
			entregarEn += ", casa " + oEntity.getCliente().getCasa();
		} else if (oEntity.getCliente().getDepto() != null && oEntity.getCliente().getDepto().length() >= 0) {
			entregarEn += ", depto " + oEntity.getCliente().getDepto();
		} else {

		}

		notificationNotification.setBody(entregarA + entregarEn);
		notificationNotification.setTitle("Pedido Anulado");

		notificationData.setJson("{\"id\":\"" + sy_proc + "\",\"json\":\"" + encodeB64(jsonOrder) + "\"}");

		notificationRequestModel.setData(notificationData);
		notificationRequestModel.setNotification(notificationNotification);
		notificationRequestModel.setTo(idpush);

		Type type = new TypeToken<PushNotRequest>() {
		}.getType();

		String json = gson.toJson(notificationRequestModel, type);

		StringEntity input = new StringEntity(json);
		input.setContentType("application/json");
		input.setContentEncoding("ISO-8859-1");

		postRequest.addHeader("Authorization", "key=" + sKey);
		postRequest.setEntity(input);

		HttpResponse response = httpClient.execute(postRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			int count = 0;
			while (response.getStatusLine().getStatusCode() != 200 || count < parametroIntentoNotificacion) {
				response = httpClient.execute(postRequest);
				count++;
				TimeUnit.SECONDS.sleep(1);
			}
		}
		httpClient.close();

	}

	public static void sendPushNotByThisMsg(String idpush, String msg, String title, int parametroIntentoNotificacion,
			int parametroTiempoNotificacion) throws ClientProtocolException, IOException, InterruptedException {

		Gson gson = new Gson();
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(urlService);

		PushNotRequest notificationRequestModel = new PushNotRequest();
		PushNotNotification notificationNotification = new PushNotNotification();

		notificationNotification.setBody(msg);
		notificationNotification.setTitle(title);

		notificationRequestModel.setNotification(notificationNotification);
		notificationRequestModel.setTo(idpush);

		Type type = new TypeToken<PushNotRequest>() {
		}.getType();

		String json = gson.toJson(notificationRequestModel, type);

		StringEntity input = new StringEntity(json);
		input.setContentType("application/json");
		input.setContentEncoding("ISO-8859-1");

		postRequest.addHeader("Authorization", "key=" + sKey);
		postRequest.setEntity(input);

		TimeUnit.SECONDS.sleep(3);
		HttpResponse response = httpClient.execute(postRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			int count = 0;
			while (response.getStatusLine().getStatusCode() != 200 || count < parametroIntentoNotificacion) {
				response = httpClient.execute(postRequest);
				count++;
				TimeUnit.SECONDS.sleep(parametroTiempoNotificacion);
			}

		}
		httpClient.close();
	}

	/// Nuevo servicio para notificaciones PUSH

	public static int sendNotClusters(String idpush, String json, String typeClust, String msg,
			int parametroIntentoNotificacion, int parametroTiempoNotificacion)
			throws ClientProtocolException, IOException, InterruptedException {

		Gson gson = new Gson();
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(urlService);
		clusterNotif notif = new Gson().fromJson(json, clusterNotif.class);
		PushNotRequest notificationRequestModel = new PushNotRequest();
		PushNotNotification notificationNotification = new PushNotNotification();
		PushNotData notificationData = new PushNotData();

		notificationData.setJson("{\"id\":\"" + typeClust + "\",\"json\":\"" + encodeB64(json) + "\"}");
		notificationNotification.setBody(msg);
		notificationRequestModel.setData(notificationData);
		notificationRequestModel.setNotification(notificationNotification);

		switch (typeClust) {
		case "1":
			notificationNotification.setTitle("Fusion de cluster");
			break;
		case "2":
			notificationNotification.setTitle("Desfusion de cluster");
			break;
		case "3":
			notificationNotification.setTitle("Colacion");
			break;
		case "4":
			notificationNotification.setTitle("Recarga");
			break;
		case "5":
			notificationNotification.setTitle("Desconexion Colacion");
			String jsonColacion = "{'msg':'" + msg + "', 'minutes':'" + notif.getMinute() + "'}";
			notificationNotification.setBody(jsonColacion);
			break;
		case "6":
			notificationNotification.setTitle("Desconexion Recarga");
			break;
		case "7":
			notificationNotification.setTitle("Desconexion Monitoreo");
			break;

		default:
			break;
		}

		notificationRequestModel.setNotification(notificationNotification);
		notificationRequestModel.setTo(idpush);

		Type type = new TypeToken<PushNotRequest>() {
		}.getType();

		String jsonParse = gson.toJson(notificationRequestModel, type);

		StringEntity input = new StringEntity(jsonParse);
		input.setContentType("application/json");
		input.setContentEncoding("ISO-8859-1");

		postRequest.addHeader("Authorization", "key=" + sKey);
		postRequest.setEntity(input);

		TimeUnit.SECONDS.sleep(3);
		HttpResponse response = httpClient.execute(postRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			int count = 0;
			while (response.getStatusLine().getStatusCode() != 200 || count < parametroIntentoNotificacion) {
				response = httpClient.execute(postRequest);
				count++;
				TimeUnit.SECONDS.sleep(parametroTiempoNotificacion);
			}

		}
		httpClient.close();
		return response.getStatusLine().getStatusCode();

	}

	public static void sendPushAbono(String idpush, String json, int parametroIntentoNotificacion,
			int parametroTiempoNotificacion) throws ClientProtocolException, IOException, InterruptedException {

		Gson gson = new Gson();
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(urlService);

		PushNotRequest notificationRequestModel = new PushNotRequest();
		PushNotData notificationData = new PushNotData();
		PushNotNotification notificationNotification = new PushNotNotification();

		notificationNotification.setBody("...");
		notificationNotification.setTitle("Abono");

		notificationData.setJson("{\"id\":\"" + "9991" + "\",\"json\":\"" + encodeB64(json) + "\"}");

		notificationRequestModel.setData(notificationData);
		notificationRequestModel.setNotification(notificationNotification);
		notificationRequestModel.setTo(idpush);

		Type type = new TypeToken<PushNotRequest>() {
		}.getType();

		String jsons = gson.toJson(notificationRequestModel, type);

		StringEntity input = new StringEntity(jsons);
		input.setContentType("application/json");
		input.setContentEncoding("ISO-8859-1");

		postRequest.addHeader("Authorization", "key=" + sKey);
		postRequest.setEntity(input);

		TimeUnit.SECONDS.sleep(3);
		HttpResponse response = httpClient.execute(postRequest);
		if (response.getStatusLine().getStatusCode() != 200) {
			int count = 0;
			while (response.getStatusLine().getStatusCode() != 200 || count < parametroIntentoNotificacion) {
				response = httpClient.execute(postRequest);
				count++;
				TimeUnit.SECONDS.sleep(parametroTiempoNotificacion);
			}

		}
		httpClient.close();

	}

	private static String encodeB64(String strToHash) {
		return encoder.encodeBuffer(strToHash.getBytes()).replaceAll("\\s+", "");
	}

	@SuppressWarnings({ "deprecation", "resource" })
	public static String sendPushDirectly(String data)
			throws ClientProtocolException, IOException, InterruptedException {

		Gson gson = new Gson();
		DataNotification oEntity = gson.fromJson(data, DataNotification.class);

		DefaultHttpClient httpClient = new DefaultHttpClient();

		HttpPost postRequest = new HttpPost(urlService);

		PushNotRequest notificationRequestModel = new PushNotRequest();
		PushNotData notificationData = new PushNotData();
		PushNotNotification notificationNotification = new PushNotNotification();

		notificationNotification.setBody(oEntity.getBody());
		notificationNotification.setTitle(oEntity.getTitle());
		notificationNotification.setChannel("e2e-noti");

		notificationData.setJson("{\"json\":\"" + encodeB64(oEntity.getJson()) + "\"}");
		// notificationData.setJson("{\"id\":\""+sy_proc+"\",\"json\":\""+encodeB64(jsonOrder)+"\",\"android\":{\"priority\":\"high\",\"ttl\":\"0s\"}}");

		AndroidStructures android = new AndroidStructures();
		android.setPriority("High");
		android.setTtl("0s");

		notificationRequestModel.setAndroid(android);
		notificationRequestModel.setData(notificationData);
		notificationRequestModel.setNotification(notificationNotification);
		notificationRequestModel.setTo(oEntity.getIdpush());

		Type type = new TypeToken<PushNotRequest>() {
		}.getType();

		String json = gson.toJson(notificationRequestModel, type);

		StringEntity input = new StringEntity(json);
		input.setContentType("application/json");
		input.setContentEncoding("ISO-8859-1");

		postRequest.addHeader("Authorization", "key=" + sKey);
		postRequest.setEntity(input);

		TimeUnit.SECONDS.sleep(10);
		HttpResponse response = httpClient.execute(postRequest);
		int count = 0;

		String sError = "";

		if (response.getStatusLine().getStatusCode() != 200) {
			// int count = 0;
			sError = response.getStatusLine().getReasonPhrase();

			while (response.getStatusLine().getStatusCode() != 200 || count < oEntity.getRetries()) {
				System.err.println("ESTADO DE LA RESPUESTA HTTP");
				System.err.println(response.getStatusLine().getStatusCode());
				System.err.println(response.getStatusLine().getReasonPhrase());
				System.err.println(response.getStatusLine().toString());
				sError = sError + " - " + response.getStatusLine().getReasonPhrase();
				try {
					httpClient = new DefaultHttpClient();
					response = httpClient.execute(postRequest);
					httpClient.close();
					count++;
					TimeUnit.SECONDS.sleep(2);
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println("CATCH ERROR");
					System.err.println(e.getMessage());
					System.err.println(e.getStackTrace());
					System.err.println(e.getLocalizedMessage());
				}
				
			}
		}
		if (response.getStatusLine().getStatusCode() == 200)
			httpClient.close();
		if (sError.equals("")) {
			return "{\"count\":\"" + count + "\",\"status\":\"" + response + "\",\"json\":\"" + json + "\"}";
		} else {
			return "{\"count\":\"" + count + "\",\"status\":\"" + response + "\",\"error\":\"" + sError + "\"}";
		}

	}

}
