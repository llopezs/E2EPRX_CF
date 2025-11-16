# S4Comm Migration Guide: NEO to Spring Boot / BTP CF

## Overview
The legacy S4CommMain and S4CommMobile servlets have been partially migrated to a Spring Boot REST controller architecture to run on SAP BTP Cloud Foundry. This document outlines the migration strategy, completed steps, and remaining tasks.

---

## 1. Architecture Changes

### Legacy (NEO):
- **Base Class**: `HttpServlet` (Servlet 3.0 spec)
- **HTTP Pattern**: `doPost()`, `doGet()` methods
- **Data Access**: Direct JNDI DataSource lookup via `InitialContext`
- **External Calls**: SAP Cloud SDK (`HttpDestination`, `HttpClientAccessor`)
- **Size**: 2000+ lines per servlet, 100+ Structures4Jsons dependency classes

### New (Spring Boot on BTP CF):
- **Base Class**: `@RestController` (Spring Framework)
- **HTTP Pattern**: `@PostMapping()`, `@GetMapping()` annotations
- **Data Access**: Spring-managed `DataSource` bean (see `HanaDataSourceConfig.java`)
- **External Calls**: Spring's `RestTemplate` or `WebClient`
- **Organization**: Service classes for business logic, POJOs for data

---

## 2. Completed Migrations

### ✅ Support Classes
- `JavaEmail.java` — Updated to Jakarta Mail with environment-based credentials
- `ListObjectsReport.java` — Full NEO implementation copied
- `Response.java` — Full NEO implementation with PojoPrimary base
- `Scheme.java` — Now reads HANA schema from `HANA_SCHEMA` environment variable
- `Destinations.java` — Static configuration copied for backward compatibility
- `ClaseMensaje.java` — Entity class ported from NEO

### ✅ Dependency Updates
- Added `spring-boot-starter-mail` to pom.xml
- Added `spring-boot-starter-jdbc` for DataSource support
- Added Gson (already available via Spring Boot)
- Updated all `javax.mail` imports → `jakarta.mail`

### ✅ DataSource Configuration
- Created `HanaDataSourceConfig.java` to:
  - Parse `VCAP_SERVICES` for HDI/HANA credentials (CF environment)
  - Fallback to `spring.datasource.*` properties in `application.properties`
  - Return a pooled `DataSource` bean for dependency injection

### ✅ REST Controller Skeleton
- Created `S4CommRestController.java` as the entry point
- `@PostMapping("/api/s4/health")` — Health check endpoint
- `@PostMapping("/api/s4/request")` — Placeholder for main business logic
- Documented TODO items for completing the migration

---

## 3. Remaining Tasks

### Phase 1: Core Business Logic (High Priority)
```
TODO: Migrate handleRequest() from S4CommMain
- Extract the 1000+ line request parsing logic
- Refactor into smaller @Service methods
- Map different request types to appropriate DAO/service calls
- Return JSON responses using Gson
```

### Phase 2: Structures4Jsons Classes (Parallel Effort)
The legacy S4CommMain references ~100 Structures4Jsons POJO classes for request/response serialization. These need to be copied and organized:

**File locations to migrate:**
```
NEO/src/com/btp/e2e/servlets/Structures4Jsons/*.java
→ src/main/java/com/btp/e2e/servlets/Structures4Jsons/
```

**High-Priority Structures** (used by most endpoints):
- `OdataStructure.java` — Base for OData responses
- `BaseOdataServiceGET.java`, `BaseOdataServicePOST.java` — OData wrappers
- `Login.java`, `LoginOutData.java`, `LoginTokenData.java` — Authentication
- `TokenSessionJwt.java` — JWT token handling
- `CreateOrderComercialSAP.java`, `CreateOrderResidencialSAP.java` — Order creation
- `DetalleVenta.java`, `DetalleMediosPago.java` — Sale details and payments

**Bulk Migration Command** (pseudo-code for your reference):
```bash
# Copy all Structures4Jsons files
cp -r NEO/src/com/btp/e2e/servlets/Structures4Jsons/* \
      src/main/java/com/btp/e2e/servlets/Structures4Jsons/
```

### Phase 3: External Service Integration (Medium Priority)
Replace SAP Cloud SDK usage:
```java
// OLD: 
HttpDestination destination = getDestination("ServiceName");
HttpClient client = getHttpClient();

// NEW:
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response = restTemplate.postForEntity(
    "https://odata-service.example.com/api",
    body,
    String.class
);
```

### Phase 4: Error Handling & Logging (Ongoing)
- Wrap business logic in try-catch with proper HTTP status codes
- Use Spring's `@ExceptionHandler` for centralized error responses
- Leverage SLF4J (already available) for structured logging

### Phase 5: Testing & Deployment
- Add unit tests for individual service methods
- Integration test with HANA HDI in a test environment
- Update `manifest.yml` with any new environment variables
- Document API endpoints in a Swagger/OpenAPI spec (optional but recommended)

---

## 4. DAO Adaptation

### Current Status:
- `BaseDao.java` accepts `DataSource` in constructor
- DAOs instantiate with `new LogRecepcionesDAO(dataSource)`

### In Spring Services:
Inject DataSource and instantiate DAOs:
```java
@Service
public class LogRecepcionesService {
    @Autowired
    private DataSource dataSource;

    public LogRecepciones getLogRecepciones(int id) throws SQLException {
        LogRecepcionesDAO dao = new LogRecepcionesDAO(dataSource);
        return dao.read("{\"id\":" + id + "}");
    }
}
```

Or **refactor DAOs to Spring repositories** (higher effort, longer-term benefit):
```java
@Repository
public class LogRecepcionesRepository extends JpaRepository<LogRecepciones, Integer> {
    // ...
}
```

---

## 5. Environment & Deployment

### application.properties
```properties
# Spring Boot basics
spring.application.name=e2eprx
server.servlet.context-path=/

# HANA DataSource (fallback if not using CF VCAP_SERVICES)
spring.datasource.url=jdbc:sap://localhost:30015/?databaseName=HDB
spring.datasource.username=E2EPRX_USR
spring.datasource.password=SecurePassword

# HANA schema (overrides Scheme.java default)
hana.schema=PUBLIC

# Mail configuration (replace with actual SMTP settings)
spring.mail.host=${MAIL_HOST:smtp.example.com}
spring.mail.port=${MAIL_PORT:587}
spring.mail.username=${MAIL_USER:noreply@example.com}
spring.mail.password=${MAIL_PASSWORD:}
```

### manifest.yml (BTP CF Deployment)
```yaml
---
applications:
  - name: e2eprx
    memory: 1024M
    instances: 1
    buildpacks:
      - sap_java_buildpack_jakarta
    env:
      TARGET_RUNTIME: tomcat
      JDK_VERSION: 17
      HANA_SCHEMA: PUBLIC
      MAIL_HOST: smtp.example.com
      MAIL_PORT: 587
      MAIL_USER: noreply@lipigas.cl
    services:
      - hdi-e2eprx         # HDI instance binding
      - logging-service    # Optional: logging service
```

---

## 6. Testing the Migration

### Health Check
```bash
curl -X POST http://localhost:8080/api/s4/health
# Expected response: {"status":"ok", "service":"S4CommRestController"}
```

### Local Build
```bash
mvn clean package -DskipTests
```

### Deploy to BTP CF
```bash
cf push
cf logs e2eprx --recent
```

---

## 7. Known Limitations & Workarounds

| Issue | Legacy (NEO) | New (Spring Boot) | Workaround |
|-------|---------|---------|-----------|
| HttpServlet patterns | Native | Requires adapter | Use `@RestController` + `@PostMapping` |
| JNDI DataSource | InitialContext lookup | Spring bean injection | Use `HanaDataSourceConfig` |
| SAP Cloud SDK | Direct SDK usage | Not included by default | Use Spring's `RestTemplate` + service bindings |
| Servlet context files | web.xml routing | Spring Boot auto-config | Annotation-based endpoints |
| Hard-coded SMTP creds | Embedded in code | Removed for security | Use environment variables |

---

## 8. Next Steps

1. **Immediate (Today)**:
   - [ ] Copy Structures4Jsons classes (bulk operation)
   - [ ] Run `mvn clean package` to verify compilation

2. **Short-term (This week)**:
   - [ ] Extract core business logic from S4CommMain.handleRequest()
   - [ ] Implement service methods in S4CommRestController
   - [ ] Write basic unit tests

3. **Medium-term (Next 1-2 weeks)**:
   - [ ] Test with HANA HDI on a CF development space
   - [ ] Add external service integration (OData calls)
   - [ ] Document public API endpoints

4. **Long-term (Future)**:
   - [ ] Consider refactoring DAOs to Spring Data JPA
   - [ ] Add comprehensive error handling and validation
   - [ ] Set up CI/CD pipeline for automated deployment

---

## 9. Support & Troubleshooting

### Common Issues

**Q: "Cannot find symbol: class [StructureName]"**
- A: Check if the Structures4Jsons class was copied. Run bulk copy command from Phase 2.

**Q: "DataSource bean not found"**
- A: Ensure `HanaDataSourceConfig` is in a scanned package. Check Spring Boot startup logs.

**Q: "VCAP_SERVICES parsing failed"**
- A: Verify HDI service is bound in CF. Check `cf services` and service binding names.

**Q: "Jakarta Mail initialization error"**
- A: Verify `spring-boot-starter-mail` is in pom.xml. Check mail config in application.properties.

### Useful Commands
```bash
# Check Spring beans
cf ssh e2eprx -c "echo 'GET /api/s4/health HTTP/1.1' | nc localhost 8080"

# View CF logs
cf logs e2eprx --recent

# List environment
cf env e2eprx

# Re-bind HDI service
cf unbind-service e2eprx hdi-e2eprx
cf bind-service e2eprx hdi-e2eprx
cf restage e2eprx
```

---

## 10. Reference

- **Original NEO Files**: `NEO/src/com/btp/e2e/servlets/S4Comm*.java`
- **Spring Boot Guide**: https://spring.io/guides/gs/rest-service/
- **BTP CF Deployment**: https://help.sap.com/docs/btp/sap-btp-cloud-foundry-environment
- **HANA JDBC**: https://help.sap.com/docs/SAP_HANA_CLOUD/0eec0d68141541d4b51d3517d411b9f2/
- **Gson Documentation**: https://www.gson.org/

---

*Last Updated: 2025-11-15*
*Migration Status: In Progress (Foundation Complete, Business Logic Pending)*
