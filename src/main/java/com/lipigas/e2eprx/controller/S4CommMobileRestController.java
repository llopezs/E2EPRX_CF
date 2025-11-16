package com.lipigas.e2eprx.controller;

import com.btp.e2e.servlets.GeneralMsg;
import com.btp.e2e.servlets.Structures4Jsons.LoginOutData;
import com.btp.e2e.servlets.Structures4Jsons.LoginTokenData;
import com.btp.e2e.servlets.Structures4Jsons.OdataStructure;
import com.btp.e2e.servlets.Structures4Jsons.ResponseSAP;
import com.btp.e2e.servlets.Structures4Jsons.UserTokenSave;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * S4CommMobileRestController: Spring Boot REST adapter for legacy S4CommMobile servlet
 * 
 * Migration Status:
 * - Endpoint: /api/mobile/* (replaced /mobile/ servlet)
 * - Methods: login, logout, recoverUser, odata, sendPush, checkBearerContent2, getFile
 * - JWT Token: HS512 algorithm with 12-hour expiration
 * - Authentication: Bearer token validation
 * - Response Format: JSON (with optional XML conversion)
 * 
 * Legacy Servlet: @WebServlet("/mobile/")
 * New Controller: @RequestMapping("/api/mobile")
 * 
 * TODO:
 * - Integrate with UsuarioDAO for actual user authentication
 * - Implement OData service calls via RestTemplate
 * - Replace hardcoded JWT secret with environment variable
 * - Add PushNot service integration
 * - Add Tiempo utility for timezone handling
 */
@RestController
@RequestMapping("/api/mobile")
public class S4CommMobileRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(S4CommMobileRestController.class);
    private static final Gson gson = new Gson();
    private static final String JWT_SECRET = "wFFft7Yrd9SxjMMwlt0x9ZRI8e6nWgrmA18V9ewBiG6kXZIDz32";
    private static final long JWT_EXPIRATION_MS = 1000 * 60 * 60 * 12; // 12 hours

    @Autowired(required = false)
    private DataSource dataSource;

    /**
     * Main mobile endpoint handler
     * Supports: login, logout, recoverUser, odata, sendPush, checkBearerContent2, getFile
     * 
     * @param method HTTP header "method" indicating operation
     * @param authorization Bearer token for authenticated operations
     * @param version App version from "VERSION" header
     * @param osVersion OS version from "OS_VERSION" header
     * @param model Device model from "MODEL" header
     * @param format Response format: "xml" or "json" (default)
     * @param requestBody JSON request payload
     * @return JSON or XML response
     */
    @PostMapping
    public ResponseEntity<String> handleMobileRequest(
            @RequestHeader(value = "method", required = false) String method,
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestHeader(value = "VERSION", required = false, defaultValue = "0") String version,
            @RequestHeader(value = "OS_VERSION", required = false, defaultValue = "No identificado") String osVersion,
            @RequestHeader(value = "MODEL", required = false, defaultValue = "No identificado") String model,
            @RequestParam(value = "format", required = false, defaultValue = "json") String format,
            @RequestBody(required = false) String requestBody) {

        LOGGER.info("Mobile request - Method: {}, Version: {}, OS: {}, Model: {}", method, version, osVersion, model);

        if (requestBody == null || requestBody.isEmpty()) {
            requestBody = "{}";
        }

        String response = "";
        HttpStatus status = HttpStatus.OK;

        try {
            // Validate request body is valid JSON
            if (!isValidJson(requestBody)) {
                response = gson.toJson(new GeneralMsg("Error, proceso invalido."));
                status = HttpStatus.BAD_REQUEST;
                return buildResponse(response, format, status);
            }

            // Parse authorization header
            String authToken = parseAuthorizationHeader(authorization);
            boolean tokenRequired = isTokenRequired(method);

            // Validate token if required
            if (tokenRequired && (authToken == null || authToken.isEmpty())) {
                response = gson.toJson(new GeneralMsg("Error de autenticacion"));
                status = HttpStatus.UNAUTHORIZED;
                return buildResponse(response, format, status);
            }

            // Route to appropriate handler method
            if (method != null) {
                switch (method.toLowerCase()) {
                    case "login":
                        response = handleLogin(requestBody);
                        status = HttpStatus.OK;
                        break;
                    case "logout":
                        response = handleLogout(requestBody);
                        status = HttpStatus.OK;
                        break;
                    case "recoveruser":
                        response = handleRecoverUser(requestBody);
                        status = HttpStatus.OK;
                        break;
                    case "odata":
                        response = handleOdataCall(requestBody);
                        status = HttpStatus.OK;
                        break;
                    case "sendpush":
                        response = handleSendPush(requestBody);
                        status = HttpStatus.OK;
                        break;
                    case "checkbearercontent2":
                        response = handleCheckBearerContent(authToken);
                        status = HttpStatus.OK;
                        break;
                    case "getfile":
                        response = handleGetFile(requestBody);
                        status = HttpStatus.OK;
                        break;
                    default:
                        // Default to OData call
                        response = handleOdataCall(requestBody);
                        status = HttpStatus.OK;
                        break;
                }
            } else {
                response = gson.toJson(new GeneralMsg("Error, metodo no especificado."));
                status = HttpStatus.BAD_REQUEST;
            }

            return buildResponse(response, format, status);
        } catch (JsonSyntaxException e) {
            LOGGER.error("JSON parsing error", e);
            GeneralMsg errorMsg = new GeneralMsg("Error de procesos durante la ejecucion", e.getMessage());
            response = gson.toJson(errorMsg);
            try {
                return buildResponse(response, format, HttpStatus.BAD_REQUEST);
            } catch (UnsupportedEncodingException uex) {
                LOGGER.error("Encoding error", uex);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            LOGGER.error("Error processing mobile request", e);
            GeneralMsg errorMsg = new GeneralMsg("Error de procesos durante la ejecucion", 
                    e.getMessage() + "; " + (e.getStackTrace().length > 0 ? e.getStackTrace()[0].toString() : ""));
            response = gson.toJson(errorMsg);
            try {
                return buildResponse(response, format, HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (UnsupportedEncodingException uex) {
                LOGGER.error("Encoding error", uex);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }
    }

    /**
     * Handle login request: authenticate user via OData and return JWT token
     * 
     * TODO: Replace odataServiceCallRead() with actual RestTemplate call
     */
    private String handleLogin(String requestBody) throws SQLException, NoSuchAlgorithmException {
        LOGGER.debug("Processing login request");

        OdataStructure request = new OdataStructure();
        request.setMethod("LOGIN");
        request.setContent(requestBody);

        LoginTokenData loginResponse = new LoginTokenData();
        ResponseSAP responseSAP = new ResponseSAP();

        try {
            // TODO: Replace with RestTemplate call to SAP OData service
            // String odataResponse = odataServiceCallRead(gson.toJson(request));
            
            // For now, return placeholder
            responseSAP = new ResponseSAP();
            responseSAP.setRESULTS("{}");

            // Parse response and extract user
            LoginOutData user = gson.fromJson(responseSAP.getRESULTS(), LoginOutData.class);
            if (user != null && user.getUSUARIO() != null && !user.getUSUARIO().isEmpty()) {
                loginResponse.setUser(user);

                // Generate JWT token
                Date expiration = new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS);
                String token = Jwts.builder()
                        .setExpiration(expiration)
                        .setSubject(gson.toJson(user))
                        .signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes(StandardCharsets.ISO_8859_1))
                        .compact();

                loginResponse.setAcces_token(token);
                loginResponse.setResultado(true);
                loginResponse.setExpires(expiration.toString());
                loginResponse.setToken_type("bearer");

                LOGGER.info("Login successful for user: {}", user.getUSUARIO());
                return gson.toJson(loginResponse);
            } else {
                loginResponse.setResultado(false);
                LOGGER.warn("Login failed - invalid credentials");
                return gson.toJson(loginResponse);
            }

        } catch (Exception e) {
            LOGGER.error("Login error", e);
            loginResponse.setResultado(false);
            return gson.toJson(loginResponse);
        }
    }

    /**
     * Handle logout request
     * 
     * TODO: Implement actual logout logic (invalidate token in SAP)
     */
    private String handleLogout(String requestBody) {
        LOGGER.debug("Processing logout request");

        OdataStructure request = new OdataStructure();
        request.setMethod("LOGOUT");
        request.setContent(requestBody);

        ResponseSAP responseSAP = new ResponseSAP();
        try {
            // TODO: Call OData LOGOUT method
            // String odataResponse = odataServiceCallRead(gson.toJson(request));
            
            responseSAP.setRESULTS("Logout successful");
            return gson.toJson(responseSAP);
        } catch (Exception e) {
            LOGGER.error("Logout error", e);
            return gson.toJson(new GeneralMsg("Error durante logout", e.getMessage()));
        }
    }

    /**
     * Handle user recovery request (password reset, account unlock, etc.)
     * 
     * TODO: Implement actual recovery logic
     */
    private String handleRecoverUser(String requestBody) {
        LOGGER.debug("Processing recoverUser request");

        OdataStructure request = new OdataStructure();
        request.setMethod("RECOVER_USER");
        request.setContent(requestBody);

        ResponseSAP responseSAP = new ResponseSAP();
        try {
            // TODO: Call OData RECOVER_USER method
            // String odataResponse = odataServiceCallRead(gson.toJson(request));
            
            responseSAP.setRESULTS("User recovery initiated");
            return gson.toJson(responseSAP);
        } catch (Exception e) {
            LOGGER.error("User recovery error", e);
            return gson.toJson(new GeneralMsg("Error durante recuperación de usuario", e.getMessage()));
        }
    }

    /**
     * Handle OData read request (generic OData proxy)
     * 
     * TODO: Implement RestTemplate call to SAP OData service
     */
    private String handleOdataCall(String requestBody) {
        LOGGER.debug("Processing OData call");

        try {
            // TODO: Parse OData structure from requestBody
            // TODO: Call SAP OData service via RestTemplate
            // TODO: Return OData response
            
            ResponseSAP response = new ResponseSAP();
            response.setRESULTS("{}");
            return gson.toJson(response);
        } catch (Exception e) {
            LOGGER.error("OData call error", e);
            return gson.toJson(new GeneralMsg("Error en llamada OData", e.getMessage()));
        }
    }

    /**
     * Handle push notification request
     * 
     * TODO: Integrate with PushNot.sendPushDirectly()
     */
    private String handleSendPush(String requestBody) {
        LOGGER.debug("Processing push notification");

        try {
            // TODO: Call PushNot.sendPushDirectly(requestBody)
            return gson.toJson(new GeneralMsg("Push notification sent"));
        } catch (Exception e) {
            LOGGER.error("Push notification error", e);
            return gson.toJson(new GeneralMsg("Error enviando notificación", e.getMessage()));
        }
    }

    /**
     * Check Bearer token validity
     * 
     * TODO: Validate JWT signature and expiration
     */
    private String handleCheckBearerContent(String authToken) {
        LOGGER.debug("Checking bearer token validity");

        try {
            if (authToken == null || authToken.isEmpty()) {
                return gson.toJson(new GeneralMsg("Invalid token"));
            }

            // TODO: Parse and validate JWT token
            // TODO: Return token attributes
            
            return gson.toJson(new GeneralMsg("Token is valid"));
        } catch (Exception e) {
            LOGGER.error("Bearer token check error", e);
            return gson.toJson(new GeneralMsg("Invalid token", e.getMessage()));
        }
    }

    /**
     * Handle file download request
     * 
     * TODO: Implement file download from SAP
     */
    private String handleGetFile(String requestBody) {
        LOGGER.debug("Processing file download request");

        try {
            // TODO: Parse file request from requestBody
            // TODO: Fetch file from SAP OData service
            // TODO: Return file as binary or base64
            
            return gson.toJson(new GeneralMsg("File download not yet implemented"));
        } catch (Exception e) {
            LOGGER.error("File download error", e);
            return gson.toJson(new GeneralMsg("Error descargando archivo", e.getMessage()));
        }
    }

    /**
     * Helper: Check if given method requires JWT token authentication
     */
    private boolean isTokenRequired(String method) {
        if (method == null) return true;
        
        String lowerMethod = method.toLowerCase();
        // These methods don't require token
        return !lowerMethod.equals("login") && 
               !lowerMethod.equals("recoveruser") && 
               !lowerMethod.equals("sendpush") && 
               !lowerMethod.equals("getfile");
    }

    /**
     * Helper: Parse "Bearer" token from Authorization header
     */
    private String parseAuthorizationHeader(String authorization) {
        if (authorization == null) return null;
        
        if (authorization.contains("Bearer ")) {
            return authorization.replaceFirst("Bearer ", "");
        }
        return authorization;
    }

    /**
     * Helper: Validate JSON string
     */
    private boolean isValidJson(String json) {
        try {
            gson.fromJson(json, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Helper: Build HTTP response with Content-Language and optional XML conversion
     */
    private ResponseEntity<String> buildResponse(String responseBody, String format, HttpStatus status)
            throws UnsupportedEncodingException {
        
        String response = responseBody;
        String contentType = "application/json; charset=ISO-8859-1";

        // Convert to XML if requested
        if ("xml".equalsIgnoreCase(format)) {
            try {
                org.json.JSONObject jsonObj = new org.json.JSONObject(response);
                response = org.json.XML.toString(jsonObj);
                contentType = "application/xml; charset=ISO-8859-1";
            } catch (Exception e) {
                LOGGER.warn("XML conversion failed, returning JSON");
            }
        }

        // Normalize whitespace
        response = response.replaceAll("\\s++", " ");

        // Build response headers
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", contentType);
        headers.set("Content-Language", "es");

        return ResponseEntity.status(status)
                .headers(headers)
                .body(new String(response.getBytes(), "ISO-8859-1"));
    }
}
