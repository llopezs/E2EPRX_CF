# E2EPRX Quick Reference - Spring Boot / BTP CF

## Build & Run

```bash
# Clean build
mvn clean package -DskipTests

# Run locally
mvn spring-boot:run

# Run tests
mvn test
```

## Key Endpoints

| Endpoint | Method | Purpose | Status |
|----------|--------|---------|--------|
| `/api/helloWorld` | GET | Health check | ✅ Working |
| `/api/s4/health` | POST | S4Comm health | ✅ Ready |
| `/api/s4/request` | POST | Main handler | 🔄 Placeholder |
| `/mobile/*` | POST | Mobile app | ⏳ To implement |

## Project Structure

```
src/main/java/
├── com/btp/e2e/          (NEO legacy classes)
│   ├── dao/              (DAOs)
│   ├── entities/         (POJOs)
│   ├── servlets/         (Servlets → REST adapters)
│   └── implementations/  (PojoPrimary, interfaces)
└── com/lipigas/e2eprx/   (Spring Boot app)
    ├── E2eprxApplication.java
    ├── MainController.java
    ├── WebConfig.java
    └── config/HanaDataSourceConfig.java
```

## Configuration Files

| File | Purpose |
|------|---------|
| `pom.xml` | Maven dependencies |
| `application.properties` | App config (JDBC, mail, etc.) |
| `manifest.yml` | BTP CF deployment config |
| `S4COMM_MIGRATION_GUIDE.md` | Detailed migration guide |
| `MIGRATION_SUMMARY.md` | Current status & next steps |

## Common Tasks

### Add a New Endpoint
```java
@PostMapping("/api/newEndpoint")
public String newEndpoint(@RequestBody String body) {
    // Your logic here
    return "{\"result\":\"success\"}";
}
```

### Inject DataSource into Service
```java
@Service
public class MyService {
    @Autowired
    private DataSource dataSource;
    
    public void someMethod() {
        MyDAO dao = new MyDAO(dataSource);
        // Use DAO
    }
}
```

### Send Email
```java
try {
    JavaEmail.Send("Subject", "Body");
} catch (Exception e) {
    LOGGER.error("Mail error", e);
}
```

### Database Access
```java
LogRecepcionesDAO dao = new LogRecepcionesDAO(dataSource);
LogRecepciones log = dao.read("{\"id\":1}");
```

## Environment Variables (BTP CF)

| Variable | Default | Purpose |
|----------|---------|---------|
| `HANA_SCHEMA` | PUBLIC | Database schema |
| `MAIL_HOST` | smtp.example.com | Mail server |
| `MAIL_PORT` | 587 | Mail port |
| `MAIL_USER` | noreply@example.com | Mail from address |
| `MAIL_PASSWORD` | (empty) | Mail password |

## Deploy to BTP CF

```bash
# Install CF CLI (if needed)
# https://docs.cloudfoundry.org/cf-cli/install-go-cli.html

# Login
cf login -a api.cf.us10-001.hana.ondemand.com

# Create HDI instance (one-time)
cf create-service hana hdi-shared hdi-e2eprx

# Deploy
cf push

# View logs
cf logs e2eprx --recent

# SSH into app
cf ssh e2eprx
```

## Troubleshooting

### Build Error: "Cannot find symbol"
→ Check `pom.xml` dependencies, run `mvn clean install`

### DataSource error
→ Check `HanaDataSourceConfig`, ensure `VCAP_SERVICES` is set (CF) or `spring.datasource.*` (local)

### Mail error  
→ Check `application.properties` mail settings, verify SMTP server is accessible

### Port conflict
→ Change `server.port=8081` in `application.properties`

### Cannot bind service on CF
→ Run `cf services`, ensure HDI instance is created, rebind: `cf bind-service e2eprx hdi-e2eprx`

## Useful URLs

- **Spring Boot Docs**: https://spring.io/projects/spring-boot
- **BTP CF Guide**: https://help.sap.com/docs/btp/sap-btp-cloud-foundry-environment
- **HANA JDBC**: https://help.sap.com/docs/SAP_HANA_CLOUD/0eec0d68141541d4b51d3517d411b9f2/
- **Gson**: https://www.gson.org/

## Git Commands (if using version control)

```bash
git add .
git commit -m "Migration: NEO to Spring Boot on BTP CF"
git push origin main
```

## IDE Tips (Visual Studio Code / IntelliJ)

- Install **Extension Pack for Java** (VSCode) or use IntelliJ
- Ctrl+Shift+O → Organize imports
- Alt+Enter → Quick fixes
- Ctrl+/ → Comment toggle
- Shift+Alt+F → Format code

## Performance Notes

- Build time: ~2-3 seconds (local), ~1-2 min (CF)
- Startup: ~5-10 seconds (CF), ~3-5 seconds (local)
- Typical request latency: 100-500ms (depends on DB)

## Contact / Support

For issues:
1. Check logs: `mvn clean package -X` (verbose)
2. Review `S4COMM_MIGRATION_GUIDE.md`
3. Check Spring Boot startup errors
4. Verify database connectivity

---

**Last Updated**: 2025-11-15  
**Status**: ✅ Foundation Complete — Ready for Development
