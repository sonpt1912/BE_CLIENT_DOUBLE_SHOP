package com.example.be_customer_double_shop.service.Impl;

import com.example.be_customer_double_shop.repository.FavoriteRepository;
import com.example.be_customer_double_shop.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

}
