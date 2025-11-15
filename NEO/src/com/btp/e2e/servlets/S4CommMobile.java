package com.btp.e2e.servlets;

import com.btp.e2e.entities.ClaseMensaje;
import com.btp.e2e.entities.LogRecepciones;
import com.btp.e2e.entities.Usuario;
import com.btp.e2e.servlets.Structures4Jsons.Login;
import com.btp.e2e.servlets.Structures4Jsons.LoginOutData;
import com.btp.e2e.servlets.Structures4Jsons.LoginTokenData;
import com.btp.e2e.servlets.Structures4Jsons.OdataStructure;
import com.btp.e2e.servlets.Structures4Jsons.Response;
import com.btp.e2e.servlets.Structures4Jsons.TokenSessionJwt;
import com.btp.e2e.servlets.Structures4Jsons.UserTokenSave;
import com.btp.e2e.servlets.aditionalContents.Tiempo;
import com.btp.e2e.servlets.pushNotifications.PushNot;
import com.google.gson.JsonSyntaxException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import sun.org.mozilla.javascript.internal.ObjToIntMap;

/**
 * Servlet implementation class S4Comm
 */
@WebServlet("/mobile/")
public class S4CommMobile extends S4CommMain {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(S4CommMobile.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S4CommMobile() {
		super();
	}

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, NoSuchAlgorithmException, ParseException, NumberFormatException,
			InterruptedException, JsonSyntaxException {
		System.err.println("HANDLE REQUEST INIT");
//		Locale newLocale = new Locale();
//		
//		request.getLocale();
//		Locale.setDefault(newLocale);
		response.setHeader("Content-Language", "es");
		boolean headerValueAuthEmpty = false;
		String headerValueAPP_VERSION = request.getHeader("VERSION");
		String headerValueOS_VERSION = request.getHeader("OS_VERSION");
		String headerValueMODEL_DEVICE = request.getHeader("MODEL");
		if (headerValueAPP_VERSION == null)
			headerValueAPP_VERSION = "0";
		if (headerValueOS_VERSION == null)
			headerValueOS_VERSION = "No identificado";
		if (headerValueMODEL_DEVICE == null)
			headerValueMODEL_DEVICE = "No identificado";
		String headerValueAuthorization = request.getHeader("Authorization");
		if (headerValueAuthorization != null)
			if (headerValueAuthorization.length() > 0)
				if (headerValueAuthorization.indexOf("Bearer ") != -1)
					headerValueAuthorization = headerValueAuthorization.replaceFirst("Bearer ", "");
				else
					headerValueAuthEmpty = true;

		String[] paramValuesFormat = request.getParameterValues("format");
		PrintWriter out = response.getWriter();
		String paramValue = "";
		String objectToReturn = "";
		String body = getPostDataBody(request);

		try {
			/* EVALUAMOS BODY AL LLEGAR A SCP */

			boolean returnXML = false;
			List<String> values = new ArrayList<String>();
			new ClaseMensaje();
			new Usuario();
			LogRecepciones logRecepciones = new LogRecepciones();
			Response responseWS = new Response();
			new Response();
			logRecepciones.setOs_version(headerValueOS_VERSION);
			logRecepciones.setModel_device(headerValueMODEL_DEVICE);
			logRecepciones.setApp_version(headerValueAPP_VERSION);

			if (body.length() == 0) {
				body = "{}";
			}

			boolean isValidJson = isJson(body);

			if (paramValuesFormat != null) {
				for (int i = 0; i < paramValuesFormat.length; i++) {
					paramValue = paramValuesFormat[i];
					switch (paramValue.toLowerCase()) {
					case "xml":
						response.setContentType("application/xml");
						returnXML = true;
						break;
					case "json":
						response.setContentType("application/json");
						break;
					default:
						response.setContentType("application/json");
						break;
					}
				}
			} else {
				response.setContentType("application/json");
			}

			if (!(isValidJson)) {
				responseWS.setResultado(false);
				responseWS.setMensaje("Error, proceso invalido.");
				objectToReturn = responseWS.returnJson();
			} else {
				paramValue = request.getHeader("method");
				if (paramValue != null) {

					switch (paramValue) { // Todos los casos que se encuentran
					// en
					// esta evaluacion se omiten de
					// necesitar tener un token JWT y se
					// pueden hacer sin token.
					case "recoverUser":
						break;
					case "login":
						break;
					case "sendPush":
						break;
					case "getFile":					
						break;
//					case "checkToken":
//						if (headerValueAuthorization != null && !headerValueAuthEmpty) {
//							System.err.println("CHECK_TOKEN INIT");
//							TokenSessionJwt tknchck = usuarioDAO.parseJWT(headerValueAuthorization);
//							// System.err.println("CHECK_TOKEN");
//							System.err.println(gson.toJson(tknchck));
//							System.err.println(gson.toJson(headerValueAuthorization));
//							if (tknchck != null) {
//								System.err.println("CHECK_TOKEN_OK");
//								String copyAuth = new String(headerValueAuthorization);
//								TokenSessionJwt jwtParsed = usuarioDAO.parseJWT(headerValueAuthorization);
//								// AQUI CHECK TOKEN
//								System.err.println("TOKEN CHECK: " + jwtParsed.getAttrs().getUSUARIO());
//								if (jwtParsed.getAttrs().getUSUARIO() != null) {
//									System.err.println("TOKEN CHECK content user not equal null: "
//											+ jwtParsed.getAttrs().getUSUARIO());
//									if (jwtParsed.getAttrs().getUSUARIO().equals("")) {
//										System.err.println("TOKEN CHECK content user empty. !# "
//												+ jwtParsed.getAttrs().getUSUARIO());
//										responseWS.setResultado(false);
//										responseWS.setMensaje("Error, proceso invalido.");
//										objectToReturn = responseWS.returnJson();
//										out.print(new String(objectToReturn.getBytes(), "ISO-8859-1"));
//										out.close();
//										return;
//									}
//									if (!jwtParsed.getAttrs().getUSUARIO().equals("")) {
//										System.err.println("TOKEN CHECK content user not empty: "
//												+ jwtParsed.getAttrs().getUSUARIO());
//										boolean checkingTokenInSAP = checkTokenInSAP(request, response, jwtParsed,
//												copyAuth);
//										if (!checkingTokenInSAP) {
//											responseWS.setResultado(false);
//											responseWS.setMensaje("Error, proceso invalido.");
//											objectToReturn = responseWS.returnJson();
//											out.print(new String(objectToReturn.getBytes(), "ISO-8859-1"));
//											out.close();
//											return;
//										}
//									}
//								} else {
//									responseWS.setResultado(false);
//									responseWS.setMensaje("Error, proceso invalido.");
//									objectToReturn = responseWS.returnJson();
//									out.print(new String(objectToReturn.getBytes(), "ISO-8859-1"));
//									out.close();
//									return;
//								}
//							} else {
//								paramValue = "";
//								response.setStatus(401);
//								responseWS.setResultado(false);
//								responseWS.setMensaje("Error de autenticacion");
//								objectToReturn = responseWS.returnJson();
//								logRecepciones.setRespuesta(objectToReturn);
//								values = new ArrayList<String>();
//								if (logRecepciones.getRespuesta().length() < 4999) {
//									values.add(logRecepciones.getRespuesta());
//								} else {
//									values.add(logRecepciones.getRespuesta().substring(0, 4999));
//								}
//								values.add(String.valueOf(response.getStatus()));
//								logRecepciones.setTimestamp_end(Tiempo.getNTPDateCL(parametroCambioHorario.getValor()));
//								values.add(logRecepciones.getTimestamp_end());
//								values.add(logRecepciones.getId());
//								out.print(new String(objectToReturn.getBytes(), "ISO-8859-1"));
//								out.close();
//								return;
//							}
//						} else {
//							paramValue = "";
//							response.setStatus(401);
//							responseWS.setResultado(false);
//							responseWS.setMensaje("Error de autenticacion");
//							objectToReturn = responseWS.returnJson();
//							logRecepciones.setRespuesta(objectToReturn);
//							values = new ArrayList<String>();
//							if (logRecepciones.getRespuesta().length() < 4999) {
//								values.add(logRecepciones.getRespuesta());
//							} else {
//								values.add(logRecepciones.getRespuesta().substring(0, 4999));
//							}
//							values.add(String.valueOf(response.getStatus()));
//							logRecepciones.setTimestamp_end(Tiempo.getNTPDateCL(parametroCambioHorario.getValor()));
//							values.add(logRecepciones.getTimestamp_end());
//							values.add(logRecepciones.getId());
//							// logRecepcionesDAO.update(values);
//							out.print(new String(objectToReturn.getBytes(), "ISO-8859-1"));
//							out.close();
//							return;
//						}
//						break;
					default:
						if (headerValueAuthorization != null && !headerValueAuthEmpty) {
							System.err.println("CHECK_TOKEN INIT");
							TokenSessionJwt tknchck = usuarioDAO.parseJWT(headerValueAuthorization);
							// System.err.println("CHECK_TOKEN");
							System.err.println(gson.toJson(tknchck));
							System.err.println(gson.toJson(headerValueAuthorization));
							if (tknchck != null) {
								System.err.println("CHECK_TOKEN_OK");
								String copyAuth = new String(headerValueAuthorization);
								TokenSessionJwt jwtParsed = usuarioDAO.parseJWT(headerValueAuthorization);
								// AQUI CHECK TOKEN
								System.err.println("TOKEN CHECK: " + jwtParsed.getAttrs().getUSUARIO());
								if (jwtParsed.getAttrs().getUSUARIO() != null) {
									System.err.println("TOKEN CHECK content user not equal null: "
											+ jwtParsed.getAttrs().getUSUARIO());
									if (jwtParsed.getAttrs().getUSUARIO().equals("")) {
										System.err.println("TOKEN CHECK content user empty. !# "
												+ jwtParsed.getAttrs().getUSUARIO());
										responseWS.setResultado(false);
										responseWS.setMensaje("Error, proceso invalido.");
										objectToReturn = responseWS.returnJson();
										response.setStatus(403);
										out.print(new String(objectToReturn.getBytes(), "ISO-8859-1"));
										out.close();
										return;
									}
									if (!jwtParsed.getAttrs().getUSUARIO().equals("")) {
										System.err.println("TOKEN CHECK content user not empty: "
												+ jwtParsed.getAttrs().getUSUARIO());
										boolean checkingTokenInSAP = checkTokenInSAP(request, response, jwtParsed,
												copyAuth);
										if (!checkingTokenInSAP) {
											responseWS.setResultado(false);
											responseWS.setMensaje("Error, proceso invalido.");
											objectToReturn = responseWS.returnJson();
											response.setStatus(403);
											out.print(new String(objectToReturn.getBytes(), "ISO-8859-1"));
											out.close();
											return;
										}
									}
								} else {
									responseWS.setResultado(false);
									responseWS.setMensaje("Error, proceso invalido.");
									objectToReturn = responseWS.returnJson();
									response.setStatus(403);
									out.print(new String(objectToReturn.getBytes(), "ISO-8859-1"));
									out.close();
									return;
								}
							} else {
								paramValue = "";
								response.setStatus(401);
								responseWS.setResultado(false);
								responseWS.setMensaje("Error de autenticacion");
								objectToReturn = responseWS.returnJson();
								logRecepciones.setRespuesta(objectToReturn);
								values = new ArrayList<String>();
								if (logRecepciones.getRespuesta().length() < 4999) {
									values.add(logRecepciones.getRespuesta());
								} else {
									values.add(logRecepciones.getRespuesta().substring(0, 4999));
								}
								values.add(String.valueOf(response.getStatus()));
								logRecepciones.setTimestamp_end(Tiempo.getNTPDateCL(parametroCambioHorario.getValor()));
								values.add(logRecepciones.getTimestamp_end());
								values.add(logRecepciones.getId());
								response.setStatus(403);
								out.print(new String(objectToReturn.getBytes(), "ISO-8859-1"));
								out.close();
								return;
							}
						} else {
							paramValue = "";
							response.setStatus(401);
							responseWS.setResultado(false);
							responseWS.setMensaje("Error de autenticacion");
							objectToReturn = responseWS.returnJson();
							logRecepciones.setRespuesta(objectToReturn);
							values = new ArrayList<String>();
							if (logRecepciones.getRespuesta().length() < 4999) {
								values.add(logRecepciones.getRespuesta());
							} else {
								values.add(logRecepciones.getRespuesta().substring(0, 4999));
							}
							values.add(String.valueOf(response.getStatus()));
							logRecepciones.setTimestamp_end(Tiempo.getNTPDateCL(parametroCambioHorario.getValor()));
							values.add(logRecepciones.getTimestamp_end());
							values.add(logRecepciones.getId());
							// logRecepcionesDAO.update(values);
							response.setStatus(403);
							out.print(new String(objectToReturn.getBytes(), "ISO-8859-1"));
							out.close();
							return;
						}

						break;
					}
					new Response();
					switch (paramValue) {
					case "recoverUser":
						objectToReturn = recoverUser(request, response, body, this, headerValueAPP_VERSION);
						break;
					case "login":
						objectToReturn = login3(request, response, body, this, headerValueAPP_VERSION);
						break;
					case "logout":
						usuarioDAO.selectUserByRut(body);
						objectToReturn = logout(request, response, body, this, headerValueAPP_VERSION);
						break;
					case "checkBearerContent2":
						if (headerValueAuthorization != null) {
							TokenSessionJwt tknchck = usuarioDAO.parseJWT(headerValueAuthorization);
							if (tknchck != null) {
								objectToReturn = tknchck.returnJson();
							} else {
								responseWS.setResultado(false);
								objectToReturn = responseWS.returnJson();
							}
						} else {
							response.setStatus(200);
							responseWS.setResultado(false);
							responseWS.setMensaje("Peticion erronea");
							objectToReturn = responseWS.returnJson();
						}

						break;
					case "odata":
						objectToReturn = this.odataServiceCallRead(body, response);
						break;
					case "sendPush":
						objectToReturn = PushNot.sendPushDirectly(body);
						break;
					case "getFile":
						objectToReturn = this.odataServiceCallReadFile(body, response);
						break;
					default:
						objectToReturn = this.odataServiceCallRead(body, response);
						break;
					}
				}
			}

			if (objectToReturn.length() == 0 || objectToReturn == null) {
				response.setStatus(200);
				responseWS.setResultado(false);
				responseWS.setMensaje("Peticion erronea");
				objectToReturn = responseWS.returnJson();
			}

			if (returnXML) {
				JSONObject json = new JSONObject(objectToReturn);
				String strXml = XML.toString(json);
				objectToReturn = strXml;
			}

			logRecepciones.setRespuesta(objectToReturn);
			values = new ArrayList<String>();
			if (logRecepciones.getRespuesta().length() < 4999) {
				values.add(logRecepciones.getRespuesta());
			} else {
				values.add(logRecepciones.getRespuesta().substring(0, 4999));
			}
			values.add(String.valueOf(response.getStatus()));
			logRecepciones.setTimestamp_end(Tiempo.getNTPDateCL(parametroCambioHorario.getValor()));
			values.add(logRecepciones.getTimestamp_end());
			values.add(logRecepciones.getId());
			// logRecepcionesDAO.update(values);

			out.print(new String(objectToReturn.getBytes(), "ISO-8859-1").replaceAll("\\s++", " "));
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("HANDLE REQUEST CATCH");
			System.err.println(e.getMessage());
			e.printStackTrace();
			GeneralMsg gmsg = new GeneralMsg("Error de procesos durante la ejecucion",
					e.getMessage() + "; " + e.getStackTrace().toString());
			objectToReturn = gson.toJson(gmsg);
			out.print(new String(objectToReturn.getBytes(), "ISO-8859-1").replaceAll("\\s++", " "));
			out.close();
		} finally {
			System.err.println("HANDLE REQUEST END");
		}
	}

	private String login3(HttpServletRequest request, HttpServletResponse response, String body, S4CommMobile s4,
			String versionName) throws IOException, SQLException, NoSuchAlgorithmException {
		OdataStructure osIncoming = new OdataStructure();
		osIncoming.setMethod("LOGIN");
		osIncoming.setContent(body);

		String strPayload = "{}";
		Date date = new Date();
//		date.setTime(date.getTime() - (date.getTimezoneOffset() * 60000));
//		System.err.println("Fecha");
//		System.err.println(date.getDate());
//		System.err.println(date.getMonth());
//		System.err.println(date.getYear());
//		System.err.println("Hora");
//		System.err.println(date.getHours());
//		System.err.println(date.getMinutes());
//		System.err.println(date.getSeconds());
		long TIME_TO_EXPIRE = 1000 * 60 * 60 * 12;
		date.setTime(date.getTime() + TIME_TO_EXPIRE);
//		System.err.println("Fecha expirar");
//		System.err.println(date.getDate());
//		System.err.println(date.getMonth());
//		System.err.println(date.getYear());
//		System.err.println("Hora expirar");
//		System.err.println(date.getHours());
//		System.err.println(date.getMinutes());
//		System.err.println(date.getSeconds());
		LoginTokenData loginTokenData = new LoginTokenData();
		ResponseSAP responseSAP = new ResponseSAP();
		try {
			strPayload = this.odataServiceCallRead(gson.toJson(osIncoming), response);
			try {
				responseSAP = gson.fromJson(strPayload, responseSAP.getClass());
				loginTokenData.setUser(gson.fromJson(responseSAP.getRESULTS(), LoginOutData.class));
				loginTokenData.getUser();
				String MSG = "";
				Iterator<String> iterator = responseSAP.getMSGS().iterator();
				while (iterator.hasNext()) {
					String tMSG = iterator.next();
					MSG += MSG + ", " + tMSG;
				}
				loginTokenData.setMensaje(MSG);
				if (loginTokenData.getUser().getUSUARIO() != null
						&& loginTokenData.getUser().getUSUARIO().length() > 0) {
					byte[] key = "wFFft7Yrd9SxjMMwlt0x9ZRI8e6nWgrmA18V9ewBiG6kXZIDz32".getBytes( // qpW7jugqpIsq889BUk6XjI7PuC0d62TLNTKU6c0R0q4gdAyCHwFFft7Yrd9SxjMMwlt0x9ZRI8e6nWgrmA18V9ewBiG6kXZIDz32
							// original
							"ISO-8859-1");
					loginTokenData.setAcces_token(Jwts.builder().setExpiration(date)
							.setSubject(gson.toJson(loginTokenData.getUser(), loginTokenData.getUser().getClass()))
							.signWith(SignatureAlgorithm.HS512, key).compact());
					loginTokenData.setResultado(true);
					loginTokenData.setExpires(date.toString());
					loginTokenData.setToken_type("bearer");
					try {
						loginTokenData.setMensajesToken(saveTokenInSAP(request, response, loginTokenData));
					} catch (Exception eToken) {
						System.err.println(eToken);
						response.setStatus(200);
						return (gson.toJson(loginTokenData));
					}
					response.setStatus(200);
					return (gson.toJson(loginTokenData));
				} else {
					response.setStatus(401);
					return (gson.toJson(loginTokenData));
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println(e);
				response.setStatus(401);
				return (gson.toJson(loginTokenData));
			}
		} catch (Exception e) {
			// TODO: handle exception
			return (gson.toJson(loginTokenData));
		}
	}

	private String logout(HttpServletRequest request, HttpServletResponse response, String body, S4CommMobile s4,
			String versionName) throws IOException, SQLException, NoSuchAlgorithmException {
		OdataStructure osIncoming = new OdataStructure();
		osIncoming.setMethod("LOGOUT");
		osIncoming.setContent(body);
		String strPayload = "{}";
		Date date = new Date();
		// date.setTime(date.getTime() + (3600 * 24 * 365));
		date.setTime(date.getTime() + (3600 * 24 * 1));
		// LoginTokenData loginTokenData = new LoginTokenData();
		ResponseSAP responseSAP = new ResponseSAP();
		try {
			// D d =
			// gson.fromJson(this.odataServiceCallRead(gson.toJson(osIncoming),
			// response),D.class);
			strPayload = this.odataServiceCallRead(gson.toJson(osIncoming), response);
			try {
				responseSAP = gson.fromJson(strPayload, responseSAP.getClass());
				response.setStatus(200);
				return (gson.toJson(responseSAP));
			} catch (Exception e) {
				// TODO: handle exception
				response.setStatus(500);
				return (gson.toJson(responseSAP));
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus(500);
			return (gson.toJson(responseSAP));
		}
	}

	private String recoverUser(HttpServletRequest request, HttpServletResponse response, String body, S4CommMobile s4,
			String versionName) throws IOException, SQLException, NoSuchAlgorithmException {
		OdataStructure osIncoming = new OdataStructure();
		osIncoming.setMethod("RECOVER_USER");
		osIncoming.setContent(body);
		String strPayload = "{}";
		ResponseSAP responseSAP = new ResponseSAP();
		try {
			// D d =
			// gson.fromJson(this.odataServiceCallRead(gson.toJson(osIncoming),
			// response),D.class);
			strPayload = this.odataServiceCallRead(gson.toJson(osIncoming), response);
			try {
				responseSAP = gson.fromJson(strPayload, responseSAP.getClass());
				response.setStatus(200);
				return (gson.toJson(responseSAP));
			} catch (Exception e) {
				// TODO: handle exception
				response.setStatus(500);
				return (gson.toJson(responseSAP));
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus(500);
			return (gson.toJson(responseSAP));
		}
	}

	private ArrayList<String> saveTokenInSAP(HttpServletRequest request, HttpServletResponse response,
			LoginTokenData ltd) throws IOException, SQLException, NoSuchAlgorithmException {
		OdataStructure osIncoming = new OdataStructure();
		UserTokenSave uts = new UserTokenSave(ltd.getUser().getUSUARIO(), ltd.getUser().getEMAIL(),
				ltd.getAcces_token());
		osIncoming.setMethod("SET_TOKEN");
		osIncoming.setContent(gson.toJson(uts, uts.getClass()));
		ResponseSAP responseSAP = new ResponseSAP();

		try {
			responseSAP = gson.fromJson(this.odataServiceCallRead(gson.toJson(osIncoming), response),
					responseSAP.getClass());
			System.err.println(responseSAP.getRESULTS());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return responseSAP.getMSGS();
	}

	private boolean checkTokenInSAP(HttpServletRequest request, HttpServletResponse response, TokenSessionJwt jwtParsed,
			String token) throws IOException, SQLException, NoSuchAlgorithmException {
		OdataStructure osIncoming = new OdataStructure();
		UserTokenSave uts = new UserTokenSave(jwtParsed.getAttrs().getUSUARIO(), jwtParsed.getAttrs().getEMAIL(),
				token);
		osIncoming.setMethod("CHECK_TOKEN");
		osIncoming.setContent(gson.toJson(uts, uts.getClass()));
		ResponseSAP responseSAP = new ResponseSAP();

		try {
			responseSAP = gson.fromJson(this.odataServiceCallRead(gson.toJson(osIncoming), response),
					responseSAP.getClass());
			System.err.println(responseSAP.getRESULTS());
			return responseSAP.getRESULTS().contains("1");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return false;
		}
	}
}
