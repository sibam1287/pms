package com.pms.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupDTO {

    private Long id;
    private String name;
    private String mobile;
    private String email;
    private String password;
    private String confirmPassword;
    private String rememberToken;
    private String userId;
    private String userType;
    private String flagActive;

    public void trim() {
        this.name = safeTrim(this.name);
        this.mobile = safeTrim(this.mobile);
        this.email = safeTrim(this.email);
        this.password = safeTrim(this.password);
        this.confirmPassword = safeTrim(this.confirmPassword);
        this.rememberToken = safeTrim(this.rememberToken);
        this.userId = safeTrim(this.userId);
        this.userType = safeTrim(this.userType);
        this.flagActive = safeTrim(this.flagActive);
    }

    private String safeTrim(String value) {
        return (value == null) ? null : value.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFlagActive() {
        return flagActive;
    }

    public void setFlagActive(String flagActive) {
        this.flagActive = flagActive;
    }
}
