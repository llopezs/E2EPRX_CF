# Mobile Endpoint Migration Guide

**Status**: ✅ Migrated from `/mobile/` servlet to `/api/mobile` REST controller  
**Date**: November 15, 2025  
**Spring Boot Version**: 3.5.7  
**Java**: 17

---

## Overview

The legacy `S4CommMobile` servlet (NEO, `@WebServlet("/mobile/")`) has been successfully migrated to a modern Spring Boot REST controller (`@RequestMapping("/api/mobile")`).

### Key Changes

| Aspect | Legacy (Servlet) | New (Spring Boot) |
|--------|------------------|-------------------|
| **Endpoint Path** | `/mobile/` | `/api/mobile` |
| **Base Class** | `HttpServlet` | `@RestController` |
| **Request Mapping** | `doPost()` / `doGet()` | `@PostMapping` |
| **Authentication** | Bearer token in header | Spring `@RequestHeader` annotation |
| **Response Format** | Custom format (JSON/XML) | `ResponseEntity<String>` with HttpHeaders |
| **JWT Signing** | jjwt library (0.9.1) | Same, imported via Maven |
| **OData Calls** | SAP Cloud SDK (deprecated) | TODO: RestTemplate implementation |

---

## Endpoint Reference

### Base URL
```
POST /api/mobile
```

### Request Headers
| Header | Required | Example | Notes |
|--------|----------|---------|-------|
| `method` | ✅ Yes | `login`, `logout`, `odata` | HTTP header (not URL param) |
| `Authorization` | ⏳ Conditional | `Bearer eyJhbGciOi...` | Required for authenticated methods |
| `VERSION` | ❌ No | `1.0.5` | App version |
| `OS_VERSION` | ❌ No | `14.5` | Operating system version |
| `MODEL` | ❌ No | `iPhone 13` | Device model |
| `Content-Type` | ✅ Yes | `application/json` | Always JSON |

### Query Parameters
| Parameter | Required | Values | Notes |
|-----------|----------|--------|-------|
| `format` | ❌ No | `json`, `xml` | Response format (default: `json`) |

### Available Methods

#### 1. **login** — User Authentication
```bash
curl -X POST http://localhost:8080/api/mobile \
  -H "method: login" \
  -H "Content-Type: application/json" \
  -d '{
    "USUARIO": "user123",
    "PASSWORD": "pass123"
  }'
```

**Response** (success):
```json
{
  "resultado": true,
  "user": {
    "USUARIO": "user123",
    "EMAIL": "user@example.com",
    "PERFIL": "distributor",
    "ACTIVO": "1"
  },
  "acces_token": "eyJhbGciOiJIUzUxMiJ9...",
  "expires": "2025-11-15 14:30:00",
  "token_type": "bearer",
  "mensaje": ""
}
```

**Response** (failure):
```json
{
  "resultado": false,
  "user": {
    "USUARIO": "",
    "EMAIL": "",
    "PERFIL": "",
    "ACTIVO": ""
  },
  "acces_token": "",
  "expires": "",
  "token_type": "bearer",
  "mensaje": "Authentication failed"
}
```

#### 2. **logout** — End User Session
```bash
curl -X POST http://localhost:8080/api/mobile \
  -H "method: logout" \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{}'
```

#### 3. **recoverUser** — Password Reset / Account Recovery
```bash
curl -X POST http://localhost:8080/api/mobile \
  -H "method: recoverUser" \
  -H "Content-Type: application/json" \
  -d '{
    "USUARIO": "user123"
  }'
```

#### 4. **odata** — Generic OData Proxy Call
```bash
curl -X POST http://localhost:8080/api/mobile \
  -H "method: odata" \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "Method": "GET",
    "Content": "/path/to/resource"
  }'
```

#### 5. **sendPush** — Send Push Notification
```bash
curl -X POST http://localhost:8080/api/mobile \
  -H "method: sendPush" \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "user123",
    "message": "Your order is ready",
    "title": "Order Notification"
  }'
```

#### 6. **checkBearerContent2** — Validate JWT Token
```bash
curl -X POST http://localhost:8080/api/mobile \
  -H "method: checkBearerContent2" \
  -H "Authorization: Bearer <token>" \
  -d ''
```

#### 7. **getFile** — Download File
```bash
curl -X POST http://localhost:8080/api/mobile \
  -H "method: getFile" \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "fileId": "DOC123"
  }'
```

---

## JWT Token Details

### Token Generation
- **Algorithm**: HS512 (HMAC with SHA-512)
- **Secret**: `wFFft7Yrd9SxjMMwlt0x9ZRI8e6nWgrmA18V9ewBiG6kXZIDz32` (TODO: Move to environment variable)
- **Expiration**: 12 hours from creation
- **Subject**: Serialized `LoginOutData` JSON object

### Token Validation (TODO)
- Parse JWT signature using HS512 secret
- Validate expiration timestamp
- Extract user data from subject
- Check token against SAP backend (method: `CHECK_TOKEN`)

### Example Decoded Token Payload
```json
{
  "iat": 1731680400,
  "exp": 1731723600,
  "sub": "{\"USUARIO\":\"user123\",\"EMAIL\":\"user@example.com\",\"PERFIL\":\"distributor\",\"ACTIVO\":\"1\"}"
}
```

---

## Response Format

### Success Response (JSON)
```json
{
  "resultado": true,
  "user": { ... },
  "acces_token": "...",
  "mensaje": ""
}
```

### Error Response (JSON)
```json
{
  "msg": "Error de autenticacion",
  "c_technical_msg": "Connection timeout on OData service"
}
```

### XML Response (if ?format=xml)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<root>
  <resultado>true</resultado>
  <user>
    <USUARIO>user123</USUARIO>
    <EMAIL>user@example.com</EMAIL>
  </user>
</root>
```

### HTTP Status Codes
| Status | Meaning | When |
|--------|---------|------|
| **200 OK** | Request processed | All methods (default) |
| **400 Bad Request** | Invalid JSON or missing method | Malformed request body |
| **401 Unauthorized** | Missing/invalid token | Token-required method without token |
| **500 Server Error** | Unexpected error | Exception during processing |

---

## Implementation Status

### ✅ Completed
- [x] REST controller created (`S4CommMobileRestController.java`)
- [x] Support classes created (`ResponseSAP`, `LoginTokenData`, `UserTokenSave`, `LoginOutData`, `GeneralMsg`)
- [x] JWT token generation (HS512, 12-hour expiration)
- [x] Authorization header parsing (Bearer token extraction)
- [x] Request method routing (7 methods)
- [x] JSON request validation
- [x] XML response conversion
- [x] Error handling with proper HTTP status codes
- [x] Response headers (Content-Language: es, Content-Type)
- [x] Maven dependencies (`jjwt`, `org.json`)

### 🔄 TODO (Phase 2)
- [ ] **OData Service Integration**
  - Implement `RestTemplate` calls to SAP OData service
  - Replace placeholder `odataServiceCallRead()` calls
  - Map legacy SAP Cloud SDK HTTP destination setup to RestTemplate configuration

- [ ] **User Authentication Logic**
  - Integrate with `UsuarioDAO.selectUserByRut()`
  - Verify password using `Usuario.authClave()`
  - Log authentication attempts

- [ ] **Token Validation**
  - Implement `TokenSessionJwt` parsing from JWT
  - Validate token expiration
  - Check token validity against SAP (method: `CHECK_TOKEN`)

- [ ] **Push Notification Support**
  - Integrate with `PushNot.sendPushDirectly()`
  - Implement message delivery to mobile apps

- [ ] **File Download Support**
  - Implement file retrieval from SAP
  - Return binary or base64-encoded files

- [ ] **JWT Secret Management**
  - Move hardcoded secret to environment variable
  - Support multiple secrets for key rotation

- [ ] **Unit Tests**
  - Test login success/failure scenarios
  - Test token validation
  - Test OData proxy calls
  - Test error handling

- [ ] **Integration Tests**
  - Test with real HANA instance
  - Test OData service connectivity
  - Test end-to-end flows

---

## Configuration

### Application Properties
```properties
# Mobile endpoint settings (add to application.properties if needed)
mobile.jwt.secret=${MOBILE_JWT_SECRET:wFFft7Yrd9SxjMMwlt0x9ZRI8e6nWgrmA18V9ewBiG6kXZIDz32}
mobile.jwt.expiration-ms=43200000  # 12 hours
mobile.odata.timeout=30000  # 30 seconds
```

### Environment Variables
```bash
# For production, set JWT secret via environment
export MOBILE_JWT_SECRET=your-production-secret-here
```

---

## Legacy to Modern Mapping

### Request Handling
**Legacy**:
```java
@WebServlet("/mobile/")
public class S4CommMobile extends S4CommMain {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        handleRequest(request, response);
    }
    
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getHeader("method");
        switch(method) {
            case "login":
                objectToReturn = login3(request, response, body, this, version);
                break;
            // ...
        }
    }
}
```

**Modern**:
```java
@RestController
@RequestMapping("/api/mobile")
public class S4CommMobileRestController {
    @PostMapping
    public ResponseEntity<String> handleMobileRequest(
        @RequestHeader(value = "method") String method,
        @RequestBody String requestBody) {
        switch(method.toLowerCase()) {
            case "login":
                response = handleLogin(requestBody);
                break;
            // ...
        }
        return buildResponse(response, format, status);
    }
}
```

### OData Calls
**Legacy**:
```java
String odataResponse = this.odataServiceCallRead(gson.toJson(request), response);
```

**Modern** (TODO):
```java
// Use RestTemplate
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> odataResponse = restTemplate.postForEntity(
    "https://sap-backend/odata/...",
    requestBody,
    String.class
);
```

---

## Common Issues & Solutions

### Issue: JWT Signature Verification Fails
**Cause**: Token secret mismatch  
**Solution**: Ensure `JWT_SECRET` constant matches production secret

### Issue: OData Service Timeout
**Cause**: RestTemplate not configured  
**Solution**: Implement actual RestTemplate call in `handleOdataCall()` with timeout settings

### Issue: CORS Errors in Mobile Client
**Cause**: Spring Boot CORS not configured  
**Solution**: Add `@CrossOrigin` to controller or configure global CORS

### Issue: Character Encoding Issues (UTF-8 vs ISO-8859-1)
**Cause**: Mixed encodings in legacy code  
**Solution**: Maintain ISO-8859-1 for response (see `buildResponse()` method)

---

## Testing Checklist

- [ ] Login with valid credentials
- [ ] Login with invalid credentials (401)
- [ ] Logout invalidates token
- [ ] Bearer token validation
- [ ] OData proxy call with authentication
- [ ] XML response conversion
- [ ] Push notification delivery
- [ ] File download functionality
- [ ] JWT expiration (after 12 hours)
- [ ] Error handling with proper status codes
- [ ] Special characters in request body
- [ ] Unicode support (Spanish characters)

---

## Performance Considerations

1. **Token Expiration**: 12 hours is relatively long; consider shorter expiration for production
2. **OData Service Calls**: Will need connection pooling via RestTemplate configuration
3. **Caching**: Consider caching user profiles to reduce OData calls
4. **Logging**: Currently logs all requests; implement request ID tracking for debugging

---

## Security Notes

⚠️ **IMPORTANT**: The hardcoded JWT secret should be moved to an environment variable or secure configuration service before production deployment.

```java
// TODO: Replace hardcoded secret
private static final String JWT_SECRET = System.getenv("MOBILE_JWT_SECRET");
```

---

## Next Steps

1. **Short-term** (1-2 days): Implement OData RestTemplate calls
2. **Medium-term** (3-5 days): Add PushNot integration and file download support
3. **Long-term** (1 week): Write comprehensive unit and integration tests, deploy to BTP CF

---

**Document prepared**: November 15, 2025  
**Controller file**: `src/main/java/com/lipigas/e2eprx/controller/S4CommMobileRestController.java`  
**Support classes**: `src/main/java/com/btp/e2e/servlets/Structures4Jsons/` and `src/main/java/com/btp/e2e/servlets/GeneralMsg.java`
