package com.pms.controller;

import com.pms.dto.ResponseData;
import com.pms.dto.SignupDTO;
import com.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pms")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/vendorSignUp")
    public String vendorSignUp(@RequestBody SignupDTO signupDTO) {
        try {
            return userService.vendorSignUp(signupDTO);
        } catch (Exception e) {
            return ResponseData.generateFailedRes("Server err!", null);
        }
    }

    @PostMapping("/getAllVendor")
    public String getAllVendor(@RequestBody SignupDTO signupDTO) {
        try {
            return userService.getAllVendor(signupDTO);
        } catch (Exception e) {
            return ResponseData.generateFailedRes("Server err!", null);
        }
    }
}
