package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.dto.ValidationException;
import com.example.be_customer_double_shop.dto.request.FavoriteRequest;
import com.example.be_customer_double_shop.entity.Customer;
import com.example.be_customer_double_shop.entity.Favorite;
import com.example.be_customer_double_shop.entity.Product;
import com.example.be_customer_double_shop.repository.FavoriteRepository;
import com.example.be_customer_double_shop.service.CustomerService;
import com.example.be_customer_double_shop.service.FavoriteService;
import com.example.be_customer_double_shop.service.ProductService;
import com.example.be_customer_double_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Override
    public Favorite addFavorite(FavoriteRequest request, String username) {
        Customer customer = customerService.findUserbyUsername(username);
        Product product = productService.getProductById(request.getIdProduct());
        Favorite favorite = Favorite.builder()
                .customer(customer)
                .product(product)
                .build();
        return favoriteRepository.save(favorite);
    }

    @Override
    public Object deleteFavortite(Favorite favorite) {
        try {
            favoriteRepository.deleteById(favorite.getId());
        } catch (Exception e) {
            return new ValidationException(Constant.API001, e.getMessage());
        }
        return Constant.SUCCESS;
    }
}
