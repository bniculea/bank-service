package com.quarkus.resources;

import com.quarkus.config.BankSupportConfig;
import com.quarkus.config.BankSupportConfigMapping;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Path("/")
public class BankResource {

    @ConfigProperty(name="bank.name", defaultValue = "Bank of Poor Fellas")
    String name;

    @ConfigProperty(name = "app.mobileBanking")
    Optional<Boolean> mobileBanking;

    @ConfigProperties(prefix = "bank-support")
    BankSupportConfig supportConfig;

    @Inject
    BankSupportConfigMapping bankSupportConfigMapping;

    @GET
    @Path("/name")
    @Produces(MediaType.TEXT_PLAIN)
    public String getName() {
        return name;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/mobilebanking")
    public Boolean getMobileBanking() {
        return mobileBanking.orElse(false);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/support")
    public HashMap<String, String> getSupport() {
        HashMap<String, String> map = new HashMap<>();
        map.put("email", supportConfig.getEmail());
        map.put("phone", supportConfig.getPhone());

        return map;
    }

    public Map<String, String> getSupportMapping() {
        HashMap<String, String> map = getSupport();

        map.put("bussiness.email", bankSupportConfigMapping.email());
        map.put("bussiness.phone", bankSupportConfigMapping.phone());
        return map;
    }
}
