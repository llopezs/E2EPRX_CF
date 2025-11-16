package com.btp.e2e.servlets.configs;

/**
 * Legacy Scheme configuration for schema name resolution.
 * 
 * In the legacy NEO project, this was set to match the database schema (e.g., "PITS", "E2E_DEV").
 * For HDI/HANA on BTP CF, the schema is typically determined by the service binding or
 * can be read from environment variables.
 * 
 * Current default: "PUBLIC" (standard HANA HDI schema)
 * To override: set environment variable HANA_SCHEMA or update application.properties
 */
public class Scheme {
    // Default schema for HANA HDI; can be overridden at runtime
    public static String now = System.getenv("HANA_SCHEMA") != null ? System.getenv("HANA_SCHEMA") : "PUBLIC";
}
