package com.example.be_customer_double_shop.service;

import com.example.be_customer_double_shop.dto.request.FavoriteRequest;
import com.example.be_customer_double_shop.entity.Favorite;

public interface FavoriteService {

    Favorite addFavorite(FavoriteRequest favorite, String username);

    Object deleteFavortite(Favorite favorite);

}
