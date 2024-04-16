package com.example.be_customer_double_shop.controller;

import com.example.be_customer_double_shop.dto.request.FavoriteRequest;
import com.example.be_customer_double_shop.entity.Favorite;
import com.example.be_customer_double_shop.security.JwtProvider;
import com.example.be_customer_double_shop.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/add-favorte")
    public ResponseEntity addFavorite(@RequestBody FavoriteRequest favorite, @RequestHeader("Authorization") String token) {
        String username = jwtProvider.getUsernameFromToken(token);
        return ResponseEntity.ok(favoriteService.addFavorite(favorite, username));
    }

    @PostMapping("/delete-favorite")
    public ResponseEntity deleteFavorite(@RequestBody Favorite favorite) {
        return ResponseEntity.ok(favoriteService.deleteFavortite(favorite));
    }


}
