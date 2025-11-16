package com.lipigas.e2eprx.config;

import java.util.Map;

import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
@Profile("cf")
public class HanaDataSourceConfig {

    /**
     * Create a DataSource bean.
     * Priority:
     *  1) If running on Cloud Foundry and VCAP_SERVICES contains a HANA/HDI binding, build DataSource from those credentials.
     *  2) Otherwise fallback to normal spring.datasource.* properties (DataSourceProperties).
     *
     * Note: The SAP HANA JDBC driver is typically provided by SAP or added as a private dependency. The driver class used here is
     * 'com.sap.db.jdbc.Driver' which is common for the HANA client (ngdbc). If you install a different driver, update driverClassName.
     */
    @Bean
    public DataSource dataSource(DataSourceProperties dsp, Environment env) {
        String vcap = System.getenv("VCAP_SERVICES");
        if (vcap != null && !vcap.isBlank()) {
            try {
                Gson gson = new Gson();
                JsonObject root = gson.fromJson(vcap, JsonObject.class);
                // iterate service types
                for (Map.Entry<String, JsonElement> entry : root.entrySet()) {
                    JsonArray services = entry.getValue().getAsJsonArray();
                    for (JsonElement el : services) {
                        JsonObject svc = el.getAsJsonObject();
                        JsonObject credentials = svc.has("credentials") ? svc.getAsJsonObject("credentials") : null;
                        if (credentials == null)
                            continue;

                        // try to detect HANA/HDI-like credentials by presence of some keys
                        String url = null;
                        String user = null;
                        String password = null;

                        if (credentials.has("jdbcurl")) {
                            url = credentials.get("jdbcurl").getAsString();
                        } else if (credentials.has("jdbcUrl")) {
                            url = credentials.get("jdbcUrl").getAsString();
                        } else if (credentials.has("url")) {
                            url = credentials.get("url").getAsString();
                        } else if (credentials.has("jdbcurl_tunnel")) {
                            url = credentials.get("jdbcurl_tunnel").getAsString();
                        }

                        if (credentials.has("user")) {
                            user = credentials.get("user").getAsString();
                        } else if (credentials.has("username")) {
                            user = credentials.get("username").getAsString();
                        }

                        if (credentials.has("password")) {
                            password = credentials.get("password").getAsString();
                        }

                        // if we found a plausible URL and user, try to build a DataSource
                        if (url != null && user != null) {
                            // Use DataSourceProperties as a builder but override values from VCAP
                            DataSourceProperties props = new DataSourceProperties();
                            props.setUrl(url);
                            props.setUsername(user);
                            props.setPassword(password == null ? "" : password);
                            // common HANA driver class; update if your environment provides a different driver
                            props.setDriverClassName("com.sap.db.jdbc.Driver");
                            return props.initializeDataSourceBuilder().build();
                        }

                        // Some HDI bindings provide host/port/database/user/password
                        if (credentials.has("host") && credentials.has("port") && credentials.has("database") && credentials.has("user")) {
                            String host = credentials.get("host").getAsString();
                            String port = credentials.get("port").getAsString();
                            String database = credentials.get("database").getAsString();
                            user = credentials.get("user").getAsString();
                            password = credentials.has("password") ? credentials.get("password").getAsString() : null;
                            // Construct a HANA JDBC URL. Adjust if your binding expects a different format.
                            url = String.format("jdbc:sap://%s:%s/?databaseName=%s", host, port, database);
                            DataSourceProperties props = new DataSourceProperties();
                            props.setUrl(url);
                            props.setUsername(user);
                            props.setPassword(password == null ? "" : password);
                            props.setDriverClassName("com.sap.db.jdbc.Driver");
                            return props.initializeDataSourceBuilder().build();
                        }
                    }
                }
            } catch (Exception ex) {
                // If parsing fails, fall through to default properties
                System.err.println("Failed to parse VCAP_SERVICES for HANA credentials: " + ex.getMessage());
            }
        }

        // Fallback to application.properties spring.datasource.* settings
        return dsp.initializeDataSourceBuilder().build();
    }
}
