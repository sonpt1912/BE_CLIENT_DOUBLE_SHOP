package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.request.ProductRequest;
import com.example.be_customer_double_shop.entity.Cart;
import com.example.be_customer_double_shop.entity.DetailProduct;
import com.example.be_customer_double_shop.repository.DetailProductRepository;
import com.example.be_customer_double_shop.service.DetailProductService;
import com.example.be_customer_double_shop.util.DateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DetailProductServiceImpl implements DetailProductService {

    @Autowired
    private DetailProductRepository detailProductRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Object updateDetailProduct(DetailProduct detailProduct, String username) {
        detailProduct.setUpdatedBy(username);
        detailProduct.setUpdatedTime(DateUtil.dateToString4(new Date()));
        return detailProductRepository.save(detailProduct);
    }

    @Override
    public DetailProduct getOneById(Long id) {
        return detailProductRepository.findById(id).get();
    }

    @Override
    public DetailProduct getDetailProductByColorSizeAndProduct(ProductRequest productRequest) {
        return detailProductRepository.getDetailProductByColorAndSizeAndProduct(productRequest.getIdColor(), productRequest.getIdSize(), productRequest.getIdProduct());
    }

    @Override
    public List<DetailProduct> getAllDetailProductById(List<Cart> cartList) {
        List<Long> idDetailProduct = new ArrayList<>();
        for (Cart cart : cartList) {
            idDetailProduct.add(cart.getDetailProduct().getId());
        }
        return detailProductRepository.getAllDetailProductByListId(idDetailProduct);
    }


}
