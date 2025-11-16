package com.btp.e2e.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.mail.MessagingException;
import javax.sql.DataSource;

import com.btp.e2e.entities.LogPreVenta;
import com.btp.e2e.entities.LogRecepciones;
import com.btp.e2e.servlets.configs.Scheme;
import com.btp.e2e.servlets.services.report.JavaEmail;
import com.btp.e2e.servlets.services.report.ListObjectsReport;

public class LogRecepcionesDAO extends BaseDao {
	
	private String sqlQueryForID = "";
	
	public LogRecepcionesDAO(DataSource dataSource) throws SQLException {
		super(dataSource);
		
		this.setTableSchema(Scheme.now);
		this.setTableName("LOG_RECEPCIONES");
		this.setTableAccess();
		this.setTableSequence();//\"PITS\".\"LOG_RECEPCIONES\" \"PITS\".\"SEQ_ID_LOG_RECEPCIONES\".NEXTVAL
//		this.setSqlQueryCreate("INSERT INTO "+this.getTableAccess()+" VALUES("+this.getTableSequence()+", ?, ?, ?, ?, ?);");
//		this.setSqlQueryCreate("INSERT INTO "+this.getTableAccess()+" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		this.setSqlQueryCreate("INSERT INTO "+this.getTableAccess()+" VALUES(" + this.getTableSequence() + ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		this.setSqlQueryRead("SELECT * FROM "+this.getTableAccess()+" WHERE \"ID\" = ?;");
		this.setSqlQueryReadLastId("SELECT TOP 1 * FROM "+this.getTableAccess()+" ORDER BY \"ID\" DESC;");
		this.setSqlQueryReadAll("SELECT * FROM "+this.getTableAccess()+";");
		this.setSqlQueryUpdate("UPDATE "+this.getTableAccess()+" SET \"RESPUESTA\"=?, \"STATUS_HTTP\"= ?, \"TIMESTAMP_END\" = ? WHERE \"ID\" = ?;");
		this.setSqlQueryDelete("DELETE FROM "+this.getTableAccess()+" WHERE \"ID\"=?;");
		this.sqlQueryForID = "SELECT TOP 1 * FROM "+this.getTableAccess()+" WHERE SERVICIO = ? AND DATOS = ? AND SY_ORIGEN = ? AND APP_VERSION = ? AND OS_VERSION = ? AND MODEL_DEVICE = ? AND STATUS_HTTP = ? ORDER BY ID DESC;";
	}
	
public Boolean readExistLogRecepStatusP(String idExterno) throws SQLException {
		
		Connection connection = dataSource.getConnection();
		LogRecepciones o = new LogRecepciones();
		Boolean exist = false;
		try {
            
        	PreparedStatement pstmt = connection
                    .prepareStatement("SELECT COUNT(*) AS \"COUNTER\" FROM "+this.getTableAccess()+" WHERE \"RESPUESTA\" LIKE '%{\"status\":\"P\"}%'"
                    		+ " and \"DATOS\" LIKE ?;");
            
        	pstmt.setString(1,'%'+idExterno+'%');
			
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
						//if(!rs.getString("COUNTER").equals("1")){
						if(Integer.parseInt(rs.getString("COUNTER")) > 10){
							exist = true;
						} else {
							exist = false;
						}				
            } 

            if (connection != null) {
            	connection.close();
            }
            
        } catch (Exception e) {
        	try {
//				ListObjectsReport report = new ListObjectsReport(lineException, classFile, getMessage, getLocalizedMessage);
				ListObjectsReport report = new ListObjectsReport(String.valueOf(e.getStackTrace()[e.getStackTrace().length-1].getLineNumber()), this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObject(connection);
				report.addObject(o);
				JavaEmail.Send(report.getClassFile()+":line:"+report.getLineException(),report.toString());
			} catch (MessagingException e1) {
				exist = false;
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			return exist;
        } finally {
            
        	if (connection != null) {
				connection.close();
			}
			
		}
		return exist;
	}

	public LogRecepciones read(String body) throws SQLException {
		
		Connection connection = dataSource.getConnection();
		LogRecepciones o = new LogRecepciones();
		LogRecepciones oParam = this.getGson().fromJson(body,LogRecepciones.class);		
		try {
            
        	PreparedStatement pstmt = connection
                    .prepareStatement(this.getSqlQueryRead());
            
        	pstmt.setInt(1,Integer.parseInt(oParam.getId()));
			
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	o.setId(rs.getString("ID"));
            	o.setServicio(rs.getString("SERVICIO"));
            	o.setDatos(rs.getString("DATOS"));          
            	o.setTimestamp(rs.getString("TIMESTAMP"));
            	o.setSy_origen(rs.getString("SY_ORIGEN"));
            	o.setRespuesta(rs.getString("RESPUESTA"));
            	o.setApp_version(rs.getString("APP_VERSION"));
            	o.setOs_version(rs.getString("OS_VERSION"));
            	o.setModel_device(rs.getString("MODEL_DEVICE"));
            } 
            if (connection != null) {
            	connection.close();
            }
            
            
        } catch (Exception e) {
        	try {
//				ListObjectsReport report = new ListObjectsReport(lineException, classFile, getMessage, getLocalizedMessage);
				ListObjectsReport report = new ListObjectsReport(String.valueOf(e.getStackTrace()[e.getStackTrace().length-1].getLineNumber()), this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObject(connection);
				report.addObject(o);
				report.addObject(oParam);
				JavaEmail.Send(report.getClassFile()+":line:"+report.getLineException(),report.toString());
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
	
public String readForID(String body) throws SQLException {
		
		Connection connection = dataSource.getConnection();
		LogRecepciones o = new LogRecepciones();
		LogRecepciones oParam = this.getGson().fromJson(body,LogRecepciones.class);		
		try {
            
        	PreparedStatement pstmt = connection
                    .prepareStatement(this.sqlQueryForID);
            
        	pstmt.setString(1,oParam.getServicio());
        	pstmt.setString(2,oParam.getDatos());
        	pstmt.setString(3,oParam.getSy_origen());
        	pstmt.setString(4,oParam.getApp_version());
        	pstmt.setString(5,oParam.getOs_version());
        	pstmt.setString(6,oParam.getModel_device());
        	pstmt.setString(7,"");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	o.setId(rs.getString("ID"));
            	return o.getId();
            } 
            if (connection != null) {
            	connection.close();
            }
            
            
        } catch (Exception e) {
        	try {
//				ListObjectsReport report = new ListObjectsReport(lineException, classFile, getMessage, getLocalizedMessage);
				ListObjectsReport report = new ListObjectsReport(String.valueOf(e.getStackTrace()[e.getStackTrace().length-1].getLineNumber()), this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObject(connection);
				report.addObject(o);
				report.addObject(oParam);
				JavaEmail.Send(report.getClassFile()+":line:"+report.getLineException(),report.toString());
			} catch (MessagingException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
			return o.getId();
        } finally {
            
        	if (connection != null) {
				connection.close();
			}
			
		}
		return o.getId();
	}
	
	public List<LogRecepciones> readAll() throws SQLException{
		Connection connection = dataSource.getConnection();
		ArrayList<LogRecepciones> list = new ArrayList<LogRecepciones>();
		try {
            
        	PreparedStatement pstmt = connection
                    .prepareStatement(this.getSqlQueryReadAll());
            
            ResultSet rs = pstmt.executeQuery();
            
            
            while (rs.next()) {
            	LogRecepciones o = new LogRecepciones();
            	o.setId(rs.getString("ID"));
            	o.setServicio(rs.getString("SERVICIO"));
            	o.setDatos(rs.getString("DATOS"));		    	
            	o.setTimestamp(rs.getString("TIMESTAMP"));
            	o.setSy_origen(rs.getString("SY_ORIGEN"));
            	o.setRespuesta(rs.getString("RESPUESTA"));
            	o.setApp_version(rs.getString("APP_VERSION"));
            	o.setOs_version(rs.getString("OS_VERSION"));
            	o.setModel_device(rs.getString("MODEL_DEVICE"));
                list.add(o);
            }
            
            if (connection != null) {
                connection.close();
            }
   
        } catch (Exception e) {
        	try {
			ListObjectsReport report = new ListObjectsReport(String.valueOf(e.getStackTrace()[e.getStackTrace().length-1].getLineNumber()), this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
			report.addObject(connection);
			report.addObject(list);
			JavaEmail.Send(report.getClassFile()+":line:"+report.getLineException(),report.toString());
		} catch (MessagingException e1) {
			
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
	
	public String readExistLogRecepTurno(String idturno) throws SQLException {
		
		Connection connection = dataSource.getConnection();
		LogRecepciones o = new LogRecepciones();
		String Result = "";
		try {
	        
	    	PreparedStatement pstmt = connection
					.prepareStatement("SELECT DATOS FROM "+this.getTableAccess()+" WHERE DATOS LIKE ?");
			
	    	pstmt.setString(1,"%\"id_turno\":\"" + idturno + "\"%");
			
			
	        ResultSet rs = pstmt.executeQuery();
	        
	        		while (rs.next()) {
	        			Result = rs.getString(1);
	        } 

	        if (connection != null) {
	        	connection.close();
	        }
	        
	    } catch (Exception e) {
	    	try {
//				ListObjectsReport report = new ListObjectsReport(lineException, classFile, getMessage, getLocalizedMessage);
				ListObjectsReport report = new ListObjectsReport(String.valueOf(e.getStackTrace()[e.getStackTrace().length-1].getLineNumber()), this.getClass().getName(), e.getMessage(), e.getLocalizedMessage());
				report.addObject(connection);
				report.addObject(o);
				JavaEmail.Send(report.getClassFile()+":line:"+report.getLineException(),report.toString());
			} catch (MessagingException e1) {
			
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (connection != null) {
	            connection.close();
	        }
			return Result;
	    } finally {
	        
	    	if (connection != null) {
	            connection.close();
	        }
	    	
		}
		return Result;
	}

	public void setNewConn(DataSource ds) {
		this.dataSource = ds;
	}
}
