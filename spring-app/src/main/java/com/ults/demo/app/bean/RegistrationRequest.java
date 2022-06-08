package com.ults.demo.app.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class RegistrationRequest {
    @JsonProperty("name")
    @NotBlank(message = "Name is mandatory")
    private String name;
    @JsonProperty("email")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @JsonProperty("password")
    @NotBlank(message = "Password is mandatory")
    private String password;
    @JsonProperty("referral_code")
    private String referralCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "name='" + name + '\'' +
                ", email=" + email +
                ", password='" + password + '\'' +
                ", referralCode='" + referralCode + '\'' +
                '}';
    }
}
