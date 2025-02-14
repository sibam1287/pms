package com.pms.controller;

import com.pms.dto.LoginDTO;
import com.pms.dto.ResponseData;
import com.pms.service.LoginService;
import com.pms.util.TokenBlacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("PMS/auth")
public class LoginController {

    @Autowired
    LoginService loginService;



    @PostMapping("/login")
    public String userLogin(@RequestBody LoginDTO loginDTO) {
        try {
            return loginService.login(loginDTO);
        } catch (Exception e) {
            return ResponseData.generateFailedRes("Server err!", null);
        }
    }
    @PostMapping("/logout")
    public String userSignOut(@RequestBody LoginDTO loginDTO) {
        try {
            return loginService.logout(loginDTO);
        } catch (Exception e) {
            return ResponseData.generateFailedRes("Server err!", null);
        }

    }

}
