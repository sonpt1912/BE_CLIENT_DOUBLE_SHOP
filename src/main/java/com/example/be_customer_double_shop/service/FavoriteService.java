package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.FavoriteRequest;
import com.example.be_customer_double_shop.dto.response.ListResponse;
import com.example.be_customer_double_shop.entity.Favorite;
import com.example.be_customer_double_shop.entity.Product;

import java.util.List;

public interface FavoriteService {

    Favorite addFavorite(FavoriteRequest favorite, String username);

    Object deleteFavortite(Favorite favorite);

    List<Product> getByCustomerId(String username) throws Exception;


}
