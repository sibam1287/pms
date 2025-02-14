package com.pms.service;

import com.pms.dto.LoginDTO;
import org.springframework.stereotype.Service;


public interface LoginService {
    String login(LoginDTO loginDTO);

    String logout(LoginDTO loginDTO);
}
