package com.pms.service.impl;

import com.pms.dto.ProductDTO;
import com.pms.dto.ResponseData;
import com.pms.model.Product;
import com.pms.model.Users;
import com.pms.repository.ProductRepository;
import com.pms.repository.UserRepository;
import com.pms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository usersRepository;



    public String saveProduct(ProductDTO productDTO) {
        productDTO.trim();
        String loggedUserEmail = productDTO.getLoggedUserEmail();
        String loggedUserId = productDTO.getLoggedUserId();
        String loggedUserType = productDTO.getLoggedUserType();


        Optional<Users> userOptional = usersRepository.findByEmailAndUserIdAndUserType(
                loggedUserEmail, loggedUserId, loggedUserType);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            if (productDTO.getId() == null) {
                Product product = new Product();
                product.setProductName(productDTO.getProductName());
                product.setDescription(productDTO.getDescription());
                product.setImageUrl(productDTO.getImageUrl());
                product.setPrice(productDTO.getPrice());
                product.setCategory(productDTO.getCategory());
                product.setUploadedBy(user.getEmail());
                product.setUploadedAt(new Date());
                product.setFlagDeleted("1");

                productRepository.save(product);
                return ResponseData.generateSuccessRes("success", null);
            } else {

                Optional<Product> existingProductOptional = productRepository.findById(productDTO.getId());

                if (existingProductOptional.isPresent()) {
                    Product existingProduct = existingProductOptional.get();

                    existingProduct.setProductName(productDTO.getProductName());
                    existingProduct.setDescription(productDTO.getDescription());
                    existingProduct.setImageUrl(productDTO.getImageUrl());
                    existingProduct.setPrice(productDTO.getPrice());
                    existingProduct.setCategory(productDTO.getCategory());
                    existingProduct.setUploadedBy(user.getEmail());
                    existingProduct.setUploadedAt(new Date());

                    productRepository.save(existingProduct);
                    return ResponseData.generateSuccessRes("success", null);
                } else {
                    return ResponseData.generateFailedRes("failed", null);
                }
            }
        } else {
            return ResponseData.generateFailedRes("User not found", null);
        }
    }


    @Override
    public String getProduct(ProductDTO productDTO) {
        try {
            productDTO.trim();

            String loggedUserEmail = productDTO.getLoggedUserEmail();
            String loggedUserId = productDTO.getLoggedUserId();
            String loggedUserType = productDTO.getLoggedUserType();

            Optional<Users> userOptional = usersRepository.findByEmailAndUserIdAndUserType(
                    loggedUserEmail, loggedUserId, loggedUserType);

            if (userOptional.isPresent()) {
                System.out.println("User found: " + userOptional.get().getEmail());

                if (productDTO.getId() != null && productDTO.getId() > 0) {
                    Optional<Product> productOptional = productRepository.findById(productDTO.getId());
                    if (productOptional.isPresent()) {
                        Product product = productOptional.get();
                        Map<String, Object> productMap = convertProductToMap(product);

                        return ResponseData.generateSuccessRes("Product retrieved successfully", productMap);
                    } else {
                        return ResponseData.generateFailedRes("Product not found", null);
                    }
                } else {
                    List<Product> products = productRepository.findAll();
                    List<Map<String, Object>> productList = products.stream()
                            .map(this::convertProductToMap)
                            .toList();
                    return ResponseData.generateSuccessRes("Product list retrieved successfully", productList);
                }
            } else {
                return ResponseData.generateFailedRes("User not found", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.generateFailedRes("Server err!", null);
        }
    }



    @Override
    public String deleteProduct(ProductDTO productDTO) {
        productDTO.trim();

        String loggedUserEmail = productDTO.getLoggedUserEmail();
        String loggedUserId = productDTO.getLoggedUserId();
        String loggedUserType = productDTO.getLoggedUserType();

        Optional<Users> userOptional = usersRepository.findByEmailAndUserIdAndUserType(
                loggedUserEmail, loggedUserId, loggedUserType);

        if (userOptional.isPresent()) {
            Optional<Product> productOptional = productRepository.findById(productDTO.getId());

            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                product.setFlagDeleted("0");
                product.setDeletedBy(userOptional.get().getEmail());
                productRepository.save(product);
                return ResponseData.generateSuccessRes("Product deleted successfully", null);
            } else {
                return ResponseData.generateFailedRes("Product not found", null);
            }
        } else {
            return ResponseData.generateFailedRes("User not found", null);
        }
    }

    private Map<String, Object> convertProductToMap(Product product) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", product.getId());
        map.put("productName", product.getProductName());
        map.put("description", product.getDescription());
        map.put("imageUrl", product.getImageUrl());
        map.put("price", product.getPrice());
        map.put("category", product.getCategory());
        map.put("uploadedBy", product.getUploadedBy());
        map.put("uploadedAt", product.getUploadedAt());
        map.put("deletedBy", product.getDeletedBy());
        map.put("flagDeleted", product.getFlagDeleted());
        return map;
    }


}
