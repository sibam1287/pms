package com.pms.service.impl;

import com.pms.dto.LoginDTO;
import com.pms.dto.ResponseData;
import com.pms.model.Users;
import com.pms.repository.UserRepository;
import com.pms.security.BCrypt;
import com.pms.service.LoginService;
import com.pms.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;



    @Override
    public String login(LoginDTO loginDTO) {

        if (loginDTO.getUserName() == null && loginDTO.getMobile() == null) {
            return ResponseData.generateFailedRes("Username (email) or mobile number must be provided", loginDTO);
        }

        Optional<Users> userOptional = Optional.empty();
        if (isValidEmail(loginDTO.getUserName())) {
            userOptional = userRepository.findByEmail(loginDTO.getUserName());
        } else if (isValidMobile(loginDTO.getUserName())) {
            userOptional = userRepository.findByMobile(loginDTO.getUserName());
        } else {
            return ResponseData.generateFailedRes("Invalid email or mobile number format", loginDTO);
        }

        if (!userOptional.isPresent()) {
            return ResponseData.generateFailedRes("Invalid Credentials!", loginDTO);
        }

        Users user = userOptional.get();


        if (user.getFlagActive() != null && "0".equals(user.getFlagActive())) {
            Map<String, String> responseData = new HashMap<>();
            responseData.put("accountStatus", "0");
            return ResponseData.generateSuccessRes("Account is inactive", responseData);
        }

        boolean matched = BCrypt.checkpw(loginDTO.getPassword(), user.getPassword());
        loginDTO.setPassword(null);

        if (matched) {

            user.setLastLogin(new Date());
            userRepository.save(user);

            user.setPassword(null);
            user.setId(null);
            String token = jwtUtil.generateToken(user.getEmail());

            loginDTO.setLoggedUserEmail(user.getEmail());
            loginDTO.setLoggedUserId(user.getUserId());
            loginDTO.setLoggedUserType(user.getUserType());
            loginDTO.setName(user.getName());
            loginDTO.setMobile(user.getMobile());
            loginDTO.setToken(token);

            return ResponseData.generateSuccessRes("Login successful", loginDTO);
        }

        loginDTO.setPassword(null);
        return ResponseData.generateFailedRes("Invalid Credentials!", null);
    }

    @Override
    public String logout(LoginDTO loginDTO) {

        if (loginDTO == null || loginDTO.getToken() == null || loginDTO.getLoggedUserEmail() == null ||
                loginDTO.getLoggedUserId() == null || loginDTO.getLoggedUserType() == null) {
            return ResponseData.generateFailedRes("Invalid input parameters", null, "Failed!");
        }

        String loggedUserEmail = loginDTO.getLoggedUserEmail();
        String loggedUserId = loginDTO.getLoggedUserId();
        String loggedUserType = loginDTO.getLoggedUserType();

        Optional<Users> userOptional = userRepository.findByEmailAndUserIdAndUserType(
                loggedUserEmail, loggedUserId, loggedUserType);

        if (userOptional.isEmpty()) {
            return ResponseData.generateFailedRes("User not found", null, "Failed!");
        }

        Users user = userOptional.get();

        // Validate the token
        boolean isTokenValid = jwtUtil.validateToken(loginDTO.getToken(), user.getEmail());
        if (!isTokenValid) {
            return ResponseData.generateFailedRes("Invalid or expired token", null, "Failed!");
        }

        // Blacklist the token
        boolean isTokenBlacklisted = jwtUtil.blacklistToken(loginDTO.getToken());
        if (!isTokenBlacklisted) {
            return ResponseData.generateFailedRes("Failed to invalidate the token", null, "Failed!");
        }

        return ResponseData.generateSuccessRes("Successfully logged out", null);
    }


    private boolean isValidEmail(String userName) {
        return userName.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    private boolean isValidMobile(String userName) {
        return userName.matches("^\\d{10}$");
    }





}
