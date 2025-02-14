package com.pms.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO {


    private String mobile;
    private String password;
    private String name;

    private String loggedUserEmail;
    private String loggedUserId;
    private String loggedUserType;
    private String token;

    private String userName;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoggedUserEmail() {
        return loggedUserEmail;
    }

    public void setLoggedUserEmail(String loggedUserEmail) {
        this.loggedUserEmail = loggedUserEmail;
    }

    public String getLoggedUserId() {
        return loggedUserId;
    }

    public void setLoggedUserId(String loggedUserId) {
        this.loggedUserId = loggedUserId;
    }

    public String getLoggedUserType() {
        return loggedUserType;
    }

    public void setLoggedUserType(String loggedUserType) {
        this.loggedUserType = loggedUserType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
