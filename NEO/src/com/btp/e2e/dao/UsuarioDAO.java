package com.btp.e2e.dao;

import com.btp.e2e.entities.Cierre;
import com.btp.e2e.entities.Entity;
import com.btp.e2e.entities.LogGuia;
import com.btp.e2e.entities.LogVenta;
import com.btp.e2e.entities.Pedido;
import com.btp.e2e.entities.StockActual;
import com.btp.e2e.entities.Turno;
import com.btp.e2e.entities.UserLoginAWS;
import com.btp.e2e.entities.Usuario;
import com.btp.e2e.servlets.S4CommMobile;
import com.btp.e2e.servlets.Structures4Jsons.AWSCELOJson;
import com.btp.e2e.servlets.Structures4Jsons.AttributesSession;
import com.btp.e2e.servlets.Structures4Jsons.BaseOdataServiceGET;
import com.btp.e2e.servlets.Structures4Jsons.CentrosAWS;
import com.btp.e2e.servlets.Structures4Jsons.LoginOutData;
import com.btp.e2e.servlets.Structures4Jsons.OdataItemGuiaProducto;
import com.btp.e2e.servlets.Structures4Jsons.ProductSLL;
import com.btp.e2e.servlets.Structures4Jsons.Response;
import com.btp.e2e.servlets.Structures4Jsons.SelectOption;
import com.btp.e2e.servlets.Structures4Jsons.SessionUser;
import com.btp.e2e.servlets.Structures4Jsons.TokenSessionJwt;
import com.btp.e2e.servlets.Structures4Jsons.UserAWS;
import com.btp.e2e.servlets.Structures4Jsons.UserAWSJson;
import com.btp.e2e.servlets.Structures4Jsons.UserUpdate;
import com.btp.e2e.servlets.Structures4Jsons.UsuarioLimagasPlaca;
import com.btp.e2e.servlets.Structures4Jsons.clusterNotif;
import com.btp.e2e.servlets.Structures4Jsons.correlativoAWS;
import com.btp.e2e.servlets.aditionalContents.Tiempo;
import com.btp.e2e.servlets.configs.Scheme;
import com.btp.e2e.servlets.pushNotifications.PushNot;
import com.btp.e2e.servlets.services.report.JavaEmail;
import com.btp.e2e.servlets.services.report.ListObjectsReport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import javax.mail.Exception;
// import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.xml.bind.DatatypeConverter;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings({ "unused", "deprecation", "restriction" })
public class UsuarioDAO extends Entity {

	private Response iresponse = new Response();
	public String parametroCambioHorario = "3";
	/* PRIVATE */
	private DataSource dataSource;
	private Gson gson;
	// private Key key = MacProvider.generateKey();
	private byte[] key;
	private BASE64Decoder decoder;
	private BASE64Encoder encoder;
	private static final int tries = 100;
	private String queryAllCenters = "SELECT DISTINCT CENTRO FROM \"" + Scheme.now
			+ "\".\"USUARIO\" ORDER BY CENTRO ASC;";

	/**
	 * Create new data access object with data source.
	 *
	 * @param conn
	 *
	 * @throws UnsupportedEncodingException
	 */
	public UsuarioDAO(DataSource newDataSource) throws SQLException, UnsupportedEncodingException {
		setDataSource(newDataSource);
		gson = new Gson();
		decoder = new BASE64Decoder();
		encoder = new BASE64Encoder();
		key = "wFFft7Yrd9SxjMMwlt0x9ZRI8e6nWgrmA18V9ewBiG6kXZIDz32".getBytes( // qpW7jugqpIsq889BUk6XjI7PuC0d62TLNTKU6c0R0q4gdAyCHwFFft7Yrd9SxjMMwlt0x9ZRI8e6nWgrmA18V9ewBiG6kXZIDz32
																				// original
				"ISO-8859-1");
	}

	/**
	 * Get data source which is used for the database operations.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Set data source to be used for the database operations.
	 */
	public void setDataSource(DataSource newDataSource) throws SQLException {
		this.dataSource = newDataSource;
		// checkTable();
	}

	/**
	 * Add a UserEntity to the table.
	 */
	public String addUser(Usuario user) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objectToReturn = "";
		try {
			PreparedStatement pstmt = connection
					.prepareStatement("INSERT INTO \"" + Scheme.now + "\".\"USUARIO\" VALUES(" + "	\"" + Scheme.now
							+ "\".\"SEQ_ID_USUARIO\".NEXTVAL/*ID <INTEGER>*/," + // 0
							"	?/*RUT <VARCHAR(255)>*/," + // 1
							"	?/*CLAVE <VARCHAR(255)>*/," + // 2
							"	?/*NOMBRE <VARCHAR(255)>*/," + // 3
							"	?/*TELEFONO <VARCHAR(255)>*/," + // 4
							"	?/*EMAIL <VARCHAR(255)>*/," + // 5
							"	?/*TOPEDEUDA <VARCHAR(255)>*/," + // 6
							"	''/*TOKEN <VARCHAR(1000)>*/," + //
							"	''/*IMEI <VARCHAR(15)>*/," + //
							"	?/*TIPO <VARCHAR(3)>*/," + // 7
							"	?/*ROL <VARCHAR(3)>*/," + // 8
							"	''/*NUMERO_CAMION <VARCHAR(255)>*/," + //
							"	?/*TERMINAL <VARCHAR(255)>*/," + // 9
							"	''/*IDPUSH <VARCHAR(255)>*/," + //
							"	?/*LISTA_PEDIDO <BOOLEAN>*/," + // 10
							"	?/*BLOQUEADO <BOOLEAN>*/," + // 11
							"	?/*USADESCUENTOS <BOOLEAN>*/," + // 12
							"	?/*CURRENT_TIMESTAMP TIMESTAMP <TIMESTAMP>*/," + // 13
							"	?/*OFERTA <VARCHAR(255)>*/," + // 14
							"	?/*CENTRO <VARCHAR(4)>*/," + // 15
							"	''/*ULTIMA_GUIA <TIMESTAMP>*/," + "	''/*ULTIMO_CIERRE <TIMESTAMP>*/," + "   ?," + // 16
							"   ?," + // 17
							"   ?, " + // 18
							"	'', /*CENTRO_DEST*/" + "	false /*STOCK_CONSIGNADO*/" + ");");

			user.setClave(user.getClave(), false);

			pstmt.setString(1, user.getRut());
			pstmt.setString(2, user.getClave());
			pstmt.setString(3, new String(user.getNombre().getBytes(), "ISO-8859-1"));
			pstmt.setString(4, user.getTelefono());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getTopeDeuda());
			// pstmt.setString(7,user.token);
			// pstmt.setString(8,user.imei);
			pstmt.setString(7, user.getTipo());
			pstmt.setString(8, user.getRol());
			// pstmt.setString(11,user.numero_camion);
			pstmt.setString(9, user.getTerminal());
			// pstmt.setString(13,user.idpush);
			pstmt.setString(10, user.getLista_pedido());
			pstmt.setString(11, user.getBloqueado());
			pstmt.setString(12, user.getUsadescuentos());
			pstmt.setString(13, Tiempo.getNTPDateCL(parametroCambioHorario));
			pstmt.setString(14, user.getOferta());
			pstmt.setString(15, user.getCentro());
			pstmt.setString(16, user.getDeudorsap());
			pstmt.setString(17, user.getAcreedorsap());
			pstmt.setString(18, user.getTransbank());

			int resultado = pstmt.executeUpdate();

			if (resultado == 1) {
				iresponse.setMensaje("Realizado.");
				iresponse.setResultado(true);
			} else {
				iresponse.setMensaje("No realizado.");
				iresponse.setResultado(false);
			}
			objectToReturn = iresponse.returnJson();
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, user, objectToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			iresponse.setMensaje("No realizado.");
			iresponse.setResultado(false);
			objectToReturn = iresponse.returnJson();
			return objectToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return objectToReturn;
	}

	// DES002PITS
	public String findWerkUser(String terminalW) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objToReturn = "";
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"TERMINAL\"='" + terminalW + "';");
			ResultSet rs = pstmt.executeQuery();

			ArrayList<Usuario> list = new ArrayList<Usuario>();

			rs.next();
			objToReturn = rs.getString("CENTRO");

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, terminalW, objToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			objToReturn = "";
			return objToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return objToReturn;
	}

	/**
	 * Update a UserEntity to the table.
	 *
	 * @throws NoSuchAlgorithmException
	 */
	public String updateUser(Usuario user) throws SQLException, NoSuchAlgorithmException {
		Connection connection = dataSource.getConnection();
		String objectToReturn = "";
		if (user.getClave().length() > 0)
			user.setClave(user.getClave(), false);

		Usuario userAuthPass = selectUserById(new Gson().toJson(user, user.getClass()));
		if (user.getClave().length() == 0) {
			user.setClave(userAuthPass.getClave(), true);
		} else {
			boolean resultAuth = userAuthPass.newAuthClave(user.getClave());
			if (resultAuth) {
				user.setClave(userAuthPass.getClave(), true);
			}
		}

		try {
			PreparedStatement pstmt = connection
					.prepareStatement("UPDATE \"" + Scheme.now + "\".\"USUARIO\" " + " SET \"RUT\" = ?, " + // 1
							"	\"NOMBRE\" = ?, " + // 2
							"	\"TELEFONO\" = ?, " + // 3
							"	\"EMAIL\" = ?, " + // 4
							"	\"TOPEDEUDA\" = ?, " + // 5
							// " \"TOKEN\" =
							// 'eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1MzE5MTk5MDYsInN1YiI6IntcInJ1dFwiOlwiMVwiLFwiZW1haWxcIjpcIkVSVUlaQFNUQVJDT05TVUxUSU5HLkNMXCIsXCJub21icmVcIjpcIkVEVUFSRE8gUlVJWlwiLFwidG9wZURldWRhXCI6XCIxLjAwMC4wMDBcIixcImxpc3RhX3BlZGlkb1wiOlwiMFwiLFwiYmxvcXVlYWRvXCI6XCIwXCIsXCJ0aXBvXCI6XCIwMDFcIixcInRlcm1pbmFsXCI6XCI5NjAwXCIsXCJ1c2FkZXNjdWVudG9zXCI6XCIwXCIsXCJyb2xcIjpcIlwifSJ9.7bUJ86wsjyyLmsOX9F7RrIN0rWVYJNta0Nul77sqs8FQW4sv1CGani_-mbistljf7j4Ci7is846v1680evRanw',
							// " +
							// " \"IMEI\" = ?, " +
							"	\"TIPO\" = ?, " + // 6
							"	\"ROL\" = ?, " + // 7
							// " \"NUMERO_CAMION\" = 'as', " +
							"	\"TERMINAL\" = ?, " + // 8
							// " \"IDPUSH\" =
							// 'dFZ-4VCNHsA:APA91bHbKvafV80-ZGEff11oVz5S66uUio460IZ14-31PrHhMJDu2LA6ANuvjlgTs2oVKNa7jsL1Wt-mYWn4tA0MDCpxKHM6YlLb7K56Abz_EJrWt51zMxGKkumxBEklIjCnwYbdZAWr_SCSPUh8sYNgBghVafm_oA',
							// " +
							"	\"LISTA_PEDIDO\" = ?, " + // 9
							"	\"BLOQUEADO\" = ?, " + // 10
							"	\"USADESCUENTOS\" = ?, " + // 11
							"	\"TIMESTAMP\" = ?, " + // 12CURRENT_TIMESTAMP
							"	\"OFERTA\" = ?," + // 13
							"	\"CLAVE\" = ?, " + // 14
							"	\"TRANSBANK\" = ?," + // 15
							"	\"CENTRO_DEST\" = ?," + // 16
							"	\"STOCK_CONSIGNADO\" = ?" + // 17
							// " \"CENTRO\" = '5100', " +
							// " \"ULTIMA_GUIA\" = CURRENT_TIMESTAMP, " +
							// " \"ULTIMO_CIERRE\" = CURRENT_TIMESTAMP" +
							" WHERE \"ID\" = ?;" + // 18
							"");

			// user.setClave(user.clave);

			pstmt.setString(1, user.getRut());
			pstmt.setString(2, new String(user.getNombre().getBytes(), "ISO-8859-1"));
			pstmt.setString(3, user.getTelefono());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getTopeDeuda());
			pstmt.setString(6, user.getTipo());
			pstmt.setString(7, user.getRol());
			pstmt.setString(8, user.getTerminal());
			pstmt.setString(9, user.getListaPedidos());
			pstmt.setString(10, user.getBloqueado());
			pstmt.setString(11, user.getUsadescuento());
			pstmt.setString(12, Tiempo.getNTPDateCL(parametroCambioHorario));
			pstmt.setString(13, user.getOferta());
			pstmt.setString(14, user.getClave());
			pstmt.setString(15, user.getTransbank());
			pstmt.setString(16, user.getCentroDest());
			pstmt.setBoolean(17, user.getStock_consignado());
			pstmt.setString(18, user.getId());

			int resultado = pstmt.executeUpdate();

			if (resultado == 1) {
				iresponse.setMensaje("Realizado.");
				iresponse.setResultado(true);
			} else {
				iresponse.setMensaje("No realizado.");
				iresponse.setResultado(false);
			}
			objectToReturn = iresponse.returnJson();
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, user, objectToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			iresponse.setMensaje("No realizado.");
			iresponse.setResultado(false);
			objectToReturn = iresponse.returnJson();
			return objectToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return objectToReturn;
	}

	public String deleteUser(Usuario user) throws SQLException, NoSuchAlgorithmException {
		Connection connection = dataSource.getConnection();
		String objectToReturn = "";
		try {
			PreparedStatement pstmt = connection
					.prepareStatement("DELETE FROM \"" + Scheme.now + "\".\"USUARIO\" " + " WHERE \"ID\" = ?;" + // 14
							"");

			// user.setClave(user.clave);

			pstmt.setString(1, user.getId());

			int resultado = pstmt.executeUpdate();

			if (resultado == 1) {
				iresponse.setMensaje("Realizado.");
				iresponse.setResultado(true);
			} else {
				iresponse.setMensaje("No realizado.");
				iresponse.setResultado(false);
			}
			objectToReturn = iresponse.returnJson();
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, user, objectToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			iresponse.setMensaje("No realizado.");
			iresponse.setResultado(false);
			objectToReturn = iresponse.returnJson();
			return objectToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return objectToReturn;
	}

	/**
	 * Update a UserEntity to the table.
	 */
	public void setUserSession(Usuario user) throws SQLException {
		Connection connection = dataSource.getConnection();
		freeUserSession(user);
		try {
			PreparedStatement pstmt = connection.prepareStatement("UPDATE \"" + Scheme.now + "\".\"USUARIO\" SET"
					+ "	\"TOKEN\" = ?," + "	\"IMEI\" = ?," + "	\"TERMINAL\" = ?," + "	\"NUMERO_CAMION\" = ?,"
					+ "	\"IDPUSH\" = ?," + "	\"TRANSBANK\" = '' " + "	WHERE \"RUT\" = ?;");

			pstmt.setString(1, user.getToken());
			pstmt.setString(2, user.getImei());
			pstmt.setString(3, user.getTerminal());
			pstmt.setString(4, user.getNcamion());
			pstmt.setString(5, user.getIdpush());
			pstmt.setString(6, user.getRut());
			pstmt.executeUpdate();

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, user });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public void freeUserSession(Usuario user) throws SQLException {
		Connection connection = dataSource.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement("UPDATE \"" + Scheme.now + "\".\"USUARIO\" SET"
					+ "	\"TOKEN\" = ''," + "	\"IMEI\" = ''," + "	\"NUMERO_CAMION\" = ''," + "	\"IDPUSH\" = '',"
					+ "	\"TRANSBANK\" = ''" + "	WHERE \"RUT\" = ?;");

			pstmt.setString(1, user.getRut());
			pstmt.executeUpdate();

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, user });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public String updateUltimaGuia(Usuario user) throws SQLException {
		Connection connection = dataSource.getConnection();
		String rtrnStr = "{\"resultado\":false}";
		try {
			PreparedStatement pstmt = connection.prepareStatement("UPDATE \"" + Scheme.now + "\".\"USUARIO\" SET"
					+ "	\"ULTIMA_GUIA\" = ?" + "	WHERE \"RUT\" = ?;");

			pstmt.setString(1, user.getUltima_guia());
			pstmt.setString(2, user.getRut());
			if (1 == pstmt.executeUpdate()) {
				rtrnStr = "{\"resultado\":true}";
			} else {
				rtrnStr = "{\"resultado\":false}";
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, user, rtrnStr });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			rtrnStr = "{\"resultado\":false}";
			return rtrnStr;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return rtrnStr;
	}

	public String updateUltimoCierre(Usuario user) throws SQLException {
		Connection connection = dataSource.getConnection();
		String rtrnStr = "{\"resultado\":false}";
		try {
			PreparedStatement pstmt = connection.prepareStatement("UPDATE \"" + Scheme.now + "\".\"USUARIO\" SET"
					+ "	\"ULTIMO_CIERRE\" = ?" + "	WHERE \"RUT\" = ?;");

			pstmt.setString(1, user.getUltimo_cierre());
			pstmt.setString(2, user.getRut());

			int resultado = pstmt.executeUpdate();

			if (resultado == 1) {
				iresponse.setMensaje("Realizado.");
				iresponse.setResultado(true);
			} else {
				iresponse.setMensaje("No realizado.");
				iresponse.setResultado(false);
			}
			rtrnStr = iresponse.returnJson();
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, user, rtrnStr });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			iresponse.setMensaje("No realizado.");
			iresponse.setResultado(false);
			if (connection != null) {
				connection.close();
			}
			rtrnStr = iresponse.returnJson();
			return rtrnStr;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return rtrnStr;
	}

	public boolean updateTransbank(Usuario user) throws SQLException {
		Connection connection = dataSource.getConnection();
		boolean rtrnStr = false;
		try {
			PreparedStatement pstmt = connection.prepareStatement(
					"UPDATE \"" + Scheme.now + "\".\"USUARIO\" SET" + "	\"TRANSBANK\" = ?" + "	WHERE \"RUT\" = ?;");

			pstmt.setString(1, user.getTransbank());
			pstmt.setString(2, user.getRut());

			int resultado = pstmt.executeUpdate();

			if (1 == resultado) {
				rtrnStr = true;
				List<String> values = new ArrayList<String>();
				// LogTransbankDAO ltTransbankDAO = new LogTransbankDAO(
				// this.getDataSource()
				// );
				// values.add(user.getRut());
				// values.add(this.findUserByRut4Terminal(user.getRut()));
				// values.add(user.getTransbank());
				// values.add(Tiempo.getNTPDateCL(parametroCambioHorario));
				// ltTransbankDAO.create(values);
			} else {
				rtrnStr = false;
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, user, rtrnStr });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			rtrnStr = false;
			return rtrnStr;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return rtrnStr;
	}

	/**
	 * Get all UserEntitys from the table.
	 */
	public List<Usuario> selectAllUsers() throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now + "\".\"USUARIO\";");

			ResultSet rs = pstmt.executeQuery();

			List<Usuario> list = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getString("ID"));
				user.setRut(rs.getString("RUT"));
				user.setClave(rs.getString("CLAVE"), true);
				user.setNombre(rs.getString("NOMBRE"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTopeDeuda(rs.getString("TOPEDEUDA"));
				user.setToken(rs.getString("TOKEN"));
				user.setImei(rs.getString("IMEI"));
				user.setTipo(rs.getString("TIPO"));
				user.setRol(rs.getString("ROL"));
				user.setNumero_camion(rs.getString("NUMERO_CAMION"));
				user.setTerminal(rs.getString("TERMINAL"));
				user.setIdpush(rs.getString("IDPUSH"));
				user.setLista_pedido(rs.getString("LISTA_PEDIDO"));
				user.setBloqueado(rs.getString("BLOQUEADO"));
				user.setUsadescuentos(rs.getString("USADESCUENTOS"));
				user.setCentro(rs.getString("CENTRO"));
				user.setOferta(rs.getString("OFERTA"));
				user.setTimestamp(rs.getString("TIMESTAMP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setUltimo_cierre(rs.getString("ULTIMO_CIERRE"));
				user.setDeudorsap(rs.getString("DEUDORSAP"));
				user.setAcreedorsap(rs.getString("ACREEDORSAP"));
				user.setTransbank(rs.getString("TRANSBANK"));
				list.add(user);
			}

			if (connection != null) {
				connection.close();
			}
			return list;
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			ArrayList<Usuario> list = new ArrayList<Usuario>();
			return list;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public List<Usuario> selectAllUsersByCenter(String json) throws SQLException {
		Connection connection = dataSource.getConnection();
		Usuario oParam = gson.fromJson(json, Usuario.class);
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"CENTRO\" = ? AND \"TERMINAL\" != '';");

			pstmt.setString(1, oParam.getCentro());

			ResultSet rs = pstmt.executeQuery();

			List<Usuario> list = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getString("ID"));
				user.setRut(rs.getString("RUT"));
				user.setClave(rs.getString("CLAVE"), true);
				user.setNombre(rs.getString("NOMBRE"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTopeDeuda(rs.getString("TOPEDEUDA"));
				user.setToken(rs.getString("TOKEN"));
				user.setImei(rs.getString("IMEI"));
				user.setTipo(rs.getString("TIPO"));
				user.setRol(rs.getString("ROL"));
				user.setNumero_camion(rs.getString("NUMERO_CAMION"));
				user.setTerminal(rs.getString("TERMINAL"));
				user.setIdpush(rs.getString("IDPUSH"));
				user.setLista_pedido(rs.getString("LISTA_PEDIDO"));
				user.setBloqueado(rs.getString("BLOQUEADO"));
				user.setUsadescuentos(rs.getString("USADESCUENTOS"));
				user.setCentro(rs.getString("CENTRO"));
				user.setOferta(rs.getString("OFERTA"));
				user.setTimestamp(rs.getString("TIMESTAMP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setUltimo_cierre(rs.getString("ULTIMO_CIERRE"));
				user.setDeudorsap(rs.getString("DEUDORSAP"));
				user.setAcreedorsap(rs.getString("ACREEDORSAP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setTransbank(rs.getString("TRANSBANK"));
				user.setStock_consignado(rs.getBoolean("STOCK_CONSIGNADO"));
				list.add(user);
			}

			if (connection != null) {
				connection.close();
			}
			return list;
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, json, oParam });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			ArrayList<Usuario> list = new ArrayList<Usuario>();
			return list;
		}
	}

	public List<Usuario> selectAllUsersByCenterAutorizados(String json) throws SQLException {
		Connection connection = dataSource.getConnection();
		Usuario oParam = gson.fromJson(json, Usuario.class);
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now
					+ "\".\"USUARIO\" WHERE \"CENTRO\" = ? AND \"TERMINAL\" != '' AND \"CENTRO_DEST\" IS NOT NULL;");

			pstmt.setString(1, oParam.getCentro());

			ResultSet rs = pstmt.executeQuery();

			List<Usuario> list = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getString("ID"));
				user.setRut(rs.getString("RUT"));
				user.setClave(rs.getString("CLAVE"), true);
				user.setNombre(rs.getString("NOMBRE"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTopeDeuda(rs.getString("TOPEDEUDA"));
				user.setToken(rs.getString("TOKEN"));
				user.setImei(rs.getString("IMEI"));
				user.setTipo(rs.getString("TIPO"));
				user.setRol(rs.getString("ROL"));
				user.setNumero_camion(rs.getString("NUMERO_CAMION"));
				user.setTerminal(rs.getString("TERMINAL"));
				user.setIdpush(rs.getString("IDPUSH"));
				user.setLista_pedido(rs.getString("LISTA_PEDIDO"));
				user.setBloqueado(rs.getString("BLOQUEADO"));
				user.setUsadescuentos(rs.getString("USADESCUENTOS"));
				user.setCentro(rs.getString("CENTRO"));
				user.setCentroDest(rs.getString("CENTRO_DEST"));
				user.setOferta(rs.getString("OFERTA"));
				user.setTimestamp(rs.getString("TIMESTAMP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setUltimo_cierre(rs.getString("ULTIMO_CIERRE"));
				user.setDeudorsap(rs.getString("DEUDORSAP"));
				user.setAcreedorsap(rs.getString("ACREEDORSAP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setTransbank(rs.getString("TRANSBANK"));
				list.add(user);
			}

			if (connection != null) {
				connection.close();
			}
			return list;
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, json, oParam });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			ArrayList<Usuario> list = new ArrayList<Usuario>();
			return list;
		}
	}

	public List<Usuario> selectAllUsersByCenterDest(String json) throws SQLException {
		Connection connection = dataSource.getConnection();
		Usuario oParam = gson.fromJson(json, Usuario.class);
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"CENTRO_DEST\" = ? AND TERMINAL != '';");

			pstmt.setString(1, oParam.getCentro());

			ResultSet rs = pstmt.executeQuery();

			List<Usuario> list = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getString("ID"));
				user.setRut(rs.getString("RUT"));
				user.setClave(rs.getString("CLAVE"), true);
				user.setNombre(rs.getString("NOMBRE"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTopeDeuda(rs.getString("TOPEDEUDA"));
				user.setToken(rs.getString("TOKEN"));
				user.setImei(rs.getString("IMEI"));
				user.setTipo(rs.getString("TIPO"));
				user.setRol(rs.getString("ROL"));
				user.setNumero_camion(rs.getString("NUMERO_CAMION"));
				user.setTerminal(rs.getString("TERMINAL"));
				user.setIdpush(rs.getString("IDPUSH"));
				user.setLista_pedido(rs.getString("LISTA_PEDIDO"));
				user.setBloqueado(rs.getString("BLOQUEADO"));
				user.setUsadescuentos(rs.getString("USADESCUENTOS"));
				user.setCentro(rs.getString("CENTRO"));
				user.setCentroDest(rs.getString("CENTRO_DEST"));
				user.setOferta(rs.getString("OFERTA"));
				user.setTimestamp(rs.getString("TIMESTAMP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setUltimo_cierre(rs.getString("ULTIMO_CIERRE"));
				user.setDeudorsap(rs.getString("DEUDORSAP"));
				user.setAcreedorsap(rs.getString("ACREEDORSAP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setTransbank(rs.getString("TRANSBANK"));
				user.setStock_consignado(rs.getBoolean("STOCK_CONSIGNADO"));
				list.add(user);
			}

			if (connection != null) {
				connection.close();
			}
			return list;
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, json, oParam });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			ArrayList<Usuario> list = new ArrayList<Usuario>();
			return list;
		}
	}

	public List<Usuario> selectAllUsersByCenterWithoutTerminal(String json) throws SQLException {
		Connection connection = dataSource.getConnection();
		Usuario oParam = gson.fromJson(json, Usuario.class);
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"CENTRO\" = ? AND \"TERMINAL\" = '';");

			pstmt.setString(1, oParam.getCentro());

			ResultSet rs = pstmt.executeQuery();

			List<Usuario> list = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getString("ID"));
				user.setRut(rs.getString("RUT"));
				user.setClave(rs.getString("CLAVE"), true);
				user.setNombre(rs.getString("NOMBRE"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTopeDeuda(rs.getString("TOPEDEUDA"));
				user.setToken(rs.getString("TOKEN"));
				user.setImei(rs.getString("IMEI"));
				user.setTipo(rs.getString("TIPO"));
				user.setRol(rs.getString("ROL"));
				user.setNumero_camion(rs.getString("NUMERO_CAMION"));
				user.setTerminal(rs.getString("TERMINAL"));
				user.setIdpush(rs.getString("IDPUSH"));
				user.setLista_pedido(rs.getString("LISTA_PEDIDO"));
				user.setBloqueado(rs.getString("BLOQUEADO"));
				user.setUsadescuentos(rs.getString("USADESCUENTOS"));
				user.setCentro(rs.getString("CENTRO"));
				user.setOferta(rs.getString("OFERTA"));
				user.setTimestamp(rs.getString("TIMESTAMP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setUltimo_cierre(rs.getString("ULTIMO_CIERRE"));
				user.setDeudorsap(rs.getString("DEUDORSAP"));
				user.setAcreedorsap(rs.getString("ACREEDORSAP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setTransbank(rs.getString("TRANSBANK"));
				list.add(user);
			}

			if (connection != null) {
				connection.close();
			}
			return list;
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, json, oParam });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			ArrayList<Usuario> list = new ArrayList<Usuario>();
			return list;
		}
	}

	public List<Usuario> selectAllUsersByCenterWithoutTerminalDS(String json) throws SQLException {
		Connection connection = dataSource.getConnection();
		Usuario oParam = gson.fromJson(json, Usuario.class);
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now
					+ "\".\"USUARIO\" WHERE \"ACREEDORSAP\"=? AND \"DEUDORSAP\"=? AND \"TERMINAL\" = '';");

			pstmt.setString(1, oParam.getAcreedorsap());
			pstmt.setString(2, oParam.getDeudorsap());

			ResultSet rs = pstmt.executeQuery();

			List<Usuario> list = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getString("ID"));
				user.setRut(rs.getString("RUT"));
				user.setClave(rs.getString("CLAVE"), true);
				user.setNombre(rs.getString("NOMBRE"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTopeDeuda(rs.getString("TOPEDEUDA"));
				user.setToken(rs.getString("TOKEN"));
				user.setImei(rs.getString("IMEI"));
				user.setTipo(rs.getString("TIPO"));
				user.setRol(rs.getString("ROL"));
				user.setNumero_camion(rs.getString("NUMERO_CAMION"));
				user.setTerminal(rs.getString("TERMINAL"));
				user.setIdpush(rs.getString("IDPUSH"));
				user.setLista_pedido(rs.getString("LISTA_PEDIDO"));
				user.setBloqueado(rs.getString("BLOQUEADO"));
				user.setUsadescuentos(rs.getString("USADESCUENTOS"));
				user.setCentro(rs.getString("CENTRO"));
				user.setOferta(rs.getString("OFERTA"));
				user.setTimestamp(rs.getString("TIMESTAMP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setUltimo_cierre(rs.getString("ULTIMO_CIERRE"));
				user.setDeudorsap(rs.getString("DEUDORSAP"));
				user.setAcreedorsap(rs.getString("ACREEDORSAP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setTransbank(rs.getString("TRANSBANK"));
				list.add(user);
			}

			if (connection != null) {
				connection.close();
			}
			return list;
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, json, oParam });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			ArrayList<Usuario> list = new ArrayList<Usuario>();
			return list;
		}
	}

	public List<Usuario> selectAllUsersByDS(String json) throws SQLException {
		Connection connection = dataSource.getConnection();
		Usuario oParam = gson.fromJson(json, Usuario.class);
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now
					+ "\".\"USUARIO\" WHERE \"ACREEDORSAP\"=? AND \"DEUDORSAP\"=? AND \"TERMINAL\" !='';");

			pstmt.setString(1, oParam.getAcreedorsap());
			pstmt.setString(2, oParam.getDeudorsap());

			ResultSet rs = pstmt.executeQuery();

			List<Usuario> list = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getString("ID"));
				user.setRut(rs.getString("RUT"));
				user.setClave(rs.getString("CLAVE"), true);
				user.setNombre(rs.getString("NOMBRE"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTopeDeuda(rs.getString("TOPEDEUDA"));
				user.setToken(rs.getString("TOKEN"));
				user.setImei(rs.getString("IMEI"));
				user.setTipo(rs.getString("TIPO"));
				user.setRol(rs.getString("ROL"));
				user.setNumero_camion(rs.getString("NUMERO_CAMION"));
				user.setTerminal(rs.getString("TERMINAL"));
				user.setIdpush(rs.getString("IDPUSH"));
				user.setLista_pedido(rs.getString("LISTA_PEDIDO"));
				user.setBloqueado(rs.getString("BLOQUEADO"));
				user.setUsadescuentos(rs.getString("USADESCUENTOS"));
				user.setCentro(rs.getString("CENTRO"));
				user.setOferta(rs.getString("OFERTA"));
				user.setTimestamp(rs.getString("TIMESTAMP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setUltimo_cierre(rs.getString("ULTIMO_CIERRE"));
				user.setDeudorsap(rs.getString("DEUDORSAP"));
				user.setAcreedorsap(rs.getString("ACREEDORSAP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setTransbank(rs.getString("TRANSBANK"));
				list.add(user);
			}

			if (connection != null) {
				connection.close();
			}

			return list;
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, json, oParam });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			ArrayList<Usuario> list = new ArrayList<Usuario>();
			return list;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public List<Usuario> selectAllUsersIdPush() throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					"	\"IDPUSH\"" + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"IDPUSH\" IS NOT NULL;");

			ResultSet rs = pstmt.executeQuery();

			List<Usuario> list = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario user = new Usuario();
				user.setIdpush(rs.getString("IDPUSH"));
				list.add(user);
			}

			if (connection != null) {
				connection.close();
			}

			return list;
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			ArrayList<Usuario> list = new ArrayList<Usuario>();
			return list;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * Get all UserEntitys from the table.
	 */
	public List<Usuario> selectUser(String rut) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"RUT\"='" + rut + "';");
			ResultSet rs = pstmt.executeQuery();

			List<Usuario> list = new ArrayList<Usuario>();
			while (rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getString("ID"));
				user.setRut(rs.getString("RUT"));
				user.setClave(rs.getString("CLAVE"), true);
				user.setNombre(rs.getString("NOMBRE"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTopeDeuda(rs.getString("TOPEDEUDA"));
				user.setToken(rs.getString("TOKEN"));
				user.setImei(rs.getString("IMEI"));
				user.setTipo(rs.getString("TIPO"));
				user.setRol(rs.getString("ROL"));
				user.setNumero_camion(rs.getString("NUMERO_CAMION"));
				user.setTerminal(rs.getString("TERMINAL"));
				user.setIdpush(rs.getString("IDPUSH"));
				user.setLista_pedido(rs.getString("LISTA_PEDIDO"));
				user.setBloqueado(rs.getString("BLOQUEADO"));
				user.setUsadescuentos(rs.getString("USADESCUENTOS"));
				user.setCentro(rs.getString("CENTRO"));
				user.setOferta(rs.getString("OFERTA"));
				user.setTimestamp(rs.getString("TIMESTAMP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setUltimo_cierre(rs.getString("ULTIMO_CIERRE"));
				user.setDeudorsap(rs.getString("DEUDORSAP"));
				user.setAcreedorsap(rs.getString("ACREEDORSAP"));
				user.setTransbank(rs.getString("TRANSBANK"));
				list.add(user);
			}

			if (connection != null) {
				connection.close();
			}
			return list;
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, rut });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			List<Usuario> list = new ArrayList<Usuario>();
			return list;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public Usuario selectUserById(String json) throws SQLException {
		Connection connection = dataSource.getConnection();
		Usuario oParam = new Gson().fromJson(json, Usuario.class);
		Usuario user = new Usuario();
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"ID\"=" + oParam.getId() + ";");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new Usuario();
				user.setId(rs.getString("ID"));
				user.setRut(rs.getString("RUT"));
				user.setClave(rs.getString("CLAVE"), true);
				user.setNombre(rs.getString("NOMBRE"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTopeDeuda(rs.getString("TOPEDEUDA"));
				user.setToken(rs.getString("TOKEN"));
				user.setImei(rs.getString("IMEI"));
				user.setTipo(rs.getString("TIPO"));
				user.setRol(rs.getString("ROL"));
				user.setNumero_camion(rs.getString("NUMERO_CAMION"));
				user.setTerminal(rs.getString("TERMINAL"));
				user.setIdpush(rs.getString("IDPUSH"));
				user.setLista_pedido(rs.getString("LISTA_PEDIDO"));
				user.setBloqueado(rs.getString("BLOQUEADO"));
				user.setUsadescuentos(rs.getString("USADESCUENTOS"));
				user.setCentro(rs.getString("CENTRO"));
				user.setOferta(rs.getString("OFERTA"));
				user.setTimestamp(rs.getString("TIMESTAMP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setUltimo_cierre(rs.getString("ULTIMO_CIERRE"));
				user.setDeudorsap(rs.getString("DEUDORSAP"));
				user.setAcreedorsap(rs.getString("ACREEDORSAP"));
				user.setTransbank(rs.getString("TRANSBANK"));
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, json, user, oParam });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			return user;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return user;
	}

	// Added select user by terminal

	public Usuario selectUserByTerminal(String json) throws SQLException {
		Connection connection = dataSource.getConnection();
		Usuario oParam = new Gson().fromJson(json, Usuario.class);
		Usuario user = new Usuario();
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"TERMINAL\"= '" + oParam.getTerminal()
					+ "';");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new Usuario();
				user.setId(rs.getString("ID"));
				user.setRut(rs.getString("RUT"));
				user.setClave(rs.getString("CLAVE"), true);
				user.setNombre(rs.getString("NOMBRE"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTopeDeuda(rs.getString("TOPEDEUDA"));
				user.setToken(rs.getString("TOKEN"));
				user.setImei(rs.getString("IMEI"));
				user.setTipo(rs.getString("TIPO"));
				user.setRol(rs.getString("ROL"));
				user.setNumero_camion(rs.getString("NUMERO_CAMION"));
				user.setTerminal(rs.getString("TERMINAL"));
				user.setIdpush(rs.getString("IDPUSH"));
				user.setLista_pedido(rs.getString("LISTA_PEDIDO"));
				user.setBloqueado(rs.getString("BLOQUEADO"));
				user.setUsadescuentos(rs.getString("USADESCUENTOS"));
				user.setCentro(rs.getString("CENTRO"));
				user.setCentroDest(rs.getString("CENTRO_DEST"));
				user.setOferta(rs.getString("OFERTA"));
				user.setTimestamp(rs.getString("TIMESTAMP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setUltimo_cierre(rs.getString("ULTIMO_CIERRE"));
				user.setDeudorsap(rs.getString("DEUDORSAP"));
				user.setAcreedorsap(rs.getString("ACREEDORSAP"));
				user.setTransbank(rs.getString("TRANSBANK"));
				user.setStock_consignado(rs.getBoolean("STOCK_CONSIGNADO"));
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, json, user, oParam });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			return user;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return user;
	}

	public Usuario selectUserByRut(String json) throws SQLException {
		Connection connection = dataSource.getConnection();
		Usuario oParam = new Gson().fromJson(json, Usuario.class);
		String RUT = oParam.getRut();
		Usuario user = new Usuario();
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"RUT\"='" + RUT + "';");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new Usuario();
				user.setId(rs.getString("ID"));
				user.setRut(rs.getString("RUT"));
				user.setClave("", true);
				user.setNombre(rs.getString("NOMBRE"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTopeDeuda(rs.getString("TOPEDEUDA"));
				user.setToken(rs.getString("TOKEN"));
				user.setImei(rs.getString("IMEI"));
				user.setTipo(rs.getString("TIPO"));
				user.setRol(rs.getString("ROL"));
				user.setNumero_camion(rs.getString("NUMERO_CAMION"));
				user.setTerminal(rs.getString("TERMINAL"));
				user.setIdpush(rs.getString("IDPUSH"));
				user.setLista_pedido(rs.getString("LISTA_PEDIDO"));
				user.setBloqueado(rs.getString("BLOQUEADO"));
				user.setUsadescuentos(rs.getString("USADESCUENTOS"));
				user.setCentro(rs.getString("CENTRO"));
				user.setOferta(rs.getString("OFERTA"));
				user.setTimestamp(rs.getString("TIMESTAMP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setUltimo_cierre(rs.getString("ULTIMO_CIERRE"));
				user.setDeudorsap(rs.getString("DEUDORSAP"));
				user.setAcreedorsap(rs.getString("ACREEDORSAP"));
				user.setTransbank(rs.getString("TRANSBANK"));
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, json, oParam, RUT, user });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			return user;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return user;
	}

	public Usuario selectUserByRutDirectValue(String json) throws SQLException {
		Connection connection = dataSource.getConnection();
		String RUT = json;
		Usuario user = new Usuario();
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"RUT\"='" + RUT + "';");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new Usuario();
				user.setId(rs.getString("ID"));
				user.setRut(rs.getString("RUT"));
				user.setClave("", true);
				user.setNombre(rs.getString("NOMBRE"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTopeDeuda(rs.getString("TOPEDEUDA"));
				user.setToken(rs.getString("TOKEN"));
				user.setImei(rs.getString("IMEI"));
				user.setTipo(rs.getString("TIPO"));
				user.setRol(rs.getString("ROL"));
				user.setNumero_camion(rs.getString("NUMERO_CAMION"));
				user.setTerminal(rs.getString("TERMINAL"));
				user.setIdpush(rs.getString("IDPUSH"));
				user.setLista_pedido(rs.getString("LISTA_PEDIDO"));
				user.setBloqueado(rs.getString("BLOQUEADO"));
				user.setUsadescuentos(rs.getString("USADESCUENTOS"));
				user.setCentro(rs.getString("CENTRO"));
				user.setOferta(rs.getString("OFERTA"));
				user.setTimestamp(rs.getString("TIMESTAMP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setUltimo_cierre(rs.getString("ULTIMO_CIERRE"));
				user.setDeudorsap(rs.getString("DEUDORSAP"));
				user.setAcreedorsap(rs.getString("ACREEDORSAP"));
				user.setTransbank(rs.getString("TRANSBANK"));
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, json, RUT, user });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			return user;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return user;
	}

	public boolean isTransbankOccupied(Usuario oParam) throws SQLException {
		Connection connection = dataSource.getConnection();
		boolean result = false;
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + " * " + " FROM \"" + Scheme.now
					+ "\".\"USUARIO\" WHERE \"TRANSBANK\"='" + oParam.getTransbank() + "';");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				result = true;
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, oParam, result });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			result = false;
			return result;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	public boolean isTransbankOccupiedByRut(Usuario oParam) throws SQLException {
		Connection connection = dataSource.getConnection();
		boolean result = false;
		try {
			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT " + " * " + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"TRANSBANK\"='"
							+ oParam.getTransbank() + "' AND \"RUT\"='" + oParam.getRut() + "';");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				result = true;
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, oParam, result });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			result = false;
			return result;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	public boolean existUserByRut(String json) throws SQLException {
		Connection connection = dataSource.getConnection();
		Usuario oParam = new Gson().fromJson(json, Usuario.class);

		String RUT = oParam.getRut();
		boolean result = false;
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"RUT\"='" + RUT + "';");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				result = true;
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, json, oParam });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			result = false;
			return result;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	public boolean existUserByTerminal(String json) throws SQLException {
		Connection connection = dataSource.getConnection();
		Usuario oParam = new Gson().fromJson(json, Usuario.class);
		boolean result = false;
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					" * " + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"TERMINAL\"='" + oParam.getTerminal()
					+ "';");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				result = true;
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, oParam, result });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			result = false;
			return result;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	/**
	 * Check if the UserEntity table already exists and create it if not.
	 */
	private void checkTable() throws SQLException {
		Connection connection = null;

		try {
			connection = dataSource.getConnection();
			if (!existsTable(connection)) {
				// createTable(connection);
			}
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * Check if the UserEntity table already exists.
	 */
	private boolean existsTable(Connection conn) throws SQLException {
		// DatabaseMetaData meta = conn.getMetaData();
		// ResultSet rs = meta.getTables("PITS", null, "USUARIO", null);
		// while (rs.next()) {
		// String name = rs.getString("TABLE_NAME");
		// if (name.equals("USUARIO")) {
		// return true;
		// }
		// }
		// return false;
		return true;
	}

	/**
	 * Create the UserEntity table.
	 */
	private void createTable(Connection connection) throws SQLException {
		// PreparedStatement pstmt = connection
		// .prepareStatement("");
		// pstmt.executeUpdate();
	}

	@SuppressWarnings({ "resource" })
	public String checkLogin3(String body, int parametroIntentoNotificacion, int parametroTiempoNotificacion,
			HttpServletResponse response,
			// StockActualDAO stockActualDAO,
			S4CommMobile s4) throws IOException, SQLException, NoSuchAlgorithmException {
		Usuario userAsking = gson.fromJson(body, Usuario.class);
		List<Usuario> resultList = selectUser(userAsking.getRut());
		String resultado = "";
		userAsking.setClave(userAsking.getClave(), true);

		boolean checkCanalBool = true;
		String mensajeFinal = "";

		for (Usuario u : resultList) {
			if ((u.getRut().equals(userAsking.getRut())) && (u.authClave(userAsking.getClave()))
					&& (u.checkCanalVenta(u.getTipo(), userAsking.getNumero_camion()))) {
				Date date = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.DATE, (365 * 5));
				date = c.getTime();
				long t = date.getTime();
				long epoch = date.getTime();
				freeUserSession(u);
				u.setImei(userAsking.getImei());
				u.setNcamion(userAsking.getNcamion());
				u.setIdpush(userAsking.getIdpush());
				StockActual userStockRead = new StockActual();
				List<String> values = new ArrayList<String>();
				values.add(u.getRut());
				// userStockRead = stockActualDAO.readByRTwithOldStock(values);
				Type listType = new TypeToken<ArrayList<ProductSLL>>() {
				}.getType();
				List<ProductSLL> productos = new ArrayList<ProductSLL>();
				if (userStockRead.getEntregar() == null
						|| (!userStockRead.getEntregar().equals("0") && !userStockRead.getEntregar().equals("1"))) {
					userStockRead.setEntregar("1");
					productos = new Gson().fromJson("[]", listType);
				} else {
					productos = new Gson().fromJson(userStockRead.getStock_detalle(), listType);
				}
				String strPayload = "";
				// LogGuiaDAO logGuiaDAO = new LogGuiaDAO(this.getDataSource());
				// TurnoDAO turnoDAO = new TurnoDAO(this.getDataSource());
				Turno turno = new Turno();
				LogGuia logGuia = new LogGuia();
				// CierreDAO cierreDAO = new CierreDAO(this.getDataSource());
				Cierre cierre = new Cierre();
				// turno = turnoDAO.readByRut(u.getRut());
				if (turno.getId().length() == 0) {
					// turno = turnoDAO.readByRutalt(u.getRut());
					if (turno.getId().length() == 0) {
						// turno.setStatus(turnoDAO.concluido);
					}
				}
				// if (turno.status.equals(turnoDAO.vencido)) {
				// // if (!(su.terminal.equals(turno.terminal))) {
				// // values = new ArrayList<String>();
				// // values.add(su.terminal);
				// // values.add(rut);
				// // values.add(turno.id);
				// // turnoDAO.updateTerminal(values);
				// // }
				// // response.setStatus(400);
				// // responseWSi.resultado = false;
				// // responseWSi.mensaje = "Turno vencido";
				// // return responseWSi.returnJson();
				// } else if (turno.status.equals(turnoDAO.activo)) {
				// // SimpleDateFormat OutPutFormat = new SimpleDateFormat(
				// // "yyyy-mm-dd hh:mm:ss", java.util.Locale.getDefault());
				// // Timestamp vencimiento =
				// Timestamp.valueOf(OutPutFormat.format(turno.vence));
				// // if (!(vencimiento.after(actualTime))) {
				// // values = new ArrayList<String>();
				// // values.add(turnoDAO.vencido);
				// // values.add(rut);
				// // values.add(turno.id);
				// // turnoDAO.updateStatus(values);
				// // response.setStatus(400);
				// // responseWSi.resultado = false;
				// // responseWSi.mensaje = "Turno vencido";
				// // return responseWSi.returnJson();
				// // }
				// } else if (turno.status.equals(turnoDAO.concluido)) {
				// values = new ArrayList<String>();
				// values.add(u.rut);
				// values.add(u.terminal);
				// values.add(Tiempo.getOutDatedTimePlusParameter(
				// Integer.parseInt(s4.parametroDiasVencimientoTurno.valor),
				// turnoDAO.parametroCambioHorario));
				// values.add(turnoDAO.activo);
				// values.add(Tiempo.getNTPDateCL(turnoDAO.parametroCambioHorario));
				// turnoDAO.create(values);
				// turno = turnoDAO.readByRut(u.rut);
				// values = new ArrayList<String>();
				// values.add(turno.id);
				// values.add(u.rut);
				// values.add(u.terminal);
				// values.add(cierreDAO.activo);
				// values.add(Tiempo.getNTPDateCL(turnoDAO.parametroCambioHorario));
				// cierreDAO.create(values);
				// }
				// if (
				// u.getRut() != null &&
				// turno.getId() != null &&
				// turno.getId().length() > 0 &&
				// u.getRut().length() > 0
				// ) logGuia = logGuiaDAO.readByTurnoRutAsc(u.getRut(),
				// turno.getId());

				if (logGuia.getFolioguia().length() == 0)
					logGuia.setFolioguia(userStockRead.getFolioguia());

				if (userStockRead.getEntregar().equals("1") && userStockRead.getEntregar().equals("0")) {
					logGuia.setFolioguia("");
				}

				DefaultHttpClient httpClient = new DefaultHttpClient();
				// HttpPost postRequest = new HttpPost(
				// URLFolioDEV + "/service/post_login"
				// );
				// postRequest.setHeader("Content-Type", "application/json");

				String userName = u.getRut().replace("-", "");
				UserLoginAWS userLogin = new UserLoginAWS();
				// 1000 DEV - 2000 QAS - 3000 PRD
				userLogin.setID_SIST("2000");
				userLogin.setID_USU(userName);
				userLogin.setPASS(userName);

				String userLoginJSON = gson.toJson(userLogin);
				StringEntity params = new StringEntity(userLoginJSON);
				// postRequest.setEntity(params);
				// HttpResponse responsePost = httpClient.execute(postRequest);
				String contentResponse = "";
				String tokenAWS = "";
				correlativoAWS correlativoAWS = new correlativoAWS();
				CentrosAWS centrosAWS = new CentrosAWS();
				String correlativoAWSJson = "";
				String centrosAWSJson = "";
				// if (responsePost.getStatusLine().getStatusCode() == 200) {
				// contentResponse =
				// EntityUtils.toString(responsePost.getEntity());
				// System.out.println("Response de AWS");
				// System.out.println(contentResponse);
				// UserAWS userAWSParsedRes = new Gson()
				// .fromJson(contentResponse, UserAWS.class);
				// System.out.println(userAWSParsedRes.getUSU().getToken());
				// System.out.println("Login!!! AWS!!");
				// if (userAWSParsedRes.getINSTRUCCION().isStatus()) {
				// System.out.println("Entre al if!");
				// tokenAWS = userAWSParsedRes.getUSU().getToken();
				// correlativoAWSJson = getCorrelativos(tokenAWS);
				// if (correlativoAWS != null) {
				// correlativoAWS =
				// new Gson().fromJson(correlativoAWSJson,
				// correlativoAWS.class);
				// } else {
				// correlativoAWS = new Gson().fromJson("{}",
				// correlativoAWS.class);
				// }
				// centrosAWSJson = getCentros(tokenAWS);
				// if (centrosAWSJson != null) {
				// centrosAWS =
				// new Gson().fromJson(centrosAWSJson, CentrosAWS.class);
				// } else {
				// centrosAWS = new Gson().fromJson("{}", CentrosAWS.class);
				// }
				// }
				// }

				UsuarioLimagasPlaca usuarioLimagasPlaca = new UsuarioLimagasPlaca();

				usuarioLimagasPlaca.setPlaca(userName);

				String resultSapFolios = s4.odataServiceCallRead("GET_DATA_TO_REFS",
						(new Gson().toJson(usuarioLimagasPlaca, usuarioLimagasPlaca.getClass())), response);
				Type listTypeCELOs = new TypeToken<ArrayList<AWSCELOJson>>() {
				}.getType();
				List<AWSCELOJson> CELOs = new ArrayList<AWSCELOJson>();
				CELOs = new Gson().fromJson(resultSapFolios, listTypeCELOs); // new
																				// ArrayList<AWSCELOJson>();

				// for (AWSCELOJson CELO : centrosAWS.getData().getCELO()) {
				// System.out.println("Centro: " + CELO.getCENT());
				// if (CELO.getCENT().equals(u.getCentro())) {
				// CELOs.add(CELO);
				// break;
				// }
				// }

				if (productos != null) {
					strPayload = "{" + "\"rut\":\"" + u.getRut() + "\"," + "\"email\":\"" + u.getEmail() + "\","
							+ "\"nombre\":\"" + u.getNombre() + "\"," + "\"correlativo\":"
							+ new Gson().toJson(correlativoAWS.getData().getCORR()) + "," + "\"centros\":" +
							// new Gson().toJson(centrosAWS.getData().getCELO())
							// +
							new Gson().toJson(CELOs) + "," + "\"topeDeuda\":\"" + u.getTopeDeuda() + "\","
							+ "\"lista_pedido\":\"" + u.getListaPedidos() + "\"," + "\"bloqueado\":\""
							+ u.getBloqueado() + "\"," + "\"tipo\":\"" + u.getTipo() + "\"," + "\"terminal\":\""
							+ u.getTerminal() + "\"," + "\"usadescuentos\":\"" + u.getUsadescuento() + "\","
							+ "\"rol\":\"" + u.getRol() + "\"," + "\"folioguia\":\"" + logGuia.getFolioguia() + "\","
							+ "\"stock\":" + (new Gson().toJson(productos)).toUpperCase() + "," + "\"centro\":\""
							+ u.getCentro() + "\"," + "\"id_turno\":\"" + turno.getId() + "\"" + "}";

					final UsuarioDAO r_uDAO = this;
					final Usuario user = u;
					new Runnable() {
						public void run() {
							// try {
							//// r_uDAO.updateTerminal(user);
							// } catch (SQLException e) {
							// try {
							// ListObjectsReport report = new ListObjectsReport(
							// String.valueOf(
							// e
							// .getStackTrace()[e.getStackTrace().length -
							// 1].getLineNumber()
							// ),
							// this.getClass().getName(),
							// e.getMessage(),
							// e.getLocalizedMessage()
							// );
							// report.addObjects(new Object[] { r_uDAO, user });
							// //JavaEmail.Send(
							// report.getClassFile() +
							// ":line:" +
							// report.getLineException(),
							// report.toString()
							// );
							// } Exception (MessagingException e1) {
							// } catch (Exception e1) {
							// e1.printStackTrace();
							// }
							// e.printStackTrace();
							// }
						}
					};
				} else {
					strPayload = "{" + "\"rut\":\"" + u.getRut() + "\"," + "\"email\":\"" + u.getEmail() + "\","
							+ "\"nombre\":\"" + u.getNombre() + "\"correlativos\":"
							+ new Gson().toJson(correlativoAWS.getData().getCORR()) + ", \"centros\":" +
							// new Gson().toJson(centrosAWS.getData().getCELO())
							// +
							new Gson().toJson(CELOs) + ", " + "\"topeDeuda\":\"" + u.getTopeDeuda() + "\","
							+ "\"lista_pedido\":\"" + u.getListaPedidos() + "\"," + "\"bloqueado\":\""
							+ u.getBloqueado() + "\"," + "\"tipo\":\"" + u.getTipo() + "\"," + "\"terminal\":\""
							+ u.getTerminal() + "\"," + "\"usadescuentos\":\"" + u.getUsadescuento() + "\","
							+ "\"rol\":\"" + u.getRol() + "\"," + "\"folioguia\":\"\"," + "\"stock\":[]" + "}";
				}

				String compactJws = Jwts.builder().setExpiration(date).setSubject(strPayload)
						.signWith(SignatureAlgorithm.HS512, key).compact();
				u.setToken(compactJws);
				setUserSession(u);

				// PushNot.sendPushSuccessfulLogin(u.getIdpush(),u.getNombre(),parametroIntentoNotificacion,parametroTiempoNotificacion);

				if (u.getBloqueado().equals("true")) {
					resultado = "El usuario se encuentra bloqueado.";
				} else {
					resultado = "";
				}

				return ("{" + "\"access_token\":\"" + compactJws + "\"," + "\"token_type\":\"bearer\","
						+ "\"expires\":\"" + epoch + "\"," + "\"mensaje\":\"" + resultado + "\"," + "\"resultado\":true"
						+ "}");
			}

			checkCanalBool = u.checkCanalVenta(u.getTipo(), userAsking.getNumero_camion());
		}

		// PushNot.sendPushFailureLogin(userAsking.getIdpush(),"");

		if (checkCanalBool) {
			mensajeFinal = "\"mensaje\":\"Error de autenticaci�n.\",";
		} else {
			mensajeFinal = "\"mensaje\":\"Usuario no pertenece a este canal de venta.\",";
		}

		return ("{" + "\"token\":\"\"," + "\"token_type\":\"\"," + "\"expires\":\"\"," + mensajeFinal
				+ "\"resultado\":false" + "}");
	}

	private String getCorrelativos(String token) {
		// try {
		// DefaultHttpClient httpClient = new DefaultHttpClient();
		// HttpGet getRequest = new HttpGet(URLFolioDEV +
		// "/service/get_ini_corr");
		// getRequest.setHeader("x-access-token", token);
		// HttpResponse responseGet = httpClient.execute(getRequest);
		// String contentResponse = "";
		// if (responseGet.getStatusLine().getStatusCode() == 200) {
		// contentResponse = EntityUtils.toString(responseGet.getEntity());
		// correlativoAWS correlativoAWSParsed = new Gson()
		// .fromJson(contentResponse, correlativoAWS.class);
		// if (correlativoAWSParsed.getINSTRUCCION().isStatus()) {
		// System.out.println("RESPUESTA DE CORRELATIVOS");
		// System.out.println(contentResponse);
		// return contentResponse;
		// }
		// }
		//
		// return null;
		// } catch (Exception e) {
		// return null;
		// }
		return "";
	}

	private String getCentros(String token) {
		// try {
		// DefaultHttpClient httpClient = new DefaultHttpClient();
		// HttpGet getRequest = new HttpGet(
		// URLFolioDEV + "/service/get_ini_infocelo"
		// );
		// getRequest.setHeader("x-access-token", token);
		// HttpResponse responseGet = httpClient.execute(getRequest);
		// String contentResponse = "";
		// if (responseGet.getStatusLine().getStatusCode() == 200) {
		// contentResponse = EntityUtils.toString(responseGet.getEntity());
		// CentrosAWS centrosAWSParsed = new Gson()
		// .fromJson(contentResponse, CentrosAWS.class);
		// if (centrosAWSParsed.getINSTRUCCION().isStatus()) {
		// System.out.println("RESPUESTA DE CENTROS");
		// System.out.println(contentResponse);
		// return contentResponse;
		// }
		// }
		//
		// return null;
		// } catch (Exception e) {
		// return null;
		// }
		return "";
	}

	public boolean updateTerminal(Usuario user) throws SQLException {
		Connection connection = dataSource.getConnection();
		boolean rtrnStr = false;
		try {
			PreparedStatement pstmt = connection.prepareStatement("UPDATE \"" + Scheme.now + "\".\"STOCK_ACTUAL\" SET"
					+ "	\"TERMINAL\" = ?" + "	WHERE \"RUT\" = ?;");

			pstmt.setString(1, user.getTerminal());
			pstmt.setString(2, user.getRut());
			if (1 == pstmt.executeUpdate()) {
				rtrnStr = true;
				List<String> values = new ArrayList<String>();
				// LogTransbankDAO ltTransbankDAO = new LogTransbankDAO(
				// this.getDataSource()
				// );
				// values.add(user.getRut());
				// values.add(this.findUserByRut4Terminal(user.getRut()));
				// values.add(user.getTransbank());
				// values.add(Tiempo.getNTPDateCL(parametroCambioHorario));
				// ltTransbankDAO.create(values);
			} else {
				rtrnStr = false;
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, user, rtrnStr });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			rtrnStr = false;
			return rtrnStr;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return rtrnStr;
	}

	public String checkLogout(String body, HttpServletResponse response) throws IOException, SQLException {
		Usuario userAsking = gson.fromJson(body, Usuario.class);
		List<Usuario> resultList = selectUser(userAsking.getRut());
		for (Usuario u : resultList) {
			if ((u.getRut().equals(userAsking.getRut()))
			/* && ( userAsking.getClave().equals(u.getClave()) ) */
			) {
				freeUserSession(u);
				iresponse.setMensaje(""
				// "Realizado, SLL:> " +
				// SLLWS.desactivarTerminal(
				// u.returnJson(),
				// response,
				// this.getDataSource(),
				// this.parametroCambioHorario
				// )
				);
				iresponse.setResultado(true);

				return iresponse.returnJson();
			}
		}
		iresponse.setMensaje("No realizado.");
		iresponse.setResultado(false);
		return iresponse.returnJson();
	}

	public TokenSessionJwt parseJWT(String compactJws) {
		TokenSessionJwt jwtParsed = new TokenSessionJwt();
		// This line will throw an exception if it is not a signed JWS (as
		// expected)
		try {
			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody();
			jwtParsed.setSub(claims.getSubject());
			jwtParsed.setExp((long) claims.getExpiration().getTime());
			jwtParsed.setAttrs(new Gson().fromJson(jwtParsed.getSub(), LoginOutData.class));
			return jwtParsed;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
			return null;
		}
	}

	private String encodeB64(String strToHash) {
		return encoder.encodeBuffer(strToHash.getBytes()).replaceAll("\\s+", "");
	}

	public String findUserIdPush(String body) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objToReturn = "";
		Pedido o = gson.fromJson(body, Pedido.class);
		o.checkChars();
		o.getCliente().checkChars();
		String terminal = o.getDatos_entrega().getTerminal();
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					"	\"TERMINAL\"," + "	\"IDPUSH\"" + " FROM \"" + Scheme.now
					+ "\".\"USUARIO\" WHERE \"TERMINAL\" = '" + terminal + "';");
			ResultSet rs = pstmt.executeQuery();

			ArrayList<Usuario> list = new ArrayList<Usuario>();
			rs.next();
			if (rs.getString("TERMINAL").equals(terminal)) {
				return (objToReturn = rs.getString("IDPUSH"));
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, terminal, objToReturn, o, body });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			objToReturn = "";
			if (connection != null) {
				connection.close();
			}
			return objToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return objToReturn;
	}

	public String findUserIdPushByRut(String rut) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objToReturn = "";
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					"	\"RUT\"," + "	\"IDPUSH\"" + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"RUT\" = '" + rut
					+ "';");
			ResultSet rs = pstmt.executeQuery();

			ArrayList<Usuario> list = new ArrayList<Usuario>();
			rs.next();
			if (rs.getString("RUT").equals(rut)) {
				return (objToReturn = rs.getString("IDPUSH"));
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, rut, objToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			objToReturn = "";
			if (connection != null) {
				connection.close();
			}
			return objToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return objToReturn;
	}

	public String findUserIdPushByTerminal(String terminal) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objToReturn = "";
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					"	\"TERMINAL\"," + "	\"IDPUSH\"" + " FROM \"" + Scheme.now
					+ "\".\"USUARIO\" WHERE \"TERMINAL\" = '" + terminal + "';");
			ResultSet rs = pstmt.executeQuery();

			ArrayList<Usuario> list = new ArrayList<Usuario>();
			rs.next();
			if (rs.getString("TERMINAL").equals(terminal)) {
				return (objToReturn = rs.getString("IDPUSH"));
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, terminal, objToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			objToReturn = "";
			if (connection != null) {
				connection.close();
			}
			return objToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return objToReturn;
	}

	public Usuario findUserByTerminal(String terminal) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objToReturn = "";
		Usuario user = new Usuario();
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					"	* " + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"TERMINAL\"='" + terminal + "';");
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			// if ( rs.getString("TERMINAL").equals(terminal) ) {
			if (rs != null) {
				user.setId(rs.getString("ID"));
				user.setRut(rs.getString("RUT"));
				user.setClave(rs.getString("CLAVE"), true);
				user.setNombre(rs.getString("NOMBRE"));
				user.setTelefono(rs.getString("TELEFONO"));
				user.setEmail(rs.getString("EMAIL"));
				user.setTopeDeuda(rs.getString("TOPEDEUDA"));
				user.setToken(rs.getString("TOKEN"));
				user.setImei(rs.getString("IMEI"));
				user.setTipo(rs.getString("TIPO"));
				user.setRol(rs.getString("ROL"));
				user.setNumero_camion(rs.getString("NUMERO_CAMION"));
				user.setTerminal(rs.getString("TERMINAL"));
				user.setIdpush(rs.getString("IDPUSH"));
				user.setLista_pedido(rs.getString("LISTA_PEDIDO"));
				user.setBloqueado(rs.getString("BLOQUEADO"));
				user.setUsadescuentos(rs.getString("USADESCUENTOS"));
				user.setCentro(rs.getString("CENTRO"));
				user.setCentroDest(rs.getString("CENTRO_DEST"));
				user.setOferta(rs.getString("OFERTA"));
				user.setTimestamp(rs.getString("TIMESTAMP"));
				user.setUltima_guia(rs.getString("ULTIMA_GUIA"));
				user.setUltimo_cierre(rs.getString("ULTIMO_CIERRE"));
				user.setDeudorsap(rs.getString("DEUDORSAP"));
				user.setAcreedorsap(rs.getString("ACREEDORSAP"));
				user.setTransbank(rs.getString("TRANSBANK"));
				user.setStock_consignado(rs.getBoolean("STOCK_CONSIGNADO"));
				// }
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, terminal, user, objToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			user = new Usuario();
			if (connection != null) {
				connection.close();
			}
			return user;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return user;
	}

	public String findUserByTransbank4Terminal(String terminal) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objToReturn = "";
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					"	\"RUT\"," + "	\"TRANSBANK\"," + "	\"TERMINAL\"" + " FROM \"" + Scheme.now
					+ "\".\"USUARIO\" WHERE \"TERMINAL\"='" + terminal + "';");
			ResultSet rs = pstmt.executeQuery();

			ArrayList<Usuario> list = new ArrayList<Usuario>();
			rs.next();
			if (rs.getString("TERMINAL").equals(terminal)) {
				objToReturn = rs.getString("TRANSBANK");
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, terminal, objToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			objToReturn = "";
			if (connection != null) {
				connection.close();
			}
			return objToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return objToReturn;
	}

	public String findUserByTransbank4Rut(String rut) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objToReturn = "";
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					"	\"RUT\"," + "	\"TRANSBANK\"," + "	\"TERMINAL\"" + " FROM \"" + Scheme.now
					+ "\".\"USUARIO\" WHERE \"RUT\"='" + rut + "';");
			ResultSet rs = pstmt.executeQuery();

			ArrayList<Usuario> list = new ArrayList<Usuario>();
			rs.next();
			if (rs.getString("RUT").equals(rut)) {
				objToReturn = rs.getString("TRANSBANK");
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, rut, objToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			objToReturn = "";
			if (connection != null) {
				connection.close();
			}
			return objToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return objToReturn;
	}

	public String findUserByTerminal4Rut(String terminal) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objToReturn = "";
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					"	\"RUT\"," + "	\"TERMINAL\"" + " FROM \"" + Scheme.now
					+ "\".\"USUARIO\" WHERE \"TERMINAL\"=? ;");

			pstmt.setString(1, terminal);
			ResultSet rs = pstmt.executeQuery();

			// HEREEEEEE

			ArrayList<Usuario> list = new ArrayList<Usuario>();
			while (rs.next()) {
				// if ( rs.getString("TERMINAL").equals(terminal) ) {
				objToReturn = rs.getString("RUT");
				// }
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, terminal, objToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			objToReturn = "";
			return objToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return objToReturn;
	}

	public String findUserByRut4Terminal(String rut) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objToReturn = "";
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					"	\"RUT\"," + "	\"TERMINAL\"" + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"RUT\"='" + rut
					+ "';");
			ResultSet rs = pstmt.executeQuery();

			ArrayList<Usuario> list = new ArrayList<Usuario>();
			rs.next();
			if (rs.getString("RUT").equals(rut)) {
				objToReturn = rs.getString("TERMINAL");
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, rut, objToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			objToReturn = "";
			if (connection != null) {
				connection.close();
			}
			return objToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return objToReturn;
	}

	public String findUserByTerminal4TipoUsuario(String terminal) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objToReturn = "";
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					"	\"TIPO\"," + "	\"TERMINAL\"" + " FROM \"" + Scheme.now + "\".\"USUARIO\" WHERE \"TERMINAL\"='"
					+ terminal + "';");

			ResultSet rs = pstmt.executeQuery();

			ArrayList<Usuario> list = new ArrayList<Usuario>();
			rs.next();
			if (rs.getString("TERMINAL").equals(terminal)) {
				objToReturn = rs.getString("TIPO");
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, terminal, objToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			objToReturn = "";
			if (connection != null) {
				connection.close();
			}
			return objToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return objToReturn;
	}

	public String findUserByTerminal4NCamion(String terminal) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objToReturn = "";
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT " + // TOP
																				// 1000
																				// para
																				// limitar
																				// la
																				// busqueda
																				// total
					"	\"NUMERO_CAMION\"," + "	\"TERMINAL\"" + " FROM \"" + Scheme.now
					+ "\".\"USUARIO\" WHERE \"TERMINAL\"='" + terminal + "';");
			ResultSet rs = pstmt.executeQuery();

			ArrayList<Usuario> list = new ArrayList<Usuario>();

			// if (rs.getFetchSize() >= 1) {
			while (rs.next()) {
				if (rs.getString("TERMINAL").equals(terminal)) {
					objToReturn = rs.getString("NUMERO_CAMION");
				}
			}
			// }

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, terminal, objToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			objToReturn = "";
			return objToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return objToReturn;
	}

	public void notifyAll(String msg, String title, int parametroIntentoNotificacion, int parametroTiempoNotificacion)
			throws SQLException, ClientProtocolException, IOException, InterruptedException {
		List<Usuario> list = selectAllUsers();
		for (int i = 0; i < list.size(); i++) {
			Usuario u = list.get(i);
			PushNot.sendPushNotByThisMsg(u.getIdpush(), msg, title, parametroIntentoNotificacion,
					parametroTiempoNotificacion);
		}
	}

	public ArrayList<SelectOption> readAllCenters() throws SQLException {
		Connection connection = getDataSource().getConnection();
		ArrayList<SelectOption> list = new ArrayList<SelectOption>();
		try {
			PreparedStatement pstmt = connection.prepareStatement(this.queryAllCenters);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SelectOption o = new SelectOption();
				o.setKey(rs.getString("CENTRO"));
				o.setText(rs.getString("CENTRO"));
				list.add(o);
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				// ListObjectsReport report = new
				// ListObjectsReport(lineException, classFile,
				// getMessage, getLocalizedMessage);
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObject(connection);
				report.addObject(list);
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

			if (connection != null) {
				connection.close();
			}

			return list;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return list;
	}

	public void setNewConn(DataSource ds) {
		this.dataSource = ds;
	}

	public String userUpdateByTerminal(String sBody) throws SQLException, NoSuchAlgorithmException {
		Response oResponse = new Response();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		UserUpdate.ByTerminalRequest oRequest = gson.fromJson(sBody, UserUpdate.ByTerminalRequest.class);

		Usuario oUsuario = this.selectUserByTerminal("{ \"terminal\" : " + oRequest.terminal + " }");

		for (UserUpdate.Property oProperty : oRequest.properties) {
			switch (oProperty.property) {
			case "telefono":
				oUsuario.setTelefono(oProperty.value);
				break;
			case "centroDest":
				oUsuario.setCentroDest(oProperty.value);
				break;
			case "stock_consignado":
				oUsuario.setStock_consignado(new Boolean(oProperty.value));
				break;
			}
			/*
			 * try { Class<?> oClass = oUsuario.getClass();
			 * java.lang.reflect.Field field =
			 * oClass.getDeclaredField(oProperty.property); field.set(oUsuario,
			 * oProperty.value);
			 *
			 * } catch (NoSuchFieldException e1) {
			 * oResponse.setResultado(false);
			 * oResponse.setMensaje("NoSuchFieldException: " +
			 * oProperty.property); return gson.toJson(oResponse); } catch
			 * (SecurityException e1) { oResponse.setResultado(false);
			 * oResponse.setMensaje("SecurityException: " + oProperty.property);
			 * return gson.toJson(oResponse); } catch (IllegalArgumentException
			 * e1) { oResponse.setResultado(false);
			 * oResponse.setMensaje("IllegalArgumentException: " +
			 * oProperty.property); return gson.toJson(oResponse); } catch
			 * (IllegalAccessException e1) { oResponse.setResultado(false);
			 * oResponse.setMensaje("IllegalAccessException: " +
			 * oProperty.property); return gson.toJson(oResponse); }
			 */
		}

		return this.updateUser_NoPasswd(oUsuario);
	}

	public String updateUser_NoPasswd(Usuario user) throws SQLException, NoSuchAlgorithmException {
		Connection connection = dataSource.getConnection();
		String objectToReturn = "";
		if (user.getClave().length() > 0)
			user.setClave(user.getClave(), false);

		Usuario userAuthPass = selectUserById(new Gson().toJson(user, user.getClass()));
		if (user.getClave().length() == 0) {
			user.setClave(userAuthPass.getClave(), true);
		} else {
			boolean resultAuth = userAuthPass.newAuthClave(user.getClave());
			if (resultAuth) {
				user.setClave(userAuthPass.getClave(), true);
			}
		}

		try {
			PreparedStatement pstmt = connection
					.prepareStatement("UPDATE \"" + Scheme.now + "\".\"USUARIO\" " + " SET \"RUT\" = ?, " + // 1
							"	\"NOMBRE\" = ?, " + // 2
							"	\"TELEFONO\" = ?, " + // 3
							"	\"EMAIL\" = ?, " + // 4
							"	\"TOPEDEUDA\" = ?, " + // 5
							// " \"TOKEN\" =
							// 'eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1MzE5MTk5MDYsInN1YiI6IntcInJ1dFwiOlwiMVwiLFwiZW1haWxcIjpcIkVSVUlaQFNUQVJDT05TVUxUSU5HLkNMXCIsXCJub21icmVcIjpcIkVEVUFSRE8gUlVJWlwiLFwidG9wZURldWRhXCI6XCIxLjAwMC4wMDBcIixcImxpc3RhX3BlZGlkb1wiOlwiMFwiLFwiYmxvcXVlYWRvXCI6XCIwXCIsXCJ0aXBvXCI6XCIwMDFcIixcInRlcm1pbmFsXCI6XCI5NjAwXCIsXCJ1c2FkZXNjdWVudG9zXCI6XCIwXCIsXCJyb2xcIjpcIlwifSJ9.7bUJ86wsjyyLmsOX9F7RrIN0rWVYJNta0Nul77sqs8FQW4sv1CGani_-mbistljf7j4Ci7is846v1680evRanw',
							// " +
							// " \"IMEI\" = ?, " +
							"	\"TIPO\" = ?, " + // 6
							"	\"ROL\" = ?, " + // 7
							// " \"NUMERO_CAMION\" = 'as', " +
							"	\"TERMINAL\" = ?, " + // 8
							// " \"IDPUSH\" =
							// 'dFZ-4VCNHsA:APA91bHbKvafV80-ZGEff11oVz5S66uUio460IZ14-31PrHhMJDu2LA6ANuvjlgTs2oVKNa7jsL1Wt-mYWn4tA0MDCpxKHM6YlLb7K56Abz_EJrWt51zMxGKkumxBEklIjCnwYbdZAWr_SCSPUh8sYNgBghVafm_oA',
							// " +
							"	\"LISTA_PEDIDO\" = ?, " + // 9
							"	\"BLOQUEADO\" = ?, " + // 10
							"	\"USADESCUENTOS\" = ?, " + // 11
							"	\"TIMESTAMP\" = ?, " + // 12CURRENT_TIMESTAMP
							"	\"OFERTA\" = ?," + // 13
							"	\"TRANSBANK\" = ?," + // 14
							"	\"CENTRO_DEST\" = ?," + // 15
							"	\"STOCK_CONSIGNADO\" = ?" + // 16
							// " \"CENTRO\" = '5100', " +
							// " \"ULTIMA_GUIA\" = CURRENT_TIMESTAMP, " +
							// " \"ULTIMO_CIERRE\" = CURRENT_TIMESTAMP" +
							" WHERE \"ID\" = ?;" + // 17
							"");

			// user.setClave(user.clave);

			pstmt.setString(1, user.getRut());
			pstmt.setString(2, new String(user.getNombre().getBytes(), "ISO-8859-1"));
			pstmt.setString(3, user.getTelefono());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getTopeDeuda());
			pstmt.setString(6, user.getTipo());
			pstmt.setString(7, user.getRol());
			pstmt.setString(8, user.getTerminal());
			pstmt.setString(9, user.getListaPedidos());
			pstmt.setString(10, user.getBloqueado());
			pstmt.setString(11, user.getUsadescuento());
			pstmt.setString(12, Tiempo.getNTPDateCL(parametroCambioHorario));
			pstmt.setString(13, user.getOferta());
			pstmt.setString(14, user.getTransbank());
			pstmt.setString(15, user.getCentroDest());
			pstmt.setBoolean(16, user.getStock_consignado());
			pstmt.setString(17, user.getId());

			int resultado = pstmt.executeUpdate();

			if (resultado == 1) {
				iresponse.setMensaje("Realizado.");
				iresponse.setResultado(true);
			} else {
				iresponse.setMensaje("No realizado.");
				iresponse.setResultado(false);
			}
			objectToReturn = iresponse.returnJson();
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObjects(new Object[] { connection, user, objectToReturn });
				// JavaEmail.Send(report.getClassFile() + ":line:" +
				// report.getLineException(), report.toString());
			} catch (Exception e1) {
				// } catch (MessagingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			iresponse.setMensaje("No realizado.");
			iresponse.setResultado(false);
			objectToReturn = iresponse.returnJson();
			return objectToReturn;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return objectToReturn;
	}
}
