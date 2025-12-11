package com.btp.e2e.servlets.configs;

/**
 * Configuration Destinations for legacy compatibility.
 * In the new Spring Boot environment on BTP CF, these are typically
 * injected from application.properties or environment variables.
 */
public class Destinations {

    public static final String door = "BTP_GLQ_RISE_MOV_MERC"; //PRD: BTP_INSTALACIONES /QAS: BTP_GLQ_RISE_SPC_LG
    public static final String doorPost = "BTP_GLQ_RISE_MOV_MERC"; //PRD: BTP_INSTALACIONES /QAS: BTP_GLQ_RISE_SPC_LG
}
