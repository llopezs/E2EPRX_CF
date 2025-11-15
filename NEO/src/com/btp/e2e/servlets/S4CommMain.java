package com.btp.e2e.servlets;

import static java.net.HttpURLConnection.HTTP_OK;

import com.btp.e2e.dao.LogRecepcionesDAO;
import com.btp.e2e.dao.UsuarioDAO;
import com.btp.e2e.entities.ClaseMensaje;
import com.btp.e2e.entities.Parametros;
import com.btp.e2e.entities.StockActual;
import com.btp.e2e.servlets.Structures4Jsons.BaseOdataServiceGET;
import com.btp.e2e.servlets.Structures4Jsons.OdataItemGuiaProducto;
import com.btp.e2e.servlets.Structures4Jsons.OdataItemGuiaProductos;
import com.btp.e2e.servlets.Structures4Jsons.OdataStructure;
import com.btp.e2e.servlets.Structures4Jsons.ParametrosInGuia;
import com.btp.e2e.servlets.Structures4Jsons.ProductSLL;
import com.btp.e2e.servlets.Structures4Jsons.Response;
import com.btp.e2e.servlets.Structures4Jsons.TerminalBlock;
import com.btp.e2e.servlets.Structures4Jsons.ValidateCoupon;
import com.btp.e2e.servlets.authentication.UserExternal;
import com.btp.e2e.servlets.configs.Destinations;
import com.btp.e2e.servlets.configs.GlobalConfiguration;
import com.btp.e2e.servlets.services.report.ListObjectsReport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.sap.core.connectivity.api.http.HttpDestination;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import com.sap.cloud.sdk.cloudplatform.connectivity.exception.DestinationNotFoundException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import static com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor.getDestination;
import static com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor.getHttpClient;

/**
 * Servlet implementation class S4Comm
 */
public class S4CommMain extends HttpServlet {

	/**
	 *
	 */
	InputStream fileLock = null;
	ServletContext servletContext = null;

	public List<TerminalBlock> terminalBlockingList = new ArrayList<TerminalBlock>();
	protected DataSource ds;

	private static final long serialVersionUID = 1L;
	private static final int COPY_CONTENT_BUFFER_SIZE = 1024;
	private static final Logger LOGGER = LoggerFactory.getLogger(S4CommMain.class);

	private static final String service = "/sap/opu/odata/SAP/ZE2E_SRV";

	@SuppressWarnings("unused")
	private static final String serviceTest = "/sap/opu/odata/SAP/ZE2E_SRV";

	private static final String commSet = "/commSet";
	private static final String FilesSet = "/FilesSet";
	// /opu/odata/sap/ZE2E_SRV/FilesSet(guid'0050569C-33D0-1EEC-A083-2F3D8CFE5B76')/$value

	@SuppressWarnings("unused")
	private static final String commSetTest = "/commSet";

	private String query = "?$format=json&$filter=Userid eq '@ANDROID@' and Method eq '@method@' and Item eq '@item@' and Imgb64 eq '@imgb64@' and Ecomm eq '@ecomm@'";
	private String queryPrx = "?$format=json&$filter=ClassPrx eq '@classprx@' and Userid eq '@userid@' and Method eq '@method@' and Content eq '@content@' and File eq '@file@'";
	private String queryFile = "(@guid@)/$value";

	// ClassPrx
	// Content
	// File
	// Method
	// Userid
	// Item

	// Method to send Notifications from server to client end.
	public static final String AUTH_KEY_FCM = "";
	public static final String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	protected BASE64Decoder decoder;
	protected BASE64Encoder encoder;
	protected Gson gson = new Gson();

	// PITS
	// protected ClaseMensajeDAO claseMenajeDAO;
	protected List<ClaseMensaje> mensajes;
	Response responseWS = new Response();

	// PVM CLASES
	// protected DefectuososDAO defectuososDAO;
	// protected LogVentaDAO logVentaDAO;
	protected UsuarioDAO usuarioDAO;
	// protected StockActualDAO stockActualDAO;
	// protected LiquidacionStockDAO liquidacionStockDAO;
	// protected ParametrosDAO parametrosDAO;
	// protected CierreDAO cierreDAO;
	// protected CierreAutDAO cierreAutDAO;
	// protected ContingenciaDAO contingenciaDAO;
	// protected TurnoDAO turnoDAO;
	// protected LogModificacionesDAO logModificacionesDAO;
	// protected NotificacionesDAO notificacionesDAO;
	// protected LogErrorSLLDAO logErrorSLLDAO;
	// protected LogPreVentaDAO logPreventaDAO;
	// protected LogPreVentaPOnlineDAO logPreventaPOnlineDAO;

	// EXTERNAL CLASES
	// protected PedidoDAO pedidoDAO;
	protected LogRecepcionesDAO logRecepcionesDAO;

	protected List<UserExternal> usersExternal = new ArrayList<UserExternal>();

	// FIORI CLASES
	// protected AdministradosrDAO administradorDAO;
	// private FirebaseApp aFirebaseApp;
	public Parametros parametroTiempoNotificacion;
	public Parametros parametroIntentoNotificacion;
	public Parametros parametroDiasVencimientoTurno;
	public Parametros parametroCambioHorario;

	// protected LogPedidoDAO logPedidoDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S4CommMain() {
		super();
	}

	@Override
	public void init() throws ServletException {
		try {
			InitialContext ctx = new InitialContext();

			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
			servletContext = getServletContext();

			decoder = new BASE64Decoder();
			encoder = new BASE64Encoder();

			usuarioDAO = new UsuarioDAO(ds);
			logRecepcionesDAO = new LogRecepcionesDAO(ds);
			mensajes = new ArrayList<ClaseMensaje>();

			if (parametroIntentoNotificacion == null)
				parametroIntentoNotificacion = new Parametros();

			if (parametroIntentoNotificacion.getValor() == null
					|| parametroIntentoNotificacion.getValor().length() == 0)
				parametroIntentoNotificacion.setValor("100");

			if (parametroTiempoNotificacion == null)
				parametroTiempoNotificacion = new Parametros();

			if (parametroTiempoNotificacion.getValor() == null || parametroTiempoNotificacion.getValor().length() == 0)
				parametroTiempoNotificacion.setValor("5");

			if (parametroDiasVencimientoTurno == null)
				parametroDiasVencimientoTurno = new Parametros();

			if (parametroDiasVencimientoTurno.getValor() == null
					|| parametroDiasVencimientoTurno.getValor().length() == 0)
				parametroDiasVencimientoTurno.setValor("3");

			if (parametroCambioHorario == null)
				parametroCambioHorario = new Parametros();

			if (parametroCambioHorario.getValor() == null || parametroCambioHorario.getValor().length() == 0)
				parametroCambioHorario.setValor("3");

			usersExternal = GlobalConfiguration.load(usersExternal);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	protected HttpServletResponse setOptions(HttpServletResponse response) {
		// response.addHeader("Access-Control-Allow-Headers", "*");
		// response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Origin", "https://account.us3.hana.ondemand.com/");
		response.addHeader("Vary", "Accept-Encoding, Origin");
		response.addHeader("Access-Control-Allow-Methods", "POST");
		response.addHeader("Access-Control-Max-Age", "300000");
		response.addHeader("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains");
		response.addHeader("X-Frame-Options", "deny");
		response.addHeader("X-Content-Type-Options", "nosniff");
		response.addHeader("Content-Security-Policy", "media-src");
		response.addHeader("X-XSS-Protection", "0");
		response.addHeader("X-Permitted-Cross-Domain-Policies", "by-content-type");
		response.addHeader("Referrer-Policy", "no-referrer");
		response.addHeader("Expect-CT",
				"max-age=300000, enforce, report-uri=\"https://account.us3.hana.ondemand.com/\"");
		response.setContentType("text/html");
		return response;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("ISO-8859-1");
			usuarioDAO.setNewConn(ds);
			logRecepcionesDAO.setNewConn(ds);
			response.setCharacterEncoding("ISO-8859-1");
			response = setOptions(response);
			handleRequest(request, response);
		} catch (SQLException | NoSuchAlgorithmException | ParseException | NumberFormatException
				| InterruptedException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("ISO-8859-1");
			usuarioDAO.setNewConn(ds);
			logRecepcionesDAO.setNewConn(ds);
			response.setCharacterEncoding("ISO-8859-1");
			response = setOptions(response);
			handleRequest(request, response);
		} catch (SQLException | NoSuchAlgorithmException | ParseException | NumberFormatException
				| InterruptedException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public String odataServiceCallReadFile(String json, HttpServletResponse response) throws IOException {
		HttpClient httpClient = null;
		String destinationName = Destinations.door;
		String objToReturn = "";
		@SuppressWarnings("unused")
		List<String> values = new ArrayList<String>();
		@SuppressWarnings("unused")
		String rut = "";
		Response responseWS = new Response();

		OdataStructure osIncoming = gson.fromJson(json, OdataStructure.class);

		System.err.println(
				"**********************************************************************JSON DE FILE**********************************************************************");
		System.err.println(json);
		System.err.println(
				"**********************************************************************JSON DE FILE**********************************************************************");
		System.err.println(json);

		try {
			// Get HTTP destination
			Context ctx = new InitialContext();
			
			if (osIncoming.getContent().equals("test"))
			osIncoming.setContent("guid'0050569C-33D0-1EEC-A083-2F3D8CFE5B76'");
			String lv_query = queryFile.replaceAll("@guid@", osIncoming.getContent());
			objToReturn = "";
			// DEPRECATE NEO
			/* 
			HttpDestination destination = null;
			destination = (HttpDestination) ctx.lookup("java:comp/env/" + destinationName);
			httpClient = destination.createHttpClient();

			// Create HTTP client
			String uri = destination.getURI().toString() + service + FilesSet + lv_query;
			uri = URIUtil.encodeQuery(uri);
			*/
			
			//CF SDK CLOUD
			HttpDestination httpDestination = getDestination(destinationName).asHttp();
			HttpClient httpClient = getHttpClient(httpDestination);
			
			String requestUrl = httpDestination.getUri() + service + FilesSet + lv_query;
			HttpGet httpGet = new HttpGet(requestUrl);
			//HttpGet httpGet = new HttpGet((generateUrl(uri).toURI()));
			
			HttpResponse httpResponse = httpClient.execute(httpGet);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			System.err.println(statusCode);
			if (statusCode != HTTP_OK) {
				HttpEntity entity = httpResponse.getEntity();
				java.io.InputStreamReader reader = new java.io.InputStreamReader(entity.getContent());
				BufferedReader br = new BufferedReader(reader);
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				String responseHTTP = sb.toString();
				System.err.println(responseHTTP);

				return (objToReturn = String.valueOf(statusCode));
			}

			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				java.io.InputStreamReader reader = new java.io.InputStreamReader(entity.getContent());
				BufferedReader br = new BufferedReader(reader);
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				Header[] headers = httpResponse.getAllHeaders();
				for (int i = 0; i < headers.length; i++) {
					response.setHeader(headers[i].getName(), headers[i].getValue());
				}
				objToReturn = sb.toString();

				InputStream instream = entity.getContent();
				objToReturn = reader.toString();
				try {
					try {
						return objToReturn;
					} catch (Exception e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
						responseWS.setResultado(false);
						responseWS.setMensaje(e.getMessage());
						return (objToReturn = responseWS.returnJson());
					}

				} catch (RuntimeException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					httpGet.abort();
					response.setStatus(400);
					responseWS.setResultado(false);
					responseWS.setMensaje(e.getMessage());
					return (objToReturn = responseWS.returnJson());
					// return gson.toJson(new
					// GeneralMsg("Error",e.getMessage()));
				} finally {
					// Closing the input stream will trigger connection release

					try {
						instream.close();
					} catch (Exception e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
						response.setStatus(400);
						responseWS.setResultado(false);
						responseWS.setMensaje(e.getMessage());
						return (objToReturn = responseWS.returnJson());
					}
				}
			}
		} catch (NamingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Lookup of destination failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have the destination " + destinationName + " configured.";
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
			response.setStatus(400);
			responseWS.setResultado(false);
			responseWS.setMensaje(errorMessage);
			return (objToReturn = responseWS.returnJson());
			// return gson.toJson(new GeneralMsg("Error",e.getMessage()));
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Connectivity operation failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have an HTTP proxy configured in your "
					+ "local Eclipse environment in case your environment uses "
					+ "an HTTP proxy for the outbound Internet " + "communication.";
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
			response.setStatus(400);
			responseWS.setResultado(false);
			responseWS.setMensaje(errorMessage);
			return (objToReturn = responseWS.returnJson());
			// return gson.toJson(new GeneralMsg("Error",e.getMessage()));
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
				return objToReturn;
			}
		}
		return objToReturn;
	}

	@SuppressWarnings("deprecation")
	public String odataServiceCallRead(String json, HttpServletResponse response) throws IOException {
		HttpClient httpClient = null;
		String destinationName = Destinations.door;
		String objToReturn = "";
		@SuppressWarnings("unused")
		List<String> values = new ArrayList<String>();
		@SuppressWarnings("unused")
		String rut = "";
		Response responseWS = new Response();

		OdataStructure osIncoming = gson.fromJson(json, OdataStructure.class);

		System.err.println(
				"**********************************************************************JSON DE DATOS**********************************************************************");
		System.err.println(json);
		System.err.println(
				"**********************************************************************JSON DE DATOS**********************************************************************");
		System.err.println(json);

		try {
			// Get HTTP destination
			Context ctx = new InitialContext();
			
			//DEPRECATE NEO
			/* 
			HttpDestination destination = null;
			destination = (HttpDestination) ctx.lookup("java:comp/env/" + destinationName);
			httpClient = destination.createHttpClient();
			*/

			//CF SDK CLOUD
			HttpDestination httpDestination = getDestination(destinationName).asHttp();
			HttpClient httpClient = getHttpClient(httpDestination);

			String lv_query = queryPrx.replaceAll("@method@", osIncoming.getMethod());
			lv_query = lv_query.replaceAll("@content@", osIncoming.getContent());
			lv_query = lv_query.replaceAll("@userid@", osIncoming.getUserid());
			lv_query = lv_query.replaceAll("@classprx@", osIncoming.getClassPrx());
			lv_query = lv_query.replaceAll("@file@", osIncoming.getFile());
			// Create HTTP client

			//String uri = destination.getURI().toString() + service + commSet + lv_query;
			String requestUrl = httpDestination.getUri() + service + commSet + lv_query;

			System.err.println(requestUrl);
			uri = URIUtil.encodeQuery(requestUrl);
			System.err.println("la otra uri");
			System.err.println(requestUrl);
			objToReturn = "";

			HttpGet httpGet = new HttpGet(requestUrl);
			HttpResponse httpResponse = httpClient.execute(httpGet);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			System.err.println(statusCode);
			if (statusCode != HTTP_OK) {
				HttpEntity entity = httpResponse.getEntity();
				java.io.InputStreamReader reader = new java.io.InputStreamReader(entity.getContent());
				BufferedReader br = new BufferedReader(reader);
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				String responseHTTP = sb.toString();
				System.err.println(responseHTTP);

				return (objToReturn = gson.toJson(httpResponse, httpResponse.getClass()));
			}

			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				java.io.InputStreamReader reader = new java.io.InputStreamReader(entity.getContent());
				BufferedReader br = new BufferedReader(reader);
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				objToReturn = sb.toString();

				InputStream instream = entity.getContent();

				try {
					String string = objToReturn;
					String[] parts = string.split("]}}");
					objToReturn = parts[0] + "]}}";

					BaseOdataServiceGET oGet = new BaseOdataServiceGET();
					try {
						oGet = new Gson().fromJson(objToReturn, BaseOdataServiceGET.class);
					} catch (Exception e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
						return objToReturn;
					}

					String concatenateAllJsons = "";

					for (int i = 0; i < oGet.getD().getResults().size(); i++) {
						concatenateAllJsons += oGet.getD().getResults().get(i).getContent();
					}

					if (!isJson(concatenateAllJsons)) {
						responseWS.setMensaje("Error de carga");
						responseWS.setResultado(false);
						response.setStatus(400);
						return responseWS.returnJson();
					}

					switch (osIncoming.getMethod()) {
					default:
						objToReturn = concatenateAllJsons;
						break;
					}
				} catch (RuntimeException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					httpGet.abort();
					response.setStatus(400);
					responseWS.setResultado(false);
					responseWS.setMensaje(e.getMessage());
					return (objToReturn = responseWS.returnJson());
				} finally {
					try {
						instream.close();
					} catch (Exception e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
						response.setStatus(400);
						responseWS.setResultado(false);
						responseWS.setMensaje(e.getMessage());
						return (objToReturn = responseWS.returnJson());
					}
				}
			}
		} catch (NamingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Lookup of destination failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have the destination " + destinationName + " configured.";
			LOGGER.error("Lookup of destination failed", e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
			response.setStatus(400);
			responseWS.setResultado(false);
			responseWS.setMensaje(errorMessage);
			return (objToReturn = responseWS.returnJson());
			// return gson.toJson(new GeneralMsg("Error",e.getMessage()));
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Connectivity operation failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have an HTTP proxy configured in your "
					+ "local Eclipse environment in case your environment uses "
					+ "an HTTP proxy for the outbound Internet " + "communication.";
			LOGGER.error("Connectivity operation failed", e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
			response.setStatus(400);
			responseWS.setResultado(false);
			responseWS.setMensaje(errorMessage);
			return (objToReturn = responseWS.returnJson());
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
				return objToReturn;
			}
		}
		return objToReturn;
	}

	@SuppressWarnings({ "deprecation" })
	public String odataServiceCallRead(String metodo, String json, HttpServletResponse response) throws IOException {
		HttpClient httpClient = null;
		String destinationName = Destinations.door;
		String objToReturn = "";
		List<String> values = new ArrayList<String>();
		StockActual userStockRead = new StockActual();
		String rut = "";
		Response responseWS = new Response();

		try {
			// Get HTTP destination
			Context ctx = new InitialContext();
	
			//DEPRECATE NEO
			/* 
			HttpDestination destination = null;
			destination = (HttpDestination) ctx.lookup("java:comp/env/" + destinationName);
			httpClient = destination.createHttpClient();
			*/
			//CF SDK CLOUD
			
			HttpDestination httpDestination = getDestination(destinationName).asHttp();
			HttpClient httpClient = getHttpClient(httpDestination);

			String lv_query = query.replaceAll("@method@", metodo);
			lv_query = lv_query.replaceAll("@item@", json);
			lv_query = lv_query.replaceAll("@ANDROID@", "AUTOMATICO");
			// Create HTTP client
			//String uri = destination.getURI().toString() + service + commSet + lv_query;
			String requestUrl = httpDestination.getUri() + service + commSet + lv_query;
			System.err.println(requestUrl);
			uri = URIUtil.encodeQuery(requestUrl);
			System.err.println("la otra uri");
			System.err.println(requestUrl);
			objToReturn = "";


			//HttpGet httpGet = new HttpGet((generateUrl(uri).toURI()));
			HttpGet httpGet = new HttpGet(requestUrl);

			HttpResponse httpResponse = httpClient.execute(httpGet);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			System.err.println(statusCode);
			if (statusCode != HTTP_OK) {
				if (!metodo.equals("CREATE_FACT_CIERRE")) {
					response.setStatus(statusCode);
				}
				HttpEntity entity = httpResponse.getEntity();
				java.io.InputStreamReader reader = new java.io.InputStreamReader(entity.getContent());
				BufferedReader br = new BufferedReader(reader);
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				String responseHTTP = sb.toString();
				System.err.println(responseHTTP);

				return (objToReturn = String.valueOf(statusCode));
			}

			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				java.io.InputStreamReader reader = new java.io.InputStreamReader(entity.getContent());
				BufferedReader br = new BufferedReader(reader);
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				objToReturn = sb.toString();

				InputStream instream = entity.getContent();

				try {

					String string = objToReturn;
					String[] parts = string.split("]}}");
					objToReturn = parts[0] + "]}}";

					if (metodo.equals("CREATE_SELL_SD_ZFIN_ZFIC_ZCOF")) {
						System.err.println("objToReturn:originalOdata:: " + objToReturn);
					}
					if (metodo.equals("GET_PRICELIST_ZFIN_ZFIC_ZCOF")) {
						System.err.println("objToReturn:originalOdata:: " + objToReturn);
					}

					if (metodo.equals("GET_GUIDE_ITEMS_FOR_DEVICE")) {
						byte[] utf8 = objToReturn.getBytes("ISO-8859-1");

						objToReturn = new String(utf8);
						objToReturn = objToReturn.replaceAll("\n", "");
						int middlePos = objToReturn.indexOf("\"uri\":") + 6;
						int finalPos = objToReturn.lastIndexOf("\"http:");
						String frontStr = objToReturn.substring(0, middlePos);
						String backStr = objToReturn.substring(finalPos, objToReturn.length());
						objToReturn = frontStr + backStr;
					}

					BaseOdataServiceGET oGet = new BaseOdataServiceGET();
					try {
						oGet = new Gson().fromJson(objToReturn, BaseOdataServiceGET.class);
					} catch (Exception e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
						return objToReturn;
					}

					String concatenateAllJsons = "";

					for (int i = 0; i < oGet.getD().getResults().size(); i++) {
						concatenateAllJsons += oGet.getD().getResults().get(i).getItem();
					}

					if (!isJson(concatenateAllJsons)) {
						responseWS.setMensaje("Error de carga");
						responseWS.setResultado(false);
						response.setStatus(400);
						return responseWS.returnJson();
					}

					switch (metodo) {
					case "VALIDATE_COUPON":
						concatenateAllJsons = concatenateAllJsons.replaceAll("\"RESULT\":\"true\"", "\"RESULT\":true");
						concatenateAllJsons = concatenateAllJsons.replaceAll("\"RESULT\":\"false\"",
								"\"RESULT\":false");
						ValidateCoupon vc = gson.fromJson(concatenateAllJsons, ValidateCoupon.class);
						if (!vc.isRESULT()) {
							response.setStatus(400);
						}
						objToReturn = gson.toJson(vc);
						break;
					case "GET_GUIDE_ITEMS_FOR_DEVICE":
						try {
							// System.err.println("JSON: " + json);
							ParametrosInGuia parametrosInGuia = new ParametrosInGuia();
							try {
								parametrosInGuia = new Gson().fromJson(json, ParametrosInGuia.class);
							} catch (Exception e) {
								System.err.println(e.getMessage());
								e.printStackTrace();
							}
							OdataItemGuiaProductos guiaProductos = new OdataItemGuiaProductos();
							try {
								guiaProductos = new Gson().fromJson(concatenateAllJsons, OdataItemGuiaProductos.class);
								OdataItemGuiaProductos guiaProductosMixed = new OdataItemGuiaProductos();
								guiaProductosMixed.setRESULT(guiaProductos.getRESULT());
								guiaProductosMixed.setMSGS(guiaProductos.getMSGS());

								for (OdataItemGuiaProducto newPosGP : guiaProductos.getITEMS()) {
									boolean codeExist = false;
									newPosGP.setCODIGO_PRODUCTO(newPosGP.getCODIGO_PRODUCTO().replaceAll("\\s+", ""));
									newPosGP.setCANTIDAD(newPosGP.getCANTIDAD().replaceAll("\\s+", ""));
									String strCnt1 = newPosGP.getCANTIDAD();
									String[] partsCnt1 = strCnt1.split(",");
									int count1 = Integer.parseInt(partsCnt1[0].replaceAll("\\s+", ""));
									for (OdataItemGuiaProducto newPosGPAux : guiaProductosMixed.getITEMS()) {
										if (newPosGP.getCODIGO_PRODUCTO().equals(newPosGPAux.getCODIGO_PRODUCTO())) {
											codeExist = true;
										}
									}
									if (codeExist) {
										for (int i = 0; i < guiaProductosMixed.getITEMS().size(); i++) {
											OdataItemGuiaProducto newPosGPAuxx = guiaProductosMixed.getITEMS().get(i);
											if (codeExist
													&& newPosGPAuxx.getCODIGO_PRODUCTO()
															.equals(newPosGP.getCODIGO_PRODUCTO())
													&& (!newPosGP.getUNIDAD().contains("CIL")
															&& !newPosGP.getUNIDAD().contains("cil"))) {
												int count2 = Integer.parseInt(newPosGPAuxx.getCANTIDAD());
												count2 += count1;
												newPosGPAuxx.setCANTIDAD(String.valueOf(count2));
												guiaProductosMixed.getITEMS().set(i, newPosGPAuxx);
											}
										}
									} else {
										if (newPosGP.getUNIDAD().contains("CIL")
												|| newPosGP.getUNIDAD().contains("cil")) {
											count1 = 0;
										}
										newPosGP.setCANTIDAD(String.valueOf(count1));
										guiaProductosMixed.getITEMS().add(newPosGP);
									}
								}

								guiaProductos.setITEMS(guiaProductosMixed.getITEMS());
							} catch (Exception e) {
								System.err.println(e.getMessage());
								e.printStackTrace();

							}
							rut = usuarioDAO.findUserByTerminal4Rut(parametrosInGuia.getTerminal());
							values = new ArrayList<String>();
							values.add(rut);

							if (userStockRead != null && userStockRead.getStock_detalle() != null) {
								if (userStockRead.getStock_detalle().length() > 0) {
									Type listType = new TypeToken<ArrayList<ProductSLL>>() {
									}.getType();
									List<ProductSLL> productos = new Gson().fromJson(userStockRead.getStock_detalle(),
											listType);
									if (productos != null) {

										for (ProductSLL pSll : productos) {
											boolean codeExist = false;
											OdataItemGuiaProducto newPosGP = new OdataItemGuiaProducto();
											newPosGP.setCODIGO_PRODUCTO(pSll.getProducto().replaceAll("\\s+", ""));
											newPosGP.setCANTIDAD(pSll.getCantidad().replaceAll("\\s+", ""));
											newPosGP.setPRECIO(pSll.getPrecio().replaceAll("\\s+", ""));
											newPosGP.setUNIDAD(pSll.getUnidad().replaceAll("\\s+", ""));
											newPosGP.setDESCRIPCION(newPosGP.getCODIGO_PRODUCTO().substring(2) + "KG "
													+ newPosGP.getUNIDAD());
											String strCnt1 = newPosGP.getCANTIDAD();
											String[] partsCnt1 = strCnt1.split(",");
											int count1 = Integer.parseInt(partsCnt1[0].replaceAll("\\s+", ""));
											for (OdataItemGuiaProducto newPosGPAux : guiaProductos.getITEMS()) {
												if (newPosGP.getCODIGO_PRODUCTO()
														.equals(newPosGPAux.getCODIGO_PRODUCTO())) {
													codeExist = true;
												}
											}
											if (codeExist) {
												for (int i = 0; i < guiaProductos.getITEMS().size(); i++) {
													OdataItemGuiaProducto newPosGPAuxx = guiaProductos.getITEMS()
															.get(i);
													if (codeExist && newPosGPAuxx.getCODIGO_PRODUCTO()
															.equals(newPosGP.getCODIGO_PRODUCTO()) &&
													// distincion con unidad
													// para diferenciar los
													// defectuosos
															!newPosGP.getUNIDAD().toUpperCase().equals("DEF")) {
														// distincion con unidad
														// para diferenciar los
														// defectuosos
														int count2 = Integer.parseInt(newPosGPAuxx.getCANTIDAD());
														count2 += count1;
														newPosGPAuxx.setCANTIDAD(String.valueOf(count2));
														guiaProductos.getITEMS().set(i, newPosGPAuxx);
													}
												}
											} else {
												newPosGP.setCANTIDAD(String.valueOf(count1));
												guiaProductos.getITEMS().add(newPosGP);
											}
											// distincion con unidad para
											// diferenciar los defectuosos
											if (newPosGP.getUNIDAD().equals("DEF")
													|| newPosGP.getUNIDAD().equals("def")) {
												guiaProductos.getITEMS().add(newPosGP);
											}
											// distincion con unidad para
											// diferenciar los defectuosos
										}
									}
								}
							}

							if (userStockRead == null || userStockRead.getEntregar() == null) {
								@SuppressWarnings("unused")
								Type OdataItemGuiaProductos_listType = new TypeToken<OdataItemGuiaProductos>() {
								}.getType();
								objToReturn = new Gson().toJson(guiaProductos, OdataItemGuiaProductos.class);
							} else {
								if (userStockRead.getEntregar().equals("1")
										&& userStockRead.getRecepcionado().equals("0")) {
									response.setStatus(421);
									responseWS.setResultado(false);
									// responseWS.mensaje = e.getMessage();
									responseWS.setMensaje(
											"Terminal pendiente de liquidar, debe asistir a su centro LIPIGAS mas cercano.");
									objToReturn = responseWS.returnJson();
								} else {
									Type OdataItemGuiaProductos_listType = new TypeToken<OdataItemGuiaProductos>() {
									}.getType();
									// System.err.println("GUIA PRODUCTOS OLD" +
									// guiaProductos);
									objToReturn = new Gson().toJson(guiaProductos, OdataItemGuiaProductos_listType);
									// System.err.println("GUIA PRODUCTOS OLD
									// POST TRANSFORM " + guiaProductos);
								}
							}
						} catch (Exception e) {
							System.err.println(e.getMessage());
							e.printStackTrace();
							response.setStatus(400);
							responseWS.setResultado(false);
							// responseWS.mensaje = e.getMessage();
							responseWS.setMensaje("Guia no encontrada, consumida o no corresponde al terminal.");
							// responseWS.mensaje = strAlternativeReturn;
							return (objToReturn = responseWS.returnJson());
						}

						break;
					default:
						objToReturn = concatenateAllJsons;
						break;
					}
				} catch (IOException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					response.setStatus(400);
					responseWS.setResultado(false);
					responseWS.setMensaje(e.getMessage());
					return (objToReturn = responseWS.returnJson());

				} catch (RuntimeException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					httpGet.abort();
					response.setStatus(400);
					responseWS.setResultado(false);
					responseWS.setMensaje(e.getMessage());
					return (objToReturn = responseWS.returnJson());
				} finally {
					try {
						instream.close();
					} catch (Exception e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
						response.setStatus(400);
						responseWS.setResultado(false);
						responseWS.setMensaje(e.getMessage());
						return (objToReturn = responseWS.returnJson());
					}
				}
			}
		} catch (NamingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Lookup of destination failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have the destination " + destinationName + " configured.";
			LOGGER.error("Lookup of destination failed", e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
			response.setStatus(400);
			responseWS.setResultado(false);
			responseWS.setMensaje(errorMessage);
			return (objToReturn = responseWS.returnJson());
			// return gson.toJson(new GeneralMsg("Error",e.getMessage()));
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Connectivity operation failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have an HTTP proxy configured in your "
					+ "local Eclipse environment in case your environment uses "
					+ "an HTTP proxy for the outbound Internet " + "communication.";
			LOGGER.error("Connectivity operation failed", e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
			response.setStatus(400);
			responseWS.setResultado(false);
			responseWS.setMensaje(errorMessage);
			return (objToReturn = responseWS.returnJson());
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
				return objToReturn;
			}
		}
		return objToReturn;
	}

	@SuppressWarnings("deprecation")
	public String odataServiceCallReadRetry(String metodo, String json, HttpServletResponse response, String id)
			throws IOException, MessagingException {
		HttpClient httpClient = null;
		// String destinationName = request.getParameter("DestinationName");
		String destinationName = Destinations.door;
		String objToReturn = "";
		List<String> values = new ArrayList<String>();
		StockActual userStockRead = new StockActual();
		String rut = "";
		Response responseWS = new Response();
		try {
			// Get HTTP destination
			Context ctx = new InitialContext();
			
			//DEPRECATE NEO
			/* 
			HttpDestination destination = null;
			destination = (HttpDestination) ctx.lookup("java:comp/env/" + destinationName);
			httpClient = destination.createHttpClient();
			*/
			//CF SDK CLOUD
			HttpDestination httpDestination = getDestination(destinationName).asHttp();
			HttpClient httpClient = getHttpClient(httpDestination);

			String lv_query = query.replaceAll("@method@", metodo);
			lv_query = lv_query.replaceAll("@item@", json);
			lv_query = lv_query.replaceAll("@ANDROID@", id);
			// Create HTTP client
			//String uri = destination.getURI().toString() + service + commSet + lv_query;
			//uri = URIUtil.encodeQuery(uri);
			
			String requestUrl = httpDestination.getUri() + service + commSet + lv_query;
			
			objToReturn = "";

			//HttpGet httpGet = new HttpGet((generateUrl(uri).toURI()));
			HttpGet httpGet = new HttpGet(requestUrl);
			HttpResponse httpResponse = httpClient.execute(httpGet);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != HTTP_OK) {
				response.setStatus(statusCode);
				return (objToReturn = String.valueOf(statusCode));
			}

			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();

				try {
					byte[] buffer = new byte[COPY_CONTENT_BUFFER_SIZE];
					while ((instream.read(buffer)) != -1) {
						// objToReturn += new String(buffer, "ISO-8859-1");
						objToReturn += new String(buffer, "ISO-8859-1");
					}

					String string = objToReturn;
					String[] parts = string.split("]}}");
					objToReturn = parts[0] + "]}}";
					if (metodo.equals("GET_GUIDE_ITEMS_FOR_DEVICE")) {
						byte[] utf8 = objToReturn.getBytes("ISO-8859-1");
						objToReturn = new String(utf8);
						objToReturn = objToReturn.replaceAll("\n", "");
						int middlePos = objToReturn.indexOf("\"uri\":") + 6;
						int finalPos = objToReturn.lastIndexOf("\"http:");
						String frontStr = objToReturn.substring(0, middlePos);
						String backStr = objToReturn.substring(finalPos, objToReturn.length());
						objToReturn = frontStr + backStr;
					}

					BaseOdataServiceGET oGet = new BaseOdataServiceGET();
					try {
						oGet = new Gson().fromJson(objToReturn, BaseOdataServiceGET.class);
					} catch (Exception e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
						return objToReturn;
					}

					String concatenateAllJsons = "";

					for (int i = 0; i < oGet.getD().getResults().size(); i++) {
						concatenateAllJsons += oGet.getD().getResults().get(i).getItem();
					}

					if (!isJson(concatenateAllJsons)) {
						responseWS.setMensaje("Error de carga");
						responseWS.setResultado(false);
						response.setStatus(400);
						return responseWS.returnJson();
					}

					switch (metodo) {
					case "VALIDATE_COUPON":
						concatenateAllJsons = concatenateAllJsons.replaceAll("\"RESULT\":\"true\"", "\"RESULT\":true");
						concatenateAllJsons = concatenateAllJsons.replaceAll("\"RESULT\":\"false\"",
								"\"RESULT\":false");
						ValidateCoupon vc = gson.fromJson(concatenateAllJsons, ValidateCoupon.class);
						if (!vc.isRESULT()) {
							response.setStatus(400);
						}
						objToReturn = gson.toJson(vc);
						break;
					case "GET_GUIDE_ITEMS_FOR_DEVICE":
						try {

							ParametrosInGuia parametrosInGuia = new ParametrosInGuia();
							try {
								parametrosInGuia = new Gson().fromJson(json, ParametrosInGuia.class);
							} catch (Exception e) {
								System.err.println(e.getMessage());
								e.printStackTrace();
							}
							OdataItemGuiaProductos guiaProductos = new OdataItemGuiaProductos();
							try {
								guiaProductos = new Gson().fromJson(concatenateAllJsons, OdataItemGuiaProductos.class);
								OdataItemGuiaProductos guiaProductosMixed = new OdataItemGuiaProductos();
								guiaProductosMixed.setRESULT(guiaProductos.getRESULT());
								guiaProductosMixed.setMSGS(guiaProductos.getMSGS());
								for (int i = 0; i < guiaProductos.getITEMS().size(); i++) {
									OdataItemGuiaProducto newPosGP = guiaProductos.getITEMS().get(i);
									newPosGP.setCODIGO_PRODUCTO(newPosGP.getCODIGO_PRODUCTO().replaceAll("\\s+", ""));
									newPosGP.setCANTIDAD(newPosGP.getCANTIDAD().replaceAll("\\s+", ""));
									String strCnt1 = newPosGP.getCANTIDAD();
									String[] partsCnt1 = strCnt1.split(",");
									int count1 = Integer.parseInt(partsCnt1[0].replaceAll("\\s+", ""));
									for (int j = 0; j < guiaProductos.getITEMS().size(); j++) {
										OdataItemGuiaProducto newPosGPAux = guiaProductos.getITEMS().get(j);
										newPosGPAux.setCODIGO_PRODUCTO(
												newPosGPAux.getCODIGO_PRODUCTO().replaceAll("\\s+", ""));
										newPosGPAux.setCANTIDAD(newPosGPAux.getCANTIDAD().replaceAll("\\s+", ""));
										String strCnt2 = newPosGP.getCANTIDAD();
										String[] partsCnt2 = strCnt2.split(",");
										int count2 = Integer.parseInt(partsCnt2[0].replaceAll("\\s+", ""));
										newPosGPAux.setCANTIDAD(String.valueOf(count2));
										if (newPosGPAux.getCODIGO_PRODUCTO().equals(newPosGP.getCODIGO_PRODUCTO())
												&& newPosGPAux.getUNIDAD().equals(newPosGP.getUNIDAD()) && i != j) {
											count1 += count2;
											guiaProductos.getITEMS().remove(j);
										}
									}
									if (newPosGP.getUNIDAD().equals("CIL"))
										count1 = 0;
									newPosGP.setCANTIDAD(String.valueOf(count1));
									guiaProductosMixed.getITEMS().add(newPosGP);
									guiaProductos.getITEMS().remove(i);
								}
								guiaProductos.setITEMS(guiaProductosMixed.getITEMS());
							} catch (Exception e) {
								System.err.println(e.getMessage());
								e.printStackTrace();

							}
							rut = usuarioDAO.findUserByTerminal4Rut(parametrosInGuia.getTerminal());
							values = new ArrayList<String>();
							values.add(rut);

							if (userStockRead != null && userStockRead.getStock_detalle() != null) {
								if (userStockRead.getStock_detalle().length() > 0) {
									Type listType = new TypeToken<ArrayList<ProductSLL>>() {
									}.getType();
									List<ProductSLL> productos = new Gson().fromJson(userStockRead.getStock_detalle(),
											listType);
									if (productos != null) {
										List<ProductSLL> productosMixed = new ArrayList<ProductSLL>();
										for (int i = 0; i < productos.size(); i++) {
											ProductSLL newPosPSLL = productos.get(i);
											newPosPSLL.setProducto(newPosPSLL.getProducto().replaceAll("\\s+", ""));
											newPosPSLL.setCantidad(newPosPSLL.getCantidad().replaceAll("\\s+", ""));
											String strCnt1 = newPosPSLL.getCantidad();
											String[] partsCnt1 = strCnt1.split(",");
											int count1 = Integer.parseInt(partsCnt1[0].replaceAll("\\s+", ""));
											for (int j = 0; j < productos.size(); j++) {
												ProductSLL newPosPSLLAux = productos.get(j);
												newPosPSLLAux.setProducto(
														newPosPSLLAux.getProducto().replaceAll("\\s+", ""));
												newPosPSLLAux.setCantidad(
														newPosPSLLAux.getCantidad().replaceAll("\\s+", ""));
												String strCnt2 = newPosPSLL.getCantidad();
												String[] partsCnt2 = strCnt2.split(",");
												int count2 = Integer.parseInt(partsCnt2[0].replaceAll("\\s+", ""));
												newPosPSLLAux.setCantidad(String.valueOf(count2));
												if (newPosPSLLAux.getProducto().equals(newPosPSLL.getProducto())
														&& newPosPSLLAux.getUnidad().equals(newPosPSLL.getUnidad())
														&& i != j) {
													count1 += count2;
													guiaProductos.getITEMS().remove(j);
												}
											}

											newPosPSLL.setCantidad(String.valueOf(count1));
											productosMixed.add(newPosPSLL);
											guiaProductos.getITEMS().remove(i);
										}
										productos = productosMixed;

										for (int j = 0; j < productos.size(); j++) {
											ProductSLL oProd = productos.get(j);
											oProd.setProducto(oProd.getProducto().replaceAll("\\s+", ""));
											oProd.setCantidad(oProd.getCantidad().replaceAll("\\s+", ""));
											int i = 0;

											do {
												OdataItemGuiaProducto oOIGProd = guiaProductos.getITEMS().get(i);
												oOIGProd.setCODIGO_PRODUCTO(
														oOIGProd.getCODIGO_PRODUCTO().replaceAll("\\s+", ""));
												oOIGProd.setCANTIDAD(oOIGProd.getCANTIDAD().replaceAll("\\s+", ""));
												if (oOIGProd.getCODIGO_PRODUCTO().equals(oProd.getProducto())) {
													String strCnt1 = oOIGProd.getCANTIDAD();
													String[] partsCnt1 = strCnt1.split(",");
													oOIGProd.setCANTIDAD(partsCnt1[0].replaceAll("\\s+", ""));
													String strCnt2 = oProd.getCantidad();
													String[] partsCnt2 = strCnt2.split(",");
													oProd.setCantidad(partsCnt2[0].replaceAll("\\s+", ""));
													int count = Integer.parseInt(oOIGProd.getCANTIDAD())
															+ Integer.parseInt(oProd.getCantidad());
													oOIGProd.setCANTIDAD(String.valueOf(count));
													oProd.setCantidad(String.valueOf(0));
												}
												guiaProductos.getITEMS().set(i, oOIGProd);
												i++;
											} while (i < guiaProductos.getITEMS().size());

											if (i == guiaProductos.getITEMS().size()
													&& !(oProd.getCantidad().equals("0"))) {
												OdataItemGuiaProducto addingProducto = new OdataItemGuiaProducto();
												addingProducto.setCODIGO_PRODUCTO(oProd.getProducto());
												addingProducto.setCANTIDAD(oProd.getCantidad());
												addingProducto.setUNIDAD(oProd.getUnidad());
												addingProducto.setPRECIO(oProd.getPrecio());
												addingProducto.setDESCRIPCION("*");
												guiaProductos.getITEMS().add(addingProducto);
											}

											if (oProd.getUnidad().equals("CIL") || oProd.getUnidad().equals("cil")
													|| oProd.getUnidad().equals("def")
													|| oProd.getUnidad().equals("DEF")) {
												boolean existProdCil = false;
												i = 0;

												do {
													OdataItemGuiaProducto oOIGProd = guiaProductos.getITEMS().get(i);
													oOIGProd.setCODIGO_PRODUCTO(
															oOIGProd.getCODIGO_PRODUCTO().replaceAll("\\s+", ""));
													if (oOIGProd.getCODIGO_PRODUCTO().equals(oProd.getProducto())) {
														existProdCil = true;
													}
													i++;
												} while (i < guiaProductos.getITEMS().size());

												if (!(existProdCil)) {
													OdataItemGuiaProducto addingProducto = new OdataItemGuiaProducto();
													addingProducto.setCODIGO_PRODUCTO(oProd.getProducto());
													addingProducto.setCANTIDAD(oProd.getCantidad());
													addingProducto.setUNIDAD(oProd.getUnidad());
													addingProducto.setPRECIO(oProd.getPrecio());
													addingProducto.setDESCRIPCION("*");
													guiaProductos.getITEMS().add(addingProducto);
												}
											}
										}
									}
								}
							}

							if (userStockRead == null || userStockRead.getEntregar() == null) {
								@SuppressWarnings("unused")
								Type OdataItemGuiaProductos_listType = new TypeToken<OdataItemGuiaProductos>() {
								}.getType();
								objToReturn = new Gson().toJson(guiaProductos, OdataItemGuiaProductos.class);
							} else {
								if (userStockRead.getEntregar().equals("1")
										&& userStockRead.getRecepcionado().equals("0")) {
									response.setStatus(421);
									responseWS.setResultado(false);
									// responseWS.mensaje = e.getMessage();
									responseWS.setMensaje(
											"Terminal pendiente de liquidar, debe asistir a su centro LIPIGAS mas cercano.");
									objToReturn = responseWS.returnJson();
								} else {
									Type OdataItemGuiaProductos_listType = new TypeToken<OdataItemGuiaProductos>() {
									}.getType();
									// System.err.println("GUIA PRODUCTOS OLD" +
									// guiaProductos);
									objToReturn = new Gson().toJson(guiaProductos, OdataItemGuiaProductos_listType);
									// System.err.println("GUIA PRODUCTOS OLD
									// POST TRANSFORM " + guiaProductos);
								}
							}
						} catch (Exception e) {
							System.err.println(e.getMessage());
							e.printStackTrace();
							response.setStatus(400);
							responseWS.setResultado(false);
							responseWS.setMensaje("Guia no encontrada, consumida o no corresponde al terminal.");
							return (objToReturn = responseWS.returnJson());
						}

						break;
					default:
						objToReturn = concatenateAllJsons;
						break;
					}
				} catch (IOException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					response.setStatus(400);
					responseWS.setResultado(false);
					responseWS.setMensaje(e.getMessage());
					return (objToReturn = responseWS.returnJson());
				} catch (RuntimeException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					httpGet.abort();
					response.setStatus(400);
					responseWS.setResultado(false);
					responseWS.setMensaje(e.getMessage());
					return (objToReturn = responseWS.returnJson());
				} finally {
					try {
						instream.close();
					} catch (Exception e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
						response.setStatus(400);
						responseWS.setResultado(false);
						responseWS.setMensaje(e.getMessage());
						return (objToReturn = responseWS.returnJson());
					}
				}
			}
		} catch (NamingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Lookup of destination failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have the destination " + destinationName + " configured.";
			LOGGER.error("Lookup of destination failed", e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
			response.setStatus(400);
			responseWS.setResultado(false);
			responseWS.setMensaje(errorMessage);
			return (objToReturn = responseWS.returnJson());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Connectivity operation failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have an HTTP proxy configured in your "
					+ "local Eclipse environment in case your environment uses "
					+ "an HTTP proxy for the outbound Internet " + "communication.";
			LOGGER.error("Connectivity operation failed", e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
			response.setStatus(400);
			responseWS.setResultado(false);
			responseWS.setMensaje(errorMessage);
			return (objToReturn = responseWS.returnJson());
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
				return objToReturn;
			}
		}
		return objToReturn;
	}

	@SuppressWarnings("deprecation")
	public String odataServiceCallRead(String metodo, String json, HttpServletResponse response, String imgb64)
			throws IOException, MessagingException {
		HttpClient httpClient = null;
		// String destinationName = request.getParameter("DestinationName");
		String destinationName = Destinations.door;
		String objToReturn = "";
		List<String> values = new ArrayList<String>();
		StockActual userStockRead = new StockActual();
		String rut = "";
		Response responseWS = new Response();
		try {
			// Get HTTP destination
			Context ctx = new InitialContext();
			
			//DEPRECATE NEO
			/*
			HttpDestination destination = null;
			destination = (HttpDestination) ctx.lookup("java:comp/env/" + destinationName);
			httpClient = destination.createHttpClient();
			 */
			
			 //CF SDK CLOUD
			HttpDestination httpDestination = getDestination(destinationName).asHttp();
			HttpClient httpClient = getHttpClient(httpDestination);

			String lv_query = query.replaceAll("@method@", metodo);
			lv_query = lv_query.replaceAll("@item@", json);
			lv_query = lv_query.replaceAll("@imgb64@", imgb64.substring(0, 200));
			objToReturn = "";
			//String uri = "";
			//uri = destination.getURI().toString() + service + commSet + lv_query;
			//uri = URIUtil.encodeQuery(uri);
			String requestUrl = httpDestination.getUri() + service + commSet + lv_query;
			
			//HttpGet httpGet = new HttpGet((generateUrl(uri).toURI()));
			HttpGet httpGet = new HttpGet(requestUrl);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();

			if (statusCode != HTTP_OK) {
				response.setStatus(statusCode);
				return (objToReturn = String.valueOf(statusCode));
			}

			HttpEntity entity = httpResponse.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();

				try {
					byte[] buffer = new byte[COPY_CONTENT_BUFFER_SIZE];
					while ((instream.read(buffer)) != -1) {
						objToReturn += new String(buffer, "ISO-8859-1");
					}

					String string = objToReturn;
					String[] parts = string.split("]}}");
					objToReturn = parts[0] + "]}}";

					BaseOdataServiceGET oGet = new BaseOdataServiceGET();

					oGet = new Gson().fromJson(objToReturn, BaseOdataServiceGET.class);

					String concatenateAllJsons = "";

					for (int i = 0; i < oGet.getD().getResults().size(); i++) {
						concatenateAllJsons += oGet.getD().getResults().get(i).getItem();
					}

					if (!isJson(concatenateAllJsons)) {
						responseWS.setMensaje("Error de carga");
						responseWS.setResultado(false);
						response.setStatus(400);
						return responseWS.returnJson();
					}

					switch (metodo) {
					case "VALIDATE_COUPON":
						concatenateAllJsons = concatenateAllJsons.replaceAll("\"RESULT\":\"true\"", "\"RESULT\":true");
						concatenateAllJsons = concatenateAllJsons.replaceAll("\"RESULT\":\"false\"",
								"\"RESULT\":false");
						ValidateCoupon vc = gson.fromJson(concatenateAllJsons, ValidateCoupon.class);
						if (!vc.isRESULT()) {
							response.setStatus(400);
						}
						objToReturn = gson.toJson(vc);
						break;
					case "GET_GUIDE_ITEMS_FOR_DEVICE":
						try {
							ParametrosInGuia parametrosInGuia = new Gson().fromJson(json, ParametrosInGuia.class);
							OdataItemGuiaProductos guiaProductos = new Gson().fromJson(concatenateAllJsons,
									OdataItemGuiaProductos.class);
							rut = usuarioDAO.findUserByTerminal4Rut(parametrosInGuia.getTerminal());
							values = new ArrayList<String>();
							values.add(rut);

							if (userStockRead != null && userStockRead.getStock_detalle() != null) {
								if (userStockRead.getStock_detalle().length() > 0) {
									Type listType = new TypeToken<ArrayList<ProductSLL>>() {
									}.getType();

									List<ProductSLL> productos = new Gson().fromJson(userStockRead.getStock_detalle(),
											listType);
									if (productos != null) {
										for (int j = 0; j < productos.size(); j++) {
											ProductSLL oProd = productos.get(j);
											oProd.setProducto(oProd.getProducto().replaceAll("\\s+", ""));
											oProd.setCantidad(oProd.getCantidad().replaceAll("\\s+", ""));
											int i = 0;

											do {
												OdataItemGuiaProducto oOIGProd = guiaProductos.getITEMS().get(i);
												oOIGProd.setCODIGO_PRODUCTO(
														oOIGProd.getCODIGO_PRODUCTO().replaceAll("\\s+", ""));
												oOIGProd.setCANTIDAD(oOIGProd.getCANTIDAD().replaceAll("\\s+", ""));
												if (oOIGProd.getCODIGO_PRODUCTO().equals(oProd.getProducto())) {
													String strCnt1 = oOIGProd.getCANTIDAD();
													String[] partsCnt1 = strCnt1.split(",");
													oOIGProd.setCANTIDAD(partsCnt1[0].replaceAll("\\s+", ""));
													String strCnt2 = oProd.getCantidad();
													String[] partsCnt2 = strCnt2.split(",");
													oProd.setCantidad(partsCnt2[0].replaceAll("\\s+", ""));
													int count = Integer.parseInt(oOIGProd.getCANTIDAD())
															+ Integer.parseInt(oProd.getCantidad());
													oOIGProd.setCANTIDAD(String.valueOf(count));
													oProd.setCantidad(String.valueOf(0));
												}
												guiaProductos.getITEMS().set(i, oOIGProd);
												i++;
											} while (i < guiaProductos.getITEMS().size());

											if (i == guiaProductos.getITEMS().size()
													&& !(oProd.getCantidad().equals("0"))) {
												OdataItemGuiaProducto addingProducto = new OdataItemGuiaProducto();
												addingProducto.setCODIGO_PRODUCTO(oProd.getProducto());
												addingProducto.setCANTIDAD(oProd.getCantidad());
												addingProducto.setUNIDAD(oProd.getUnidad());
												addingProducto.setPRECIO(oProd.getPrecio());
												addingProducto.setDESCRIPCION("*");
												guiaProductos.getITEMS().add(addingProducto);
											}

											if (oProd.getUnidad().equals("CIL") || oProd.getUnidad().equals("cil")
													|| oProd.getUnidad().equals("def")
													|| oProd.getUnidad().equals("DEF")) {
												boolean existProdCil = false;
												i = 0;

												do {
													OdataItemGuiaProducto oOIGProd = guiaProductos.getITEMS().get(i);
													oOIGProd.setCODIGO_PRODUCTO(
															oOIGProd.getCODIGO_PRODUCTO().replaceAll("\\s+", ""));
													if (oOIGProd.getCODIGO_PRODUCTO().equals(oProd.getProducto())) {
														existProdCil = true;
													}
													i++;
												} while (i < guiaProductos.getITEMS().size());

												if (!(existProdCil)) {
													OdataItemGuiaProducto addingProducto = new OdataItemGuiaProducto();
													addingProducto.setCODIGO_PRODUCTO(oProd.getProducto());
													addingProducto.setCANTIDAD(oProd.getCantidad());
													addingProducto.setUNIDAD(oProd.getUnidad());
													addingProducto.setPRECIO(oProd.getPrecio());
													addingProducto.setDESCRIPCION("*");
													guiaProductos.getITEMS().add(addingProducto);
												}
											}
										}
									}
								}
							}

							objToReturn = new Gson().toJson(guiaProductos, guiaProductos.getClass());
						} catch (Exception e) {
							System.err.println(e.getMessage());
							e.printStackTrace();
							response.setStatus(400);
							responseWS.setResultado(false);
							responseWS.setMensaje("Guia no encontrada, consumida o no corresponde al terminal.");
							return (objToReturn = responseWS.returnJson());
						}

						break;
					default:
						objToReturn = concatenateAllJsons;
						break;
					}
				} catch (IOException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					response.setStatus(400);
					return gson.toJson(new GeneralMsg("Error", e.getMessage()));
				} catch (RuntimeException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					httpGet.abort();
					response.setStatus(400);
					responseWS.setResultado(false);
					responseWS.setMensaje(e.getMessage());
					return gson.toJson(new GeneralMsg("Error", e.getMessage()));
				} finally {
					// Closing the input stream will trigger connection release

					try {
						instream.close();
					} catch (Exception e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
						response.setStatus(400);
						return gson.toJson(new GeneralMsg("Error", e.getMessage()));
					}
				}
			}
		} catch (NamingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			response.setStatus(400);
			return gson.toJson(new GeneralMsg("Error", e.getMessage()));
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			response.setStatus(400);
			return gson.toJson(new GeneralMsg("Error", e.getMessage()));
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
				return objToReturn;
				// return gson.toJson(new GeneralMsg("Error",e.getMessage()));
			}
		}
		return objToReturn;
	}

	@SuppressWarnings("deprecation")
	protected String odataGetXCSRFToken(String metodo, String json, HttpServletResponse response) throws IOException {
		HttpClient httpClient = null;
		// String destinationName = request.getParameter("DestinationName");
		String destinationName = Destinations.door;
		String objToReturn = "";
		try {
			// Get HTTP destination
			Context ctx = new InitialContext();
			//DEPRECATE NEO
			/* 
			HttpDestination destination = null;

			destination = (HttpDestination) ctx.lookup("java:comp/env/" + destinationName);

			httpClient = destination.createHttpClient();
			// Create HTTP client
			String uri = destination.getURI().toString() + service + commSet;
			uri = URIUtil.encodeQuery(uri);
			*/
			HttpDestination httpDestination = getDestination(destinationName).asHttp();
			HttpClient httpClient = getHttpClient(httpDestination);

			String requestUrl = httpDestination.getUri() + service + commSet;

			objToReturn = "";

			//HttpGet httpGet = new HttpGet((generateUrl(uri).toURI()));
			HttpGet httpGet = new HttpGet(requestUrl);

			httpGet.setHeader("X-CSRF-Token", "Fetch");
			httpGet.setHeader("Content-Type", "application/json");
			HttpResponse httpResponse = httpClient.execute(httpGet);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != HTTP_OK) {
				throw new ServletException("Expected response status code is 200 but it is " + statusCode + " .");
			}

			// "X-CSRF-Token"
			Header[] headers = httpResponse.getHeaders("X-CSRF-Token");
			for (int i = 0; i < headers.length; i++) {
				// if (headers[i].getName().equals("X-CSRF-Token")) {
				objToReturn += headers[i].getValue();
				// }
			}
		} catch (NamingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Lookup of destination failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have the destination " + destinationName + " configured.";
			LOGGER.error("Lookup of destination failed", e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Connectivity operation failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have an HTTP proxy configured in your "
					+ "local Eclipse environment in case your environment uses "
					+ "an HTTP proxy for the outbound Internet " + "communication.";
			LOGGER.error("Connectivity operation failed", e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
				return objToReturn;
			}
		}
		return objToReturn;
	}

	@SuppressWarnings("deprecation")
	protected List<Header> odataGetXCSRFToken2(String metodo, String json, HttpServletResponse response)
			throws IOException {
		HttpClient httpClient = null;
		String destinationName = Destinations.doorPost;
		List<Header> headers = new ArrayList<Header>();

		try {
			// Get HTTP destination
			Context ctx = new InitialContext();
		
			//DEPRECATE NEO
			/* 
			HttpDestination destination = null;

			destination = (HttpDestination) ctx.lookup("java:comp/env/" + destinationName);
			httpClient = destination.createHttpClient();
			// Create HTTP client
			String uri = destination.getURI().toString() + service + "/$metadata";
			uri = URIUtil.encodeQuery(uri);
			*/
			//CF SDK CLOUD
			HttpDestination httpDestination = getDestination(destinationName).asHttp();
			HttpClient httpClient = getHttpClient(httpDestination);

			String requestUrl = httpDestination.getUri() + service + "/$metadata";

			//HttpGet httpGet = new HttpGet((generateUrl(uri).toURI()));
			HttpGet httpGet = new HttpGet(requestUrl);
			
			httpGet.setHeader("X-CSRF-Token", "Fetch");
			HttpResponse httpResponse = httpClient.execute(httpGet);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != HTTP_OK) {
				throw new ServletException(
						metodo + ": Expected response status code is 200 but it is " + statusCode + " .");
			}

			Header[] allHeaders = httpResponse.getAllHeaders();

			for (int i = 0; i < allHeaders.length; i++) {
				Header header = allHeaders[i];

				if (header.getName().equalsIgnoreCase("X-CSRF-Token")
						|| header.getName().equalsIgnoreCase("set-cookie")) {
					headers.add(header);
				}
			}
		} catch (NamingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Lookup of destination failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have the destination " + destinationName + " configured.";
			LOGGER.error("Lookup of destination failed", e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Connectivity operation failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have an HTTP proxy configured in your "
					+ "local Eclipse environment in case your environment uses "
					+ "an HTTP proxy for the outbound Internet " + "communication.";
			LOGGER.error("Connectivity operation failed", e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
				return headers;
			}
		}
		return headers;
	}

	@SuppressWarnings("deprecation")
	protected Response odataServiceCallCreate(String metodo, String json, HttpServletResponse response)
			throws IOException {
		//HttpClient httpClient = null;

		// String destinationName = Destinations.door;
		String destinationName = Destinations.doorPost;

		String objToReturn = "";
		Response responseWS = new Response();

		try {
			// Get HTTP destination
			Context ctx = new InitialContext();
			
			//DEPRECATE NEO 
			/* 
			HttpDestination destination = null;
			destination = (HttpDestination) ctx.lookup("java:comp/env/" + destinationName);
			httpClient = destination.createHttpClient();

						
			 * String lv_query = query.replaceAll("@method@", metodo); lv_query
			 * = lv_query.replaceAll("@item@", json);
			 
			// Create HTTP client
			String uri = destination.getURI().toString() + service + commSet;
			// String uri = destination.getURI().toString();
			uri = URIUtil.encodeQuery(uri);

			HttpPost httpPost = new HttpPost((generateUrl(uri).toURI()));

			*/

			// CF SDK Cloud
			HttpDestination httpDestination = getDestination(destinationName).asHttp();
			HttpClient httpClient = getHttpClient(httpDestination);
			
			String requestUrl = httpDestination.getUri() + service + commSet;
		
			HttpPost httpPost = new HttpPost(requestUrl);


			objToReturn = "";
			StringEntity postingString = new StringEntity(json);
			httpPost.setEntity(postingString);
			httpPost.addHeader("Content-type", "application/json");
			String cookie = "";

			// httpPost.addHeader("X-CSRF-Token", odataGetXCSRFToken("", "",
			// response));
			for (Header header : odataGetXCSRFToken2("OData get token", "", response)) {
				if (header.getName().equalsIgnoreCase("set-cookie")) {
					cookie += header.getValue() + ";";
				} else if (header.getName().equalsIgnoreCase("X-CSRF-Token")) {
					httpPost.addHeader("X-CSRF-Token", header.getValue());
				}
			}
			httpPost.addHeader("Cookie", cookie);

			HttpResponse httpResponse = httpClient.execute(httpPost);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != 201) {
				// throw new ServletException("Expected response status code is
				// 200 but it is "
				// + statusCode + " .");
				response.setStatus(400);
				responseWS.setResultado(false);
				responseWS.setMensaje(metodo + ": Expected response status code is 201 but it is " + statusCode + " .");
				// return (objToReturn = responseWS.returnJson());
				return responseWS;
			}

			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				String data = "";

				try {
					// byte[] buffer = new byte[2048];
					// instream.read(buffer);
					// data = new String(buffer, "ISO-8859-1");

					byte[] buffer = new byte[COPY_CONTENT_BUFFER_SIZE];
					while ((instream.read(buffer)) != -1) {
						data += new String(buffer, "ISO-8859-1");
					}

					responseWS.setResultado(true);
					responseWS.setMensaje(new String(data.getBytes("ISO-8859-1")));
					objToReturn = responseWS.returnJson();
				} catch (IOException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					response.setStatus(400);
					responseWS.setResultado(false);
					responseWS.setMensaje(e.getMessage());
					// return (objToReturn = responseWS.returnJson());
					return responseWS;
				} catch (RuntimeException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					response.setStatus(400);
					httpPost.abort();
					responseWS.setResultado(false);
					responseWS.setMensaje(e.getMessage());
					// return (objToReturn = responseWS.returnJson());
					return responseWS;
				} finally {
					// Closing the input stream will trigger connection release

					try {
						instream.close();
					} catch (Exception e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		} catch (NamingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Lookup of destination failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have the destination " + destinationName + " configured.";
			// LOGGER.error("Lookup of destination failed", e);
			// response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			// errorMessage);
			response.setStatus(400);
			responseWS.setResultado(false);
			responseWS.setMensaje(errorMessage);
			// return (objToReturn = responseWS.returnJson());
			return responseWS;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			String errorMessage = "Connectivity operation failed with reason: " + e.getMessage() + ". See "
					+ "logs for details. Hint: Make sure to have an HTTP proxy configured in your "
					+ "local Eclipse environment in case your environment uses "
					+ "an HTTP proxy for the outbound Internet " + "communication.";
			// LOGGER.error("Connectivity operation failed", e);
			// response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			// errorMessage);
			response.setStatus(400);
			responseWS.setResultado(false);
			responseWS.setMensaje(errorMessage);
			// return (objToReturn = responseWS.returnJson());
			return responseWS;
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
				// return objToReturn;
				return responseWS;
			}
		}
		// return objToReturn;
		return responseWS;
	}

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException,
			SQLException, NoSuchAlgorithmException, ParseException, NumberFormatException, InterruptedException {
	}

	protected static String getPostDataBody(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = request.getReader();
			reader.mark(10000);

			String line;
			do {
				line = reader.readLine();
				if (line != null)
					sb.append(line);
			} while (line != null);
			reader.reset();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			e.printStackTrace();
		}

		return sb.toString().trim().replaceAll("\\s++", " ");
	}

	protected Map<String, String> getHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}

	protected String encodeB64(String strToHash) {
		return encoder.encodeBuffer(strToHash.getBytes()).replaceAll("\\s+", "");
	}

	protected String decodeB64(String hash) throws IOException {
		String objToReturn = new String(decoder.decodeBuffer(hash));
		return objToReturn;
	}

	protected static boolean isJson(String Json) {
		try {
			new JSONObject(Json);
		} catch (JSONException ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			try {
				new JSONArray(Json);
			} catch (JSONException ex1) {
				System.err.println(ex1.getMessage());
				ex1.printStackTrace();
				return false;
			}
		}
		return true;
	}

	private static URL generateUrl(String urlplusquery) {
		try {
			return new URL(urlplusquery);
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
	}

	protected String toMD5(String clave) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(clave.getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		return number.toString(16);
	}

	public void destroy() {
		try {
			exiting();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void exiting() throws SQLException {
		System.err.println("Destroying servlet...");
	}

	public void displayFileTree(File rootDirectory) {
		displayFileTree(rootDirectory, "");
	}

	private void displayFileTree(File rootDirectory, String indent) {
		for (File file : rootDirectory.listFiles()) {
			System.err.println(indent + file.getName());
			if (file.isDirectory()) {
				displayFileTree(file, indent + "    ");
			}
		}
	}

	public void findFile(File rootDirectory, String fileName) {
		assignFile(rootDirectory, "", fileName);
	}

	private void assignFile(File rootDirectory, String indent, String fileName) {
		for (File file : rootDirectory.listFiles()) {
			// if (!file.isDirectory()) {
			// System.err.println(file.getName());
			// System.err.println(file.getAbsolutePath());
			// System.err.println(file.getPath());
			// System.err.println(file.getParent());
			// }
			if (file.isDirectory()) {
				assignFile(file, indent + "    ", fileName);
			}
			if (file.getName().replaceAll(" |", "").equals(fileName)) {

				try {
					this.fileLock = FileUtils.openInputStream(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}
