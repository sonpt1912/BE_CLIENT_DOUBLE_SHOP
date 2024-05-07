package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.dto.request.ProductRequest;
import com.example.be_customer_double_shop.service.DetailProductService;
import com.example.be_customer_double_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DetailProductService detailProductService;

    @PostMapping("/get-all-by-condition")
    public ResponseEntity getAllByCondition(@RequestBody ProductRequest request) throws Exception {
        return new ResponseEntity(productService.getAllByCondition(request), HttpStatus.OK);
    }

    @PostMapping("/get-detail-product-by-product")
    public ResponseEntity getDetailProductByProduct(@RequestBody ProductRequest request) {
        return new ResponseEntity(productService.getDetailProductByProduct(request), HttpStatus.OK);
    }

    @GetMapping("/get-product")
    public ResponseEntity getProduct(@Param("id") long id) throws Exception {
        return new ResponseEntity(productService.getProduct(id), HttpStatus.OK);
    }

    @PostMapping("/get-detail-product")
    public ResponseEntity getDetailProduct(@RequestBody ProductRequest request) {
        return new ResponseEntity(detailProductService.getDetailProductByColorSizeAndProduct(request), HttpStatus.OK);
    }

    //TODO: get top 3 - theo cac loai du kien ( ban chay nhat, moi... )
    @PostMapping("/get-top-3-by-condition")
    public ResponseEntity getTop3ByConditioin(ProductRequest request) throws Exception {
        return new ResponseEntity(productService.getAllByCondition(request), HttpStatus.OK);
    }


}
