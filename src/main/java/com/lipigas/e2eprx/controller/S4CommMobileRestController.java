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
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import java.net.URLEncoder;
import org.apache.http.client.utils.URIBuilder;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import static com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor.getDestination;
import static com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor.getHttpClient;
import com.btp.e2e.servlets.Structures4Jsons.BaseOdataServiceGET;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

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
    @Value("${jwt.secret:wFFft7Yrd9SxjMMwlt0x9ZRI8e6nWgrmA18V9ewBiG6kXZIDz32}")
    private String JWT_SECRET;
    private static final long JWT_EXPIRATION_MS = 1000 * 60 * 60 * 12; // 12 hours

    // OData configuration (CF)
    @Value("${sap.destination.name:BTP_GLQ_RISE_SPC_LG}")
    private String destinationName;
    @Value("${sap.gw.base-url:}")
    private String gwBaseUrl;
    private static final String SERVICE_PATH = "/sap/opu/odata/SAP/ZE2E_SRV";
    private static final String COMM_SET = "/commSet";
    private static final String FILES_SET = "/FilesSet";
    private static final String QUERY_PRX = "?$format=json&$filter=ClassPrx eq '@classprx@' and Userid eq '@userid@' and Method eq '@method@' and Content eq '@content@' and File eq '@file@'";
    private static final String QUERY_FILE = "(@guid@)/$value";

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
            String odataResponse = odataServiceCallRead(gson.toJson(request));
            LOGGER.debug("OData LOGIN response: {}", trimForLog(odataResponse));
            responseSAP = gson.fromJson(odataResponse, ResponseSAP.class);

            String userPayload = responseSAP.getRESULTS();
            try {
                ResponseSAP inner = gson.fromJson(userPayload, ResponseSAP.class);
                if (inner != null && inner.getRESULTS() != null && !inner.getRESULTS().isEmpty()) {
                    userPayload = inner.getRESULTS();
                    if (responseSAP.getMSGS() == null || responseSAP.getMSGS().isEmpty()) {
                        responseSAP.setMSGS(inner.getMSGS());
                    }
                }
            } catch (Exception ignore) {}

            LoginOutData user = gson.fromJson(userPayload, LoginOutData.class);
            if (user != null && user.getUSUARIO() != null && !user.getUSUARIO().isEmpty()) {
                loginResponse.setUser(user);

                // Generate JWT token
                Date expiration = new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS);
                byte[] keyBytes = JWT_SECRET.getBytes(StandardCharsets.ISO_8859_1);
                SecretKey key = Keys.hmacShaKeyFor(keyBytes);
                String token = Jwts.builder()
                        .setExpiration(expiration)
                        .setSubject(gson.toJson(user))
                        .signWith(key, SignatureAlgorithm.HS512)
                        .compact();

                loginResponse.setAcces_token(token);
                loginResponse.setResultado(true);
                loginResponse.setExpires(expiration.toString());
                loginResponse.setToken_type("bearer");

                try {
                    loginResponse.setMensajesToken(saveTokenInSAP(user.getUSUARIO(), user.getEMAIL(), token));
                } catch (Exception eToken) {
                    LOGGER.warn("SET_TOKEN failed", eToken);
                }

                LOGGER.info("Login successful for user: {}", user.getUSUARIO());
                return gson.toJson(loginResponse);
            } else {
                loginResponse.setResultado(false);
                LOGGER.warn("Login failed - invalid credentials; RESULTS={}, MSGS={}", responseSAP.getRESULTS(), responseSAP.getMSGS());
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
            String odataResponse = odataServiceCallRead(gson.toJson(request));
            return odataResponse;
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
            String odataResponse = odataServiceCallRead(gson.toJson(request));
            return odataResponse;
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
            OdataStructure req = new OdataStructure();
            req.setMethod("READ");
            req.setContent(requestBody);
            return odataServiceCallRead(gson.toJson(req));
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

            SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.ISO_8859_1));
            io.jsonwebtoken.Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(authToken)
                    .getBody();
            String subject = Optional.ofNullable(claims.getSubject()).orElse("{}");
            return subject;
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
            OdataStructure req = new OdataStructure();
            req.setMethod("GET_FILE");
            req.setContent(requestBody);
            return odataServiceCallReadFile(gson.toJson(req));
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

    // ===== CF OData implementations =====
    private String odataServiceCallRead(String json) throws Exception {
        OdataStructure osIncoming = gson.fromJson(json, OdataStructure.class);
        String userid = (osIncoming.getUserid() == null || osIncoming.getUserid().isEmpty()) ? "AUTOMATICO" : osIncoming.getUserid();
        String contentMin = compactJson(osIncoming.getContent());
        String filter = String.format("ClassPrx eq '%s' and Userid eq '%s' and Method eq '%s' and Content eq '%s' and File eq '%s'",
                escapeSingleQuotes(osIncoming.getClassPrx()),
                escapeSingleQuotes(userid),
                escapeSingleQuotes(osIncoming.getMethod()),
                escapeSingleQuotes(contentMin),
                escapeSingleQuotes(osIncoming.getFile()));

        String requestUrl = null;
        HttpClient client = null;
        LOGGER.info("Destination name configured: {}", destinationName);
        String vcap = System.getenv("VCAP_SERVICES");
        LOGGER.debug("VCAP_SERVICES contains destination: {}", vcap != null && vcap.contains("\"destination\""));
        try {
            HttpDestination httpDestination = getDestination(destinationName).asHttp();
            client = getHttpClient(httpDestination);
            URIBuilder ub = new URIBuilder(httpDestination.getUri() + SERVICE_PATH + COMM_SET);
            ub.addParameter("$format", "json");
            ub.addParameter("$filter", filter);
            requestUrl = ub.build().toString();
            LOGGER.info("OData READ via destination: name={}, url={}", destinationName, requestUrl);
        } catch (Exception ex) {
            LOGGER.error("Destination access failed for {}", destinationName, ex);
            if (gwBaseUrl != null && !gwBaseUrl.isBlank()) {
                client = org.apache.http.impl.client.HttpClients.createDefault();
                URIBuilder ub = new URIBuilder(gwBaseUrl + SERVICE_PATH + COMM_SET);
                ub.addParameter("$format", "json");
                ub.addParameter("$filter", filter);
                requestUrl = ub.build().toString();
                LOGGER.warn("Using gwBaseUrl fallback: {}", requestUrl);
            } else {
                ResponseSAP response = new ResponseSAP();
                response.setRESULTS("{}");
                LOGGER.warn("No destination and no fallback configured");
                return gson.toJson(response);
            }
        }
        HttpGet httpGet = new HttpGet(requestUrl);
        HttpResponse resp = client.execute(httpGet);
        int statusCode = resp.getStatusLine().getStatusCode();
        LOGGER.info("OData HTTP status: {}", statusCode);
        HttpEntity entity = resp.getEntity();
        java.io.InputStreamReader reader = new java.io.InputStreamReader(entity.getContent());
        java.io.BufferedReader br = new java.io.BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String body = sb.toString();
        LOGGER.debug("OData body: {}", trimForLog(body));

        try {
            String[] parts = body.split("]}}\s*");
            if (parts.length > 0) body = parts[0] + "]}}";
            BaseOdataServiceGET oGet = gson.fromJson(body, BaseOdataServiceGET.class);
            String concatenateAllJsons = "";
            for (OdataStructure r : oGet.getD().getResults()) {
                concatenateAllJsons += r.getContent();
            }
            ResponseSAP response = new ResponseSAP();
            response.setRESULTS(concatenateAllJsons);
            LOGGER.debug("OData parsed RESULTS: {}", trimForLog(concatenateAllJsons));
            return gson.toJson(response);
        } catch (Exception ex) {
            LOGGER.error("OData parse error", ex);
            return body;
        }
    }

    private String odataServiceCallReadFile(String json) throws Exception {
        OdataStructure osIncoming = gson.fromJson(json, OdataStructure.class);
        String lvQuery = QUERY_FILE.replaceAll("@guid@", safe(osIncoming.getContent()));
        String requestUrl = null;
        HttpClient client = null;
        LOGGER.info("Destination name configured: {}", destinationName);
        String vcap2 = System.getenv("VCAP_SERVICES");
        LOGGER.debug("VCAP_SERVICES contains destination: {}", vcap2 != null && vcap2.contains("\"destination\""));
        try {
            HttpDestination httpDestination = getDestination(destinationName).asHttp();
            client = getHttpClient(httpDestination);
            requestUrl = httpDestination.getUri() + SERVICE_PATH + FILES_SET + lvQuery;
            LOGGER.info("OData FILE via destination: name={}, url={}", destinationName, requestUrl);
        } catch (Exception ex) {
            LOGGER.error("Destination access failed for {}", destinationName, ex);
            if (gwBaseUrl != null && !gwBaseUrl.isBlank()) {
                requestUrl = gwBaseUrl + SERVICE_PATH + FILES_SET + lvQuery;
                client = org.apache.http.impl.client.HttpClients.createDefault();
                LOGGER.warn("Using gwBaseUrl fallback: {}", requestUrl);
            } else {
                LOGGER.warn("No destination and no fallback configured for file");
                return gson.toJson(new GeneralMsg("Destination/Gateway no disponible"));
            }
        }
        HttpGet httpGet = new HttpGet(requestUrl);
        HttpResponse resp = client.execute(httpGet);
        int statusCode = resp.getStatusLine().getStatusCode();
        LOGGER.info("OData FILE HTTP status: {}", statusCode);
        HttpEntity entity = resp.getEntity();
        java.io.InputStreamReader reader = new java.io.InputStreamReader(entity.getContent());
        java.io.BufferedReader br = new java.io.BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        LOGGER.debug("OData FILE body: {}", trimForLog(sb.toString()));
        return sb.toString();
    }

    private ArrayList<String> saveTokenInSAP(String usuario, String email, String token) throws Exception {
        UserTokenSave uts = new UserTokenSave(usuario, email, token);
        OdataStructure osIncoming = new OdataStructure();
        osIncoming.setMethod("SET_TOKEN");
        osIncoming.setContent(gson.toJson(uts));
        String response = odataServiceCallRead(gson.toJson(osIncoming));
        ResponseSAP rsp = gson.fromJson(response, ResponseSAP.class);
        return rsp.getMSGS();
    }

    private boolean checkTokenInSAP(String usuario, String email, String token) throws Exception {
        UserTokenSave uts = new UserTokenSave(usuario, email, token);
        OdataStructure osIncoming = new OdataStructure();
        osIncoming.setMethod("CHECK_TOKEN");
        osIncoming.setContent(gson.toJson(uts));
        String response = odataServiceCallRead(gson.toJson(osIncoming));
        ResponseSAP rsp = gson.fromJson(response, ResponseSAP.class);
        return rsp.getRESULTS() != null && rsp.getRESULTS().contains("1");
    }

    private String safe(String s) {
        if (s == null) return "";
        return s.replace("'", "\\'");
    }

    private String encodeForQuery(String s) {
        if (s == null) return "";
        String value = s;
        try {
            Object obj = gson.fromJson(s, Object.class);
            value = gson.toJson(obj);
        } catch (Exception e) {
        }
        try {
            String enc = URLEncoder.encode(value, java.nio.charset.StandardCharsets.UTF_8.name());
            return enc.replace("+", "%20");
        } catch (Exception e) {
            return value;
        }
    }

    private String compactJson(String s) {
        if (s == null) return "";
        try {
            Object obj = gson.fromJson(s, Object.class);
            return gson.toJson(obj);
        } catch (Exception e) {
            return s.replaceAll("\s+", " ").trim();
        }
    }

    private String escapeSingleQuotes(String s) {
        if (s == null) return "";
        return s.replace("'", "''");
    }

    private String trimForLog(String s) {
        if (s == null) return "null";
        int max = 300;
        return s.length() <= max ? s : s.substring(0, max) + "...";
    }
}
