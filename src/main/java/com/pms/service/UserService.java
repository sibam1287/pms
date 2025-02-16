package com.pms.service;

import com.pms.dto.SignupDTO;
import org.springframework.stereotype.Service;


public interface UserService {

    String vendorSignUp(SignupDTO signupDTO);


    String getAllVendor(SignupDTO signupDTO);
}
