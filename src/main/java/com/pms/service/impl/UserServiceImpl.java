package com.pms.service.impl;

import com.pms.dto.ResponseData;
import com.pms.dto.SignupDTO;
import com.pms.model.Users;
import com.pms.repository.UserRepository;
import com.pms.security.BCrypt;
import com.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    public String vendorSignUp(SignupDTO signupDTO) {
        try {
            if (userRepository.existsByMobile(signupDTO.getMobile())) {
                return ResponseData.generateFailedRes("Mobile number already registered", null);
            }

            if (userRepository.existsByEmail(signupDTO.getEmail())) {
                return ResponseData.generateFailedRes("Email already registered", null);
            }

            if (!signupDTO.getPassword().equals(signupDTO.getConfirmPassword())) {
                return ResponseData.generateFailedRes("Passwords do not match", null);
            }
            Users user = createNewUsers("V", signupDTO);

            Users savedUser = userRepository.save(user);

            if (savedUser.getId() != null) {
                return ResponseData.generateSuccessRes("success", null);
            } else {
                return ResponseData.generateFailedRes("failed", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.generateFailedRes("Server error ", e.getMessage());
        }
    }



    private Users createNewUsers(String userType, SignupDTO signupDTO) {
        Users user = new Users();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setMobile(signupDTO.getMobile());

        String hashedPassword = BCrypt.hashpw(signupDTO.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hashedPassword);

        user.setUserType(userType);
        user.setCreatedAt(new Date());


        user.setUserId(generateUniqueUserId());

        return user;
    }

    public String generateUniqueUserId() {
        String userId;
        Optional<Users> existingUser;
        do {
            userId = generateUserId();
            existingUser = userRepository.findByUserId(userId);
        } while (existingUser.isPresent());
        return userId;
    }

    public static String generateUserId() {
        String uuid = String.valueOf(Math.abs(UUID.randomUUID().getMostSignificantBits()));
        return uuid.substring(0, Math.min(uuid.length(), 9)).length() == 9
                ? uuid.substring(0, 9)
                : String.format("%09d", Integer.parseInt(uuid.substring(0, Math.min(uuid.length(), 8))));
    }


    @Override
    public String getAllVendor(SignupDTO signupDTO) {
        return null;
    }



}
