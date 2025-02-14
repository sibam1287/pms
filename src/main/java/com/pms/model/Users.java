package com.pms.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class Users {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private String mobile;

        private String email;

        private String password;

        @Column(name = "last_login")
        private Date lastLogin;

        @Column(name = "remember_token")
        private String rememberToken;

        @Column(name = "user_id")
        private String userId;

        @Column(name = "user_type")
        private String userType;

        @Column(name = "flag_active")
        private String flagActive;

        @Column(name = "created_at")
        private Date createdAt;

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

        public Date getLastLogin() {
                return lastLogin;
        }

        public void setLastLogin(Date lastLogin) {
                this.lastLogin = lastLogin;
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

        public Date getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
                this.createdAt = createdAt;
        }
}
