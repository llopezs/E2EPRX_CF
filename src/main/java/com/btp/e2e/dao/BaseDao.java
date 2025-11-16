package com.btp.e2e.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.NumberFormat;
import java.util.List;

import jakarta.mail.MessagingException;
import javax.sql.DataSource;

import com.btp.e2e.entities.Parametros;
import com.btp.e2e.servlets.Structures4Jsons.Response;
import com.btp.e2e.servlets.services.report.JavaEmail;
import com.btp.e2e.servlets.services.report.ListObjectsReport;
import com.google.gson.Gson;

public class BaseDao {

	protected Response iresponse = new Response();

	public String activo = "A";
	public String vencido = "V";
	public String concluido = "C";

	public String parametroCambioHorario;
	protected DataSource dataSource;
	private Gson gson;

	private String schemaName = "";
	private String tableName = "";
	private String tableSequence = "";

	private String tableAccess = "";
	private String sqlQueryCreate = "";
	private String sqlQueryRead = "";
	private String sqlQueryReadAll = "";
	private String sqlQueryReadLastId = "";
	private String sqlQueryUpdate = "";
	private String sqlQueryDelete = "";

	public BaseDao(DataSource dataSource) throws SQLException {
		super();
		this.dataSource = dataSource;
		this.setGson(new Gson());
//		ParametrosDAO parametrosDAO = new ParametrosDAO(dataSource);
//		Parametros parametroCambioHorarioP = parametrosDAO.readByParametro("{\"parametro\":\"CAMBIO_HORARIO\"}");
//		parametroCambioHorario = parametroCambioHorarioP.getValor();
	}
	
	public BaseDao(DataSource dataSource, String voiding) throws SQLException {
		super();
		this.dataSource = dataSource;
		this.setGson(new Gson());
	}

	public String returnJson() {
		return getGson().toJson(this);
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
	}

	public String create(List<String> values) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objectToReturn = "";
		try {

			PreparedStatement pstmt = connection.prepareStatement(this.sqlQueryCreate);

			// pstmt.setString(1,o.pedido);

			for (int i = 0; i < values.size(); i++) {
				// int pos = values.get(i).indexOf(".ic");
				// if (pos != -1) {
				// String parts = values.get(i).replaceAll(".ic","" ).replaceAll("\\s+","" );
				// pstmt.setInt((i+1),Integer.valueOf(parts));
				// } else {
				if (values.get(i) != null)
					pstmt.setString((i + 1), values.get(i).replaceAll("\\s++", " "));
				else
					pstmt.setString((i + 1), "");
				// }
			}

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

			e.printStackTrace();
			iresponse.setMensaje("No realizado.");
			iresponse.setResultado(false);
			objectToReturn = iresponse.returnJson();
			try {
				// ListObjectsReport report = new ListObjectsReport(lineException, classFile,
				// getMessage, getLocalizedMessage);
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObject(connection);
				report.addObject(objectToReturn);
				JavaEmail.Send(report.getClassFile() + ":line:" + report.getLineException(), report.toString());
			} catch (MessagingException e1) {
				
				e1.printStackTrace();
			}
			if (connection != null) {
				connection.close();
			}
			return objectToReturn;
		} finally {

			if (connection != null) {
				connection.close();
			}

		}

		return objectToReturn;
	}

	public String update(List<String> values) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objectToReturn = "";
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(this.sqlQueryUpdate);
			for (int i = 0; i < values.size(); i++) {
				
				// int pos = values.get(i).indexOf(".ic");
				// if (pos != -1) {
				// String parts = values.get(i).replaceAll(".ic","" ).replaceAll("\\s+","" );
				// pstmt.setInt((i+1),Integer.valueOf(parts));
				// } else {
				if (values.get(i) != null)
					pstmt.setString((i + 1), values.get(i).replaceAll("\\s++", " "));
				else
					pstmt.setString((i + 1), "");
				// }
			}

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
				// ListObjectsReport report = new ListObjectsReport(lineException, classFile,
				// getMessage, getLocalizedMessage);
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObject(connection);
				report.addObject(objectToReturn);
				JavaEmail.Send(report.getClassFile() + ":line:" + report.getLineException(), report.toString());
			} catch (MessagingException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
			iresponse.setMensaje("No realizado.");
			iresponse.setResultado(false);
			objectToReturn = iresponse.returnJson();
			if (connection != null) {
				connection.close();
			}
			return objectToReturn;
		} finally {

			if (connection != null) {
				connection.close();
			}

		}

		return objectToReturn;
	}

	public String delete(List<String> values) throws SQLException {
		Connection connection = dataSource.getConnection();
		String objectToReturn = "";
		try {

			PreparedStatement pstmt = connection.prepareStatement(this.sqlQueryDelete);

			for (int i = 0; i < values.size(); i++) {
				// int pos = values.get(i).indexOf(".ic");
				// if (pos != -1) {
				// String parts = values.get(i).replaceAll(".ic","" ).replaceAll("\\s+","" );
				// pstmt.setInt((i+1),Integer.valueOf(parts));
				// } else {
				if (values.get(i) != null)
					pstmt.setString((i + 1), values.get(i).replaceAll("\\s++", " "));
				else
					pstmt.setString((i + 1), "");
				// }
			}

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
				// ListObjectsReport report = new ListObjectsReport(lineException, classFile,
				// getMessage, getLocalizedMessage);
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObject(connection);
				report.addObject(objectToReturn);
				JavaEmail.Send(report.getClassFile() + ":line:" + report.getLineException(), report.toString());
			} catch (MessagingException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
			iresponse.setMensaje("No realizado.");
			iresponse.setResultado(false);
			objectToReturn = iresponse.returnJson();
			if (connection != null) {
				connection.close();
			}
			return objectToReturn;
		} finally {

			if (connection != null) {
				connection.close();
			}

		}

		return objectToReturn;
	}

	public String readLastId() throws SQLException {

		Connection connection = getDataSource().getConnection();
		String o = "0";
		try {

			PreparedStatement pstmt = connection.prepareStatement(this.getSqlQueryReadLastId());

			// pstmt.setInt(1,Integer.parseInt(oParam.id));

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				o = rs.getString("ID");
			}

			if (connection != null) {
				connection.close();
			}

		} catch (Exception e) {
			try {
				// ListObjectsReport report = new ListObjectsReport(lineException, classFile,
				// getMessage, getLocalizedMessage);
				ListObjectsReport report = new ListObjectsReport(
						String.valueOf(e.getStackTrace()[e.getStackTrace().length - 1].getLineNumber()),
						this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObject(connection);
				report.addObject(o);
				JavaEmail.Send(report.getClassFile() + ":line:" + report.getLineException(), report.toString());
			} catch (MessagingException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			return o;
		} finally {

			if (connection != null) {
				connection.close();
			}

		}
		return o;
	}

	/**
	 * @return the sqlQueryCreate
	 */
	public String getSqlQueryCreate() {
		return sqlQueryCreate;
	}

	/**
	 * @param sqlQueryCreate
	 *            the sqlQueryCreate to set
	 */
	public void setSqlQueryCreate(String sqlQueryCreate) {
		this.sqlQueryCreate = sqlQueryCreate;
	}

	/**
	 * @return the sqlQueryRead
	 */
	public String getSqlQueryRead() {
		return sqlQueryRead;
	}

	/**
	 * @param sqlQueryRead
	 *            the sqlQueryRead to set
	 */
	public void setSqlQueryRead(String sqlQueryRead) {
		this.sqlQueryRead = sqlQueryRead;
	}

	/**
	 * @return the sqlQueryReadAll
	 */
	public String getSqlQueryReadAll() {
		return sqlQueryReadAll;
	}

	/**
	 * @param sqlQueryReadAll
	 *            the sqlQueryReadAll to set
	 */
	public void setSqlQueryReadAll(String sqlQueryReadAll) {
		this.sqlQueryReadAll = sqlQueryReadAll;
	}

	/**
	 * @return the sqlQueryUpdate
	 */
	public String getSqlQueryUpdate() {
		return sqlQueryUpdate;
	}

	/**
	 * @param sqlQueryUpdate
	 *            the sqlQueryUpdate to set
	 */
	public void setSqlQueryUpdate(String sqlQueryUpdate) {
		this.sqlQueryUpdate = sqlQueryUpdate;
	}

	/**
	 * @return the sqlQueryDelete
	 */
	public String getSqlQueryDelete() {
		return sqlQueryDelete;
	}

	/**
	 * @param sqlQueryDelete
	 *            the sqlQueryDelete to set
	 */
	public void setSqlQueryDelete(String sqlQueryDelete) {
		this.sqlQueryDelete = sqlQueryDelete;
	}

	/**
	 * @return the tableSchema
	 */
	public String getTableSchema() {
		return getSchemaName();
	}

	/**
	 * @param tableSchema
	 *            the tableSchema to set
	 */
	public void setTableSchema(String tableSchema) {
		this.setSchemaName(tableSchema);
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the tScNa
	 */
	public String getTableAccess() {
		return tableAccess;
	}

	/**
	 * @param tableAccess
	 *            the tScNa to set
	 */
	public void setTableAccess() {
		this.tableAccess = "\"" + this.getSchemaName() + "\".\"" + this.tableName + "\"";
	}

	/**
	 * @return the tableSequence
	 */
	public String getTableSequence() {
		return tableSequence;
	}

	/**
	 * @param tableSequence
	 *            the tableSequence to set
	 */
	public void setTableSequence() {
		this.tableSequence = "\"" + this.getSchemaName() + "\".\"SEQ_ID_" + this.tableName + "\".NEXTVAL";
	}

	public String getSqlQueryReadLastId() {
		return sqlQueryReadLastId;
	}

	public void setSqlQueryReadLastId(String sqlQueryReadLastId) {
		this.sqlQueryReadLastId = sqlQueryReadLastId;
	}

	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

}
