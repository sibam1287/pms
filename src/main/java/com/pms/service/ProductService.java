package com.pms.service;

import com.pms.dto.ProductDTO;

public interface ProductService {
    String saveProduct(ProductDTO productDTO);

    String getProduct(ProductDTO productDTO);

    String deleteProduct(ProductDTO productDTO);
}
