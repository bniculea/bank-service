package com.quarkus.config;

import org.eclipse.microprofile.config.inject.ConfigProperties;

@ConfigProperties(prefix="bank-support")
public class BankSupportConfig {
    private String phone;
    private String email;

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
