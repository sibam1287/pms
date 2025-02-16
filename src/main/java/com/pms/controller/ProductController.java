package com.pms.controller;

import com.pms.dto.ProductDTO;
import com.pms.dto.ResponseData;
import com.pms.dto.SignupDTO;
import com.pms.repository.ProductRepository;
import com.pms.service.ProductService;
import com.pms.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/saveProduct")
    public String saveProduct(@RequestHeader("Authorization") String authHeader, @RequestBody ProductDTO productDTO) {
        return processRequest(authHeader, () -> productService.saveProduct(productDTO));
    }



    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestHeader("Authorization") String authHeader, @RequestBody ProductDTO productDTO) {
        return processRequest(authHeader, () -> productService.deleteProduct(productDTO));
    }

    private String processRequest(String authHeader, RequestHandler handler) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseData.generateFailedRes("Missing or invalid Authorization header", null);
            }

            String token = authHeader.substring(7);
            String username = jwtUtil.getUsernameFromToken(token);

            if (jwtUtil.validateToken(token, username)) {
                return handler.handle();
            } else {
                return ResponseData.generateFailedRes("Invalid or expired token", null);
            }
        } catch (ExpiredJwtException e) {
            return ResponseData.generateFailedRes("Token has expired", null);
        } catch (JwtException e) {
            return ResponseData.generateFailedRes("Invalid token", null);
        } catch (Exception e) {
            return ResponseData.generateFailedRes("Server error!", e.getMessage());
        }
    }





    

    @FunctionalInterface
    interface RequestHandler {
        String handle();
    }





}
